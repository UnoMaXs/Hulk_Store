package Vista;

import Controlador.RegistroUsuario;

import java.util.Scanner;


public class MenuPrincipal {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        System.out.println("--- HULK STORE ---");
        System.out.println("1. Iniciar sesion");
        System.out.println("2. Registrar usuario");
        System.out.print("Seleccione una opcion: ");
        int opcion = teclado.nextInt();

        switch (opcion) {
            case 1:
                InicioSesion inicioSesion = new InicioSesion();
                inicioSesion.iniciarSesion();
                break;
            case 2:
                RegistroUsuario registro = new RegistroUsuario();
                registro.registrarNuevoUsuario();
                break;
            default:
                System.out.println("Cerrando app");
                break;
        }
    }
}

