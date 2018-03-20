package acciones;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

public class Seat extends Json{
	private static final long serialVersionUID = 1L;
	private String seatId = null;
    
	public String confirm(){
		System.out.println("confirming.." + seatId);
		
		//preguntar si esta en proceso o reservado
		
		//poner en proceso
		
		//liberar
		if (Math.random() < 0.5) {
			setMsg("Libre");
			setSuccess(true);
		}else {
			setMsg("Ocupado");
			setSuccess(false);
		}
		
		return SUCCESS;
 	}
	
	public String releaseAll(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		
		System.out.println("releasing.." + session.getId());
		
		if (Math.random() < 0.5) {
			setMsg("Libre");
			setSuccess(true);
		}else {
			setMsg("Ocupado");
			setSuccess(false);
		}
		
		return SUCCESS;
 	}

	public String getSeatId() {
		return seatId;
	}

	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}

}