package me.sirenninja.evilbot.commands.general;

import me.sirenninja.evilbot.commands.Command;
import me.sirenninja.evilbot.commands.CommandHandler;
import net.dv8tion.jda.core.EmbedBuilder;

import java.awt.*;
import java.util.List;

public class Ping implements Command {

    boolean enabled = true;

    @Override
    public String getCommand() {
        return "ping";
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
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.BLUE);
        builder.setAuthor("Pong!");
        builder.setDescription("Ping is: " + handler.getEvent().getJDA().getPing() + "ms");

        handler.sendMessage(builder);
    }
}
