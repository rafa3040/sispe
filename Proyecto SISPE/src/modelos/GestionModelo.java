package modelos;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;

public class GestionModelo {
	
	private ConexionMysql conexionMysql;
	private UsuarioDao usuarioDao;
	private PersonaDao personaDao;
	private ExperienciaDao experienciaDao;
	
	private ArrayList<Integer> filasNoExtraidas;
	private ArrayList<Long> filasDuplicadas;
	
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
	
	// ********************************************
	// * Métodos para la gestión de hojas de vida *
	// ********************************************
	
	public HojaVida consultarHojaVida(long numeroIdentificacion){
		HojaVida hojaVida=personaDao.consultarPersona(numeroIdentificacion);
		if(hojaVida!=null){
			ArrayList<Experiencia> experiencias=experienciaDao.consultarExperiencias(numeroIdentificacion);
			hojaVida.setExperiencias(experiencias);
			return hojaVida;
		} else {
			return null;
		}
	}
	
	public void insertarHojaVida(HojaVida hojaVida){
		personaDao.insertarPersona(hojaVida);
		for (Experiencia experiencia : hojaVida.getExperiencias()) {
			experienciaDao.insertarExperiencia(experiencia);
		}
	}
	
	// Como el objeto HojaVida a actualizar puede tener Experiencias diferentes a las que
	// tenía anteriormente, se eliminan todas las experiencias anteriores y se
	// ingresan las nuevas experiencias
	public void actualizarHojaVida(HojaVida hojaVida){
		experienciaDao.eliminarExperiencias(hojaVida.getNumeroIdentificacion());
		for (Experiencia experiencia : hojaVida.getExperiencias()) {
			experienciaDao.insertarExperiencia(experiencia);
		}
		personaDao.actualizarPersona(hojaVida);
	}
	
	public void eliminarHojaVida(long numeroIdentificacion){
		experienciaDao.eliminarExperiencias(numeroIdentificacion);		
		personaDao.eliminarPersona(numeroIdentificacion);
	}
	
	// ***************************************
	// * Métodos para la gestión de archivos *
	// ***************************************
	public void cargarHojasVida(Workbook libroExcel) throws InvalidFormatException, IOException{
		CargaExcel cargaExcel=new CargaExcel();
		cargaExcel.cargarArchivo(libroExcel);
		ArrayList<HojaVida> hojasVidaExtraidas=cargaExcel.getHojasVidaExtraidas();
		filasNoExtraidas=cargaExcel.getFilasNoExtraidas();
		filasDuplicadas=new ArrayList<Long>();
		for (HojaVida hojaVida : hojasVidaExtraidas) {
			if(consultarHojaVida(hojaVida.getNumeroIdentificacion())==null){
				insertarHojaVida(hojaVida);
			} else {
				filasDuplicadas.add(hojaVida.getNumeroIdentificacion());
			}
		}
	}

	public ArrayList<Integer> getFilasNoExtraidas() {
		return filasNoExtraidas;
	}

	public ArrayList<Long> getFilasDuplicadas() {
		return filasDuplicadas;
	}

}
