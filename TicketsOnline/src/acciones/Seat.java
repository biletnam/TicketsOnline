package acciones;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import clases.SQLServerConnection;

public class Seat extends Json{
	private static final long serialVersionUID = 1L;
	private String seatId = null;
	private String eventId = null;
	private String sectionId = null;
	private String index = null;
	private String number = null;
	private String location = null;
	private double grandTotal = 0;
	private String companyId = null;
    
	public String confirm(){
		HttpSession session = ServletActionContext.getRequest().getSession();
//		System.err.println("2 eventId = " + session.getAttribute("eventId"));
		eventId = session.getAttribute("eventId").toString();
//		System.err.println("userId = " + session.getAttribute("userId"));
		String user = session.getAttribute("userId").toString();
		
		try {
			if (new SQLServerConnection(companyId).contar("select count(*) from tbButacasEnProceso where EventoPkId = "+eventId+" and Seccion = '"+sectionId+"' and Butaca = "+index+" and idSesion != '" + user + "'") > 0) {
				setMsg("Ocupado");
				setState(1);	//Ocupado
				setSuccess(false);
			}else if (new SQLServerConnection(companyId).actualizar("delete from tbButacasEnProceso where EventoPkId = "+eventId+" and Seccion = '"+sectionId+"' and Seccion != '0' and Butaca = "+index+" and idSesion = '" + user + "' ") > 0) {
				setMsg("Se borraron");
				setState(0);	//Libre
				setSuccess(true);
			}else {
				try {
					new SQLServerConnection(companyId).actualizar("insert into tbButacasEnProceso (EventoPkId, Seccion, Butaca, NumeroButaca, CajeroPkId, Fecha, UsuarioId, Status, idSesion, Descripcion) values ("+eventId+", '"+sectionId+"', "+index+", '"+number+"', 2, getDate(), 1, 1, '"+user+"', '"+location+"') ");
					setState(2);		//Apartar
				} catch (Exception e) {
					e.printStackTrace();
				}
				setMsg("Libre");
				setSuccess(true);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
 	}
	
	public String confirmSection(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		int n = 0;
//		System.err.println("3 eventId = " + eventId);
//		System.err.println("location = " + location);
//		System.err.println("seccion = " + sectionId);
//		System.err.println("grandTotal = " + grandTotal);
//		System.err.println("userId = " + session.getAttribute("userId"));
		String user = session.getAttribute("userId").toString();
		
		
		try {
			if (grandTotal == 0) {
				new SQLServerConnection(companyId).actualizar("delete from tbButacasEnProceso where eventopkid = "+eventId+" and seccion = '"+sectionId+"' and descripcion = '"+location+"' and idSesion = "+user);
			}
			
			n = new SQLServerConnection(companyId).consultar1ValorNumerico("select boletos from tbpreciosdescuentos where eventopkid = "+eventId+" and seccion = '"+sectionId+"' and descripcion = '"+location+"' ");
			
			if (n > 0){
				new SQLServerConnection(companyId).actualizar("insert into tbButacasEnProceso (EventoPkId, Seccion, Butaca, NumeroButaca, CajeroPkId, Fecha, UsuarioId, Status, idSesion, Descripcion) values ("+eventId+", '"+sectionId+"', "+0+", '"+0+"', 2, getDate(), 1, 1, '"+user+"', '"+location+"') ");
				setSuccess(true);
			}else {
				setSuccess(false);
			}
		}catch(Exception e) {
			e.printStackTrace();
			setSuccess(false);
		}
		
		return SUCCESS;
 	}
	
	public String releaseAll(final String companyId_){ 
		HttpSession session = ServletActionContext.getRequest().getSession();
		String user = null;
		
		companyId = companyId_;
		
		try {
//			System.err.println("userId = " + session.getAttribute("userId"));
			user = session.getAttribute("userId").toString();
			new SQLServerConnection(companyId).actualizar("delete from tbButacasEnProceso where idSesion = '" + user + "' ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
 	}
	
	public String releaseAll(){ 
		HttpSession session = ServletActionContext.getRequest().getSession();
		String user = null;
		companyId = ServletActionContext.getRequest().getParameter("companyId");
		
		try {
			user = session.getAttribute("userId").toString();
			new SQLServerConnection(companyId).actualizar("delete from tbButacasEnProceso where idSesion = '" + user + "' ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
 	}

	public String getSeatId() {
		return seatId;
	}

	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

}