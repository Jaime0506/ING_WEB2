import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Ejercicio_17 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        Function<Integer, List<Integer>> generateList = (size) -> 
            IntStream.generate(() -> random.nextInt(100) + 1)
                .limit(size)
                .boxed()
                .toList();

        Function<List<Integer>, List<Double>> divideListByTwo = (list) ->
            list.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n / 2.0)
                .toList();

        System.out.println("Ingrese el tamaño de la lista: ");
        int size = scanner.nextInt();

        List<Integer> numbers = generateList.apply(size);
        List<Double> result = divideListByTwo.apply(numbers);

        System.out.println("Matriz original: " + numbers);
        System.out.println("\nMatriz transformada: " + result);

        scanner.close();
    }
}
