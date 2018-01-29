<%@ page language="java" contentType="application/json; charset=ISO-8859-15" pageEncoding="ISO-8859-15"%>
<%
	response.setHeader("Cache-Control", "no-Cache");
	response.setHeader("Pragma", "No-cache");
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:property value="jsonData" escape="false"/>