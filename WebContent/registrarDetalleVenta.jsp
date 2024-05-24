<%@ page import="beans.ProductoDTO" %>
<%@ page import="beans.VentaDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Registrar Detalle de Venta</title>
    <link rel="stylesheet" type="text/css" href="css/estilosregistrarventa.css">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>    
</head>
	<body>
			<% 
		        VentaDTO obj = (VentaDTO) request.getAttribute("Venta");
		    %>
	    	<div class="container-registrar">
		        <h1>Registrar Detalle de Venta</h1>
		        
		        <% String mensajeError = (String) request.getAttribute("msj"); %>
		        <% if (mensajeError != null) { %>
		            <p class="animate__animated animate__headShake" style="color: red; font-weight: bold;"><%= mensajeError %></p>
		        <% } %>
		
		        <form action="ServletVenta?tipo=registrarDetalleVenta" method="post">
		        	<input type="hidden" name="txt_codVen" value="${requestScope.Venta.codVenta}">
	
	
		            <table class="form-table">   
		                <tr>
		                    <td>Producto:</td>
		                    <td>
		                        <div class="select-container">
		                            <select name="txt_codPro" id="txt_codPro" class="required" onchange="actualizarPrecio()">
									    <% ArrayList<ProductoDTO> listaDatosPro = (ArrayList<ProductoDTO>) request.getAttribute("listaDatosPro");
									    if (listaDatosPro != null && !listaDatosPro.isEmpty()) {
									        for (ProductoDTO producto : listaDatosPro) {
									    %>
									    <option value="<%= producto.getCod_pro()%>" data-precio="<%= producto.getPre_producto()%>"><%= producto.getNom_pro()%></option>
									    <% }
									    } else { %>
									    <option value="" disabled>No hay productos disponibles</option>
									    <% } %>
									</select>	
		                        </div>
		                    </td>
		                </tr>
		                <tr>
		                    <td>Cantidad:</td>
		                    <td><input type="text" name="txt_cant" class="required"></td>
		                </tr>
		                <tr>
		                    <td>Precio Unitario del Producto</td>
							<td><input type="text" name="txt_pre" id="txt_pre" class="required" readonly></td>	
		                </tr>
		                <tr>
		                    <td colspan="2" align="right"><input type="submit" value="Registrar Detalle"></td>
		                </tr>
		            </table>
	            <div class="regresar-inicio">
	                <a href="ServletVenta?tipo=listar">Regresar</a>
	            </div>
	        </form>
	    </div>
	    <script>
	    function actualizarPrecio() {
	        var select = document.getElementById("txt_codPro");
	        var precioInput = document.getElementById("txt_pre");
	
	        // Obtener el precio del producto seleccionado
	        var selectedOption = select.options[select.selectedIndex];
	        var precio = selectedOption.getAttribute("data-precio");
	
	        // Mostrar el precio en el campo correspondiente
	        precioInput.value = precio;
	    }
		</script>
	</body>
<head>
  <link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"
  />
</head>
</html>