/**
 * Este programa encuentra los números hambrientos en una matriz dada y calcula
 * su tiempo de ejecución utilizando dos enfoques: uno iterativo y otro recursivo.
 * Un número hambriento es el número natural más pequeño 'n' tal que 2^n contiene
 * los primeros 'k' dígitos de Pi, donde 'k' es la longitud de 'n'. Los números
 * hambrientos encontrados en la matriz se imprimen junto con su tiempo de
 * ejecución correspondiente.
 */
package problema_4_3;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        int[][] matriz = new int[][]{
                {1, 2, 74},
                {144, 2003, -1},
                {5, 17, 3}
        };

        // Dígitos de PI
        String DIGITOS = "3141592653";

        // Tiempo de ejecución Algoritmo iterativo
        long inicioIterativo = System.nanoTime();
        ArrayList<Integer> numerosHambrientos = encontrarNumerosHambrientos(matriz, DIGITOS);
        long finIterativo = System.nanoTime();
        long tiempoTotal = finIterativo - inicioIterativo;
        System.out.println("Los números hambrientos encontrados en la matriz son: " + numerosHambrientos);
        System.out.println("Tiempo de ejecución del método ITERATIVO (nano): " + tiempoTotal + "\n");

        // Tiempo de ejecución Algoritmo recursivo
        long inicioRecursivo = System.nanoTime();
        ArrayList<Integer> numerosHambrientosR = new ArrayList<>();
        numerosHambrientosR = encontrarHambrientosRecursivo(matriz, DIGITOS, 0, 0, numerosHambrientosR);
        long finRecursivo = System.nanoTime();
        long tiempoTotalR = finRecursivo - inicioRecursivo;
        System.out.println("Los números hambrientos encontrados en la matriz son: " + numerosHambrientosR);
        System.out.println("Tiempo de ejecución del método RECURSIVO (nano): " + tiempoTotalR + "\n");

    }

    /**
     * Método para recorrer la matriz y encontrar números hambrientos.
     *
     * @param matriz  La matriz en la que se buscarán los números hambrientos.
     * @param DIGITOS Los dígitos de Pi a considerar.
     * @return Una lista de números hambrientos encontrados en la matriz.
     */
    public static ArrayList<Integer> encontrarNumerosHambrientos(int[][] matriz, String DIGITOS) {
        // Arreglo para retornar los números hambrientos encontrados
        ArrayList<Integer> numerosHambrientos = new ArrayList<>();

        // Ciclo para iterar la matriz
        for (int[] ints : matriz) {
            for (int num : ints) {
                boolean numHambriento = isHambriento(num, DIGITOS); // Llamado a la función
                if (numHambriento) {
                    numerosHambrientos.add(num);
                }
            }
        }

        return numerosHambrientos;
    }

    /**
     * Método recursivo para encontrar números hambrientos en la matriz.
     *
     * @param matriz   La matriz en la que se buscarán los números hambrientos.
     * @param DIGITOS  Los dígitos de Pi a considerar.
     * @param i        Índice de fila actual.
     * @param j        Índice de columna actual.
     * @param nums     Lista para almacenar los números hambrientos encontrados.
     * @return La lista de números hambrientos encontrados en la matriz.
     */
    public static ArrayList<Integer> encontrarHambrientosRecursivo(int[][] matriz, String DIGITOS, int i, int j, ArrayList<Integer> nums) {
        if (i == matriz.length) {
            // Si hemos alcanzado el final de la matriz, salimos de la recursión.
            return nums;
        }
        if (j == matriz[i].length) {
            return encontrarHambrientosRecursivo(matriz, DIGITOS, ++i, 0, nums);
        }
        boolean numHambrientos = isHambriento(matriz[i][j], DIGITOS);
        if (numHambrientos) {
            nums.add(matriz[i][j]);
        }
        return encontrarHambrientosRecursivo(matriz, DIGITOS, i, ++j, nums);
    }

    /**
     * Función para determinar si un número es hambriento.
     *
     * @param num     El número a verificar.
     * @param DIGITOS Los dígitos de Pi a considerar.
     * @return true si el número es hambriento, false en caso contrario.
     */
    public static boolean isHambriento(int num, String DIGITOS) {
        // Si el número es negativo, no es hambriento
        if (num < 0) {
            return false;
        }

        // Acotar la cantidad de dígitos
        String tamanioNum = Integer.toString(num);
        String digitosPi = DIGITOS.substring(0, tamanioNum.length());

        // Calcular la k-ésima potencia de dos
        double numPotencia = Math.pow(2, num);

        // Convertir a String para comparar sus dígitos
        String digitosPotencia = Double.toString(numPotencia);

        // Comprobar si los dígitos acotados de Pi están en los dígitos de la potencia
        return digitosPotencia.contains(digitosPi);
    }
}
