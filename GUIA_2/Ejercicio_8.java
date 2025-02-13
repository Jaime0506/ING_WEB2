import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

// Realizar un algoritmo que calcule la edad de una persona.

public class Ejercicio_8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese su año de nacimiento: ");
        int year = scanner.nextInt();

        System.out.println("Ingrese su mes de nacimiento (1-12): ");
        int month = scanner.nextInt();

        System.out.println("Ingrese su dia de nacimiento: ");
        int day = scanner.nextInt();

        LocalDate birthdate = LocalDate.of(year, month, day);
        LocalDate currentDate = LocalDate.now();

        int age = Period.between(birthdate, currentDate).getYears();

        System.out.println("Su edad es de: " + age + " años");
        scanner.close();
    }
}
