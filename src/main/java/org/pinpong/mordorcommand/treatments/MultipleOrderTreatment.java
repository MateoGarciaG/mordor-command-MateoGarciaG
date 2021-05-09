package org.pinpong.mordorcommand.treatments;

import org.pinpong.mordorcommand.interfaces.Order;
import org.pinpong.mordorcommand.interfaces.OrderTreatment;

import java.util.HashSet;
import java.util.Set;

public class MultipleOrderTreatment implements OrderTreatment {

    private Integer totalWeight = null;
    private Long numLumps = null;

    private Set<Order> orders = null;

    public MultipleOrderTreatment(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean treat() {
        this.calculateTotalWeight();
        this.calculateNumLumps();

        /* El criterio es: Si el tamaño de las ordenes es igual al tamaño del SET y además el peso Total de todas las ordenes es mayor a 0, entonces es apto/true */
        return this.numLumps == this.orders.size() && this.totalWeight > 0;
    }

    public void calculateTotalWeight() {
        this.totalWeight = this.orders.stream()
                .map(Order::weight)
                .reduce(0, Integer::sum);
    }

    public void calculateNumLumps() {
        this.numLumps = this.orders.stream()
                .count();
    }

    public Integer getTotalWeight() {
        return this.totalWeight;
    }

    public Long getNumLumps() {
        return this.numLumps;
    }
}
