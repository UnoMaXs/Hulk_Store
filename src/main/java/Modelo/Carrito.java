package Modelo;

import Controlador.ProductoController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Carrito {

    private List<Producto> productos;
    private Map<Producto, Integer> productosEnCarrito;

    public Carrito() {
        this.productos = new ArrayList<>();
        productosEnCarrito = new HashMap<>();
    }

    public void agregarProductoPorId(int id, List<Producto> productosDisponibles, int cantidad) {
        for (Producto producto : productosDisponibles) {
            if (producto.getId() == id) {
                if (productosEnCarrito.containsKey(producto)) {
                    int cantidadExistente = productosEnCarrito.get(producto);
                    productosEnCarrito.put(producto, cantidadExistente + cantidad);
                } else {
                    productosEnCarrito.put(producto, cantidad);
                }
                System.out.println("Producto agregado al carrito exitosamente.");
                return; // Importante para salir del bucle una vez que se agrega el producto
            }
        }
        System.out.println("Error: No hay productos disponibles con el ID proporcionado.");
    }

    public Map<Producto, Integer> mostrarCarrito() {
        if (productosEnCarrito.isEmpty()) {
            System.out.println("El carrito está vacío.");
        } else {
            System.out.println("----- Productos en el carrito -----");
            for (Map.Entry<Producto, Integer> entry : productosEnCarrito.entrySet()) {
                Producto producto = entry.getKey();
                int cantidad = entry.getValue();
                System.out.println("Nombre: " + producto.getNombre() + " Cantidad en carrito: " + cantidad);
            }
        }
        return productosEnCarrito;
    }

    public void vaciarCarrito() {
        productosEnCarrito.clear();
        System.out.println("Carrito vaciado.");
    }

    public void realizarCompra() {
        Map<Producto, Integer> productosEnCarrito = mostrarCarrito();

        if (productosEnCarrito.isEmpty()) {
            System.out.println("El carrito está vacío. Agregue productos antes de realizar una compra.");
        } else {
            double costoTotal = 0.0;

            System.out.println("\nProductos en el carrito:");
            for (Map.Entry<Producto, Integer> entry : productosEnCarrito.entrySet()) {
                Producto producto = entry.getKey();
                int cantidadCompra = entry.getValue();
                double costoProducto = producto.getPrecio() * cantidadCompra;
                costoTotal += costoProducto;

                System.out.println("Nombre: " + producto.getNombre() + ", Cantidad: " + cantidadCompra + ", Precio por unidad: " + producto.getPrecio() + ", Costo por producto: " + costoProducto);

                // Actualiza la cantidad disponible en la base de datos
                ProductoController.actualizarCantidadDisponible(producto, cantidadCompra);
            }

            System.out.println("Costo total de la compra: " + costoTotal);
        }
    }
}
