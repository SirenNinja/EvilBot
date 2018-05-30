package me.sirenninja.evilbot.utils.trivia;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.sirenninja.evilbot.EvilBotAPI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class TriviaManager { // https://opentdb.com/api.php?amount=10

    private EvilBotAPI api;

    public TriviaManager(EvilBotAPI api){
        this.api = api;
    }

    public void addNewQuestions(int amount){
        System.out.println("Adding questions..");

        try{
            URL url = new URL("https://opentdb.com/api.php?amount=" + amount);
            URLConnection conn = url.openConnection();
            conn.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoOutput(true);
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder builder = new StringBuilder();
            for(int line; (line = in.read()) >= 0;)
                builder.append((char)line);

            String o;
            String output = builder.toString();
            do {
                o = output;
                output = URLDecoder.decode(o, "UTF-8");

            } while (!o.equals(output));

            JsonObject json = new JsonParser().parse(output).getAsJsonObject();
            JsonArray results = json.get("results").getAsJsonArray();

            for(JsonElement result : results){
                JsonObject r = result.getAsJsonObject();

                String category = r.get("category").getAsString();
                String difficulty = r.get("difficulty").getAsString();
                String question = r.get("question").getAsString();
                String correct = r.get("correct_answer").getAsString();

                List<String> incorrect = new ArrayList<>();
                for(JsonElement i : r.get("incorrect_answers").getAsJsonArray())
                    incorrect.add(i.getAsString());

                api.getQuestions().add(new Question(category, difficulty, question, correct, incorrect));
            }
        }catch(Exception ex){
            ex.getMessage();
            ex.printStackTrace();
        }

        System.out.println("Done.");
    }

    public Question getQuestion(){
        if(api.getQuestions().size() <= 0)
            addNewQuestions(10);

        Question question = api.getQuestions().get(0);
        api.getQuestions().remove(0);

        return question;
    }
}
