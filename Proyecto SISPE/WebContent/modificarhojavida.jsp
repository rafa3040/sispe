<%@page import="java.util.Calendar"%>
<%@page import="modelos.Experiencia"%>
<%@page import="modelos.Persona"%>
<%@ include file="encabezado.jsp" %>
        
<section>

<h2>Modificación de la hoja de vida solicitada</h2>
<br>
<br>

<%
Persona persona=(Persona)session.getAttribute("personaSeleccionada");
Experiencia[] experiencias=persona.getExperiencias();
Calendar fechaInicio, fechaFinal;
String[][] textosExperiencias=new String[3][6];

int i=0;
for( ; i<experiencias.length ; i++){
	fechaInicio=experiencias[i].getFechaInicio();
	fechaFinal=experiencias[i].getFechaFinal();
	textosExperiencias[i][0]=String.valueOf(fechaInicio.get(Calendar.YEAR));
	textosExperiencias[i][1]=String.valueOf(fechaInicio.get(Calendar.MONTH)+1);
	textosExperiencias[i][2]=String.valueOf(fechaInicio.get(Calendar.DAY_OF_MONTH)); 
	textosExperiencias[i][3]=String.valueOf(fechaFinal.get(Calendar.YEAR));
	textosExperiencias[i][4]=String.valueOf(fechaFinal.get(Calendar.MONTH)+1);
	textosExperiencias[i][5]=String.valueOf(fechaFinal.get(Calendar.DAY_OF_MONTH)); 
}
for( ; i<textosExperiencias.length ; i++){
	textosExperiencias[i][0]="";
	textosExperiencias[i][1]="";
	textosExperiencias[i][2]="";
	textosExperiencias[i][3]="";
	textosExperiencias[i][4]="";
	textosExperiencias[i][5]="";
}
%>

<form action="ModificarHojaVida" method="post">

	Número de documento
	<input name="numeroIdentificacion" value="<%out.print(persona.getNumeroIdentificacion());%>" disabled="disabled">
	<br>
	Tipo de documento
	<input name="tipoDocumento" value="<%out.print(persona.getTipoDocumento());%>" disabled="disabled">
	<br>
	Nombre
	<input name="nombrePersona" value="<%out.print(persona.getNombrePersona());%>">
	<br>
	Apellido
	<input name="apellidoPersona" value="<%out.print(persona.getApellidoPersona());%>">
	<br>
	Fecha de nacimiento
	<input name="fechaNacimiento" value="<%out.print(persona.textoFechaNacimiento());%>">
	<br>
	Teléfono
	<input name="telefono" value="<%out.print(persona.getTelefono());%>">
	<br>
	Correo electrónico
	<input name="telefono" value="<%out.print(persona.getCorreoElectronico());%>">
	<br>
	Profesión
	<input name="apellidoPersona" value="<%out.print(persona.getProfesion());%>">
	<br>
	Especialización
	<input name="apellidoPersona" value="<%out.print(persona.getEspecializacion());%>">
	<br>
	<input type="submit" value="Modificar hoja de vida">
	<input type="reset" value="Revertir cambios">

</form>
                    
</section>

<%@ include file="piedepagina.jsp" %>
                            