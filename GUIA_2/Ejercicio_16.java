import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

// Cree una función que cuente el número de veces que aparece x en una lista. Desarrolle
// dos versiones de la función: una usando filter y otra usando reduce y map.

public class Ejercicio_16 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        BiFunction<List<Integer>, Integer, Long> countUsingFilter = (list, x) -> 
            list.stream()
                .filter(num -> num.equals(x))
                .count();
        
        BiFunction<List<Integer>, Integer, Long> countUsingReduce = (list, x) -> 
            list.stream()
                .reduce(0L, (count, num) -> num.equals(x) ? count + 1 : count, Long::sum);

        BiFunction<List<Integer>, Integer, Integer> countUsingMapReduce = (list, x) -> 
            list.stream()
                .map(num -> num.equals(x) ? 1 : 0)
                .reduce(0, Integer::sum);

        System.out.println("Digite la cantidad del vector a generar");
        int size = scanner.nextInt();

        List<Integer> numbers = IntStream.generate(() -> random.nextInt(11))
                                .limit(size)
                                .boxed()
                                .toList();

        System.out.println("Vector generado: " + numbers);
        System.out.println("Digite el numero a contar");
        int x = scanner.nextInt();

        Long xUsingFilter = countUsingFilter.apply(numbers, x);
        Long xUsingReduce = countUsingReduce.apply(numbers, x);
        Integer xUsingMap = countUsingMapReduce.apply(numbers, x);

        System.out.println("El numero " + x + " fue encontrado: ");
        System.out.println("" + xUsingFilter + " veces usando Filter");
        System.out.println("" + xUsingReduce + " veces usando Reduce");
        System.out.println("" + xUsingMap + " veces usando Map");
                
        scanner.close();
    }
}