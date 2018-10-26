//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package speeduhc.kotya.utils;

import java.util.Arrays;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtil {
    public ItemUtil() {
    }

    public static boolean isTool(ItemStack item) {
        return Enchantment.DURABILITY.canEnchantItem(item);
    }

    public static String getDisplayName(ItemStack item) {
        return item.hasItemMeta() ? item.getItemMeta().getDisplayName() : "Unknown display name";
    }

    public static ItemStack createItem(Material material, String name, String string) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(string));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createItem(Material material, String name) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createWoolItem(Material material, String name, String[] desc, byte color) {
        ItemStack item = new ItemStack(material, 1, (short)color);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(desc));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createWoolItem(Material material, String name, byte color) {
        ItemStack item = new ItemStack(material, 1, (short)color);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }
}
