package org.pinpong.mordorcommand;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.junit.Test;

import org.pinpong.mordorcommand.interfaces.Order;
import org.pinpong.mordorcommand.interfaces.DangerousOrder;
import org.pinpong.mordorcommand.interfaces.Processor;
import org.pinpong.mordorcommand.interfaces.OrderTreatment;
import org.pinpong.mordorcommand.orders.InternationalOrder;
import org.pinpong.mordorcommand.orders.NationalOrder;
import org.pinpong.mordorcommand.orders.ConcreteDangerousOrder;
import org.pinpong.mordorcommand.processor.Office;
import org.pinpong.mordorcommand.treatments.InternationalOrderTreatment;
import org.pinpong.mordorcommand.treatments.MultipleOrderTreatment;
import org.pinpong.mordorcommand.treatments.DangerousOrderTreatment;

/**
 * Unit test for simple App.
 */
public class OrdersTest
{
    /**
     * Crea una clase InternationalOrderTreatment que permita treat
     * Orders internacionales.
     * 
     * La clase permite treat todos los Orders excepto 
     * los que van a Mordor.
     * 
     * Crea las clases necesarias que se requieren en los casos test
     * respetando los constructores que se exigen.
     */
    @Test
	public void test_Mordor() {	

        Order OrderInt = new InternationalOrder("Mordor", 100);
        assertEquals("Mordor", OrderInt.destiny());

		OrderTreatment tratamientoKO = new InternationalOrderTreatment(
                                                    (InternationalOrder) OrderInt);
        assertNotNull(tratamientoKO);
        assertFalse(tratamientoKO.treat());			
	}

	@Test
	public void test_Comarca() {

        Order OrderInt = new InternationalOrder("Comarca", 100);
        assertEquals("Comarca", OrderInt.destiny());

		OrderTreatment tratamientoOK = new InternationalOrderTreatment(
                                                    (InternationalOrder) OrderInt);
        assertNotNull(tratamientoOK);        
        assertTrue(tratamientoOK.treat());
    }

    /**
     * Crea una clase DangerousOrderTreatment que permita treat
     * Orders peligrosos.
     * 
     * La clase permite treat todos los Orders segun sus
     * instrucciones excepto aquellos cuya instruccion sea 
     * "No ponerselo en el dedo". 
     * 
     * Crea las clases necesarias que se requieren en los casos test
     * respetando los constructores que se exigen.
     */
    @Test
    public void test_Order_peligroso_KO() {

        Order OrderConPeligro = new ConcreteDangerousOrder("Monte del destiny", 
                                                           "No ponerselo en el dedo");
        assertEquals("Monte del destiny", OrderConPeligro.destiny());

        OrderTreatment tratamientoKO = new DangerousOrderTreatment(
                                                    (DangerousOrder) OrderConPeligro);
        assertNotNull(tratamientoKO);
        assertFalse(tratamientoKO.treat());
    }

    @Test
    public void test_Order_peligroso_OK() {

        Order OrderConPeligro = new ConcreteDangerousOrder("Cima de los vientos", 
                                                           "No urgarse en las uñas con este puñal");
        assertEquals("Cima de los vientos", OrderConPeligro.destiny());

        OrderTreatment tratamientoOK = new DangerousOrderTreatment(
                                                    (DangerousOrder) OrderConPeligro);
        assertTrue(tratamientoOK.treat());
    }

    /**
     * Añade a las clases InternationalOrder y ConcreteDangerousOrder
     * una identificador Id de tipo String
     * autogenerado haciendo uso de la clase UUID de Java
     * https://docs.oracle.com/javase/8/docs/api/java/util/UUID.html
     */
    @Test
    public void test_UUID_generator() {

        InternationalOrder internacional = new InternationalOrder("Mordor", 10);
        ConcreteDangerousOrder peligroso = new ConcreteDangerousOrder("Cima de los vientos", 
                                                                  "No urgarse en las uñas con este puñal");
        assertNotNull(internacional.getId());
        assertNotNull(peligroso.getId());
       
        assertNotEquals(internacional.getId(), peligroso.getId());
    }

    /**
     * Añade una clase para los Orders nacionales.
     */

