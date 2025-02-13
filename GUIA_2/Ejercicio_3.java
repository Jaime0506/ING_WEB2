import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;

// Un aprendiz desea saber cuál será su calificación final en la materia de Algoritmos.
// Dicha calificación se compone de los siguientes porcentajes:
// . 55% del promedio de sus tres calificaciones parciales.
// . 30% de la calificación del examen final.
// . 15% de la calificación de un trabajo final.

public class Ejercicio_3 {
    private static final double PERCENT_WORKS = 0.55;
    private static final double PERCENT_FINAL_EXAM = 0.3;
    private static final double PERCENT_FINAL_WORK = 0.15;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Double> qualifications = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            System.out.println("Digite la nota parcial numero " + (i + 1) + " del 0 a 5");
            double note = scanner.nextDouble();

            qualifications.add(note);
        }

        System.out.println("Digite la nota de su examen final");
        double quaFinalExam = scanner.nextDouble();

        System.out.println("Digite la nota de su trabajo final");
        double quaFinalWork = scanner.nextDouble();

        Function<List<Double>, Double> calculateAverageThree = (listQualifications) -> {
            double totalQualifications = listQualifications.stream().reduce(0.0, Double::sum);
            double average = qualifications.isEmpty() ? 0 : totalQualifications / qualifications.size();

            return average;
        };

        BiFunction<Double, Double, Double> calculatePercent = (percent, value) -> {
            return percent * value;
        };

        double averageThree = calculateAverageThree.apply(qualifications);

        double quaParcial = calculatePercent.apply(PERCENT_WORKS, averageThree);
        double finalExam = calculatePercent.apply(PERCENT_FINAL_EXAM, quaFinalExam);
        double finalWork = calculatePercent.apply(PERCENT_FINAL_WORK, quaFinalWork);

        double finalQualification = quaParcial + finalExam + finalWork;

        String message = String.format(
                "El promedio de calificaciones parciales fue: %.2f con un 55%% ponderacion para un total de: %.2f %n El 30%% de su examen final es de: %.2f %n El 15%% de su trabajo final es de: %.2f %n Obteniendo una nota final de: %.2f",
                averageThree, quaParcial, finalExam, finalWork, finalQualification);

        System.out.println(message);
        scanner.close();
    }
}
