<%@page import="controladores.Confirmacion"%>

<%@ include file="encabezado.jsp" %>
        
<section>

<%
try{
    Confirmacion confirmacion=(Confirmacion)session.getAttribute("confirmacion");
    out.println(confirmacion.getDescripcion()+"<br>");
    out.println("<a href="+confirmacion.getArchivoEnlace()+">"+confirmacion.getMensajeEnlace()+"</a>");
    session.setAttribute("confirmacion", null);
} catch (NullPointerException e) {
	out.println("Has llegado a un lugar equivocado<br>");
	out.println("<a href=\"inicio.jsp\">Ir a la página de inicio</a>");
}
%>
                    
</section>

<%@ include file="piedepagina.jsp" %>
                            