package me.sirenninja.evilbot.listeners;

import me.sirenninja.evilbot.EvilBot;
import me.sirenninja.evilbot.utils.trivia.Question;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {

    // The EvilBot instance.
    private EvilBot bot;

    // The default prefix. This will be moved when the guild
    // settings are created and finished.

    // The constructor for this class.
    public MessageListener(EvilBot bot){
        this.bot = bot;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        if(event.getAuthor().isBot() || event.getAuthor() == event.getJDA().getSelfUser() || event.getMessage().getAttachments().size() > 0)
            return;

        String[] args = event.getMessage().getContentRaw().split(" ");
        Character c = (event.getChannelType().isGuild() ? bot.getApi().findGuild(event.getGuild()).getPrefix() : '!');

        if(args[0].charAt(0) == c){
            System.out.println(String.format("Command used in %s by %s#%s: %s", (event.getChannelType().isGuild() ? event.getGuild().getName() : "PM"), event.getAuthor().getName(), event.getAuthor().getDiscriminator(), args[0].substring(1)));
            bot.checkCommand(args[0].substring(1), event.getMessage().getContentRaw(), event);
            return;
        }else if(args[0].equalsIgnoreCase(event.getJDA().getSelfUser().getAsMention())){
            System.out.println(String.format("Command used (from mentioning) in %s by %s#%s: %s", (event.getChannelType().isGuild() ? event.getGuild().getName() : "PM"), event.getAuthor().getName(), event.getAuthor().getDiscriminator(), args[0].substring(1)));
            bot.checkCommand(args[1], event.getMessage().getContentRaw(), event);
            return;
        }

        if(bot.getApi().getPlayingTrivia().containsKey(event.getMember())){
            Question q = bot.getApi().getPlayingTrivia().get(event.getMember());
            if(!(event.getTextChannel() == q.getChannel()))
                return;

            if(q.isCorrect(event.getMessage().getContentRaw()))
                event.getChannel().sendMessage("Correct.").complete();
            else
                event.getChannel().sendMessage("Incorrect. Correct answer was: " + q.getCorrectLetter()).complete();

            bot.getApi().getPlayingTrivia().remove(event.getMember());
        }
    }
}
