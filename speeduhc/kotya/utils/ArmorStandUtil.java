package speeduhc.kotya.utils;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import java.util.*;

public class ArmorStandUtil {


    public static void createarmor(final Material ItemInHand, final Material boots, final Material Leggins, final Material ChestPlate){
        ArmorStand as = new ArmorStand() {
            @Override
            public ItemStack getItemInHand() {
                return null;
            }

            @Override
            public void setItemInHand(ItemStack itemStack) {
                ItemUtil.createItem(ItemInHand, "");
            }

            @Override
            public ItemStack getBoots() {
                return null;

            }
            @Override
            public void setBoots(ItemStack itemStack) {
                ItemUtil.createItem(boots, "");
            }

            @Override
            public ItemStack getLeggings() {
                return null;
            }

            @Override
            public void setLeggings(ItemStack itemStack) {
                ItemUtil.createItem(Leggins, "");
            }

            @Override
            public ItemStack getChestplate() {
                return null;
            }

            @Override
            public void setChestplate(ItemStack itemStack) {

                ItemUtil.createItem(ChestPlate, "");

            }

            @Override
            public ItemStack getHelmet() {
                return null;
            }

            @Override
            public void setHelmet(ItemStack itemStack) {

            }

            @Override
            public EulerAngle getBodyPose() {
                return null;
            }

            @Override
            public void setBodyPose(EulerAngle eulerAngle) {

            }

            @Override
            public EulerAngle getLeftArmPose() {
                return null;
            }

            @Override
            public void setLeftArmPose(EulerAngle eulerAngle) {

            }

            @Override
            public EulerAngle getRightArmPose() {
                return null;
            }

            @Override
            public void setRightArmPose(EulerAngle eulerAngle) {

            }

            @Override
            public EulerAngle getLeftLegPose() {
                return null;
            }

            @Override
            public void setLeftLegPose(EulerAngle eulerAngle) {

            }

            @Override
            public EulerAngle getRightLegPose() {
                return null;
            }

            @Override
            public void setRightLegPose(EulerAngle eulerAngle) {

            }

            @Override
            public EulerAngle getHeadPose() {
                return null;
            }

            @Override
            public void setHeadPose(EulerAngle eulerAngle) {

            }

            @Override
            public boolean hasBasePlate() {
                return false;
            }

            @Override
            public void setBasePlate(boolean b) {

            }

            @Override
            public boolean hasGravity() {
                return false;
            }

            @Override
            public void setGravity(boolean b) {

            }

            @Override
            public boolean isVisible() {
                return false;
            }

            @Override
            public void setVisible(boolean b) {

            }

            @Override
            public boolean hasArms() {
                return false;
            }

            @Override
            public void setArms(boolean b) {

            }

            @Override
            public boolean isSmall() {
                return false;
            }

            @Override
            public void setSmall(boolean b) {

            }

            @Override
            public boolean isMarker() {
                return false;
            }

            @Override
            public void setMarker(boolean b) {

            }

            @Override
            public double getEyeHeight() {
                return 0;
            }

            @Override
            public double getEyeHeight(boolean b) {
                return 0;
            }

            @Override
            public Location getEyeLocation() {
                return null;
            }

            @Override
            public List<Block> getLineOfSight(HashSet<Byte> hashSet, int i) {
                return null;
            }

            @Override
            public List<Block> getLineOfSight(Set<Material> set, int i) {
                return null;
            }

            @Override
            public Block getTargetBlock(HashSet<Byte> hashSet, int i) {
                return null;
            }

            @Override
            public Block getTargetBlock(Set<Material> set, int i) {
                return null;
            }

            @Override
            public List<Block> getLastTwoTargetBlocks(HashSet<Byte> hashSet, int i) {
                return null;
            }

            @Override
            public List<Block> getLastTwoTargetBlocks(Set<Material> set, int i) {
                return null;
            }

            @Override
            public Egg throwEgg() {
                return null;
            }

            @Override
            public Snowball throwSnowball() {
                return null;
            }

            @Override
            public Arrow shootArrow() {
                return null;
            }

            @Override
            public int getRemainingAir() {
                return 0;
            }

            @Override
            public void setRemainingAir(int i) {

            }

            @Override
            public int getMaximumAir() {
                return 0;
            }

            @Override
            public void setMaximumAir(int i) {

            }

            @Override
            public int getMaximumNoDamageTicks() {
                return 0;
            }

            @Override
            public void setMaximumNoDamageTicks(int i) {

            }

            @Override
            public double getLastDamage() {
                return 0;
            }

            @Override
            public void setLastDamage(double v) {

            }

            @Override
            public int getNoDamageTicks() {
                return 0;
            }

            @Override
            public void setNoDamageTicks(int i) {

            }

            @Override
            public Player getKiller() {
                return null;
            }

            @Override
            public boolean addPotionEffect(PotionEffect potionEffect) {
                return false;
            }

            @Override
            public boolean addPotionEffect(PotionEffect potionEffect, boolean b) {
                return false;
            }

            @Override
            public boolean addPotionEffects(Collection<PotionEffect> collection) {
                return false;
            }

            @Override
            public boolean hasPotionEffect(PotionEffectType potionEffectType) {
                return false;
            }

            @Override
            public void removePotionEffect(PotionEffectType potionEffectType) {

            }

            @Override
            public Collection<PotionEffect> getActivePotionEffects() {
                return null;
            }

            @Override
            public boolean hasLineOfSight(Entity entity) {
                return false;
            }

            @Override
            public boolean getRemoveWhenFarAway() {
                return false;
            }

            @Override
            public void setRemoveWhenFarAway(boolean b) {

            }

            @Override
            public EntityEquipment getEquipment() {
                return null;
            }

            @Override
            public void setCanPickupItems(boolean b) {

            }

            @Override
            public boolean getCanPickupItems() {
                return false;
            }

            @Override
            public boolean isLeashed() {
                return false;
            }

            @Override
            public Entity getLeashHolder() throws IllegalStateException {
                return null;
            }

            @Override
            public boolean setLeashHolder(Entity entity) {
                return false;
            }

            @Override
            public void damage(double v) {

            }

            @Override
            public void damage(double v, Entity entity) {

            }

            @Override
            public double getHealth() {
                return 0;
            }

            @Override
            public void setHealth(double v) {

            }

            @Override
            public double getMaxHealth() {
                return 0;
            }

            @Override
            public void setMaxHealth(double v) {

            }

            @Override
            public void resetMaxHealth() {

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
            public void setVelocity(Vector vector) {

            }

            @Override
            public Vector getVelocity() {
                return null;
            }

            @Override
            public boolean isOnGround() {
                return false;
            }

            @Override
            public World getWorld() {
                return null;
            }

            @Override
            public boolean teleport(Location location) {
                return false;
            }

            @Override
            public boolean teleport(Location location, PlayerTeleportEvent.TeleportCause teleportCause) {
                return false;
            }

            @Override
            public boolean teleport(Entity entity) {
                return false;
            }

            @Override
            public boolean teleport(Entity entity, PlayerTeleportEvent.TeleportCause teleportCause) {
                return false;
            }

            @Override
            public List<Entity> getNearbyEntities(double v, double v1, double v2) {
                return null;
            }

            @Override
            public int getEntityId() {
                return 0;
            }

            @Override
            public int getFireTicks() {
                return 0;
            }

            @Override
            public int getMaxFireTicks() {
                return 0;
            }

            @Override
            public void setFireTicks(int i) {

            }

            @Override
            public void remove() {

            }

            @Override
            public boolean isDead() {
                return false;
            }

            @Override
            public boolean isValid() {
                return false;
            }

            @Override
            public Server getServer() {
                return null;
            }

            @Override
            public Entity getPassenger() {
                return null;
            }

            @Override
            public boolean setPassenger(Entity entity) {
                return false;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean eject() {
                return false;
            }

            @Override
            public float getFallDistance() {
                return 0;
            }

            @Override
            public void setFallDistance(float v) {

            }

            @Override
            public void setLastDamageCause(EntityDamageEvent entityDamageEvent) {

            }

            @Override
            public EntityDamageEvent getLastDamageCause() {
                return null;
            }

            @Override
            public UUID getUniqueId() {
                return null;
            }

            @Override
            public int getTicksLived() {
                return 0;
            }

            @Override
            public void setTicksLived(int i) {

            }

            @Override
            public void playEffect(EntityEffect entityEffect) {

            }

            @Override
            public EntityType getType() {
                return null;
            }

            @Override
            public boolean isInsideVehicle() {
                return false;
            }

            @Override
            public boolean leaveVehicle() {
                return false;
            }

            @Override
            public Entity getVehicle() {
                return null;
            }

            @Override
            public void setCustomName(String s) {

            }

            @Override
            public String getCustomName() {
                return null;
            }

            @Override
            public void setCustomNameVisible(boolean b) {

            }

            @Override
            public boolean isCustomNameVisible() {
                return false;
            }

            @Override
            public Spigot spigot() {
                return null;
            }

            @Override
            public void sendMessage(String s) {

            }

            @Override
            public void sendMessage(String[] strings) {

            }

            @Override
            public String getName() {
                return null;
            }

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
            public boolean isPermissionSet(String s) {
                return false;
            }

            @Override
            public boolean isPermissionSet(Permission permission) {
                return false;
            }

            @Override
            public boolean hasPermission(String s) {
                return false;
            }

            @Override
            public boolean hasPermission(Permission permission) {
                return false;
            }

            @Override
            public PermissionAttachment addAttachment(Plugin plugin, String s, boolean b) {
                return null;
            }

            @Override
            public PermissionAttachment addAttachment(Plugin plugin) {
                return null;
            }

            @Override
            public PermissionAttachment addAttachment(Plugin plugin, String s, boolean b, int i) {
                return null;
            }

            @Override
            public PermissionAttachment addAttachment(Plugin plugin, int i) {
                return null;
            }

            @Override
            public void removeAttachment(PermissionAttachment permissionAttachment) {

            }

            @Override
            public void recalculatePermissions() {

            }

            @Override
            public Set<PermissionAttachmentInfo> getEffectivePermissions() {
                return null;
            }

            @Override
            public boolean isOp() {
                return false;
            }

            @Override
            public void setOp(boolean b) {

            }

            @Override
            public <T extends Projectile> T launchProjectile(Class<? extends T> aClass) {
                return null;
            }

            @Override
            public <T extends Projectile> T launchProjectile(Class<? extends T> aClass, Vector vector) {
                return null;
            }
        };
    }
}
