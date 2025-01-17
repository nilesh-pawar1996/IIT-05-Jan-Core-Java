package sunbeam.servicestation;

public class OilService extends Service {
	private static final long serialVersionUID = 1L;
	private double cost;
	public OilService() {
		this("", 0.0);
	}
	public OilService(String desc, double cost) {
		super(desc);
		this.cost = cost;
	}
	
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	@Override
	public double price() {
		return this.cost;
	}
	
	@Override
	public String toString() {
		return "[O] " + super.toString();
	}
}
