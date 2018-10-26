package speeduhc.kotya.utils;

import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Item;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class ChestUtil extends JavaPlugin implements Listener {


    public static void addItems(ItemStack item){
        Chest(null, item);
    }


    public static void Chest(Chest chest, ItemStack mat){
        chest.getBlockInventory().addItem(mat);
    }
}
