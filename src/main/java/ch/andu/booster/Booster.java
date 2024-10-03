package ch.andu.booster;

import ch.andu.booster.booster.Boostereffect;
import ch.andu.booster.commands.Boostercommand;
import ch.andu.booster.lisstener.PlayerJoinEvent;
import ch.andu.booster.sql.SQL;
import com.zaxxer.hikari.HikariDataSource;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public final class Booster extends JavaPlugin {
    public static  HashMap<String,Integer> type = new HashMap<>();
    public String prefix = "§cBooster"; //noch in config hinzufügen
    private File file = new File("plugins//booster//mysql.yml");
    private File filem = new File("plugins//booster//message.yml");
    private YamlConfiguration cfgm = YamlConfiguration.loadConfiguration(filem);
    private YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
    private HikariDataSource hikari;
    private SQL sql;
    public static Economy econ = null;

    public static Booster instance;



    public void addtype(){
        type.put("fly",0);
        type.put("money",0);
        type.put("xp",0);
        type.put("break",0);
        type.put("drops",0);
    }



    @Override
    public void onEnable() {
        instance = this;
        addCfg();
        new Boostereffect().Scheudler();
        connectToDatabase();
        addtype();
       if(!addEconomy()){
           Bukkit.getConsoleSender().sendMessage("§cVault ist nicht installiert!");
       }
        sql = new SQL(hikari);
        sql.createTabel();
        Bukkit.getConsoleSender().sendMessage(prefix+" §7Das Plugin wurde erfolgreich geladen!");
        Bukkit.getConsoleSender().sendMessage("plugin by anducrafter");
        getCommand("booster").setExecutor(new Boostercommand(sql));
        Bukkit.getPluginManager().registerEvents(new PlayerJoinEvent(sql),this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    private  void addCfg() {
        if(!cfg.isSet("address")){
            cfg.set("address","localhost");
            cfg.set("port",3306);
            cfg.set("database","skysuchttest");
            cfg.set("user","anducrafter");
            cfg.set("password","anducrafter");
            try {
                cfg.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private void addMessage(){
if(!cfg.isSet("message.booster")){
   cfg.set("message.booster","Du hast booster %booster%");
    cfg.set("message.help","Alle Befehle ausgeführt");
    cfg.set("message.booster","Du hast booster %booster%");;
    cfg.set("message.boostertype","%type% booster wurde aktiviert Level %level% ");
    cfg.set("message.offBooster","%type% ist deaktiviert");

}
    }
    public static Booster getInstance() {
        return instance;
    }
public boolean  addEconomy(){
    if (getServer().getPluginManager().getPlugin("Vault") == null) {
        return false;
    }
    RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
    if (rsp == null) {
        return true;
    }
    econ = rsp.getProvider();
    return econ != null;
}
    private void connectToDatabase() {
        //Database details
        String address = cfg.getString("address");
        String name = cfg.getString("database");
        String username =cfg.getString("user");
        String password = cfg.getString("password");
        //Initialise hikari instace
        hikari = new HikariDataSource();
        //Setting Hikari properties
        hikari.setMaximumPoolSize(10);
        hikari.setDataSourceClassName("com.mysql.cj.jdbc.MysqlDataSource");
        hikari.addDataSourceProperty("serverName", address);
        hikari.addDataSourceProperty("port", cfg.getString("port"));
        hikari.addDataSourceProperty("databaseName", name);
        hikari.addDataSourceProperty("user", username);
        hikari.addDataSourceProperty("password", password);

    }
}
