package net.crystalmc.ddostnt.commands;

import net.crystalmc.ddostnt.utils.ColorUtils;
import net.crystalmc.ddostnt.utils.NBTEditor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GiveDdosTntCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("ddostnt.give")) {
                ItemStack ddosTnt = new ItemStack(Material.TNT);
                ItemMeta meta = ddosTnt.getItemMeta();
                meta.setDisplayName(ColorUtils.colorize("&c&lDDOS TNT"));
                List<String> lore = new ArrayList<>();
                lore.add(ColorUtils.colorize("&7This TNT IP-Bans a player when they're touched with it."));
                lore.add(ColorUtils.colorize("&cPlacing it immediately triggers a fuse on the TNT. Beware!"));
                meta.setLore(lore);
                ddosTnt.setItemMeta(meta);
                ddosTnt = NBTEditor.set(ddosTnt, true, "crystalmc", "ddostnt");
                player.getInventory().addItem(ddosTnt);
            } else {
                player.sendMessage(ColorUtils.colorize("&cYou do not have permission to use this command."));
            }
        }

        return false;
    }
}
