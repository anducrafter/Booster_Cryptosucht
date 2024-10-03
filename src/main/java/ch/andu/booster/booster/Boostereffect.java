package ch.andu.booster.booster;

import ch.andu.booster.Booster;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.HashMap;

import static ch.andu.booster.Booster.econ;

public class Boostereffect {
    public int schedulerID;
    static HashMap<String, Integer> boosters = Booster.type;
    static int flytime;
    static int xptime;


    public static void fly(String name){
        int level = boosters.get(name);
        if(level == 0){
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                //Nopch checken für die Welten
                onlinePlayer.setAllowFlight(true);
                onlinePlayer.setFlying(true);
            }
        }
        if(!(level > 6)){
        boosters.put(name,level+1);
        flytime=+900;

        Bukkit.getScheduler().runTaskLater(Booster.getInstance(), new Runnable() {
            @Override
            public void run() {
                boosters.put(name,level-1);
            }
        },18000);
    }
    }

    public static void xp(String name){
        int level = boosters.get(name);


        if(level == 0){
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                //Nopch checken für die Welten
                onlinePlayer.setFlying(true);
            }
        }
        if(!(level > 6)){
            boosters.put(name,level+1);
            flytime=+900;

            Bukkit.getScheduler().runTaskLater(Booster.getInstance(), new Runnable() {
                @Override
                public void run() {
                    boosters.put(name,level-1);
                }
            },18000);
        }
    }

    public static void Break(String name){
        int level = boosters.get(name);


        if(!(level > 6)){
            boosters.put(name,level+1);
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                //NWenns nicht so addiertwird der effect, Ihn noch per duration herausbekommen!
                if(onlinePlayer.hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
                    onlinePlayer.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 18000+onlinePlayer.getPotionEffect(PotionEffectType.FAST_DIGGING).getDuration(), 3));
                }else{
                    onlinePlayer.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 18000, 3));
                }
            }
            Bukkit.getScheduler().runTaskLater(Booster.getInstance(), new Runnable() {
                @Override
                public void run() {
                    boosters.put(name,level-1);
                }
            },18000);
        }
    }

    public static void Drops(String name){
        int level = boosters.get(name);
        if(!(level > 6)){
            boosters.put(name,level+1);
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                //NWenns nicht so addiertwird der effect, Ihn noch per duration herausbekommen!
                if(onlinePlayer.hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
                    onlinePlayer.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 18000+onlinePlayer.getPotionEffect(PotionEffectType.FAST_DIGGING).getDuration(), 3));
                }else{
                    onlinePlayer.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 18000, 3));
                }
            }

            Bukkit.getScheduler().runTaskLater(Booster.getInstance(), new Runnable() {
                @Override
                public void run() {
                    boosters.put(name,level-1);
                }
            },18000);
        }
    }

    public static void Money(String name){
        int level = boosters.get(name);
        if(!(level > 6)){
            boosters.put(name,level+1);
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                //Nopch checken für die Welten
                //Hier wird erstmal geld gegeben
                //für Jobs System
                EconomyResponse r = econ.depositPlayer(onlinePlayer, 1.05);
                if(r.transactionSuccess()) {
                    onlinePlayer.sendMessage(String.format("You were given %s and now have %s", econ.format(r.amount), econ.format(r.balance)));
                } else {
                    onlinePlayer.sendMessage(String.format("An error occured: %s", r.errorMessage));
                }
            }
            Bukkit.getScheduler().runTaskLater(Booster.getInstance(), new Runnable() {
                @Override
                public void run() {
                    boosters.put(name,level-1);
                }
            },18000);
        }
    }


    public static void setFlytime() {
        if(flytime == 0){
                Bukkit.getOnlinePlayers().forEach(player -> {
                    player.setFlying(false);
                    player.setAllowFlight(false);
                });
            return;
        }
        flytime--;
    }

    public static void setXptime() {
        if(flytime == 0){
            Bukkit.getOnlinePlayers().forEach(player -> {
                player.setFlying(false);
            });
            return;
        }
        xptime--;
    }

    public void Scheudler(){
        Bukkit.getScheduler().runTaskTimer(Booster.instance, new Runnable() {
            @Override
            public void run() {
                    setFlytime();
                    setXptime();
            }
        },0,20);
    }


}
