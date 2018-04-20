package me.sirenninja.evilbot.listeners;

import me.sirenninja.evilbot.EvilBot;
import me.sirenninja.evilbot.data.GuildData;
import net.dv8tion.jda.core.events.guild.GuildBanEvent;
import net.dv8tion.jda.core.events.guild.GuildUnbanEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageDeleteEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageUpdateEvent;
import net.dv8tion.jda.core.events.role.RoleCreateEvent;
import net.dv8tion.jda.core.events.role.RoleDeleteEvent;
import net.dv8tion.jda.core.events.user.UserTypingEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;

import static me.sirenninja.evilbot.utils.Utils.embedBuilder;

public class LoggingListeners extends ListenerAdapter {

    private EvilBot bot;

    public LoggingListeners(EvilBot bot){
        this.bot = bot;
    }

    @Override
    public void onGuildMessageDelete(GuildMessageDeleteEvent event){
        // log.
    }

    @Override
    public void onGuildMessageUpdate(GuildMessageUpdateEvent event){
        // log.
    }

    @Override
    public void onRoleCreate(RoleCreateEvent event){
        // log.
    }

    @Override
    public void onRoleDelete(RoleDeleteEvent event){
        // log.
    }

    @Override
    public void onGuildBan(GuildBanEvent event){
        // log.
    }

    @Override
    public void onGuildUnban(GuildUnbanEvent event){
        // log.
    }

    @Override
    public void onGuildVoiceJoin(GuildVoiceJoinEvent event){
        // log.
    }

    @Override
    public void onGuildVoiceLeave(GuildVoiceLeaveEvent event){
        // log.
    }
}
