package me.sirenninja.evilbot.commands.fun;

import me.sirenninja.evilbot.commands.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static me.sirenninja.evilbot.utils.Utils.arrayContains;
import static me.sirenninja.evilbot.utils.Utils.capitalizeFirstCharacter;
import static me.sirenninja.evilbot.utils.Utils.embedBuilder;

public class RockPaperScissors implements Command {

    private boolean enabled = true;
    private String[] aliases = {"rps"};

    @Override
    public String getCommand() {
        return "rockpaperscissors";
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
        String[] correctArgs = {"rock", "paper", "scissors"};

        if(!(arrayContains(correctArgs, args[1].toLowerCase()))){
            event.getChannel().sendMessage(event.getAuthor().getAsMention() + ", please use !rps [rock, paper, scissors].").complete();
            return;
        }

        Random random = new Random();
        int decision = random.nextInt(correctArgs.length);

        String user = args[1];
        String bot = correctArgs[decision];

        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor((user.equalsIgnoreCase(bot) ? Color.GRAY : (didUserWin(user, bot) ? Color.GREEN : Color.RED)));
        builder.setThumbnail(event.getMember().getUser().getEffectiveAvatarUrl());
        builder.setFooter("Information generated at: " + OffsetDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME), event.getMember().getUser().getEffectiveAvatarUrl());
        builder.setDescription("**Rock Paper Scissors**")
                .addField("You chose:", capitalizeFirstCharacter(user), true)
                .addField("Bot chose:", capitalizeFirstCharacter(bot), true)
                .addBlankField(false)
                .addField("Result:", (user.equalsIgnoreCase(bot) ? "Draw!" : (didUserWin(user, bot) ? "You won!" : "Bot one!")), false);

        event.getChannel().sendMessage(builder.build()).complete();
    }

    private boolean didUserWin(String user, String bot){
        if(user.equalsIgnoreCase("rock") && bot.equalsIgnoreCase("scissors"))
            return true;
        else if(user.equalsIgnoreCase("paper") && bot.equalsIgnoreCase("rock"))
            return true;
        else if(user.equalsIgnoreCase("scissors") && bot.equalsIgnoreCase("paper"))
            return true;

        return false;
    }
}
