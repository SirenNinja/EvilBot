package me.sirenninja.evilbot.data;

import net.dv8tion.jda.core.entities.Guild;

public class GuildData {

    private Guild guild;
    private char prefix;

    private String joinAndLeaveMessageChannel;
    private boolean hasJoinMessageEnabled;
    private boolean hasLeaveMessageEnabled;
    private String joinMessage;
    private String leftMessage;

    public GuildData(Guild guild){
        this.guild = guild;
        prefix = '!';

        joinAndLeaveMessageChannel = "general";
        hasJoinMessageEnabled = true;
        hasLeaveMessageEnabled = true;
        joinMessage = "Welcome member!";
        leftMessage = "Member has left!";
    }

    public Guild getGuild(){
        return guild;
    }

    public char getPrefix(){
        return prefix;
    }

    public void setPrefix(char letter){
        prefix = letter;
    }

    public String getJoinAndLeaveMessageChannel() {
        return joinAndLeaveMessageChannel;
    }

    public void setJoinAndLeaveMessageChannel(String joinAndLeaveMessageChannel) {
        this.joinAndLeaveMessageChannel = joinAndLeaveMessageChannel;
    }

    public boolean isJoinMessageEnabled() {
        return hasJoinMessageEnabled;
    }

    public void setJoinMessageEnabled(boolean hasJoinMessageEnabled) {
        this.hasJoinMessageEnabled = hasJoinMessageEnabled;
    }

    public boolean isLeaveMessageEnabled() {
        return hasLeaveMessageEnabled;
    }

    public void setLeaveMessageEnabled(boolean hasLeaveMessageEnabled) {
        this.hasLeaveMessageEnabled = hasLeaveMessageEnabled;
    }

    public String getJoinMessage() {
        return joinMessage;
    }

    public void setJoinMessage(String joinMessage) {
        this.joinMessage = joinMessage;
    }

    public String getLeftMessage() {
        return leftMessage;
    }

    public void setLeftMessage(String leftMessage) {
        this.leftMessage = leftMessage;
    }

}
