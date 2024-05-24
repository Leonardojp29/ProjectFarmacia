<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Registrar Personal</title>
    <link rel="stylesheet" type="text/css" href="css/estilosregistrarper.css">
</head>
<body>
    <div class="container-registrar">
        <h1>Registrar Personal</h1>

        <%-- Verificar si existe un mensaje de error --%>
        <% String mensajeError = (String) request.getAttribute("msj"); %>
        <% if (mensajeError != null) { %>
            <p class="animate__animated animate__headShake" style="color: red; font-weight: bold;"><%= mensajeError %></p>
        <% } %>

        <form action="ServletPersonal?tipo=registrar" id="frmagregar" method="post" onsubmit="return validarFormulario();">

            <table class="form-table">
                <tr>
                    <td>Nombre:</td>
                    <td><input type="text" name="txt_nom_per" class="required"></td>
                    <td rowspan="6" style="text-align: center;">
                        <div class="imagen-contenedor">
                            <img src="img/personal2.jpg" alt="Imagen">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Apellido:</td>
                    <td><input type="text" name="txt_ape_per" class="required"></td>
                </tr>
                <tr>
                    <td>DNI:</td>
                    <td><input type="text" name="txt_dni" class="required"></td>
                </tr>
                <tr>
                    <td>Login:</td>
                    <td><input type="text" name="txt_login" class="required"></td>
                </tr>
                <tr>
                    <td>Clave:</td>
                    <td><input type="password" name="txt_clave" class="required"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center" style="padding-top: 20px;"><input type="submit" value="Registrar"></td>
                </tr>
            </table>
            <div class="regresar-inicio">
                <a href="inicioSesion.jsp">Regresar</a>
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
