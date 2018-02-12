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
     * Add settings for separate Guilds.
     * Add a system to disallow new accounts to join.
     * Add role support for the commands.
     * Add cooldown for commands. (possibly role based.)
     * Add more settings (changeable prefix, bot name, etc.)
     * Join / leave messages (and make them modifiable.)
     * Logs (kicks, bans, deletes, etc.)
     * Auto role.
     * Toggleable commands.
     * Verification like thing.
     * Custom responses for some commands.
     * ADD A DAMN HELP COMMAND.
     *
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


    // The bot instance.
    private static EvilBot bot;

    // JDA, Data, and EvilBotAPI instances.
    private static JDA jda;
    private static Data data;
    private static EvilBotAPI api;

    public static void main(String[] args) throws Exception{
        data = new Data();
        bot = new EvilBot();
        api = new EvilBotAPI(bot).getAPI();

        // Checks if the DISCORD API KEY was added.
        if(data.getKey().equalsIgnoreCase("DISCORD-API-KEY")) {
            System.out.println("Config has been created and loaded! Please look at the file and restart!");
            return;
        }

        addCommands();

        // Builds the bot and starts it.
        jda = new JDABuilder(AccountType.BOT).setToken(data.getKey()).setStatus(OnlineStatus.valueOf(data.getStatus())).buildAsync();
        jda.setAutoReconnect(true);

        addListeners();
    }

    /**
     * Simply just a way to clean up the commands.
     * This is where all of the commands get added.
     */
    private static void addCommands(){
        api.addCommand(new EightBall(), new UserStats());
    }

    private static void addListeners(){
        api.addListener(new MessageListener(bot), new JoinAndLeaveListeners(), new Ready(bot));
    }

    /**
     * Checks the command when someone uses the bot's prefix.
     * @param command
     *        The first argument of the message.
     * @param event
     *        The MessageReceivedEvent event.
     */
    public void checkCommand(String command, MessageReceivedEvent event){
        for(Command c : api.getCommands()){
            List<String> aliases = c.getAliases();

            if(c.getCommand().equalsIgnoreCase(command) || aliases.contains(command.toLowerCase())){
                if(!(c.isEnabled()))
                    return;

                c.onCommand(event);
                return;
            }
        }
    }

    /**
     * The instance of the Data.
     * @return
     *        The Data instance.
     */
    public Data getData(){
        return data;
    }

    /**
     * The instance of the current JDA.
     * @return
     *        The JDA instance.
     */
    public JDA getJDA(){
        return jda;
    }

    /**
     * The  instance of the Bot.
     * @return
     *        The Bot instance.
     */
    public static EvilBot getBot() {
        return bot;
    }

    /**
     * The instance of the API.
     * @return
     *        The API instance.
     */
    public EvilBotAPI getApi(){
        return api;
    }
}
