public class problema_4_1 {

    /*
    Llenar un sector de acuerdo a la estructura presentada. Para este caso el valor de n es 5.
    Pero debe funcionar para diferentes tamaños de entrada. Se debe realizar de forma recursiva e iterativa.
     */

    public static void main(String[] args) {
        int n = 5;
        int[][] matriz = new int[n][2*n];
        llenarMatrizIterativamente(matriz);
        llenarMatrizRecursivamente(matriz, n, 0, 0);
    }

    private static void llenarMatrizIterativamente(int[][] matriz) {

        for (int i=0; i<matriz.length; i++) {
            for (int j=0; j<matriz[j].length; j++) {
                if (i==0 || i==matriz.length-1) {
                    matriz[i][j] = 1;
                } else if (j==0 || j==matriz[j].length-1) {
                    matriz[i][j] = 1;
                } else {
                    matriz[i][j] = 0;
                }
            }
        }

    }

    private static void llenarMatrizRecursivamente(int[][] matriz, int n, int i, int i1) {
    }
}