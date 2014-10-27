package controladores;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import modelos.Experiencia;
import modelos.GestionModelo;
import modelos.HojaVida;
import modelos.TipoDocumento;

@ManagedBean
@SessionScoped
public class HojaVidaBean {
	
	private GestionModelo gestionModelo;
	
	private Long numeroIdentificacion;
	private String nombrePersona;
	private String apellidoPersona;
	private String tipoDocumento;
	private Date fechaNacimiento;
	private long telefono;
	private String correoElectronico;
	private String profesion;
	private String especializacion;
	private ArrayList<FechaExperiencia> fechasExperiencias;
	
	public HojaVidaBean() {
		HttpSession sesion=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		gestionModelo=(GestionModelo) sesion.getAttribute("gestionModelo");
		fechasExperiencias=new ArrayList<FechaExperiencia>();
	}

	public Long getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(Long numeroIdentificacion) {
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

	public String cargarHojaVida(){
		HojaVida hojaVida=gestionModelo.consultarHojaVida(numeroIdentificacion);
		if (hojaVida!=null) {
			nombrePersona=hojaVida.getNombrePersona();
			apellidoPersona=hojaVida.getApellidoPersona();
			tipoDocumento=hojaVida.getTipoDocumento().toString();
			fechaNacimiento=new Date(hojaVida.getFechaNacimiento().getTimeInMillis());
			telefono=hojaVida.getTelefono();
			correoElectronico=hojaVida.getCorreoElectronico();
			profesion=hojaVida.getProfesion();
			especializacion=hojaVida.getEspecializacion();
			// Borra la lista fechaExperiencias
			fechasExperiencias.clear();
			// Crea tres instancias de FechaExperiencia, de tal modo que el Facelet
			// modificarhojavida pueda modificar hasta tres experiencias, incluso 
			// si alguna de estas no existe en la base de datos
			for(int i=0; i<3; i++){
				fechasExperiencias.add(new FechaExperiencia());
			}
			ArrayList<Experiencia> experiencias=hojaVida.getExperiencias();
			Experiencia experiencia;
			FechaExperiencia fechaExperiencia;
			for (int i = 0; i < experiencias.size(); i++) {
				experiencia=experiencias.get(i);
				fechaExperiencia=fechasExperiencias.get(i);
				fechaExperiencia.setFechaInicio(experiencia.getFechaInicio().getTime());
				fechaExperiencia.setFechaFinal(experiencia.getFechaFinal().getTime());
			}
			return "mostrarhojavida.xhtml?faces-redirect=true";
		} else {
			agregarMensaje(FacesMessage.SEVERITY_WARN, "La hoja de vida no se encuentra en el sistema", false);
			return "hojasvida.xhtml";
		}
	}
	
	public String modificarHojaVida(){
		HojaVida hojaVida=new HojaVida();
		hojaVida.setNumeroIdentificacion(numeroIdentificacion);
		hojaVida.setTipoDocumento(TipoDocumento.valueOf(tipoDocumento));
		hojaVida.setNombrePersona(nombrePersona);
		hojaVida.setApellidoPersona(apellidoPersona);
		Calendar fecha=Calendar.getInstance();
		fecha.setTime(fechaNacimiento);
		hojaVida.setFechaNacimiento(fecha);
		hojaVida.setTelefono(telefono);
		hojaVida.setCorreoElectronico(correoElectronico);
		hojaVida.setProfesion(profesion);
		hojaVida.setEspecializacion(especializacion);
		Experiencia experiencia;
		Calendar fechaInicio, fechaFinal;
		for (int i =0 ; i<fechasExperiencias.size() ; i++) {
			Date dateFechaInicio=fechasExperiencias.get(i).getFechaInicio();
			Date dateFechaFinal=fechasExperiencias.get(i).getFechaFinal();
			if(dateFechaInicio!=null && dateFechaFinal!=null){
				if(dateFechaInicio.before(dateFechaFinal)){
					if (dateFechaInicio.after(new Date()) || dateFechaFinal.after(new Date())) {
						agregarMensaje(FacesMessage.SEVERITY_WARN, "Experiencia "+(i+1)+": fecha de inicio o final posterior a fecha actual", false);
						return "modificarhojavida.xthml";
					} else {
						fechaInicio=Calendar.getInstance();
						fechaInicio.setTime(dateFechaInicio);
						fechaFinal=Calendar.getInstance();
						fechaFinal.setTime(dateFechaFinal);
						experiencia=new Experiencia();
						experiencia.setHojaVida(hojaVida);
						experiencia.setFechaInicio(fechaInicio);
						experiencia.setFechaFinal(fechaFinal);
						hojaVida.getExperiencias().add(experiencia);
					}
				} else {
					agregarMensaje(FacesMessage.SEVERITY_WARN, "Experiencia "+(i+1)+": fecha de inicio posterior a fecha final", false);
					return "modificarhojavida.xthml";
				}
			} else if (dateFechaInicio==null && dateFechaFinal==null) {
				continue;
			} else {
				agregarMensaje(FacesMessage.SEVERITY_WARN, "Experiencia "+(i+1)+": fecha incompleta", false);
				return "modificarhojavida.xthml";
			}
		}
		gestionModelo.actualizarHojaVida(hojaVida);		
		agregarMensaje(FacesMessage.SEVERITY_INFO, "La hoja de vida ha sido modificada", true);
		return "mostrarhojavida.xthml?faces-redirect=true";
	}
	
	public String eliminarHojaVida(){
		gestionModelo.eliminarHojaVida(numeroIdentificacion);
		agregarMensaje(FacesMessage.SEVERITY_INFO, "La hoja de vida ha sido eliminada", true);
		return "hojasvida.xhtml?faces-redirect=true";
	}
	
	public String cancelarModificacion(){
		cargarHojaVida();
		agregarMensaje(FacesMessage.SEVERITY_INFO, "Se ha cancelado la modificación de la hoja de vida", true);
		return "mostrarhojavida.xhtml?faces-redirect=true";
	}
	
	private void agregarMensaje(Severity claseMensaje, String descripcion, boolean redirigir){
		FacesMessage mensajeEmergente=new FacesMessage(claseMensaje, descripcion, "");
		FacesContext.getCurrentInstance().addMessage(null, mensajeEmergente);
		if (redirigir) {
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}
	}

}
