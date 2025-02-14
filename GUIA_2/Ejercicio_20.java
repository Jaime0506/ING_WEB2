import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Ejercicio_20 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la cantidad de números en la lista: ");
        int amount = scanner.nextInt();

        System.out.println("Ingrese los " + amount + " números:");
        List<Integer> numbers = IntStream.range(0, amount)
                .mapToObj(i -> scanner.nextInt())
                .toList();

        System.out.println("Indique el argumento");
        int argument = scanner.nextInt();

        List<Integer> result = numbers.stream()
                .filter(n -> n <= argument)
                .toList();

        System.out.println("Matriz original: " + numbers);
        System.out.println("Matriz filtrada: " + result);

        scanner.close();
    }
}
