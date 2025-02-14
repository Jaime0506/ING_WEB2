import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Ejercicio_18 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar cantidad de números y el rango
        System.out.print("Ingrese la cantidad de números aleatorios: ");
        int amount = scanner.nextInt();

        System.out.print("Ingrese el valor mínimo del rango: ");
        int min = scanner.nextInt();

        System.out.print("Ingrese el valor máximo del rango: ");
        int max = scanner.nextInt();

        List<Integer> numbers = new Random().ints(amount, 1, 50)
                                            .boxed()
                                            .toList();

        System.out.println("Lista generada: " + numbers);

        TriFunction<Integer, Integer, List<Integer>, List<Integer>> eRange = (a, b, list) ->
            list.stream()
                .filter(n -> n >= a && n <= b)
                .toList();
        
        List<Integer> result = eRange.apply(min, max, numbers);
        
        System.out.println("Números en el rango [" + min + ", " + max + "]: " + result);

        scanner.close();
    }

    @FunctionalInterface
    interface TriFunction<T, U, V, R> {
        R apply(T t, U u, V v);
    }
}
