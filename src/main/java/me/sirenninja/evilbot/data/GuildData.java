package me.sirenninja.evilbot.data;

import net.dv8tion.jda.core.entities.Guild;

public class GuildData {

    private Guild guild;
    private char prefix;

    public GuildData(Guild guild){
        this.guild = guild;
        prefix = '!';
    }

    public Guild getGuild(){
        return guild;
    }

    public char getPrefix(){
        return prefix;
    }

}
