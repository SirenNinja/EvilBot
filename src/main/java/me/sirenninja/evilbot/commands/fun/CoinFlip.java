package me.sirenninja.evilbot.commands.fun;

import me.sirenninja.evilbot.commands.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static me.sirenninja.evilbot.utils.Utils.capitalizeFirstCharacter;
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
    public void onCommand(String[] args, MessageReceivedEvent event) {
        // Lulz... the more there are, the more random it'll be, I guess.
        String[] sides = {"Heads", "Tails", "Heads", "Tails", "Heads", "Tails", "Heads", "Tails", "Heads", "Tails", "Heads", "Tails", "Heads", "Tails", "Heads", "Tails", "Heads", "Tails", "Heads", "Tails", "Heads", "Tails", "Heads", "Tails", "Heads", "Tails", "Heads", "Tails", "Heads", "Tails", };

        Random random = new Random();
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(getRandomColor());
        builder.setThumbnail(event.getMember().getUser().getEffectiveAvatarUrl());
        builder.setFooter("Information generated at: " + OffsetDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME), event.getMember().getUser().getEffectiveAvatarUrl());
        builder.setDescription("**Coin Flip**")
                .addField("Side Chosen:", sides[random.nextInt(sides.length)], false);

        event.getChannel().sendMessage(builder.build()).complete();
    }
}
