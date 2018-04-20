package me.sirenninja.evilbot.commands.fun;

import me.sirenninja.evilbot.commands.Command;
import me.sirenninja.evilbot.commands.CommandHandler;
import me.sirenninja.evilbot.utils.math.Calculator;
import me.sirenninja.evilbot.utils.math.ExpressionParser;

import java.util.List;

public class MathEx implements Command {

    private boolean isEnabled = true;

    @Override
    public String getCommand() {
        return "math";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public void setEnabled(boolean state) {
        this.isEnabled = state;
    }

    @Override
    public void onCommand(String[] args, CommandHandler handler) {
        StringBuilder builder = new StringBuilder();

        for(int i = 1; i < args.length; i++)
            builder.append(args[i]).append(" ");

        ExpressionParser<Double> parser = Calculator.DoubleProcessor.createParser();

        try{
            handler.sendMessage("Result: " + parser.parse(builder.toString()));
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
