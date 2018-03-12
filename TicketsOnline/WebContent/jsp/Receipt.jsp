<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Comprobante de pago</title>
</head>
<body style="word-wrap: break-word; -webkit-nbsp-mode: space; line-break: after-white-space;" class="">
<div style="width:320px;padding:5px;font-size:12px;font-family:tahoma,arial,sans-serif;" class="">
<div style="background:#fff url(http://www.autoboleto.com/media/bg_boleto.png) no-repeat center bottom" class="">
<table cellpadding="2" cellspacing="0" border="1" style="border-color:#50ADE5;font-family:arial,sans-serif;; font-size:12px;border-collapse:collapse;" class="">
<tbody class="">
<tr class="">
<td style="padding:15px 0 15px 0; background:#000;" class="">
<center class=""><img src="../images/logo-small.png" alt="" id="1511472342776" class=""></center>
</td>
</tr>
<tr class="">
<td style="padding:5px;border:1px solid #50ADE5;color:#444;" class="">Datos del comprador:
</td>
</tr>
<tr class="">
<td class="">
<center class=""><b style="" class=""><s:property value="customerName"/><br class="">
<a href="mailto:<s:property value="email"/>" class=""><s:property value="email"/></a> <br class="">
<s:property value="phone"/></b></center>
</td>
</tr>
<tr class="">
<td style="padding:5px;border:1px solid #50ADE5;color:#444;" class="">Haz realizado una compra para:
</td>
</tr>
<tr class="">
<td style="text-align:center;padding:30px 0px 30px 0px" class="">
<center class=""><b style="" class=""><s:property value="eventDescription"/><br class="">
<s:property value="dateTime"/><br class="">
<s:property value="place"/>, <s:property value="city"/>, <s:property value="state"/></b></center>
</td>
</tr>
<tr class="">
<td style="padding:5px;border:1px solid #50ADE5;color:#444;" class="">Puedes recoger tus boletos en cualquiera de nuestros puntos de venta presentando los siguientes documentos: Este correo impreso, una copia de tu identificaci�n que hayas ingresado en el sistema
 de pago, la tarjeta con que hiciste la compra y el titular de la tarjeta ya que tiene que firmar el Boucher de compra al momento de entregar los boletos. Sin alguno de estos requisitos no ser�n entregados los boletos.</td>
</tr>
<s:iterator status="stat" value="seats">
	<tr class="">
	<td style="padding: 5px 0px 5px 0px; border:1px solid #50ADE5; " class="">
	<center class=""><img src="http://www.autoboleto.com/barcodes/img/387001" alt="387001" width="200" height="50" id="1511472342778" class="">
	<div style="padding:5px" class=""><b style="color:red" class=""><s:property value="idPayment"/></b></div>
	<b style="" class=""><s:property value="code"/> <s:property value="location"/> $<s:property value="cost"/></b> </center>
	</td>
	</tr>
</s:iterator>
</tbody>
</table>
</div>
<p style="color:#999" class=""><br class="">
MD5: <b class="">1RG91400MM146060L</b> </p>
</div>
</div>
</div>
</blockquote>
</div>
<br class="">


</body>
</html>