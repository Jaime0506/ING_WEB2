import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

// Realice un algoritmo que almacene 15 valores y los ordena en orden inverso

public class Ejercicio_10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = IntStream.range(0, 15)
                .mapToObj(i -> {
                    System.out.println("Elemento " + (i + 1) + ": ");
                    return scanner.nextInt();
                }).toList();

        List<Integer> reversedNumbers = IntStream.range(0, numbers.size())
                .mapToObj(i -> {
                    return numbers.get(numbers.size() - 1 - i);
                }).toList();

        System.out.println("Valores originales: " + numbers);
        System.out.println("Valores en orden inverso: " + reversedNumbers);

        scanner.close();
    }
}
