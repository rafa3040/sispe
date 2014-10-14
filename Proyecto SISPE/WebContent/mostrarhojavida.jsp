<%@page import="modelos.Experiencia"%>
<%@page import="modelos.Persona"%>
<%@page import="modelos.Gestion"%>

<%@ include file="encabezado.jsp" %>
        
<section>
                    
<h3>Información de la hoja de vida solicitada</h3>
<br>
<br>
<%

Gestion gestion=(Gestion)session.getAttribute("gestion");
long documento=Long.parseLong(request.getParameter("documento"));
Persona persona=gestion.consultarPersona(documento);

out.print("<h5>Numero de identificación</h5>"+persona.getNumeroIdentificacion()+"<br>");
out.print("<h5>Nombre</h5>"+persona.getNombrePersona()+"<br>");
out.print("<h5>Apellido</h5>"+persona.getApellidoPersona()+"<br>");
out.print("<h5>Tipo de documento</h5>"+persona.getTipoDocumento().toString()+"<br>");
out.print("<h5>Fecha de nacimiento</h5>"+persona.textoFechaNacimiento()+"<br>");
out.print("<h5>Teléfono</h5>"+persona.getTelefono()+"<br>");
out.print("<h5>Profesión</h5>"+persona.getProfesion()+"<br>");
out.print("<h5>Especialización</h5>"+persona.getEspecializacion()+"<br>");
Experiencia[] experiencias=persona.getExperiencias();
for(int i=0 ; i<experiencias.length ; i++){
	out.print("<h4>Experiencia laboral #"+(i+1)+"</h4><br>");
	out.print("<h5>Fecha inicio</h5>"+persona.getExperiencias()[i].textoFechaInicio()+"<br>");
	out.print("<h5>Fecha final</h5>"+persona.getExperiencias()[i].textoFechaFinal()+"<br>");
}
session.setAttribute("personaSeleccionada", persona);

%>
<br>
<a href="modificarhojavida.jsp">Modificar hoja de vida</a>
<br>
<a href="EliminarHojaVida">Eliminar hoja de vida</a>        
                    
</section>

<%@ include file="piedepagina.jsp" %>
                            