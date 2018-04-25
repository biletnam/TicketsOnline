package acciones;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class Company extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private final String VACIO = "";
	private HttpServletRequest request = null;
	private String id = VACIO;
     
	public String login(){
		request = ServletActionContext.getRequest();
		id = request.getParameter("id").toString();
		request.getSession().setAttribute("companyId", id);
		System.out.println(id);
		System.out.println(request.getSession().getAttribute("companyId"));
		
		return SUCCESS;
 	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}