/**
 * Este programa busca números de Smith en una matriz dada y calcula su tiempo de ejecución
 * utilizando un enfoque recursivo. Un número de Smith es un número entero que cumple con la
 * propiedad de que la suma de los dígitos de sus factores primos es igual a la suma de sus propios dígitos.
 * El programa encuentra los números de Smith en la matriz y los muestra junto con su tiempo de ejecución correspondiente.
 */
package problema_4_4;

import java.util.ArrayList;

public class MainRecursivo {

    public static void main(String[] args) {
        int[][] matriz = {
                {4, 14, 27},
                {378, 13, 8},
                {19, 71, 45}
        };
        ArrayList<Integer> numSmith = new ArrayList<>();
        // Tiempo de ejecución Algoritmo recursivo
        long inicioRecursivo = System.nanoTime();
        numSmith = encontrarNumSmith(matriz, 0, 0, numSmith);
        System.out.println(numSmith);
        long finRecursivo = System.nanoTime();
        long tiempoTotalR = finRecursivo - inicioRecursivo;
        System.out.println("Tiempo de ejecución del método RECURSIVO (nano): " + tiempoTotalR + "\n");

    }

    /**
     * Encuentra los números de Smith en una matriz dada utilizando un enfoque recursivo.
     *
     * @param matriz La matriz en la que se buscarán los números de Smith.
     * @param i      Índice de fila actual.
     * @param j      Índice de columna actual.
     * @param nums   Lista para almacenar los números de Smith encontrados.
     * @return Una lista de números de Smith encontrados en la matriz.
     */
    public static ArrayList<Integer> encontrarNumSmith(int[][] matriz, int i, int j, ArrayList<Integer> nums) {
        if (i == matriz.length) {
            // Si se llega al final de las filas se retorna el arreglo
            return nums;
        }
        if (j == matriz[i].length) {
            // Si se llega al final de las columnas debe continuar con la fila siguiente
            return encontrarNumSmith(matriz, ++i, 0, nums);
        }

        // Se verifica que el número no sea primo, si es primo, se continúa la búsqueda
        if (isPrimo(matriz[i][j], 0, 1)) {
            return encontrarNumSmith(matriz, i, ++j, nums);
        } else {
            if (isSmithNumber(matriz[i][j])) {
                nums.add(matriz[i][j]);
            }
        }
        return encontrarNumSmith(matriz, i, ++j, nums);
    }

    /**
     * Verifica si un número es un número de Smith.
     *
     * @param n El número que se verificará si es un número de Smith.
     * @return true si el número es un número de Smith, false en caso contrario.
     */
    static boolean isSmithNumber(int n) {
        if (n <= 1) return false;

        ArrayList<Integer> primeFactorsList = new ArrayList<>();
        primeFactors(n, 2, primeFactorsList);

        // Encuentra la suma de los dígitos de los factores primos
        int sumOfPrimeDigits = primeFactorsList.stream().mapToInt(Integer::intValue).sum();

        // Encuentra la suma de los dígitos del número original
        int sumOfOriginalDigits = sumOfDigits(n, 0);

        // Compara las sumas de dígitos
        return sumOfPrimeDigits == sumOfOriginalDigits;
    }

    /**
     * Encuentra los factores primos de un número utilizando un enfoque recursivo.
     *
     * @param numero          El número para el cual se buscarán los factores primos.
     * @param divisor         El divisor actual.
     * @param factoresPrimos  Lista para almacenar los factores primos encontrados.
     */
    static void primeFactors(int numero, int divisor, ArrayList<Integer> factoresPrimos) {
        if (numero <= 1) {
            return;
        }
        if (numero % divisor == 0) {
            factoresPrimos.add(divisor);
            primeFactors(numero / divisor, divisor, factoresPrimos);
        } else {
            primeFactors(numero, divisor + 1, factoresPrimos);
        }
    }

    /**
     * Verifica si un número es primo utilizando un enfoque recursivo.
     *
     * @param num       El número que se verificará si es primo.
     * @param divisores La cantidad de divisores encontrados hasta el momento.
     * @param i         El divisor actual.
     * @return true si el número es primo, false en caso contrario.
     */
    public static boolean isPrimo(int num, int divisores, int i) {
        if (i == num) {
            return divisores == 1;
        } else if (num % i == 0) {
            divisores++;
        }
        return isPrimo(num, divisores, ++i);
    }

    /**
     * Calcula la suma de los dígitos de un número utilizando un enfoque recursivo.
     *
     * @param n    El número del cual se calculará la suma de dígitos.
     * @param sum  La suma acumulada de dígitos.
     * @return La suma de los dígitos del número.
     */
    public static int sumOfDigits(int n, int sum) {
        if (n <= 0) {
            return sum;
        } else {
            sum += n % 10;
            n /= 10;
            return sumOfDigits(n, sum);
        }
    }
}
