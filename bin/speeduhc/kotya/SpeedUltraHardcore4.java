
package speeduhc.kotya;

import com.boydti.fawe.Fawe;
import com.boydti.fawe.FaweAPI;
import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.Vector;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import speeduhc.kotya.utils.*;

public class SpeedUltraHardcore4 extends JavaPlugin implements Listener {
    public boolean GameInGame = false;
    Scoreboard board;
    Objective obj;

    public SpeedUltraHardcore4() {
    }

    public void onEnable() {
        this.FastCraft();
        if (this.getServer().getPluginManager().getPlugin("CorpseReborn").isNaggable()) {
            this.getLogger().info("CorpseRebordApi Был подключен!");
        } else {
            this.getLogger().severe("Плагин CorpseRebornApi не найден пожалуйста скачайте его с официального сайта SPigot https://www.spigotmc.org/resources/corpsereborn.29875/");
            this.getServer().getPluginManager().disablePlugin(this);
        }

        if (this.getServer().getPluginManager().getPlugin("WorldEdit").isNaggable()) {
            this.getLogger().info("WorldEditApi Был подключен!");
        } else {
            this.getLogger().severe("Плагин WOrldEdit не найден пожалуйста скачайте его с официального сайта bukkit https://dev.bukkit.org/projects/worldedit");
            this.getServer().getPluginManager().disablePlugin(this);
        }

        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        this.getServer().dispatchCommand(this.getServer().getConsoleSender(), "tp " + e.getPlayer().getName() + " 0 100 0");
        Inventory inv = e.getPlayer().getInventory();
        inv.setItem(8, ItemUtil.createItem(Material.BARRIER, "§cВыйти"));
        inv.setItem(1, ItemUtil.createItem(Material.COMMAND_MINECART, "§aВыбор кита", "§ayа свой вкус"));
        int nick = e.getPlayer().getName().length();
        e.getPlayer().setDisplayName(ChatColor.RED + e.getPlayer().getName());
        e.getPlayer().setPlayerListName("§c§k" + nick);
        this.board = Bukkit.getScoreboardManager().getMainScoreboard();
        this.obj = this.board.registerNewObjective(e.getPlayer().getName(), "dummy");
        final Score players1 = this.obj.getScore("Players " + this.getServer().getOnlinePlayers().size());
        (new BukkitRunnable() {
            int i;

            public void run() {
                ++this.i;
                if (this.i == 20) {
                    SpeedUltraHardcore4.this.getServer().dispatchCommand(SpeedUltraHardcore4.this.getServer().getConsoleSender(), "tell @a " + this.i);
                    players1.setScore(2);
                    SpeedUltraHardcore4.this.obj.setDisplayName("§6" + ChatColor.BOLD + "UHC");
                    this.cancel();
                }

            }
        }).runTaskTimer(this, 20L, 1L);
        this.obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        e.setJoinMessage(ChatColor.BOLD + "§eИгрок " + e.getPlayer().getName() + "§l§eПрисоединился§r§c(" + this.getServer().getOnlinePlayers().size() + "/" + this.getServer().getMaxPlayers() + ")");
    }

    @EventHandler
    public void onleave(PlayerQuitEvent e) {
        this.getServer().dispatchCommand(this.getServer().getConsoleSender(), "scoreboard objectives remove " + e.getPlayer().getName());
        e.setQuitMessage("§e§lИгрок " + e.getPlayer().getName() + "§e§lВышел§r§c(" + this.getServer().getOnlinePlayers().size() + "/" + this.getServer().getMaxPlayers());
        e.getPlayer().getInventory().clear();
    }

    public void onDisable() {
        this.getServer().getPluginManager().disablePlugin(this);
    }

