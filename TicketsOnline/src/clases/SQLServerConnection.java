package clases;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class SQLServerConnection{
	private Connection con = null;
	private Statement st = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	private CallableStatement sp = null;
	
	private void abrir(){
	   try{
		   Context ctx = new InitialContext();
		   Context envContext = (Context) ctx.lookup("java:/comp/env");
		   DataSource ds = (DataSource)envContext.lookup("jdbc/xptickets");
		   con = ds.getConnection();
	   }catch (Exception e){
		   e.printStackTrace();
	   }
	}
	
	private void cerrar(){
		try{
			if (sp != null)
				sp.close();
			if (ps != null)
				ps.close();
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
		        if (rs.next()){
		        	for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
		        		renglon.add(rs.getString(i));
					}
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
		st = con.createStatement();
		n = st.executeUpdate(_actualizacion);
		cerrar();
		
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
	
	@SuppressWarnings("unchecked")
	public ArrayList<ArrayList<String>> ejecutarSP(final String nombre_, String[] valoresParametros_){
		ArrayList<String> renglon = new ArrayList<String>();
		ArrayList<ArrayList<String>> matriz = new ArrayList<ArrayList<String>>();
		StringBuffer storedProcedure = new StringBuffer();
		int n = 0;
		
		try {
			storedProcedure.append("exec ").append(nombre_);
			
			for (int i = 0; i < valoresParametros_.length; i++) {
				
				if (i < valoresParametros_.length - 1){
					storedProcedure.append(" ?, ");
				}else{
					storedProcedure.append(" ? ");
				}
				
			}

			abrir();
			ps = con.prepareStatement(storedProcedure.toString());
			ps.setEscapeProcessing(true);
			ps.setQueryTimeout(20000);
		
			for (int i = 0; i < valoresParametros_.length; i++) {
				ps.setString(i + 1, valoresParametros_[i]);
			}
			
			rs = ps.executeQuery();
			n = rs.getMetaData().getColumnCount();
			
			while (rs.next()){
				
	        	 for (int i = 1; i <= n; i++){
	        		 renglon.add(noNulo(rs.getString(i)).trim());
	        	 }
	        	 
	        	 matriz.add((ArrayList<String>)renglon.clone());
	        	 renglon.clear();
	        }
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			cerrar();
		}
		
		return (ArrayList<ArrayList<String>>) matriz.clone();
		
	}
	
	public String ejecutarSPUnRetorno(final String nombre_, final String[] valoresParametros_){
		StringBuffer storedProcedure = new StringBuffer();
		String valor = "";
		
		try {
			storedProcedure.append("{ call ").append(nombre_).append(" (");
			
			for (int i = 0; i < valoresParametros_.length; i++) {
				
				if (i < valoresParametros_.length - 1){
					storedProcedure.append(" ?, ");
				}else{
					storedProcedure.append(" ? ");
				}
				
			}
			
			storedProcedure.append(") }");
			
			abrir();
			sp = con.prepareCall(storedProcedure.toString());
		
			for (int i = 0; i < valoresParametros_.length - 1; i++) {
				sp.setString(i + 1, valoresParametros_[i]);
			}
			
			sp.registerOutParameter(valoresParametros_.length, Types.VARCHAR);
			sp.execute();
			valor = sp.getString(valoresParametros_.length);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			cerrar();
		}
		
		return valor;
	}
	
}