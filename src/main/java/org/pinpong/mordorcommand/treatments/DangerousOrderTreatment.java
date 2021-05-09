package org.pinpong.mordorcommand.treatments;

import org.pinpong.mordorcommand.interfaces.DangerousOrder;
import org.pinpong.mordorcommand.interfaces.Order;
import org.pinpong.mordorcommand.interfaces.OrderTreatment;
import org.pinpong.mordorcommand.orders.InternationalOrder;

public class DangerousOrderTreatment implements OrderTreatment {

    private DangerousOrder dangerousOrder = null;

    public DangerousOrderTreatment(DangerousOrder dangerousOrder) {
        this.dangerousOrder = dangerousOrder;
    }

    @Override
    public boolean treat() {
        // Si no es igual a No ponerselo en el dedo entonces es TRUE
        return !this.dangerousOrder.instructions().equals("No ponerselo en el dedo");
    }

    public DangerousOrder getOrder() {
        return this.dangerousOrder;
    }
}
