package servlets;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crystaldecisions.sdk.occa.report.application.ParameterFieldController;
import com.crystaldecisions.sdk.occa.report.application.ReportClientDocument;
import com.crystaldecisions.sdk.occa.report.data.Fields;
import com.crystaldecisions.sdk.occa.report.data.IParameterField;
import com.crystaldecisions.sdk.occa.report.exportoptions.ReportExportFormat;
import com.crystaldecisions.sdk.occa.report.lib.ReportSDKException;

@WebServlet("/Reporte")
public class Reporte extends HttpServlet {
	private ByteArrayInputStream flujoEntrada = null;
	private static final long serialVersionUID = 1L;
	private HttpServletRequest req = null;
	private HttpServletResponse res = null;
	private long tamano = 0;
	private String contexto = null;
	private String nombreReporte = null; 
	private String tipo = null;
	private String msgError = null;
	private String eventId = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reporte() {
        super(); 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma","no-cache");
		response.setDateHeader("Expires", 0); 
		procesar(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void procesar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		req = request;
		res = response;
		
		System.err.println("6 eventId = " + request.getSession().getAttribute("eventId"));
		eventId = request.getSession().getAttribute("eventId").toString();
		contexto = req.getSession().getServletContext().getRealPath("/rpt");
		
		if (req.getParameter("nombre") != null){
			nombreReporte = req.getParameter("nombre");
			tipo = req.getParameter("tipo");
			
			if (leerReporte()){
			
				if (pasarArchivo()){
					ponerEnResponse();
				}
				
			}else{
				mandarMensajeResponse();
			}
			
		}else{
			msgError = "Favor de mandar el parametro nombre"; 
			mandarMensajeResponse();
		}

	}
	
	private void ponerEnResponse() {
		FileInputStream fileInputStream = null;
		OutputStream responseOutputStream = null;
		
		res.setContentType("application/" + tipo);
		res.setContentLength((int) tamano);
		try {
			if (tipo.equalsIgnoreCase("PDF")) {
				fileInputStream = new FileInputStream(contexto + "\\" + nombreReporte + ".pdf");
			}else {
				fileInputStream = new FileInputStream(contexto + "\\" + nombreReporte + ".xls");
			}
			
			responseOutputStream = res.getOutputStream();
			int bytes;
			while ((bytes = fileInputStream.read()) != -1) {
				responseOutputStream.write(bytes);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				fileInputStream.close();
				responseOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void mandarMensajeResponse() {
		try {
			res.getWriter().println(msgError);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * 
	 */
	private boolean leerReporte(){
		ReportClientDocument reporte = new ReportClientDocument();
		boolean resultado = true;
		ParameterFieldController paramController = null;
		Fields<IParameterField> parametrosReporte = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		
		try {
			reporte.open(contexto + "\\" + nombreReporte + ".rpt", 0);
			paramController = reporte.getDataDefController().getParameterFieldController();
			parametrosReporte = reporte.getDataDefinition().getParameterFields();
			
			for (int i = 0; i < parametrosReporte.size(); i++) {
				if (req.getParameter(parametrosReporte.get(i).getName()) != null){
			
					switch (parametrosReporte.get(i).getType().value()) {
						case 9:
							date = sdf.parse(req.getParameter(parametrosReporte.get(i).getName()));
							paramController.setCurrentValue("",parametrosReporte.get(i).getName() , date);
							date = null;
							break;
						case 8:
							paramController.setCurrentValue("",parametrosReporte.get(i).getName(), Boolean.valueOf(req.getParameter(parametrosReporte.get(i).getName()).toString()));
							break;
						default:
							paramController.setCurrentValue("",parametrosReporte.get(i).getName(), req.getParameter(parametrosReporte.get(i).getName()).toString());
					}
				}
				
			}
			
			if (resultado) {
				if (tipo.equalsIgnoreCase("PDF")) {
					flujoEntrada = (ByteArrayInputStream) reporte.getPrintOutputController().export(ReportExportFormat.PDF);
				}else {
					flujoEntrada = (ByteArrayInputStream) reporte.getPrintOutputController().export(ReportExportFormat.MSExcel);
				}
			}
			
		} catch (Exception e) {
			resultado = false;
			msgError = e.getMessage();
			e.printStackTrace();
		} finally{
			try {
				reporte.close();
			} catch (ReportSDKException e) {
				e.printStackTrace();
			}
		}
		
		return resultado;
	}
	
	/**
	 * 
	 */
	private boolean pasarArchivo(){
		boolean resultado = true;
		byte byteArray[] = null;
        File file = null;
        FileOutputStream fileOutputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;	             
        int x = 0;
		
        try {
        	byteArray = new byte[flujoEntrada.available()];
        	
        	if (tipo.equalsIgnoreCase("PDF")) {
        		file = new File(contexto + "\\" + nombreReporte + ".pdf");
        	}else {
        		file = new File(contexto + "\\" + nombreReporte + ".xls");
        	}
        	
            fileOutputStream = new FileOutputStream(file);
            byteArrayOutputStream = new ByteArrayOutputStream(flujoEntrada.available());	             
            x = flujoEntrada.read(byteArray, 0, flujoEntrada.available());
            byteArrayOutputStream.write(byteArray, 0, x);
            byteArrayOutputStream.writeTo(fileOutputStream);
        } catch (Exception e) {
        	resultado = false;
            e.printStackTrace();
        } finally{
			try {
				flujoEntrada.close();
				byteArrayOutputStream.close();
				fileOutputStream.close();
				tamano = file.length();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }

        return resultado;
		
	}
	
}
