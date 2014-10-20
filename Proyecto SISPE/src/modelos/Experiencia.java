package modelos;

import java.util.Calendar;

public class Experiencia {
	
	private int numeroExperiencia;
	private HojaVida hojaVida;
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

	public HojaVida getPersona() {
		return hojaVida;
	}

	public void setPersona(HojaVida hojaVida) {
		this.hojaVida = hojaVida;
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
