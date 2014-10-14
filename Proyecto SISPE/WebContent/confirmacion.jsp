                                <!DOCTYPE html>
<%@page import="controladores.Confirmacion"%>
<%@page import="modelos.TipoDocumento"%>
<%@page import="modelos.Persona"%>

<html lang="en">
<head>
    <title></title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/reset.css" type="text/css" media="screen">
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
    <link rel="stylesheet" href="css/grid.css" type="text/css" media="screen">   
    <script src="js/jquery-1.6.3.min.js" type="text/javascript"></script>
    <script src="js/cufon-yui.js" type="text/javascript"></script>
    <script src="js/cufon-replace.js" type="text/javascript"></script>
    <script src="js/NewsGoth_400.font.js" type="text/javascript"></script>
	<script src="js/NewsGoth_700.font.js" type="text/javascript"></script>
    <script src="js/NewsGoth_Lt_BT_italic_400.font.js" type="text/javascript"></script>
    <script src="js/Vegur_400.font.js" type="text/javascript"></script> 
    <script src="js/FF-cash.js" type="text/javascript"></script>
	<!--[if lt IE 7]>
    <div style=' clear: both; text-align:center; position: relative;'>
        <a href="http://windows.microsoft.com/en-US/internet-explorer/products/ie/home?ocid=ie6_countdown_bannercode">
        	<img src="http://storage.ie6countdown.com/assets/100/images/banners/warning_bar_0000_us.jpg" border="0" height="42" width="820" alt="You are using an outdated browser. For a faster, safer browsing experience, upgrade for free today." />
        </a>
    </div>
	<![endif]-->
    <!--[if lt IE 9]>
   		<script type="text/javascript" src="js/html5.js"></script>
        <link rel="stylesheet" href="css/ie.css" type="text/css" media="screen">
	<![endif]-->
	
</head>
<body id="page2">
	<div class="extra">
    	<!--==============================header=================================-->
        <header>
        	<div class="row-top">
            	<div class="main">
                	<div class="wrapper">
                    	<h1><a href="inicio.html">Consulting</a></h1>
                        <form id="search-form" method="post" enctype="multipart/form-data">
                        <fieldset>	
                            <div class="search-field">
                                <input name="search" type="text" value="Search..." onBlur="if(this.value=='') this.value='Search...'" onFocus="if(this.value =='Search...' ) this.value=''" />
                                <a class="search-button" href="#" onClick="document.getElementById('search-form').submit()"></a>	
                            </div>						
                        </fieldset>
                    </form>
                    </div>
                </div>
            </div>
            <div class="menu-row">
            	<div class="menu-bg">
                    <div class="main">
                        <nav class="indent-left">
                            <ul class="menu wrapper">
                                <li><a href="inicio.jsp">Inicio</a></li>
                                <li class="active"><a href="hojasvida.jsp">Hojas de Vida</a></li>
                                <li><a href="services.html">our services</a></li>
                                <li><a href="projects.html">our projects</a></li>
                                <li><a href="Validacion?orden=cerrarsesion">Cerrar sesión</a></li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
            <div class="row-bot">
            	<div class="center-shadow">
                </div>
            </div>
        </header>
        
        <!--==============================content================================-->
        <section id="content">
            <div class="content-bg">
                <div class="main">
                    <div class="container_12">
                        <div class="wrapper">
                        
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
                            
                        </div>
                    </div>
                </div>
                <div class="block"></div>
            </div>
        </section>
    </div>
	
	<!--==============================footer=================================-->
    <footer>
        <div class="padding">
            <div class="main">
                <div class="container_12">
                    <div class="wrapper">
                        <article class="grid_8">
                            <h4>Contact Form:</h4>
                            <form id="contact-form" method="post">
                                <fieldset>
                                    <label><input name="email" value="Email" onBlur="if(this.value=='') this.value='Email'" onFocus="if(this.value =='Email' ) this.value=''" /></label>
                                    <label><input name="subject" value="Subject" onBlur="if(this.value=='') this.value='Subject'" onFocus="if(this.value =='Subject' ) this.value=''" /></label>
                                    <textarea onBlur="if(this.value=='') this.value='Message'" onFocus="if(this.value =='Message' ) this.value=''">Message</textarea>
                                    <div class="buttons">
                                        <a href="#" onClick="document.getElementById('contact-form').reset()">Clear</a>
                                        <a href="#" onClick="document.getElementById('contact-form').submit()">Send</a>
                                    </div>											
                                </fieldset>           
                            </form>
                        </article>
                        <article class="grid_4">
                        	<h4 class="indent-bot">Link to Us:</h4>
                            <ul class="list-services border-bot img-indent-bot">
                            	<li><a href="#">Facebook</a></li>
                                <li><a class="item-1" href="#">Twitter</a></li>
                                <li><a class="item-2" href="#">Picassa</a></li>
                                <li><a class="item-3" href="#">You Tube</a></li>
                            </ul>
                            <p class="p1">Consulting.com &copy; 2011 </p>
                            <p class="p1">Website Template by <a class="link" target="_blank" href="http://www.templatemonster.com/" rel="nofollow">TemplateMonster.com</a></p>
                        </article>
                    </div>
                </div>
            </div>
        </div>
    </footer>
	<script type="text/javascript"> Cufon.now(); </script>
</body>
</html>

                            