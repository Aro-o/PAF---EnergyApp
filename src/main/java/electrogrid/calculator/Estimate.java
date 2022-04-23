package electrogrid.calculator;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Estimate {

	private Integer id;
	private String name;
	private String user;
	private Timestamp created_at;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Estimate() {
	}

	public Estimate(Integer id, String name, String user, Timestamp created_at) {
		super();
		this.id = id;
		this.name = name;
		this.user = user;
		this.created_at = created_at;
	}

	public Estimate(Integer id, String name, String user) {
		super();
		this.id = id;
		this.name = name;
		this.user = user;
		this.created_at = Timestamp.valueOf(LocalDateTime.now());
	}

	public Estimate(String name, String user) {
		super();
		this.id = null;
		this.name = name;
		this.user = user;
		this.created_at = Timestamp.valueOf(LocalDateTime.now());
	}

}
