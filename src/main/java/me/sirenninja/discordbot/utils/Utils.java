package me.sirenninja.discordbot.utils;

import net.dv8tion.jda.core.EmbedBuilder;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class Utils {

    public static boolean isInArray(Object[] array, Object value){
        return Arrays.asList(array).contains(value);
    }

    public static boolean isNumber(String s){
        return s.matches("\\d+");
    }

    public static EmbedBuilder embedBuilder(String author, String title, String message, String avatar, Color color){
        EmbedBuilder emBuilder = new EmbedBuilder();
        emBuilder.setAuthor(author);
        emBuilder.setTitle(title);
        emBuilder.setDescription(message);

        if(avatar != null)
            emBuilder.setThumbnail(avatar);

        emBuilder.setColor(color);

        return emBuilder;
    }

    public static Color getRandomColor(){
        Color color = new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256));

        Random random = new Random();
        final float hue = random.nextFloat();
        final float saturation = 0.9f;
        final float luminance = 1.0f;
        color = Color.getHSBColor(hue, saturation, luminance);

        return color;
    }

}
