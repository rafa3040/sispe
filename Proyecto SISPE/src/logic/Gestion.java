package logic;

import java.util.ArrayList;

import persistence.ExperienciaDao;
import persistence.GestionBD;
import persistence.PersonaDao;
import persistence.UsuarioDao;

public class Gestion {
	
	private GestionBD gestionBD;
	private UsuarioDao usuarioDao;
	private PersonaDao personaDao;
	private ExperienciaDao experienciaDao;
	
	public Gestion() {
		gestionBD=new GestionBD();
		usuarioDao=gestionBD.getUsuarioDao();
		personaDao=gestionBD.getPersonaDao();
		experienciaDao=gestionBD.getExperienciaDao();
	}
	
	// Métodos para la gestión de usuarios
	
	public Usuario consultarUsuario(String nombreUsuario){
		return usuarioDao.consultarUsuario(nombreUsuario);
	}
	
	// Métodos para la gestión de personas
	
	public Persona consultarPersona(long numeroIdentificacion){
		Persona persona=personaDao.consultarPersona(numeroIdentificacion);
		ArrayList<Experiencia> experiencias=experienciaDao.consultarExperiencias(numeroIdentificacion);
		Experiencia[] experienciasPersona=new Experiencia[experiencias.size()];
		experiencias.toArray(experienciasPersona);
		persona.setExperiencias(experienciasPersona);
		return persona;
	}
	
	public void actualizarPersona(Persona persona){
		personaDao.actualizarPersona(persona);
	}
	
	public void eliminarPersona(long numeroIdentificacion){
		personaDao.eliminarPersona(numeroIdentificacion);
	}

}
