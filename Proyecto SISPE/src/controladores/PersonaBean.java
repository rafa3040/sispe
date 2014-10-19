package controladores;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.sun.faces.context.flash.ELFlash;

import modelos.Experiencia;
import modelos.GestionModelo;
import modelos.Persona;
import modelos.TipoDocumento;

@ManagedBean
@SessionScoped
public class PersonaBean {
	
	private GestionModelo gestionModelo;
	
	private long numeroIdentificacion;
	private String nombrePersona;
	private String apellidoPersona;
	private String tipoDocumento;
	private Date fechaNacimiento;
	private long telefono;
	private String correoElectronico;
	private String profesion;
	private String especializacion;
	private ArrayList<FechaExperiencia> fechasExperiencias;
	
	public PersonaBean() {
		HttpSession sesion=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		gestionModelo=(GestionModelo) sesion.getAttribute("gestionModelo");
		fechasExperiencias=new ArrayList<FechaExperiencia>();
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

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
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

	public GestionModelo getGestionModelo() {
		return gestionModelo;
	}

	public void setGestionModelo(GestionModelo gestionModelo) {
		this.gestionModelo = gestionModelo;
	}

	public ArrayList<FechaExperiencia> getFechasExperiencias() {
		return fechasExperiencias;
	}

	public void setFechasExperiencias(ArrayList<FechaExperiencia> fechasExperiencias) {
		this.fechasExperiencias = fechasExperiencias;
	}

	public String cargarPersona(){
		try {
			Persona persona=gestionModelo.consultarPersona(numeroIdentificacion);
			nombrePersona=persona.getNombrePersona();
			apellidoPersona=persona.getApellidoPersona();
			tipoDocumento=persona.getTipoDocumento().toString();
			fechaNacimiento=new Date(persona.getFechaNacimiento().getTimeInMillis());
			telefono=persona.getTelefono();
			correoElectronico=persona.getCorreoElectronico();
			profesion=persona.getProfesion();
			especializacion=persona.getEspecializacion();
			// Borra la lista fechaExperiencias
			fechasExperiencias.clear();
			// Crea tres instancias de FechaExperiencia, de tal modo que el Facelet
			// modificarhojavida pueda modificar hasta tres experiencias, incluso 
			// si alguna de estas no existe en la base de datos
			for(int i=0; i<3; i++){
				fechasExperiencias.add(new FechaExperiencia());
			}
			ArrayList<Experiencia> experiencias=persona.getExperiencias();
			Experiencia experiencia;
			FechaExperiencia fechaExperiencia;
			for (int i = 0; i < experiencias.size(); i++) {
				experiencia=experiencias.get(i);
				fechaExperiencia=fechasExperiencias.get(i);
				fechaExperiencia.setFechaInicio(experiencia.getFechaInicio().getTime());
				fechaExperiencia.setFechaFinal(experiencia.getFechaFinal().getTime());
			}
			return "mostrarhojavida.xhtml?faces-redirect=true";
		} catch (NullPointerException e) {
			ELFlash.getFlash().put("mensaje", "La hoja de vida no se encuentra en la base de datos");
			return "mensaje.xhtml?faces-redirect=true";
		}
	}
	
	public String modificarHojaVida(){
		Persona persona=new Persona();
		persona.setNumeroIdentificacion(numeroIdentificacion);
		persona.setTipoDocumento(TipoDocumento.valueOf(tipoDocumento));
		persona.setNombrePersona(nombrePersona);
		persona.setApellidoPersona(apellidoPersona);
		Calendar fecha=Calendar.getInstance();
		fecha.setTime(fechaNacimiento);
		persona.setFechaNacimiento(fecha);
		persona.setTelefono(telefono);
		persona.setCorreoElectronico(correoElectronico);
		persona.setProfesion(profesion);
		persona.setEspecializacion(especializacion);
		Experiencia experiencia;
		Calendar fechaInicio, fechaFinal;
		for (int i =0 ; i<fechasExperiencias.size() ; i++) {
			Date dateFechaInicio=fechasExperiencias.get(i).getFechaInicio();
			Date dateFechaFinal=fechasExperiencias.get(i).getFechaFinal();
			if(dateFechaInicio!=null && dateFechaFinal!=null){
				if(dateFechaInicio.before(dateFechaFinal)){
					fechaInicio=Calendar.getInstance();
					fechaInicio.setTime(dateFechaInicio);
					fechaFinal=Calendar.getInstance();
					fechaFinal.setTime(dateFechaFinal);
					experiencia=new Experiencia();
					experiencia.setPersona(persona);
					experiencia.setFechaInicio(fechaInicio);
					experiencia.setFechaFinal(fechaFinal);
					persona.getExperiencias().add(experiencia);
				} else {
					FacesMessage mensajeEmergente=new FacesMessage(FacesMessage.SEVERITY_WARN, "Experiencia "+(i+1), "Fecha de inicio posterior a fecha final");
					FacesContext.getCurrentInstance().addMessage(null, mensajeEmergente);
					return "modificarhojavida.xthml";
				}

			} else if (dateFechaInicio==null && dateFechaFinal==null) {
				continue;
			} else {
				FacesMessage mensajeEmergente=new FacesMessage(FacesMessage.SEVERITY_WARN, "Experiencia "+(i+1), "Rango de fechas incompleto");
				FacesContext.getCurrentInstance().addMessage(null, mensajeEmergente);
				return "modificarhojavida.xthml";
			}
		}
		gestionModelo.actualizarPersona(persona);		
		ELFlash.getFlash().put("mensaje", "Hoja de vida actualizada");
		return "mensaje.xhtml?faces-redirect=true";
	}
	
	public String eliminarHojaVida(){
		gestionModelo.eliminarPersona(numeroIdentificacion);
		ELFlash.getFlash().put("mensaje", "Hoja de vida eliminada");
		return "mensaje.xhtml?faces-redirect=true";
	}

}
