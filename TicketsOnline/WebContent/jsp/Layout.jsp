<%@ page language="java" contentType="text/html; charset=ISO-8859-15" pageEncoding="ISO-8859-15"%>
<%
	response.setHeader("Cache-Control", "no-Cache");
	response.setHeader("Pragma", "No-cache");
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <!--<script src="../js/Zoom.js"></script>-->
  </head>
  <body>
    <div id="container" style="width: 100%; frameborder="0">
    	<s:property value="svg" escape="false"/>	
    </div>
    <script>
     // window.onload = function() {
     //   window.zoomTiger = svgPanZoom('#svg', {
     //     zoomEnabled: true,
     //     controlIconsEnabled: true,
     //     fit: true,
     //     center: true,
     //  });
     // };
    </script>
  </body>
</html>