    @EventHandler
    public void oniteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR && e.getItem().getType() == Material.BARRIER) {
            e.getPlayer().getInventory().clear();
            e.getPlayer().kickPlayer("");
            e.setCancelled(true);
        }

    }

    @EventHandler
    public void onKit(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) && e.getPlayer().getItemInHand().getType() == Material.COMMAND_MINECART) {
            OpenGUI(p);
        }

    }

    public static void OpenGUI(Player p) {
        Inventory kits = Bukkit.createInventory((InventoryHolder)null, 27, "§cВыбор кита");
        ItemStack Sa = ItemUtil.createItem(Material.STONE_PICKAXE, "§bStone age", "§cПокачто так");
        kits.setItem(1, Sa);
        kits.setItem(25, ItemUtil.createItem(Material.BARRIER, "§cвийти"));
        p.openInventory(kits);
    }

    @EventHandler
    public void INVS(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
        if (e.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY && e.getInventory().getName().equalsIgnoreCase("§cВыбор кита")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() == Material.BARRIER) {
                p.closeInventory();
            }
        }

    }

    @EventHandler
    public void onKill(EntityDeathEvent e) {
        if (e.getEntityType() == EntityType.PLAYER) {
            e.getDrops().add(ItemUtil.createItem(Material.GOLDEN_APPLE, "§6§lЗолотая голова"));
            e.setDroppedExp(100);
            double x = e.getEntity().getLocation().getX();
            double ys = e.getEntity().getLocation().getY();
            double y = ys + 10.0D;
            double z = e.getEntity().getLocation().getZ();
            this.getServer().dispatchCommand(this.getServer().getConsoleSender(), "summon LightningBolt " + e.getEntity().getLocation().getX() + " " + y + 10 + " " + e.getEntity().getLocation().getZ());
            this.getServer().broadcastMessage("§l§cИгрок " + e.getEntity().getKiller().getName() + " убил игрока " + e.getEntity().getName());
        }

    }

    @EventHandler
    public void onHead(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR && e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lЗолотая голова")) {
            this.getServer().dispatchCommand(this.getServer().getConsoleSender(), "effect " + e.getPlayer().getName() + " minecraft:speed 10 1 true");
        }

    }

    private void FastCraft() {
        ItemStack item = new ItemStack(Material.IRON_INGOT, 10);
        ItemMeta meta = item.getItemMeta();
        ItemStack fpick = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta fpickmeta = item.getItemMeta();
        fpickmeta.addEnchant(Enchantment.DIG_SPEED, 3, true);
        fpickmeta.addEnchant(Enchantment.DURABILITY, 10, true);
        fpickmeta.setDisplayName("§cБыстрая кирка");
        ShapedRecipe fs = new ShapedRecipe(fpick);
        fs.shape("  R", "SSD", "  R");
        fs.setIngredient('R', Material.IRON_ORE);
        fs.setIngredient('S', Material.STICK);
        fs.setIngredient('D', Material.COBBLESTONE);
        ShapedRecipe s = new ShapedRecipe(item);
        s.shape("III", "ICI", "III");
        s.setIngredient('I', Material.IRON_ORE);
        s.setIngredient('C', Material.COAL);
        Bukkit.getServer().addRecipe(fs);
        Bukkit.getServer().addRecipe(s);
    }

    public void onChest(Location loc) {
    }

    @EventHandler
    public void onOre(BlockBreakEvent e) {
        if (e.getBlock().getType() == Material.IRON_ORE) {
            e.getBlock().getDrops().clear();
            e.getPlayer().getInventory().addItem(new ItemStack[]{new ItemStack(Material.IRON_INGOT)});
        }

    }

   // private void loadSchematic(Player player) {
     //   Location location = player.getLocation();
       // FaweAPI worldEditPlugin = (FaweAPI) Bukkit.getPluginManager().getPlugin("WorldEdit");
        //File schematic = new File(this.getDataFolder() + File.separator + "123.schematic");


       // try {
         //  CuboidClipboard clipboard = FaweAPI.streamSchematic(schematic, new Vector(location.getX(), location.getY(), location.getZ())).getFormat(schematic).load(schematic);


           // clipboard.rotate2D(90);
           // clipboard.paste(worldEditPlugin.getSession(player).createEditSession((com.sk89q.worldedit.entity.Player)player), new Vector(location.getX(), location.getY(), location.getZ()), false);
        //} catch (DataException | IOException | MaxChangedBlocksException var6) {
        //}

    //}
}
