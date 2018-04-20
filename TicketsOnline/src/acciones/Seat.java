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
    
	public String confirm(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		eventId = session.getAttribute("eventId").toString();
		String user = session.getAttribute("userId").toString();
		
		try {
			if (new SQLServerConnection().contar("select count(*) from tbButacasEnProceso where EventoPkId = "+eventId+" and Seccion = '"+sectionId+"' and Butaca = "+index+" and NumeroButaca = '" + number + "' and idSesion != '" + session.getId() + "'") > 0) {
				setMsg("Ocupado");
				setSuccess(false);
			}else if (new SQLServerConnection().actualizar("delete from tbButacasEnProceso where EventoPkId = "+eventId+" and Seccion = '"+sectionId+"' and Seccion != '0' and Butaca = "+index+" and NumeroButaca = '" + number + "' and idSesion = '" + session.getId() + "' ") > 0) {
				setMsg("Se borraron");
				setSuccess(true);
			}else {
				try {
					new SQLServerConnection().actualizar("insert into tbButacasEnProceso values ("+eventId+", '"+sectionId+"', "+index+", '"+number+"', 2, getDate(), 1, 1, '"+session.getId()+"', '"+location+"') ");
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
	
	public String releaseAll(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		
		try {
			new SQLServerConnection().actualizar("delete from tbButacasEnProceso where idSesion = '" + session.getId() + "' ");
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

}