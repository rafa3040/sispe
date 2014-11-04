package modelos;

import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Workbook;

public class GestionModelo {
	
	private ConexionMysql conexionMysql;
	private UsuarioDao usuarioDao;
	private HojaVidaDao hojaVidaDao;
	
	private ArrayList<Integer> registrosNoAgregados;
	private ArrayList<Long> registrosAgregados;
	private ArrayList<Long> registrosActualizados;
	
	public GestionModelo() {
		conexionMysql=new ConexionMysql();
		usuarioDao=new UsuarioDao(conexionMysql.getConexion());
		hojaVidaDao=new HojaVidaDao(conexionMysql.getConexion());
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
		return hojaVidaDao.consultarHojaVida(numeroIdentificacion);
	}
	
	public void insertarHojaVida(HojaVida hojaVida){
		hojaVidaDao.insertarHojaVida(hojaVida);
	}
	
	// Como el objeto HojaVida a actualizar puede tener Experiencias diferentes a las que
	// tenía anteriormente, se eliminan todas las experiencias anteriores y se
	// ingresan las nuevas experiencias
	public void actualizarHojaVida(HojaVida hojaVida){
		hojaVidaDao.actualizarHojaVida(hojaVida);
	}
	
	public void eliminarHojaVida(long numeroIdentificacion){
		hojaVidaDao.eliminarHojaVida(numeroIdentificacion);
	}
	
	public ArrayList<HojaVida> consultarHojasVida(int edadMinima, int edadMaxima, String patronProfesion, String patronEspecializacion, int mesesExperiencia){
		ArrayList<HojaVida> seleccionadas=hojaVidaDao.consultarHojasVida(edadMinima, edadMaxima, patronProfesion, patronEspecializacion, mesesExperiencia);
		return seleccionadas;
	}
	
	// ***************************************
	// * Métodos para la gestión de archivos *
	// ***************************************
	
	public void cargarHojasVida(Workbook libroExcel) {
		CargaExcel cargaExcel=new CargaExcel();
		cargaExcel.cargarArchivo(libroExcel);
		ArrayList<HojaVida> hojasVidaExtraidas=cargaExcel.getHojasVidaExtraidas();
		registrosNoAgregados=cargaExcel.getFilasNoExtraidas();
		registrosAgregados=new ArrayList<Long>();
		registrosActualizados=new ArrayList<Long>();
		for (HojaVida hojaVida : hojasVidaExtraidas) {
			if(consultarHojaVida(hojaVida.getNumeroIdentificacion())==null){
				insertarHojaVida(hojaVida);
				registrosAgregados.add(hojaVida.getNumeroIdentificacion());
			} else {
				actualizarHojaVida(hojaVida);
				registrosActualizados.add(hojaVida.getNumeroIdentificacion());
			}
		}
	}

	public ArrayList<Integer> getRegistrosNoAgregados() {
		return registrosNoAgregados;
	}

	public ArrayList<Long> getRegistrosAgregados() {
		return registrosAgregados;
	}

	public ArrayList<Long> getRegistrosActualizados() {
		return registrosActualizados;
	}

}
