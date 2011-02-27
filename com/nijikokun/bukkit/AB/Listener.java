 package com.nijikokun.bukkit.AB;
 
 import com.nijiko.Misc;
 import com.nijikokun.bukkit.Permissions.Permissions;

 import org.bukkit.World;
 import org.bukkit.entity.Player;
 import org.bukkit.event.player.PlayerItemEvent;
 import org.bukkit.event.player.PlayerListener;
 
 public class Listener extends PlayerListener
 {
 
   public Misc Misc = new Misc();
   public static AntiBuild plugin;
 
   public Listener(AntiBuild instance)
   {
     plugin = instance;
   }
 
   public void onPlayerItem(PlayerItemEvent event)
   {
     Player player = event.getPlayer();
     World world = event.getPlayer().getWorld();
     String group = Permissions.Security.getGroup(world.getName(), player.getName());
 
     if ((group != null) && 
       (!Permissions.Security.canGroupBuild(world.getName(), group)))
       event.setCancelled(true);
   }
 }