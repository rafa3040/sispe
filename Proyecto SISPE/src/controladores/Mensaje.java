package controladores;

public class Mensaje {
	
	private String descripcion;
	private String enlace;
	
	public Mensaje() {
		descripcion="";
		enlace="";
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getEnlace() {
		return enlace;
	}
	
	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}
	
}
