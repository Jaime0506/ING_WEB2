import java.util.Scanner;
import java.util.function.BiFunction;

// Un maestro desea saber qué porcentaje de hombres y que porcentaje de mujeres hay
// en un grupo de estudiantes

public class Ejercicio_7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Cuantos estudiantes hay en el salon?");
        int total = scanner.nextInt();

        System.out.println("Cuantos hombres hay");
        int menTotal = scanner.nextInt();

        System.out.println("Cuantas mujeres hay");
        int womenTotal = scanner.nextInt();

        BiFunction<Integer, Integer, Double> calculatePercent = (cant, totalCant) -> {
            return ((double) cant / totalCant) * 100;
        };

        double percentMen = calculatePercent.apply(menTotal, total);
        double percetWomen = calculatePercent.apply(womenTotal, total);

        String message = String.format(
            "El salon en total tiene: %d de estudiantes %n De los cuales %d son hombres %n y %d son mujeres %n Representando respectivamente un %.2f%% y un %.2f%% de el total",
            total, menTotal, womenTotal, percentMen, percetWomen
        );

        System.out.println(message);

        scanner.close();
    }
}
