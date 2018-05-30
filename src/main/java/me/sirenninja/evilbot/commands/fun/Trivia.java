package me.sirenninja.evilbot.commands.fun;

import me.sirenninja.evilbot.EvilBot;
import me.sirenninja.evilbot.commands.Command;
import me.sirenninja.evilbot.commands.CommandHandler;
import me.sirenninja.evilbot.utils.trivia.Question;
import net.dv8tion.jda.core.EmbedBuilder;

import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

public class Trivia implements Command {

    private boolean enabled = true;
    private EvilBot bot;

    public Trivia(EvilBot bot){
        this.bot = bot;
    }

    @Override
    public String getCommand() {
        return "trivia";
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
        this.enabled = state;
    }

    @Override
    public void onCommand(String[] args, CommandHandler handler) {
        // Checks if the player already has a question unanswered.
        if(bot.getApi().getPlayingTrivia().containsKey(handler.getMember())){
            handler.sendMessage("You already have a trivia question in progress.");
            return;
        }

        bot.getApi().getPlayingTrivia().remove(handler.getMember());
        Question question = bot.getApi().getTriviaManager().getQuestion();

        // Randomizes the list of answers.
        List<String> answers = question.getIncorrect();
        answers.add(question.getCorrect());
        Collections.shuffle(answers);

        // Grabs the correct letter.
        String correctLetter = "";
        for(int i = 0; i < answers.size(); i++){
            if(answers.get(0).equalsIgnoreCase(question.getCorrect()))
                continue;

            switch(i){
                case 1:
                    correctLetter = "b";
                    break;
                case 2:
                    correctLetter = "c";
                    break;
                case 3:
                    correctLetter = "d";
                    break;
                default:
                    correctLetter = "a";
            }
        }

        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor((question.getDifficulty().equalsIgnoreCase("hard") ? Color.RED : (question.getDifficulty().equalsIgnoreCase("medium") ? Color.YELLOW : Color.GREEN)));
        builder.setFooter("Information generated at: " + handler.getEvent().getMessage().getCreationTime().format(DateTimeFormatter.RFC_1123_DATE_TIME), handler.getUser().getEffectiveAvatarUrl());
        builder.setDescription("**Trivia for __" + handler.getUser().getName() + "__** - Reply with one of the letters to answer.")
                .addField("Question:", "**" + question.getQuestion() + "**", false)
                .addBlankField(false)
                .addField("Category:", question.getCategory(), true)
                .addField("Difficulty:", question.getDifficulty(), true)
                .addBlankField(false)
                .addField("Answers:", "**" + String.format("A. %s\nB. %s\n", answers.get(0), answers.get(1)) + (answers.size() > 2 ? String.format("C. %s\nD. %s", answers.get(2), answers.get(3)) : "") + "**",false);

        handler.sendMessage(builder);
        bot.getApi().getPlayingTrivia().put(handler.getMember(), new Question(question, correctLetter, handler.getEvent().getTextChannel()));
    }
}
