package com.xm666.rehurttime;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.config.ConfigTracker;
import net.minecraftforge.fml.config.IConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;

import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.Locale;

public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.ConfigValue<String> BYPASSES_INVULNERABILITY_PREDICATE = BUILDER
            .comment("""
                    Available variables:
                    LivingEntity entity
                    DamageSource source
                    Available methods:
                    String getEntityType(Entity entity)
                    Set<String> getEntityTags(Entity entity)
                    String getSourceType(DamageSource source)
                    Set<String> getSourceTags(DamageSource source)
                    String getItemType(ItemStack stack)
                    Set<String> getItemTags(ItemStack stack)
                    DamageSource getLastDamageSource(Entity entity)
                    Entity getEntity(DamageSource source)
                    Entity getDirectEntity(DamageSource source)
                    ItemStack getWeaponItem(DamageSource source)""")
            .define("bypassesInvulnerabilityPredicate", "if (getEntityType(entity) != 'minecraft:player') {let type = getSourceType(source); type != 'minecraft:in_fire' && type != 'minecraft:on_fire' && type != 'minecraft:lava' && type != 'minecraft:hot_floor' && type != 'minecraft:drown' && type != 'minecraft:starve' && type != 'minecraft:dry_out' && type != 'minecraft:freeze' && type != 'minecraft:lightning_bolt' && type != 'minecraft:cactus' && type != 'minecraft:stalagmite' && type != 'minecraft:falling_stalactite' && type != 'minecraft:falling_block' && type != 'minecraft:falling_anvil' && type != 'minecraft:cramming' && type != 'minecraft:fly_into_wall' && type != 'minecraft:sweet_berry_bush' && type != 'minecraft:in_wall'}");

    public static final ForgeConfigSpec.ConfigValue<String> APPLIES_KNOCKBACK_PREDICATE = BUILDER
            .define("appliesKnockbackPredicate", "let lastSource = getLastDamageSource(entity); include(getSourceTags(lastSource), 'minecraft:is_explosion') || getEntity(lastSource) == nil");

    public static final ForgeConfigSpec.BooleanValue LOG_ENABLED = BUILDER
            .define("logEnabled", false);

    public static final ForgeConfigSpec.ConfigValue<String> LOG_FUNCTION = BUILDER
            .define("logFunction", "seq.map('entityType', getEntityType(entity), 'sourceEntityType', getEntityType(getEntity(source)), 'sourceType', getSourceType(source))");

    private static final ForgeConfigSpec SPEC = BUILDER.build();

    public static void init(ModContainer container) {
        registerConfig(ModConfig.Type.COMMON, SPEC, container);
    }

    public static void registerConfig(ModConfig.Type type, IConfigSpec<?> spec, ModContainer container) {
        registerConfig(type, spec, container, type.extension());
    }

    public static void registerConfig(ModConfig.Type type, IConfigSpec<?> spec, ModContainer container, String extension) {
        var fileName = String.format(Locale.ROOT, "%s-%s.toml", ReHurtTime.MODID, extension);
        var config = new ModConfig(type, spec, container, fileName);
        try {
            var method = ConfigTracker.class.getDeclaredMethod("openConfig", ModConfig.class, Path.class);
            method.setAccessible(true);
            method.invoke(ConfigTracker.INSTANCE, config, FMLPaths.CONFIGDIR.get());
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
