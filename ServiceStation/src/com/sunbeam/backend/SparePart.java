package sunbeam.servicestation;

import java.io.Serializable;

public class SparePart implements Serializable {
	private static final long serialVersionUID = 1L;
	private String desc;
	private double rate;
	public SparePart() {
		this("", 0.0);
	}
	public SparePart(String desc, double rate) {
		this.desc = desc;
		this.rate = rate;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public String toString() {
		return desc + " - " + rate;
	}
}
