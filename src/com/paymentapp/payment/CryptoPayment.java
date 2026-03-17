package com.paymentapp.payment;

import com.paymentapp.interfaces.PaymentMethod;

public class CryptoPayment implements PaymentMethod {
    private String walletAddress;
    private String cryptoType;
    private double amount;
    private double exchangeRate;

    public CryptoPayment(String walletAddress, String cryptoType, double amount) {
        this.walletAddress = walletAddress;
        this.cryptoType = cryptoType;
        this.amount = amount;

        switch(cryptoType.toUpperCase()) {
            case "BTC": this.exchangeRate = 65000.0; break;
            case "ETH": this.exchangeRate = 3500.0; break;
            default: this.exchangeRate = 1.0;
        }
    }

    @Override
    public void ProcesarPago() {
        double cryptoAmount = amount / exchangeRate;
        System.out.println("   Procesando pago con Criptomoneda");
        System.out.println("   Tipo: " + cryptoType);
        System.out.println("   Wallet: " + walletAddress.substring(0, 6) + "...");
        System.out.println("   Monto USD: $" + String.format("%.2f", amount));
        System.out.println("   Monto Crypto: " + String.format("%.6f", cryptoAmount) + " " + cryptoType);
    }

    @Override
    public String MostrarTipoPago() {
        return "Criptomoneda (" + cryptoType + ")";
    }

    @Override
    public String MostrarDetalleTrans(double monto, String metodoPago) {
        double cryptoAmount = monto / exchangeRate;
        return String.format("  Transacción: %s - Wallet: %s... - Monto: %.6f %s (USD: $%.2f)",
                metodoPago, walletAddress.substring(0, 6), cryptoAmount, cryptoType, monto);
    }

    // Getters
    public double getAmount() {
        return amount;
    }
}