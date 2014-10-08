package gui;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.Conexion;

@WebServlet("/Validacion")
public class Validacion extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public Validacion() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombreusuario=request.getParameter("nombreusuario");
		String contrasenha=request.getParameter("contrasenha");

		Conexion conexion=new Conexion();
		
		
		
		PrintWriter escritor=response.getWriter();
		escritor.write(nombreusuario);
		escritor.write(contrasenha);
	}

}
