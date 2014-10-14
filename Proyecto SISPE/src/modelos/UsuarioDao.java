package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase que realiza las consultas en la tabla USUARIO
 * @author Solutions Developers
 *
 */
public class UsuarioDao {
	
	private Connection conexion;
	
	private PreparedStatement psConsultar;
	
	public UsuarioDao(Connection conexion) {
		this.conexion=conexion;
		crearSentencias();
	}
	
	private void crearSentencias(){
		try {
			psConsultar=conexion.prepareStatement("SELECT * FROM usuario WHERE nombre_usuario=?");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Usuario consultarUsuario(String nombreUsuario){
		Usuario usuario=null;
		if (conexion!=null) {
			try {
				psConsultar.setString(1, nombreUsuario);
				ResultSet dato=psConsultar.executeQuery();
				if(dato.next()){
					usuario=new Usuario();
					usuario.setIdUsuario(dato.getInt(1));
					usuario.setNombreUsuario(dato.getString(2));
					usuario.setContrasenhaUsuario(dato.getString(3));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return usuario;
	}

}