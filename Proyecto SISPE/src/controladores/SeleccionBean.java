package controladores;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import modelos.GestionModelo;
import modelos.HojaVida;

@ManagedBean
@SessionScoped
public class SeleccionBean {
	
	private GestionModelo gestionModelo;
	
	private int edadMinima, edadMaxima;
	private String profesion;
	private String especializacion;
	private int mesesExperiencia;
	
	private boolean activarEdad, activarProfesion, activarEspecializacion, activarMesesExperiencia;
	
	private ArrayList<HojaVida> hojasVida;
	
	public SeleccionBean() {
		HttpSession sesion=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		gestionModelo=(GestionModelo) sesion.getAttribute("gestionModelo");
		establecerRangoEdades();
		profesion="";
		especializacion="";
		mesesExperiencia=0;
	}

	public int getEdadMinima() {
		return edadMinima;
	}

	public void setEdadMinima(int edadMinima) {
		this.edadMinima = edadMinima;
	}

	public int getEdadMaxima() {
		return edadMaxima;
	}

	public void setEdadMaxima(int edadMaxima) {
		this.edadMaxima = edadMaxima;
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

	public int getMesesExperiencia() {
		return mesesExperiencia;
	}

	public void setMesesExperiencia(int mesesExperiencia) {
		this.mesesExperiencia = mesesExperiencia;
	}

	public boolean isActivarEdad() {
		return activarEdad;
	}

	public void setActivarEdad(boolean activarEdad) {
		if (!activarEdad) {
			establecerRangoEdades();
		}
		this.activarEdad = activarEdad;
	}

	public boolean isActivarProfesion() {
		return activarProfesion;
	}

	public void setActivarProfesion(boolean activarProfesion) {
		if (!activarProfesion) {
			profesion="";
		}
		this.activarProfesion = activarProfesion;
	}

	public boolean isActivarEspecializacion() {
		return activarEspecializacion;
	}

	public void setActivarEspecializacion(boolean activarEspecializacion) {
		if (!activarEspecializacion) {
			especializacion="";
		}
		this.activarEspecializacion = activarEspecializacion;
	}

	public boolean isActivarMesesExperiencia() {
		return activarMesesExperiencia;
	}

	public void setActivarMesesExperiencia(boolean activarMesesExperiencia) {
		if (!activarMesesExperiencia) {
			mesesExperiencia=0;
		}
		this.activarMesesExperiencia = activarMesesExperiencia;
	}

	public ArrayList<HojaVida> getHojasVida() {
		return hojasVida;
	}

	public void setHojasVida(ArrayList<HojaVida> hojasVida) {
		this.hojasVida = hojasVida;
	}

	public String seleccionarHojasVida(){
		if (!activarEdad && !activarProfesion && !activarEspecializacion && !activarMesesExperiencia) {
			FacesMessage mensajeEmergente=new FacesMessage(FacesMessage.SEVERITY_WARN, "No existe ningún criterio de búsqueda asignado", "");
			FacesContext.getCurrentInstance().addMessage(null, mensajeEmergente);
			return "";
		} else {
			hojasVida=gestionModelo.consultarHojasVida(edadMinima, edadMaxima, profesion, especializacion, mesesExperiencia);
			return "resultadosseleccion.xhtml?faces-redirect=true";
		}
	}
	
	private void establecerRangoEdades(){
		edadMinima=18;
		edadMaxima=70;
	}

}
