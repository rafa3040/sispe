package controladores;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class FiltroSesion implements Filter {

    public FiltroSesion() {

    }

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest peticion=(HttpServletRequest) request;
		HttpServletResponse respuesta=(HttpServletResponse) response;
		HttpSession sesion=peticion.getSession();
		String url=peticion.getServletPath();
		// Verifica si la URL requiere evadir el filtro
		if (evadirFiltro(url)) {
			chain.doFilter(request, response);
			return;
		}
		// Verifica si se ha iniciado sesión
		if(sesion==null || sesion.getAttribute("nombreUsuario")==null){
			respuesta.sendRedirect("iniciosesion.xhtml");
			return;
		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}
	
	public boolean evadirFiltro(String url){
		if (url.equals("/iniciosesion.xhtml")){
			return true;
		} else if (url.equals("/GestionSesion")){
			return true;
		} else if (url.indexOf("/javax.faces.resource/") != -1){
			return true;
		} else {
			return false;
		}
	}

}
