package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import logic.Usuario;

public class UsuarioDao {
	
	private Conexion conexion;
	private UsuarioSql usuarioSql;
	
	public UsuarioDao() {
		conexion=new Conexion();
		usuarioSql=new UsuarioSql();
	}
	
	public Usuario consultarUsuario(String nombreUsuario){
		Usuario usuario=null;
		if (conexion.conectorBD()) {
			try {
				Statement estamento= conexion.getConexion().createStatement();
				ResultSet dato=estamento.executeQuery(usuarioSql.consultarUsuario(nombreUsuario));
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