package clases;

public class Seat {
	private String id = null;
	private String code = null;
	private String location = null;
	private double cost = 0;
	private double commision = 0;
	private double subTotal = 0;
	private boolean reserved = false;
	private String idPayment = null;
	
	public Seat(final String id_, final double cost_, final double commision_, final double subTotal_, final boolean reserved_, final String location_, 
			final String code_, final String idPayment_) {
		id = id_;
		code = code_;
		location = location_;
		cost = cost_;
		commision = commision_;
		subTotal = subTotal_;
		reserved = reserved_;
		idPayment = idPayment_;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getIdPayment() {
		return idPayment;
	}

	public void setIdPayment(String idPayment) {
		this.idPayment = idPayment;
	}

}
