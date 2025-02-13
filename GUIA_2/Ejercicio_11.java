import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

// Realice un algoritmo que lea una secuencia de 20 números almacenarlos en un vector
// y mostrar la posición donde se encuentra el mayor valor leído

public class Ejercicio_11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = IntStream.range(0, 20)
                .mapToObj(i -> {
                    System.out.println("Ingrese el elemento " + (i + 1) + ":");
                    return scanner.nextInt();
                }).toList();

        int maxValue = numbers.stream().max(Integer::compareTo).orElseThrow();

        int maxPosition = IntStream.range(0, numbers.size())
                .filter(i -> numbers.get(i) == maxValue)
                .findFirst()
                .orElse(-1);
                


        System.out.println("Numeros ingresados: " + numbers);
        System.out.println("Numero mayor: " + numbers.get(maxPosition) + " en la posicion: " + maxPosition);

        scanner.close();
    }
}
