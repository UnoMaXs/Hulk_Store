package Vista;

import Controlador.ProductoController;
import Controlador.UsuarioDAO;
import Modelo.Carrito;
import Modelo.Usuario;

import java.util.Scanner;

public class InicioSesion {

    private final Scanner scanner;
    private final UsuarioDAO usuarioDAO;
    private final ProductoController productoController;
    private final Carrito carrito;

    public InicioSesion() {
        scanner = new Scanner(System.in);
        usuarioDAO = new UsuarioDAO();
        productoController = new ProductoController();
        carrito = new Carrito();
    }

    public void iniciarSesion() {
        System.out.println("\nInicio de Sesión en Hulk Store");
        System.out.print("Correo electrónico: ");
        String correo = scanner.nextLine();

        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();

        // Realizar la autenticación del usuario
        if (autenticarUsuario(correo, contrasena)) {
            System.out.println("\n¡Inicio de sesión exitoso!");
            mostrarMenuSecundario();
        } else {
            System.out.println("\nError: Credenciales no válidas. Vuelve a intentarlo.");
        }
    }

    private boolean autenticarUsuario(String correo, String contrasena) {
        Usuario usuario = usuarioDAO.obtenerUsuarioPorCorreo(correo);
        return usuario != null && usuario.getContrasena().equals(contrasena);
    }

    private void mostrarMenuSecundario() {
        MenuSecundario menuSecundario = new MenuSecundario(scanner, carrito);
        menuSecundario.mostrarMenu();
    }

    // Método para cerrar el Scanner
    public void cerrarScanner() {
        scanner.close();
    }
}
