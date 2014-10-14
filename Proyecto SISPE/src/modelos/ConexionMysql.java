package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMysql {
	
	private Connection conexion;
	
	public ConexionMysql() {
		abrirConexion();
	}
	
	public Connection getConexion() {
		return conexion;
	}

	private void abrirConexion(){
		String DRIVER="com.mysql.jdbc.Driver";
		String USER="usuariosispe";
		String PASSWORD="qwerty1234";
		String URL ="jdbc:mysql://localhost:3306/sispe?useServerPrepStmts=true";
		try{
			Class.forName(DRIVER).newInstance();
			conexion=DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (InstantiationException ie) {
			System.out.println("InstantiationException \n"+ie.getMessage());
		} catch (IllegalAccessException iae) {
			System.out.println("IllegalAccessException \n"+iae.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println("ClassNotFoundException \n"+cnfe.getMessage());
		} catch (SQLException sqle) {
			System.out.println("SQLExceptioniuytre \n"+sqle.getMessage());
		}
	}
	
	public void cerrarConexion(){
		try {
			conexion.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}

}
