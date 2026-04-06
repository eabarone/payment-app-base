# 🏦 Parcial — Sistema de pagos `payment-app-base`

## Información General

| Campo              | Detalle                                                        |
|--------------------|----------------------------------------------------------------|
| **Materia**        | Programación III                                               |
| **Duración**       | 2 horas                                                        |
| **Modalidad**      | Individual — sobre código existente                            |
| **Entregable**     | Proyecto compilable y funcional con todos los puntos resueltos |

---

## Contexto

Usted ha sido contratado como desarrollador Java en la empresa **PayCo**. El equipo anterior dejó un sistema de pagos funcional pero incompleto. Su líder técnico le ha asignado **5 tickets** que debe resolver antes de que termine la jornada. Cada ticket tiene una prioridad, criterios de aceptación y pistas sobre dónde trabajar.

> **⚠️ Regla de oro:** No debe modificar las interfaces `PaymentMethod`, `Refundable` ni `DiscountStrategy` ya existentes (a menos que un ticket lo indique explícitamente). Todo código nuevo debe integrarse sin romper lo que ya funciona.

---

## 📋 Ticket 1 — Nueva forma de pago: Transferencia Bancaria (1.25)

**Prioridad:** Alta  
**Temas evaluados:** Interfaces, Herencia múltiple por interfaces  
**Paquete destino:** `com.paymentapp.payment`

### Descripción

El área de negocio solicita agregar **Transferencia Bancaria** como nuevo método de pago. Este método debe soportar tanto el procesamiento de pagos como la posibilidad de reembolso.

### Requerimientos

1. Cree la clase `BankTransferPayment` en el paquete `com.paymentapp.payment`.
2. La clase debe implementar **tanto** `PaymentMethod` **como** `Refundable` (herencia múltiple por interfaces).
3. Atributos mínimos:
   - `String bankName` — nombre del banco.
   - `String accountNumber` — número de cuenta.
   - `double amount` — monto del pago.
4. Implemente todos los métodos de ambas interfaces:
   - `ProcesarPago()`: Imprime en consola los detalles del pago (banco, cuenta enmascarada mostrando solo los últimos 4 dígitos, monto).
   - `MostrarTipoPago()`: Retorna `"Transferencia Bancaria"`.
   - `MostrarDetalleTrans(double monto, String metodoPago)`: Retorna un `String` formateado con banco, cuenta enmascarada y monto.
   - `ProcesarReembolso()`: Reembolsa el **100%** del monto (a diferencia de PayPal que reembolsa 50%). Imprime detalle en consola y retorna el monto reembolsado.
   - `DetalleReembolso()`: Retorna un `String` con el detalle del reembolso procesado.
5. Integre esta nueva opción en el menú de `Main.java` como **opción 4** pidiendo al usuario: nombre del banco y número de cuenta.

### Criterios de aceptación

- [ ] La clase compila e implementa correctamente las dos interfaces.
- [ ] El menú principal ofrece la opción 4 y funciona correctamente.
- [ ] El enmascaramiento de cuenta funciona (solo últimos 4 dígitos visibles).

---

## 📋 Ticket 2 — Descuentos personalizados con Lambdas (1.0)

**Prioridad:** Alta  
**Temas evaluados:** Interfaces funcionales, Expresiones Lambda  
**Paquete destino:** `com.paymentapp.discount`

### Descripción

El equipo de marketing necesita nuevas estrategias de descuento más flexibles. Se le pide crear una clase que centralice nuevos descuentos usando la interfaz funcional `DiscountStrategy` que ya existe.

### Requerimientos

1. Cree la clase `CustomDiscount` en el paquete `com.paymentapp.discount`.
2. Defina las siguientes estrategias como **constantes `static final`** implementadas con **expresiones lambda**:

   | Constante               | Regla                                                                                          |
   |-------------------------|------------------------------------------------------------------------------------------------|
   | `BlackFriday`           | 30% de descuento                                                                               |
   | `CuponFijo`             | Resta $5.000 al monto (si el monto es menor a $5.000, el resultado debe ser $0, nunca negativo)|
   | `DescuentoEscalonado`   | Si el monto > $100.000 aplica 25% off; si está entre $50.000 y $100.000 aplica 15% off; de lo contrario 5% off |

3. Cree un método estático `aplicarDescuentoConLog(double monto, DiscountStrategy strategy, String nombre)` que:
   - Aplique el descuento.
   - Imprima: nombre del descuento, monto original, ahorro y monto final (similar a `Descuento.applyAndShow` pero con el prefijo `[CustomDiscount]` en cada línea).
   - Retorne el monto final.
4. Integre estas nuevas opciones al menú de descuentos en `Main.java` como opciones **5, 6 y 7**.

### Criterios de aceptación

