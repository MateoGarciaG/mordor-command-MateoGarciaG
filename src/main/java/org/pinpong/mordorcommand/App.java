package org.pinpong.mordorcommand;

/**
 * @author @dfleta He is the author of this main code, I only changes the name of the Classes of the object
 */
public class App {
    public static void main(String[] args) {

        /*
        * Explicación/Apuntes míos:
        *
        * Pedidos: Son el REQUEST Object el cual será tratado por los Tratamientos de pedidos/commands según el tipo de comportamiento del pedido, donde según el tipo de comportamiento del pedido. El cual puede ser Nacional, internacional o Peligroso será tratado de manera diferente por un Command/Tratamiento
        *
         * Tratamientos de Pedidos: Serían según el patron los: COMMANDS, se que se encargan en este caso de tratar los pedidos y chequear si son aptos o no. Los COMMANDS según el libro son: "Son objetos intermediarios para identificar qué objeto ejecutará qué comando/order(en el ejemplo) según el tipo de comportamiento de la request object que se quiera ejecutar.". En este caso en vez de "ejecutar" el comportamiento del objeto. Lo que hacen los commands/tratamientos de pedidos en este caso es "TRATAR()" el pedido. Cada Command Class(en este caso serían los Tratamientos) está asociada con el manejo de un comportamiento especifico del Objeto Request(que sería en este caso los Pedidos)
        *
        * Oficina: Es el procesador el cual se encarga de procesar los tratamientos con sus solicitudes. Según el patrón sería como el Invoker Class que se encargaría de ejecutar los Commands.
        *
        * */

        /* El procesador de tratamientos */
        Oficina oficina = new Oficina();

        /* Pedido de tipo internacional */
        Pedido pedido = new PedidoInternacional("Comarca", 10);
        /* Tratamiento para los pedidos de tipo internacional */
        TratamientoPedido tratamientoInt = new TratamientoPedidoInternacional((PedidoInternacional) pedido);
        /* Oficina método: procesa(). Procesa el tratamientoInternacional el cual ya contiene el pedidoInternacional.
         Mientras que printarStatus() muestra el estatus del tratamiento y pedido*/
        System.out.println(oficina.printarStatus(oficina.procesa(tratamientoInt), pedido));

        pedido = new PedidoInternacional("Mordor", 10);
        tratamientoInt = new TratamientoPedidoInternacional((PedidoInternacional) pedido);

        System.out.println(oficina.printarStatus(oficina.procesa(tratamientoInt), pedido));

        pedido = new PedidoPeligrosoOrden("Cima de los vientos", "No urgarse en las uñas con este puñal");
        TratamientoPedido peligroso = new TratamientoPedidoPeligroso((PedidoPeligroso) pedido);

        System.out.println(oficina.printarStatus(oficina.procesa(peligroso), pedido));

        pedido = new PedidoPeligrosoOrden("Monte del destino", "No ponerselo en el dedo");
        peligroso = new TratamientoPedidoPeligroso((PedidoPeligroso) pedido);

        System.out.println(oficina.printarStatus(oficina.procesa(peligroso), pedido));

        /**
         * Los pedidos multiples se completan en el ultimo de los casos test
         */

    }



}
