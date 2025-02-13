import java.util.Scanner;
import java.util.function.Function;

// Un vendedor recibe un sueldo base más un 10% extra por comisión de sus ventas, el
// vendedor desea saber cuánto dinero obtendrá por concepto de comisiones por las tres
// ventas que realiza en el mes y el total que recibirá en el mes tomando en cuenta su
// sueldo base y comisiones.

class Ejercicio_1 {
    private static final Double PERCENTAGE_COMMISSION = 0.1;
    private static final Double PERCENTAGE_TAXES = 0.19;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el sueldo base del empleado: ");
        double sueldoBase = scanner.nextDouble();

        System.out.println("Digite el total de ventas realizadas");
        double totalVentas = scanner.nextDouble();

        Function<Double, Double> calculateCommission = (sales) -> sales * PERCENTAGE_COMMISSION;
        Function<Double, Double> calculateGrossSalary = (base) -> base + calculateCommission.apply(totalVentas);
        Function<Double, Double> calculateNetSalary = (gross) -> gross - (gross * PERCENTAGE_TAXES); 

        double commission = calculateCommission.apply(totalVentas);
        double grossSalary = calculateGrossSalary.apply(sueldoBase);
        double netSalary = calculateNetSalary.apply(grossSalary);

        String result = String.format(
            "\nSueldo base: %.2f\nTotal de ventas: %.2f\nComisión de ventas (%.0f%%): %.2f\nSueldo Bruto: %.2f\nSueldo Neto: %.2f", 
            sueldoBase, totalVentas, PERCENTAGE_COMMISSION * 100, commission, grossSalary, netSalary
        );

        System.out.println(result);
        scanner.close();
    }
}