import java.util.Scanner;
import com.paymentapp.interfaces.*;
import com.paymentapp.interfaces.Descuento;
import com.paymentapp.payment.*;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Ingresar el valor de la compra
        System.out.print("Ingrese el valor de la compra: $");
        double compra = scanner.nextDouble();
        scanner.nextLine();

        // 2. Seleccionar el método de pago
        System.out.println("\n--- Método de pago ---");
        System.out.println("1. Tarjeta de crédito");
        System.out.println("2. PayPal");
        System.out.println("3. Criptomoneda");
        System.out.print("Seleccione: ");
        int opcionPago = scanner.nextInt();
        scanner.nextLine();

        PaymentMethod metodo;
        switch (opcionPago) {
            case 1:
                System.out.print("Número de tarjeta: ");
                String numero = scanner.nextLine();
                System.out.print("Titular: ");
                String titular = scanner.nextLine();
                metodo = new CreditCardPayment(numero, titular, compra);
                break;
            case 2:
                System.out.print("Email de PayPal: ");
                String email = scanner.nextLine();
                metodo = new PaypalPayment(email, compra);
                break;
            case 3:
                System.out.print("Dirección de billetera: ");
                String billetera = scanner.nextLine();
                System.out.print("Tipo de cripto (BTC/ETH/USDT): ");
                String cripto = scanner.nextLine();
                metodo = new CryptoPayment(billetera, cripto, compra);
                break;
            default:
                System.out.println("Opción inválida.");
                scanner.close();
                return;
        }

        // 3. Seleccionar el tipo de descuento
        System.out.println("\n--- Descuentos ---");
        System.out.println("1. Estudiante        (15% off)");
        System.out.println("2. Promoción         (10% off)");
        System.out.println("3. Cliente frecuente (20% off)");
        System.out.println("4. Sin descuento");
        System.out.print("Seleccione: ");
        int opcionDescuento = scanner.nextInt();

        DiscountStrategy estrategia;
        String nombreDescuento;
        switch (opcionDescuento) {
            case 1:
                estrategia = Descuento.DesEstudiantes;
                nombreDescuento = "Estudiantes";
                break;
            case 2:
                estrategia = Descuento.DesPromocion;
                nombreDescuento = "Promoción";
                break;
            case 3:
                estrategia = Descuento.DesClienteFijo;
                nombreDescuento = "Cliente frecuente";
                break;
            default:
                estrategia = Descuento.ClienteNormal;
                nombreDescuento = "Sin descuento";
                break;
        }

        // 4. Procesar el pago
        metodo.ProcesarPago();

        // 5. Mostrar el resultado final
        double montoFinal = Descuento.applyAndShow(compra, estrategia, nombreDescuento);
        System.out.println(metodo.MostrarDetalleTrans(montoFinal, metodo.MostrarTipoPago()));

        scanner.close();
    }
}