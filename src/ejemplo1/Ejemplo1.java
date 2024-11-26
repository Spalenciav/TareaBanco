package ejemplo1;

import java.util.Scanner;

public class Ejemplo1 {

    public static void main(String[] args) {
        // Crear cliente y cuenta
        Cliente cliente1 = new Cliente("Nestor P");
        Cuenta cuenta1 = new Cuenta(cliente1);      // se crea un cliente y luego una cuenta

        // Crear cliente2 y cuenta2
        Cliente cliente2 = new Cliente("Santiago");
        Cuenta cuenta2 = new Cuenta(cliente2);
        
        // Variables para autenticación y límite
        String PIN = "1234"; // Simulamos un PIN fijo para autenticación.
        int limiteDiario = 20000000; // Límite diario de transacciones.
        int transaccionesHoy = 0; // Acumulador de transacciones realizadas en el día.

        // Scanner para leer entrada del usuario
        Scanner s = new Scanner(System.in);
        int opcion;

        // Menú interactivo
        do {
            System.out.println("\n--- MENÚ DE OPERACIONES ---");
            System.out.println("1. Consignación");
            System.out.println("2. Retiro");
            System.out.println("3. Transferencia");
            System.out.println("4. Consultar saldo");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = s.nextInt();

            switch (opcion) {
                case 1: // Consignación
                
                System.out.println("Ingrese el valor que quiere consignar(Sin puntos ni comas)");
                int h = s.nextInt();
                System.out.println("Consignando " + h + " pesos...");
                cuenta1.consignar(h, false);     //dar el valor false para no aplicar el 4xmil
                System.out.println("Saldo después de consignar: " + cuenta1.getSaldo() + " pesos");
        
                    break;

                case 2: // Retiro
                    if (!autenticarUsuario(s, PIN)) break; // Verificar autenticación.
                    System.out.println("Ingrese el valor que quiere retirar(Sin puntos ni comas)");

                    int i = s.nextInt();
                    System.out.println("Retirando "+ i + " pesos...");
                    if (cuenta1.retirar(i)) {
                    System.out.println("Retiro exitoso. Saldo actual: " + cuenta1.getSaldo() + " pesos");
                    } else {
                    System.out.println("Retiro fallido. Saldo actual: " + cuenta1.getSaldo() + " pesos");
                    }

                    break;

                case 3: // Transferencia
                    System.out.println("Ingrese el valor que quiere transferir a la cuenta 2");

                    int t = s.nextInt();
                    System.out.println("Transfiriendo " + t + " pesos a Santiago Palencia...");
                    if (cuenta1.transferir(cuenta2, t)) {
                    System.out.println("Transferencia exitosa.");
                    } else {
                    System.out.println("Transferencia fallida.");
                    }

                    break;

                case 4: // Consultar saldo
                    System.out.println("Saldo actual de la cuenta de " + cuenta1.getNombreDelTitular() + ": " + cuenta1.getSaldo());
                    System.out.println("Saldo actual de la cuenta de " + cuenta2.getNombreDelTitular() + ": " + cuenta2.getSaldo());

                    break;

                case 5: // Salir
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        } while (opcion != 5);

        s.close(); // Cerramos el Scanner al final
    }


    // Método para autenticación del usuario
    private static boolean autenticarUsuario(Scanner scanner, String PIN) {
        System.out.print("Ingrese su PIN para continuar: ");
        String pinIngresado = scanner.next();
        if (pinIngresado.equals(PIN)) {
            System.out.println("Autenticación exitosa.");
            return true;
        } else {
            System.out.println("Autenticación fallida. Operación cancelada.");
            return false;
        }
    }
}