import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Ejercicio_19 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la cantidad de números en la lista: ");
        int amount = scanner.nextInt();

        System.out.println("Ingrese los " + amount + " números:");
        List<Integer> numbers = IntStream.range(0, amount)
                .mapToObj(i -> scanner.nextInt())
                .toList();
                
        int positives = (int) numbers.stream().filter(n -> n >= 0).count();

        System.out.println("Vector original: " + numbers);
        System.out.println("cantidad de numeros positivos (incluyendo el 0): " + positives);

        scanner.close();
    }
}
