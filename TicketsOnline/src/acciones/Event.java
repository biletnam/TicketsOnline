package acciones;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import clases.SQLServerConnection;

public class Event extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final String VACIO = "";
	
	private HttpServletRequest request = null;
	private String id = VACIO;
	//Event
	private String productorId = VACIO;
	private String tittle = VACIO;
	private String placeId = VACIO;
	private String dateTime = VACIO;
	private String description = VACIO;
	private String latitude = VACIO;
	private String longitude = VACIO;
	private String artistName = VACIO;
	private String eventType = VACIO;
	private String shortDay = VACIO;
	private String shortMonth = VACIO;
	private String shortYear = VACIO;
	private String wholeDate = VACIO;
	private String time = VACIO;
	private String phone = VACIO;
	private String companyId = VACIO;
	private String artistId = VACIO;
	
	//Company
	private String nameCompany = VACIO;
	private String phoneCompany = VACIO;
	private String emailCompany = VACIO;
	private String payPalKey = VACIO;
     
	public String get(){
		request = ServletActionContext.getRequest();
		id = request.getParameter("id").toString();
		companyId = request.getParameter("companyId").toString();
		request.getSession().setAttribute("eventId", id);
		request.getSession().setAttribute("companyId", companyId);
		StringBuffer sb = new StringBuffer();
		ArrayList<String> arr = null;

		try {
			sb.append("select ");
			sb.append("a.EventoPkId, a.ProductorPkId, a.Titulo, a.ClaveLugar, a.FechaHora, ");
			sb.append("b.Descripcion, b.lat, b.lon, c.nombreArtista, c.tipoEvento, ");
			sb.append("c.diaCorto, c.mesCorto, c.anoCorto, c.fechaCompleta, c.hora, d.Telefono, ");
			sb.append("b.clave, e.artistaPkId, e.nombre ");
			sb.append("from ");
			sb.append("tbEventos a ");
			sb.append("join tbEspaciosLugares b on a.ClaveLugar = b.Clave ");
			sb.append("join Configuracion.tbEventosComplemento c on a.EventoPkId = c.EventoPkId ");
			sb.append("join tbProductores d on a.ProductorPkId = d.ProductorPkId ");
			sb.append("join tbArtistas e on a.artistaPkId = e.artistaPkId ");
			sb.append("where ");
			sb.append("a.EventoPkId = '").append(id).append("' ");
			arr = new SQLServerConnection(companyId).consultarVector(sb.toString());
			sb.setLength(0);
			
//			System.err.println(arr);
			
			if (arr.size() > 0) {
				id = arr.get(0).toString();
				productorId = arr.get(1).toString();
				tittle = arr.get(2).toString();
				placeId = arr.get(3).toString();
				dateTime = arr.get(4).toString();
				description = arr.get(5).toString();
				latitude = arr.get(6).toString();
				longitude = arr.get(7).toString();
				artistName = arr.get(18).toString();
				eventType = arr.get(9).toString();
				shortDay = arr.get(10).toString();
				shortMonth = arr.get(11).toString();
				shortYear = arr.get(12).toString();
				wholeDate = arr.get(13).toString();
				time = arr.get(14).toString();
				phone = arr.get(15).toString();
				artistId = arr.get(17).toString();
				arr.clear();
			}
			
			sb.append("select name, email, phone, payPalKey from Configuracion.BasesDatos where companyId = ").append(companyId);
			arr = new SQLServerConnection().consultarVector(sb.toString());
			sb.setLength(0);
			
//			System.err.println(arr);
			
			if (arr.size() > 0) {
				nameCompany = arr.get(0).toString();
				emailCompany = arr.get(1).toString();
				phoneCompany = arr.get(2).toString();
				payPalKey = arr.get(3).toString();
				arr.clear();
			}
			
		} catch (Exception e) {
			e.printStackTrace(); 
		} 
		
		return SUCCESS;
 	}
	
	public ArrayList<ArrayList<String>> getCurrent(final String companyId_){
		StringBuffer sb = new StringBuffer();
		ArrayList<ArrayList<String>> mat = null;

		try {
			sb.append("select ");
			sb.append("a.EventoPkId, a.ProductorPkId, a.Titulo, a.ClaveLugar, a.FechaHora, ");
			sb.append("b.Descripcion, b.lat, b.lon, c.nombreArtista, c.tipoEvento, ");
			sb.append("c.diaCorto, c.mesCorto, c.anoCorto, c.fechaCompleta, c.hora, d.Telefono, ");
			sb.append("b.clave, e.artistaPkId, e.nombre ");
			sb.append("from ");
			sb.append("tbEventos a ");
			sb.append("join tbEspaciosLugares b on a.ClaveLugar = b.Clave ");
			sb.append("join Configuracion.tbEventosComplemento c on a.EventoPkId = c.EventoPkId ");
			sb.append("join tbProductores d on a.ProductorPkId = d.ProductorPkId ");
			sb.append("join tbArtistas e on a.artistaPkId = e.artistaPkId ");
			sb.append("where ");
			sb.append("a.cancelado = 0 "); 
			sb.append("and a.agotado = 0 "); 
			sb.append("and a.fechaHora >= getdate() ");
			sb.append("order by a.FechaHora ");
			mat = new SQLServerConnection(companyId_).consultarMatriz(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
			mat = new ArrayList<ArrayList<String>>();
		} 
		
//		System.err.println(mat);
		
		return mat;
 	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductorId() {
		return productorId;
	}

	public void setProductorId(String productorId) {
		this.productorId = productorId;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getShortDay() {
		return shortDay;
	}

	public void setShortDay(String shortDay) {
		this.shortDay = shortDay;
	}

	public String getShortMonth() {
		return shortMonth;
	}

	public void setShortMonth(String shortMonth) {
		this.shortMonth = shortMonth;
	}

	public String getShortYear() {
		return shortYear;
	}

	public void setShortYear(String shortYear) {
		this.shortYear = shortYear;
	}

	public String getWholeDate() {
		return wholeDate;
	}

	public void setWholeDate(String wholeDate) {
		this.wholeDate = wholeDate;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getNameCompany() {
		return nameCompany;
	}

	public void setNameCompany(String nameCompany) {
		this.nameCompany = nameCompany;
	}

	public String getPhoneCompany() {
		return phoneCompany;
	}

	public void setPhoneCompany(String phoneCompany) {
		this.phoneCompany = phoneCompany;
	}

	public String getEmailCompany() {
		return emailCompany;
	}

	public void setEmailCompany(String emailCompany) {
		this.emailCompany = emailCompany;
	}

	public String getPayPalKey() {
		return payPalKey;
	}

	public void setPayPalKey(String payPalKey) {
		this.payPalKey = payPalKey;
	}

	public String getArtistId() {
		return artistId;
	}

	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}
	
}