import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.IntStream;

// Realice un algoritmo que cree tres vectores de 10 posiciones, los lea y asigne sus
// valores a una matriz x(3,10). imprimir la matriz

public class Ejercicio_14 {
    public static void main(String[] args) {
        final int COLS = 10;
        final int ROWS = 3;

        Random random = new Random();

        Supplier<List<Integer>> generateVector = () -> IntStream.generate(() -> random.nextInt(100))
                .limit(COLS)
                .boxed()
                .toList();

        List<Integer> vector1 = generateVector.get();
        List<Integer> vector2 = generateVector.get();
        List<Integer> vector3 = generateVector.get();

        int[][] matrix = new int[ROWS][COLS];

        IntStream.range(0, COLS).forEach(j -> {
            matrix[0][j] = vector1.get(j);
            matrix[1][j] = vector2.get(j);
            matrix[2][j] = vector3.get(j);
        });

        System.out.println("Vector 1: " + vector1);
        System.out.println("Vector 2: " + vector2);
        System.out.println("Vector 3: " + vector3);

        System.out.println("\nMatriz generada: ");
        Arrays.stream(matrix).forEach(row -> 
            System.out.println(Arrays.toString(row))
        );
    }
}
