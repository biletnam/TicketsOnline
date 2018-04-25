<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="acciones.Event"%>
<%@page import="java.util.ArrayList"%>

<%
	response.setHeader("Cache-Control", "no-Cache");
	response.setHeader("Pragma", "No-cache");
	
	String companyId = null;
	
	companyId = request.getParameter("companyId") == null? "4" : request.getParameter("companyId").toString();  
	request.getSession().setAttribute("companyId", companyId);
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<!--[if lt IE 10]> <html  lang="en" class="iex"> <![endif]-->
<!--[if (gt IE 10)|!(IE)]><!-->
<html lang="en">
<!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>XP Tickets</title>
    <meta name="description" content="Nightlife, night club and bar / pub HTML template with dark / black design and style.">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <link rel="stylesheet" href="scripts/bootstrap/css/bootstrap.css">
    <script src="scripts/script.js"></script>
    <link rel="stylesheet" href='scripts/flexslider/flexslider.css'>
    <link rel="stylesheet" href="css/content-box.css">
    <link rel="stylesheet" href='css/components.css'>
    <link rel="stylesheet" href='scripts/magnific-popup.css'>
    <link rel="stylesheet" href='scripts/social.stream.css'>
    <link rel="stylesheet" href="style.css">
    <link rel="icon" href="images/favicon.png">
    <!-- Extra optional content header -->
