package com.paymentapp.modelo;

import com.paymentapp.interfaces.DiscountStrategy;
import com.paymentapp.interfaces.PaymentMethod;
import com.paymentapp.interfaces.Refundable;

public class DefaultPayment implements PaymentMethod, Refundable, DiscountStrategy {
    private double monto;

    public DefaultPayment (double monto){
        this.monto = monto;
    }

    @Override
    public void ProcesarPago() {

    }

    @Override
    public String MostrarTipoPago() {
        return "";
    }

    @Override
    public String MostrarDetalleTrans(double monto, String metodoPago) {
        return "";
    }

    @Override
    public double AplicarDescuento(double monto) {
        return 0;
    }

    @Override
    public double ProcesarReembolso() {
        return 0;
    }

    @Override
    public String DetalleReembolso() {
        return "";
    }
}