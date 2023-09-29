/**
 * Este programa encuentra números de Smith en una matriz dada y calcula su tiempo de ejecución
 * utilizando un enfoque iterativo. Un número de Smith es un número entero que cumple con la
 * propiedad de que la suma de los dígitos de sus factores primos es igual a la suma de sus propios dígitos.
 * El programa encuentra los números de Smith en la matriz y los muestra junto con su tiempo de ejecución.
 */
package problema_4_4;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int[][] matriz = {
                {4, 14, 27},
                {378, 13, 8},
                {19, 71, 45}
        };
        // Tiempo de ejecución Algoritmo iterativo
        long inicioIterativo = System.nanoTime();
        ArrayList<Integer> nums = encontrarNumSmith(matriz);
        System.out.println("Los números de Smith encontrados fueron: " + nums);
        long finIterativo = System.nanoTime();
        long tiempoTotal = finIterativo - inicioIterativo;
        System.out.println("Tiempo de ejecución del método ITERATIVO (nano): " + tiempoTotal + "\n");

    }

    /**
     * Encuentra los números de Smith en una matriz dada.
     *
     * @param matriz La matriz en la que se buscarán los números de Smith.
     * @return Una lista de números de Smith encontrados en la matriz.
     */
    public static ArrayList<Integer> encontrarNumSmith(int[][] matriz) {
        ArrayList<Integer> numSmith = new ArrayList<>();
        for (int[] ints : matriz) {
            for (int j = 0; j < matriz.length; j++) {
                if (isPrime(ints[j])) {
                    continue;
                }
                if (isSmithNumber(ints[j])) {
                    numSmith.add(ints[j]);
                }
            }
        }
        return numSmith;
    }

    /**
     * Verifica si un número es un número de Smith.
     *
     * @param n El número que se verificará si es un número de Smith.
     * @return true si el número es un número de Smith, false en caso contrario.
     */
    static boolean isSmithNumber(int n) {
        if (n <= 1) return false;

        // Encuentra la lista de factores primos del número
        List<Integer> primeFactors = new ArrayList<>();
        int originalNumber = n;
        for (int i = 2; i * i <= n; i++) {
            while (n % i == 0) {
                if (isPrime(i)) {
                    primeFactors.add(i);
                }
                n /= i;
            }
        }
        if (n > 1 && isPrime(n)) {
            primeFactors.add(n);
        }

        // Encuentra la suma de los dígitos de los factores primos
        int sumOfPrimeDigits = 0;
        for (int factor : primeFactors) {
            sumOfPrimeDigits += sumOfDigits(factor);
        }

        // Encuentra la suma de los dígitos del número original
        int sumOfOriginalDigits = sumOfDigits(originalNumber);

        // Compara las sumas de dígitos
        return sumOfPrimeDigits == sumOfOriginalDigits;
    }

    /**
     * Verifica si un número es primo.
     *
     * @param num El número que se verificará si es primo.
     * @return true si el número es primo, false en caso contrario.
     */
    static boolean isPrime(int num) {
        // Los números menores o iguales a 1 no son primos
        if (num <= 1) {
            return false;
        }
        // Comprobamos si el número es divisible por algún número desde 2 hasta la raíz cuadrada de 'número'
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                // Si es divisible, no es primo
                return false;
            }
        }
        // Si no se encontró ningún divisor, es primo
        return true;
    }

    /**
     * Calcula la suma de los dígitos de un número.
     *
     * @param n El número del cual se calculará la suma de dígitos.
     * @return La suma de los dígitos del número.
     */
    static int sumOfDigits(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }
}
