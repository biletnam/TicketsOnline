package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class SQLServerConnection{
	private String stringConexion = null;
	private String user = null;
	private String password = null;
	private Connection con = null;
	private Statement st = null;
	private ResultSet rs = null;
	
	public SQLServerConnection(){
		stringConexion = "jdbc:sqlserver://"+Xml.getIpBD()+";databaseName="+Xml.getBd1();
		user = Xml.getUsrBD();
		password = Xml.getPswBD();
	}
	
	public SQLServerConnection(final String nomDB_){
		stringConexion = "jdbc:sqlserver://"+Xml.getIpBD()+";databaseName="+nomDB_;
		user = Xml.getUsrBD();
		password = Xml.getPswBD();
	}

	private void abrir(){
	   try{
		   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		   con = DriverManager.getConnection(stringConexion, user, password);
	   }catch (Exception e){
		   e.printStackTrace();
	   }
	}
	
	private void cerrar(){
		try{
			if (rs != null) 
				rs.close();
			if (st != null)
				st.close();
			if (con != null)
				con.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<ArrayList<String>> consultarMatriz(final String _query) throws Exception{
		ArrayList<String> renglon = new ArrayList<String>();
		ArrayList<ArrayList<String>> matriz = new ArrayList<ArrayList<String>>();
		int n = 0;
		
		abrir();
		if (con != null){
			st = con.createStatement();
			rs = st.executeQuery(_query);
			n = rs.getMetaData().getColumnCount();
	        while (rs.next()){
	        	 for (int i = 1; i <= n; i++){
	        		 renglon.add(noNulo(rs.getString(i)).trim());
	        	 }
	        	 matriz.add((ArrayList<String>)renglon.clone());
	        	 renglon.clear();
	        }
		}
		cerrar();
		
//		System.out.println(matriz);

		return (ArrayList<ArrayList<String>>) matriz.clone();
   }
	
	private String noNulo(final Object valor_) {
		if (valor_ == null){
			return "";
		}else{
			return valor_.toString();
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<String> consultarVector(final String _query){
		ArrayList<String> renglon = new ArrayList<String>();
		
		abrir();
		try{
			if (con != null){
				st = con.createStatement();
				rs = st.executeQuery(_query);
		        while (rs.next()){
		        	renglon.add(rs.getString(1));
		        }
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			cerrar();
		}

		return (ArrayList<String>) renglon.clone();
   }
	
	
	public int contar(final String _query){
		
		int retorno = 0;
		abrir();
		try{
			if (con != null){
				st = con.createStatement();
				rs = st.executeQuery(_query);
		        if (rs.next()){
		        	retorno = rs.getInt(1);
		        }
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			cerrar();
		}
        return retorno;
   }
	
	
	@SuppressWarnings("unchecked")
	public HashMap<String, String> consultarHM(final String _query){
		HashMap<String, String> matriz = new HashMap<String, String>();
		
		abrir();
		try{
			st = con.createStatement();
			rs = st.executeQuery(_query);
	        while (rs.next()){
	        	 matriz.put(rs.getString(1), rs.getString(2));
	        }
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			cerrar();
		}
        return (HashMap<String, String>) matriz.clone();
   }
	
	/**
	 * 
	 * @param _query
	 * @return
	 */
	public String consultar1Valor(final String _query){
		String resultado = "";
		
		abrir();
		try{
			st = con.createStatement();
			rs = st.executeQuery(_query);
	        if (rs.next()){
	        	 resultado = rs.getString(1);
	        }
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			cerrar();
		}
        return resultado;
   }
	
	
	public int actualizar(final String _actualizacion) throws Exception{
		int n = 0;
		
//		System.out.println(_actualizacion);
		
		abrir();
		try {
			st = con.createStatement();
			n = st.executeUpdate(_actualizacion);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			cerrar();
		}
		
        return n;
   }

	public ArrayList<ArrayList<String>> getColumnas(final String tabla_) throws Exception {
		return consultarMatriz("SELECT a.name, b.name, a.max_length FROM sys.columns a join sys.types b on a.user_type_id = b.user_type_id WHERE object_id = OBJECT_ID('" + tabla_ + "') and a.is_nullable = 0 and a.is_identity = 0 and a.is_computed = 0 order by a.name ");
	}

	public int consultar1ValorNumerico(final String _query) {
		int resultado = 0;
		
		abrir();
		try{
			st = con.createStatement();
			rs = st.executeQuery(_query);
	        if (rs.next()){
	        	 resultado = rs.getInt(1);
	        }
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			cerrar();
		}
        return resultado;
	}
	
	public String[] consultarStringArray(final String query_){
		return consultarVector(query_).toArray(new String[0]);
	}
	
}