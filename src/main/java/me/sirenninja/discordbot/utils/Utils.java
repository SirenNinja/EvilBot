package me.sirenninja.discordbot.utils;

import java.util.Arrays;

public class Utils {

    public static boolean isInArray(Object[] array, Object value){
        return Arrays.asList(array).contains(value);
    }

}
