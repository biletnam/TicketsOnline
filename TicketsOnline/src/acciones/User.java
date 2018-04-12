package acciones;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import clases.Correo;
import clases.SQLServerConnection;

public class User extends Json{
	private static final long serialVersionUID = 1L;
	private String name = null;
	private String email = null;
	private String address = null;
	private String phone = null;
	private String password1 = null;
	private String password2 = null;
    
	public String add(){
		int n = 0;

		try {
			n = new SQLServerConnection().consultar1ValorNumerico("select count(*) from tbUsuarios where Email = '" + email + "' ");
			
			if (n > 0) {
				setSuccess(false);
			}else {
				n = new SQLServerConnection().actualizar("insert into tbUsuarios (Email, Contrasena, Nombre, Direccion, Telefono) values ('"+email+"', '"+password1+"', '"+name+"', '"+address+"', '"+phone+"') ");
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
		String eventId = session.getAttribute("eventId").toString();
		
		arrRow = new SQLServerConnection().consultarVector("select Nombre, Direccion, Telefono, Type from tbUsuarios where Email = '"+email+"' and Contrasena = '"+password1+"' ");

		if (arrRow.size() > 0) {
			session.setAttribute("email", email);
			session.setAttribute("password", password1);
			session.setAttribute("name", arrRow.get(0).toString());
			session.setAttribute("address", arrRow.get(1).toString());
			session.setAttribute("phone", arrRow.get(2).toString());
			setUsrType(arrRow.get(3).toString());
			session.setAttribute("eventDesc", new SQLServerConnection().consultar1Valor("select Titulo from tbEventos where eventoPkId = " + eventId));
			session.setAttribute("eventDate", new SQLServerConnection().consultar1Valor("select CONVERT(varchar, FechaHora, 102) from tbEventos where eventoPkId = " + eventId));
			
			setSuccess(true);
		}else {
			setSuccess(false);
		}

		return SUCCESS;
 	}
	
	public String remember(){
		String password = null;

		password = new SQLServerConnection().consultar1Valor("select Contrasena from tbUsuarios where Email = '"+email+"' ");
		new Correo().enviar(email, "XPTickets - Recordatorio de contraseña", "Este es un mensaje enviado desde el sitio de XPTickets para recordar su contraseña, es la siguiente:<br><br><b>" + password + "</b><br><br>Saludos.");
		setSuccess(true);
		
		return SUCCESS;
 	}
	
	public String isLogued(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		ArrayList<String> arrRow = null;
		String eventId = session.getAttribute("eventId").toString();
		
		if (session.getAttribute("email") != null && session.getAttribute("password") != null ) {
			email = session.getAttribute("email").toString();
			password1 = session.getAttribute("password").toString();
			arrRow = new SQLServerConnection().consultarVector("select Nombre, Direccion, Telefono, type from tbUsuarios where Email = '"+email+"' and Contrasena = '"+password1+"' ");

			if (arrRow.size() > 0) {
				session.setAttribute("email", email);
				session.setAttribute("password", password1);
				session.setAttribute("name", arrRow.get(0).toString());
				session.setAttribute("address", arrRow.get(1).toString());
				session.setAttribute("phone", arrRow.get(2).toString());
				setUsrType(arrRow.get(3).toString());
				session.setAttribute("eventDesc", new SQLServerConnection().consultar1Valor("select Titulo from tbEventos where eventoPkId = " + eventId));
				session.setAttribute("eventDate", new SQLServerConnection().consultar1Valor("select CONVERT(varchar, FechaHora, 102) from tbEventos where eventoPkId = " + eventId));
				
				setSuccess(true);
			}else {
				setSuccess(false);
			}
		}else {
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

}