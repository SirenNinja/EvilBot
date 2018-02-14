package me.sirenninja.evilbot.listeners;

import me.sirenninja.evilbot.EvilBot;
import me.sirenninja.evilbot.data.GuildData;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;

import static me.sirenninja.evilbot.utils.Utils.getRandomColor;
import static me.sirenninja.evilbot.utils.Utils.embedBuilder;

public class JoinAndLeaveListeners extends ListenerAdapter {

    private EvilBot bot;

    public JoinAndLeaveListeners(EvilBot bot){
        this.bot = bot;
    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event){
        GuildData guildData = bot.getApi().findGuild(event.getGuild());

        if(guildData.isJoinMessageEnabled())
            event.getGuild().getTextChannelsByName(guildData.getJoinAndLeaveMessageChannel(), true).get(0).sendMessage(embedBuilder("Member Has Joined!", " ", guildData.getJoinMessage(), null, getRandomColor()).build()).complete();
    }

    @Override
    public void onGuildMemberLeave(GuildMemberLeaveEvent event){
        GuildData guildData = bot.getApi().findGuild(event.getGuild());

        if(guildData.isLeaveMessageEnabled())
            event.getGuild().getTextChannelsByName(guildData.getJoinAndLeaveMessageChannel(), true).get(0).sendMessage(embedBuilder("Member Has Left!", " ", guildData.getLeftMessage(), null, Color.RED).build()).complete();
    }
}
