<%@ include file="encabezado.jsp" %>
        
<section>

<h2>Informaci�n de las hojas de vida cargadas al sistema</h2>

<h2>Visualizaci�n de las hojas de vida cargadas al sistema</h2>

<form action="mostrarhojavida.jsp" method="post">

	<label>Ingrese el documento de identidad de la hoja de vida a mostrar</label>
	<br>
	<input type="text" name="documento">
	<input type="submit" value="Buscar">

</form>
                    
</section>

<%@ include file="piedepagina.jsp" %>
                            