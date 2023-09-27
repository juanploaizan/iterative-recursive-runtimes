import java.util.ArrayList;

public class problema_4_4_recursivo {

    public static void main(String[] args) {
        int[][] matriz = {
                {4, 14, 27},
                {378, 13, 8},
                {19, 71, 45}
        };
        ArrayList<Integer> numSmith = new ArrayList<>();
        //Tiempo de ejecución Algoritmo recursivo
        long inicioRecursivo = System.nanoTime();
        numSmith = encontrarNumSmith(matriz,0,0,numSmith);
        System.out.println(numSmith);
        long finRecursivo = System.nanoTime();
        long tiempoTotalR = finRecursivo - inicioRecursivo;
        System.out.println("Tiempo de ejecucción del método RECURSIVO (nano): " + tiempoTotalR + "\n");

    }

    public static ArrayList<Integer> encontrarNumSmith(int[][] matriz,int i, int j, ArrayList<Integer> nums){
        if (i == matriz.length) {
            //Si se llega al final de las filas se retorna el arreglo
            return nums;
        }
        if (j == matriz[i].length) {
            //Si se llega al final de las columnas debe continuar con la fila siguiente
            return encontrarNumSmith(matriz,++i,0,nums);
        }

        //Se verifica que el numero no sea primo, si es primo continua
        if(isPrimo(matriz[i][j],0,1)){
            return encontrarNumSmith(matriz,i,++j,nums);
        }else{
            if(isSmithNumber(matriz[i][j])){
                nums.add(matriz[i][j]);
            }
        }
        return encontrarNumSmith(matriz,i,++j,nums);
    }

    static boolean isSmithNumber(int n){
        if(n<=1) return false;

        ArrayList<Integer> primeFactorsList = new ArrayList<>();
        primeFactors(n,2,primeFactorsList);

        // Encuentra la suma de los dígitos de los factores primos
        int sumOfPrimeDigits = primeFactorsList.stream().mapToInt(Integer::intValue).sum();

        // Encuentra la suma de los dígitos del número original
        int sumOfOriginalDigits = sumOfDigits(n,0);

        // Compara las sumas de dígitos
        return sumOfPrimeDigits == sumOfOriginalDigits;


    }
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
    public static boolean isPrimo(int num, int divisores,int i){
        if(i==num){
            return divisores == 1;
        } else if (num%i==0) {
            divisores++;
        }
        return isPrimo(num,divisores,++i);
    }

    public static int sumOfDigits(int n, int sum){
        if(n<=0){
            return sum;
        }else{
            sum += n % 10;
            n /= 10;
            return  sumOfDigits(n,sum);
        }
    }
}
