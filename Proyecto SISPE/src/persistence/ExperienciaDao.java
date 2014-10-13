package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import logic.Experiencia;

/**
 * Clase que realiza las consultas en la tabla EXPERIENCIA000
 * @author Solutions Developers
 *
 */
public class ExperienciaDao {
	
	private Connection conexion;
	
	private PreparedStatement psConsultarExperiencia;
	private PreparedStatement psActualizarExperiencia;
	private PreparedStatement psEliminarExperiencia;
	
	public ExperienciaDao(Connection conexion) {
		this.conexion=conexion;
		crearSentencias();
	}
	
	private void crearSentencias(){
		try {
			psConsultarExperiencia=conexion.prepareStatement("SELECT * FROM EXPERIENCIA WHERE numero_identificacion=?");
			psActualizarExperiencia=conexion.prepareStatement("UPDATE EXPERIENCIA SET fecha_inicio=?, fecha_final=? WHERE numero_experiencia=?");
			psEliminarExperiencia=conexion.prepareStatement("DELETE FROM EXPERIENCIA WHERE numero_experiencia=?");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Experiencia> consultarExperiencias(long numeroIdentificacion){
		ResultSet resultados=null;
		if (conexion!=null) {
			try {
				psConsultarExperiencia.setLong(1, numeroIdentificacion);
				resultados=psConsultarExperiencia.executeQuery();
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
					experiencias.add(experiencia);
				}
				return experiencias;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public boolean actualizarExperiencia(Experiencia experiencia){
		if(conexion!=null){
			Calendar fechaInicio=experiencia.getFechaInicio();
			Timestamp tiempoInicio=new Timestamp(fechaInicio.getTimeInMillis());
			Calendar fechaFinal=experiencia.getFechaFinal();
			Timestamp tiempoFinal=new Timestamp(fechaFinal.getTimeInMillis());
			int numeroExperiencia=experiencia.getNumeroExperiencia();
			try {
				psActualizarExperiencia.setTimestamp(1, tiempoInicio);
				psActualizarExperiencia.setTimestamp(2, tiempoFinal);
				psActualizarExperiencia.setInt(3, numeroExperiencia);
				psActualizarExperiencia.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

	public boolean eliminarExperiencia(long numeroExperiencia){
		if(conexion!=null){
			try {
				psEliminarExperiencia.setLong(1, numeroExperiencia);
				psEliminarExperiencia.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

}