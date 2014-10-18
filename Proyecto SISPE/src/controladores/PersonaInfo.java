package controladores;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.sun.faces.context.flash.ELFlash;

import modelos.GestionModelo;
import modelos.Persona;

@ManagedBean
@SessionScoped
public class PersonaInfo {
	
	private String numeroIdentificacion;
	private String nombrePersona;
	private String apellidoPersona;
	private String tipoDocumento;
	private String fechaNacimiento;
	private String telefono;
	private String correoElectronico;
	private String profesion;
	private String especializacion;
	
	public PersonaInfo() {

	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}
	
	public void setNumeroIdentificacion(String numeroIdentificacion) {
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
	
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
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
	
	public String cargarPersona(){
		HttpSession sesion=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		GestionModelo gestionModelo=(GestionModelo) sesion.getAttribute("gestionModelo");
		try {
			long documento=Integer.parseInt(numeroIdentificacion);
			Persona persona=gestionModelo.consultarPersona(documento);
			System.out.println(persona);
			return "";
		} catch (NullPointerException e) {
			ELFlash.getFlash().put("mensaje", "La hoja de vida no se encuentra en la base de datos");
			return "mensaje.xhtml?faces-redirect=true";
		}
	}
	
}
