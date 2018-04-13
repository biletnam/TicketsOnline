package clases;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ArchivoTexto {
	private String nombre = null;
	private String delimitador = null;
	private String caracterNuevo = null;
	private String caracterViejo = null;
	private FileInputStream inp = null;
	private DataInputStream datos = null;
	private BufferedReader buffer = null;
	private String linea = null;
	private ArrayList<ArrayList<String>> matriz = new ArrayList<ArrayList<String>>();
	

	public ArchivoTexto(final String nombre_, final String caracterViejo_, final String caracterNuevo_){
		nombre = nombre_;
		caracterNuevo = caracterNuevo_;
		caracterViejo = caracterViejo_;
	}
	
	public ArchivoTexto(final String nombre_, final String delimitador_){
		nombre = nombre_;
		delimitador = delimitador_;
	}
	
	public ArchivoTexto(final String nombre_){
		nombre = nombre_;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<ArrayList<String>> leer(){
		
		try {
			inp = new FileInputStream(nombre);
			datos = new DataInputStream(inp);
			buffer = new BufferedReader(new InputStreamReader(datos));
			ArrayList <String> renglon = new ArrayList<String>();
			String[] renglonStringArray = null;
			
			while ((linea = buffer.readLine()) != null){
				renglonStringArray = linea.split("\\"+delimitador);
				for (int i = 0; i < renglonStringArray.length; i++) {
					renglon.add(renglonStringArray[i].trim());
				}
				matriz.add((ArrayList<String>) renglon.clone());
				renglon.clear();
				renglonStringArray = null;
			}
			
		}catch (Exception e) {
			MiLog.logger.severe(e.getMessage());
		}finally{
			try {
				datos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return (ArrayList<ArrayList<String>>) matriz.clone();
	}
	
	
	public boolean reemplazar(){
		boolean res = true;
		Path ruta = Paths.get(nombre);
		Charset tipo = StandardCharsets.UTF_8;
		String contenido = null;
		
		try {
			contenido = new String(Files.readAllBytes(ruta), tipo);
			contenido = contenido.replace(caracterViejo, caracterNuevo);
			Files.write(ruta, contenido.getBytes(tipo));
		} catch (Exception e) {
			e.printStackTrace();
			res = false;
		}
		
		return res;
	}
	
	
	//Falta meterle UI
	public static void main(String[] args){
//		System.out.println(new ArchivoTexto("C:\\Users\\Hp Pro\\Desktop\\Event1.svg").leer());
	}
}
