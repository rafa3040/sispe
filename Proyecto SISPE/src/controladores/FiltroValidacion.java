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

@WebFilter(filterName="/FiltroValidacion",urlPatterns="/*")
public class FiltroValidacion implements Filter {

    public FiltroValidacion() {

    }

    public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest peticion=(HttpServletRequest) request;
		HttpServletResponse respuesta=(HttpServletResponse) response;
		HttpSession sesion=peticion.getSession();
		boolean evadirFiltro=false;
		// evade el filtro si la página es /index.jsp o /Validacion
		String url=peticion.getServletPath();
		if(url.equals("/index.jsp") || url.equals("/Validacion")){
			evadirFiltro=true;
		}
		if(evadirFiltro==false){
			// si no se ha iniciado sesión, se redirige a index.jsp
			if(sesion==null || sesion.getAttribute("nombreUsuario")==null){
				respuesta.sendRedirect("index.jsp");
				return;
			}
		}
		// abre el filtro
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
