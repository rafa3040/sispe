package controladores;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import modelos.GestionModelo;
import modelos.Usuario;

public class GestionSesion {
	
	private String nombreusuario;
	private String contrasenha;
	
	public GestionSesion() {

	}

	public String getNombreusuario() {
		return nombreusuario;
	}

	public void setNombreusuario(String nombreusuario) {
		this.nombreusuario = nombreusuario;
	}

	public String getContrasenha() {
		return contrasenha;
	}

	public void setContrasenha(String contrasenha) {
		this.contrasenha = contrasenha;
	}

	public String iniciarSesion(){
		HttpSession sesion=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		GestionModelo gestionModelo=new GestionModelo();
		Usuario usuario=gestionModelo.consultarUsuario(nombreusuario);
		FacesMessage mensajeEmergente=null;
		if (usuario!=null) {
			if (usuario.getContrasenhaUsuario().equals(contrasenha)) {
				// se crea el atributo nombreUsuario, para poder verificar
				// en FiltroValidacion que existe una sesión iniciada
				sesion.setAttribute("nombreUsuario", nombreusuario);
				// se inicializan los objetos de la logica que se van a 
				// usar a lo largo de la sesión
				sesion.setAttribute("gestionModelo", gestionModelo);
				// redireccion a la pagina principal
				redirigir("index.xhtml");
				return "";
			} else {
				gestionModelo=null;
				mensajeEmergente=new FacesMessage(FacesMessage.SEVERITY_INFO, "No se inició sesión", "Contraseña incorrecta");
				FacesContext.getCurrentInstance().addMessage(null, mensajeEmergente);
				return "iniciosesion";
			}
		} else {
			gestionModelo=null;
			mensajeEmergente=new FacesMessage(FacesMessage.SEVERITY_INFO, "No se inició sesión", "El usuario no existe en el sistema");
			FacesContext.getCurrentInstance().addMessage(null, mensajeEmergente);
			return "iniciosesion";
		}
	}
	
	public void cerrarSesion(){
		HttpSession sesion=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		sesion.invalidate();
		redirigir("iniciosesion.xhtml");
	}
	
	private void redirigir(String url){
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();  
		try {
			externalContext.redirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
