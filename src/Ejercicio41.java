import javax.swing.JOptionPane;
/*
 * Autores: Jose Alejandro Zambrano Jiménez, Juan Felipe López Castaño, Juan Pablo Loaiza Nieto
 * Ejercicio 4.1 Seguimiento 1
 * Análisis de Algoritmos 01-D
 */
public class Ejercicio41 {
    public static void main(String[] args) {
        int n;
        String input = JOptionPane.showInputDialog("Ingrese el valor de n (debe ser impar)");
        try {
            n = Integer.parseInt(input);
            if (n % 2 == 0) {
                System.err.println("El valor de n debe ser impar");
            } else {
                int[][] matrizIterativa = new int[n][2*n];
                //int[][] matrizRecursiva = new int[n][2*n];

                System.out.println("Para un n = " + n + "\n");

                // Tiempo de ejecución iterativo
                long inicioIterativo = System.nanoTime();
                matrizIterativa = sectoresIterativo(matrizIterativa);
                long finIterativo = System.nanoTime();
                long tiempoIterativo = finIterativo - inicioIterativo;
                imprimirMatriz(matrizIterativa);
                System.out.println("Tiempo de ejecución del método ITERATIVO (nano): " + tiempoIterativo + "\n");

                // Tiempo de ejecución recursivo
                // long inicioRecursivo = System.nanoTime();
                // llenarMatrizRecursivamente(matrizRecursiva, 0, 0, 0);
                // long finRecursivo = System.nanoTime();
                // long tiempoRecursivo = finRecursivo - inicioRecursivo;
                // mostrarMatriz(matrizRecursiva);
                // System.out.println("Tiempo de ejecución del método RECURSIVO (nano): " + tiempoRecursivo + "\n");

                // System.out.println("La diferencia de los algoritmos, respectivamente, es de: " + (tiempoRecursivo - tiempoIterativo) + " nanosegundos.");
            }
        } catch (NumberFormatException e) {
            System.err.println("El valor ingresado no es un número entero.");
        }
    }

    public static void imprimirMatriz(int[][] matriz){
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("\n");
    }

    public static int[][] sectoresIterativo(int[][] matriz) {
        int n = matriz.length; // calculamos las filas para hacer la relación
        boolean flag = true; // bandera para saber si llenamos la columna o no
        int e = 0; // indica la cantidad de espacios se deja al inicio y final de la columna
        int y = 1; // espacio entre columnas para el reflejo de la segunda mitad de la matriz
        for (int j = 0; j < n; j++) {
            for (int i = e; i < (n - e); i++) {
                if (j < n) { // si es la primer mitad de la matriz
                    if (flag == true) {
                        if (j == (n - 1)) {
                            matriz[i][j] = 1;
                        } else {
                            matriz[i][j] = (int) Math.pow(2, (i + e));
                        }
                    }
                }
            }

            if (j != (n - 1)) {
                flag = !flag;
            }
            if (j < (n - 1) && flag == true) {
                e++;
            }
        }
        
        // SEGUNDA MITAD
        for (int j = n; j < matriz[0].length; j++) {
            for (int i = 0; i < n; i++) {
                matriz[i][j] = matriz[(n-1)-i][j-y];
            }
            y+= 2; //se hace cada iteración sin importar el flag
        }

        e = 0;
        flag = true;
        return matriz;
    }

    // public static int[][] sectoresRecursivo(int[][] matriz) {
    //     int n = matriz.length; // calculamos las filas para hacer la relación
    //     boolean flag = true; // bandera para saber si llenamos la columna o no
    //     int e = 0; // indica la cantidad de espacios se deja al inicio y final de la columna
    //     return matriz;
    // }
}
