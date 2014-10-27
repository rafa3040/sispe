package modelos;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

	public HojaVida getHojaVida() {
		return hojaVida;
	}

	public void setHojaVida(HojaVida hojaVida) {
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
	
	public void establecerFechas(Date dateInicio, Date dateFinal){
		fechaInicio=Calendar.getInstance();
		fechaFinal=Calendar.getInstance();
		fechaInicio.setTime(dateInicio);
		fechaFinal.setTime(dateFinal);
	}
	
	public String textoFechaInicio(){
		SimpleDateFormat formato=new SimpleDateFormat("yyyy-MM-dd");
		return formato.format(fechaInicio.getTime());
	}
	
	public String textoFechaFinal(){
		SimpleDateFormat formato=new SimpleDateFormat("yyyy-MM-dd");
		return formato.format(fechaFinal.getTime());
	}

	@Override
	public String toString() {
		return "Fecha inicio: " + textoFechaInicio() + " Fecha final: " + textoFechaFinal();
	}

}
