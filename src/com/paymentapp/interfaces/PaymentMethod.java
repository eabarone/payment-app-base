package com.paymentapp.interfaces;

public interface PaymentMethod {
     void ProcesarPago();
     String MostrarTipoPago();
     String MostrarDetalleTrans(double monto, String metodoPago);
}
