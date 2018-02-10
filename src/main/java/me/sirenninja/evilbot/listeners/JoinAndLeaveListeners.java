package me.sirenninja.evilbot.listeners;

import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;

import static me.sirenninja.evilbot.utils.Utils.getRandomColor;
import static me.sirenninja.evilbot.utils.Utils.embedBuilder;

public class JoinAndLeaveListeners extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event){
        event.getGuild().getTextChannelsByName("general", true).get(0).sendMessage(embedBuilder("Member Has Joined!", " ", "Welcome " + event.getMember().getAsMention() + " to " + event.getGuild().getName() + "!", event.getMember().getUser().getEffectiveAvatarUrl(), getRandomColor()).build()).complete();
    }

    @Override
    public void onGuildMemberLeave(GuildMemberLeaveEvent event){
        event.getGuild().getTextChannelsByName("general", true).get(0).sendMessage(embedBuilder("Member Has Left!", " ", event.getMember().getAsMention() + " has left!", event.getMember().getUser().getEffectiveAvatarUrl(), Color.RED).build()).complete();
    }

}
