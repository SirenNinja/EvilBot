package me.sirenninja.evilbot;

import me.sirenninja.evilbot.commands.Command;
import me.sirenninja.evilbot.commands.fun.EightBall;
import me.sirenninja.evilbot.commands.general.UserStats;
import me.sirenninja.evilbot.data.Data;
import me.sirenninja.evilbot.listeners.JoinAndLeaveListeners;
import me.sirenninja.evilbot.listeners.MessageListener;
import me.sirenninja.evilbot.listeners.Ready;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;

public class EvilBot {


    /**
     * TODO:
     * Add a system to disallow new accounts to join.
     * Add role support for the commands.
     * Add cooldown for commands. (possibly role based.)
     * Add more settings (changeable prefix, bot name, etc.)
     * Join / leave messages.
     * Logs (kicks, bans, deletes, etc.)
     * Auto role.
     * Toggleable commands.
     * Verification like thing.
     * Custom responses for some commands.
     * ADD A DAMN HELP COMMAND.
     *
     * TODO PROGRESSIVELY:
     * Add commands.
     *
     *
     * TODO EVENTUALLY:
     * Add music support.
     * Twitter / YouTube / Twitch / Mixer notifications.
     * Leveling system.
     */



    private static EvilBot bot;

    private static List<Command> commands = new ArrayList<>();

    private static JDA jda;
    private static Data data;

    public static void main(String[] args) throws Exception{
        data = new Data();
        bot = new EvilBot();

        if(data.getKey().equalsIgnoreCase("DISCORD-API-KEY")) {
            System.out.println("Config has been created and loaded! Please look at the file and restart!");
            return;
        }

        addCommands();

        jda = new JDABuilder(AccountType.BOT).setToken(data.getKey()).setStatus(OnlineStatus.valueOf(data.getStatus())).buildAsync();
        jda.setAutoReconnect(true);

        jda.addEventListener(new MessageListener(bot));
        jda.addEventListener(new JoinAndLeaveListeners());
        jda.addEventListener(new Ready(bot));
    }

    private static void addCommand(Command clazz){
        if(!(commands.contains(clazz)))
            commands.add(clazz);
    }

    public void checkCommand(String command, MessageReceivedEvent event){
        for(Command c : commands){
            List<String> aliases = c.getAliases();

            if(c.getCommand().equalsIgnoreCase(command) || aliases.contains(command.toLowerCase())){
                c.onCommand(event);
                return;
            }
        }
    }

    private static void addCommands(){
        addCommand(new EightBall());
        addCommand(new UserStats());
    }

    public Data getData(){
        return data;
    }

    public JDA getJDA(){
        return jda;
    }

    public static EvilBot getBot() {
        return bot;
    }
}
