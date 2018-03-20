package clases;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class Xml {
	static private String ipApp = null;
	static private String ipBD = null;
	static private String ptoBD = null;
	static private String nomBD = null;
	static private String usrBD = null;
	static private String pswBD = null;
	static private String ipLic = null;
	static private String usrSAP = null;
	static private String pswSAP = null;
	static private String bd1 = null;
	static private int numSQL = 0;
	static private String rutaBase = null;
	static private String leyendaAjuste = null;
	static private String idAjuste = null;
	static private String escribir = null;
	static private String idEmpleado = null;
	static private String para = null;
	static private String cc = null;
	static private String msg = null;
	static private String host = null;
	static private String puertoFactory = null;
	static private String clase = null;
	static private String autenticacion = null;
	static private String puerto = null;
	static private String usuario = null;
	static private String contrasena = null;
	static private String debug = null;
	static private String nombreArchivo = null;
	
	static{
		leer();
	}

	private static void leer() {
		DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
		DocumentBuilder constructor = null;
		Document documento = null;
		
		try{
			File archivo = null;
			
			try {
				System.err.print("Buscando config.xml en directorio local..");
				archivo = new File("config.xml");
				constructor = fabrica.newDocumentBuilder();
				documento = constructor.parse(archivo);
				System.err.println("Encontrado en " + archivo.getAbsolutePath());
			} catch (Exception e) {			
				System.err.println(e.getMessage());
				try {
					System.err.print("Buscando config.xml en C:\\Program Files\\Apache Software Foundation\\Tomcat 8.0\\conf\\config.xml..");
					archivo = new File("C:\\Program Files\\Apache Software Foundation\\Tomcat 8.0\\conf\\config.xml");
					constructor = fabrica.newDocumentBuilder();
					documento = constructor.parse(archivo); 
					System.err.println("Encontrado en " + archivo.getAbsolutePath());
				} catch (Exception e2) {
					System.err.println(e.getMessage());
					try{
						System.err.print("Buscando config.xml en C:\\Program Files (x86)\\Apache Software Foundation\\Tomcat 8.0\\conf\\config.xml..");
						archivo = new File("C:\\Program Files (x86)\\Apache Software Foundation\\Tomcat 8.0\\conf\\config.xml");
						constructor = fabrica.newDocumentBuilder();
						documento = constructor.parse(archivo);
						System.err.println("Encontrado en " + archivo.getAbsolutePath());
					}catch(Exception e3){
						e3.printStackTrace();
					}
				}
			}
			
			documento.getDocumentElement().normalize();
			
			ipBD = documento.getElementsByTagName("ipBD").item(0).getTextContent();
			ptoBD = documento.getElementsByTagName("ptoBD").item(0).getTextContent();
			nomBD = documento.getElementsByTagName("nomBD").item(0).getTextContent();
			usrBD = documento.getElementsByTagName("usrBD").item(0).getTextContent();
			pswBD = documento.getElementsByTagName("pswBD").item(0).getTextContent();
			ipLic = documento.getElementsByTagName("ipLic").item(0).getTextContent();
			usrSAP = documento.getElementsByTagName("usrSAP").item(0).getTextContent();
			pswSAP = documento.getElementsByTagName("pswSAP").item(0).getTextContent();
			bd1 = documento.getElementsByTagName("bd1").item(0).getTextContent();
			numSQL = Integer.valueOf(documento.getElementsByTagName("numSQL").item(0).getTextContent());
			rutaBase = documento.getElementsByTagName("rutaBase").item(0).getTextContent();
			leyendaAjuste = documento.getElementsByTagName("leyendaAjuste").item(0).getTextContent();
			idAjuste = documento.getElementsByTagName("idAjuste").item(0).getTextContent();
			escribir = documento.getElementsByTagName("escribir").item(0).getTextContent();
			idEmpleado = documento.getElementsByTagName("idEmpleado").item(0).getTextContent();
			para = documento.getElementsByTagName("para").item(0).getTextContent();
			cc = documento.getElementsByTagName("cc").item(0).getTextContent();
			msg = documento.getElementsByTagName("msg").item(0).getTextContent();
			host = documento.getElementsByTagName("host").item(0).getTextContent();
			puertoFactory = documento.getElementsByTagName("puertoFactory").item(0).getTextContent();
			clase = documento.getElementsByTagName("clase").item(0).getTextContent();
			autenticacion = documento.getElementsByTagName("autenticacion").item(0).getTextContent();
			puerto = documento.getElementsByTagName("puerto").item(0).getTextContent();
			usuario = documento.getElementsByTagName("usuario").item(0).getTextContent();
			contrasena = documento.getElementsByTagName("contrasena").item(0).getTextContent();
			debug = documento.getElementsByTagName("debug").item(0).getTextContent();
			nombreArchivo = documento.getElementsByTagName("nombreArchivo").item(0).getTextContent();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static String getIpBD() {
		return ipBD;
	}

	public static void setIpBD(String ipBD) {
		Xml.ipBD = ipBD;
	}

	public static String getNomBD() {
		return nomBD;
	}

	public static void setNomBD(String nomBD) {
		Xml.nomBD = nomBD;
	}

	public static String getUsrBD() {
		return usrBD;
	}

	public static void setUsrBD(String usrBD) {
		Xml.usrBD = usrBD;
	}

	public static String getPswBD() {
		return pswBD;
	}

	public static void setPswBD(String pswBD) {
		Xml.pswBD = pswBD;
	}

	public static String getIpLic() {
		return ipLic;
	}

	public static void setIpLic(String ipLic) {
		Xml.ipLic = ipLic;
	}

	public static String getUsrSAP() {
		return usrSAP;
	}

	public static void setUsrSAP(String usrSAP) {
		Xml.usrSAP = usrSAP;
	}

	public static String getPswSAP() {
		return pswSAP;
	}

	public static void setPswSAP(String pswSAP) {
		Xml.pswSAP = pswSAP;
	}

	public static int getNumSQL() {
		return numSQL;
	}

	public static void setNumSQL(int numSQL) {
		Xml.numSQL = numSQL;
	}

	public static String getIpApp() {
		return ipApp;
	}

	public static void setIpApp(String ipApp) {
		Xml.ipApp = ipApp;
	}

	public static String getPtoBD() {
		return ptoBD;
	}

	public static void setPtoBD(String ptoBD) {
		Xml.ptoBD = ptoBD;
	}

	public static String getBd1() {
		return bd1;
	}

	public static void setBd1(String bd1) {
		Xml.bd1 = bd1;
	}

	public static String getRutaBase() {
		return rutaBase;
	}

	public static void setRutaBase(String rutaBase) {
		Xml.rutaBase = rutaBase;
	}

	public static String getLeyendaAjuste() {
		return leyendaAjuste;
	}

	public static String getIdAjuste() {
		return idAjuste;
	}

	public static void setIdAjuste(String idAjuste) {
		Xml.idAjuste = idAjuste;
	}

	public static void setLeyendaAjuste(String leyendaAjuste) {
		Xml.leyendaAjuste = leyendaAjuste;
	}

	public static String getEscribir() {
		return escribir;
	}

	public static void setEscribir(String escribir) {
		Xml.escribir = escribir;
	}

	public static String getIdEmpleado() {
		return idEmpleado;
	}

	public static void setIdEmpleado(String idEmpleado) {
		Xml.idEmpleado = idEmpleado;
	}

	public static String getPara() {
		return para;
	}
	
	public static String getMsg() {
		return msg;
	}

	public static String getHost() {
		return host;
	}

	public static String getPuertoFactory() {
		return puertoFactory;
	}

	public static String getClase() {
		return clase;
	}

	public static String getAutenticacion() {
		return autenticacion;
	}

	public static String getPuerto() {
		return puerto;
	}

	public static String getUsuario() {
		return usuario;
	}

	public static String getContrasena() {
		return contrasena;
	}
	
	public static String getDebug() {
		return debug;
	}

	public static String getNombreArchivo() {
		return nombreArchivo;
	}

	public static String getCc() {
		return cc;
	}
	
}
