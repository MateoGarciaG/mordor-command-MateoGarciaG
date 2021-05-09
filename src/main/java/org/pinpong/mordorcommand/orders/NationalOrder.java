package org.pinpong.mordorcommand.orders;

import org.pinpong.mordorcommand.interfaces.Order;

public class NationalOrder implements Order {

    private String id = null;
    private String destiny = null;
    private int weight = 0;

    public NationalOrder(String destiny, int weight) {
        this.destiny = destiny;
        this.weight = weight;
    }


    @Override
    public int weight() {
        return 0;
    }

    @Override
    public String destiny() {
        return null;
    }

    public String getId() {
        return this.id;
    }
}
