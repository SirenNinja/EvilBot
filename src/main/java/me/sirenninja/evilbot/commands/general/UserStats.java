package me.sirenninja.evilbot.commands.general;

import me.sirenninja.evilbot.commands.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class UserStats implements Command {

    private String[] aliases = {"userstats", "stat", "stats"};
    private boolean enabled = true;

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean state) {
        enabled = state;
    }

    @Override
    public String getCommand() {
        return "userstat";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList(aliases);
    }

    @Override
    public void onCommand(String[] args, MessageReceivedEvent event) {
        Member member = event.getMember();

        String game = "null";

        try{
            game = member.getGame().getName();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(member.getColor());
        builder.setThumbnail(member.getUser().getEffectiveAvatarUrl());
        builder.setFooter("Information generated at: " + OffsetDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME), member.getUser().getEffectiveAvatarUrl());
        builder.setDescription("**User information (__" + member.getUser().getName() + "__):**")
                .addField("Name:", member.getUser().getName() + "#" + member.getUser().getDiscriminator(), true)
                .addField("Nickname:", member.getEffectiveName(), true)
                .addField("ID:", member.getUser().getId(), true)
                .addBlankField(false)
                .addField("Current Status:", member.getOnlineStatus().getKey(), true)
                .addField("Current Game:", game, true)
                .addBlankField(false)
                .addField("Joined Discord:", member.getUser().getCreationTime().format(DateTimeFormatter.RFC_1123_DATE_TIME), true)
                .addField("Joined This Guild:", member.getJoinDate().format(DateTimeFormatter.RFC_1123_DATE_TIME), true);

        event.getChannel().sendMessage(builder.build()).complete();
    }
}
