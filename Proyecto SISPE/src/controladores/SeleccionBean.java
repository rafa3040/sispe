package controladores;

import java.util.ArrayList;

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
	
	public SeleccionBean() {
		HttpSession sesion=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		gestionModelo=(GestionModelo) sesion.getAttribute("gestionModelo");
		edadMinima=18;
		edadMaxima=28;
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

	public void seleccionarHojasVida(){
		ArrayList<HojaVida> seleccionadas=gestionModelo.consultarHojasVida(edadMinima, edadMaxima, profesion, especializacion, mesesExperiencia);
		for (HojaVida hojaVida : seleccionadas) {
			System.out.println(hojaVida);
		}
	}
	

}
