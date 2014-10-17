package controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelos.GestionModelo;
import modelos.Usuario;

/**
 * Servlet que se encarga de iniciar y cerrar sesión
 * @author Solutions Developers
 *
 */
@WebServlet("/Validacion")
public class Validacion extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private GestionModelo gestionModelo;
       
    public Validacion() {
    	gestionModelo=new GestionModelo();
    }

    /**
     * Método para cerrar sesión
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion=request.getSession();
		String orden=request.getParameter("orden");
		if (orden!=null && orden.equals("cerrarsesion")) {
			sesion.invalidate();
			response.sendRedirect("index.jsp");
		}
	}

	/**
	 * Método para iniciar sesión
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion=request.getSession();
		String nombreUsuario=request.getParameter("nombreusuario");
		String contrasenha=request.getParameter("contrasenha");
		Usuario usuario=gestionModelo.consultarUsuario(nombreUsuario);
		if (usuario!=null) {
			if (usuario.getContrasenhaUsuario().equals(contrasenha)) {
				// se crea el atributo nombreusuario, para poder verificar
				// en otras paginas que existe una sesión iniciada
				sesion.setAttribute("nombreUsuario", nombreUsuario);
				// se inicializan los objetos de la logica que se van a 
				// usar a lo largo de la sesion
				sesion.setAttribute("gestionModelo", gestionModelo);
				// redireccion a la pagina principal
				sesion.setAttribute("mensajeValidacion", "");
				response.sendRedirect("index.jsp");
			} else {
				sesion.setAttribute("mensajeValidacion", "Contraseña incorrecta");
				response.sendRedirect("iniciosesion.xhtml");
			}
		} else {
			sesion.setAttribute("mensajeValidacion", "El usuario no existe en el sistema");
			response.sendRedirect("iniciosesion.xhtml");
		}
	}

}
