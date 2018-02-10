package me.sirenninja.evilbot.listeners;

import me.sirenninja.evilbot.EvilBot;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Ready extends ListenerAdapter {

    private EvilBot bot;

    public Ready(EvilBot bot){
        this.bot = bot;
    }

    @Override
    public void onReady(ReadyEvent event){
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        Runnable runnable = () -> {
            if(bot.getData().getGame() != null || bot.getData().getGame().equalsIgnoreCase("null"))
                event.getJDA().getPresence().setPresence(OnlineStatus.valueOf(bot.getData().getStatus()), Game.playing(bot.getData().getGame().replace("%servers%", String.valueOf(event.getJDA().getGuilds().size()))), true);
        };

        executor.scheduleAtFixedRate(runnable, 0, 300, TimeUnit.SECONDS);
    }
}
