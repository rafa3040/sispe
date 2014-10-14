package modelos;

public class Usuario {
	
	private int idUsuario;
	private String nombreUsuario;
	private String contrasenhaUsuario;
	
	public Usuario() {

	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContrasenhaUsuario() {
		return contrasenhaUsuario;
	}

	public void setContrasenhaUsuario(String contrasenhaUsuario) {
		this.contrasenhaUsuario = contrasenhaUsuario;
	}

}
