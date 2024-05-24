<jsp:include page="validar.jsp"></jsp:include>
<%@ page import="beans.ClienteDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Registrar Venta</title>
    <link rel="stylesheet" type="text/css" href="css/estilosregistrarventa.css">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
</head>
<body>
    <div class="container-registrar">
        <h1>Registrar Venta</h1>
        <% String mensajeError = (String) request.getAttribute("msj"); %>
        <% if (mensajeError != null) { %>
            <p class="animate__animated animate__headShake" style="color: red; font-weight: bold;"><%= mensajeError %></p>
        <% } %>

        <form action="ServletVenta?tipo=registrarVenta" id="frmagregar" method="post">

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
					      <option value="<%= cliente.getCod_cli()%>"><%= cliente.getNom_cli()%></option>
					      <% }
					        } else { %>
					      <option value="" disabled>No hay clientes disponibles</option>
					      <% } %>
					    </select>
					  </div>
					</td>
                </tr>
                <tr>
                    <td>Fecha de Venta:</td>
                    <td><input type="text" name="txt_fech" id="fecha" class="required"></td>
                </tr>                
                <tr>
                    <td colspan="2" align="right"><input type="submit" value="Registrar"></td>
                </tr>
            </table>
            <div class="regresar-inicio">
                <a href="ServletVenta?tipo=listar">Regresar</a>
            </div>
        </form>
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