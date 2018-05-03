package acciones;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import clases.Correo;
import clases.SQLServerConnection;
import clases.Seat;

public class Receipt extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
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
	private ArrayList<Seat> seats = new ArrayList<Seat>();
	private String companyId = null;
	
     
	public String get(){
		HttpServletRequest request = null;
		HttpSession s = null;
		Seat seat = null;
		StringBuffer sb = new StringBuffer();
		String userId = null;
		String eventId = null;
		ArrayList<ArrayList<String>> arrSeats = null;
		ArrayList<String> arrRow = null;
		String[] params = new String[35];
		String boletoPkId = "0";
		ArrayList<String> arrRenglon = null;
		String description = null;
		String row = null;

		try {
			request = ServletActionContext.getRequest();
			s = request.getSession();
			paymentID = request.getParameter("paymentID");
			
			System.err.println("****************PaymentId = "+paymentID);
			
			//Validation
//			System.err.println("evento = " + s.getAttribute("eventId"));
			if (s.getAttribute("eventId") != null && s.getAttribute("eventId").toString().compareTo("") > 0) {
				eventId = s.getAttribute("eventId").toString();
			}else {
//				System.err.println("**************** No hay id de evento ****************");
			}
			
//			System.err.println("***************user = " + s.getAttribute("userId"));
			if (s.getAttribute("userId") != null && s.getAttribute("userId").toString().compareTo("") > 0) {
				userId = s.getAttribute("userId").toString();
			}else {
//				System.err.println("**************** No hay id de user ****************");
			}
			
//			System.err.println("name = " + s.getAttribute("name"));
			if (s.getAttribute("name") != null && s.getAttribute("name").toString().compareTo("") > 0) {
				name = s.getAttribute("name").toString();
			}else {
				s.setAttribute("name", new SQLServerConnection(companyId).consultar1Valor("select Nombre from tbusuarios where UsuarioPkId = '"+userId+"' "));
				name = s.getAttribute("name").toString();
			}
			
//			System.err.println("email = " + s.getAttribute("email"));
			if (s.getAttribute("email") != null && s.getAttribute("email").toString().compareTo("") > 0) {
				email = s.getAttribute("email").toString();
			}else {
				s.setAttribute("email", new SQLServerConnection(companyId).consultar1Valor("select email from tbusuarios where UsuarioPkId = '"+userId+"' "));
				email = s.getAttribute("email").toString();
			}
			
//			System.err.println("phone = " + s.getAttribute("phone"));
			
			if (s.getAttribute("phone") != null && s.getAttribute("phone").toString().compareTo("") > 0) {
				phone = s.getAttribute("phone").toString();
			}else {
				s.setAttribute("phone", new SQLServerConnection(companyId).consultar1Valor("select Telefono from tbusuarios where UsuarioPkId = '"+userId+"' "));
				phone = s.getAttribute("phone").toString();
			}
			
			//Query
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
			sb.append("b.CargoPorServicio, ");
			sb.append("(b.Precio + b.CargoPorServicio) * 0.06, ");
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
			sb.append("on a.EventoPkId = b.EventoPkId and a.Seccion = b.Seccion and a.Descripcion = b.Descripcion and a.Butaca = b.Butaca ");
			sb.append("where  ");
			sb.append("b.Activo = 1 and a.idSesion = '").append(userId).append("' ");
			arrSeats = new SQLServerConnection(companyId).consultarMatriz(sb.toString());
			sb.setLength(0);
//			System.err.println("****************Butacas en proceso = "+arrSeats);
			
			for (int j = 0; j < arrSeats.size(); j++) {
				arrRow = (ArrayList<String>) arrSeats.get(j);
//				System.err.println("Butaca = " + arrRow);
				params[0] = arrRow.get(0).toString();		//BoletoPkId
				params[1] = arrRow.get(1).toString();		//CajeroPkId
				params[2] = arrRow.get(2).toString();		//EventoPkId
				params[3] = arrRow.get(3).toString();		//Seccion
				params[4] = arrRow.get(4).toString();		//FechaHora
				params[5] = arrRow.get(5).toString();		//TipoPublico
				params[6] = arrRow.get(6).toString();		//Adulto
				params[7] = arrRow.get(7).toString();		//PrecioDescuentoPkId
				params[8] = arrRow.get(8).toString();		//TipoCambio
				params[9] = arrRow.get(9).toString();		//Precio
				params[10] = arrRow.get(10).toString();	//Descuento
				params[11] = arrRow.get(11).toString();	//Importe
				params[12] = arrRow.get(12).toString();	//Cancelado
				params[13] = arrRow.get(13).toString();	//Impreso
				params[14] = arrRow.get(14).toString();	//Usado
				params[15] = arrRow.get(15).toString();	//Reportado
				params[16] = arrRow.get(16).toString();	//Personas
				params[17] = arrRow.get(17).toString();	//PagoEfectivo
				params[18] = arrRow.get(18).toString();	//PagoTarjetaCredito (Precio mas cargo por servicio)
				params[19] = arrRow.get(19).toString();	//PagoRecibo
				params[20] = paymentID;					//FolioRecibo
				params[21] = arrRow.get(21).toString();	//ControlButaca
				params[22] = arrRow.get(22).toString();	//NumeroButaca
				params[23] = arrRow.get(23).toString();	//Status
				params[24] = arrRow.get(24).toString();	//Abono
				params[25] = arrRow.get(25).toString();	//CargoPorServicio
				params[26] = arrRow.get(26).toString();	//CargoPorTarjeta
				params[27] = arrRow.get(27).toString();	//PagadoConTarjeta
				params[28] = arrRow.get(28).toString();	//Nombre
				params[29] = arrRow.get(29).toString();	//eMail
				params[30] = arrRow.get(30).toString();	//Telefono
				params[31] = arrRow.get(31).toString();	//Observaciones
				params[32] = arrRow.get(32).toString();	//BoletoDuro
				params[33] = arrRow.get(33).toString();	//TransaccionPkId
				params[34] = arrRow.get(34).toString(); //RetVal
				
				boletoPkId = new SQLServerConnection(companyId).ejecutarSPUnRetorno("pc_tbboletosV2_save", params);	//CSAXX 
//				boletoPkId = "1000";		//CSAXXX
				description = arrRow.get(35).toString();	//Descripcion
				row = arrRow.get(36).toString();	//Fila
				
				System.err.println("ID Boleto = "+boletoPkId);
				
				if (boletoPkId.compareTo("0") > 0) {
					seat = new Seat(boletoPkId, params[3], description, params[18], params[21], row, params[22], params[23], params[25], params[2]);
					seats.add(seat);
					
					if (params[3].equals("0")) {
						new SQLServerConnection(companyId).actualizar("update tbPreciosDescuentos set boletos = boletos - 1 where descripcion = '"+description+"' and seccion = '0' ");
					}
				}
				
				arrRow.clear();
				
			}
		
			sb.append("select a.Titulo, a.FechaHora, b.Descripcion from tbEventos a left join tbEspaciosLugares b on a.ClaveLugar = b.Clave where a.EventoPkId = '"+eventId+"' ");
			arrRenglon = new SQLServerConnection(companyId).consultarVector(sb.toString()); 
			sb.setLength(0);
			eventDescription = arrRenglon.get(0).toString();
			dateTime = arrRenglon.get(1).toString();
			place = arrRenglon.get(2).toString();
			
			if (sendMail) {
				sb.append("Estimado cliente:");
				sb.append("<br><br>Se genero el pago exitosamente con el folio ").append(paymentID).append(", se adjunta link para ver documento:");
				sb.append("<br><br><a href=\"http://"+request.getServerName()+":"+request.getServerPort()+"/Accion2/viewReceipt?paymentID=").append(paymentID).append("&companyId=").append(companyId).append("\">Link</a>");
				sb.append("<br><br>Saludos.");

				try {
					new Correo().enviar(email, "Comprobante de pago " + paymentID + " " + eventDescription + " en " + place + " a las " + dateTime, sb.toString());
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); 
		} finally {
			new acciones.Seat().releaseAll(companyId);
		}
		
		return SUCCESS;
 	}
	
	public String view(){
		HttpServletRequest request = null;
		Seat seat = null;
		StringBuffer sb = new StringBuffer();
		ArrayList<ArrayList<String>> arrSeats = null;
		ArrayList<String> arrRow = null;
		String eventId = null;

		try {
			request = ServletActionContext.getRequest();
			paymentID = request.getParameter("paymentID");
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
		
			arrSeats = new SQLServerConnection(companyId).consultarMatriz(sb.toString());
			sb.setLength(0);
			
			seats = new ArrayList<Seat>();
			
//			System.err.println(arrSeats);
			
			for (int j = 0; j < arrSeats.size(); j++) {
				arrRow = (ArrayList<String>) arrSeats.get(j);
				seat = new Seat(arrRow.get(0).toString(), arrRow.get(1).toString(), arrRow.get(2).toString(), arrRow.get(3).toString(), arrRow.get(4).toString()
						,arrRow.get(5).toString(), arrRow.get(6).toString(), arrRow.get(7).toString(), arrRow.get(8).toString(), arrRow.get(9).toString());
				eventId = seat.getEventId();
				name = arrRow.get(10).toString();
				email = arrRow.get(11).toString();
				phone = arrRow.get(12).toString();
				seats.add(seat);
			}
	
			sb.append("select a.Titulo, a.FechaHora, b.Descripcion from tbEventos a left join tbEspaciosLugares b on a.ClaveLugar = b.Clave where a.EventoPkId = "+eventId);
			ArrayList<String> arrRenglon = new SQLServerConnection(companyId).consultarVector(sb.toString()); 
			sb.setLength(0);
			eventDescription = arrRenglon.get(0).toString();
			dateTime = arrRenglon.get(1).toString();
			place = arrRenglon.get(2).toString();
		} catch (Exception e) {
			e.printStackTrace(); 
		} 
		
		return SUCCESS;
 	}

	public ArrayList<Seat> getSeats() {
		return seats;
	}

	public void setSeats(ArrayList<Seat> seats) {
		this.seats = seats;
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

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

}