package electrogrid.calculator;

public class Device {
	
	private Integer id;
	private Integer estimate_id;
	private String name;
	private Float load;
	private Integer hours;
	private Float units;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getEstimate_id() {
		return estimate_id;
	}
	public void setEstimate_id(Integer estimate_id) {
		this.estimate_id = estimate_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getLoad() {
		return load;
	}
	public void setLoad(Float load) {
		this.load = load;
	}
	public Integer getHours() {
		return hours;
	}
	public void setHours(Integer hours) {
		this.hours = hours;
	}
	
	public Float getUnits() {
		return units;
	}
	public void setUnits(Float units) {
		this.units = units;
	}
	
	public Device() {
		
		this.units = (float) 0.0;
	}
	
	public Device(Integer id, Integer estimate_id, String name, Float load, Integer hours) {
		super();
		this.id = id;
		this.estimate_id = estimate_id;
		this.name = name;
		this.load = load;
		this.hours = hours;
		
		this.units = (load / 1000 ) * hours;
	}
	
	public void calculateUnits() {
		this.units = (this.load / 1000 ) * this.hours;
	}
	
	
	
	
	
	

}
