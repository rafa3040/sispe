<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<title>SISPE - Inicio de sesión</title>
<link rel="stylesheet" href="css/validacion.css" media="screen" type="text/css" />

</head>

<body>

<section id="content">
	<form action="Validacion" method="post">
		<h1>SISPE</h1>
		<h1>Inicio de sesión</h1>
		<%
		Object mensajeValidacion=session.getAttribute("mensajeValidacion");
		if(mensajeValidacion!=null){
			out.print("<h3>"+String.valueOf(mensajeValidacion)+"</h3>");
			session.setAttribute("mensajeValidacion", "");
		}
		%>
		<input name="nombreusuario" type="text" placeholder="Nombre de usuario" required="" id="username"/>
		<input name="contrasenha" type="password" placeholder="Contraseña" required="" id="password" />
		<input type="submit" value="Iniciar sesión" />
	</form>
</section>

</body>

</html>
