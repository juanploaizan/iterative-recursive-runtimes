/**
 * Este programa solicita al usuario ingresar un número entero 'n' y luego
 * calcula y muestra dos representaciones de una matriz cuadrada 'n x n'.
 * El programa implementa dos enfoques diferentes para llenar la matriz:
 * uno de manera iterativa y otro de manera recursiva. Luego, muestra el tiempo
 * de ejecución de ambos enfoques y la diferencia en el tiempo de ejecución.
 */
package problema_4_2;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int n;
        String input = JOptionPane.showInputDialog("Ingrese el valor de n.");
        try {
            // Lee el valor de 'n' ingresado por el usuario.
            n = Integer.parseInt(input);

            if (n % 2 == 0) {
                // Verifica si 'n' es par y muestra un mensaje de error si lo es.
                System.err.println("El valor de n debe ser impar.");
            } else {
                int[][] matrizIterativa = new int[n][n], matrizRecursiva = new int[n][n];

                System.out.println("Para un n = " + n + "\n");

                // Tiempo de ejecución iterativo
                long inicioIterativo = System.nanoTime();
                llenarMatrizIterativamente(matrizIterativa);
                long finIterativo = System.nanoTime();
                long tiempoIterativo = finIterativo - inicioIterativo;
                mostrarMatriz(matrizIterativa);
                System.out.println("Tiempo de ejecución del método ITERATIVO (nano): " + tiempoIterativo + "\n");

                // Tiempo de ejecución recursivo
                long inicioRecursivo = System.nanoTime();
                llenarMatrizRecursivamente(matrizRecursiva, 0, 0, 0);
                long finRecursivo = System.nanoTime();
                long tiempoRecursivo = finRecursivo - inicioRecursivo;
                mostrarMatriz(matrizRecursiva);
                System.out.println("Tiempo de ejecución del método RECURSIVO (nano): " + tiempoRecursivo + "\n");

                // Muestra la diferencia en el tiempo de ejecución entre los algoritmos.
                System.out.println("La diferencia de los algoritmos, respectivamente, es de: " + (tiempoRecursivo - tiempoIterativo) + " nanosegundos.");
            }
        } catch (NumberFormatException e) {
            // Maneja la excepción si el valor ingresado no es un número entero.
            System.err.println("El valor ingresado no es un número entero.");
        }
    }

    /**
     * Imprime una matriz en la consola.
     * @param matriz La matriz que se va a imprimir.
     */
    private static void mostrarMatriz(int[][] matriz) {
        for (int[] ints : matriz) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Llena una matriz de manera iterativa siguiendo un patrón especificado.
     * @param matriz La matriz a llenar.
     */
    private static void llenarMatrizIterativamente(int[][] matriz) {
        int n = matriz.length;
        for (int columna = 0; columna < n; columna += 2) {
            int sec = 0;
            for (int fila = columna / 2; fila < n - columna / 2; fila++) {
                matriz[fila][columna] = 1 << (n - 1 - sec); // 1 << (n - 1 - sec) es equivalente a 2^(n-1-sec)
                sec++;
            }
        }
    }

    /**
     * Llena una matriz de manera recursiva siguiendo un patrón especificado.
     * @param matriz La matriz a llenar.
     * @param fila Índice de fila actual.
     * @param columna Índice de columna actual.
     * @param sec Valor que se utiliza en el cálculo.
     * @return La matriz llenada.
     */
    private static int[][] llenarMatrizRecursivamente(int[][] matriz, int fila, int columna, int sec) {
        if (columna >= matriz.length) {
            return matriz;
        }
        matriz[fila][columna] = 1 << (matriz.length - 1 - sec);
        if (fila + 1 < matriz.length - columna / 2) {
            return llenarMatrizRecursivamente(matriz, fila + 1, columna, sec + 1);
        } else {
            return llenarMatrizRecursivamente(matriz, (columna + 2) / 2, columna + 2, 0);
        }
    }
}
