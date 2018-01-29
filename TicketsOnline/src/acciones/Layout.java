package acciones;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import clases.ArchivoTexto;

public class Layout extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private HttpServletResponse response = ServletActionContext.getResponse();
	private HttpServletRequest request = ServletActionContext.getRequest();
	private PrintWriter writer = null;
	private String location = null; 
	private String section = null;
	private ArrayList<ArrayList<String>> matrix = null;
	private StringBuffer path = new StringBuffer();
	private StringBuffer html = new StringBuffer();
     
	public void get(){
		getParameters();
		getLayout();
 	}

	private void getLayout() {
		String str = null;
		String id = null;
		ArrayList<String> idsSold = new ArrayList<String>();
		
		idsSold.add("1_A_1");
		idsSold.add("1_A_2");
		idsSold.add("1_A_3");
		idsSold.add("1_A_4");
		idsSold.add("1_A_6");
		idsSold.add("1_A_9");
		idsSold.add("1_A_15");
		idsSold.add("1_A_20");
		idsSold.add("2_A_3");
		idsSold.add("2_A_4");
		
		System.out.println(path);
		
		matrix = new ArchivoTexto(path.toString()).leer();
		
		for (int i = 0; i < matrix.size(); i++) {
			str = ((ArrayList<String>) matrix.get(i)).get(0).toString();
			System.out.println(str);

			if (str.indexOf("<ellipse") > -1) {
				html.append(str).append("\n");
				str = ((ArrayList<String>) matrix.get(++i)).get(0).toString();
				id = str.substring(str.indexOf("id=") + 4, str.length() - 1);
				html.append(str).append("\n");
				System.out.println("id = "+id);
				
				for (int j = 0; j < 4; j++) {
					str = ((ArrayList<String>) matrix.get(++i)).get(0).toString();
					html.append(str).append("\n");
				}
				
				if (idsSold.contains(id)) {
					System.out.println("Si entra");
					html.append("style=\"fill:#999999;fill-opacity:1;stroke:#000000;stroke-width:0.09017658\"\n");
					i++;
				}
			}else {
				html.append(str).append("\n");
			}
		}
		
		try {
			writer = response.getWriter();
			writer.println(html.toString());
		} catch (IOException e) {
			e.printStackTrace();
			writer.println(e.getMessage());
		}
	}

	private void getParameters() {
		if (getLocation() != null && getSection() != null) { 
			path.append(request.getRealPath(File.separator)).append("\\svg\\section\\").append(getLocation()).append("_").append(getSection()).append(".svg");
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