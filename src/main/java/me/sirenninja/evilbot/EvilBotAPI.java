package me.sirenninja.evilbot;

import me.sirenninja.evilbot.commands.Command;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;

public class EvilBotAPI {

    // The EvilBotAPI instance.
    private EvilBotAPI api;

    // The EvilBot instance.
    private EvilBot bot;

    // The List of commands (used by classes.)
    private List<Command> commands = new ArrayList<>();

    /**
     * Allows other programs to hook into EvilBot and use the API to add commands and listeners.
     * @param bot
     *        The EvilBot instance.
     */
    public EvilBotAPI(EvilBot bot){
        if(api != null)
            api = this;

        this.bot = bot;
    }

    /**
     * Grabs the API so other programs and hook into EvilBot.
     * @return
     *        The API instance.
     */
    public EvilBotAPI getAPI(){
        return api;
    }

    /**
     * Grabs the JDA instance from the Evilbot class.
     * @return
     *        The JDA instance.
     */
    public JDA getJDA(){
        return bot.getJDA();
    }

    /**
     * Adds a command (or technically "class".)
     * @param clazz
     *        The class that implements Command.
     */
    public void addCommand(Command clazz){
        if(!(commands.contains(clazz)))
            commands.add(clazz);
    }

    /**
     * Adds multiple commands at the same time.
     * @param classes
     *        A list of classes that implement Command.
     */
    public void addCommand(Command... classes){
        for(Command clazz : classes)
            addCommand(clazz);
    }

    /**
     * Adds a listener.
     * @param clazz
     *        A class that implements ListenerAdapter.
     */
    public void addListener(ListenerAdapter clazz){
        bot.getJDA().addEventListener(clazz);
    }

    /**
     * Adds multiple listeners at the same time.
     * @param classes
     *        A list of classes that implement ListenerAdapter.
     */
    public void addListener(ListenerAdapter... classes){
        for(ListenerAdapter clazz : classes)
            bot.getJDA().addEventListener(clazz);
    }

    /**
     * Sets the enabled state of the command.
     * @param clazz
     *        The class that implements Command.
     * @param state
     *        The boolean state to change the current state of the command.
     */
    public void setEnabled(Command clazz, Boolean state){
        clazz.setEnabled(state);
    }

    /**
     * Gets the boolean state of the command.
     * @param clazz
     *        The class that implements Command.
     * @return
     *        The enabled state of the command.
     */
    public boolean isEnabled(Command clazz){
        return clazz.isEnabled();
    }


    /**
     * Get's the commands List.
     * @return
     *        The commands List.
     */
    public List<Command> getCommands(){
        return commands;
    }
}
