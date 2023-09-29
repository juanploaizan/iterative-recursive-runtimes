/**
 * Este programa calcula y muestra dos representaciones de una matriz cuadrada
 * de dimensiones 'n x 2n', donde 'n' es un número impar ingresado por el
 * usuario. El programa utiliza dos enfoques diferentes: uno iterativo y otro
 * recursivo. Luego, muestra el tiempo de ejecución de ambos enfoques y la
 * diferencia en el tiempo de ejecución.
 */
package problema_4_1;

import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        int n;
        String input = JOptionPane.showInputDialog("Ingrese el valor de n (debe ser impar)");
        try {
            // Lee el valor de 'n' ingresado por el usuario.
            n = Integer.parseInt(input);

            if (n % 2 == 0) {
                // Verifica si 'n' es par y muestra un mensaje de error si lo es.
                System.err.println("El valor de n debe ser impar");
            } else {
                int[][] matrizIterativa = new int[n][2 * n];
                int[][] matrizRecursiva = new int[n][2 * n];

                System.out.println("Para un n = " + n + "\n");

                // Tiempo de ejecución iterativo
                long inicioIterativo = System.nanoTime();
                matrizIterativa = sectoresIterativo(matrizIterativa);
                long finIterativo = System.nanoTime();
                long tiempoIterativo = finIterativo - inicioIterativo;
                imprimirMatriz(matrizIterativa);
                System.out.println("Tiempo de ejecución del método ITERATIVO (nano): " +
                        tiempoIterativo + "\n");

                // Tiempo de ejecución recursivo
                long inicioRecursivo = System.nanoTime();
                matrizRecursiva = sectoresRecursivo(matrizRecursiva, 0, 0, 0, matrizRecursiva.length, 1, true);
                long finRecursivo = System.nanoTime();
                long tiempoRecursivo = finRecursivo - inicioRecursivo;
                imprimirMatriz(matrizRecursiva);
                System.out.println("Tiempo de ejecución del método RECURSIVO (nano): " + tiempoRecursivo + "\n");

                // Muestra la diferencia en el tiempo de ejecución entre los algoritmos.
                System.out.println("La diferencia de los algoritmos, respectivamente, es de: "
                        + Math.abs(tiempoRecursivo - tiempoIterativo) + " nanosegundos.");
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
    public static void imprimirMatriz(int[][] matriz) {
        for (int[] ints : matriz) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(ints[j] + "\t");
            }
            System.out.println();
        }
        System.out.println("\n");
    }

    /**
     * Calcula y llena una matriz con valores siguiendo un patrón especificado
     * de manera iterativa.
     * @param matriz La matriz a llenar.
     * @return La matriz llenada.
     */
    public static int[][] sectoresIterativo(int[][] matriz) {
        int n = matriz.length; // calculamos las filas para hacer la relación
        boolean flag = true; // bandera para saber si llenamos la columna o no
        int e = 0; // Indica la cantidad de espacios se deja al inicio y final de la columna
        int y = 1; // espacio entre columnas para el reflejo de la segunda mitad de la matriz
        // PRIMERA MITAD
        for (int j = 0; j < n; j++) {
            for (int i = e; i < (n - e); i++) {
                if (flag) {
                    if (j == (n - 1)) {
                        matriz[i][j] = 1;
                    } else {
                        matriz[i][j] = (int) Math.pow(2, (i + e));
                    }

                }
            }

            if (j != (n - 1)) {
                flag = !flag;
            }
            if (j < (n - 1) && flag) {
                e++;
            }
        }

        // SEGUNDA MITAD
        for (int j = n; j < matriz[0].length; j++) {
            for (int i = 0; i < n; i++) {
                matriz[i][j] = matriz[(n - 1) - i][j - y];
            }
            y += 2; // se hace cada iteración sin importar el flag
        }

        return matriz;
    }

    /**
     * Calcula y llena una matriz con valores siguiendo un patrón especificado
     * de manera recursiva.
     * @param arreglo La matriz a llenar.
     * @param i Índice de fila actual.
     * @param j Índice de columna actual.
     * @param e Cantidad de espacios al inicio y final de la columna.
     * @param n Tamaño de la primera mitad de la matriz.
     * @param y Espacio entre columnas para el reflejo de la segunda mitad.
     * @param flag Bandera para alternar entre llenar y no llenar la columna.
     * @return La matriz llenada.
     */
    public static int[][] sectoresRecursivo(int[][] arreglo, int i, int j, int e, int n, int y, boolean flag) {
        if (i == (arreglo.length - 1) && j == (arreglo[0].length - 1)) {
            arreglo[i][j] = 1;
            return arreglo;
        } else if (i < arreglo.length) {
            if (j < n) { // primera mitad
                if (i >= e && i < (n - e)) {
                    if (flag) {
                        if (j == (n - 1)) {
                            arreglo[i][j] = 1;
                        } else {
                            arreglo[i][j] = (int) Math.pow(2, (i + e));
                        }
                    }
                }
            } else { // segunda mitad
                arreglo[i][j] = arreglo[(n - 1) - i][j - y];
            }
            return sectoresRecursivo(arreglo, i + 1, j, e, n, y, flag);
        } else {
            if (j < n) { // primera mitad
                if (j != (n - 1)) {
                    flag = !flag;
                }
                if (j < (n - 1) && flag) {
                    e++;
                }
            } else { // segunda mitad
                y += 2;
            }
            return sectoresRecursivo(arreglo, 0, j + 1, e, n, y, flag);
        }
    }
}
