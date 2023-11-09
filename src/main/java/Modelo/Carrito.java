package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Carrito {

    private List<Producto> productos;

    public Carrito() {
        this.productos = new ArrayList<>();
    }

    public Carrito(List<Producto> productos) {
        this.productos = new ArrayList<>();
    }

    public void vaciarCarrito() {
        productos.clear();
        System.out.println("Carrito vaciado.");
    }

    public List<Producto> getProductos() {
        return productos;
    }

}
