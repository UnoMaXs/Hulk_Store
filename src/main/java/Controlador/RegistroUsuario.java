package Controlador;

import Modelo.Usuario;

import java.util.Scanner;

public class RegistroUsuario {

    private Scanner scanner;
    private UsuarioDAO usuarioDAO;

    public RegistroUsuario(){
        scanner = new Scanner(System.in);
        usuarioDAO = new UsuarioDAO();
    }

    public void registrarNuevoUsuario() {
        System.out.println("Registro de Usuario en Hulk Store");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Correo electrónico: ");
        String correo = scanner.nextLine();

        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();

        Usuario nuevoUsuario = new Usuario(nombre, correo, contrasena);
        usuarioDAO.crearUsuario(nuevoUsuario);

        System.out.println("¡Usuario registrado con éxito!");
    }

}
