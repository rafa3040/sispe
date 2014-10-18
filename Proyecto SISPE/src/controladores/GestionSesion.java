package controladores;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import modelos.GestionModelo;
import modelos.Usuario;

@ManagedBean
@SessionScoped
public class GestionSesion {
	
	private String nombreUsuario;
	private String contrasenha;
	
	public GestionSesion() {

	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
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
		Usuario usuario=gestionModelo.consultarUsuario(nombreUsuario);
		FacesMessage mensajeEmergente=null;
		if (usuario!=null) {
			if (usuario.getContrasenhaUsuario().equals(contrasenha)) {
				// se crea el atributo nombreUsuario, para poder verificar
				// en FiltroValidacion que existe una sesión iniciada
				sesion.setAttribute("nombreUsuario", nombreUsuario);
				// se inicializan los objetos de la logica que se van a 
				// usar a lo largo de la sesión
				sesion.setAttribute("gestionModelo", gestionModelo);
				// redireccion a la pagina principal
				return "index.xhtml?faces-redirect=true";
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
	
	public String cerrarSesion(){
		HttpSession sesion=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		sesion.invalidate();
		return "iniciosesion.xhtml?faces-redirect=true";
	}

}
