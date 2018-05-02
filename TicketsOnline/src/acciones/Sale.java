package acciones;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import clases.SQLServerConnection;

public class Sale extends Json{
	private static final long serialVersionUID = 1L;
	private String companyId = null;
    
	public String getGlobal(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		
		try {
//			System.err.println("1 eventId = " + session.getAttribute("eventId"));
			String eventId = session.getAttribute("eventId").toString();
			companyId = ServletActionContext.getRequest().getParameter("companyId");
			
			setData(new SQLServerConnection(companyId).ejecutarSP("fn_rptReporteVentasPorEventoGlobalTotalizado", new String[]{eventId, null, null, "false", "2"}));
			setSuccess(true);
		}catch(Exception e) {
			e.printStackTrace();
			setSuccess(true);
		}
		
		return SUCCESS;
 	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
}