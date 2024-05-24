<jsp:include page="validar.jsp"></jsp:include>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Menu</title>
    <link rel="stylesheet" type="text/css" href="css/estilosmenu.css">
</head>
<body>

    <div class="container">
        <div class="productos">
            <a href="ServletProducto?tipo=listar">Lista de Productos</a>
            <img src="img/productos.jpeg" alt="Imagen de productos">
        </div>
        <div class="personal">
            <a href="ServletPersonal?tipo=listar">Lista de Personal</a>
            <img src="img/personal.jpg" alt="Imagen de personal">
        </div>
        <div class="cliente">
            <a href="ServletCliente?tipo=listar">Lista de Clientes</a>
            <img src="img/cliente.png" alt="Imagen de clientes">
        </div>
        <div class="venta">
            <a href="ServletVenta?tipo=listar">Lista de Ventas</a>
            <img src="img/Venta.jpg" alt="Imagen de ventas">
        </div>
    </div>
   <div>
   <a href="ServletPersonal?tipo=cerrarSesion" class="logout-link">Cerrar sesión</a>   
   </div>   
</body>
</html>
