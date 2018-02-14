package me.sirenninja.evilbot.plugins;

import me.sirenninja.evilbot.EvilBotPlugin;

public class Plugin {

    private EvilBotPlugin plugin;
    private String name;
    private String version;

    public Plugin(EvilBotPlugin plugin){
        this.plugin = plugin;
        this.name = plugin.pluginName();
        this.version = plugin.pluginVersion();
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }
}
