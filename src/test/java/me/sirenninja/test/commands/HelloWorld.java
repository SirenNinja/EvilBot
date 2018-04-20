package me.sirenninja.test.commands;

import me.sirenninja.evilbot.commands.Command;
import me.sirenninja.evilbot.commands.CommandHandler;

import java.util.List;

public class HelloWorld implements Command {

    private boolean enabled = true;

    @Override
    public String getCommand() {
        return "hello";
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
    public void onCommand(String[] args, CommandHandler handler) {
        handler.sendMessage("Hello world!");
    }
}
