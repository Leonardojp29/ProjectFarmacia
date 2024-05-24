<jsp:include page="validar.jsp"></jsp:include>
<%@page import="beans.ClienteDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Actualizar Cliente</title>
    <link rel="stylesheet" type="text/css" href="css/estilosactualizarCliente.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
</head>
	<body>
	    <% 
	        ClienteDTO obj = (ClienteDTO) request.getAttribute("Cliente");
	    %>
	
	    <div class="container-actualizar">
	        <h1>Actualizar Cliente</h1>
	
	        <form action="ServletCliente?tipo=actualizar" id="frmactualizar" method="post">
	            <input type="hidden" name="txt_codCli" value="${requestScope.Cliente.cod_cli}">
	
	            <table class="form-table">
	                <tr>
	                    <td>Nombre:</td>
	                    <td><input type="text" name="txt_nom_cli" class="required" value="${requestScope.Cliente.nom_cli}"></td>
	                </tr>
	                <tr>
	                    <td>Dirección:</td>
	                    <td><input type="text" name="txt_direccion" class="required" value="${requestScope.Cliente.direccion}"></td>
	                </tr>
	                <tr>
	                    <td>Teléfono:</td>
	                    <td><input type="text" name="txt_telefono" class="required" value="${requestScope.Cliente.telefono}"></td>
	                </tr>
	                <tr>
	                    <td>Correo:</td>
	                    <td><input type="text" name="txt_correo" class="required" value="${requestScope.Cliente.correo}"></td>
	                </tr>
	                <tr>
	                    <td>DNI:</td>
	                    <td><input type="text" name="txt_dni" class="required" value="${requestScope.Cliente.dni}"></td>
	                </tr>
	                <tr>
	                    <td colspan="2" align="right"><input type="submit" value="Actualizar"></td>
	                </tr>            
	            </table>
	        </form>
	        <%-- Verificar si existe un mensaje de error --%>
	        <% String mensajeError = (String) request.getAttribute("msj"); %>
	        <% if (mensajeError != null) { %>
	            <p class="animate__animated animate__headShake" style="color: black; font-weight: bold;"><%= mensajeError %></p>
	        <% } %>
	        <br>       
	        <div class="regresar-inicio">
	            <a href="ServletCliente?tipo=listar">Regresar</a>
	        </div>
	    </div>
	</body>
<head>
  <link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"
  />
</head>
</html>
