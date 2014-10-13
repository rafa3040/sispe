package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que gestiona la conexión entre Java y MySQL
 * @author Solutions Developers
 *
 */
public class GestionBD {
	
	private Connection conexion;
	
	private UsuarioDao usuarioDao;
	private PersonaDao personaDao;
	
	public GestionBD() {
		abrirConexion();
		usuarioDao=new UsuarioDao(conexion);
		personaDao=new PersonaDao(conexion);
	}
	
	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public PersonaDao getPersonaDao() {
		return personaDao;
	}

	private void abrirConexion(){
		String DRIVER="com.mysql.jdbc.Driver";
		String USER="usuariosispe";
		String PASSWORD="qwerty1234";
		String URL ="jdbc:mysql://localhost:3306/SISPE?useServerPrepStmts=true";
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
