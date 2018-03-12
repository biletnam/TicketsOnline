package acciones;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import clases.Seat;

public class Receipt extends Json{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request = ServletActionContext.getRequest();
	private List<Seat> seats = null;
	private String customerName = null;
	private String dateTime = null;
	private String place = null;
	private String city = null;
	private String state = null;
	private String name = null;
	private String email = null;
	private String phone = null;
	private String eventDescription = null;
     
	public String execute(){
		String paymentID = request.getParameter("paymentID");
		Seat seat = null;
		
		//Consulta DB
		
		eventDescription = "Jesse & Joy - Un besito mas Tour";
		customerName = "Carlos Santana";
		email = "huosantana@hotmail.com";
		phone = "664 315 7108";
		dateTime = "Jul 15, 2018 21:00 hrs.";
		place = "Audiorama, El Trompo";
		city = "Tijuana";
		state = "Baja California";
		
		seats = new ArrayList<Seat>();
		seat = new Seat("1", 100.99, 6, 106, true, "BARRERA SOL.", "F3-33", "990001");
		seats.add(seat);
		seat = new Seat("2", 100.00, 6, 106, true, "BARRERA SOL.", "F3-34", "990002");
		seats.add(seat);
		seat = new Seat("3", 100.00, 6, 106, true, "BARRERA SOL.", "F3-35", "990003");
		seats.add(seat);
		seat = new Seat("4", 100.00, 6, 106, true, "BARRERA SOL.", "F3-36", "990004");
		seats.add(seat);
		
		return SUCCESS;
 	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	
	

}