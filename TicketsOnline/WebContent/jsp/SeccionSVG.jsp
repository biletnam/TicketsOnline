<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
	<script language="JavaScript">
		function onLoadJS(){
			var a = document.getElementById("svgObject");
			var doc = a.contentDocument;
			var asientos = doc.getElementsByTagName("rect");
			var asiento = null;
			var id = null;
			
			for (var i = 0; i < asientos.length; i++){
				asiento = asientos[i];
				id = asiento.parentNode.id;
				asiento.addEventListener("click", seatSelect);
			}
			
		}
		
		function seatSelect(event){
			var seat = event.target;
			
			window.location.href = '/TicketsOnline/jsp/TestSVG.jsp?fileName=Escenario01.svg'
		}
			
	</script>
</head>
<body onload="onLoadJS()">
	<object id="svgObject" data="/TicketsOnline/img/layouts/<%=request.getParameter("fileName")%>" type="image/svg+xml" height="100%" width="100%">
		Your browser doesn't support SVG
	</object>
</body>