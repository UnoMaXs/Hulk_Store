package Vista;

import Controlador.ProductoController;
import Modelo.Carrito;
import Modelo.Producto;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static Controlador.ProductoController.obtenerProductosDisponibles;

public class MenuSecundario {

    private final Scanner teclado;
    private final Carrito carrito;

    public MenuSecundario(Scanner teclado, Carrito carrito) {
        this.teclado = teclado;
        this.carrito = carrito;
    }

    private void mostrarProductos(List<Producto> productos) {
        System.out.println("Productos Disponibles:");
        for (int i = 0; i < productos.size(); i++) {
            System.out.println(i + ". " + productos.get(i).getNombre() + " - " + productos.get(i).getPrecio());
        }
    }

    private Producto obtenerProductoDesdeBaseDeDatos() {
        List<Producto> productosDisponibles = obtenerProductosDisponibles();

        if (!productosDisponibles.isEmpty()) {
            // Supongamos que seleccionamos el primer producto disponible
            return productosDisponibles.get(0);
        } else {
            return null;
        }
    }


    public void mostrarMenu() {
        int opcion;

        do {
            System.out.println("\n--- Bienvenido ---");
            System.out.println("""
                0. Añadir productos al inventario
                1. Ver Productos disponibles
                2. Agregar al carrito
                3. Ver Carrito
                4. Realizar Compra
                5. Vaciar Carrito
                6. Salir
                Seleccione una opción (0-3):\s""");

            try {
                opcion = teclado.nextInt();
                teclado.nextLine(); // Limpiar el buffer de entrada

                switch (opcion) {
                    case 0:
                        ProductoController.agregarProductoDesdeConsola();
                        break;
                    case 1:
                        List<Producto> productosDisponibles = obtenerProductosDisponibles();
                        mostrarProductos(productosDisponibles);
                        break;
                    case 2:
                        // Lógica para agregar al carrito
                        break;
                    case 3:
                        // Logica para ver carrito
                        break;
                    case 4:
                        // Logica para realizar compra
                        break;
                    case 5:
                        carrito.vaciarCarrito();
                        break;
                    case 6:
                        System.out.println("Cerrando app");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, elija una opción del 0 al 3.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un número válido.");
                teclado.nextLine(); // Limpiar el buffer en caso de entrada no válida
                opcion = -1; // Establecer opción a un valor inválido para que siga en el bucle
            }



        } while (opcion != 6);


    }
}
