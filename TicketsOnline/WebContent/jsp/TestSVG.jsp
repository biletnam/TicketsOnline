<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
	<script language="JavaScript">
		function onLoadJS(){
			var a = document.getElementById("svgObject");
			var doc = a.contentDocument;
			var asientos = doc.getElementsByTagName("ellipse");
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
			var cls = seat.getAttribute("class");
			
			if (cls == "st1"){
				seat.setAttribute("class", "st2");
				seat.setAttribute("busy", true);
				
			}else{
				seat.setAttribute("class", "st1");
				seat.setAttribute("busy", false);
			}
		}
			
	</script>
</head>
<body onload="onLoadJS()">
	<object id="svgObject" data="/TicketsOnline/img/layouts/<%=request.getParameter("fileName")%>" type="image/svg+xml" height="100%" width="100%">
		Your browser doesn't support SVG
	</object>
</body>