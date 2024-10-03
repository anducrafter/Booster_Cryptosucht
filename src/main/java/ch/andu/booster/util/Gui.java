package ch.andu.booster.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Gui {

    public void openInventory(Player player){
        Inventory inv = Bukkit.createInventory(null,9*3,"§6§lMineCreation §8» §7Booster");
    }


}
