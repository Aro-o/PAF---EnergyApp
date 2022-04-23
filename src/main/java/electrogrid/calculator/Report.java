package electrogrid.calculator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Report {

	private Estimate estimate;
	private List<Device> deviceList;
	private Float Total;

	public Estimate getEstimate() {
		return estimate;
	}

	public void setEstimate(Estimate estimate) {
		this.estimate = estimate;
	}

	public List<Device> getDeviceList() {
		return deviceList;
	}

	public void setDeviceList(List<Device> deviceList) {
		this.deviceList = deviceList;
	}

	public Float getTotal() {
		return Total;
	}

	public void setTotal(Float total) {
		Total = total;
	}

	public Report() {
		this.deviceList = new ArrayList<Device>();
		this.Total = (float) 0.0;
	}

	public void generateReport(Integer estId) {

		DB db = new DB();

		try {
			ResultSet rs = db.getquery("select * from estimates where id='" + estId + "';");

			if (rs.next()) {
				this.estimate = new Estimate(rs.getInt("id"), rs.getString("name"), rs.getString("user"),
						rs.getTimestamp("created_at"));
			}

			ResultSet rsdev = db.getquery("select * from devices where estimate_id=" + estId + ";");

			while (rsdev.next()) {
				Device dev = new Device(rsdev.getInt("id"), rsdev.getInt("estimate_id"), rsdev.getString("name"),
						rsdev.getFloat("load"), rsdev.getInt("hours"));
				this.deviceList.add(dev);
				this.Total += dev.getUnits();

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
