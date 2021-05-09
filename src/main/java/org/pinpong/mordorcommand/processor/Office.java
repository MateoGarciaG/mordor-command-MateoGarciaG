package org.pinpong.mordorcommand.processor;

import org.pinpong.mordorcommand.interfaces.Order;
import org.pinpong.mordorcommand.interfaces.OrderTreatment;
import org.pinpong.mordorcommand.interfaces.Processor;
import org.pinpong.mordorcommand.interfaces.Status;

import java.util.Formatter;

public class Office implements Processor {

    public Office() {}

    @Override
    public boolean process(OrderTreatment orderTreatment) {

        return orderTreatment.treat();
    }

    public String showStatus(boolean checkTreat, Order order) {
        StringBuilder stringBuilder = new StringBuilder();
        Formatter formatter = new Formatter(stringBuilder);

        if (checkTreat) {
            formatter.format("Destiny: %1$s. STATUS: %2$s", order.destiny(), Status.ACCEPT.name());
        } else {
            formatter.format("Destiny: %1$s. STATUS: %2$s", order.destiny(), Status.REJECTED.name());
        }
        
        formatter.close();
        return stringBuilder.toString();

    }
}
