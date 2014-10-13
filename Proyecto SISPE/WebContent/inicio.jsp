<!DOCTYPE html>
<html>
<head>
    <title>SISPE - Sistema de Selecci�n de Personal</title>
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
    <script src="js/jquery.featureCarousel.js" type="text/javascript"></script>     
    <script type="text/javascript">
      $(document).ready(function() {
        $("#carousel").featureCarousel({
			autoPlay:7000,
			trackerIndividual:false,
			trackerSummation:false,
			topPadding:50,
			smallFeatureWidth:.9,
			smallFeatureHeight:.9,
			sidePadding:0,
			smallFeatureOffset:0
		});
      });
    </script>
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
	
	<%
	if(session.getAttribute("nombreUsuario")==null){
		response.sendRedirect("index.jsp");
	}
	%>
	
</head>
<body id="page1">
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
                                <li class="active"><a href="inicio.html">Home page</a></li>
                                <li><a href="company.jsp">our Company</a></li>
                                <li><a href="hojasvida.jsp">Hojas de vida</a></li>
                                <li><a href="projects.jsp">our projects</a></li>
                                <li><a href="Validacion?orden=cerrarsesion">Cerrar sesi�n</a></li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
            <div class="row-bot">
            	<div class="center-shadow">
                	<div class="carousel-container">
                      <div id="carousel">
                        <div class="carousel-feature">
                          <a href="#"><img class="carousel-image" alt="" src="images/gallery-img1.png"></a>                          
                        </div>
                        <div class="carousel-feature">
                          <a href="#"><img class="carousel-image" alt="" src="images/gallery-img3.png"></a>
                        </div>
                        <div class="carousel-feature">
                          <a href="#"><img class="carousel-image" alt="" src="images/gallery-img2.png"></a>
                        </div>
                      </div>
    				</div>
                </div>
            </div>
        </header>
        
        <!--==============================content================================-->
        <section id="content"><div class="ic">More Website Templates @ TemplateMonster.com. December10, 2011!</div>
            <div class="main">
                <div class="container_12">
                    <div class="wrapper">
                        <article class="grid_8">
                        	<h3>Welcome! Why Choose Us?</h3>
                            <em class="text-1 margin-bot">Consulting is one of <a class="link" target="_blank" href="http://blog.templatemonster.com/free-website-templates/">free website templates</a> created by TemplateMonster.com team. This website template is optimized for 1280X1024 screen resolution. It is also XHTML &amp; CSS valid.</em>
                            <div class="wrapper p4">
                            	<article class="grid_4 alpha">
                                	<div class="wrapper p1">
                                    	<figure class="img-indent2"><img src="images/page1-img1.png" alt="" /></figure>
                                        <div class="extra-wrap">
                                        	<div class="indent-top">
                                            	<h4>STARTING UP<em>How-to</em></h4>
                                            </div>
                                        </div>
                                    </div>
                                    <p class="prev-indent-bot">This <a class="color-2" href="http://blog.templatemonster.com/2011/12/10/free-website-template-jquery-slider-consulting-business/" target="_blank" rel="nofollow">Consulting Template</a> goes with two<br> packages – with PSD source files and<br> without them.</p>
                                    <a class="link-1" href="#">Know More</a>
                                </article>
                                <article class="grid_4 omega">
                                	<div class="wrapper p1">
                                    	<figure class="img-indent2"><img src="images/page1-img2.png" alt="" /></figure>
                                        <div class="extra-wrap">
                                        	<div class="indent-top">
                                            	<h4>Development<em>Strategies</em></h4>
                                            </div>
                                        </div>
                                    </div>
                                    <p class="prev-indent-bot">PSD source files of this Consulting Template<br> are available for free for the registered<br> members of TemplateMonster.com.</p>
                                    <a class="link-1" href="#">Know More</a>
                                </article>
                            </div>
                            <div class="wrapper">
                            	<article class="grid_4 alpha">
                                	<div class="wrapper p1">
                                    	<figure class="img-indent2"><img src="images/page1-img3.png" alt="" /></figure>
                                        <div class="extra-wrap">
                                        	<div class="indent-top">
                                            	<h4>Planning<em>Need Help?</em></h4>
                                            </div>
                                        </div>
                                    </div>
                                    <p class="prev-indent-bot">The basic package of this Consulting<br> Template (without PSD source) is available<br> for anyone without registration.</p>
                                    <a class="link-1" href="#">Know More</a>
                                </article>
                                <article class="grid_4 omega">
                                	<div class="wrapper p1">
                                    	<figure class="img-indent2"><img src="images/page1-img4.png" alt="" /></figure>
                                        <div class="extra-wrap">
                                        	<div class="indent-top">
                                            	<h4>INTEGRATION<em>Exploit your ideas</em></h4>
                                            </div>
                                        </div>
                                    </div>
                                    <p class="prev-indent-bot">This template has several pages: <a class="color-2" href="inicio.html">Home</a>,<br>  <a class="color-2" href="company.html">Company</a>, <a class="color-2" href="services.html">Services</a>, <a class="color-2" href="projects.html">Projects</a>, <a class="color-2" href="contact.html">Contacts</a> (note<br> that contact us form – doesn’t work).</p>
                                    <a class="link-1" href="#">Know More</a>
                                </article>
                            </div>
                        </article>
                        <article class="grid_4">
                        	<h3>Testimonials</h3>
                            <div class="wrapper p3">
                            	<figure class="img-indent"><a href="#"><img class="img-border" src="images/page1-img5.jpg" alt="" /></a></figure>
                                <div class="extra-wrap">
                                	<span class="text-2">Director</span>
                                    <h4 class="p2">Mary Ryan</h4>
                                    <p class="prev-indent-bot">Ut vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum atque corrupti...</p>
                                    <a class="link-1" href="#">Read More</a>
                                </div>
                            </div>
                            <div class="wrapper p3">
                            	<figure class="img-indent"><a href="#"><img class="img-border" src="images/page1-img6.jpg" alt="" /></a></figure>
                                <div class="extra-wrap">
                                	<span class="text-2">Senior assistant</span>
                                    <h4 class="p1">Bill Joel</h4>
                                    <p class="prev-indent-bot">Ut vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum atque corrupti...</p>
                                    <a class="link-1" href="#">Read More</a>
                                </div>
                            </div>
                            <div class="wrapper">
                            	<figure class="img-indent"><a href="#"><img class="img-border" src="images/page1-img7.jpg" alt="" /></a></figure>
                                <div class="extra-wrap">
                                	<span class="text-2">Junior researcher</span>
                                    <h4 class="prev-indent-bot">Michael Anderson</h4>
                                    <p class="prev-indent-bot">Ut vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum atque corrupti...</p>
                                    <a class="link-1" href="#">Read More</a>
                                </div>
                            </div>
                        </article>
                    </div>
                </div>
            </div>
            <div class="block"></div>
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
                            <!-- {%FOOTER_LINK} -->
                        </article>
                    </div>
                </div>
            </div>
        </div>
    </footer>
	<script type="text/javascript"> Cufon.now(); </script>
</body>
</html>

                            