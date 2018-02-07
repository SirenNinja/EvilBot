package me.sirenninja.discordbot.data;

import java.io.*;
import java.util.Properties;

import static me.sirenninja.discordbot.utils.Utils.*;

public class Data {

    private Properties properties = new Properties();
    private String filename;

    public Data(){
        filename = "config.properties";
        loadConfig();
    }


    private void loadConfig(){
        try{
            if(new File(filename).exists()){
                InputStream input = new FileInputStream(filename);

                properties.clear();
                properties.load(input);

                System.out.println("Config has been loaded!");
                input.close();

            }else{
                OutputStream output = new FileOutputStream(filename);

                properties.setProperty("botkey", "DISCORD-API-KEY");
                properties.setProperty("status", "ONLINE");
                properties.setProperty("game", "null");

                properties.store(output, null);

                output.close();
            }
        }catch(Exception e){
            System.out.println("loadConfig() exception: " + e.getMessage());
        }
    }

    public String getKey(){
        return properties.getProperty("botkey");
    }

    public String getStatus(){
        String[] statuses = {"ONLINE", "OFFLINE", "DO_NOT_DISTURB", "IDLE", "INVISIBLE", "UNKNOWN"};

        if(isInArray(statuses, properties.getProperty("status")))
            return properties.getProperty("status").toUpperCase();

        return "ONLINE";
    }

    public String getGame(){
        return properties.getProperty("game");
    }

}
