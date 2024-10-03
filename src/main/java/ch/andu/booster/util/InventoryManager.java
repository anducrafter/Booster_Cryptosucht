package ch.andu.booster.util;

import ch.andu.booster.Booster;
import ch.andu.booster.sql.SQL;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InventoryManager {

    public Inventory getInventory(Player player, HikariDataSource hikari){
        Inventory inv = Bukkit.createInventory(null,9*3,"§b§lBooster");
        inv.setItem(0,getItemStack(Material.OAK_SIGN,"§7Deine Booster "+new SQL(hikari).getPlayerBooster(player.getUniqueId().toString()),null));
        inv.setItem(4,getItemStack(Material.GOLDEN_PICKAXE,"§7Momentaner Booster Level ", Arrays.asList("§7Bei diesem Booster können alle Spieler","§7Jegliche Blöcke schneller abbauen.")));
        inv.setItem(10,getItemStack(Material.FEATHER,"§7Momentaner Booster Level ", Arrays.asList("§7Bei diesem Booster können alle Spieler","§7Fliegen")));
        inv.setItem(16,getItemStack(Material.ROTTEN_FLESH,"§7Momentaner Booster Level ", Arrays.asList("§7Bei diesem Booster ","werden alle Mob drops multipliziert")));
        inv.setItem(21,getItemStack(Material.EXPERIENCE_BOTTLE,"§7Momentaner Booster Level ", Arrays.asList("§7Bei diesem Booster können alle Spieler","Jegliche Blöcke schneller abbauen.")));





        return inv;
    }


    private ItemStack getItemStack(Material material, String displayname, List<String> lore){
        ItemStack i = new ItemStack(material);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(displayname);
        if(!(lore == null)){
            im.setLore(lore);
        }
        i.setItemMeta(im);

        return i;
    }
}
