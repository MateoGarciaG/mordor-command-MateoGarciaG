package org.pinpong.mordorcommand.interfaces;

import java.util.Random;

public interface Order {
    int weight();
    String destiny();

    default String generatesRandomId() {
        String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder id = new StringBuilder();
        Random random = new Random();
        while (id.length() <= 5) { // 5 WILL BE the length of the random string.
            char getCharacter =  ALPHANUMERIC.charAt(random.nextInt(ALPHANUMERIC.length()));
            id.append(getCharacter);
        }
        return id.toString();
    }
    
}