</head>
<body>
    <div id="preloader"></div>
    <header class="fixed-top scroll-change" data-menu-anima="fade-left">
        <div class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="navbar-main navbar-middle">
                <div class="container">
                    <div class="scroll-hide">
                        <div class="container">
                            <a class="navbar-brand center" href="#">
                                <img src="images/system/logo.png" />
                            </a>
                        </div>
                    </div>
                    <div class="navbar-header">
                        <a class="navbar-brand" href="index.jsp"><img alt="" src="images/system/logo.png" /></a>
                        <button type="button" class="navbar-toggle">
                            <i class="fa fa-bars"></i>
                        </button>
                    </div>
                    <div class="collapse navbar-collapse">
                        <ul class="nav navbar-nav no-margins">
                            <li><a href="index.jsp">Home</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <div class="section-slider home-slider">
        <div class="flexslider advanced-slider slider" data-options="animation:fade,slideshowSpeed:5000">
            <ul class="slides">
            	<%
            		ArrayList<ArrayList<String>> arrEventos = new Event().getCurrent();
            		ArrayList<String> arrEvento = null;
            		
            		for (int i = 0; i < arrEventos.size(); i++){
            			arrEvento = arrEventos.get(i);
            	%>
                <li data-slider-anima="fade-top" data-timeline="asc" data-time="1000" data-timeline-time="500">
                    <div class="section-slide">
                        <div class="bg-cover" style="background-image:url('images/slide-bg.jpg')">
                        </div>
                        <div class="container">
                            <hr class="space" />
                            <img alt="" class="pos-slider pos-left pos-bottom anima anima-fade-bottom" style="z-index:0" width="530" src="images/slider/<%=arrEvento.get(0).toString()%>.png" />
                            <div class=" pos-slider pos-right text-right">
                                <h1 class="text-bold text-xl anima" style="z-index:9"><%=arrEvento.get(8).toString()%></h1>
                                <p class="text-m anima" style="z-index:9"><b><%=arrEvento.get(13).toString()%></b></p>
                                <p class="anima"><%=arrEvento.get(5).toString()%><br/></p>
                            </div>
                        </div>
                    </div>
                </li>
                <%
            		}
                %>
            </ul>
        </div>
    </div>
    <div class="section-empty">
        <div class="container content">
            <hr class="space m" />
            <hr class="h" />
            <h2 class="text-xl">NUESTROS EVENTOS</h2>
            <p><b>TRAEMOS PARA TI</b> SOLO LOS MEJORES EVENTOS</p>
            <hr class="space xs" />
            <div class="row">
               	<%
	           		arrEvento = null;
	           		
	           		for (int i = 0; i < arrEventos.size(); i++){
	           			arrEvento = arrEventos.get(i);
            	%>
                <div class="col-md-4">
                    <div class="advs-box advs-box-multiple boxed" data-anima="scale-rotate" data-trigger="hover">
                        <a class="img-box" href="Accion2/getEvent?id=<%=arrEvento.get(0).toString()%>"><img alt="" class="anima" src="images/art/<%=arrEvento.get(0).toString()%>.jpg" /></a>
                        <div class="circle anima-rotate-20 anima"><%=arrEvento.get(10).toString()%> <span><%=arrEvento.get(11).toString()%>, <%=arrEvento.get(12).toString()%></span></div>
                        <div class="advs-box-content">
                            <h3><%=arrEvento.get(8).toString()%></h3>
                            <p><%=arrEvento.get(9).toString()%></p>
                            <p>
                                <%=arrEvento.get(5).toString()%>
                                <%=arrEvento.get(14).toString()%>
                            </p>
                            <a class="anima-button circle-button btn-sm" href="Accion2/getEvent?id=2"><i class="fa fa-long-arrow-right"></i>Comprar Boletos </a>
                        </div>
                    </div>
                </div>
                <%
	           		}
                %>
            </div>
        </div>
    </div>
    <!-- 
    <div class="section-empty bg-color-2 section-main-event">
        <div class="container content">
            <div class="row">
                <div class="col-md-3">
                    <a class="img-box lightbox" href="images/ManaCountDown.jpg" data-lightbox-anima="show-scale">
                        <img alt="" src="images/ManaCountDown.jpg">
                    </a>
                </div>
                <div class="col-md-6">
                    <h3>MANA GIRA "VIVA TIJUANA"</h3>
                    <p>09 DE SEPTIEMBRE 2017 09:00 P.M.</p>
                    <p>La cuenta regresiva para la venta de boletos ya ha iniciado, esperalos!!</p>
                </div>
                <div class="col-md-3 text-right">
                    <div class="countdown text-s" data-time="07/14/2017 11:00:00" data-utc-offset="-8">
                        <div>
                            <span class="days countdown-values text-m">00</span><br /><span class="countdown-label">Days</span>
                        </div>
                        <div>
                            <span class="hours countdown-values text-m">00</span><br /><span class="countdown-label">Hours</span>
                        </div>
                        <div>
                            <span class="minutes countdown-values text-m">00</span><br /><span class="countdown-label">Mins</span>
                        </div>
                        <div>
                            <span class="seconds countdown-values text-m">00</span><br /><span class="countdown-label">Secs</span>
                        </div>
                    </div>
                    <hr class="space m" />
                    <hr class="space m" />
                    <hr class="space m" />
                    <hr class="space m" />
                    <!--
                    <a class="anima-button circle-button btn-sm" href="single-event-big.html"><i class="fa fa-long-arrow-right"></i>Event details </a>
                </div>
            </div>
        </div>
    </div>
     -->
    <!--
    <div class="section-empty bg-color-2 section-main-event">
        <div class="container content">
            <div class="row">
                <div class="col-md-3">
                    <a class="img-box lightbox" href="images/flyer-big.jpg" data-lightbox-anima="show-scale">
                        <img alt="" src="images/flyer-big.jpg">
                    </a>
                </div>
                <div class="col-md-6">
                    <h3>J. BALVIN EN CONCIERTO</h3>
                    <p>10 DE NOVIEMBRE 2017 09:00 P.M.</p>
                    <p>La cuenta regresiva para la venta de boletos ya ha iniciado, esperalos!!</p>
                </div>
                <div class="col-md-3 text-right">
                    <div class="countdown text-s" data-time="07/20/2017 11:00:00" data-utc-offset="-5">
                        <div>
                            <span class="days countdown-values text-m">00</span><br /><span class="countdown-label">Days</span>
                        </div>
                        <div>
                            <span class="hours countdown-values text-m">00</span><br /><span class="countdown-label">Hours</span>
                        </div>
                        <div>
                            <span class="minutes countdown-values text-m">00</span><br /><span class="countdown-label">Mins</span>
                        </div>
                        <div>
                            <span class="seconds countdown-values text-m">00</span><br /><span class="countdown-label">Secs</span>
                        </div>
                    </div>
                    <hr class="space m" />
                    <hr class="space m" />
                    <hr class="space m" />
                    <hr class="space m" />
                    <a class="anima-button circle-button btn-sm" href="single-event-big.html"><i class="fa fa-long-arrow-right"></i>Event details </a>
                </div>
            </div>
        </div>
    </div>
	 -->
    <div class="section-bg-image parallax-window text-center" data-natural-height="585" data-natural-width="1920" data-parallax="scroll" data-image-src="images/bg-1.jpg">
        <div class="container content">
            <hr class="space m" />
            <h2 class="text-xl">ULTIMAS FOTOS</h2>
            <p>MEJORES MOMENTOS</p>
            <hr class="space xs" />
            <div class="grid-list gallery">
                <div class="grid-box row" data-lightbox-anima="show-scale">
              		<%
	           		arrEvento = null;
	           		
	           		for (int i = 0; i < arrEventos.size(); i++){
	           			arrEvento = arrEventos.get(i);
            	%>
                    <div class="grid-item col-md-3">
                        <a class="img-box" href="images/gallery/bestMoments/<%=arrEvento.get(0).toString()%>_1.jpg">
                            <img alt="" src="images/gallery/bestMoments/<%=arrEvento.get(0).toString()%>_1.jpg">
                        </a>
                    </div>
                    <div class="grid-item col-md-3">
                        <a class="img-box" href="images/gallery/bestMoments/<%=arrEvento.get(0).toString()%>_2.jpg">
                            <img alt="" src="images/gallery/bestMoments/<%=arrEvento.get(0).toString()%>_2.jpg">
                        </a>
                    </div>
                    	<%
			           		}
                    	%>
                </div>
                <ul class="pagination-sm pagination-grid hide-first-last" data-page-items="4" data-pagination-anima="show-scale"></ul>
            </div>
        </div>
    </div>
    <!-- 
    <div class="section-bg-image" style="background-image:url(images/bg-social.png)">
        <div class="container content">
            <div class="row">
                <div class="col-md-12 text-center">
                    <hr class="space m" />
                    <h2 class="text-xl">NUESTRAS REDES SOCIALES</h2>
                    <p>Mantente en contacto con nosotros</p>
                    <hr class="space m" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="social-feed-fb no-comments" data-social-id="6798562721" data-options="limit:2"></div>
                </div>
                <div class="col-md-6">
                    <div class="social-feed-tw" data-social-id="fivestarconcert" data-options="limit:5"></div>
                </div>
            </div>
        </div>
    </div>
     -->
    <div class="footer-section section-bg-animation header-animation">
        <div id="anima-layer-a" class="anima-layer clouds-2"></div>
        <div id="anima-layer-b" class="anima-layer clouds-1"></div>
        <div class="container content overlay-content text-center">
            <hr class="space" />
            <hr class="h" />
            <h1>CONTACTO</h1>
            <p class="text-bold">
                sistemas@xptix.com
            </p>
            <hr class="space s" />
            <!-- <a class="anima-button circle-button btn-sm" href="contacts.html"><i class="fa fa-map-marker"></i>Localice Nuestras Taquillas </a> -->
            <a class="anima-button circle-button btn-sm" href=""><i class="fa fa-map-marker"></i>Localice Nuestras Taquillas </a>
        </div>
    </div>
    <footer>
        <div class="container">
            <div class="row">
                <div class="col-md-8">
                    <hr class="space xs" />
                    <div class="copy-row">
                        <div class="tag-row">
                            <span>Copyright © 2018</span>
                            <span>XP Tickets</span>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 text-right">
                    <div class="btn-group social-group">
                        <a target="_blank" href="#"><i class="fa fa-facebook"></i></a>
                        <a target="_blank" href="#"><i class="fa fa-twitter"></i></a>
                        <a target="_blank" href="#"><i class="fa fa-instagram"></i></a>
                        <a target="_blank" href="#"><i class="fa fa-youtube"></i></a>
                    </div>
                </div>
            </div>
        </div>
        <link rel="stylesheet" href="scripts/font-awesome/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/animations.css">
        <script async src="scripts/bootstrap/js/bootstrap.min.js"></script>
        <script src="scripts/imagesloaded.min.js"></script>
        <script src='scripts/flexslider/jquery.flexslider-min.js'></script>
        <script src='scripts/parallax.min.js'></script>
        <script src='scripts/jquery.magnific-popup.min.js'></script>
        <script src='scripts/jquery.progress-counter.js'></script>
        <script src='scripts/jquery.twbsPagination.min.js'></script>
        <script src='scripts/social.stream.min.js'></script>
        <script src='scripts/jquery.spritely.min.js'></script>
        <script src="scripts/smooth.scroll.min.js"></script>
    </footer>
</body>
</html>
