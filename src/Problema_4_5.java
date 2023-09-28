import javax.swing.JOptionPane;

/*
 * Autores: Jose Alejandro Zambrano Jiménez, Juan Felipe López Castaño, Juan Pablo Loaiza Nieto
 * Ejercicio 4.5 Seguimiento 1: Llenar la matriz con unos y ceros de acuerdo al patrón mostrado en el documento
 * Análisis de Algoritmos 01-D
 */
public class Problema_4_5 {
    public static void main(String[] args) {
        int n;
        String input = JOptionPane.showInputDialog("Ingrese el valor de n (que sea mayor a 5 e impar)");
        try {
            n = Integer.parseInt(input);
            if (n % 2 == 0 || n < 5) {
                System.err.println("El valor de n debe ser impar y mayor a 5");
            } else {
                int[][] matrizIterativa = new int[n][n - 2];
                int[][] matrizRecursiva = new int[n][n - 2];

                System.out.println("Para un n = " + n + "\n");

                // // Tiempo de ejecución iterativo
                long inicioIterativo = System.nanoTime();
                matrizIterativa = llenarPatron(matrizIterativa);
                long finIterativo = System.nanoTime();
                long tiempoIterativo = finIterativo - inicioIterativo;
                imprimirMatriz(matrizIterativa);
                System.out.println("Tiempo de ejecución del método ITERATIVO (nano): " +
                        tiempoIterativo + "\n");

                // Tiempo de ejecución recursivo
                long inicioRecursivo = System.nanoTime();
                matrizRecursiva = llenarPatronRecursivo(matrizRecursiva, 0, 0, matrizRecursiva.length,
                        (matrizRecursiva.length - 3) / 2, 0);
                long finRecursivo = System.nanoTime();
                long tiempoRecursivo = finRecursivo - inicioRecursivo;
                imprimirMatriz(matrizRecursiva);
                System.out.println("Tiempo de ejecución del método RECURSIVO (nano): " + tiempoRecursivo + "\n");

                System.out.println("La diferencia de los algoritmos, respectivamente, es de: "
                        + Math.abs(tiempoRecursivo - tiempoIterativo) + " nanosegundos.");
            }
        } catch (NumberFormatException e) {
            System.err.println("El valor ingresado no es un número entero.");
        }
    }

    public static void imprimirMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("\n");
    }

    public static int[][] llenarPatron(int[][] matriz) {
        int n = matriz.length;
        int a = (n - 3) / 2;
        int y = 0; // espacio entre columnas para el reflejo de la segunda mitad de la matriz

        for (int j = 0; j < (n - 2); j++) {
            for (int i = 0; i < n; i++) {
                if (j <= (matriz[0].length) / 2) {
                    if (j == 0) {
                        matriz[i][j] = 1;
                    } else { // de ceros el patrón calculado con el a y el resto de unos
                        if (i >= a && i < (n - a)) {
                            matriz[i][j] = 0;
                        } else {
                            matriz[i][j] = 1;
                        }
                    }
                } else {
                    matriz[i][j] = matriz[i][j - (2 + y)];
                }

            }
            // aumento del y para el reflejo de columnas
            if (j > (matriz[0].length) / 2) {
                if (j != (n - 2)) {
                    y += 2;
                }
            }
            // disminución del a para saber dónde van los ceros
            if (j != 0 && j < (matriz[0].length) / 2) {
                a -= 1;
            }
        }
        return matriz;
    }

    public static int[][] llenarPatronRecursivo(int[][] arreglo, int i, int j, int n, int a, int y) {

        if (i == arreglo.length - 1 && j == arreglo[0].length - 1) {
            arreglo[i][j] = 1;
            return arreglo;

        } else if (i < arreglo.length) {
            if (j <= (arreglo[0].length) / 2) {
                if (j == 0) {
                    arreglo[i][j] = 1;
                } else { // de ceros el patrón calculado con el a y el resto de unos
                    if (i >= a && i < (n - a)) {
                        arreglo[i][j] = 0;
                    } else {
                        arreglo[i][j] = 1;
                    }
                }
            } else {
                arreglo[i][j] = arreglo[i][j - (2 + y)];
            }
            return llenarPatronRecursivo(arreglo, i + 1, j, n, a, y);

        } else {

            if (j > (arreglo[0].length) / 2) {
                if (j != (n - 2)) {
                    y += 2;
                }
            }

            if (j != 0 && j < (arreglo[0].length) / 2) {
                a -= 1;
            }
            return llenarPatronRecursivo(arreglo, 0, j + 1, n, a, y);
        }

    }
}
