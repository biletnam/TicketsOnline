<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<!--[if lt IE 10]> <html  lang="en" class="iex"> <![endif]-->
<!--[if (gt IE 10)|!(IE)]><!-->
<html lang="en">
<!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=2">
    <title><s:property value="nameCompany"/> - P&aacute;gina del evento <s:property value="description"/></title>
    <meta name="description" content="Detalles del evento">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <link rel="stylesheet" href="../scripts/bootstrap/css/bootstrap.css">
    <script src="../scripts/script.js"></script>
    <link rel="stylesheet" href="../css/content-box.css">
    <link rel="stylesheet" href="../scripts/magnific-popup.css">
    <link rel="stylesheet" href="../scripts/flexslider/flexslider.css">
    <link rel="stylesheet" href="../style.css">
    <link rel="icon" href="../images/favicon.png">

    <script>
      var companyId = <s:property value="companyId"/>; 
      var eventId = <s:property value="id"/>;
      var key = '<s:property value="payPalKey"/>';
    
	  function resizeIframe(obj) {
	    obj.style.height = obj.contentWindow.document.body.scrollHeight + 'px';
	  }
	  
	  var dialog = null;
	</script>
	
	<link rel="stylesheet" type="text/css" href="/statics/extjs/resources/css/ext-all-access.css" />
    <script type="text/javascript" src="/statics/extjs/ext-debug.js"></script>
    <script type="text/javascript" src="../js/registration.js"></script>
    <script type="text/javascript" src="../js/ticketReceipt.js"></script>
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
        <b>Pol&iacute;ticas de uso</b>
		<br>Bienvenidos a <s:property value="nameCompany"/>, a continuaci&oacute;n se describen las pol&iacute;ticas de uso que rigen el uso del sitio web <s:property value="nameCompany"/>.com para usar o visitar el sitio, expresamente manifiestas tu conformidad y te obligas con los t&eacute;rminos y condiciones de esta p&aacute;gina, as&iacute;≠ como todas las leyes y reglamentos aplicables de conformidad a la legislaci&oacute;n vigente y aplicable para el uso del sitio. <s:property value="nameCompany"/>, se reserva el derecho a cambiar estas condiciones en cualquier momento, las cuales aplicaran inmediatamente despu&eacute;s de que hayan sido publicadas en este sitio. Por favor, visite esta p&iacute;gina del sitio regularmente, si usted viola estas condiciones, <s:property value="nameCompany"/> puede cancelar su uso de este sitio, excluirlo para el uso en un futuro de operaciones que pretenda realizar en el en el sitio, cancelar boletos que haya recibido a trav&eacute;s del sitio, cancelar su pedido de boletos, y/o tomar la acci&oacute;n legal que juzgue conveniente para sus intereses.
		
		<br><br><b>Uso Permitido</b>
		<br>Usted manifiesta y acepta en este acto plenamente que conoce que &uacute;nicamente est&aacute;° autorizado a visitar, ver y consultar las paginas para uso personal, que no se puede duplicar, descargar, publicar, modificar, distribuir o alterar el contenido de esta p&aacute;gina para ning&uacute;n prop&iacute;sito distinto al de revisar la informaci&oacute;n de los eventos y publicidad o compra de boletos, bienes y servicios que se ofrecen a trav&eacute;s de este sitio.
		
		<br>El contenido y software de este sitio es propiedad de <s:property value="nameCompany"/> y/o sus proveedores y est&aacute; protegido bajo las leyes internacionales y nacionales de derecho de autor.
		
		<br><br><b>Pol&iacute;tica de compra y venta de boletos</b>
		<br>Revisa por favor la pol&iacute;tica de Venta, la cual regir&aacute;° tu pedido o compra de cualquier boleto en el Sitio.
		
		<br><s:property value="nameCompany"/> &uacute;nicamente distribuye boletos de los eventos ofrecidos, por lo que no es responsable del contenido ni de los horarios de los mismos.
		<s:property value="nameCompany"/> te proporciona el mejor lugar disponible al momento de realizar la compra, de acuerdo al precio solicitado.
		El n&uacute;mero de boletos adquiribles por una misma persona est&aacute; limitado. Si en virtud de una o m&aacute;s operaciones de compra se excede el l&iacute;mite de boletos adquiribles por una misma persona, las solicitudes de compra ser&aacute;n canceladas sin previo aviso y sin responsabilidad para <s:property value="nameCompany"/>. En el caso que se hubiere hecho alg&uacute;n cargo a la tarjeta de cr&eacute;dito o d&eacute;bito del Cliente por las compras que se cancelen por los supuestos previstos en este p&aacute;rrafo, se reembolsar&aacute; al Cliente la totalidad de los cargos realizados.
		La compra de boletos a trav&eacute;s de <s:property value="nameCompany"/> generar&aacute; un cargo por servicio y un cargo por entrega de boletos, el cual en este &uacute;ltimo caso puede variar dependiendo del tipo de entrega seleccionado por el Cliente y autorizado por <s:property value="nameCompany"/>, en adici&oacute;n al precio de cada boleto. Los cargos por servicio y por entrega de boletos aplicar&aacute;n para cada boleto adquirido a trav&eacute;s de <s:property value="nameCompany"/>.
		El perfeccionamiento, finalizaci&oacute;n o realizaci&oacute;n de la compra de boletos a trav&eacute;s del sistema <s:property value="nameCompany"/> est&aacute; sujeta a la comprobaci&oacute;n de los datos personales y de la tarjeta proporcionados por el cliente y a la autorizaci&oacute;n por parte del banco emisor de la tarjeta de cr&eacute;dito o d&eacute;bito cuyos datos ha proporcionado el Cliente para el pago de los boletos solicitados o por parte del banco aceptante. Si los datos personales o de la tarjeta de cr&eacute;dito proporcionados por el Cliente no coinciden con los datos a disposici&oacute;n del banco emisor de la tarjeta de cr&eacute;dito o d&eacute;bito o, aun coincidiendo los datos en cuesti&oacute;n, el banco emisor o el banco aceptante no autorizan el cargo solicitado por el Cliente, la compra no ser&aacute; procesada ni finalizada y los boletos ser&aacute;n ofrecidos para venta al pl&uacute;blico sin responsabilidad alguna para <s:property value="nameCompany"/>.
		Sin perjuicio de lo anterior, el Cliente expresamente reconoce y acepta que la solicitud de compra de los boletos que realiza a trav&eacute;s del sistema <s:property value="nameCompany"/> es una oferta vinculante y que una vez recibida la autorizaci&oacute;n del banco emisor de la tarjeta de cr&eacute;dito o d&eacute;bito correspondiente o del banco aceptante, la operaci&oacute;n de compra ser&iacute; definitiva y no estar&aacute; sujeta a cambios, reembolsos, devoluciones o cancelaciones.
		En virtud de lo anterior, el Cliente expresamente reconoce y acepta que no tendr&aacute; derecho a cambios, reembolsos, devoluciones o cancelaciones a&uacute;n en el supuesto de que no recoja o imprima los boletos adquiridos o no haga uso de ellos. El Cliente expresamente reconoce y acepta que s&oacute;lo tendr&aacute; derecho al reembolso del costo de los boletos en caso que se cancele el evento para el cual adquirir&aacute; los boletos, en el entendido que en ese supuesto el cliente NO tendr&aacute; derecho a que se le reembolsen los cargos por servicios y env&iacute;o de <s:property value="nameCompany"/>.
		En nuestra p&aacute;gina de internet y centro telef&oacute;nico no se aceptan tarjetas de d&eacute;bito, virtuales, mini o twin, ni tarjetas no esbozadas (si intenta comprar con una de estas tarjetas y llegase a realizarse el cargo no podremos entregarle sus boletos), por lo que si Ud. necesita pagar con alguno de estos tipos de tarjeta le recomendamos llevar a cabo la compra en uno de nuestros centros de venta.
		Si por alg&uacute;n motivo el evento se pospone o se cancela, el cargo por servicio no es reembolsable solo el costo del boleto. Para realizar el tr&aacute;mite de reembolso de un boleto por evento pospuesto se debe devolver el boleto. 
		<s:property value="nameCompany"/> cobra un cargo adicional (cargo por servicio) a trav&eacute;s de sus distintos canales de venta, el cual puede variar en funci&oacute;n al canal y a la ciudad de compra.
		Veracidad de la Informaci&oacute;n que nos Proporcionas; Cumplimiento con las Leyes; Verificaciones y Autorizaciones
		La venta  de boletos a eventos de entretenimiento est&aacute; regulada a nivel estatal, municipal o en su caso federal. Se te podr&aacute; pedir que proporciones cierta informaci&oacute;n cuando se involucre en ciertos procesos del Sitio. En este acto manifiestas y garantizas que toda la informaci&oacute;n que proporcionas es verdadera, completa y correcta, y que actualizar&aacute;s toda la informaci&oacute;n a medida que cambie. Tambi&eacute;n reconoces que  cumplir&aacute;s en todo momento con los t&eacute;rminos, condiciones y leyes que rigen el presente servicio, Y manifiestas estar de ACUERDO EN LIBERAR A <s:property value="nameCompany"/> DE CUALQUIER RESPONSABILIDAD, DERIVADAS DE LA FALTAS COMETIDAS POR TI EN CONTRA DE CUALQUIER LEY, NOSOTROS CUMPLIREMOS CON LAS AUTORIDADES QUE ASI;ç LO REQUIERAN Y LES PODREMOS PROPORCIONAR TODA LA INFORMACIOìN QUE TU NOS HAYAS REMITIDO PARA AYUDAR EN CUALQUIER INVESTIGACIOìN O PROCESO QUE PUEDAN LLEVAR A CABO. Si no podemos verificar o comprobar la autenticidad de cualquier informaci&oacute;n o boletos que nos proporciones durante cualquier proceso de inscripci&oacute;n, pedido, compra, publicaci&oacute;n de boletos, venta, verificaci&oacute;n de autenticidad, entrega, pago o proceso de remesa, o cualquier otro proceso no mencionado aqu&iacute;, o si no podemos verificar o autorizar tu tarjeta de cr&eacute;dito o informaci&oacute;n de cuenta bancaria, entonces se te prohibir&aacute; el uso del Sitio.
		
		<br><br><b>B&uacute;squeda y enlaces en sitios web</b>
		<br>El Sitio puede producir autom&aacute;ticamente resultados de b&uacute;squeda que tienen referencia o se enlazan a sitios de terceros a trav&eacute;s de la Red Mundial World Wide Web (WWW). <s:property value="nameCompany"/> no tiene control sobre estos sitios o su contenido. <s:property value="nameCompany"/> no puede garantizar, representar o asegurar que el contenido de estos sitios sea exacto, legal y/o inofensivo. <s:property value="nameCompany"/> no patrocina el contenido de sitios de terceros, ni asegura que no contendr&aacute;n virus o que no impactar&aacute;n de cualquier otra manera su computadora. Al utilizar este Sitio para realizar b&uacute;squedas o para enlazarse a otro sitio, t&uacute; est&aacute;s de acuerdo y entiendes que no podr&aacute;s realizar ning&uacute;n reclamo en contra de <s:property value="nameCompany"/> por cualquier dato o p&eacute;rdida, cualquiera que sea, que resulte al usar el Sitio para obtener resultados de b&uacute;queda o enlaces a otro sitio. Si tu tienes un problema con un enlace del Sitio, por favor env&iacute;a un correo electr&oacute;nico a <s:property value="emailCompany"/>
		
		<br><br><b>Uso Comercial</b>
		<br>Todos los anuncios de este sitio est&aacute;n protegidos y no puedes hacer uso de ellos, <s:property value="nameCompany"/> se reserva el derecho de bloquear el acceso, o cancelar boletos y compras cuando exista sospecha que se est&aacute; violando alguna de nuestras pol&iacute;ticas o se crea que est&aacute; actuando en conexi&oacute;n con cualquier persona que se crea que est&eacute; violando la ley o los t&eacute;rminos establecidos por o los derechos de <s:property value="nameCompany"/>, o bien que ha ordenado un n&uacute;mero de boletos que excede los l&iacute;mites establecidos. El violar cualquiera de las limitaciones o los t&eacute;rminos de este Sitio ser&aacute; considerado como una violaci&oacute;n de estos t&eacute;rminos.
		
		<br><br><b>Acceso</b>
		<br>Acuerdas en que no utilizar&aacute;s ning&uacute;n robot, aparato autom&aacute;tico, o de procesamiento manual para monitorear o copiar nuestras p&aacute;ginas Web o el contenido de &eacute;stas para otro prop&oacute;sito no autorizado sin previo permiso expresado por escrito. Acuerdas  en que no utilizar&aacute;s ning&uacute;n tipo de aparato, software o rutina que interfiera con el funcionamiento adecuado del Sitio. Est&aacute;s de acuerdo en que no tomar&aacute;s ninguna forma de acci&oacute;n que imponga una gran carga irrazonable o desproporcionada en nuestra infraestructura. Acuerdas en que no acceder&aacute;s, recargar&aacute;s o actualizar&aacute;s un evento de transacci&oacute;n o las p&aacute;ginas de boletos, o realizar&aacute;s cualquier otra solicitud de servidores transacciones, m&aacute;s de una vez durante cualquier intervalo de tres segundos. Acuerdas en que no copiar&aacute;s, reproducir&aacute;s, alterar&aacute;s, modificar&aacute;s, crear&aacute;s trabajos derivativos, o mostrar&aacute;s p&uacute;blicamente ning&uacute;n contenido (con excepci&oacute;n de que sea para tu propio uso personal, y uso no comercial) del Sitio sin previo permiso expreso por escrito de <s:property value="nameCompany"/>.
		
		<br><br>Sospecha de violaci&oacute;n de estas Condiciones de uso o de la ley; medidas cautelares, equitativas e indemnizaci&oacute;n de da&ntilde;os y perjuicios
		<br>Las violaciones de estas Condiciones de Uso, incluyendo el uso no autorizado del sitio, pueden ser investigadas y se pueden tomar las acciones legales necesarias, incluyendo m&aacute;s no limit&aacute;ndose acciones civiles, penales y cautelares. En este acto manifiestas tu conformidad que a la sola discreci&oacute;n de <s:property value="nameCompany"/>, y sin previo aviso, <s:property value="nameCompany"/> puede restringir tu acceso al Sitio, cancelar tu pedido de boletos o boletos adquiridos a trav&eacute;s de tu pedido de boletos, cancelar su publicaci&oacute;n de boletos, eliminar cualquier Contenido del Usuario no autorizado o ejercer cualquier otro recurso disponible, si <s:property value="nameCompany"/> tiene elementos o indicios que tu conducta o la conducta de cualquier tercero con la cual <s:property value="nameCompany"/> cree que act&uacute;as en com&uacute;n, o el Contenido de Usuario que usted proporcionaste,  son inconsistentes con los T&eacute;rminos establecidos en esta p&aacute;gina o con la Legislaci&oacute;n , o que violan los derechos de <s:property value="nameCompany"/>, de un  usuario de <s:property value="nameCompany"/> o cualquier otro usuario del Sitio. En este acto manifiestas tu conformidad en que los da&ntilde;os monetarios pueden no proporcionar una soluci&oacute;n suficiente para <s:property value="nameCompany"/> en cuanto a la violaci&oacute;n de estos T&eacute;rminos y  con el pago de da&ntilde;os y perjuicios o cualquier otra compensaci&oacute;n equitativa de tales violaciones.
		
		<br>Acuerdas en que el Uso Ilegal del sitio, como se defini&oacute; anteriormente, puede causar da&ntilde;os y perjuicios a <s:property value="nameCompany"/> que puede ocasionar entre otras cosas  el impedimento de mantener  una buena relaci&oacute;n con los clientes, la p&eacute;rdida de ventas y el aumento de los gastos involucrados en combatir el Uso.
		
		<br><s:property value="nameCompany"/> no tiene la obligaci&oacute;n de proporcionarte ning&uacute;n tipo de reembolso si ejerce cualquiera de sus derechos o recursos sise determina que  has violado estos T&eacute;rminos o cualquier otro derecho de <s:property value="nameCompany"/>. Adem&aacute;s, nos reservamos el derecho, a nuestra propia discreci&oacute;n, de modificar, suspender o descontinuar cualquier parte de este Sitio en cualquier momento, con o sin previo aviso. Tambi&eacute;n nos reservamos el derecho, a nuestra propia discreci&oacute;n, de imponer l&iacute;mites en ciertas funciones y servicios y en restringir el acceso a cualquier parte o a todo el Sitio sin previo aviso. Nosotros no tendremos ninguna responsabilidad legal hacia ti o hacia cualquier tercera parte por ning&uacute;n reclamo o causa de acci&oacute;n que surja al ejercer los derechos anteriormente mencionados.
		
		<br><br><b>Privacidad</b>
		<br>Creemos que tu privacidad y la privacidad de nuestros usuarios son muy importantes. Estos T&eacute;rminos est&aacute;n sujetos a la Pol&iacute;tica de Privacidad, la cual es incorporada a la presente para su referencia. <s:property value="nameCompany"/> no es responsable o de cualquier otra manera responsable legalmente de cualquier uso o divulgaci&oacute;n de su informaci&oacute;n de contacto, o informaci&oacute;n financiera, por parte de un tercero con quien <s:property value="nameCompany"/> tenga permitido divulgar su informaci&oacute;n de contacto bajo esta pol&iacute;tica de privacidad. No deber&aacute;s colocar en el Sitio informaci&oacute;n personal que pueda ser utilizada para identificarte o ponerse en contacto contigo, incluyendo, m&aacute;s no limit&aacute;ndose a tu nombre, direcci&oacute;n de residencia o trabajo, n&uacute;meros de tel&eacute;fono, localizadores, direcciones de correo electr&oacute;nico o cualquier otro tipo de informaci&oacute;n. Si "colocas" o "publicas" esta informaci&oacute;n, <s:property value="nameCompany"/> no puede prevenir que sea utilizada en una manera que viole estos T&eacute;rminos, la ley, o su privacidad personal o su seguridad. Al "colocar" o "publicar" dicha informaci&oacute;n en el Sitio, violas estos T&eacute;rminos.
		
		<br><s:property value="nameCompany"/> no es responsable por ninguna p&eacute;rdida de datos que surja por la operaci&oacute;n del Sitio o por aplicar los T&eacute;rminos. Instamos a todos los usuarios a que mantengan su propia versi&oacute;n de respaldo de cualquier Contenido de Usuario o cualquier otro tipo de informaci&oacute;nque emitan al Sitio.
		
		<br><br><b>Derechos de Autor</b>
		<br>Nosotros determinaremos los privilegios de cualquier usuario que utilice este Sitio para transmitir ilegalmente cualquier material con derechos de autor sin previo consentimiento expreso de licencia, defensa v&aacute;lida o exenci&oacute;n de uso justo para hacerlo. En particular, los usuarios que emitan Contenido de Usuario a este Sitio, ya sean art&iacute;culos, im&aacute;genes, historias, software o cualquier otro material con derechos de autor; deben de asegurarse que el Contenido de Usuario cargado no infringe ning&uacute;n derecho de autor o cualquier otro derecho de terceros (incluyendo, m&aacute;s no limit&aacute;ndose a, marcas registradas, secretos comerciales, derechos de privacidad o publicidad). Despu&eacute;s de ser notificados debidamente por el due&ntilde;o de los derechos de autor o sus agentes, y despu&eacute;s de que se reciba una confirmaci&oacute;n a trav&eacute;s de una orden de corte o por admisi&oacute;n por parte del usuario de que han utilizado este Sitio como un instrumento de infracci&oacute;n ilegal, entonces nosotros cancelaremos los derechos del usuario que ha infringido los derechos de autor para usar y/o acceder a este Sitio. Tambi&eacute;n podremos, a nuestra propia discreci&oacute;n, decidir y cancelar los derechos del usuario para usar o acceder al Sitio antes de ese momento, si creemos que se ha llevado a cabo la infracci&oacute;n en cuesti&oacute;n.
		
		<br><br><b>Cl&aacute;usula de Exenci&oacute;n de Responsabilidades</b>
		<br><s:property value="nameCompany"/> NO SE OBLIGA  A QUE EL SITIO ESTE LIBRE DE ERRORES, QUE NO TENDRA INTERRUPCIONES, O QUE PROPORCIONE RESULTADOS ESPECIçFICOS POR EL USO DEL SITIO O POR CUALQUIER CONTENIDO, BUSQUEDA O ENCLACE EN EL SITIO Y SUS CONTENIDOS SON ENTREGADOS "COMO ESTA DISPONIBLE". <s:property value="nameCompany"/> NO PUEDE ASEGURAR QUE LOS ARCHIVOS QUE DESCARGE DEL SITIO ESTARAÅN LIBRES DE VIRUS O DE CONTAMINACIOìN O DE CARACTERISTICAS DESTRUCTIVAS. <s:property value="nameCompany"/> SE LIBERA  DE TODA RESPONSABILIDAD DE GARANTIçAS, <s:property value="nameCompany"/> NO SERAÅ RESPONSABLE POR NINGUN DA&Ntilde;O DE CUALQUIER TIPO QUE SURJA DEL USO DE ESTE SITIO, INCLUYENDO SIN LIMITACIOìN, DA&Ntilde;ëOS DIRECTOS, INDIRECTOS, INCIDENTALES, PUNITIVOS Y CONSECUENCIALES. <s:property value="nameCompany"/> NO GARANTIZA NINGUNö RESULTADO ESPECIçFICO DEL USO DE ESTE SITIO O EL USO DE LOS SERVICIOS DE <s:property value="nameCompany"/>.
		
		<br><s:property value="nameCompany"/> SE LIBERA  DE CUALQUIER Y TODA RESPONSABILIDAD POR CUALQUIER HECHO, OMISION Y CONDUCTA POR PARTE DE CUALQUIER USUARIO DE TERCEROS, USUARIO DE <s:property value="nameCompany"/>, PUBLICISTAS Y/O PATROCINADORES DEL SITIO, EN CONEXION CON EL SERVICIO DE <s:property value="nameCompany"/> O DE CUALQUIER OTRA FORMA RELACIONADA CON SU USO DEL SITIO Y/O DEL SERVICIO DE <s:property value="nameCompany"/>. <s:property value="nameCompany"/> NO ES RESPONSABLE POR LOS PRODUCTOS, SERVICIOS, ACCIONES O FALTA DE ACCIOìN DE CUALQUIER LUGAR, ARTISTA, PROMOTOR O CUALQUIER TERCERO EN CONEXIOìN CON EL SITIO O AL QUE SE HA HECHO REFERENCIA EN EL SITIO.
		
		<br>Sin prejuicio de lo anteriormente mencionado, usted puede reportar la mala conducta de usuarios y/o publicistas de terceros, proveedores de servicios y/o productos a los que se hace referencia o que est&aacute;n incluidos en el Sitio de <s:property value="nameCompany"/> a trav&eacute;s de este formulario. <s:property value="nameCompany"/> puede investigar el reclamo y tomar la acci&oacute;n adecuada, a su plena discreci&oacute;n.
		
		<br><br><b>L&iacute;mites de Responsabilidad</b>
		<br>BAJO NINGUNA CIRCUNSTANCIA <s:property value="nameCompany"/> SERA RESPONSABLE DE CUALQUIER DA&Ntilde;ëO INDIRECTO, CONSECUENCIAL, EJEMPLAR, INCIDENTAL, ESPECIAL O PUNITIVO, O POR LA PERDIDA DE GANANCIAS, INGRESOS U OPORTUNIDADES DE NEGOCIOS, AUN CUANDO SE LE HAYA AVISADO A <s:property value="nameCompany"/> DE LAS POSIBILIDADES DE TALES DA&Ntilde;ëOS.
		
		<br><br><b>Disputas</b>
		<br>Si tienes una disputa y tu disputa involucra un evento (o el boleto de un evento) que est&aacute; localizado en los Estados Unidos Mexicanos, entonces la disputa ser&aacute; regida por las leyes y la jurisdicci&oacute;n de la Ciudad de M&eacute;xico, Distrito Federal, renunciando en este acto de cualquier otro jurisdicci&oacute;n que pudiere corresponderle de conformidad a su domicilio presente o futuro
		
		<br><br><b>Indemnizaci&oacute;n</b>
		<br>En este acto manifiestas de manera expresa que es tu voluntad el liberarnos de manera expresa  de cualquier indemnizaci&oacute;n, liberando a <s:property value="nameCompany"/> y sus filiales, y a cada uno de los funcionarios, gerentes, agentes, empleados, contratistas y principales de las filiales de <s:property value="nameCompany"/> de cualquier responsabilidad de perdida, obligaci&oacute;n, reclamo o demanda, incluyendo los honorarios razonables de abogados, realizados por un tercero, debido a, o que surjan del uso de este Sitio, incluyendo tambi&eacute;n su uso del Sitio para proporcionar un enlace a otro Sitio o para cargar contenido o cualquier otra informaci&oacute;n al Sitio.
    </div>
    <div id="preloader"></div>
    <header class="fixed-top scroll-change" data-menu-anima="fade-left">
        <div class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="navbar-main navbar-middle">
                <div class="container">
                    <div class="scroll-hide">
                        <div class="container">
                            <a class="navbar-brand center" href="#">
                                <img src="../images/<s:property value="companyId"/>/system/logo.png" alt="logo" />
                            </a>
                        </div>
                    </div>
                    <div class="navbar-header">
                        <a class="navbar-brand" href="../index.jsp?companyId=<s:property value="companyId"/>"><img src="../images/<s:property value="companyId"/>/system/logo.png" alt="logo" /></a>
                        <button type="button" class="navbar-toggle">
                            <i class="fa fa-bars"></i>
                        </button>
                    </div>
                    <div class="collapse navbar-collapse">
                        <ul class="nav navbar-nav no-margins" id="menu">
                            <li><a href="../index.jsp?companyId=<s:property value="companyId"/>">Home</a></li>
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
    <div class="header-title ken-burn ken-burn-center" data-parallax="scroll" data-position="top" data-natural-height="670" data-natural-width="1300" data-image-src="../images/<s:property value="companyId"/>/wide/<s:property value="id"/>.jpg">
        <div class="container">
            <div class="title-base">
                <h1 class="text-xl"><s:property value="artistName"/></h1>
                <hr class="h" />
                <p> <s:property value="wholeDate"/></p>
            </div>
        </div>
    </div>
    <div class="section-empty">
        <div class="container content">
            <div class="row">
                <div class="col-md-4">
                    <p>DETALLE</p>
                    <h2><s:property value="artistName"/></h2>
                    <hr class="h" />
                    <ul class="list-texts">
                        <li><b>Fecha:</b> <s:property value="shortDay"/>-<s:property value="shortMonth"/>-<s:property value="shortYear"/></li>
                        <li><b>Hora:</b>  <s:property value="time"/></li>
                        <li><b>Direcci&oacute;n:</b> <s:property value="description"/></li>
                        <li><b>Tickets:</b> <a href="#">contacto@xptix.com</a></li>
                        <li><b>Tel:</b> <s:property value="phone"/></li>
                        <li><b>Categoria:</b> <s:property value="eventType"/></li>
                    </ul>
                </div>
                <div class="col-md-8">
                    <div class="row">
                        <div class="col-md-9">
                            <div class="flexslider slider visible-dir-nav nav-inner">
                                <ul class="slides">
                                    <li>
                                        <a class="img-box img-scale-rotate lightbox grayscale" href="../images/<s:property value="companyId"/>/gallery/mini/<s:property value="id"/>_1.jpg" data-lightbox-anima="show-scale">
                                            <img alt="" src="../images/<s:property value="companyId"/>/gallery/mini/<s:property value="id"/>_1.jpg" />
                                        </a>
                                    </li>
                                    <li>
                                        <a class="img-box img-scale-rotate lightbox grayscale" href="../images/<s:property value="companyId"/>/gallery/mini/<s:property value="id"/>_2.jpg" data-lightbox-anima="show-scale">
                                            <img alt="" src="../images/<s:property value="companyId"/>/gallery/mini/<s:property value="id"/>_2.jpg" />
                                        </a>
                                    </li>
                                    <li>
                                        <a class="img-box img-scale-rotate lightbox grayscale" href="../images/<s:property value="companyId"/>/gallery/mini/<s:property value="id"/>_3.jpg" data-lightbox-anima="show-scale">
                                            <img alt="" src="../images/<s:property value="companyId"/>/gallery/mini/<s:property value="id"/>_3.jpg" />
                                        </a>
                                    </li>
                                    <li>
                                        <a class="img-box img-scale-rotate lightbox grayscale" href="../images/<s:property value="companyId"/>/gallery/mini/<s:property value="id"/>_4.jpg" data-lightbox-anima="show-scale">
                                            <img alt="" src="../images/<s:property value="companyId"/>/gallery/mini/<s:property value="id"/>_4.jpg" />
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <p>FLYER OFICIAL</p>
                            <a class="img-box lightbox" href="../images/<s:property value="companyId"/>/flyer/<s:property value="id"/>_S.png" data-lightbox-anima="show-scale">
                                <img alt="" src="../images/<s:property value="companyId"/>/flyer/<s:property value="id"/>_B.png">
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
                    <iframe src="../seats.html" id="frame" name="frame" width="100%" frameborder="0" scrolling="no"></iframe>
					<iframe src="../html/payment.jsp?companyId=<s:property value="companyId"/>" id="payment" name="payment" width="100%" frameborder="0" scrolling="no"></iframe>                    
                	<iframe src="../html/minute-counter.html" id="minute-counter" name="minute-counter" width="100%" frameborder="0" scrolling="no"></iframe>
                	<iframe id="frameTicketReceipt" name="frameTicketReceipt" width="100%" frameborder="0" scrolling="no" onload="resizeIframe(this)"></iframe>
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
                <s:property value="nameCompany"/>, M&eacute;xico
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
                            <span>Copyright 2018</span>
                            <span><s:property value="nameCompany"/></span>
                            <span><s:property value="phoneCompany"/></span>	
                            <span><s:property value="emailCompany"/></span>
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
        <link rel="stylesheet" href="../scripts/font-awesome/css/font-awesome.min.css">
        <link rel="stylesheet" href="../css/animations.css">
        <script async src="../scripts/bootstrap/js/bootstrap.min.js"></script>
        <script src="../scripts/imagesloaded.min.js"></script>
        <script src='../scripts/flexslider/jquery.flexslider-min.js'></script>
        <script src='../scripts/parallax.min.js'></script>
        <script src='../scripts/jquery.progress-counter.js'></script>
        <script src='../scripts/google.maps.min.js'></script>
        <script src='https://maps.googleapis.com/maps/api/js?sensor=false'></script>
        <script src='../scripts/jquery.magnific-popup.min.js'></script>
        <script src='../scripts/jquery.spritely.min.js'></script>
        <script src="../scripts/smooth.scroll.min.js"></script>
    </footer>
</body>
</html>
