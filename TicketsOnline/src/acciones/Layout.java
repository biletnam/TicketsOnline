package acciones;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import clases.SQLServerConnection;
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
	private String seats = null;
     
	public void get(){
		getParameters();
		getLayout();
 	}

	private void getLayout() {
		ArrayList<Seat> seats = new ArrayList<Seat>();
		ArrayList<ArrayList<String>> arrSeats = null;
		ArrayList<String> arrRow = null;
		
		try {
			arrSeats = new SQLServerConnection().consultarMatriz("select PrecioDescuentoPkId, seccion, descripcion, precio, butaca, fila, case mesa when '0' then concat(Fila,'-',NumeroButaca) else concat(Fila,'/',NumeroMesa,'-',NumeroButaca) end, status, cargoPorServicio, EventoPkId from tbPreciosDescuentos where EventoPkId = 1 and Activo = 1 ");
		} catch (Exception e1) {
			arrSeats = new ArrayList<ArrayList<String>>();
			e1.printStackTrace();
		}
		
		for (int i = 0; i < arrSeats.size(); i++) {
			arrRow = arrSeats.get(i);
			seats.add(new Seat(arrRow.get(0).toString(), arrRow.get(1).toString(), arrRow.get(2).toString(), arrRow.get(3).toString(), arrRow.get(4).toString(), arrRow.get(5).toString(), arrRow.get(6).toString(), arrRow.get(7).toString(), arrRow.get(8).toString(), arrRow.get(9).toString())); 
		}
		
		try {
			writer = response.getWriter();
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

	public String getSeats() {
		return seats;
	}

	public void setSeats(String seats) {
		this.seats = seats;
	}
	
}