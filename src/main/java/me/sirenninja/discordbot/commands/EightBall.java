package me.sirenninja.discordbot.commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.List;
import java.util.Random;

public class EightBall implements Command {

    @Override
    public String getCommand() {
        return "8ball";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public void onCommand(MessageReceivedEvent event){
        event.getChannel().sendMessage("8ball says: " + getRandomAnswer()).complete();
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

        int reply = 0;
        Random random = new Random();

        for(int i = 0; i < 67; i++)
            reply = random.nextInt(replies.length);

        return replies[reply];
    }
}
