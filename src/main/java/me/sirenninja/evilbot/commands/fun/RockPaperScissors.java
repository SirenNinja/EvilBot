package me.sirenninja.evilbot.commands.fun;

import me.sirenninja.evilbot.commands.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static me.sirenninja.evilbot.utils.Utils.arrayContains;
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
    public void onCommand(MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        String[] correctArgs = {"rock", "paper", "scissors"};

        if(!(arrayContains(correctArgs, args[1].toLowerCase()))){
            event.getChannel().sendMessage(event.getAuthor().getAsMention() + ", please use !rps [rock, paper, scissors].").complete();
            return;
        }

        int decision = 0;

        for(int i = 0; i < 67; i++)
            decision = (int) (Math.random() * correctArgs.length);

        String user = args[1];
        String bot = correctArgs[decision];

        if(user.equalsIgnoreCase(bot)){
            event.getChannel().sendMessage(embedBuilder("Rock Paper Scissors", "Bot has picked: " + bot, "Draw!", null, Color.GRAY).build()).complete();
            return;
        }

        event.getChannel().sendMessage(embedBuilder("Rock Paper Scissors", "Bot has picked: " + bot, (didUserWin(user, bot) ? "You won!" : "You lost!"), null, (didUserWin(user, bot) ? Color.GREEN : Color.RED)).build()).complete();
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
