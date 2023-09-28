import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;


/*
Número hambriento:
el k-ésimo número hambriento es el más pequeño número natural n que cumple que 2^n
contiene los primeros k dígitos de Pi. Los primeros números hambrientos son: 5, 17, 74, 144,
 */
public class Problema_4_3 {

    public static void main(String[] args) {

      int[][] matriz = new int[][]{
                {1, 2, 74},
                {144, 2003, -1},
                {5, 17, 3}
      };

      //Digitos de PI
      String DIGITOS = "3141592653";

      //Tiempo de ejecución Algoritmo interativo
      long inicioIterativo = System.nanoTime();
      ArrayList<Integer> numerosHambrientos = encontrarNumerosHambrientos(matriz, DIGITOS);
      long finIterativo = System.nanoTime();
      long tiempoTotal = finIterativo-inicioIterativo;
      System.out.println("Los números hambrientos encontrados en la matriz son: " + numerosHambrientos);
      System.out.println("Tiempo de ejecucción del método ITERATIVO (nano): " + tiempoTotal + "\n");

      //Tiempo de ejecución Algoritmo recursivo
      long inicioRecursivo = System.nanoTime();
      ArrayList<Integer> numerosHambrientosR = new ArrayList<>();
      numerosHambrientosR = encontrarHambrientosRecursivo(matriz,DIGITOS,0,0,numerosHambrientosR);
      long finRecursivo = System.nanoTime();
      long tiempoTotalR = finRecursivo - inicioRecursivo;
      System.out.println("Los numeros ambrientos encontrados en la matriz son: " + numerosHambrientosR);
      System.out.println("Tiempo de ejecucción del método RECURSIVO (nano): " + tiempoTotalR + "\n");

    }

    /**
     * Metodo para recorrer la matriz
     *
     */
    public static ArrayList<Integer> encontrarNumerosHambrientos(int[][] matriz, String DIGITOS) {
        //Arreglo para retornar los numero hambrientos encontrados
        ArrayList<Integer> numerosHambrientos = new ArrayList<>();

        //Ciclo para interar la matriz
        for (int i = 0; i < matriz.length ; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                int num = matriz[i][j];
                boolean numHambriento = isHambriento(num,DIGITOS); //Llamado a la funcion
                if(numHambriento){
                    numerosHambrientos.add(num);
                }
            }
        }

        return numerosHambrientos;
    }

    public static ArrayList<Integer> encontrarHambrientosRecursivo(int[][] matriz, String DIGITOS, int i, int j, ArrayList<Integer> nums){
        if (i == matriz.length) {
            // Si hemos alcanzado el final de la matriz, salimos de la recursión.
            return nums;
        }
        if (j == matriz[i].length) {
            return encontrarHambrientosRecursivo(matriz,DIGITOS,++i,0,nums);
        }
        boolean numHambrientos = isHambriento(matriz[i][j],DIGITOS);
        if(numHambrientos){
            nums.add(matriz[i][j]);
        }
        return encontrarHambrientosRecursivo(matriz,DIGITOS,i,++j,nums);
    }
    /**
     * Funcion para determinar si los numeros son o no hambrientos
     */
    public static boolean isHambriento(int num,String DIGITOS){
        // Si el número es negativo, no es hambriento
        if (num < 0) {
            return false;
        }

        // Acotar la cantidad de digitos
        String tamanioNum = Integer.toString(num);
        String digitosPi = DIGITOS.substring(0,tamanioNum.length());

        // sacar la k-esima potencia de dos
        double numPotencia = Math.pow(2,num);

        // Pasar al String para poder comparar sus digitos
        String digitosPotencia = Double.toString(numPotencia);

        //Mira si los digitos de PI acotados estan en los digitos de la potencia
        if(digitosPotencia.contains(digitosPi)){
            return true;
        }else{
            return false;
        }
    }
}

