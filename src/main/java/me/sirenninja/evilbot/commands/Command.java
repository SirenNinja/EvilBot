package me.sirenninja.evilbot.commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.List;

public interface Command {

    String getCommand();

    List<String> getAliases();

    void onCommand(MessageReceivedEvent event);

}
