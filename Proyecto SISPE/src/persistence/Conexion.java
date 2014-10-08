package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	private String DRIVER="com.mysql.jdbc.Driver";
	private String USER="root";
	private String PASSWORD="electiva2";
	private Connection conexion;
	private String URL ="jdbc:mysql://localhost:3306/sispe";
	
	public Conexion() {
		conexion=null;
		
	}

	
	public Connection getConexion(){
		return conexion;
		
	}

	public boolean conectorBD(){
		boolean conectar=false;
		try{
			Class.forName(DRIVER).newInstance();
			conexion=DriverManager.getConnection(URL,USER,PASSWORD);
			conectar=true;
		}catch (InstantiationException ie) {
			System.out.println("InstantiationException \n"+ie.getMessage());
		}catch (IllegalAccessException iae) {
			System.out.println("IllegalAccessException \n"+iae.getMessage());
		}catch (ClassNotFoundException cnfe) {
			System.out.println("ClassNotFoundException \n"+cnfe.getMessage());
		}catch (SQLException sqle) {
			System.out.println("SQLExceptioniuytre \n"+sqle.getMessage());
		}
		return conectar;
	}
	
	public void cerrarConexion(){ 
		
		try {
			conexion.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
}
