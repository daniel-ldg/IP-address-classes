package app;

import domain.IpAddress;

import java.util.Scanner;

/**
 * @author Daniel Ladrón de Guevara
 */
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("------------------------------------------------");
        System.out.println("             Programa calculo red");
        System.out.println("------------------------------------------------");

        IpAddress ipAddress = null;
        do {
            System.out.print("Escribe una dirección IP: ");
            String input = scanner.nextLine();
            ipAddress = new IpAddress(input);

            if (!ipAddress.isValid()) {
                System.out.println("Dirección no valida");
            }
        } while (!ipAddress.isValid());

        System.out.println("Dirección valida");
        System.out.println("Clase: " + ipAddress.getAddressClass());
        System.out.println("Dirección de red: " + ipAddress.getNetworkAddress());
        System.out.println("Dirección broadcast: " + ipAddress.getBroadcastAddress());
        System.out.println("Máscara de red: " + ipAddress.getNetmask());
    }
}