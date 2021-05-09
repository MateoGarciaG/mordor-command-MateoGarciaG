package org.pinpong.mordorcommand;

import org.pinpong.mordorcommand.interfaces.DangerousOrder;
import org.pinpong.mordorcommand.interfaces.Order;
import org.pinpong.mordorcommand.interfaces.OrderTreatment;
import org.pinpong.mordorcommand.orders.ConcreteDangerousOrder;
import org.pinpong.mordorcommand.orders.InternationalOrder;
import org.pinpong.mordorcommand.processor.Office;
import org.pinpong.mordorcommand.treatments.DangerousOrderTreatment;
import org.pinpong.mordorcommand.treatments.InternationalOrderTreatment;

/**
 * @author @dfleta He is the author of this main code, I only changes the name of the Classes of the object
 */
public class App {
    public static void main(String[] args) {

        /*
        * Explicación/Apuntes míos:
        *
        * Orders: Son el REQUEST Object el cual será tratado por los Tratamientos de Orders/commands según el tipo de comportamiento del Order, donde según el tipo de comportamiento del Order. El cual puede ser Nacional, internacional o Peligroso será tratado de manera diferente por un Command/Tratamiento
        *
         * Tratamientos de Orders: Serían según el patron los: COMMANDS, se que se encargan en este caso de tratar los Orders y chequear si son aptos o no. Los COMMANDS según el libro son: "Son objetos intermediarios para identificar qué objeto ejecutará qué comando/order(en el ejemplo) según el tipo de comportamiento de la request object que se quiera ejecutar.". En este caso en vez de "ejecutar" el comportamiento del objeto. Lo que hacen los commands/tratamientos de Orders en este caso es "TRATAR()" el Order. Cada Command Class(en este caso serían los Tratamientos) está asociada con el manejo de un comportamiento especifico del Objeto Request(que sería en este caso los Orders)
        *
        * Office: Es el processdor el cual se encarga de processr los tratamientos con sus solicitudes. Según el patrón sería como el Invoker Class que se encargaría de ejecutar los Commands.
        *
        * */

        /* El processdor de tratamientos */
        Office Office = new Office();

        /* Order de tipo internacional */
        Order Order = new InternationalOrder("Comarca", 10);
        /* Tratamiento para los Orders de tipo internacional */
        OrderTreatment tratamientoInt = new InternationalOrderTreatment((InternationalOrder) Order);
        /* Office método: process(). process el tratamientoInternacional el cual ya contiene el InternationalOrder.
         Mientras que showStatus() muestra el estatus del tratamiento y Order*/
        System.out.println(Office.showStatus(Office.process(tratamientoInt), Order));

        Order = new InternationalOrder("Mordor", 10);
        tratamientoInt = new InternationalOrderTreatment((InternationalOrder) Order);

        System.out.println(Office.showStatus(Office.process(tratamientoInt), Order));

        Order = new ConcreteDangerousOrder("Cima de los vientos", "No urgarse en las uñas con este puñal");
        OrderTreatment peligroso = new DangerousOrderTreatment((DangerousOrder) Order);

        System.out.println(Office.showStatus(Office.process(peligroso), Order));

        Order = new ConcreteDangerousOrder("Monte del destino", "No ponerselo en el dedo");
        peligroso = new DangerousOrderTreatment((DangerousOrder) Order);

        System.out.println(Office.showStatus(Office.process(peligroso), Order));

        /**
         * Los Orders multiples se completan en el ultimo de los casos test
         */

    }



}
