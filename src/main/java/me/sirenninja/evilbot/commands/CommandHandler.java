package me.sirenninja.evilbot.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandHandler {

    private MessageReceivedEvent event;

    public CommandHandler(MessageReceivedEvent event){
        this.event = event;
    }

    public MessageReceivedEvent getEvent() {
        return event;
    }

    public boolean isGuild(){
        return this.event.getChannelType().isGuild();
    }

    public User getUser(){
        return event.getAuthor();
    }

    public Member getMember(){
        return event.getMember();
    }

    public void sendMessage(String message){
        event.getChannel().sendMessage(message).complete();
    }

    public void sendMessage(EmbedBuilder builder){
        event.getChannel().sendMessage(builder.build()).complete();
    }
}
