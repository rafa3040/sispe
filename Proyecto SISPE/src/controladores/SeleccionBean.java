package controladores;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class SeleccionBean {
	
	private int edadMinima, edadMaxima;
	private String profesion;
	private String especializacion;
	private int mesesExperiencia;
	
	public SeleccionBean() {
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
		System.out.println("seleccion");
	}
	

}
