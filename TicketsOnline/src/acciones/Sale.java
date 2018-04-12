package acciones;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import clases.SQLServerConnection;

public class Sale extends Json{
	private static final long serialVersionUID = 1L;
    
	public String getGlobal(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		String eventId = session.getAttribute("eventId").toString();

		try {
			//setData(new SQLServerConnection().ejecutarSP("fn_rptReporteVentasPorEventoGlobalTotalizado", new String[]{"2","2018-01-01","2018-04-08", "true", "2"}));
			setData(new SQLServerConnection().ejecutarSP("fn_rptReporteVentasPorEventoGlobalTotalizado", new String[]{"1", null, null, "false", "2"}));
			setSuccess(true);
		}catch(Exception e) {
			e.printStackTrace();
			setSuccess(true);
		}
		
		return SUCCESS;
 	}
	
}