package me.sirenninja.discordbot.listeners;

import me.sirenninja.discordbot.EvilBot;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import static me.sirenninja.discordbot.utils.Utils.isInArray;

public class MessageListener extends ListenerAdapter {

    private EvilBot bot;
    private char prefix = '!';
    private String[] aliases = {"@evilbot", "evilbot", "@evilbot#3634", "evilbot#3634"};

    public MessageListener(EvilBot bot){
        this.bot = bot;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        if(event.getMember().getUser().isBot())
            return;

        String[] args = event.getMessage().getContentRaw().split(" ");

        if(args[0].charAt(0) == prefix){
            System.out.println(args[0].substring(1));
            bot.checkCommand(args[0].substring(1), event);
        }else if(isInArray(aliases, args[0].toLowerCase())){
            if(args[1] == null)
                return;

            bot.checkCommand((args[1].charAt(0) == prefix ? args[1].substring(1) : args[1]), event);
        }
    }
}
