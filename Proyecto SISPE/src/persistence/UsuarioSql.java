package persistence;

public class UsuarioSql {
	
	public UsuarioSql() {

	}
	
	public String consultarUsuario(String nombreUsuario){
		return "SELECT * FROM USUARIO WHERE nombre_usuario='"+nombreUsuario+"'";
	}

}