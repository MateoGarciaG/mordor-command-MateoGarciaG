package org.pinpong.mordorcommand.orders;

import org.pinpong.mordorcommand.interfaces.Order;

public class InternationalOrder  implements Order{

    private final String id = this.generatesRandomId();
    private String destiny = null;
    private int weight = 0;

    public InternationalOrder(String destiny, int weight) {
        this.destiny = destiny;
        this.weight = weight;
    }


    @Override
    public int weight() {
        return this.weight;
    }

    @Override
    public String destiny() {
        return this.destiny;
    }

    public String getId() {
        return this.id;
    }
}
