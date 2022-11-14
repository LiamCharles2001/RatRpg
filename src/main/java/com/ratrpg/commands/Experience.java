package com.ratrpg.commands;

import com.ratrpg.utilities.Message;
import com.ratrpg.utilities.PlayerUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.ratrpg.utilities.ExperienceUtil.changeExp;
import static com.ratrpg.utilities.ExperienceUtil.getExpToNext;

public class Experience {
    //Gives player Experience
    public Experience() {
        new CommandBase("givexp", "Gives a player xp", 2, true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;

                try {
                    String targetName = arguments[0];
                    Player targetPlayer = Bukkit.getServer().getPlayerExact(targetName);

                    if (targetPlayer == null) {
                        Message.send(player, "This player is not online.");
                        return true;
                    }

                    int expToGive = Integer.parseInt(arguments[1]);

                    //Data
                    PlayerUtil.getPlayerMemory(player).giveExperience(expToGive);
                    int level = PlayerUtil.getPlayerMemory(player).getLevel();
                    int exp = PlayerUtil.getPlayerMemory(player).getExperience();
                    int req = PlayerUtil.getPlayerMemory(player).getRequiredExperience();

                    //UI //TODO this math needs to be fixed
                    float x = exp * getExpToNext(player.getLevel());
                    float currentExp = x / req;

                    Message.send(player, "----------------------");
                    Message.send(player, "1 exp "+player.getExp()+" / "+getExpToNext(player.getLevel()));
                    Message.send(player, "2 exp "+exp+" / "+req);
                    Message.send(player, "currentExp "+ currentExp);
                    Message.send(player, "x "+ x);
                    Message.send(player, "lvl "+ level);
                    Message.send(player, "----------------------");


                    changeExp(player, currentExp, level);
                    Message.send(player, expToGive + " has been given.");

                } catch (Exception e) {
                    Message.send(player, "Invalid number provided.");
                    return true;
                }
                return true;
            }

            @Override
            public String getUsage() {
                return "/givexp <Player> <Amount>";
            }

        }.enableDelay(1);
    }
}