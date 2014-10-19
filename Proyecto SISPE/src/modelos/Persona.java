package modelos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Persona {
	
	private long numeroIdentificacion;
	private String nombrePersona;
	private String apellidoPersona;
	private TipoDocumento tipoDocumento;
	private Calendar fechaNacimiento;
	private long telefono;
	private String correoElectronico;
	private String profesion;
	private String especializacion;
	private ArrayList<Experiencia> experiencias;
	
	public Persona() {
		experiencias=new ArrayList<Experiencia>();
	}

	public long getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(long numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getNombrePersona() {
		return nombrePersona;
	}

	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}

	public String getApellidoPersona() {
		return apellidoPersona;
	}

	public void setApellidoPersona(String apellidoPersona) {
		this.apellidoPersona = apellidoPersona;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Calendar getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Calendar fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public long getTelefono() {
		return telefono;
	}

	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public String getEspecializacion() {
		return especializacion;
	}

	public void setEspecializacion(String especializacion) {
		this.especializacion = especializacion;
	}

	public ArrayList<Experiencia> getExperiencias() {
		return experiencias;
	}

	public void setExperiencias(ArrayList<Experiencia> experiencias) {
		this.experiencias = experiencias;
	}

	@Override
	public String toString() {
		String mensaje=numeroIdentificacion+" "+nombrePersona+" "+apellidoPersona+" "
				+tipoDocumento.toString()+" "+textoFechaNacimiento()+" "+telefono+" "
				+correoElectronico+" "+profesion+" "+especializacion;
		return mensaje;
	}
	
	public String textoFechaNacimiento(){
		SimpleDateFormat formato=new SimpleDateFormat("yyyy-MM-dd");
		return formato.format(fechaNacimiento.getTime());
	}

}
