/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author acosta
 */
public class Carrito {
    private List<Producto> productos = new ArrayList<>();

    public void addProduct(Producto product) {
        productos.add(product);
    }

    public List<Producto> getProducts() {
        return productos;
    }

    public double getTotalPrice() {
        double total = 0;
        for (Producto product : productos) {
            total += product.getPrice();
        }
        return total;
    }
}
