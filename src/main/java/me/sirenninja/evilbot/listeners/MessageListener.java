package me.sirenninja.evilbot.listeners;

import me.sirenninja.evilbot.EvilBot;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {

    // The EvilBot instance.
    private EvilBot bot;

    // The default prefix. This will be moved when the guild
    // settings are created and finished.

    // The constructor for this class.
    public MessageListener(EvilBot bot){
        this.bot = bot;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        if(event.getMember().getUser().isBot() || event.getMember().getUser() == event.getJDA().getSelfUser())
            return;

        String[] args = event.getMessage().getContentRaw().split(" ");


        if(args[0].charAt(0) == bot.getApi().findGuild(event.getGuild()).getPrefix()){
            System.out.println("Command used: " + args[0].substring(1));
            bot.checkCommand(args[0].substring(1), event);
        }else if(args[0].equalsIgnoreCase(event.getJDA().getSelfUser().getAsMention())){
            System.out.println("Command used (from mentioning): " + args[1]);
            bot.checkCommand(args[1], event);
        }
    }
}
