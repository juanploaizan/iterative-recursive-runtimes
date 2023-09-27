import java.util.ArrayList;
import java.util.List;

public class problema_4_4 {

    public static void main(String[] args) {
        int[][] matriz = {
                {4, 14, 27},
                {378, 13, 8},
                {19, 71, 45}
        };
        //Tiempo de ejecución Algoritmo interativo
        long inicioIterativo = System.nanoTime();
        ArrayList<Integer> nums = encontrarNumSmith(matriz);
        System.out.println("Los numeros de Smith encontrados fueron: " + nums);
        long finIterativo = System.nanoTime();
        long tiempoTotal = finIterativo-inicioIterativo;
        System.out.println("Tiempo de ejecucción del método ITERATIVO (nano): " + tiempoTotal + "\n");

    }

    public static ArrayList<Integer> encontrarNumSmith(int[][] matriz){
        ArrayList<Integer> numSmith = new ArrayList<>();
        for (int i = 0; i<matriz.length; i++){
            for (int j = 0; j<matriz.length;j++){
                if(isPrime(matriz[i][j])){
                    continue;
                }
                if(isSmithNumber(matriz[i][j])){
                    numSmith.add(matriz[i][j]);
                }
            }
        }
        return numSmith;
    }
    // Función para verificar si un número es un número de Smith
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

    // Función para verificar si un número es primo
    static boolean isPrime(int num) {
        // Los números menores o iguales a 1 no son primos
        if (num <= 1) {
            return false;
        }
        // Comprobamos si el número es divisible por algún número desde 2 hasta la raíz cuadrada de 'numero'
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                // Si es divisible, no es primo
                return false;
            }
        }
        // Si no se encontró ningún divisor, es primo
        return true;
    }

    // Función para encontrar la suma de los dígitos de un número
    static int sumOfDigits(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }



}
