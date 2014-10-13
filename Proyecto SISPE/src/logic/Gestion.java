package logic;

import persistence.GestionBD;
import persistence.PersonaDao;
import persistence.UsuarioDao;

public class Gestion {
	
	private GestionBD gestionBD;
	private UsuarioDao usuarioDao;
	private PersonaDao personaDao;
	
	public Gestion() {
		gestionBD=new GestionBD();
		usuarioDao=gestionBD.getUsuarioDao();
		personaDao=gestionBD.getPersonaDao();
	}
	
	// Métodos para la gestión de usuarios
	
	public Usuario consultarUsuario(String nombreUsuario){
		return usuarioDao.consultarUsuario(nombreUsuario);
	}
	
	// Métodos para la gestión de personas
	
	public Persona consultarPersona(long numeroIdentificacion){
		return personaDao.consultarPersona(numeroIdentificacion);
	}
	
	public void actualizarPersona(Persona persona){
		personaDao.actualizarPersona(persona);
	}
	
	public void eliminarPersona(long numeroIdentificacion){
		personaDao.eliminarPersona(numeroIdentificacion);
	}

}
