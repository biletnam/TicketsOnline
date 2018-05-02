package acciones;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import clases.Correo;
import clases.SQLServerConnection;

public class User extends Json{
	private static final long serialVersionUID = 1L;
	private String userId = null;
	private String name = null;
	private String email = null;
	private String address = null;
	private String phone = null;
	private String password1 = null;
	private String password2 = null;
	private String eventId = null;
	private String companyId = null;
    
	public String add(){
		int n = 0;

		try {
			n = new SQLServerConnection(companyId).consultar1ValorNumerico("select count(*) from tbUsuarios where Email = '" + email + "' ");
			
			if (n > 0) {
				setSuccess(false);
			}else {
				n = new SQLServerConnection(companyId).actualizar("insert into tbUsuarios (Email, Contrasena, Nombre, Direccion, Telefono) values ('"+email+"', '"+password1+"', '"+name+"', '"+address+"', '"+phone+"') ");
				if (n > 0) {
					setSuccess(true);
				}else {
					setSuccess(false);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
 	}
	
	public String login(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		ArrayList<String> arrRow = null;
//		System.err.println("4 eventId = " + session.getAttribute("eventId"));
		String eventId = session.getAttribute("eventId").toString();
		
		try {
			arrRow = new SQLServerConnection(companyId).consultarVector("select Nombre, Direccion, Telefono, Type, UsuarioPkId from tbUsuarios where Email = '"+email+"' and Contrasena = '"+password1+"' ");
	
			if (arrRow.size() > 0) {
				session.setAttribute("userId", arrRow.get(4).toString());
				session.setAttribute("email", email);
				session.setAttribute("password", password1);
				session.setAttribute("name", arrRow.get(0).toString());
				session.setAttribute("address", arrRow.get(1).toString());
				session.setAttribute("phone", arrRow.get(2).toString());
				setUsrType(arrRow.get(3).toString());
				session.setAttribute("eventDesc", new SQLServerConnection(companyId).consultar1Valor("select Titulo from tbEventos where eventoPkId = " + eventId));
				session.setAttribute("eventDate", new SQLServerConnection(companyId).consultar1Valor("select CONVERT(varchar, FechaHora, 102) from tbEventos where eventoPkId = " + eventId));
				
				new SQLServerConnection(companyId).actualizar("delete from tbButacasenProceso where idSesion = "+arrRow.get(4).toString());
				
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
	
	public String remember(){
		String password = null;

		try {
			password = new SQLServerConnection(companyId).consultar1Valor("select Contrasena from tbUsuarios where Email = '"+email+"' ");
			new Correo().enviar(email, "XPTickets - Recordatorio de contraseña", "Este es un mensaje enviado desde el sitio de XPTickets para recordar su contraseña, es la siguiente:<br><br><b>" + password + "</b><br><br>Saludos.");
			setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			setSuccess(false);
		}
		
		return SUCCESS;
 	}
	
	public String isLogued(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		ArrayList<String> arrRow = null;
		
		try {
			eventId = ServletActionContext.getRequest().getParameter("eventId");
//			System.err.println("5 eventId = " + session.getAttribute("eventId"));
			
			if (session.getAttribute("eventId") != null) {
				eventId = session.getAttribute("eventId").toString();
			}
			
//			System.err.println("email = " + session.getAttribute("email"));
//			System.err.println("password = " + session.getAttribute("password"));
			if (session.getAttribute("email") != null && session.getAttribute("password") != null ) {
				email = session.getAttribute("email").toString();
				password1 = session.getAttribute("password").toString();
				arrRow = new SQLServerConnection(companyId).consultarVector("select Nombre, Direccion, Telefono, type, UsuarioPkId from tbUsuarios where Email = '"+email+"' and Contrasena = '"+password1+"' ");

				if (arrRow.size() > 0) {
					session.setAttribute("userId", arrRow.get(4).toString());
					session.setAttribute("email", email);
					session.setAttribute("password", password1);
					session.setAttribute("name", arrRow.get(0).toString());
					session.setAttribute("address", arrRow.get(1).toString());
					session.setAttribute("phone", arrRow.get(2).toString());
					setUsrType(arrRow.get(3).toString());
					session.setAttribute("eventDesc", new SQLServerConnection(companyId).consultar1Valor("select Titulo from tbEventos where eventoPkId = " + eventId));
					session.setAttribute("eventDate", new SQLServerConnection(companyId).consultar1Valor("select CONVERT(varchar, FechaHora, 102) from tbEventos where eventoPkId = " + eventId));
					
					new SQLServerConnection(companyId).actualizar("delete from tbButacasenProceso where idSesion = "+arrRow.get(4).toString());
					
					setSuccess(true);
				}else {
					setSuccess(false);
				}
			}else {
				setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			setSuccess(false);
		}
		return SUCCESS;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

}