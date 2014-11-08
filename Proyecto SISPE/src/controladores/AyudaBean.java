package controladores;

import java.io.InputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;


@ManagedBean
@SessionScoped
public class AyudaBean {
	
	private StreamedContent formatoCarga;
	
	public AyudaBean() {
        InputStream flujoEntrada = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/resources/archivos/formato carga.xlsx");
        formatoCarga = new DefaultStreamedContent(flujoEntrada, "application/vnd.ms-excel", "formato carga.xlsx");
	}

	public StreamedContent getFormatoCarga() {
		return formatoCarga;
	}

}
