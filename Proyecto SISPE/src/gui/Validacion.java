package gui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.Usuario;
import persistence.UsuarioDao;

@WebServlet("/Validacion")
public class Validacion extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private UsuarioDao usuarioDao;
       
    public Validacion() {
    	usuarioDao=new UsuarioDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion=request.getSession();
		String orden=request.getParameter("orden");
		if (orden!=null && orden.equals("cerrarsesion")) {
			sesion.invalidate();
			response.sendRedirect("index.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion=request.getSession();
		String nombreUsuario=request.getParameter("nombreusuario");
		String contrasenha=request.getParameter("contrasenha");
		Usuario usuario=usuarioDao.consultarUsuario(nombreUsuario);
		if (usuario!=null) {
			if (usuario.getContrasenhaUsuario().equals(contrasenha)) {
				sesion.setAttribute("nombreUsuario", nombreUsuario);
				sesion.setAttribute("mensajeValidacion", "");
				response.sendRedirect("inicio.jsp");
			} else {
				sesion.setAttribute("mensajeValidacion", "Contraseña incorrecta");
				response.sendRedirect("index.jsp");
			}
		} else {
			sesion.setAttribute("mensajeValidacion", "El usuario no existe en el sistema");
			response.sendRedirect("index.jsp");
		}
	}

}
