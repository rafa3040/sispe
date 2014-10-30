package controladores;

import java.util.ArrayList;

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
	
	private ArrayList<Integer> registrosNoAgregados;
	private ArrayList<Long> registrosAgregados;
	private ArrayList<Long> registrosActualizados;
	
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

	public ArrayList<Integer> getRegistrosNoAgregados() {
		return registrosNoAgregados;
	}

	public ArrayList<Long> getRegistrosAgregados() {
		return registrosAgregados;
	}

	public ArrayList<Long> getRegistrosActualizados() {
		return registrosActualizados;
	}

	public String cargarHojasVida() {
		if(archivo != null && archivo.getSize()>0) {
			try {
				Workbook libroExcel=WorkbookFactory.create(archivo.getInputstream());
				gestionModelo.cargarHojasVida(libroExcel);
				archivo=null;
				registrosNoAgregados=gestionModelo.getRegistrosNoAgregados();
				registrosAgregados=gestionModelo.getRegistrosAgregados();
				registrosActualizados=gestionModelo.getRegistrosActualizados();
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
