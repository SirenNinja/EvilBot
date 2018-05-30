package me.sirenninja.evilbot.utils.trivia;

import net.dv8tion.jda.core.entities.TextChannel;

import java.util.List;

public class Question {

    private String category;
    private String difficulty;
    private String question;
    private String correct;
    private List<String> incorrect;

    private String correctLetter;
    private TextChannel channel;

    public Question(String category, String difficulty, String question, String correct, List<String> incorrect){
        this.category = category;
        this.difficulty = difficulty;
        this.question = question;
        this.correct = correct;
        this.incorrect = incorrect;
    }

    public Question(Question question, String correctLetter, TextChannel channel){
        this.category = question.getCategory();
        this.difficulty = question.getDifficulty();
        this.question = question.getQuestion();
        this.correct = question.getCorrect();
        this.incorrect = question.getIncorrect();
        this.correctLetter = correctLetter;
        this.channel = channel;
    }

    public String getCategory() {
        return category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrect() {
        return correct;
    }

    public List<String> getIncorrect() {
        return incorrect;
    }

    public boolean isCorrect(String letter){
        return letter.equalsIgnoreCase(this.correctLetter);
    }

    public String getCorrectLetter(){
        return correctLetter.toUpperCase();
    }

    public TextChannel getChannel() {
        return channel;
    }
}
