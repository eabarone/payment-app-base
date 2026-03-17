package com.paymentapp.interfaces;

public class Descuento {


    public static final DiscountStrategy DesEstudiantes =
            monto -> monto * 0.85;

    public static final DiscountStrategy DesPromocion =
            monto -> monto * 0.90;

    public static final DiscountStrategy DesClienteFijo=
            monto -> monto * 0.80;

    public static final DiscountStrategy ClienteNormal =
            monto -> monto * 0.00;


    public static double applyAndShow(double monto, DiscountStrategy strategy, String discountName) {
        double montoFinal = strategy.AplicarDescuento(monto);
        double ahorro = monto - montoFinal;
        
        System.out.println("----------------------------------");
        System.out.println("Descuento        : " + discountName);
        System.out.println("Monto original   : $" + monto);
        System.out.println("Ahorro           : $" + ahorro);
        System.out.println("Monto final      : $" + montoFinal);
        System.out.println("----------------------------------");

        return montoFinal;
    }


}