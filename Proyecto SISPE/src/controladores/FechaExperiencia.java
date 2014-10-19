package controladores;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FechaExperiencia {
	
	private Date fechaInicio;
	private Date fechaFinal;
	
	public FechaExperiencia() {

	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	
	public String textoFechaInicio(){
		if(fechaInicio!=null){
			SimpleDateFormat formato=new SimpleDateFormat("dd-MM-yyyy");
			return formato.format(fechaInicio);
		} else return "---";
	}
	
	public String textoFechaFinal(){
		if(fechaFinal!=null){
			SimpleDateFormat formato=new SimpleDateFormat("dd-MM-yyyy");
			return formato.format(fechaFinal);
		} else return "---";
	}

}
