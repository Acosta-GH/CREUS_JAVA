package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Producto;

@WebServlet(name = "HomeController", urlPatterns = {"/HomeController"})
public class HomeController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        
        List<Producto> cart = (List<Producto>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }

        String productName = request.getParameter("productName");
        String productPriceStr = request.getParameter("productPrice");
        if (productName != null && productPriceStr != null) {
            double productPrice = Double.parseDouble(productPriceStr);
            Producto product = new Producto(1,productName, productPrice);
            cart.add(product);
        }

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Carrito de Compras</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Carrito de Compras</h1>");

            out.println("<h2>Productos en el carrito:</h2>");
            if (cart.isEmpty()) {
                out.println("<p>El carrito está vacío.</p>");
            } else {
                out.println("<ul>");
                for (Producto product : cart) {
                    out.println("<li>" + product.getName() + " - $" + product.getPrice() + "</li>");
                }
                out.println("</ul>");
            }

            // Formulario para agregar productos
            out.println("<h2>Agregar Producto</h2>");
            out.println("<form method='post' action='HomeController'>");
            out.println("    <input type='text' name='productName' placeholder='Nombre del producto' required>");
            out.println("    <input type='number' name='productPrice' placeholder='Precio' required step='0.01'>");
            out.println("    <input type='submit' value='Agregar al carrito'>");
            out.println("</form>");

            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Carrito de Compras Sencillo";
    }
}
