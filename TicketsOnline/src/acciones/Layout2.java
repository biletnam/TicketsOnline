package acciones;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import clases.NonNumbered;
import clases.SQLServerConnection;
import clases.SVG;
import clases.Seat;

public class Layout2 extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request = ServletActionContext.getRequest();
	private String location = null; 
	private String section = null;
	private StringBuffer path = new StringBuffer();
	private String svg = null;
	private String companyId = null;
	private String placeId = null;
	private boolean isMain = true;
     
	public String execute(){
		getParameters();
		getLayout();
		return SUCCESS;
 	}

	private void getLayout() {
		ArrayList<Seat> seats = new ArrayList<Seat>();
		ArrayList<NonNumbered> nonNumbereds = new ArrayList<NonNumbered>();
		ArrayList<ArrayList<String>> arrSeats = null;
		ArrayList<ArrayList<String>> arrNonNumbered = null;
		ArrayList<String> arrRow = null;
		
//		System.out.println(isMain);
		
		if (!isMain) {
			//Seats
			try {
				section = section.replace('_', ' ');
				arrSeats = new SQLServerConnection(companyId).consultarMatriz("select PrecioDescuentoPkId, seccion, descripcion, precio, butaca, fila, NumeroButaca, status, cargoPorServicio, EventoPkId from tbPreciosDescuentos where EventoPkId = "+location+" and Seccion = '"+section+"' order by Butaca ");
			} catch (Exception e1) {
				arrSeats = new ArrayList<ArrayList<String>>();
				e1.printStackTrace();
			}
			
			for (int i = 0; i < arrSeats.size(); i++) {
				arrRow = arrSeats.get(i);
				seats.add(new Seat(arrRow.get(0).toString(), arrRow.get(1).toString(), arrRow.get(2).toString(), arrRow.get(3).toString(), arrRow.get(4).toString(), arrRow.get(5).toString(), arrRow.get(6).toString(), arrRow.get(7).toString(), arrRow.get(8).toString(), arrRow.get(9).toString()));
				arrRow.clear();
			}
		}
			
		//Non Numbered
		try {
			arrNonNumbered = new SQLServerConnection(companyId).consultarMatriz("select PrecioDescuentoPkId, seccion, descripcion, precio, butaca, fila, NumeroButaca, status, cargoPorServicio, EventoPkId from tbPreciosDescuentos where EventoPkId = "+location+" and Activo = 1 and Seccion = '0' order by Seccion, Descripcion, Butaca ");
		} catch (Exception e1) {
			arrNonNumbered = new ArrayList<ArrayList<String>>();
			e1.printStackTrace();
		}
		
		for (int i = 0; i < arrNonNumbered.size(); i++) {
			arrRow = arrNonNumbered.get(i);
			nonNumbereds.add(new NonNumbered(arrRow.get(0).toString(), arrRow.get(1).toString(), arrRow.get(2).toString(), arrRow.get(3).toString(), arrRow.get(4).toString(), arrRow.get(5).toString(), arrRow.get(6).toString(), arrRow.get(7).toString(), arrRow.get(8).toString(), arrRow.get(9).toString()));
			arrRow.clear();
		}
		
		try {
			svg = new SVG(path.toString(), seats, nonNumbereds).getXML();
		}catch(Exception e) {
			e.printStackTrace();
			svg = e.getMessage();
		}
	}
	
	@SuppressWarnings("deprecation")
	private void getParameters() {
		if (getPlaceId() != null && getSection() != null) { 
			path.append(request.getRealPath(File.separator)).append("\\svg\\").append(getPlaceId()).append("\\").append(getSection()).append(".svg");
			isMain = false;
		}else if (getPlaceId() != null){
			path.append(request.getRealPath(File.separator)).append("\\svg\\").append(getPlaceId()).append("\\main.svg");
			isMain = true;
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

	public String getSvg() {
		return svg;
	}

	public void setSvg(String svg) {
		this.svg = svg;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

}