package ch.andu.booster.lisstener;

import ch.andu.booster.sql.SQL;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerJoinEvent implements Listener {

    private SQL sql;
    public PlayerJoinEvent(SQL sql){
        this.sql = sql;
    }
    @EventHandler
    public void playerJoin(org.bukkit.event.player.PlayerJoinEvent event){
        Player player = event.getPlayer();
        if(!sql.playerExist(player.getUniqueId().toString())){
            sql.createPlayer(player.getUniqueId().toString());
        }
    }
}
