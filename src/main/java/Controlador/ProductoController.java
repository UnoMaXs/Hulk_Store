package Controlador;

import ConexionSQL.ConexionMySQL;
import Modelo.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static ConexionSQL.ProductoSQL.agregarProducto;


public class ProductoController {

    private static final Scanner scanner = new Scanner(System.in);

    public static void agregarProductoDesdeConsola() {
        String nombre = obtenerEntradaNoVacia("Nombre");
        String descripcion = obtenerEntrada("Descripción");
        double precio = obtenerNumero("Precio");
        int cantidad = obtenerNumeroEntero("Cantidad");

        agregarProducto(nombre, descripcion, precio, cantidad);
        System.out.println("Producto agregado exitosamente.");
    }

    private static String obtenerEntrada(String mensaje) {
        System.out.print(mensaje + ": ");
        return scanner.nextLine();
    }

    private static String obtenerEntradaNoVacia(String mensaje) {
        String entrada;
        do {
            entrada = obtenerEntrada(mensaje);
        } while (entrada.isEmpty());
        return entrada;
    }

    private static double obtenerNumero(String mensaje) {
        try {
            System.out.print(mensaje + ": ");
            return scanner.nextDouble();
        } catch (InputMismatchException e) {
            throw new InputMismatchException("El campo " + mensaje + " debe ser un número válido.");
        } finally {
            scanner.nextLine(); // Consumir la nueva línea pendiente después de nextDouble
        }
    }

    private static int obtenerNumeroEntero(String mensaje) {
        try {
            System.out.print(mensaje + ": ");
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            throw new InputMismatchException("El campo " + mensaje + " debe ser un número entero válido.");
        } finally {
            scanner.nextLine(); // Consumir la nueva línea pendiente después de nextInt
        }
    }

    public static List<Producto> obtenerProductosDisponibles() {
        List<Producto> productos = new ArrayList<>();

        String sql = "SELECT * FROM productos WHERE cantidad > 0";

        try (Connection conn = ConexionMySQL.obtenerConexion();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                double precio = resultSet.getDouble("precio");
                int cantidad = resultSet.getInt("cantidad");

                Producto producto = new Producto(id,nombre, descripcion, precio, cantidad);
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    public static void actualizarCantidadDisponible(Producto producto, int cantidad) {
        // Conexión a la base de datos (debes tener una clase para obtener la conexión)
        try (Connection conn = ConexionMySQL.obtenerConexion()) {
            String sql = "UPDATE productos SET cantidad = cantidad - ? WHERE id = ?"; // Asumiendo que tienes una columna 'id' como identificador único.

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, cantidad); // Cantidad a restar
                statement.setInt(2, producto.getId()); // ID del producto a actualizar

                statement.executeUpdate(); // Ejecutar la actualización en la base de datos
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

