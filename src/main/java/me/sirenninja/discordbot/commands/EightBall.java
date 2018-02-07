package me.sirenninja.discordbot.commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.List;

public class EightBall implements Command {

    @Override
    public String getCommand() {
        return "8ball";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public void onCommand(MessageReceivedEvent event){

    }
}
