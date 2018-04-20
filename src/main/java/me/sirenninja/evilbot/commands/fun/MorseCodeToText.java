package me.sirenninja.evilbot.commands.fun;

import me.sirenninja.evilbot.commands.Command;
import me.sirenninja.evilbot.commands.CommandHandler;
import me.sirenninja.evilbot.utils.morsecode.MorseCode;
import net.dv8tion.jda.core.EmbedBuilder;

import java.util.Arrays;
import java.util.List;

import static me.sirenninja.evilbot.utils.Utils.getRandomColor;

public class MorseCodeToText implements Command {

    private String[] aliases = {"mtt", "mctt", "morsetotext"};
    private boolean enabled = true;

    @Override
    public String getCommand() {
        return "morsecodetotext";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList(aliases);
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
        StringBuilder message = new StringBuilder();

        for(int i = 1; i < args.length; i++)
            message.append(args[i]).append(" ");

        message.setLength(message.length() - 1);
        MorseCode mc = new MorseCode(false, message.toString());

        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(getRandomColor());
        builder.setDescription("**Text to Morse Code**")
                .addField("Morse Code:", message.toString(), false)
                .addBlankField(false)
                .addField("Text:", mc.convertToNormalMessage(), true);

        handler.sendMessage(builder);
    }
}
