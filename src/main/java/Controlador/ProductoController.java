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

    /*public static void mostrarProductosDisponibles(){
        List<Producto> productos= obtenerTodosLosProductos();

        if (productos.isEmpty()){
            System.out.println("No Hay productos disponibles");
        } else {
            System.out.println("-----PRODUCTOS DISPONIBLES-----");
            for (Producto producto : productos){
                System.out.println(producto.getNombre()+" - "+producto.getPrecio()+" - "+producto.getCantidad());
            }
        }
    }*/

    /*private static List<Producto> obtenerTodosLosProductos() {
        List<Producto> productos= new ArrayList<>();

        String sql = "SELECT * FROM productos";
        try(Connection conn = ConexionMySQL.obtenerConexion()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();{
                while (resultSet.next()) {
                    String nombre = resultSet.getString("nombre");
                    String descripcion = resultSet.getString("descripcion");
                    double precio = resultSet.getDouble("precio");
                    int cantidad = resultSet.getInt("cantidad");

                    Producto producto = new Producto(nombre, descripcion, precio, cantidad);
                    productos.add(producto);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productos;
    }*/

    public static List<Producto> obtenerProductosDisponibles() {
        List<Producto> productos = new ArrayList<>();

        String sql = "SELECT * FROM productos WHERE cantidad > 0"; // Supongo que la columna cantidad indica la disponibilidad

        try (Connection conn = ConexionMySQL.obtenerConexion();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                double precio = resultSet.getDouble("precio");
                int cantidad = resultSet.getInt("cantidad");

                Producto producto = new Producto(nombre, descripcion, precio, cantidad);
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }
}

