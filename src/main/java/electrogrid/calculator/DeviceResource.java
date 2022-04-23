package electrogrid.calculator;

import java.sql.ResultSet;
import java.sql.SQLException;
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

@Path("/devices")
public class DeviceResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Device> getIt() {

		List<Device> devices = new ArrayList<Device>();

		DB db = new DB();

		try {
			ResultSet rs = db.getquery("select * from devices");

			while (rs.next()) {
				Device dev = new Device(rs.getInt("id"), rs.getInt("estimate_id"), rs.getString("name"),
						rs.getFloat("load"), rs.getInt("hours"));
				devices.add(dev);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return devices;
	}

	@POST
	public Response create(Device dev) {

		dev.calculateUnits();

		DB db = new DB();

		System.out.println("INSERT INTO devices(estimate_id, name, `load`, hours, units) VALUES("
				+ dev.getEstimate_id().toString() + ", '" + dev.getName() + "', " + dev.getLoad().toString() + ", "
				+ dev.getHours().toString() + ", " + dev.getUnits().toString() + ");");

		try {
			db.updatequery("INSERT INTO electro_grid.devices(estimate_id, name, `load`, hours, units) VALUES("
					+ dev.getEstimate_id().toString() + ", '" + dev.getName() + "', " + dev.getLoad().toString() + ", "
					+ dev.getHours().toString() + ", " + dev.getUnits().toString() + ");");

			return Response.created(null).build();

		} catch (SQLException e1) {
			e1.printStackTrace();

			return Response.status(Status.BAD_REQUEST).build();
		}

	}

	@PUT
	@Path("/{devId}")
	public Response update(@PathParam("devId") String devId, Device dev) {

		dev.calculateUnits();

		DB db = new DB();

		try {

			ResultSet rs = db.getquery("select * from devices where id='" + devId + "';");

			if (rs.next()) {
				db.updatequery("update devices set name='" + dev.getName() + "', `load`=" + dev.getLoad().toString()
						+ " , hours=" + dev.getHours().toString() + " , units=" + dev.getHours().toString() + " where id=" + devId + ";");
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
	@Path("/{devId}")
	public Response update(@PathParam("devId") String devId) {

		DB db = new DB();

		try {

			ResultSet rs = db.getquery("select * from devices where id='" + devId + "';");

			if (rs.next()) {
				db.updatequery("delete FROM devices where id=" + devId + ";");
				return Response.ok().build();
			} else {
				return Response.status(Status.NOT_FOUND).build();
			}

		} catch (SQLException e1) {
			e1.printStackTrace();

			return Response.status(Status.BAD_REQUEST).build();
		}

	}

}
