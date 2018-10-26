//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package speeduhc.kotya;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.schematic.MCEditSchematicFormat;
import com.sk89q.worldedit.world.DataException;
import net.minecraft.server.v1_8_R3.Blocks;
import net.minecraft.server.v1_8_R3.MaterialLiquid;
import org.bukkit.*;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import speeduhc.kotya.utils.ArmorStandUtil;
import speeduhc.kotya.utils.ChestUtil;
import speeduhc.kotya.utils.ItemUtil;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class SpeedUltraHardcore4 extends JavaPlugin implements Listener {
    public boolean GameInGame = false;
    Scoreboard board;
    Objective obj;

    //@Override
    //public List<Class<?>>

    public SpeedUltraHardcore4() {
    }

    public void onEnable() {
        FastCraft();
        if (this.getServer().getPluginManager().getPlugin("CorpseReborn").isNaggable()){
            this.getLogger().info("CorpseRebordApi Был подключен!");
        }else {
            this.getLogger().severe("Плагин CorpseRebornApi не найден пожалуйста скачайте его с официального сайта SPigot https://www.spigotmc.org/resources/corpsereborn.29875/");
            this.getServer().getPluginManager().disablePlugin(this);
        }
        if (this.getServer().getPluginManager().getPlugin("WorldEdit").isNaggable()){
            this.getLogger().info("WorldEditApi Был подключен!");
        }else {
            this.getLogger().severe("Плагин WOrldEdit не найден пожалуйста скачайте его с официального сайта bukkit https://dev.bukkit.org/projects/worldedit");
            this.getServer().getPluginManager().disablePlugin(this);
        }

        this.getServer().getPluginManager().registerEvents(this, this);
        //this.getCommand("SpeedUhcCommand").setExecutor(new SpeedUhcCommand());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        this.getServer().dispatchCommand(this.getServer().getConsoleSender(), "tp " + e.getPlayer().getName() + " 0 100 0");
        loadSchematic(e.getPlayer());
        Inventory inv = e.getPlayer().getInventory();
        inv.setItem(8, ItemUtil.createItem(Material.BARRIER, "§cВыйти"));
        inv.setItem(1, ItemUtil.createItem(Material.COMMAND_MINECART, "§aВыбор кита", "§ayа свой вкус"));
        final int pl = 1;
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
                    players1.setScore(pl + 1);
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
        Inventory kits = Bukkit.createInventory(null, 9*3, "§cВыбор кита");
        ItemStack Sa = ItemUtil.createItem(Material.STONE_PICKAXE, "§bStone age", "§cПокачто так");
        kits.setItem(1, Sa);
        kits.setItem(25, ItemUtil.createItem(Material.BARRIER, "§cвийти"));
        p.openInventory(kits);
    }

    @EventHandler
    public void INVS(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
        if (e.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
            if (e.getInventory().getName().equalsIgnoreCase("§cВыбор кита")) {
                e.setCancelled(true);
                if (e.getCurrentItem().getType() == Material.BARRIER){
                    p.closeInventory();
                }
            }
        }

    }

    @EventHandler
    public void onKill(EntityDeathEvent e){
        if (e.getEntityType() == EntityType.PLAYER){
            e.getDrops().add(ItemUtil.createItem(Material.GOLDEN_APPLE, "§6§lЗолотая голова"));
            e.setDroppedExp(100);
            ///summon ArmorStand ~ ~ ~ {Invulnerable:1b,NoBasePlate:1b,NoGravity:1b,ShowArms:1b,ArmorItems:[{id:"leather_boots",Count:1b,tag:{display:{color:16746496}}},{id:"leather_leggings",Count:1b,tag:{display:{color:16746496}}},{id:"leather_chestplate",Count:1b,tag:{display:{color:16746496}}},],HandItems:[{},{}],CustomName:"`s inv",CustomNameVisible:1b,Pose:{Head:[36f,0f,0f],LeftLeg:[77f,0f,0f],RightLeg:[81f,0f,0f],LeftArm:[339f,0f,0f],RightArm:[324f,0f,0f]}}
            //e.getEntity().getKiller().getInventory().addItem(ItemUtil.createItem(e.getDrops().get(<ITEMS>).getType()));
            ArmorStandUtil.createarmor(Material.BARRIER, Material.IRON_BOOTS, Material.IRON_LEGGINGS, Material.CHAINMAIL_CHESTPLATE);
            ///summon ArmorStand ~ ~ ~ {Invulnerable:1b,NoBasePlate:1b,NoGravity:1b,ShowArms:1b,ArmorItems:[{id:"leather_boots",Count:1b,tag:{display:{color:16746496}}},{id:"leather_leggings",Count:1b,tag:{display:{color:16746496}}},{id:"leather_chestplate",Count:1b,tag:{display:{color:16746496}}},{id:"leather_helmet",Count:1b,tag:{display:{color:16746496}}}],HandItems:[{},{}],CustomName:"`s inv",CustomNameVisible:1b,Pose:{Head:[36f,0f,0f],LeftLeg:[77f,0f,0f],RightLeg:[81f,0f,0f],LeftArm:[339f,0f,0f],RightArm:[324f,0f,0f]}}
            double x = e.getEntity().getLocation().getX();
            double ys = e.getEntity().getLocation().getY();
            double y = ys + 10;
            double z = e.getEntity().getLocation().getZ();

            //this.getServer().dispatchCommand(this.getServer().getConsoleSender(), "summon ArmorStand "+ x + " " + y + " " + z + "{NoBasePlate:1b,NoGravity:1b,ShowArms:1b,ArmorItems:[{id:leather_boots,Count:1b,tag:{display:{color:16746496}}},{id:leather_leggings,Count:1b,tag:{display:{color:16746496}}},{id:leather_chestplate,Count:1b,tag:{display:{color:16746496}}},],HandItems:[{},{}],CustomName:\"" + e.getEntity().getName() + "\",CustomNameVisible:1b,Pose:{Head:[36f,0f,0f],LeftLeg:[77f,0f,0f],RightLeg:[81f,0f,0f],LeftArm:[339f,0f,0f],RightArm:[324f,0f,0f]}}");
            //e.





            Chest c = new Chest() {
                @Override
                public void setMetadata(String s, MetadataValue metadataValue) {

                }

                @Override
                public List<MetadataValue> getMetadata(String s) {
                    return null;
                }

                @Override
                public boolean hasMetadata(String s) {
                    return false;
                }

                @Override
                public void removeMetadata(String s, Plugin plugin) {

                }

                @Override
                public Inventory getInventory() {
                    return null;
                }

                @Override
                public Block getBlock() {
                    return null;
                }

                @Override
                public MaterialData getData() {
                    return null;
                }

                @Override
                public Material getType() {
                    return null;
                }

                @Override
                public int getTypeId() {
                    return 0;
                }

                @Override
                public byte getLightLevel() {
                    return 0;
                }

                @Override
                public World getWorld() {
                    return null;
                }

                @Override
                public int getX() {
                    return 0;
                }

                @Override
                public int getY() {
                    return 0;
                }

                @Override
                public int getZ() {
                    return 0;
                }

                @Override
                public Location getLocation() {
                    return null;
                }

                @Override
                public Location getLocation(Location location) {
                    return null;
                }

                @Override
                public Chunk getChunk() {
                    return null;
                }

                @Override
                public void setData(MaterialData materialData) {

                }

                @Override
                public void setType(Material material) {

                }

                @Override
                public boolean setTypeId(int i) {
                    return false;
                }

                @Override
                public boolean update() {
                    return false;
                }

                @Override
                public boolean update(boolean b) {
                    return false;
                }

                @Override
                public boolean update(boolean b, boolean b1) {
                    return false;
                }

                @Override
                public byte getRawData() {
                    return 0;
                }

                @Override
                public void setRawData(byte b) {

                }

                @Override
                public boolean isPlaced() {
                    return false;
                }

                @Override
                public Inventory getBlockInventory() {
                    return null;
                }
            };
            c.getBlockInventory().setContents(new ItemStack[] {(ItemStack) e.getDrops()});
            c.getBlock().getLocation().setX(e.getEntity().getLocation().getX());
            c.getBlock().getLocation().setZ(e.getEntity().getLocation().getY());
            c.getBlock().getLocation().setZ(e.getEntity().getLocation().getZ());
            this.getServer().dispatchCommand(this.getServer().getConsoleSender(), "summon LightningBolt " + e.getEntity().getLocation().getX() + " " + y + 10 + " " + e.getEntity().getLocation().getZ());
            this.getServer().broadcastMessage("§l§cИгрок " + e.getEntity().getKiller().getName() + " убил игрока " + e.getEntity().getName());

        }

    }




    @EventHandler
    public void onHead(PlayerInteractEvent e){
        if (e.getAction() == Action.RIGHT_CLICK_AIR){
            if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lЗолотая голова")){
                this.getServer().dispatchCommand(this.getServer().getConsoleSender(), "effect " + e.getPlayer().getName() + " minecraft:speed" + " 10 1 true");
                //this.getServer().dispatchCommand(this,getServer(), "clear " + e.getPlayer().getName().toLowerCase() + " 1");
            }
        }

    }


    private void FastCraft(){
        ItemStack item = new ItemStack(Material.IRON_INGOT, 10);
        ItemMeta meta = item.getItemMeta();
        //PickAxeCraft
        ItemStack fpick = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta fpickmeta = item.getItemMeta();
        fpickmeta.addEnchant(Enchantment.DIG_SPEED , 3, true);
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


    public void onChest(Location loc){

        //loc.getWorld().getBlockAt(loc.getX(), loc.getY(), loc.getBlockZ()).setType(Blocks.CHEST);
    }



    @EventHandler
    public void onOre(BlockBreakEvent e){
        if(e.getBlock().getType() == Material.IRON_ORE){
            e.getBlock().getDrops().clear();
            e.getPlayer().getInventory().addItem(new ItemStack(Material.IRON_INGOT));
        }
    }

    private void loadSchematic(Player player)
    {
        Location location = player.getLocation();
        WorldEditPlugin worldEditPlugin = (WorldEditPlugin)Bukkit.getPluginManager().getPlugin("WorldEdit");
        File schematic = new File(getDataFolder() + File.separator + "123.schematic");
        try
        {
            CuboidClipboard clipboard;
            clipboard = MCEditSchematicFormat.getFormat(schematic).load(schematic);
            clipboard.rotate2D(90);
            clipboard.paste(worldEditPlugin.getSession(player).createEditSession((com.sk89q.worldedit.entity.Player) player), new Vector(location.getX(), location.getY(), location.getZ()), false);
        }
        catch (MaxChangedBlocksException |DataException |IOException e)
        {
            e.printStackTrace();
        }
    }
}
