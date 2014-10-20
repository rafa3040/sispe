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

	public HojaVida consultarPersona(long numeroIdentificacion){
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
					Timestamp tiempo=dato.getTimestamp(5);
					Calendar fechaNacimiento=Calendar.getInstance();
					fechaNacimiento.setTimeInMillis(tiempo.getTime());
					hojaVida.setFechaNacimiento(fechaNacimiento);
					hojaVida.setTelefono(dato.getLong(6));
					hojaVida.setCorreoElectronico(dato.getString(7));
					hojaVida.setProfesion(dato.getString(8));
					hojaVida.setEspecializacion(dato.getString(9));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return hojaVida;
	}
	
	public boolean actualizarPersona(HojaVida hojaVida){
		if(conexion!=null){
			try {
				psActualizarPersona.setString(1, hojaVida.getNombrePersona());
				psActualizarPersona.setString(2, hojaVida.getApellidoPersona());
				Timestamp fechaNacimiento=new Timestamp(hojaVida.getFechaNacimiento().getTimeInMillis());
				psActualizarPersona.setTimestamp(3, fechaNacimiento);
				psActualizarPersona.setLong(4, hojaVida.getTelefono());
				psActualizarPersona.setString(5, hojaVida.getCorreoElectronico());
				psActualizarPersona.setString(6, hojaVida.getProfesion());
				psActualizarPersona.setString(7, hojaVida.getEspecializacion());
				psActualizarPersona.setLong(8, hojaVida.getNumeroIdentificacion());
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