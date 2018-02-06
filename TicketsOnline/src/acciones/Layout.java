package acciones;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import clases.SVG;
import clases.Seat;

public class Layout extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private HttpServletResponse response = ServletActionContext.getResponse();
	private HttpServletRequest request = ServletActionContext.getRequest();
	private PrintWriter writer = null;
	private String location = null; 
	private String section = null;
	private StringBuffer path = new StringBuffer();
     
	public void get(){
		getParameters();
		getLayout();
 	}

	private void getLayout() {
		ArrayList<Seat> seats = new ArrayList<Seat>();
		seats.add(new Seat("1_A_1", 451.99, false));
		seats.add(new Seat("1_A_2", 452.99, false));
		seats.add(new Seat("1_A_3", 453.99, false));
		seats.add(new Seat("1_A_4", 454.99, false));
		seats.add(new Seat("1_A_5", 456.99, false));
		
		try {
			writer = response.getWriter();
			writer.println(new SVG(path.toString(), seats).getXML());
		}catch(Exception e) {
			e.printStackTrace();
			writer.println(e.getMessage());
		}
	}

	@SuppressWarnings("deprecation")
	private void getParameters() {
		if (getLocation() != null && getSection() != null) { 
			path.append(request.getRealPath(File.separator)).append("\\svg\\section\\").append(getLocation()).append("_").append(getSection()).append(".svg");
		}else if (getLocation() != null){
			path.append(request.getRealPath(File.separator)).append("\\svg\\location\\").append(getLocation()).append(".svg");
		}
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}
	
}