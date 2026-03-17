package com.paymentapp.payment;

import com.paymentapp.interfaces.PaymentMethod;
import com.paymentapp.interfaces.Refundable;

public class PaypalPayment implements PaymentMethod, Refundable {
    private String email;
    private double amount;
    private double refundedAmount;

    public PaypalPayment(String email, double amount) {
        this.email = email;
        this.amount = amount;
        this.refundedAmount = 0.0;
    }

    @Override
    public void ProcesarPago() {
        System.out.println("   Procesando pago con PayPal");
        System.out.println("   Cuenta: " + email);
        System.out.println("   Monto: $" + String.format("%.2f", amount));
    }

    @Override
    public String MostrarTipoPago() {
        return "PayPal";
    }

    @Override
    public String MostrarDetalleTrans(double monto, String metodoPago) {
        return String.format(" Transacción: %s - Email: %s - Monto: $%.2f",
                metodoPago, email, monto);
    }

    @Override
    public double ProcesarReembolso() {
        refundedAmount = amount * 0.5;
        System.out.println("   Procesando reembolso para PayPal: " + email);
        System.out.println("   Monto a reembolsar: $" + String.format("%.2f", refundedAmount));
        return refundedAmount;
    }

    @Override
    public String DetalleReembolso() {
        return String.format(" Reembolso procesado para %s - Monto reembolsado: $%.2f - Saldo restante: $%.2f",
                email, refundedAmount, amount - refundedAmount);
    }

    // Getters
    public double getAmount() {
        return amount;
    }
}