import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.IntStream;

// Realice un algoritmo que cree una matriz m(4,5), la llene y calcule la posición de un valor
// en una matriz ingresado por el usuario

public class Ejercicio_13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final int ROWS = 4, COLS =5;

        List<List<Integer>> matrix = IntStream.range(0, ROWS)
            .mapToObj(i -> IntStream.range(0, COLS)
                .mapToObj(j -> {
                    System.out.println("Ingrese el valor para la posicion [" + i + "][" + j + "]");
                    return scanner.nextInt();
                })
                .toList()
            ).toList();
        
        System.out.println("Ingrese el numero que desea buscar en la matriz");
        int target = scanner.nextInt();

        Optional<String> position = IntStream.range(0, ROWS)
            .boxed()
            .flatMap(i -> IntStream.range(0, COLS)
                .mapToObj(j -> matrix.get(i).get(j) == target ? "[" + i + "," + j + "]": null))
            .filter(pos -> pos != null)
            .findFirst();

        System.out.println("Matriz ingresada: ");
        matrix.forEach(System.out::println);
                
        System.out.println(
            position.map(p -> "El numero " + target + " se encuentra en la posicion " + p)
                .orElse("El numero no se encuentra en la matriz")
        );

        scanner.close();
    }
}
