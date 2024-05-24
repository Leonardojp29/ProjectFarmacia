<jsp:include page="validar.jsp"></jsp:include>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Registrar Producto</title>
    <link rel="stylesheet" type="text/css" href="css/estilosregistrarpro.css">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
</head>
<body>
    <div class="container-registrar">
        <h1>Agregar Producto</h1>

        <%-- Verificar si existe un mensaje de error --%>
        <% String mensajeError = (String) request.getAttribute("msj"); %>
        <% if (mensajeError != null) { %>
            <p class="animate__animated animate__headShake" style="color: red; font-weight: bold;"><%= mensajeError %></p>
        <% } %>

        <form action="ServletProducto?tipo=registrar" id="frmagregar" method="post">

            <table class="form-table">
                <tr>
                    <td>Nombre:</td>
                    <td><input type="text" name="txt_nom_pro" class="required"></td>
                    <td rowspan="6" style="text-align: center;">
                        <div class="imagen-contenedor">
                            <img src="img/productos2.jpg" alt="Imagen">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Stock:</td>
                    <td><input type="text" name="txt_stock" class="required"></td>
                </tr>
                <tr>
                    <td>Precio:</td>
                    <td><input type="text" name="txt_pre_pro" class="required"></td>
                </tr>
                <tr>
                    <td>Fecha de Vencimiento:</td>
                    <td><input type="text" name="txt_fech" id="fecha" class="required"></td>
                </tr>
                <tr>
                    <td colspan="2" align="right"><input type="submit" value="Registrar"></td>
                </tr>
            </table>
            <div class="regresar-inicio">
                <a href="ServletProducto?tipo=listar">Regresar</a>
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
