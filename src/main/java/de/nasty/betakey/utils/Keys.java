/*
 * Class created by Nasty / Ron S.
 * 29.11.20, 05:48
 */

package de.nasty.betakey.utils;

import java.util.Random;

public class Keys {

    public static String generate() {
        String chars = "ABCDEFGHIKJLKMNOPQRSTUVWXYZ123456789";
        String randomString = "";
        int length = 16;


        Random random = new Random();

        char[] text = new char[length];

        for (int i = 0; i < length; i++) {
            text[i] = chars.charAt(random.nextInt(chars.length()));
        }

        for(int i = 0; i < text.length; i++) {
            randomString += text[i];
        }


        StringBuilder str = new StringBuilder(randomString);
        int idx = str.length() - 4;

        while (idx > 0)
        {
            str.insert(idx, "-");
            idx = idx - 4;
        }

        return str.toString();
    }


}
