package me.sirenninja.discordbot.listeners;

import me.sirenninja.discordbot.DiscordBot;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {

    private DiscordBot bot;

    public MessageListener(DiscordBot bot){
        this.bot = bot;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split(" ");

        if(args[0].startsWith("!")){
            bot.checkCommand(args[0].substring(1), event);
        }
    }

}
