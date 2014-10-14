package modelos;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Experiencia {
	
	private int numeroExperiencia;
	private Persona hojaVida;
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

	public Persona getHojaVida() {
		return hojaVida;
	}

	public void setHojaVida(Persona hojaVida) {
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
	
	public String textoFechaInicio(){
		SimpleDateFormat formato=new SimpleDateFormat("yyyy-MM-dd");
		return formato.format(fechaInicio.getTime());
	}
	
	public String textoFechaFinal(){
		SimpleDateFormat formato=new SimpleDateFormat("yyyy-MM-dd");
		return formato.format(fechaFinal.getTime());
	}

}