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
		experienciaDao=new ExperienciaDao(conexionMysql.getConexion(), personaDao);
	}
	
	// ***************************************
	// * Métodos para la gestión de usuarios *
	// ***************************************
	
	public Usuario consultarUsuario(String nombreUsuario){
		return usuarioDao.consultarUsuario(nombreUsuario);
	}
	
	// ***************************************
	// * Métodos para la gestión de personas *
	// ***************************************
	
	public Persona consultarPersona(long numeroIdentificacion){
		Persona persona=personaDao.consultarPersona(numeroIdentificacion);
		ArrayList<Experiencia> experiencias=experienciaDao.consultarExperiencias(numeroIdentificacion);
		persona.setExperiencias(experiencias);
		return persona;
	}
	
	// Como el objeto Persona a actualizar puede tener Experiencias diferentes a las que
	// tenía anteriormente, se eliminan todas las experiencias anteriores y se
	// ingresan las nuevas experiencias
	public void actualizarPersona(Persona persona){
		experienciaDao.eliminarExperiencias(persona.getNumeroIdentificacion());
		for (Experiencia experiencia : persona.getExperiencias()) {
			experienciaDao.insertarExperiencia(experiencia);
		}
		personaDao.actualizarPersona(persona);
	}
	
	public void eliminarPersona(long numeroIdentificacion){
		personaDao.eliminarPersona(numeroIdentificacion);
	}

}
