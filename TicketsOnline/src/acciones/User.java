package acciones;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

public class User extends Json{
	private static final long serialVersionUID = 1L;
	private String name = null;
	private String email = null;
	private String address = null;
	private String phone = null;
	private String password1 = null;
	private String password2 = null;
    
	public String add(){
		HttpSession session = ServletActionContext.getRequest().getSession();

		System.out.println(name);
		System.out.println(email);
		System.out.println(address);
		System.out.println(phone);
		System.out.println(password1);
		System.out.println(password2);
		session.setAttribute("name", name);
		session.setAttribute("email", email);
		session.setAttribute("address", address);
		session.setAttribute("phone", phone);
		session.setAttribute("password", password1);
		
		setMsg("Alta de usuario con exito, ahora puede iniciar sesion");
		setSuccess(true);
		
		return SUCCESS;
 	}
	
	public String login(){
		HttpSession session = ServletActionContext.getRequest().getSession();

		//validar si es valido el pssw
		//subir a sesion
		session.setAttribute("email", email);
		session.setAttribute("password", password1);
		session.setAttribute("name", "NOMBRE DESDE BD");
		session.setAttribute("address", "DIRECCION DESDE BD");
		session.setAttribute("phone", "TELEBD");
		System.out.println(email);
		System.out.println(password1);
		
		setMsg("Auntenticacion exitosa, puede ingresar");
		setSuccess(true);
		
		return SUCCESS;
 	}
	
	public String remember(){
		HttpSession session = ServletActionContext.getRequest().getSession();

		System.out.println(email);
		System.out.println(password1);
		
		setMsg("Se acaba de enviar la contrasena al correo indicado, con exito");
		setSuccess(true);
		
		return SUCCESS;
 	}
	
	public String isLogued(){
		HttpSession session = ServletActionContext.getRequest().getSession();

		System.out.println(session.getAttribute("email"));
		
		if (session.getAttribute("email") != null) {
			session.setAttribute("name", "NOMBRE DESDE BD");
			session.setAttribute("address", "DIRECCION DESDE BD");
			session.setAttribute("phone", "TELEBD");
			setSuccess(true);
		}else {
			setMsg("Usuario no logueado");
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