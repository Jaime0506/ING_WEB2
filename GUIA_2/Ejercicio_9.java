import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Realice un algoritmo que cree un vector de longitud n donde n lo define el usuario
// calcula número mayor y meno

public class Ejercicio_9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite el tamaño del vector (+0)");
        int size = scanner.nextInt();

        List<Integer> numbers = IntStream.range(0, size)
                .mapToObj(i -> {
                    System.out.println("Elemento " + (i + 1) + ": ");
                    return scanner.nextInt();
                }).collect(Collectors.toList());

        int max = numbers.stream().max(Integer::compareTo).orElseThrow();
        int min = numbers.stream().min(Integer::compareTo).orElseThrow();

        System.out.println("Vector ingresado: " + numbers);
        System.out.println("Numero mayor: " + max);
        System.out.println("Numero menor: " + min);

        scanner.close();
    }
}
