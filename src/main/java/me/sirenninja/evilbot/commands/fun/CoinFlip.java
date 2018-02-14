package me.sirenninja.evilbot.commands.fun;

import me.sirenninja.evilbot.commands.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

import static me.sirenninja.evilbot.utils.Utils.embedBuilder;
import static me.sirenninja.evilbot.utils.Utils.getRandomColor;

public class CoinFlip implements Command {

    private boolean enabled = true;
    private String[] aliases = {"cf"};

    @Override
    public String getCommand() {
        return "coinflip";
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
    public void onCommand(MessageReceivedEvent event) {
        // Lulz...
        String[] sides = {"Heads", "Tails", "Heads", "Tails", "Heads", "Tails", "Heads", "Tails", "Heads", "Tails", "Heads", "Tails", "Heads", "Tails", "Heads", "Tails", "Heads", "Tails", "Heads", "Tails", "Heads", "Tails", "Heads", "Tails", "Heads", "Tails", "Heads", "Tails", "Heads", "Tails", };

        int side = 0;

        for(int i = 0; i < 67; i++)
            side = (int) (Math.random() * sides.length);

        event.getChannel().sendMessage(embedBuilder("CoinFlip", "Side chosen:", sides[side], null, getRandomColor()).build()).complete();
    }
}
