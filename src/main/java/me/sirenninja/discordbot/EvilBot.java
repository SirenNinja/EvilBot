package me.sirenninja.discordbot;

import me.sirenninja.discordbot.commands.Command;
import me.sirenninja.discordbot.commands.EightBall;
import me.sirenninja.discordbot.data.Data;
import me.sirenninja.discordbot.listeners.MessageListener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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

    public EvilBot(){
        bot = this;
    }

    public static void main(String[] args) throws Exception{
        data = new Data();

        if(data.getKey().equalsIgnoreCase("DISCORD-API-KEY")) {
            System.out.println("Config has been created and loaded! Please look at the file and restart!");
            return;
        }

        jda = new JDABuilder(AccountType.BOT).setToken(data.getKey()).setStatus(OnlineStatus.valueOf(data.getStatus())).buildAsync();
        jda.addEventListener(new MessageListener(bot));
        jda.setAutoReconnect(true);

        updateServers();
        addCommands();
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
    }

    private static void updateServers(){
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        Runnable runnable = () -> {
            if(data.getGame() != null || data.getGame().equalsIgnoreCase("null"))
                jda.getPresence().setPresence(OnlineStatus.valueOf(data.getStatus()), Game.playing(data.getGame().replace("%servers%", String.valueOf(jda.getGuilds().size()))), true);
        };

        executor.scheduleAtFixedRate(runnable, 0, 300, TimeUnit.SECONDS);
    }
}
