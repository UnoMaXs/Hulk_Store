package Vista;

import java.util.Scanner;

public class Main {
    public static void main (String[] args) {

        Scanner teclado = new Scanner(System.in);

        System.out.println("--- HULK STORE ---");
        System.out.println("1. Registrar usuario");
        System.out.println("2. Iniciar sesion");
        System.out.print("Seleccione una opcion: ");
        int opcion = teclado.nextInt();

        switch (opcion) {
            case 1:
                RegistroUsuario registro = new RegistroUsuario();
                registro.registrarNuevoUsuario();
                break;
            case 2:
                InicioSesion inicioSesion = new InicioSesion();
                inicioSesion.iniciarSesion();
                break;
            default:
                System.out.println("Cerrando app");
                break;
        }
    }
}
