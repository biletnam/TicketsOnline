package clases;

public class Seat {
	private String id = null;
	private double cost = 0;
	private double commision = 0;
	private double subTotal = 0;
	private boolean reserved = false;
	
	public Seat(final String id_, final double cost_, final double commision_, final double subTotal_, final boolean reserved_) {
		id = id_;
		cost = cost_;
		commision = commision_;
		subTotal = subTotal_;
		reserved = reserved_;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public double getCost() {
		return cost;
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	public double getCommision() {
		return commision;
	}

	public void setCommision(double commision) {
		this.commision = commision;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	
}
