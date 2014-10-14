package controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelos.Gestion;
import modelos.Persona;

@WebServlet("/ModificarHojaVida")
public class ModificarHojaVida extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public ModificarHojaVida() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion=request.getSession();
		// Extracción datos formulario
		String nombrePersona=request.getParameter("nombrePersona");
		String apellidoPersona=request.getParameter("apellidoPersona");
		long telefono=Long.parseLong(request.getParameter("telefono"));
		String correoElectronico=request.getParameter("correoElectronico");
		String profesion=request.getParameter("profesion");
		String especializacion=request.getParameter("especializacion");
		// Actualización del objeto Persona
		Persona persona=(Persona) sesion.getAttribute("personaSeleccionada");
		persona.setNombrePersona(nombrePersona);
		persona.setApellidoPersona(apellidoPersona);
		persona.setTelefono(telefono);
		persona.setCorreoElectronico(correoElectronico);
		persona.setProfesion(profesion);
		persona.setEspecializacion(especializacion);
		// Actualización de la base de datos
		Gestion gestion=(Gestion) sesion.getAttribute("gestion");
		gestion.actualizarPersona(persona);
		// Confirmación
		Confirmacion confirmacion=new Confirmacion();
		confirmacion.setDescripcion("La hoja de vida se ha modificado exitosamente");
		confirmacion.setArchivoEnlace("hojasvida.jsp");
		confirmacion.setMensajeEnlace("Volver a la página de hojas de vida");
		sesion.setAttribute("confirmacion", confirmacion);
		response.sendRedirect("confirmacion.jsp");
	}

}
