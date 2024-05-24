<jsp:include page="validar.jsp"></jsp:include>
<%@page import="beans.VentaDTO"%>
<%@page import="beans.ClienteDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Actualizar Venta</title>
    <link rel="stylesheet" type="text/css" href="css/estilosactualizarProducto.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
</head>
	<body>    
	    <% 
	        VentaDTO obj = (VentaDTO) request.getAttribute("Venta");
	    %>
	
	    <div class="container-actualizar">
	        <h1>Actualizar Venta</h1>
	
	        <form action="ServletVenta?tipo=actualizarVen" id="frmactualizar" method="post">
			    <input type="hidden" name="txt_cod" value="<%= obj.getCodVenta() %>">
			    <table class="form-table">
			        <tr>                    
			            <td>Cliente:</td>
			            <td>
			                <div class="select-container">
			                    <select name="txt_codCli" class="required">
			                        <% ArrayList<ClienteDTO> listaDatosCli = (ArrayList<ClienteDTO>) request.getAttribute("listaDatosCli");
			                            if (listaDatosCli != null && !listaDatosCli.isEmpty()) {
			                                for (ClienteDTO cliente : listaDatosCli) {
			                        %>
			                        <option value="<%= cliente.getCod_cli()%>" <%= (cliente.getCod_cli() == obj.getCod_cli()) ? "selected" : "" %>><%= cliente.getNom_cli()%></option>
			                        <% }
			                            } else { %>
			                        <option value="" disabled>No hay clientes disponibles</option>
			                        <% } %>
			                    </select>
			                </div>
			            </td>
			        </tr>
			        <tr>
			            <td>Fecha de la Venta</td>
			            <td><input type="text" name="txt_fech" id="fecha" class="required" value="<%= obj.getFechaVenta() %>"></td>
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
	            <a href="ServletVenta?tipo=listar">Regresar</a>
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
