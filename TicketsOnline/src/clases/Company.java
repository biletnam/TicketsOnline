package clases;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

public class Company extends ActionSupport{
	private static final long serialVersionUID = 1L;

	private final String VACIO = "";
	private String companyId = VACIO;
	private String name = VACIO;
	private String eMail = VACIO;
	private String phone = VACIO;
	private ArrayList<String> arr = null;
    
	public Company(final String companyId_) {
		StringBuffer sb = new StringBuffer();
		
		companyId = companyId_;
		
		try {
			sb.append("select name, email, phone from Configuracion.BasesDatos where companyId = ").append(companyId);
			arr = new SQLServerConnection().consultarVector(sb.toString());
			
			if (arr.size() > 0) {
				name = arr.get(0).toString();
				eMail = arr.get(1).toString();
				phone = arr.get(2).toString();
			}
			
		} catch (Exception e) {
			e.printStackTrace(); 
		} 
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}