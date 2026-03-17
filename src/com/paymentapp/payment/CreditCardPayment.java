package com.paymentapp.payment;

import com.paymentapp.interfaces.PaymentMethod;

public class CreditCardPayment implements PaymentMethod {
    private String cardNumber;
    private String cardHolder;
    private double amount;  // Almacenar el monto a pagar
    private String lastFourDigits;

    public CreditCardPayment(String cardNumber, String cardHolder, double amount) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.amount = amount;
        // Guardar últimos 4 dígitos para mostrar
        if (cardNumber != null && cardNumber.length() >= 4) {
            this.lastFourDigits = cardNumber.substring(cardNumber.length() - 4);
        }
    }

    @Override
    public void ProcesarPago() {
        // Aquí iría la lógica real de procesamiento
        System.out.println("   Procesando pago con Tarjeta de Crédito");
        System.out.println("   Titular: " + cardHolder);
        System.out.println("   Tarjeta: ****" + lastFourDigits);
        System.out.println("   Monto: $" + String.format("%.2f", amount));
    }

    @Override
    public String MostrarTipoPago() {
        return "Tarjeta de Crédito";
    }

    @Override
    public String MostrarDetalleTrans(double monto, String metodoPago) {
        return String.format(" Transacción: %s - Tarjeta ****%s - Titular: %s - Monto: $%.2f",
                metodoPago, lastFourDigits, cardHolder, monto);
    }

    // Getters necesarios para otros equipos
    public double getAmount() {
        return amount;
    }
}