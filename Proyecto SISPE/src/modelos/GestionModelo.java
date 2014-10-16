package modelos;

import java.util.ArrayList;

public class GestionModelo {
	
	private ConexionMysql conexionMysql;
	private UsuarioDao usuarioDao;
	private PersonaDao personaDao;
	private ExperienciaDao experienciaDao;
	
	public GestionModelo() {
		conexionMysql=new ConexionMysql();
		usuarioDao=new UsuarioDao(conexionMysql.getConexion());
		personaDao=new PersonaDao(conexionMysql.getConexion());
		experienciaDao=new ExperienciaDao(conexionMysql.getConexion());
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
