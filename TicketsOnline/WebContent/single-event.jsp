<%@ page language="java" contentType="text/html; charset=ISO-8859-15" pageEncoding="ISO-8859-15"%>
<%
	response.setHeader("Cache-Control", "no-Cache");
	response.setHeader("Pragma", "No-cache");
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	session.setAttribute("eventId", request.getParameter("eventId"));	
%>
<!DOCTYPE html>
<!--[if lt IE 10]> <html  lang="en" class="iex"> <![endif]-->
<!--[if (gt IE 10)|!(IE)]><!-->
<html lang="en">
<!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>XPTickets - P&aacute;gina del evento</title>
    <meta name="description" content="Detalles del evento">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <link rel="stylesheet" href="scripts/bootstrap/css/bootstrap.css">
    <script src="scripts/script.js"></script>
    <link rel="stylesheet" href="css/content-box.css">
    <link rel="stylesheet" href="scripts/magnific-popup.css">
    <link rel="stylesheet" href="scripts/flexslider/flexslider.css">
    <link rel="stylesheet" href="style.css">
    <link rel="icon" href="images/favicon.png">
    <!-- Extra optional content header -->
    <script>
	  function resizeIframe(obj) {
	    obj.style.height = obj.contentWindow.document.body.scrollHeight + 'px';
	  }
	  
	  var dialog = null;
	</script>
	<link rel="stylesheet" type="text/css" href="/statics/extjs/resources/css/ext-all-access.css" />
    <script type="text/javascript" src="/statics/extjs/ext-debug.js"></script>
    <script type="text/javascript" src="registration.js"></script>
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
	 <div id="legalese" style="display:none;">
        <b>Políticas de uso</b>
		<br>Bienvenidos a XpTickets, a continuación se describen las políticas de uso que rigen el uso del sitio web XpTickets.com para usar o visitar el sitio, expresamente manifiestas tu conformidad y te obligas con los términos y condiciones de esta página, así como todas las leyes y reglamentos aplicables de conformidad a la legislación vigente y aplicable para el uso del sitio. XpTickets, se reserva el derecho a cambiar estas condiciones en cualquier momento, las cuales aplicaran inmediatamente después de que hayan sido publicadas en este sitio. Por favor, visite esta página del sitio regularmente, si usted viola estas condiciones, XpTickets puede cancelar su uso de este sitio, excluirlo para el uso en un futuro de operaciones que pretenda realizar en el en el sitio, cancelar boletos que haya recibido a través del sitio, cancelar su pedido de boletos, y/o tomar la acción legal que juzgue conveniente para sus intereses.
		
		<br><br><b>Uso Permitido</b>
		<br>Usted manifiesta y acepta en este acto plenamente que conoce que únicamente está autorizado a visitar, ver y consultar las paginas para uso personal, que no se puede duplicar, descargar, publicar, modificar, distribuir o alterar el contenido de esta página para ningún propósito distinto al de revisar la información de los eventos y publicidad o compra de boletos, bienes y servicios que se ofrecen a través de este sitio.
		
		<br>El contenido y software de este sitio es propiedad de XpTickets y/o sus proveedores y está protegido bajo las leyes internacionales y nacionales de derecho de autor.
		
		<br><br><b>Política de compra y venta de boletos</b>
		<br>Revisa por favor la política de Venta, la cual regirá tu pedido o compra de cualquier boleto en el Sitio.
		
		<br>XpTickets únicamente distribuye boletos de los eventos ofrecidos, por lo que no es responsable del contenido ni de los horarios de los mismos.
		XpTickets te proporciona el mejor lugar disponible al momento de realizar la compra, de acuerdo al precio solicitado.
		El número de boletos adquiribles por una misma persona está limitado. Si en virtud de una o más operaciones de compra se excede el límite de boletos adquiribles por una misma persona, las solicitudes de compra serán canceladas sin previo aviso y sin responsabilidad para XpTickets. En el caso que se hubiere hecho algún cargo a la tarjeta de crédito o débito del Cliente por las compras que se cancelen por los supuestos previstos en este párrafo, se reembolsará al Cliente la totalidad de los cargos realizados.
		La compra de boletos a través de XpTickets generará un cargo por servicio y un cargo por entrega de boletos, el cual en este último caso puede variar dependiendo del tipo de entrega seleccionado por el Cliente y autorizado por XpTickets, en adición al precio de cada boleto. Los cargos por servicio y por entrega de boletos aplicarán para cada boleto adquirido a través de XpTickets.
		El perfeccionamiento, finalización o realización de la compra de boletos a través del sistema XpTickets está sujeta a la comprobación de los datos personales y de la tarjeta proporcionados por el cliente y a la autorización por parte del banco emisor de la tarjeta de crédito o débito cuyos datos ha proporcionado el Cliente para el pago de los boletos solicitados o por parte del banco aceptante. Si los datos personales o de la tarjeta de crédito proporcionados por el Cliente no coinciden con los datos a disposición del banco emisor de la tarjeta de crédito o débito o, aun coincidiendo los datos en cuestión, el banco emisor o el banco aceptante no autorizan el cargo solicitado por el Cliente, la compra no será procesada ni finalizada y los boletos serán ofrecidos para venta al público sin responsabilidad alguna para XpTickets.
		Sin perjuicio de lo anterior, el Cliente expresamente reconoce y acepta que la solicitud de compra de los boletos que realiza a través del sistema XpTickets es una oferta vinculante y que una vez recibida la autorización del banco emisor de la tarjeta de crédito o débito correspondiente o del banco aceptante, la operación de compra será definitiva y no estará sujeta a cambios, reembolsos, devoluciones o cancelaciones.
		En virtud de lo anterior, el Cliente expresamente reconoce y acepta que no tendrá derecho a cambios, reembolsos, devoluciones o cancelaciones aún en el supuesto de que no recoja o imprima los boletos adquiridos o no haga uso de ellos. El Cliente expresamente reconoce y acepta que sólo tendrá derecho al reembolso del costo de los boletos en caso que se cancele el evento para el cual adquirió los boletos, en el entendido que en ese supuesto el cliente NO tendrá derecho a que se le reembolsen los cargos por servicios y envío de XpTickets.
		En nuestra página de internet y centro telefónico no se aceptan tarjetas de débito, virtuales, mini o twin, ni tarjetas no esbozadas (si intenta comprar con una de estas tarjetas y llegase a realizarse el cargo no podremos entregarle sus boletos), por lo que si Ud. necesita pagar con alguno de estos tipos de tarjeta le recomendamos llevar a cabo la compra en uno de nuestros centros de venta.
		Si por algún motivo el evento se pospone o se cancela, el cargo por servicio no es reembolsable solo el costo del boleto. Para realizar el trámite de reembolso de un boleto por evento pospuesto se debe devolver el boleto. 
		XpTickets cobra un cargo adicional (cargo por servicio) a través de sus distintos canales de venta, el cual puede variar en función al canal y a la ciudad de compra.
		Veracidad de la Información que nos Proporcionas; Cumplimiento con las Leyes; Verificaciones y Autorizaciones
		La venta  de boletos a eventos de entretenimiento está regulada a nivel estatal, municipal o en su caso federal. Se te podrá pedir que proporciones cierta información cuando se involucre en ciertos procesos del Sitio. En este acto manifiestas y garantizas que toda la información que proporcionas es verdadera, completa y correcta, y que actualizarás toda la información a medida que cambie. También reconoces que  cumplirás en todo momento con los términos, condiciones y leyes que rigen el presente servicio, Y manifiestas estar de ACUERDO EN LIBERAR A XpTickets DE CUALQUIER RESPONSABILIDAD, DERIVADAS DE LA FALTAS COMETIDAS POR TI EN CONTRA DE CUALQUIER LEY, NOSOTROS CUMPLIREMOS CON LAS AUTORIDADES QUE ASÍ LO REQUIERAN Y LES PODREMOS PROPORCIONAR TODA LA INFORMACIÓN QUE TU NOS HAYAS REMITIDO PARA AYUDAR EN CUALQUIER INVESTIGACIÓN O PROCESO QUE PUEDAN LLEVAR A CABO. Si no podemos verificar o comprobar la autenticidad de cualquier información o boletos que nos proporciones durante cualquier proceso de inscripción, pedido, compra, publicación de boletos, venta, verificación de autenticidad, entrega, pago o proceso de remesa, o cualquier otro proceso no mencionado aquí, o si no podemos verificar o autorizar tu tarjeta de crédito o información de cuenta bancaria, entonces se te prohibirá el uso del Sitio.
		
		<br><br><b>Búsqueda y enlaces en sitios web</b>
		<br>El Sitio puede producir automáticamente resultados de búsqueda que tienen referencia o se enlazan a sitios de terceros a través de la Red Mundial World Wide Web (WWW). XpTickets no tiene control sobre estos sitios o su contenido. XpTickets no puede garantizar, representar o asegurar que el contenido de estos sitios sea exacto, legal y/o inofensivo. XpTickets no patrocina el contenido de sitios de terceros, ni asegura que no contendrán virus o que no impactarán de cualquier otra manera su computadora. Al utilizar este Sitio para realizar búsquedas o para enlazarse a otro sitio, tú estás de acuerdo y entiendes que no podrás realizar ningún reclamo en contra de XpTickets por cualquier daño o pérdida, cualquiera que sea, que resulte al usar el Sitio para obtener resultados de búsqueda o enlaces a otro sitio. Si tú tienes un problema con un enlace del Sitio, por favor envía un correo electrónico a contacto@xptix.com
		
		<br><br><b>Uso Comercial</b>
		<br>Todos los anuncios de este sitio están protegidos y no puedes hacer uso de ellos, XpTickets se reserva el derecho de bloquear el acceso, o cancelar boletos y compras cuando exista sospecha que se está violando alguna de nuestras políticas o se crea que está actuando en conexión con cualquier persona que se crea que esté violando la ley o los términos establecidos por o los derechos de XpTickets, o bien que ha ordenado un número de boletos que excede los límites establecidos. El violar cualquiera de las limitaciones o los términos de este Sitio será considerado como una violación de estos términos.
		
		<br><br><b>Acceso</b>
		<br>Acuerdas en que no utilizarás ningún robot, aparato automático, o de procesamiento manual para monitorear o copiar nuestras páginas Web o el contenido de éstas para otro propósito no autorizado sin previo permiso expresado por escrito. Acuerdas  en que no utilizarás ningún tipo de aparato, software o rutina que interfiera con el funcionamiento adecuado del Sitio. Estás de acuerdo en que no tomarás ninguna forma de acción que imponga una gran carga irrazonable o desproporcionada en nuestra infraestructura. Acuerdas en que no accederás, recargarás o actualizarás un evento de transacción o las páginas de boletos, o realizarás cualquier otra solicitud de servidores transacciones, más de una vez durante cualquier intervalo de tres segundos. Acuerdas en que no copiarás, reproducirás, alterarás, modificarás, crearás trabajos derivativos, o mostrarás públicamente ningún contenido (con excepción de que sea para tu propio uso personal, y uso no comercial) del Sitio sin previo permiso expreso por escrito de XpTickets.
		
		<br><br>Sospecha de violación de estas Condiciones de uso o de la ley; medidas cautelares, equitativas e indemnización de daños y perjuicios
		<br>Las violaciones de estas Condiciones de Uso, incluyendo el uso no autorizado del sitio, pueden ser investigadas y se pueden tomar las acciones legales necesarias, incluyendo más no limitándose acciones civiles, penales y cautelares. En este acto manifiestas tu conformidad que a la sola discreción de XpTickets, y sin previo aviso, XpTickets puede restringir tu acceso al Sitio, cancelar tu pedido de boletos o boletos adquiridos a través de tu pedido de boletos, cancelar su publicación de boletos, eliminar cualquier Contenido del Usuario no autorizado o ejercer cualquier otro recurso disponible, si XpTickets tiene elementos o indicios que tu conducta o la conducta de cualquier tercero con la cual XpTickets cree que actúas en común, o el Contenido de Usuario que usted proporcionaste,  son inconsistentes con los Términos establecidos en esta página o con la Legislación , o que violan los derechos de XpTickets, de un  usuario de XpTickets o cualquier otro usuario del Sitio. En este acto manifiestas tu conformidad en que los daños monetarios pueden no proporcionar una solución suficiente para XpTickets en cuanto a la violación de estos Términos y  con el pago de daños y perjuicios o cualquier otra compensación equitativa de tales violaciones.
		
		<br>Acuerdas en que el Uso Ilegal del sitio, como se definió anteriormente, puede causar daños y perjuicios a XpTickets que puede ocasionar entre otras cosas  el impedimento de mantener  una buena relación con los clientes, la pérdida de ventas y el aumento de los gastos involucrados en combatir el Uso.
		
		<br>XpTickets no tiene la obligación de proporcionarte ningún tipo de reembolso si ejerce cualquiera de sus derechos o recursos sise determina que  has violado estos Términos o cualquier otro derecho de XpTickets. Además, nos reservamos el derecho, a nuestra propia discreción, de modificar, suspender o descontinuar cualquier parte de este Sitio en cualquier momento, con o sin previo aviso. También nos reservamos el derecho, a nuestra propia discreción, de imponer límites en ciertas funciones y servicios y en restringir el acceso a cualquier parte o a todo el Sitio sin previo aviso. Nosotros no tendremos ninguna responsabilidad legal hacia ti o hacia cualquier tercera parte por ningún reclamo o causa de acción que surja al ejercer los derechos anteriormente mencionados.
		
		<br><br><b>Privacidad</b>
		<br>Creemos que tu privacidad y la privacidad de nuestros usuarios son muy importantes. Estos Términos están sujetos a la Política de Privacidad, la cual es incorporada a la presente para su referencia. XpTickets no es responsable o de cualquier otra manera responsable legalmente de cualquier uso o divulgación de su información de contacto, o información financiera, por parte de un tercero con quien XpTickets tenga permitido divulgar su información de contacto bajo esta política de privacidad. No deberás colocar en el Sitio información personal que pueda ser utilizada para identificarte o ponerse en contacto contigo, incluyendo, más no limitándose a tu nombre, dirección de residencia o trabajo, números de teléfono, localizadores, direcciones de correo electrónico o cualquier otro tipo de información. Si "colocas" o "publicas" esta información, XpTickets no puede prevenir que sea utilizada en una manera que viole estos Términos, la ley, o su privacidad personal o su seguridad. Al "colocar" o "publicar" dicha información en el Sitio, violas estos Términos.
		
		<br>XpTickets no es responsable por ninguna pérdida de datos que surja por la operación del Sitio o por aplicar los Términos. Instamos a todos los usuarios a que mantengan su propia versión de respaldo de cualquier Contenido de Usuario o cualquier otro tipo de informaciónque emitan al Sitio.
		
		<br><br><b>Derechos de Autor</b>
		<br>Nosotros determinaremos los privilegios de cualquier usuario que utilice este Sitio para transmitir ilegalmente cualquier material con derechos de autor sin previo consentimiento expreso de licencia, defensa válida o exención de uso justo para hacerlo. En particular, los usuarios que emitan Contenido de Usuario a este Sitio, ya sean artículos, imágenes, historias, software o cualquier otro material con derechos de autor; deben de asegurarse que el Contenido de Usuario cargado no infringe ningún derecho de autor o cualquier otro derecho de terceros (incluyendo, más no limitándose a, marcas registradas, secretos comerciales, derechos de privacidad o publicidad). Después de ser notificados debidamente por el dueño de los derechos de autor o sus agentes, y después de que se reciba una confirmación a través de una orden de corte o por admisión por parte del usuario de que han utilizado este Sitio como un instrumento de infracción ilegal, entonces nosotros cancelaremos los derechos del usuario que ha infringido los derechos de autor para usar y/o acceder a este Sitio. También podremos, a nuestra propia discreción, decidir y cancelar los derechos del usuario para usar o acceder al Sitio antes de ese momento, si creemos que se ha llevado a cabo la infracción en cuestión.
		
		<br><br><b>Cláusula de Exención de Responsabilidades</b>
		<br>XpTickets NO SE OBLIGA  A QUE EL SITIO ESTE LIBRE DE ERRORES, QUE NO TENDRÁ INTERRUPCIONES, O QUE PROPORCIONE RESULTADOS ESPECÍFICOS POR EL USO DEL SITIO O POR CUALQUIER CONTENIDO, BÚSQUEDA O ENCLACE EN EL SITIO Y SUS CONTENIDOS SON ENTREGADOS "COMO ESTÁ" Y "COMO ESTÉ DISPONIBLE". XpTickets NO PUEDE ASEGURAR QUE LOS ARCHIVOS QUE DESCARGE DEL SITIO ESTARÁN LIBRES DE VIRUS O DE CONTAMINACIÓN O DE CARACTERÍSTICAS DESTRUCTIVAS. XpTickets SE LIBERA  DE TODA RESPONSABILIDAD DE GARANTÍAS, XpTickets NO SERÁ RESPONSABLE POR NINGUN DAÑO DE CUALQUIER TIPO QUE SURJA DEL USO DE ESTE SITIO, INCLUYENDO SIN LIMITACIÓN, DAÑOS DIRECTOS, INDIRECTOS, INCIDENTALES, PUNITIVOS Y CONSECUENCIALES. XpTickets NO GARANTIZA  NINGÚ RESULTADO ESPECÍFICO DEL USO DE ESTE SITIO O EL USO DE LOS SERVICIOS DE XpTickets.
		
		<br>XpTickets SE LIBERA  DE CUALQUIER Y TODA RESPONSABILIDAD POR CUALQUIER HECHO, OMISIÓN Y CONDUCTA POR PARTE DE CUALQUIER USUARIO DE TERCEROS, USUARIO DE XpTickets, PUBLICISTAS Y/O PATROCINADORES DEL SITIO, EN CONEXIÓN CON EL SERVICIO DE XpTickets O DE CUALQUIER OTRA FORMA RELACIONADA CON SU USO DEL SITIO Y/O DEL SERVICIO DE XpTickets. XpTickets NO ES RESPONSABLE POR LOS PRODUCTOS, SERVICIOS, ACCIONES O FALTA DE ACCIÓN DE CUALQUIER LUGAR, ARTISTA, PROMOTOR O CUALQUIER TERCERO EN CONEXIÓN CON EL SITIO O AL QUE SE HA HECHO REFERENCIA EN EL SITIO.
		
		<br>Sin prejuicio de lo anteriormente mencionado, usted puede reportar la mala conducta de usuarios y/o publicistas de terceros, proveedores de servicios y/o productos a los que se hace referencia o que están incluidos en el Sitio de XpTickets a través de este formulario. XpTickets puede investigar el reclamo y tomar la acción adecuada, a su plena discreción.
		
		<br><br><b>Límites de Responsabilidad</b>
		<br>BAJO NINGUNA CIRCUNSTANCIA XpTickets SERÁ RESPONSABLE DE CUALQUIER DAÑO INDIRECTO, CONSECUENCIAL, EJEMPLAR, INCIDENTAL, ESPECIAL O PUNITIVO, O POR LA PÉRDIDA DE GANANCIAS, INGRESOS U OPORTUNIDADES DE NEGOCIOS, AUN CUANDO SE LE HAYA AVISADO A XpTickets DE LAS POSIBILIDADES DE TALES DAÑOS.
		
		<br><br><b>Disputas</b>
		<br>Si tienes una disputa y tu disputa involucra un evento (o el boleto de un evento) que está localizado en los Estados Unidos Mexicanos, entonces la disputa será regida por las leyes y la jurisdicción de la Ciudad de México, Distrito Federal, renunciando en este acto de cualquier otro jurisdicción que pudiere corresponderle de conformidad a su domicilio presente o futuro
		
		<br><br><b>Indemnización</b>
		<br>En este acto manifiestas de manera expresa que es tu voluntad el liberarnos de manera expresa  de cualquier indemnización, liberando a XpTickets y sus filiales, y a cada uno de los funcionarios, gerentes, agentes, empleados, contratistas y principales de las filiales de XpTickets de cualquier responsabilidad de perdida, obligación, reclamo o demanda, incluyendo los honorarios razonables de abogados, realizados por un tercero, debido a, o que surjan del uso de este Sitio, incluyendo también su uso del Sitio para proporcionar un enlace a otro Sitio o para cargar contenido o cualquier otra información al Sitio.
    </div>
    <div id="preloader"></div>
    <header class="fixed-top scroll-change" data-menu-anima="fade-left">
        <div class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="navbar-main navbar-middle">
                <div class="container">
                    <div class="scroll-hide">
                        <div class="container">
                            <a class="navbar-brand center" href="#">
                                <img src="img/logo.png" alt="logo" />
                            </a>
                        </div>
                    </div>
                    <div class="navbar-header">
                        <a class="navbar-brand" href="index.html"><img src="img/logo.png" alt="logo" /></a>
                        <button type="button" class="navbar-toggle">
                            <i class="fa fa-bars"></i>
                        </button>
                    </div>
                    <div class="collapse navbar-collapse">
                        <ul class="nav navbar-nav no-margins" id="menu">
                            <li><a href="index.html">Home</a></li>
                            <!-- 
                            <li class="dropdown">
                                <a href="events.html" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">Events <i class="caret"></i></a>
                                <ul class="dropdown-menu multi-level">
                                    <li><a href="events.html">All events</a></li>
                                    <li><a href="single-event.html">Single event</a></li>
                                    <li><a href="single-event-big.html">Big single event</a></li>
                                </ul>
                            </li>
                            <li><a href="gallery.html">Gallery</a></li>
                            <li class="scroll-show"><a href="index.html"><img src="images/logo-small.png" alt="logo"></a></li>
                            <li><a href="products.html">Products</a></li>
                            <li><a href="blog.html">Blog</a></li>
                            <li><a href="contacts.html">Contacts</a></li>
                             -->
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <div class="header-title ken-burn ken-burn-center" data-parallax="scroll" data-position="top" data-natural-height="670" data-natural-width="1300" data-image-src="images/events/Event00001_large.jpg">
        <div class="container">
            <div class="title-base">
                <h1 class="text-xl">INTOCABLE</h1>
                <hr class="h" />
                <p> 25 DE MAYO DEL 2018 A LAS 08:00 PM</p>
            </div>
        </div>
    </div>
    <div class="section-empty">
        <div class="container content">
            <div class="row">
                <div class="col-md-4">
                    <p>DETALLE</p>
                    <h2>INTOCABLE</h2>
                    <hr class="h" />
                    <ul class="list-texts">
                        <li><b>Fecha:</b> 25/MAY/2018</li>
                        <li><b>Hora:</b>  08:00 PM</li>
                        <li><b>Direcci&oacute;n:</b> Acropolis, Puebla</li>
                        <li><b>Tickets:</b> <a href="#">contact@xptix.com</a></li>
                        <li><b>Tel:</b> (221) 276 3966</li>
                        <li><b>Categoria:</b> Concierto</li>
                    </ul>
                </div>
                <div class="col-md-8">
                    <div class="row">
                        <div class="col-md-9">
                            <div class="flexslider slider visible-dir-nav nav-inner">
                                <ul class="slides">
                                    <li>
                                        <a class="img-box img-scale-rotate lightbox grayscale" href="images/gallery/Event00001_01.jpg" data-lightbox-anima="show-scale">
                                            <img alt="" src="images/gallery/Event00001_01.jpg" />
                                        </a>
                                    </li>
                                    <li>
                                        <a class="img-box img-scale-rotate lightbox grayscale" href="images/gallery/Event00001_02.jpg" data-lightbox-anima="show-scale">
                                            <img alt="" src="images/gallery/Event00001_02.jpg" />
                                        </a>
                                    </li>
                                    <li>
                                        <a class="img-box img-scale-rotate lightbox grayscale" href="images/gallery/Event00001_03.jpg" data-lightbox-anima="show-scale">
                                            <img alt="" src="images/gallery/Event00001_03.jpg" />
                                        </a>
                                    </li>
                                    <li>
                                        <a class="img-box img-scale-rotate lightbox grayscale" href="images/gallery/Event00001_04.jpg" data-lightbox-anima="show-scale">
                                            <img alt="" src="images/gallery/Event00001_04.jpg" />
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <p>FLYER OFICIAL</p>
                            <a class="img-box lightbox" href="images/Event00001_Slide.png" data-lightbox-anima="show-scale">
                                <img alt="" src="images/Event00001_Slide.png">
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="section-empty">
        <div class="container content">
            <div class="row">
                <div class="col-md-15">
                    <iframe src="seats.html" id="frame" name="frame" width="100%" frameborder="0" scrolling="no" onload="resizeIframe(this)"></iframe>
					<iframe src="html/payment.html" id="payment" name="payment" width="100%" frameborder="0" scrolling="no"></iframe>                    
                	<iframe src="html/minute-counter.html" id="minute-counter" name="minute-counter" width="100%" frameborder="0" scrolling="no"></iframe>
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
            <p class="text-bold">
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
                            <span>sistemas@xptix.com</span>
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
