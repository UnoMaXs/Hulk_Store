package Vista;

import Modelo.Usuario;
import Modelo.UsuarioDAO;

import java.util.Scanner;

public class InicioSesion {

    private Scanner scanner;
    private UsuarioDAO usuarioDAO;

    public InicioSesion() {
        scanner = new Scanner(System.in);
        usuarioDAO = new UsuarioDAO();
    }

    public void iniciarSesion() {
        System.out.println("Inicio de Sesión en Hulk Store");
        System.out.print("Correo electrónico: ");
        String correo = scanner.nextLine();

        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();

        // Realizar la autenticación del usuario
        if (autenticarUsuario(correo, contrasena)) {
            System.out.println("¡Inicio de sesión exitoso!");
        } else {
            System.out.println("Error en las credenciales. Vuelve a intentarlo.");
        }
    }

    private boolean autenticarUsuario(String correo, String contrasena) {
        // Aquí debes implementar la lógica de autenticación
        // Puedes consultar la base de datos y comparar las credenciales con los registros de usuarios
        // Ejemplo simplificado:
        Usuario usuario = usuarioDAO.obtenerUsuarioPorCorreo(correo);

        if (usuario != null && usuario.getContrasena().equals(contrasena)) {
            return true;
        }
        return false;
    }

}
