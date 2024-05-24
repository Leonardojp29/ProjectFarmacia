<jsp:include page="validar.jsp"></jsp:include>
<%@page import="beans.ClienteDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de Clientes</title>
    <link rel="stylesheet" type="text/css" href="css/estiloslistarCliente.css">
    <script>
        function confirmarEliminacion(codCli) {
            if (confirm("¿Estás seguro de que deseas eliminar este cliente?")) {
                location.href = "ServletCliente?tipo=eliminar&codCli=" + codCli;
            }
        }
    </script>
</head>
<body>
    <h1>Listado de Clientes</h1>
    <div align="center">
        <a href="registrarCliente.jsp">Agregar un cliente</a>
        <a href="menu.jsp">Regresar</a>
    </div>
    <form action="ServletCliente" id="frmbuscarClientePorNombre" method="post" class="row g-4">
        <div class="col-auto">
            <input type="text" name="nombre" class="form-control" placeholder="Nombre del Cliente">
        </div>
        <div class="col-auto">
            <input type="hidden" name="tipo" value="buscarClientePorNombre">
            <input type="submit" value="Consultar" class="btn btn-secondary">
        </div>
    </form>
    <br>
    <table border="2" align="center" width="75%">
        <tr>
            <th>CÓDIGO</th>
            <th>NOMBRE</th>
            <th>DIRECCIÓN</th>
            <th>TELÉFONO</th>
            <th>CORREO</th>
            <th>DNI</th>
            <th colspan="2">ACCIONES</th>
        </tr>
        <% ArrayList<ClienteDTO> lista = (ArrayList<ClienteDTO>) request.getAttribute("listaClientes");
        int inicio = 0;
        int fin = 10;
        if (request.getParameter("inicio") != null && request.getParameter("fin") != null) {
            inicio = Integer.parseInt(request.getParameter("inicio"));
            fin = Integer.parseInt(request.getParameter("fin"));
        }
        if (lista != null && !lista.isEmpty()) {
            for (int i = inicio; i < fin && i < lista.size(); i++) {
                ClienteDTO cliente = lista.get(i);
                %>
                <tr>
                    <td><%= cliente.getCod_cli() %></td>
                    <td><%= cliente.getNom_cli() %></td>
                    <td><%= cliente.getDireccion() %></td>
                    <td><%= cliente.getTelefono() %></td>
                    <td><%= cliente.getCorreo() %></td>
                    <td><%= cliente.getDni() %></td>
                    <td align='center'><a href='ServletCliente?tipo=buscar&codCli=<%= cliente.getCod_cli() %>'><img src='img/edit.png' title='Editar'></a></td>
                    <td align='center'><a href='javascript:confirmarEliminacion(<%= cliente.getCod_cli() %>)'><img src='img/delete.png' title='Eliminar'></a></td>
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
            int anterior = inicio - 10;
            int siguiente = inicio + 10;
            if (anterior < 0) {
                anterior = 0;
            }
            %>
            <a href="ServletCliente?tipo=listar&inicio=<%= anterior %>&fin=<%= inicio %>" class="btn btn-secondary">Anterior</a>
            <%
        }
        if (lista != null && fin < lista.size()) {
            %>
            <a href="ServletCliente?tipo=listar&inicio=<%= fin %>&fin=<%= fin + 10 %>" class="btn btn-secondary">Siguiente</a>
            <%
        }
        %>
    </div>
</body>
</html>
