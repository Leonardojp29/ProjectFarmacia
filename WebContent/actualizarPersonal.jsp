<jsp:include page="validar.jsp"></jsp:include>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Actualizar Personal</title>
    <link rel="stylesheet" type="text/css" href="css/estilosactualizarPersonal.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
</head>
<body>    
    <% 
        beans.PersonalDTO obj = (beans.PersonalDTO) request.getAttribute("Personal");
    %>

    <div class="container-actualizar">
        <h1>Actualizar Personal</h1>

        <form action="ServletPersonal?tipo=actualizar" id="frmactualizar" method="post">
            <input type="hidden" name="txt_cod" value="${requestScope.Personal.cod_per}">

            <table class="form-table">
                <tr>
                    <td>Nombre:</td>
                    <td><input type="text" name="txt_nom_per" class="required" value="${requestScope.Personal.nom_per}"></td>
                </tr>
                <tr>
                    <td>Apellido:</td>
                    <td><input type="text" name="txt_ape_per" class="required" value="${requestScope.Personal.ape_per}"></td>
                </tr>
                <tr>
                    <td>DNI:</td>
                    <td><input type="text" name="txt_dni" class="required" value="${requestScope.Personal.dni}"></td>
                </tr>
                <tr>
                    <td>Login:</td>
                    <td><input type="text" name="txt_login" class="required" value="${requestScope.Personal.login}"></td>
                </tr>
                <tr>
                    <td>Clave actual:</td>
                    <td><input type="password" name="txt_clave_actual" class="required"></td>
                </tr>
                <tr>
                    <td>Nueva Clave:</td>
                    <td><input type="password" name="txt_clave" class="required"></td>
                </tr>
                <tr>
                    <td colspan="2" align="right"><input type="submit" value="Actualizar"></td>
                </tr>            
            </table>
        </form>

        <div class="animate__animated animate__heartBeat" style="color: black; font-weight: bold;">${requestScope.msg}</div>
        <br>
        <div class="regresar-inicio">
            <a href="ServletPersonal?tipo=listar">Regresar</a>
        </div>
    </div>
</body>
<head>
  <link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"
  />
</head>
</html>