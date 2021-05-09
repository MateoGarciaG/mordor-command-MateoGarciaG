package org.pinpong.mordorcommand.orders;

import org.pinpong.mordorcommand.interfaces.DangerousOrder;

public class ConcreteDangerousOrder implements DangerousOrder {

    private final String id = this.generatesRandomId();
    private String destiny = null;
    private int weight = 0;
    private String instructions = null;

    public ConcreteDangerousOrder(String destiny, String instructions) {
        this.destiny = destiny;
//        this.weight = weight;
        this.instructions = instructions;
    }

    @Override
    public String instructions() {
        return this.instructions;
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
