import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

// Realice un algoritmo que cree un vector de 16 posiciones llenarlo y partirlo en dos de 8
// posiciones

public class Ejercicio_12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = IntStream.range(0, 16)
                .mapToObj(i -> {
                    System.out.println("Ingrese el elemento " + (i + 1) + " :");
                    return scanner.nextInt();
                }).toList();

        List<Integer> firstHalf = numbers.stream().limit(8).toList();
        List<Integer> secondHalf = numbers.stream().skip(8).toList();

        System.out.println("Lista completa: " + numbers);
        System.out.println("Primera mitad: " + firstHalf);
        System.out.println("Segunda Mitad: " + secondHalf);

        scanner.close();
    }
}
