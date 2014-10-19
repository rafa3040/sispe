package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Clase que realiza las consultas en la tabla persona
 * @author Solutions Developers
 *
 */
public class PersonaDao {
	
	private Connection conexion;
	
	private PreparedStatement psConsultarPersona;
	private PreparedStatement psActualizarPersona;
	private PreparedStatement psEliminarPersona;
	
	public PersonaDao(Connection conexion) {
		this.conexion=conexion;
		crearSentencias();
	}
	
	private void crearSentencias(){
		try {
			psConsultarPersona=conexion.prepareStatement("SELECT * FROM persona WHERE numero_identificacion=?");
			psActualizarPersona=conexion.prepareStatement("UPDATE persona SET nombre_persona=?, apellido_persona=?, fecha_nacimiento=?, telefono=?, correo_electronico=?, profesion=?, especializacion=? WHERE numero_identificacion=?");
			psEliminarPersona=conexion.prepareStatement("DELETE FROM persona WHERE numero_identificacion=?");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Persona consultarPersona(long numeroIdentificacion){
		Persona persona=null;
		if (conexion!=null) {
			try {
				psConsultarPersona.setLong(1, numeroIdentificacion);
				ResultSet dato=psConsultarPersona.executeQuery();
				if(dato.next()){
					persona=new Persona();
					persona.setNumeroIdentificacion(dato.getLong(1));
					persona.setNombrePersona(dato.getString(2));
					persona.setApellidoPersona(dato.getString(3));
					persona.setTipoDocumento(TipoDocumento.valueOf(dato.getString(4)));
					Timestamp tiempo=dato.getTimestamp(5);
					Calendar fechaNacimiento=Calendar.getInstance();
					fechaNacimiento.setTimeInMillis(tiempo.getTime());
					persona.setFechaNacimiento(fechaNacimiento);
					persona.setTelefono(dato.getLong(6));
					persona.setCorreoElectronico(dato.getString(7));
					persona.setProfesion(dato.getString(8));
					persona.setEspecializacion(dato.getString(9));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return persona;
	}
	
	public boolean actualizarPersona(Persona persona){
		if(conexion!=null){
			try {
				psActualizarPersona.setString(1, persona.getNombrePersona());
				psActualizarPersona.setString(2, persona.getApellidoPersona());
				Timestamp fechaNacimiento=new Timestamp(persona.getFechaNacimiento().getTimeInMillis());
				psActualizarPersona.setTimestamp(3, fechaNacimiento);
				psActualizarPersona.setLong(4, persona.getTelefono());
				psActualizarPersona.setString(5, persona.getCorreoElectronico());
				psActualizarPersona.setString(6, persona.getProfesion());
				psActualizarPersona.setString(7, persona.getEspecializacion());
				psActualizarPersona.setLong(8, persona.getNumeroIdentificacion());
				psActualizarPersona.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

	public boolean eliminarPersona(long numeroIdentificacion){
		if(conexion!=null){
			try {
				psEliminarPersona.setLong(1, numeroIdentificacion);
				psEliminarPersona.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

}