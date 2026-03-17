package com.paymentapp.interfaces;
@FunctionalInterface
public interface DiscountStrategy {
    double AplicarDescuento(double monto);
}
