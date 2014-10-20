package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Clase que realiza las consultas en la tabla experiencia
 * @author Solutions Developers
 *
 */
public class ExperienciaDao {
	
	private Connection conexion;
	private PersonaDao personaDao;
	
	private PreparedStatement psConsultarExperiencias;
	private PreparedStatement psInsertarExperiencia;
	private PreparedStatement psEliminarExperiencias;
	
	public ExperienciaDao(Connection conexion, PersonaDao personaDao) {
		this.conexion=conexion;
		this.personaDao=personaDao;
		crearSentencias();
	}
	
	private void crearSentencias(){
		try {
			psConsultarExperiencias=conexion.prepareStatement("SELECT * FROM experiencia WHERE numero_identificacion=?");
			psInsertarExperiencia=conexion.prepareStatement("INSERT INTO experiencia (fecha_inicio, fecha_final, numero_identificacion) VALUES (?,?,?)");
			psEliminarExperiencias=conexion.prepareStatement("DELETE FROM experiencia WHERE numero_identificacion=?");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Experiencia> consultarExperiencias(long numeroIdentificacion){
		ResultSet resultados=null;
		if (conexion!=null) {
			try {
				psConsultarExperiencias.setLong(1, numeroIdentificacion);
				resultados=psConsultarExperiencias.executeQuery();
				ArrayList<Experiencia> experiencias=new ArrayList<>();
				Experiencia experiencia;
				while (resultados.next()) {
					experiencia=new Experiencia();
					experiencia.setNumeroExperiencia(resultados.getInt(1));
					Timestamp tiempoInicio=resultados.getTimestamp(2);
					Calendar fechaInicio=Calendar.getInstance();
					fechaInicio.setTimeInMillis(tiempoInicio.getTime());
					experiencia.setFechaInicio(fechaInicio);
					Timestamp tiempoFinal=resultados.getTimestamp(3);
					Calendar fechaFinal=Calendar.getInstance();
					fechaFinal.setTimeInMillis(tiempoFinal.getTime());
					experiencia.setFechaFinal(fechaFinal);
					HojaVida hojaVida=personaDao.consultarPersona(numeroIdentificacion);
					experiencia.setPersona(hojaVida);
					experiencias.add(experiencia);
				}
				return experiencias;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public boolean insertarExperiencia(Experiencia experiencia){
		if(conexion!=null){
			Calendar fechaInicio=experiencia.getFechaInicio();
			Timestamp tiempoInicio=new Timestamp(fechaInicio.getTimeInMillis());
			Calendar fechaFinal=experiencia.getFechaFinal();
			Timestamp tiempoFinal=new Timestamp(fechaFinal.getTimeInMillis());
			long numeroIdentificacion=experiencia.getPersona().getNumeroIdentificacion();
			try {
				psInsertarExperiencia.setTimestamp(1, tiempoInicio);
				psInsertarExperiencia.setTimestamp(2, tiempoFinal);
				psInsertarExperiencia.setLong(3, numeroIdentificacion);
				psInsertarExperiencia.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
	
	public boolean eliminarExperiencias(long numeroIdentificacion){
		if(conexion!=null){
			try {
				psEliminarExperiencias.setLong(1, numeroIdentificacion);
				psEliminarExperiencias.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

}