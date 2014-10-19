package modelos;

import java.util.Calendar;

public class Experiencia {
	
	private int numeroExperiencia;
	private Persona persona;
	private Calendar fechaInicio;
	private Calendar fechaFinal;
	
	public Experiencia() {

	}

	public int getNumeroExperiencia() {
		return numeroExperiencia;
	}

	public void setNumeroExperiencia(int numeroExperiencia) {
		this.numeroExperiencia = numeroExperiencia;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Calendar getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Calendar fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Calendar getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Calendar fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

}
