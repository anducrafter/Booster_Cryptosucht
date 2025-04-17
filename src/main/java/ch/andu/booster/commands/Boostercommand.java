package ch.andu.booster.commands;

import ch.andu.booster.Booster;
import ch.andu.booster.booster.Boostereffect;
import ch.andu.booster.sql.SQL;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.util.HashMap;
import java.util.Locale;

public class Boostercommand implements CommandExecutor {
    SQL sql;
    public Boostercommand(SQL sql){
        this.sql = sql;
    }
    HashMap<String,Integer> type = Booster.type;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (((Player) sender).getPlayer());
                if(args.length == 0){
                    player.sendMessage("Du besitzt "+sql.getPlayerBooster(player.getUniqueId().toString())+" Booster");
                }
                if(args.length == 1){
                    if(args[0].equalsIgnoreCase("help")){

                    }
                    if(sql.getPlayerBooster(player.getUniqueId().toString()) > 1) {
                        int reducebooster = sql.getPlayerBooster(player.getUniqueId().toString())-1;
                        player.sendMessage(reducebooster+"");

                        if (args[0].equalsIgnoreCase("fly")) {
                            Boostereffect.fly(args[0].toLowerCase(Locale.ROOT));
                            
                            sql.setPlayerBooster(player.getUniqueId().toString(),reducebooster);
                        }
                        if (args[0].equalsIgnoreCase("money")) {
                            //money for jobs
                            Boostereffect.Money(args[0].toLowerCase(Locale.ROOT));

                            sql.setPlayerBooster(player.getUniqueId().toString(),reducebooster);
                        }
                        if (args[0].equalsIgnoreCase("xp")) {
                            Boostereffect.xp(args[0].toLowerCase(Locale.ROOT));
                            //xp for jobs
                            sql.setPlayerBooster(player.getUniqueId().toString(),reducebooster);
                        }
                        if (args[0].equalsIgnoreCase("break")) {
                            //miningfatic
                            Boostereffect.Break(args[0].toLowerCase(Locale.ROOT));
                            sql.setPlayerBooster(player.getUniqueId().toString(),reducebooster);
                        }

                        if (args[0].equalsIgnoreCase("drops")) {
                            Boostereffect.Break(args[0].toLowerCase(Locale.ROOT));
                            sql.setPlayerBooster(player.getUniqueId().toString(),reducebooster);
                        }
                    }

                }
        }
        return false;
    }
}
