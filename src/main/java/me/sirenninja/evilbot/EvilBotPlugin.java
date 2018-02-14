package me.sirenninja.evilbot;

public interface EvilBotPlugin {

    String pluginName();

    String pluginVersion();

    String pluginAuthor();

    void onLoad();

}
