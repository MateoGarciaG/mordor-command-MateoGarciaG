package org.pinpong.mordorcommand.treatments;

import org.pinpong.mordorcommand.interfaces.Order;
import org.pinpong.mordorcommand.interfaces.OrderTreatment;
import org.pinpong.mordorcommand.orders.InternationalOrder;

public class InternationalOrderTreatment implements OrderTreatment {

    private InternationalOrder internationalOrder = null;

    public InternationalOrderTreatment(InternationalOrder internationalOrder) {
        this.internationalOrder = internationalOrder;
    }

    @Override
    public boolean treat() {
        return !this.internationalOrder.destiny().equals("Mordor");
    }

    public InternationalOrder getOrder() {
        return this.internationalOrder;
    }
}
