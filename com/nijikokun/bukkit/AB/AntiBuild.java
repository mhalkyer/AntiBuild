 package com.nijikokun.bukkit.AB;
 
 import com.nijiko.AB.configuration.ConfigurationHandler;
 import com.nijiko.AB.configuration.DefaultConfiguration;
 import com.nijiko.Messaging;
 import com.nijiko.Misc;
 import com.nijikokun.bukkit.Permissions.Permissions;
 import java.util.logging.Logger;
 import org.bukkit.plugin.Plugin;
 import org.bukkit.plugin.java.JavaPlugin;
 import org.bukkit.event.Event;
 
 public class AntiBuild extends JavaPlugin
 {
   public static final Logger log = Logger.getLogger("Minecraft");
 
   public static String name = "AntiBuild";
   public static String codename = "Abadaba";
   public static String version = "1.1";
 
   public Listener l = new Listener(this);
   public BListener bl = new BListener(this);
   public Permissions Permissions;
   public Misc Misc = new Misc();
   private DefaultConfiguration config;
   public static boolean show = true;

   public void onDisable() 
   {
     log.info(Messaging.bracketize(name) + " version " + Messaging.bracketize(version) + " (" + codename + ") disabled");
   }
 
   public void onEnable() 
   {
		this.config = new ConfigurationHandler(getConfiguration());
		getConfiguration().load();
		this.config.load();
		registerEvents();
		log.info(Messaging.bracketize(name) + " version " + Messaging.bracketize(version) + " (" + codename + ") loaded");
     	setupPermissions();
   }
 
   private void registerEvents() {
     getServer().getPluginManager().registerEvent(Event.Type.BLOCK_DAMAGED, this.bl, Event.Priority.Normal, this);
     getServer().getPluginManager().registerEvent(Event.Type.BLOCK_PLACED, this.bl, Event.Priority.Normal, this);
     getServer().getPluginManager().registerEvent(Event.Type.PLAYER_ITEM, this.l, Event.Priority.Normal, this);
   }
 
   public void setupPermissions() {
     Plugin test = getServer().getPluginManager().getPlugin("Permissions");
 
     if (this.Permissions == null)
       if (test != null) {
         this.Permissions = ((Permissions)test);
       } else {
         log.info(Messaging.bracketize(name) + " Permission system not enabled. Disabling plugin.");
         getServer().getPluginManager().disablePlugin(this);
       }
   }
 }