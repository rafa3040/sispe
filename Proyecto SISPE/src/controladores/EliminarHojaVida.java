package controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelos.Persona;


@WebServlet("/EliminarHojaVida")
public class EliminarHojaVida extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public EliminarHojaVida() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion=request.getSession();
		Persona persona=(Persona) sesion.getAttribute("personaSeleccionada");
		

		// Confirmación
//		Confirmacion confirmacion=new Confirmacion();
//		confirmacion.setDescripcion("La hoja de vida se ha eliminado exitosamente");
//		confirmacion.setArchivoEnlace("hojasvida.jsp");
//		confirmacion.setMensajeEnlace("Volver a la página de hojas de vida");
//		sesion.setAttribute("confirmacion", confirmacion);
//		response.sendRedirect("confirmacion.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
