package me.sirenninja.test.listeners;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        if(!(event.getMember().getUser().isBot()) && !(event.getMember().getUser() == event.getJDA().getSelfUser()))
            return;

        if(event.getMessage().isMentioned(event.getJDA().getSelfUser()))
            event.getChannel().sendMessage("Hello!").complete();
    }

}
