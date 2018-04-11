package me.sirenninja.evilbot.commands.fun;

import me.sirenninja.evilbot.commands.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static me.sirenninja.evilbot.utils.Utils.getRandomColor;

public class EightBall implements Command {

    private String[] aliases = {"eightball", "ball"};
    private boolean enabled = true;

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean state) {
        enabled = state;
    }

    @Override
    public String getCommand() {
        return "8ball";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList(aliases);
    }

    @Override
    public void onCommand(String[] args, MessageReceivedEvent event){
        StringBuilder sBuilder = new StringBuilder();

        for(int i = 0; i < args.length; i++)
            sBuilder.append(args[i]).append(" ");

        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(getRandomColor());
        builder.setFooter("Information generated at: " + OffsetDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME), event.getMember().getUser().getEffectiveAvatarUrl());
        builder.setDescription("**8Ball**")
                .addField("Question:", sBuilder.toString(), false)
                .addBlankField(false)
                .addField("8Ball Says:", getRandomAnswer(), false);

            event.getChannel().sendMessage(builder.build()).complete();
    }

    private String getRandomAnswer(){
        String[] replies = {
                "Yes.",
                "No.",
                "Maybe.",
                "Maybe not.",
                "It is certain.",
                "It is decidedly so.",
                "Without a doubt.",
                "Yes, definitely.",
                "You may rely on it.",
                "As I see it, Yes.",
                "Most likely.",
                "Outlook good.",
                "Signs point to yes.",
                "Cannot predict now.",
                "Concentrate and ask again.",
                "Don't count on it.",
                "My reply is no.",
                "My sources say no.",
                "Outlook not so good.",
                "Very doubtful."
        };

        Random random = new Random();
        return replies[random.nextInt(replies.length)];
    }
}
