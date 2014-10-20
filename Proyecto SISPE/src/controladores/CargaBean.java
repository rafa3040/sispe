package controladores;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import modelos.GestionModelo;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.primefaces.model.UploadedFile;

import com.sun.faces.context.flash.ELFlash;

@ManagedBean
@SessionScoped
public class CargaBean {
	
	private GestionModelo gestionModelo;
	
	private UploadedFile archivo;
	
	public CargaBean() {
		HttpSession sesion=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		gestionModelo=(GestionModelo) sesion.getAttribute("gestionModelo");
	}

	public UploadedFile getArchivo() {
		return archivo;
	}

	public void setArchivo(UploadedFile archivo) {
		this.archivo = archivo;
	}

	public String cargarHojaVida() {
		if(archivo != null && archivo.getSize()>0) {
			try {
				Workbook libroExcel=WorkbookFactory.create(archivo.getInputstream());
				gestionModelo.cargarHojasVida(libroExcel);
				archivo=null;
				ELFlash.getFlash().put("mensaje", "Archivo cargado exitosamente");
				return "resultadoscarga.xhtml?faces-redirect=true";
			} catch (Exception e) {
				FacesMessage mensajeEmergente=new FacesMessage(FacesMessage.SEVERITY_ERROR, "Carga de archivo", "El archivo no es compatible con Excel");
				FacesContext.getCurrentInstance().addMessage(null, mensajeEmergente);
				return "";
			}
		} else {
			FacesMessage mensajeEmergente=new FacesMessage(FacesMessage.SEVERITY_ERROR, "Carga de archivo", "No existe ningún archivo para cargar");
			FacesContext.getCurrentInstance().addMessage(null, mensajeEmergente);
			return "";
		}
    }

}
