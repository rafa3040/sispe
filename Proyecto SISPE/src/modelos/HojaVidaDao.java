package modelos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Clase que realiza las consultas en la tabla persona
 * @author Solutions Developers
 *
 */
public class HojaVidaDao {
	
	private Connection conexion;
	
	private PreparedStatement psConsultarPersona;
	private PreparedStatement psInsertarPersona;
	private PreparedStatement psActualizarPersona;
	private PreparedStatement psEliminarPersona;
	private PreparedStatement psConsultarExperiencias;
	private PreparedStatement psInsertarExperiencia;
	private PreparedStatement psEliminarExperiencias;
	private PreparedStatement psConsultarHojasVida;
	
	public HojaVidaDao(Connection conexion) {
		this.conexion=conexion;
		crearSentencias();
	}
	
	private void crearSentencias(){
		try {
			// Sentencias para la tabla persona
			psConsultarPersona=conexion.prepareStatement("SELECT * FROM persona WHERE numero_identificacion=?");
			psInsertarPersona=conexion.prepareStatement("INSERT INTO persona SET numero_identificacion=?, nombre_persona=?, apellido_persona=?, fecha_nacimiento=?, telefono=?, correo_electronico=?, profesion=?, especializacion=?");
			psActualizarPersona=conexion.prepareStatement("UPDATE persona SET nombre_persona=?, apellido_persona=?, fecha_nacimiento=?, telefono=?, correo_electronico=?, profesion=?, especializacion=? WHERE numero_identificacion=?");
			psEliminarPersona=conexion.prepareStatement("DELETE FROM persona WHERE numero_identificacion=?");
			// Sentencias para la tabla experiencia
			psConsultarExperiencias=conexion.prepareStatement("SELECT * FROM experiencia WHERE numero_identificacion=?");
			psInsertarExperiencia=conexion.prepareStatement("INSERT INTO experiencia (fecha_inicio, fecha_final, numero_identificacion) VALUES (?,?,?)");
			psEliminarExperiencias=conexion.prepareStatement("DELETE FROM experiencia WHERE numero_identificacion=?");
			// Sentencias para las consultas detalladas
			psConsultarHojasVida=conexion.prepareStatement("SELECT * FROM persona WHERE timestampdiff(YEAR,fecha_nacimiento,curdate()) BETWEEN ? and ? AND profesion LIKE ? AND especializacion LIKE ?");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public HojaVida consultarHojaVida(long numeroIdentificacion){
		HojaVida hojaVida=null;
		if (conexion!=null) {
			try {
				psConsultarPersona.setLong(1, numeroIdentificacion);
				ResultSet dato=psConsultarPersona.executeQuery();
				if(dato.next()){
					hojaVida=new HojaVida();
					hojaVida.setNumeroIdentificacion(dato.getLong(1));
					hojaVida.setNombrePersona(dato.getString(2));
					hojaVida.setApellidoPersona(dato.getString(3));
					hojaVida.setTipoDocumento(TipoDocumento.valueOf(dato.getString(4)));
					Date tiempo=dato.getDate(5);
					if(tiempo!=null){
						Calendar fechaNacimiento=Calendar.getInstance();
						fechaNacimiento.setTimeInMillis(tiempo.getTime());
						hojaVida.setFechaNacimiento(fechaNacimiento);
					} else {
						hojaVida.setFechaNacimiento(null);
					}
					if (dato.getObject(6)!=null) {
						hojaVida.setTelefono(dato.getLong(6));
					} else {
						hojaVida.setTelefono(null);
					}
					hojaVida.setCorreoElectronico(dato.getString(7));
					hojaVida.setProfesion(dato.getString(8));
					hojaVida.setEspecializacion(dato.getString(9));
					ArrayList<Experiencia> experiencias=consultarExperiencias(hojaVida);
					hojaVida.setExperiencias(experiencias);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return hojaVida;
	}
	
	public boolean insertarHojaVida(HojaVida hojaVida){
		if(conexion!=null){
			try {
				psInsertarPersona.setLong(1, hojaVida.getNumeroIdentificacion());
				psInsertarPersona.setString(2, hojaVida.getNombrePersona());
				psInsertarPersona.setString(3, hojaVida.getApellidoPersona());
				if(hojaVida.getFechaNacimiento()!=null){
					Date fechaNacimiento=new Date(hojaVida.getFechaNacimiento().getTimeInMillis());
					psInsertarPersona.setDate(4, fechaNacimiento);
				} else {
					psInsertarPersona.setTimestamp(4, null);
				}
				if(hojaVida.getTelefono()!=null){
					psInsertarPersona.setLong(5, hojaVida.getTelefono());
				} else {
					psInsertarPersona.setNull(5, Types.BIGINT);
				}
				psInsertarPersona.setString(6, hojaVida.getCorreoElectronico());
				psInsertarPersona.setString(7, hojaVida.getProfesion());
				psInsertarPersona.setString(8, hojaVida.getEspecializacion());
				psInsertarPersona.executeUpdate();
				for (Experiencia experiencia : hojaVida.getExperiencias()) {
					insertarExperiencia(experiencia);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
	
	public boolean actualizarHojaVida(HojaVida hojaVida){
		if(conexion!=null){
			try {
				psActualizarPersona.setString(1, hojaVida.getNombrePersona());
				psActualizarPersona.setString(2, hojaVida.getApellidoPersona());
				if (hojaVida.getFechaNacimiento()!=null) {
					Date fechaNacimiento=new Date(hojaVida.getFechaNacimiento().getTimeInMillis());
					psActualizarPersona.setDate(3, fechaNacimiento);
				} else {
					psActualizarPersona.setTimestamp(3, null);
				}
				if (hojaVida.getTelefono()!=null) {
					psActualizarPersona.setLong(4, hojaVida.getTelefono());
				} else {
					psActualizarPersona.setNull(4, Types.BIGINT);
				}
				psActualizarPersona.setString(5, hojaVida.getCorreoElectronico());
				psActualizarPersona.setString(6, hojaVida.getProfesion());
				psActualizarPersona.setString(7, hojaVida.getEspecializacion());
				psActualizarPersona.setLong(8, hojaVida.getNumeroIdentificacion());
				psActualizarPersona.executeUpdate();
				eliminarExperiencias(hojaVida.getNumeroIdentificacion());
				for (Experiencia experiencia : hojaVida.getExperiencias()) {
					insertarExperiencia(experiencia);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

	public boolean eliminarHojaVida(long numeroIdentificacion){
		if(conexion!=null){
			try {
				eliminarExperiencias(numeroIdentificacion);
				psEliminarPersona.setLong(1, numeroIdentificacion);
				psEliminarPersona.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
	
	// Consultas referentes a la tabla experiencia
	
	public ArrayList<Experiencia> consultarExperiencias(HojaVida hojaVida){
		ResultSet resultados=null;
		if (conexion!=null) {
			try {
				psConsultarExperiencias.setLong(1, hojaVida.getNumeroIdentificacion());
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
					experiencia.setHojaVida(hojaVida);
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
			long numeroIdentificacion=experiencia.getHojaVida().getNumeroIdentificacion();
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
	
	// Consultas detalladas
	
	public ArrayList<HojaVida> consultarHojasVida(int edadMinima, int edadMaxima, String patronProfesion, String patronEspecializacion){
		ResultSet resultados=null;
		if (conexion!=null) {
			try {
				psConsultarHojasVida.setInt(1, edadMinima);
				psConsultarHojasVida.setInt(2, edadMaxima);
				psConsultarHojasVida.setString(3, "%"+patronProfesion+"%");
				psConsultarHojasVida.setString(4, "%"+patronEspecializacion+"%");
				resultados=psConsultarHojasVida.executeQuery();
				ArrayList<HojaVida> hojasVida=new ArrayList<HojaVida>();
				HojaVida hojaVida;
				while (resultados.next()) {
					hojaVida=new HojaVida();
					hojaVida.setNumeroIdentificacion(resultados.getLong(1));
					hojaVida.setNombrePersona(resultados.getString(2));
					hojaVida.setApellidoPersona(resultados.getString(3));
					hojaVida.setTipoDocumento(TipoDocumento.valueOf(resultados.getString(4)));
					Date tiempo=resultados.getDate(5);
					if(tiempo!=null){
						Calendar fechaNacimiento=Calendar.getInstance();
						fechaNacimiento.setTimeInMillis(tiempo.getTime());
						hojaVida.setFechaNacimiento(fechaNacimiento);
					} else {
						hojaVida.setFechaNacimiento(null);
					}
					if (resultados.getObject(6)!=null) {
						hojaVida.setTelefono(resultados.getLong(6));
					} else {
						hojaVida.setTelefono(null);
					}
					hojaVida.setCorreoElectronico(resultados.getString(7));
					hojaVida.setProfesion(resultados.getString(8));
					hojaVida.setEspecializacion(resultados.getString(9));
					ArrayList<Experiencia> experiencias=consultarExperiencias(hojaVida);
					hojaVida.setExperiencias(experiencias);
					hojasVida.add(hojaVida);
				}
				return hojasVida;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}