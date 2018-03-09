package me.sirenninja.evilbot.commands.fun;

import me.sirenninja.evilbot.commands.Command;
import me.sirenninja.evilbot.utils.Utils;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

public class Translator implements Command {

    private final String translateLink = "https://script.google.com/macros/s/AKfycbzJypWkp829RTnahqSNu5Nevs083X7zei_jQE1ur8_M-fJdRPwa/exec";
    private boolean enabled = true;

    @Override
    public String getCommand() {
        return "translate";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean state) {
        enabled = state;
    }

    @Override
    public void onCommand(String[] args, MessageReceivedEvent event) {
        String from = "";
        String to = "";

        StringBuilder message = new StringBuilder();
        for(int i = 1; i < args.length; i++) {
            if(args[i].startsWith("from:") && from.isEmpty()){
                from = args[i].split(":")[1].trim();
                continue;
            }else if(args[i].startsWith("to:") && to.isEmpty()){
                to = args[i].split(":")[1].trim();
                continue;
            }

            message.append(args[i]).append(" ");
        }

        if(from.isEmpty() || to.isEmpty()){
            event.getChannel().sendMessage(Utils.embedBuilder("Translator", " ", "Error: Please specify the **from** and **to** languages. \nExample: !translate from:en to:ru <message>", null, Color.RED).build()).complete();
            return;
        }

        try{
            event.getChannel().sendMessage(Utils.embedBuilder("Translator", "From **" + from + "** to **" + to + "**", translate(from, to, message.toString()), null, Color.BLUE).build()).complete();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private String translate(String langFrom, String langTo, String text) throws IOException {
        String urlStr = translateLink + "?q=" + URLEncoder.encode(text, "UTF-8") + "&target=" + langTo + "&source=" + langFrom;
        URL url = new URL(urlStr);

        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            response.append(inputLine);

        in.close();

        System.out.println(response.toString());
        return response.toString();
    }
}
