import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Realice un algoritmo que calcule las notas de un grupo de 25 alumnos, guardando
// nombre, apellido y 4 notas por cada uno, calcular promedio, e imprimir todos los
// datos en pantalla. utilizando matrices.

public class Ejercicio_15 {
    public static void main(String[] args) {
        final int NUM_STUDENTS = 25;
        final int NUM_NOTES = 4;

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String[][] students = new String[NUM_STUDENTS][2];
        double[][] notes = new double[NUM_STUDENTS][NUM_NOTES];

        IntStream.range(0, NUM_STUDENTS).forEach(i -> {
            System.out.println("Ingrese el nombre del estudiante " + (i + 1) + ": ");
            students[i][0] = scanner.next();  
            scanner.nextLine(); // Evitar problemas con nombres compuestos

            System.out.println("Ingrese el apellido del estudiante " + (i + 1) + ": ");
            students[i][1] = scanner.nextLine(); 

            IntStream.range(0, NUM_NOTES).forEach(j -> {
                notes[i][j] = 1 + (4 * random.nextDouble()); // Notas aleatorias entre 1 y 5
            });
        });

        Function<double[], Double> calculateAverage = 
            notesStudent -> Arrays.stream(notesStudent).average().orElse(0.0);

        System.out.println("\n ======== Notas de los estudiantes =======");

        IntStream.range(0, NUM_STUDENTS).forEach(i -> {
            String fullName = students[i][0] + " " + students[i][1];
            double average = calculateAverage.apply(notes[i]);

            String notesString = Arrays.stream(notes[i])
                    .mapToObj(n -> String.format("%.2f", n)) // Redondear notas a 2 decimales
                    .collect(Collectors.joining(", "));

            System.out.printf("Alumno: %s | Notas: %s | Promedio: %.2f%n",
                    fullName, notesString, average); // Agregar %n para salto de línea
        });

        scanner.close();
    }
}
