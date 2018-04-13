package acciones;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import clases.Correo;
import clases.SQLServerConnection;
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
	private boolean sendMail = false;
	private String paymentID = null;
	
     
	public String get(){
		paymentID = request.getParameter("paymentID");
		Seat seat = null;
		StringBuffer sb = new StringBuffer();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String sessionId = session.getId();
		ArrayList<ArrayList<String>> arrSeats = null;
		ArrayList<String> arrRow = null;
		String[] params = new String[35];
		String boletoPkId = "0";
		int i = 0;
		String name = session.getAttribute("name").toString();
		String email = session.getAttribute("email").toString();
		String phone = session.getAttribute("phone").toString();
		
		sb.append("select ");
		sb.append("1, ");
		sb.append("2, ");  
		sb.append("a.EventoPkId, ");
		sb.append("a.Seccion, ");
		sb.append("format(getDate(), 'yyyy-MM-dd'), ");
		sb.append("1, ");
		sb.append("1, ");
		sb.append("b.PrecioDescuentoPkId, ");
		sb.append("1, ");
		sb.append("b.Precio, ");
		sb.append("0, ");
		sb.append("b.Precio + b.Descuento, ");
		sb.append("0, ");
		sb.append("0, ");
		sb.append("0, ");
		sb.append("0, ");
		sb.append("1, ");
		sb.append("0, ");
		sb.append("b.Precio + b.CargoPorServicio, ");
		sb.append("0, ");
		sb.append("'PaypalId', ");
		sb.append("a.Butaca, ");
		sb.append("b.NumeroButaca, ");	
		sb.append("1, ");
		sb.append("0, ");
		sb.append("b.Precio * 0.10, ");
		sb.append("b.Precio * 0.06, ");
		sb.append("1, ");
		sb.append("'").append(name).append("', ");
		sb.append("'").append(email).append("', ");
		sb.append("'").append(phone).append("', ");
		sb.append("'Compra WEB', ");
		sb.append("0, ");
		sb.append("0, ");
		sb.append("0, ");
		sb.append("b.Descripcion, ");
		sb.append("b.Fila ");
		sb.append("from  ");
		sb.append("tbButacasEnProceso a  ");
		sb.append("join tbPreciosDescuentos b  ");
		sb.append("on a.EventoPkId = b.EventoPkId and a.Seccion = b.Seccion and a.Butaca = b.Butaca and a.Descripcion = b.Descripcion ");
		sb.append("where  ");
		sb.append("a.idSesion = '").append(sessionId).append("' ");
		
		try {
			arrSeats = new SQLServerConnection().consultarMatriz(sb.toString());
			seats = new ArrayList<Seat>();
			
			for (int j = 0; j < arrSeats.size(); j++) {
				arrRow = (ArrayList<String>) arrSeats.get(j);
				i = 0;
				params[i++] = arrRow.get(0).toString();		//BoletoPkId
				params[i++] = arrRow.get(1).toString();		//CajeroPkId
				params[i++] = arrRow.get(2).toString();		//EventoPkId
				params[i++] = arrRow.get(3).toString();		//Seccion
				params[i++] = arrRow.get(4).toString();		//FechaHora
				params[i++] = arrRow.get(5).toString();		//TipoPublico
				params[i++] = arrRow.get(6).toString();		//Adulto
				params[i++] = arrRow.get(7).toString();		//PrecioDescuentoPkId
				params[i++] = arrRow.get(8).toString();		//TipoCambio
				params[i++] = arrRow.get(9).toString();		//Precio
				params[i++] = arrRow.get(10).toString();	//Descuento
				params[i++] = arrRow.get(11).toString();	//Importe
				params[i++] = arrRow.get(12).toString();	//Cancelado
				params[i++] = arrRow.get(13).toString();	//Impreso
				params[i++] = arrRow.get(14).toString();	//Usado
				params[i++] = arrRow.get(15).toString();	//Reportado
				params[i++] = arrRow.get(16).toString();	//Personas
				params[i++] = arrRow.get(17).toString();	//PagoEfectivo
				params[i++] = arrRow.get(18).toString();	//PagoTarjetaCredito (Precio mas cargo por servicio)
				params[i++] = arrRow.get(19).toString();	//PagoRecibo
				params[i++] = paymentID;					//FolioRecibo
				params[i++] = arrRow.get(21).toString();	//ControlButaca
				params[i++] = arrRow.get(22).toString();	//NumeroButaca
				params[i++] = arrRow.get(23).toString();	//Status
				params[i++] = arrRow.get(24).toString();	//Abono
				params[i++] = arrRow.get(25).toString();	//CargoPorServicio
				params[i++] = arrRow.get(26).toString();	//CargoPorTarjeta
				params[i++] = arrRow.get(27).toString();	//PagadoConTarjeta
				params[i++] = arrRow.get(28).toString();	//Nombre
				params[i++] = arrRow.get(29).toString();	//eMail
				params[i++] = arrRow.get(30).toString();	//Telefono
				params[i++] = arrRow.get(31).toString();	//Observaciones
				params[i++] = arrRow.get(32).toString();	//BoletoDuro
				params[i++] = arrRow.get(33).toString();	//TransaccionPkId
				params[i++] = "0";							//RetVal
				
				boletoPkId = new SQLServerConnection().ejecutarSPUnRetorno("pc_tbboletosV2_save", params);
				
				if (boletoPkId.compareTo("0") > 0) {
					seat = new Seat(boletoPkId, params[3], arrRow.get(35).toString(), params[18], params[21], arrRow.get(36).toString(), params[22], params[23], params[25], params[2]);
					seats.add(seat);
					
					if (arrRow.get(3).toString().equals("0")) {
						new SQLServerConnection().actualizar("update tbPreciosDescuentos set boletos = boletos - 1 where descripcion = '"+arrRow.get(35).toString()+"' and seccion = '0' ");
					}
				}
				
				arrRow.clear();
				
			}
		
		} catch (Exception e) {
			e.printStackTrace(); 
		} finally {
			new acciones.Seat().releaseAll();
		}
		
		sb.setLength(0);
		sb.append("select a.Titulo, a.FechaHora, b.Descripcion from tbEventos a left join tbEspaciosLugares b on a.ClaveLugar = b.Clave where a.EventoPkId = 2 ");	//CSAXXX
		ArrayList<String> arrRenglon = new SQLServerConnection().consultarVector(sb.toString()); 
		sb.setLength(0);
		
		eventDescription = arrRenglon.get(0).toString();
		customerName = session.getAttribute("name").toString();
		email = session.getAttribute("email").toString();
		phone = session.getAttribute("phone").toString();
		dateTime = arrRenglon.get(1).toString();
		place = arrRenglon.get(2).toString();
		
		if (sendMail) {
			sb.setLength(0);
			sb.append("Estimado cliente:");
			sb.append("<br><br>Se genero el pago exitosamente con el folio ").append(paymentID).append(", se adjunta link para ver documento:");
			sb.append("<br><br><a href=\"http://"+request.getServerName()+":"+request.getServerPort()+"/TicketsOnline/Accion2/viewReceipt?paymentID=").append(paymentID).append("\">Link</a>");
			sb.append("<br><br>Saludos.");
			new Correo().enviar(email, "Comprobante de pago " + paymentID + " " + eventDescription + " en " + place + " a las " + dateTime, sb.toString());
		}
		
		return SUCCESS;
 	}
	
	public String view(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		paymentID = request.getParameter("paymentID");
		Seat seat = null;
		StringBuffer sb = new StringBuffer();
		ArrayList<ArrayList<String>> arrSeats = null;
		ArrayList<String> arrRow = null;
		int i = 0;
		
		sb.append("select "); 
		sb.append("a.BoletoPkId, "); 
		sb.append("a.Seccion, ");
		sb.append("b.Descripcion, "); 
		sb.append("a.PagoTarjetaCredito + a.CargoPorServicio + a.CargoPorTarjeta, "); 
		sb.append("a.ControlButaca, ");
		sb.append("b.Fila, ");
		sb.append("a.NumeroButaca, "); 
		sb.append("a.Status, ");
		sb.append("a.CargoPorServicio, "); 
		sb.append("a.EventoPkId, ");
		sb.append("a.Nombre, ");
		sb.append("a.Email, ");
		sb.append("a.Telefono ");
		sb.append("from ");
		sb.append("tbBoletos a "); 
		sb.append("join tbPreciosDescuentos b on a.PrecioDescuentoPkId = b.PrecioDescuentoPkId "); 
		sb.append("where ");
		sb.append("FolioRecibo = '"+paymentID+"'");
		
		try {
			arrSeats = new SQLServerConnection().consultarMatriz(sb.toString());
			seats = new ArrayList<Seat>();
			
			for (int j = 0; j < arrSeats.size(); j++) {
				arrRow = (ArrayList<String>) arrSeats.get(j);
				seat = new Seat(arrRow.get(0).toString(), arrRow.get(1).toString(), arrRow.get(2).toString(), arrRow.get(3).toString(), arrRow.get(4).toString()
						,arrRow.get(5).toString(), arrRow.get(6).toString(), arrRow.get(7).toString(), arrRow.get(8).toString(), arrRow.get(9).toString()); 
				seats.add(seat);
			}
		
		} catch (Exception e) {
			e.printStackTrace(); 
		} finally {
			new acciones.Seat().releaseAll();
		}
		
		sb.setLength(0);
		sb.append("select a.Titulo, a.FechaHora, b.Descripcion from tbEventos a left join tbEspaciosLugares b on a.ClaveLugar = b.Clave where a.EventoPkId = 2 ");	//CSAXXX
		ArrayList<String> arrRenglon = new SQLServerConnection().consultarVector(sb.toString()); 
		sb.setLength(0);
		
		eventDescription = arrRenglon.get(0).toString();
		customerName = arrRow.get(10).toString();
		email = arrRow.get(11).toString();
		phone = arrRow.get(12).toString();
		dateTime = arrRenglon.get(1).toString();
		place = arrRenglon.get(2).toString();
		
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

	public boolean isSendMail() {
		return sendMail;
	}

	public void setSendMail(boolean sendMail) {
		this.sendMail = sendMail;
	}

	public String getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(String paymentID) {
		this.paymentID = paymentID;
	}

}