<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="models.Carrito"%>
<%@ page import="models.Producto"%>
<%
    List<Producto> products = new ArrayList<>();
    products.add(new Producto(1, "Producto 1", 10.0));
    products.add(new Producto(2, "Producto 2", 20.0));
    
    Carrito cart = (Carrito) session.getAttribute("cart");
    if (cart == null) {
        cart = new Carrito();
        session.setAttribute("cart", cart);
    }

    String productId = request.getParameter("productId");
    if (productId != null) {
        int id = Integer.parseInt(productId);
        Producto product = new Producto(id, "Producto " + productId, Double.parseDouble(productId) * 10);
        cart.addProduct(product);
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Carrito de Compras</title>
</head>
<body>
    <h1>Carrito de Compras</h1>
    <form action="index.jsp" method="get">
        <select name="productId">
            <option value="1">Producto 1 - $10.0</option>
            <option value="2">Producto 2 - $20.0</option>
        </select>
        <button type="submit">Agregar al carrito</button>
    </form>

    <h2>Productos en el Carrito</h2>
    <table>
        <tr>
            <th>Nombre</th>
            <th>Precio</th>
        </tr>
        <%
            for (Producto product : cart.getProducts()) {
        %>
        <tr>
            <td><%= product.getName() %></td>
            <td><%= product.getPrice() %></td>
        </tr>
        <%
            }
        %>
    </table>
    <h3>Total: <%= cart.getTotalPrice() %></h3>
    <a href="index.jsp">Continuar comprando</a>
</body>
</html>
