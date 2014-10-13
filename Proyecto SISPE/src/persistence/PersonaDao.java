package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import logic.Persona;
import logic.TipoDocumento;

/**
 * Clase que realiza las consultas en la tabla PERSONA
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
			psConsultarPersona=conexion.prepareStatement("SELECT * FROM PERSONA WHERE numero_identificacion=?");
			psActualizarPersona=conexion.prepareStatement("UPDATE PERSONA SET nombre_persona=?, apellido_persona=? WHERE numero_identificacion=?");
			psEliminarPersona=conexion.prepareStatement("DELETE FROM PERSONA WHERE numero_identificacion=?");
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
			long numeroIdentificacion=persona.getNumeroIdentificacion();
			String nombrePersona=persona.getNombrePersona();
			String apellidoPersona=persona.getApellidoPersona();
			try {
				psActualizarPersona.setString(1, nombrePersona);
				psActualizarPersona.setString(2, apellidoPersona);
				psActualizarPersona.setLong(3, numeroIdentificacion);
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