    @Test
    public void test_Order_nacional_UUDI() {

        InternationalOrder internacional = new InternationalOrder("Mordor", 10);
        assertNotNull(internacional);
        NationalOrder nacional = new NationalOrder("Gondor", 50);
        assertNotNull(nacional);
        assertNotEquals(internacional.getId(), nacional.getId());
    }
    
    /**
     * Construye una Office que procese todo tipo de Orders.
     * 
     * La Office process los Orders en funcion de si
     * es posible treatlos o no segun las reglas de cada
     * tipo de Order
     */

    @Test
    public void test_interface_Processor() {
        
        Processor correos = new Office();
        OrderTreatment OrderInt = new InternationalOrderTreatment(
                                            new InternationalOrder("Comarca", 100));
        assertTrue(correos.process(OrderInt));

        OrderTreatment OrderConPeligro = new DangerousOrderTreatment(
                                                 new ConcreteDangerousOrder("Cima de los vientos", 
                                                "No urgarse en las uñas con este puñal"));
        assertTrue(correos.process(OrderConPeligro));
    }

    /**
     * La Office puede enviar un mensaje que informe del
     * status del Order, en funcion de si ha sido posible processrlo.
     * 
     * Hace uso de un tipo enumerado STATUS con las constantes
     * ACEPTADO y RECHAZADO.
     */

    @Test
    public void test_printar_status() {

        Office correos = new Office();
        InternationalOrder toComarcaWithLove = new InternationalOrder("Comarca", 100);
        OrderTreatment OrderInt = new InternationalOrderTreatment(toComarcaWithLove);

        assertTrue(correos.process(OrderInt));
        assertEquals("Comarca ACCEPT", correos.showStatus(
                                            correos.process(OrderInt), toComarcaWithLove));

        DangerousOrder OrderConPeligro = new ConcreteDangerousOrder("Monte del destiny", 
                                                                    "No ponerselo en el dedo");
        OrderTreatment tratamientoKO = new DangerousOrderTreatment(OrderConPeligro);

        assertFalse(correos.process(tratamientoKO));
        assertEquals("Monte del destiny REJECTED", correos.showStatus(
                                                        correos.process(tratamientoKO), 
                                                                        OrderConPeligro));

    }

    /**
     * Crea una clase MultipleOrderTreatment que permita treat
     * Orders multiples.
     * 
     * La clase permite treat el Order multiple si 
     * el peso total de los Orders es mayor que 0
     * y 
     * el numero de bultos coincide con el numero de
     * Orders individuales que forman el Order multiple.
     * 
     * Crea las clases necesarias que se requieren en los casos test
     * respetando los constructores que se exigen.
     */

    @Test
    public void test_tratamiento_Order_multiple_treat() {

        /**
         * Crea una colección de tres Orders nacionales, a "Gondor", "Minas Tirith", "Rohan"
         * con un peso de 10 cada uno.
         * 
         * Pasasela a OrderTreatmentsMultiple en su constructor.
         */

        Set<Order> Orders = new HashSet<>();
        List<String> destinys = Arrays.asList("Gondor", "Minas Tirith", "Rohan");
        List<Integer> pesos = Arrays.asList(10, 10, 10);
        
        for (int i=0; i<destinys.size(); i++) {
            Orders.add(new NationalOrder(destinys.get(i), pesos.get(i)));
        }
        assertEquals(3, Orders.size());

        MultipleOrderTreatment OrdersMult = new MultipleOrderTreatment(Orders);
        assertNotNull(OrdersMult);

        /**
         * Completa los metodos del Order multiple.
         * Se valorara el uso de streams.
         * 
         * calculateNumLumps
         * @param   void
         * @return  void
         *   
         * calculateTotalWeight
         * @param   void
         * @return  void
         * 
         */

        OrdersMult.calculateNumLumps();
        assertEquals(3, OrdersMult.getNumLumps(), 0);

        OrdersMult.calculateTotalWeight();
        assertEquals(30, OrdersMult.getTotalWeight(), 0);

        /**
         * Trata el Order multiple.
         */
        assertTrue(OrdersMult.treat());
    }
}