- [ ] Las tres lambdas compilan y aplican la lógica correcta.
- [ ] `CuponFijo` nunca retorna un valor negativo.
- [ ] `DescuentoEscalonado` maneja correctamente los tres rangos.
- [ ] Las nuevas opciones aparecen en el menú y funcionan de extremo a extremo.

---

## 📋 Ticket 3 — Procesador Genérico de Pagos (1.25)

**Prioridad:** Alta  
**Temas evaluados:** Tipos genéricos (Generics)  
**Paquete destino:** `com.paymentapp.modelo`

### Descripción

Se necesita una clase genérica que pueda almacenar y operar sobre cualquier tipo de pago, evitando crear una clase distinta para cada método de pago.

### Esqueleto proporcionado

Se le entrega la siguiente estructura. **Debe completar el cuerpo de cada método** donde dice `// TODO`.

```java
package com.paymentapp.modelo;

import com.paymentapp.interfaces.PaymentMethod;
import java.util.ArrayList;
import java.util.List;

public class PaymentProcessor<T extends PaymentMethod> {

    private List<T> historial;

    public PaymentProcessor() {
        // TODO: inicializar la lista
    }

    public void registrarPago(T pago) {
        // TODO: agregar el pago a la lista y llamar a pago.ProcesarPago()
    }

    public int totalTransacciones() {
        // TODO: retornar la cantidad de pagos en el historial
        return 0;
    }

    public void mostrarHistorial(double montoFinal) {
        // TODO: recorrer el historial e imprimir cada pago numerado (1, 2, 3...)
        //       usando pago.MostrarDetalleTrans(montoFinal, pago.MostrarTipoPago())
    }
}
```

### Requerimientos

1. Cree el archivo `PaymentProcessor.java` en `com.paymentapp.modelo` con el esqueleto anterior.
2. Complete el cuerpo de los 4 métodos marcados con `// TODO`.
3. En `Main.java`, después de procesar el pago:
   - Cree una instancia: `PaymentProcessor<PaymentMethod> processor = new PaymentProcessor<>();`
   - Llame a `processor.registrarPago(metodo);`
   - Llame a `processor.mostrarHistorial(montoFinal);`
   - Imprima: `"Total de transacciones: " + processor.totalTransacciones()`

### 💡 Pista

Observe que `<T extends PaymentMethod>` significa que `T` puede ser **cualquier clase** que implemente `PaymentMethod`. Por eso dentro de la clase puede llamar a los métodos de esa interfaz sobre objetos de tipo `T`.

### Criterios de aceptación

- [ ] La clase compila con el tipo genérico `<T extends PaymentMethod>`.
- [ ] `registrarPago` agrega a la lista y llama a `ProcesarPago()`.
- [ ] `mostrarHistorial` imprime la lista numerada.
- [ ] Se usa en `Main.java` y muestra el historial correctamente.

---

## 📋 Ticket 4 — Notificaciones de Pago con Eventos (1.0)

**Prioridad:** Media  
**Temas evaluados:** Eventos, Interfaces funcionales, Lambdas  
**Paquete destino:** `com.paymentapp.interfaces` (interfaz) y `com.paymentapp.modelo` (clase)

### Descripción

El equipo de auditoría necesita que el sistema imprima un log automático cada vez que se procesa un pago o se aplica un descuento. Usted debe crear un mecanismo simple de "escucha" (listener) que permita reaccionar a estos eventos.

### Paso 1 — Crear la interfaz funcional

Cree la interfaz `PaymentEventListener` en `com.paymentapp.interfaces`. **Cópiela tal cual:**

```java
package com.paymentapp.interfaces;

@FunctionalInterface
public interface PaymentEventListener {
    void onEvent(String mensaje);
}
```

> **¿Qué es esto?** Una interfaz funcional tiene **un solo método abstracto**, lo que permite usarla con lambdas.

### Paso 2 — Crear la clase EventManager

Cree la clase `EventManager` en `com.paymentapp.modelo` con el siguiente esqueleto y **complete los `// TODO`**:

```java
package com.paymentapp.modelo;

import com.paymentapp.interfaces.PaymentEventListener;
import java.util.ArrayList;
import java.util.List;

public class EventManager {

    private List<PaymentEventListener> listeners;

    public EventManager() {
        // TODO: inicializar la lista de listeners
    }

    public void subscribe(PaymentEventListener listener) {
        // TODO: agregar el listener a la lista
    }

    public void notify(String mensaje) {
        // TODO: recorrer la lista y llamar a onEvent(mensaje) en cada listener
    }
}
```

### Paso 3 — Usar en Main.java con Lambdas

En `Main.java`, **antes** de procesar el pago:

1. Cree una instancia de `EventManager`.
2. Suscriba **dos listeners usando expresiones lambda**:
   ```java
   eventos.subscribe((msg) -> System.out.println("[LOG] " + msg));
   eventos.subscribe((msg) -> System.out.println("[AUDITORIA] " + msg));
   ```
