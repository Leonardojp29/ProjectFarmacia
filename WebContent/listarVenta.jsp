<jsp:include page="validar.jsp"></jsp:include>
<%@page import="java.util.ArrayList"%>
<%@page import="beans.VentaDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de Ventas</title>
    <link rel="stylesheet" type="text/css" href="css/estiloslistarVenta.css">
    <script>
        function confirmarEliminacion(codVenta) {
            if (confirm("¿Estás seguro de que deseas eliminar esta venta?")) {
                location.href = "ServletVenta?tipo=eliminar&cod=" + codVenta;
            }
        }
    </script>
</head>
	<body>
	    <h1>Listado de Ventas</h1>
	    <div align="center">
	        <a href="ServletVenta?tipo=listarDatosCli">Agregar una venta</a>
	        <a href="menu.jsp">Regresar</a>
	    </div>
	    
    <br>
	    <table border="2" align="center" width="75%">
	        <tr>
	            <th>CÓDIGO</th>
	            <th>CLIENTE</th>
	            <th>FECHA DE VENTA</th>
	            <th>TOTAL VENTA</th>
	            <th colspan="2">ACCIONES</th>
	            <th colspan="2">DETALLE DE LA VENTA</th>
	        </tr>
	        <%
	            ArrayList<VentaDTO> lista = (ArrayList<VentaDTO>) request.getAttribute("data");
	            if (lista != null && !lista.isEmpty()) {
	                for (VentaDTO venta : lista) {
	        %>
	        <tr>
	            <td><%= venta.getCodVenta() %></td>
	            <td><%= venta.getNom_cli() %></td>
	            <td><%= venta.getFechaVenta() %></td>
	            <td>S/. <%=venta.getTotalVenta()%></td>
	            <td align='center'><a href='ServletVenta?tipo=buscarVen&cod=<%= venta.getCodVenta() %>'><img src='img/edit.png' title='Editar'></a></td>
	            <td align='center'><a href='javascript:confirmarEliminacion(<%= venta.getCodVenta() %>)'><img src='img/delete.png' title='Eliminar'></a></td>
	            <td align='center'><a href='ServletVenta?tipo=buscar&cod=<%= venta.getCodVenta() %>'>Agregar Detalle</a></td>	            
	            <td align='center'><a href='ServletVenta?tipo=detalle&codVenta=<%= venta.getCodVenta() %>'><img src='img/detalle.png' title='Ver Detalle'>                  
	            Ver</a></td>
	        </tr>
	        <%
	                }
	            }
	        %>
	    </table>
	</body>
</html>
