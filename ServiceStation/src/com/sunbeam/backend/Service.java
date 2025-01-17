package sunbeam.servicestation;

import java.io.Serializable;

public abstract class Service implements Serializable {
	private static final long serialVersionUID = 1L;
	private String desc;
	public Service() {
		this("");
	}
	public Service(String desc) {
		this.desc = desc;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public abstract double price();
	@Override
	public String toString() {
		return desc + " - " + price();
	}
}
