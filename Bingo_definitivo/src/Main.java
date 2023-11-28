import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private static final int TAMANO_CARTON = 10;
    private static final int MAX_NUMB = 99;
    private static final int NUMBS_PARA_LINEA = 5;
    private static final int PREMIO_POR_BINGO = 10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Paso 1: Crear un cartón de bingo
        ArrayList<Integer> carton = generarCarton();

        // Paso 2: Pedir la cantidad de apuesta del jugador
        System.out.print("Ingrese la cantidad de apuesta: ");
        int apuesta = scanner.nextInt();

        // Paso 3: Pedir la cantidad de números previstos para acertar el bingo
        System.out.print("Ingrese la cantidad de números previstos para acertar el bingo: ");
        int numerosParaBingo = scanner.nextInt();

        // Paso 4: Simular el juego de bingo
        jugarBingo(carton, apuesta, numerosParaBingo);

        scanner.close();
    }

    private static ArrayList<Integer> generarCarton() {
        ArrayList<Integer> carton = new ArrayList<>();
        Set<Integer> numerosElegidos = new HashSet<>();
        Random random = new Random();

        while (numerosElegidos.size() < TAMANO_CARTON) {
            int numero = random.nextInt(MAX_NUMB) + 1;
            if (numerosElegidos.add(numero)) {
                carton.add(numero);
            }
        }

        Collections.sort(carton);
        System.out.println("Cartón generado: " + carton);
        return carton;
    }

    private static void jugarBingo(ArrayList<Integer> carton, int apuesta, int numerosParaBingo) {
        Set<Integer> numerosSalidos = new HashSet<>();
        int intentos = 0;

        while (numerosSalidos.size() < MAX_NUMB) {
            int numero = generarNumeroNoRepetido(numerosSalidos);
            intentos++;

            System.out.println("Número sacado: " + numero);

            if (carton.contains(numero)) {
                carton.remove(Integer.valueOf(numero));
                System.out.println("¡Has acertado un número!");
            }

            if (carton.isEmpty()) {
                System.out.println("¡BINGO!");

                // Mostrar intentos para bingo y linea
                System.out.println("Intentos para bingo: " + intentos);
                System.out.println("Intentos para línea: " + (intentos / NUMBS_PARA_LINEA));

                // Calcular premio y mostrarlo
                int premio = apuesta * PREMIO_POR_BINGO;
                if (numerosParaBingo == intentos) {
                    System.out.println("¡Felicidades! Has ganado €" + premio);
                } else {
                    System.out.println("No has conseguido ningún premio");
                }
                break;
            }
        }
    }

    private static int generarNumeroNoRepetido(Set<Integer> numerosSalidos) {
        Random random = new Random();
        int numero;
        do {
            numero = random.nextInt(MAX_NUMB) + 1;
        } while (numerosSalidos.contains(numero));

        numerosSalidos.add(numero);
        return numero;
    }
}
