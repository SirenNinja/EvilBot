package me.sirenninja.evilbot.commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.List;

public interface Command {

    /**
     * Grabs the native command.
     * @return
     *        The command.
     */
    String getCommand();

    /**
     * Grabs the list of aliases.
     * @return
     *        A list of aliases.
     */
    List<String> getAliases();

    /**
     * Checks if the command is enabled.
     * @return
     *        The enable state of the command.
     */
    boolean isEnabled();

    /**
     * Sets the enabled state of the command.
     * @param state
     *        The boolean.
     */
    void setEnabled(boolean state);

    /**
     * When the bot finds a command, this void gets called.
     * @param event
     *        The MessageReceivedEvent event.
     */
    void onCommand(MessageReceivedEvent event);

}
