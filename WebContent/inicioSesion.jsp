<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Iniciar Sesión - Farmacia</title>
    <link rel="stylesheet" type="text/css" href="css/estilosiniciosesion.css">
</head>
<body>
    <div class="container-login">
        <h1>Iniciar Sesión</h1>

        <form action="ServletPersonal?tipo=iniciarSesion" name="frmsesion" method="post">

            <table class="form-table">
                <tr>
                    <td><input type="text" name="txt_login" placeholder="Usuario" required></td>
                </tr>
                <tr>
                    <td><input type="password" name="txt_clave" placeholder="Contraseña" required></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Iniciar sesión">
                    </td>         
            </table>         
                   
          
        </form>

        <div class="register-link">
            <a href="registrarPersonal.jsp">Registrarse</a>
        </div>
        <br>
        <div class="animate__animated animate__headShake" style="color: black; font-weight: bold;">${requestScope.msg}</div>

    </div>
</body>
<head>
  <link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"
  />
</head>
</html>

