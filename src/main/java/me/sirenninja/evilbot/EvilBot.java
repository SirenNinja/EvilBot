package me.sirenninja.evilbot;

import me.sirenninja.evilbot.commands.Command;
import me.sirenninja.evilbot.commands.fun.EightBall;
import me.sirenninja.evilbot.commands.general.UserStats;
import me.sirenninja.evilbot.data.Data;
import me.sirenninja.evilbot.listeners.JoinAndLeaveListeners;
import me.sirenninja.evilbot.listeners.MessageListener;
import me.sirenninja.evilbot.listeners.Ready;
import me.sirenninja.evilbot.plugins.Plugin;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.jar.JarFile;

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

    private static String pluginsFolder = "plugins";
    private static ArrayList<Plugin> plugins = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        data = new Data();
        bot = new EvilBot();
        api = new EvilBotAPI(bot);

        File folder = new File(pluginsFolder);
        if(!(folder.exists()))
            folder.mkdir();

        // Checks if the DISCORD API KEY was added.
        if(data.getKey().equalsIgnoreCase("DISCORD-API-KEY")) {
            System.out.println("Config has been created and loaded! Please look at the file and restart!");
            return;
        }

        // Builds the bot and starts it.
        jda = new JDABuilder(AccountType.BOT).setToken(data.getKey()).setStatus(OnlineStatus.valueOf(data.getStatus())).buildAsync();
        jda.setAutoReconnect(true);

        addCommands();
        addListeners();

        loadPlugins(folder);
    }

    /**
     * Simply just a way to clean up the commands.
     * This is where all of the commands get added.
     */
    private static void addCommands(){
        getBot().getApi().addCommand(new EightBall(), new UserStats());
    }

    private static void addListeners(){
        getBot().getApi().addListener(new MessageListener(getBot()), new JoinAndLeaveListeners(getBot()), new Ready(getBot()));
    }

    private static void loadPlugins(File folder){
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".jar"));
        ArrayList<URL> urls = new ArrayList<>();
        ArrayList<String> classes = new ArrayList<>();

        if(!(files == null)){
            Arrays.asList(files).forEach(file -> {
                try{
                    JarFile jarFile = new JarFile(file);
                    urls.add(new URL("jar:file:" + pluginsFolder + "/" + file.getName() + "!/"));

                    jarFile.stream().forEach(jarEntry -> classes.add(jarEntry.getName()));
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
            });

            URLClassLoader pluginLoader = new URLClassLoader(urls.toArray(new URL[urls.size()]));
            classes.forEach(s -> {
                try{
                    Class clazz = pluginLoader.loadClass(s.replaceAll("/", ".").replace(".class", ""));
                    Class[] interfaces = clazz.getInterfaces();

                    for(Class anInterface : interfaces){
                        if(anInterface == EvilBotPlugin.class){
                            EvilBotPlugin plugin = (EvilBotPlugin) clazz.newInstance();
                            plugin.onLoad();

                            plugins.add(new Plugin(plugin));
                        }
                    }
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
            });
        }

        StringBuilder builder = new StringBuilder("Plugins loaded: ");
        String message = "%name% [%version%]";
        for(Plugin plugin : plugins)
            builder.append(message.replace("%name%", plugin.getName()).replace("%version%", plugin.getVersion())).append(", ");

        if(builder.length() > 16)
            builder.setLength(builder.length()-2);
        else
            builder.append("None.");

        System.out.println(builder.toString());
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

            List<String> aliases = new ArrayList<>();

            try{
                aliases = c.getAliases();
            }catch(Exception ex){
                System.out.println("Command has no aliases!");
            }

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
