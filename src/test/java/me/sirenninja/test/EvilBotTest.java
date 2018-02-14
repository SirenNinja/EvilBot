package me.sirenninja.test;

import me.sirenninja.evilbot.EvilBot;
import me.sirenninja.evilbot.EvilBotAPI;
import me.sirenninja.evilbot.EvilBotPlugin;
import me.sirenninja.test.commands.HelloWorld;
import me.sirenninja.test.listeners.MessageListener;

public class EvilBotTest implements EvilBotPlugin {

    @Override
    public String pluginName() {
        return "HelloWorld";
    }

    @Override
    public String pluginAuthor() {
        return "SirenNinja";
    }

    @Override
    public String pluginVersion() {
        return "1.0";
    }

    @Override
    public void onLoad(){
        EvilBotAPI api = EvilBot.getBot().getApi();

        api.addCommand(new HelloWorld());
        api.addListener(new MessageListener());
    }
}