3. Justo **después** de `metodo.ProcesarPago()`, dispare:
   ```java
   eventos.notify("Pago procesado con " + metodo.MostrarTipoPago());
   ```
4. Justo **después** de aplicar el descuento, dispare:
   ```java
   eventos.notify("Descuento aplicado: " + nombreDescuento);
   ```

### 💡 Pista

Como `PaymentEventListener` es una interfaz funcional con un solo método `onEvent(String)`, se puede implementar con una lambda: `(msg) -> { ... }`. Cada lambda que suscriba se convierte en un listener independiente.

### Criterios de aceptación

- [ ] `PaymentEventListener` tiene la anotación `@FunctionalInterface` y un solo método.
- [ ] `EventManager` compila y sus 3 métodos funcionan.
- [ ] Los listeners se registran con **lambdas** (no con clases anónimas).
- [ ] Al ejecutar el programa, se ven los mensajes `[LOG]` y `[AUDITORIA]` en consola en el momento correcto.

---

## 📋 Ticket 5 — Integración final y prueba de concepto (0.5)

**Prioridad:** Media  
**Temas evaluados:** Integración de todos los conceptos anteriores  

### Descripción

Una vez completados los tickets anteriores, demuestre que todo el sistema funciona junto.

### Requerimientos

1. Modifique `Main.java` para que el flujo completo sea:
   - El usuario ingresa el monto.
   - Selecciona el método de pago (**incluyendo la nueva opción 4: Transferencia Bancaria**).
   - Selecciona el tipo de descuento (**incluyendo las nuevas opciones 5, 6 y 7**).
   - Se procesa el pago → se dispara evento `"PAGO_PROCESADO"`.
   - Se aplica el descuento → se dispara evento `"DESCUENTO_APLICADO"`.
   - Se registra el pago en el `PaymentProcessor` genérico.
   - Se muestra el historial de transacciones.
   - Se muestra el resumen final de la transacción.
2. Si el método de pago es `Refundable` (PayPal o Transferencia Bancaria):
   - Pregunte al usuario si desea un reembolso (S/N).
   - Si acepta, procese el reembolso y muestre el detalle.

### Criterios de aceptación

- [ ] El programa compila y corre sin errores.
- [ ] Todos los métodos de pago funcionan (4 opciones).
- [ ] Todos los descuentos funcionan (7 opciones + sin descuento).
- [ ] Los eventos se disparan y los logs `[LOG]` y `[AUDITORIA]` aparecen.
- [ ] El historial genérico muestra la transacción registrada.
- [ ] El reembolso funciona para los métodos que implementan `Refundable`.

---

## 📊 Rúbrica de Evaluación

| Ticket | Tema principal                                 | Nota  |
|--------|------------------------------------------------|-------|
| 1      | Interfaces + Herencia múltiple                 | 1.25  |
| 2      | Interfaces funcionales + Lambdas               | 1.0   |
| 3      | Tipos genéricos (Generics)                     | 1.25  |
| 4      | Notificaciones con Eventos + Lambdas           | 1.0   |
| 5      | Integración final                              | 0.5   |
| **Total** |                                             | **5.0** |

### Penalizaciones

| Falta                                  | Penalización |
|----------------------------------------|-------------|
| No compila                             | -1.5        |
| Modificó interfaces existentes         | -0.5        |
| Código duplicado evitable              | -0.25       |
| No usa lambdas donde se pide           | -0.5        |
| No usa generics donde se pide          | -0.5        |
| Nombres de clases/paquetes incorrectos | -0.25       |

---

## 📁 Estructura esperada al finalizar

```
src/
├── Main.java
└── com/paymentapp/
    ├── interfaces/
    │   ├── PaymentMethod.java          ← (sin modificar)
    │   ├── Refundable.java             ← (sin modificar)
    │   ├── DiscountStrategy.java       ← (sin modificar)
    │   ├── Descuento.java              ← (sin modificar)
    │   └── PaymentEventListener.java   ← Nuevo (Ticket 4)
    ├── modelo/
    │   ├── DefaultPayment.java         ← (sin modificar)
    │   ├── PaymentProcessor.java       ← Nuevo (Ticket 3, esqueleto dado)
    │   └── EventManager.java           ← Nuevo (Ticket 4, esqueleto dado)
    ├── payment/
    │   ├── CreditCardPayment.java      ← (sin modificar)
    │   ├── PaypalPayment.java          ← (sin modificar)
    │   ├── CryptoPayment.java          ← (sin modificar)
    │   └── BankTransferPayment.java    ← Nuevo (Ticket 1)
    └── discount/
        └── CustomDiscount.java         ← Nuevo (Ticket 2)
```

---

> **Consejo final:** Lea todos los tickets antes de empezar. Algunos tickets dependen de otros. Se recomienda resolverlos en orden numérico. ¡Éxitos!
