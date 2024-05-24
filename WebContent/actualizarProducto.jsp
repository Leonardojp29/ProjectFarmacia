<jsp:include page="validar.jsp"></jsp:include>
<%@page import="beans.ProductoDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Actualizar Producto</title>
    <link rel="stylesheet" type="text/css" href="css/estilosactualizarProducto.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
</head>
	<body>    
	    <% 
	        ProductoDTO obj = (ProductoDTO) request.getAttribute("Producto");
	    %>
	
	    <div class="container-actualizar">
	        <h1>Actualizar Producto</h1>
	
	        <form action="ServletProducto?tipo=actualizar" id="frmactualizar" method="post">
	            <input type="hidden" name="txt_cod" value="${requestScope.Producto.cod_pro}">
	
	            <table class="form-table">
	                <tr>
	                    <td>Nombre:</td>
	                    <td><input type="text" name="txt_nom_pro" class="required" value="${requestScope.Producto.nom_pro}"></td>
	                </tr>
	                <tr>
	                    <td>Stock:</td>
	                    <td><input type="text" name="txt_stock" class="required" value="${requestScope.Producto.stock}"></td>
	                </tr>
	                <tr>
	                    <td>Precio:</td>
	                    <td><input type="text" name="txt_pre_pro" class="required" value="${requestScope.Producto.pre_producto}"></td>
	                </tr>
	                <tr>
                    <td>Fecha de Vencimiento:</td>
                    <td><input type="text" name="txt_fech" id="fecha" class="required"></td>
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
	            <a href="ServletProducto?tipo=listar">Regresar</a>
	        </div>
	    </div>
	    
	    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    	<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
    	<script>
	        $(function() {
	            $("#fecha").datepicker({
	                dateFormat: "yy-mm-dd"
	            });
	        });
    	</script>
	</body>
<head>
  <link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"
  />
</head>
</html>
