package acciones;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import clases.SQLServerConnection;

public class Sale extends Json{
	private static final long serialVersionUID = 1L;
    
	public String getGlobal(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		
		try {

			System.err.println("eventId = " + session.getAttribute("eventId"));
			String eventId = session.getAttribute("eventId").toString();
			
			setData(new SQLServerConnection().ejecutarSP("fn_rptReporteVentasPorEventoGlobalTotalizado", new String[]{eventId, null, null, "false", "2"}));
			setSuccess(true);
		}catch(Exception e) {
			e.printStackTrace();
			setSuccess(true);
		}
		
		return SUCCESS;
 	}
	
}