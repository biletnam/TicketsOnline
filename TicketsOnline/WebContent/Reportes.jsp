<%@ page language="java" contentType="text/html; charset=ISO-8859-15" pageEncoding="ISO-8859-15"%>
<%
	response.setHeader("Cache-Control", "no-Cache");
	response.setHeader("Pragma", "No-cache");
%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html lang="en">
<script>
	var companyId = <%=request.getParameter("companyId")%>;
	var eventId = <%=request.getParameter("eventId")%>;
</script>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>XP Tickets - Reportes</title>
    <meta name="description" content="Single one event and calendar HTML template dark black design and style.">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <link rel="stylesheet" href="scripts/bootstrap/css/bootstrap.css">
    <script src="scripts/script.js"></script>
    <link rel="stylesheet" href="css/content-box.css">
    <link rel="stylesheet" href="scripts/magnific-popup.css">
    <link rel="stylesheet" href="scripts/flexslider/flexslider.css">
    <link rel="stylesheet" href="style.css">
    <link rel="icon" href="images/favicon.png">
    <!-- Extra optional content header -->
	<link rel="stylesheet" type="text/css" href="/statics/extjs/resources/css/ext-all-access.css" />
    <script type="text/javascript" src="/statics/extjs/ext-debug.js"></script>
    <script type="text/javascript" src="Reportes.js"></script>
    <style type="text/css">
        /* Styling of global error indicator */
        .form-error-state {
            font-size: 11px;
            padding-left: 20px;
            height: 16px;
            line-height: 18px;
            background: no-repeat 0 0;
            cursor: default;
        }
        .form-error-state-invalid {
            color: #C30;
            background-image: url(/statics/extjs/resources/themes/images/default/form/exclamation.gif);
        }
        .form-error-state-valid {
            color: #090;
            background-image: url(/statics/extjs/resources/themes/images/default/dd/drop-yes.gif);
        }

        /* Error details tooltip */
        .errors-tip .error {
            font-style: italic;
        }
    </style>
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
                                <img src="images/company/<%=request.getParameter("companyId")%>/system/logo.png" alt="logo" />
                            </a>
                        </div>
                    </div>
                    <div class="navbar-header">
                        <a class="navbar-brand" href="index.jsp"><img src="images/company/<%=request.getParameter("companyId")%>/system/logo.png" alt="logo" /></a>
                        <button type="button" class="navbar-toggle">
                            <i class="fa fa-bars"></i>
                        </button>
                    </div>
                    <div class="collapse navbar-collapse">
                        <ul class="nav navbar-nav no-margins" id="menu">
                            <li><a href="index.jsp?companyId=<%=request.getParameter("companyId")%>">Home</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <div class="section-empty">
    	 <div class="container content">
            <hr class="space m" />
            <hr class="h" />
            <h2 class="text-m anima fade-top"><%=request.getSession().getAttribute("eventDesc")%> <%=request.getSession().getAttribute("eventDate")%></h2>
        </div>
        <div class="container content">
            <div class="row">
                <div class="col-md-15">
                    <iframe src="Reportes.html" id="frame" name="frame" width="100%" height="400" frameborder="0"></iframe>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-8"></div>
            </div>
        </div>
    </div>
    <div class="footer-section section-bg-animation header-animation">
        <div id="anima-layer-a" class="anima-layer fog-1"></div>
        <div id="anima-layer-b" class="anima-layer fog-2"></div>
        <div class="container content overlay-content text-center">
            <hr class="space" />
            <hr class="h" />
            <h1>CONTACTANOS</h1>
            <p class="text-m anima fade-top">
                XP Tickets, Mexico
            </p>
            <hr class="space s" />
            <a class="anima-button circle-button btn-sm" href="contacts.html"><i class="fa fa-map-marker"></i>Ubicanos en el mapa</a>
        </div>
    </div>
    <footer>
        <div class="container">
            <div class="row">
                <div class="col-md-8">
                    <hr class="space xs" />
                    <div class="row copy-row">
                        <div class="tag-row">
                            <span>Copyright © 2018</span>
                            <span>xptix.com</span>
                            <span>664 315 7108</span>
                            <span>contacto@xptix.com</span>
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
        <script src='scripts/jquery.progress-counter.js'></script>
        <script src='scripts/google.maps.min.js'></script>
        <script src='https://maps.googleapis.com/maps/api/js?sensor=false'></script>
        <script src='scripts/jquery.magnific-popup.min.js'></script>
        <script src='scripts/jquery.spritely.min.js'></script>
        <script src="scripts/smooth.scroll.min.js"></script>
    </footer>
</body>
</html>
