<%@page import="java.util.List"%>
<%@page import="beans.DetalleVentaDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Detalle de Venta</title>
    <link rel="stylesheet" type="text/css" href="css/estilosdetalleVenta.css">
    <script>
    function confirmarEliminacion(codDetalleVenta, codVenta) {
        if (confirm("¿Estás seguro de que deseas eliminar esta venta?")) {
            var url = "ServletVenta?tipo=eliminarDet&codDet=" + codDetalleVenta + "&codVenta=" + codVenta;
            window.location.replace(url);
        }
    }
</script>

</head>
<body>
    <h1>Detalle de Venta</h1>
    <% 
        List<DetalleVentaDTO> detalleVenta = (List<DetalleVentaDTO>) request.getAttribute("detalleVenta");
    %>
    <% if (detalleVenta != null && !detalleVenta.isEmpty()) { %>
        <table border="1">
                <tr>
                    <th>Nombre de los Productos</th>
                    <th>Cantidades</th>
                    <th>Precios de los Productos</th>
                    <th colspan="1">ACCIONES</th>
                </tr>
                <% 
                    double totalVenta = 0; 
                    for (DetalleVentaDTO detalle : detalleVenta) { 
                        totalVenta += detalle.getPre_producto() * detalle.getCantidad(); 
                %>
               <tr>
                        <td><%= detalle.getNom_pro() %></td>
                        <td><%= detalle.getCantidad() %></td>
                        <td>S/. <%= detalle.getPre_producto() %></td>
                        <td align='center'><a href='javascript:confirmarEliminacion(<%= detalle.getCodDetalleVenta() %>, <%= detalle.getCodVenta() %>)'><img src='img/delete.png' title='Eliminar'></a></td>
                                                
              </tr>
               <% } %>
                <tr>
                    <td colspan="2" align="right"><strong>Total de la Venta:</strong></td>
                    <td><strong>S/. <%= totalVenta %></strong></td>
                    <td><img src="img/total.png" alt="Imagen"></td>                    
                </tr>                
        </table>
    <br>
		<div align="center">
	        <a href="ServletVenta?tipo=listar">Regresar</a>
	    </div>
    <% } else { %>
        <div align="center">
        <h3>No hay detalle de Venta</h3>
	        <a href="ServletVenta?tipo=listar">Regresar</a>	        
	    </div>
    <% } %>
</body>
</html>
