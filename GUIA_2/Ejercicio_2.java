import java.util.Scanner;
import java.util.function.Function;

// Una tienda ofrece un descuento del 15% sobre el total de la compra y un cliente desea
// saber cuánto deberá pagar finalmente por su compra.

public class Ejercicio_2 {
    private static final double PERCENT_DISCOUNT = 0.15;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite el total comprado x el cliente");
        Double total = scanner.nextDouble();
        
        Function<Double, Double> calculateDiscount = (totalBuy) -> totalBuy * PERCENT_DISCOUNT;
        Function<Double, Double> calculateTotal = (totalBuy) -> totalBuy - calculateDiscount.apply(totalBuy);

        double totalWithDiscount = calculateTotal.apply(total);

        String message = String.format("Total de la compra sin descuento: %.2f %n Descuento total: %.2f %n Total a cancelar: %.2f", total, calculateDiscount.apply(total), totalWithDiscount);
        System.out.println(message);

        scanner.close();
    }
}
