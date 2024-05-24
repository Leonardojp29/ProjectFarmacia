<jsp:include page="validar.jsp"></jsp:include>
<%@page import="beans.ProductoDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de Productos</title>
    <link rel="stylesheet" type="text/css" href="css/estiloslistarProducto.css">
    <script>
        function confirmarEliminacion(codProducto) {
            if (confirm("¿Estás seguro de que deseas eliminar este producto?")) {
                location.href = "ServletProducto?tipo=eliminar&cod=" + codProducto;
            }
        }
    </script>
</head>
	<body>
	    <h1>Listado de Productos</h1>
	    <div align="center">
	        <a href="registrarProducto.jsp">Agregar un producto</a>
	        <a href="menu.jsp">Regresar</a>
	    </div>
	    <form action="ServletProducto" id="frmbuscarProductoPorNombre" method="post" class="row g-4">
	        <div class="col-auto">
	            <input type="text" name="nombre" class="form-control" placeholder="Nombre de Producto">
	        </div>
	        <div class="col-auto">
	            <input type="hidden" name="tipo" value="buscarProductoPorNombre">
	            <input type="submit" value="Consultar" class="btn btn-secondary">
	        </div>
	    </form>
	    <br>
	    <table border="2" align="center" width="75%">
	        <tr>
	            <th>CÓDIGO</th>
	            <th>NOMBRE DE PRODUCTO</th>
	            <th>STOCK</th>
	            <th>PRECIO</th>
	            <th>FECHA DE VENCIMIENTO</th>
	            <th colspan="2">ACCIONES</th>
	        </tr>
	        <% ArrayList<ProductoDTO> lista = (ArrayList<ProductoDTO>) request.getAttribute("data");
	        int inicio = 0;
	        int fin = 10;
	        if (request.getParameter("inicio") != null && request.getParameter("fin") != null) {
	            inicio = Integer.parseInt(request.getParameter("inicio"));
	            fin = Integer.parseInt(request.getParameter("fin"));
	        }
	        if (lista != null && !lista.isEmpty()) {
	            for (int i = inicio; i < fin && i < lista.size(); i++) {
	                ProductoDTO xPro = lista.get(i);
	                %>
	                <tr>
	                    <td><%= xPro.getCod_pro() %></td>
	                    <td><%= xPro.getNom_pro() %></td>
	                    <td><%= xPro.getStock() %></td>
	                    <td>S/. <%= xPro.getPre_producto() %></td>
	                    <td><%= xPro.getFech_ven() %></td>
	                    <td align='center'><a href='ServletProducto?tipo=buscar&cod=<%= xPro.getCod_pro() %>'><img src='img/edit.png' title='Editar'></a></td>
	                    <td align='center'><a href='javascript:confirmarEliminacion(<%= xPro.getCod_pro() %>)'><img src='img/delete.png' title='Eliminar'></a></td>
	                </tr>
	                <%
	            }
	        }
	        %>
	    </table>
	    <br>
	    <div align="center">
	        <%
	        if (inicio > 0) {
	            int anteriorInicio = Math.max(0, inicio - 10);
	            int anteriorFin = inicio;
	            %>
	            <a href='ServletProducto?tipo=listar&inicio=<%= anteriorInicio %>&fin=<%= anteriorFin %>'>Anterior</a>
	            <%
	        }
	        if (fin < lista.size()) {
	            int siguienteInicio = fin;
	            int siguienteFin = Math.min(lista.size(), fin + 10);
	            %>
	            <a href='ServletProducto?tipo=listar&inicio=<%= siguienteInicio %>&fin=<%= siguienteFin %>'>Siguiente</a>
	            <%
	        }
	        %>
	    </div>
	    <br>
	    <br>
	</body>
</html>
