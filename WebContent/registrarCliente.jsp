<jsp:include page="validar.jsp"></jsp:include>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Registrar Cliente</title>
    <link rel="stylesheet" type="text/css" href="css/estilosregistrarcli.css">
</head>
<body>
    <div class="container-registrar">
        <h1>Agregar Cliente</h1>

        <%-- Verificar si existe un mensaje de error --%>
        <% String mensajeError = (String) request.getAttribute("msj"); %>
        <% if (mensajeError != null) { %>
            <p class="animate__animated animate__headShake" style="color: red; font-weight: bold;"><%= mensajeError %></p>
        <% } %>

        <form action="ServletCliente?tipo=registrar" id="frmagregar" method="post">

            <table class="form-table">
                <tr>
                    <td>Nombre:</td>
                    <td><input type="text" name="txt_nom_cli" class="required"></td>
                    <td rowspan="6" style="text-align: center;">
                        <div class="imagen-contenedor">
                            <img src="img/cliente2.jpg" alt="Imagen">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Dirección:</td>
                    <td><input type="text" name="txt_direccion" class="required"></td>
                </tr>
                <tr>
                    <td>Teléfono:</td>
                    <td><input type="text" name="txt_telefono" class="required"></td>
                </tr>
                <tr>
                    <td>Correo:</td>
                    <td><input type="text" name="txt_correo" class="required"></td>
                </tr>
                <tr>
                    <td>DNI:</td>
                    <td><input type="text" name="txt_dni" class="required"></td>
                </tr>
                <tr>
                    <td colspan="2" align="right"><input type="submit" value="Registrar"></td>
                </tr>
            </table>
            <div class="regresar-inicio">
                <a href="ServletCliente?tipo=listar">Regresar</a>
            </div>
        </form>
    </div>
</body>
<head>
  <link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"
  />
</head>
</html>
