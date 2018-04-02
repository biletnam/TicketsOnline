package clases;

public class Seat {
	private String id = null;
	private String section = null;
	private String location = null;
	private String index = null;
	private String row = null;
	private String number = null;
	private String status = null;
	private double cost = 0;
	private double commision = 0;
	private double subTotal = 0;
	private boolean reserved = false;
	private String idPayment = null;
	private String eventId = null;
	
	public Seat(final String id_, final String section_, final String location_, final String cost_, final String index_,
			final String row_, final String number_, final String status_, final String commision_, final String eventId_) {
		id = id_;
		section = section_;
		location = location_;
		cost = Double.valueOf(cost_);
		index = index_;
		row = row_;
		number = number_;
		status = status_;
		commision = Double.valueOf(commision_);
		subTotal = cost + commision;
		status = status_;
		eventId = eventId_;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
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

	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	public String getIdPayment() {
		return idPayment;
	}

	public void setIdPayment(String idPayment) {
		this.idPayment = idPayment;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	
}
