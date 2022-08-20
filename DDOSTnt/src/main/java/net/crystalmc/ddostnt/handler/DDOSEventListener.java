package net.crystalmc.ddostnt.handler;

import net.crystalmc.ddostnt.DDOSTNT;
import net.crystalmc.ddostnt.utils.ColorUtils;
import net.crystalmc.ddostnt.utils.NBTEditor;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class DDOSEventListener implements Listener {

    @EventHandler
    public void playerDamageEvent(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        Entity damaged = e.getEntity();
        if (damaged instanceof Player) {
            if (damager instanceof TNTPrimed) {
                TNTPrimed tnt = (TNTPrimed) damager;
                Player player = (Player) damaged;
                int entityId = tnt.getEntityId();
                if (DDOSHandler.getInstance().isDdosTnt(entityId)) {
                    String ip = player.getAddress().getAddress().getHostAddress();
                    DDOSHandler.getInstance().addBannedIP(ip);
                    player.kick(Component.text(ColorUtils.colorize("&cYou have been IP Banned for 5 seconds for being hit by DDOS tnt.")));
                    DDOSHandler.getInstance().removeDdosTnt(entityId);

                    Bukkit.getScheduler().runTaskLater(JavaPlugin.getPlugin(DDOSTNT.class), () -> {
                        DDOSHandler.getInstance().removeBannedIP(ip);
                    }, 5L * 20L); // 5 minutes.
                }
            }
        }
    }

    @EventHandler
    public void blockPlaceEvent(BlockPlaceEvent e) {
        ItemStack hand = e.getItemInHand();
        if (NBTEditor.getBoolean(hand, "crystalmc", "ddostnt")) {
            e.getBlockPlaced().setType(Material.AIR);
            TNTPrimed tnt = e.getBlock().getWorld().spawn(e.getBlock().getLocation(), TNTPrimed.class);
            DDOSHandler.getInstance().addDdosTnt(tnt.getEntityId());
        }
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        String ip = e.getPlayer().getAddress().getAddress().getHostAddress();
        if (DDOSHandler.getInstance().isBanned(ip)) {
            e.getPlayer().kick(Component.text(ColorUtils.colorize("&cYou have been IP Banned for 5 seconds for being hit by DDOS tnt.")));
        }
    }

}
