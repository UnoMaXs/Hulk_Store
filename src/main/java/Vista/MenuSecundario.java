package Vista;

import Controlador.ProductoController;
import Modelo.Carrito;
import Modelo.Producto;

import java.util.*;

import static Controlador.ProductoController.obtenerProductosDisponibles;

public class MenuSecundario {

    private final Scanner teclado;
    private final Carrito carrito;

    public MenuSecundario(Scanner teclado, Carrito carrito) {
        this.teclado = teclado;
        this.carrito = carrito;
    }

    public void mostrarMenu() {
        int opcion;
        List<Producto> productosDisponibles = new ArrayList<>(); // Inicializa la lista aquí

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
                        productosDisponibles = obtenerProductosDisponibles(); // Actualizamos productosDisponibles
                        mostrarProductos(productosDisponibles);
                        break;
                    case 2:
                        // Lógica para agregar al carrito
                        System.out.println("\nIngrese el Id del producto: ");
                        int codigo = teclado.nextInt();
                        System.out.println("Ingrese cantidad de productos: ");
                        int cantidad = teclado.nextInt();
                        carrito.agregarProductoPorId(codigo, productosDisponibles, cantidad); // Pasamos productosDisponibles
                        break;
                    case 3:
                        carrito.mostrarCarrito();
                        break;
                    case 4:
                        carrito.realizarCompra();
                        carrito.vaciarCarrito();
                        System.out.println("Compra realizada con éxito");
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


    private void mostrarProductos(List<Producto> productos) {
        if (productos.isEmpty()){
            System.out.println("No hay productos disponibles");
        } else {
            System.out.println("Productos Disponibles:");
            for (Producto producto : productos) {
                System.out.println("Id: " + producto.getId() + " Nombre: " + producto.getNombre() + " Cantidad: " + producto.getCantidad());
            }
        }
    }
}
