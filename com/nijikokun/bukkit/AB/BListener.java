 package com.nijikokun.bukkit.AB;
 
 import com.nijiko.Misc;
 import com.nijikokun.bukkit.Permissions.Permissions;
 
 import org.bukkit.World;
 import org.bukkit.entity.Player;
 import org.bukkit.event.block.BlockDamageEvent;
 import org.bukkit.event.block.BlockInteractEvent;
 import org.bukkit.event.block.BlockListener;
 import org.bukkit.event.block.BlockPlaceEvent;
 
 public class BListener extends BlockListener
 { 
   public Misc Misc = new Misc();
   public static AntiBuild plugin;
 
   public BListener(AntiBuild instance)
   {
     plugin = instance;
   }
 
   public void onBlockDamage(BlockDamageEvent event)
   {
     Player player = event.getPlayer();
			 World world = event.getPlayer().getWorld();
     String group = Permissions.Security.getGroup(world.getName(), player.getName());
 
     if ((group != null) && 
       (!Permissions.Security.canGroupBuild(world.getName(), group)))
       event.setCancelled(true);
   }
 
   public void onBlockPlace(BlockPlaceEvent event)
   {
     Player player = event.getPlayer();
			 World world = event.getPlayer().getWorld();
     String group = Permissions.Security.getGroup(world.getName(), player.getName());
 
     if ((group != null) && 
       (!Permissions.Security.canGroupBuild(world.getName(), group)))
       event.setCancelled(true);
   }

   public void onBlockInteract(BlockInteractEvent event)
   {
     Player player = null;
	 World world = event.getBlock().getWorld();
 
     if (event.isPlayer()) {
       player = (Player)event.getEntity();
     }
 
     if (player != null) {
       String group = Permissions.Security.getGroup(world.getName(), player.getName());
 
       if ((group != null) && 
         (!Permissions.Security.canGroupBuild(world.getName(), group)))
         event.setCancelled(true);
     }
   }
 }
