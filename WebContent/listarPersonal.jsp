<jsp:include page="validar.jsp"></jsp:include>
<%@page import="beans.PersonalDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de Personal</title>
    <link rel="stylesheet" type="text/css" href="css/estiloslistarPersonal.css">
    <script>
        function confirmarEliminacion(codigo) {
            var clavePersonal = prompt("Ingrese la clave del personal:");

            if (clavePersonal) {
                var confirmacion = confirm("¿Estás seguro de eliminar este registro del Personal?");

                if (confirmacion) {
                    var form = document.createElement("form");
                    form.method = "post";
                    form.action = "ServletPersonal?tipo=eliminar&cod=" + codigo;

                    var inputClavePersonal = document.createElement("input");
                    inputClavePersonal.type = "hidden";
                    inputClavePersonal.name = "txt_clave_personal";
                    inputClavePersonal.value = clavePersonal;

                    form.appendChild(inputClavePersonal);
                    document.body.appendChild(form);
                    form.submit();
                }
            }
        }
    </script>
</head>
<body>
    <h1>Listado de Personal</h1>
    <div class="animate__animated animate__headShake" style="color: red; font-weight: bold;">
        <% String errorMsg = (String) request.getAttribute("errorMsg");
           if (errorMsg != null && !errorMsg.isEmpty()) {
               out.println(errorMsg);
           }
        %>
    </div>
    <br>
    
    <table border="2" align="center" width="75%">
        <tr>
            <th>CÓDIGO</th>
            <th>NOMBRE</th>
            <th>APELLIDO</th>
            <th>DNI</th>
            <th>LOGIN</th>
            <th colspan="2">ACCIONES</th>
        </tr>

        <% ArrayList<PersonalDTO> lista = (ArrayList<PersonalDTO>) request.getAttribute("data");

		   if (lista != null) {
		       for (PersonalDTO personal : lista) {
		           out.println("<tr>");
		           out.println("<td>" + personal.getCod_per() + "</td>");
		           out.println("<td>" + personal.getNom_per() + "</td>");
		           out.println("<td>" + personal.getApe_per() + "</td>");
		           out.println("<td>" + personal.getDni() + "</td>");
		           out.println("<td>" + personal.getLogin() + "</td>");                    
		           out.println("<td align='center'><a href='ServletPersonal?tipo=buscar&cod=" + personal.getCod_per() + "'><img src='img/edit.png' title='Editar'></a></td>");
		           out.println("<td align='center'><a onclick=\"confirmarEliminacion(" + personal.getCod_per() + ")\" class='delete-link'><img src='img/delete.png' title='Eliminar'></a></td>");
		           out.println("</tr>");
		       }
		   }
		%>

    </table>
    <br>
    <div align="center">
        <a href="menu.jsp">Regresar</a>
    </div>
</body>
<head>
  <link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"
  />
</head>
</html>
