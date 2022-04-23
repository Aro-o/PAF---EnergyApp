package electrogrid.calculator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/estimates")
public class EstimateResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Estimate> getIt() {

		List<Estimate> estimates = new ArrayList<Estimate>();

		DB db = new DB();
		try {
			ResultSet estimatesrs = db.getquery("select * from estimates");

			while (estimatesrs.next()) {
				Estimate e = new Estimate(estimatesrs.getInt("id"), estimatesrs.getString("name"),
						estimatesrs.getString("user"), estimatesrs.getTimestamp("created_at"));

				estimates.add(e);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return estimates;
	}

	@GET
	@Path("/{estId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getItall(@PathParam("estId") String estId) {

		List<Estimate> estimates = new ArrayList<Estimate>();

		DB db = new DB();
		try {
			ResultSet estimatesrs = db.getquery("select * from estimates where id='" + estId + "';");

			while (estimatesrs.next()) {
				Estimate e = new Estimate(estimatesrs.getInt("id"), estimatesrs.getString("name"),
						estimatesrs.getString("user"), estimatesrs.getTimestamp("created_at"));

				estimates.add(e);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			return Response.ok(estimates.get(0)).build();
		} catch (Exception e) {
			// TODO: handle exception
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@POST
	public Response create(Estimate e) {

		System.out.println(e.getName());
		System.out.println(e.getUser());

		DB db = new DB();
		try {
			db.updatequery("insert into estimates (name,`user`) values('" + e.getName() + "' , " + e.getUser() + ");");

			return Response.created(null).build();

		} catch (SQLException e1) {
			e1.printStackTrace();

			return Response.status(Status.BAD_REQUEST).build();
		}

	}

	@PUT
	@Path("/{estId}")
	public Response update(@PathParam("estId") String estId, Estimate e) {

		DB db = new DB();

		try {

			ResultSet rs = db.getquery("select * from estimates where id='" + estId + "';");

			if (rs.next()) {
				db.updatequery("update estimates set name='" + e.getName() + "' , `user`=" + e.getUser() + " where id="
						+ estId + ";");
				return Response.ok().build();
			} else {
				return Response.status(Status.NOT_FOUND).build();
			}

		} catch (SQLException e1) {
			e1.printStackTrace();

			return Response.status(Status.BAD_REQUEST).build();
		}

	}

	@DELETE
	@Path("/{estId}")
	public Response update(@PathParam("estId") String estId) {

		DB db = new DB();

		try {

			ResultSet rs = db.getquery("select * from estimates where id='" + estId + "';");

			if (rs.next()) {
				db.updatequery("delete FROM estimates where id=" + estId + ";");
				return Response.ok().build();
			} else {
				return Response.status(Status.NOT_FOUND).build();
			}

		} catch (SQLException e1) {
			e1.printStackTrace();

			return Response.status(Status.BAD_REQUEST).build();
		}

	}
	
	@GET
	@Path("/{estId}/report")
	@Produces(MediaType.APPLICATION_JSON)
	public Report getreport(@PathParam("estId") Integer estId) {

		Report rep =  new Report();
		
		rep.generateReport(estId);

		return rep;
	}

}
