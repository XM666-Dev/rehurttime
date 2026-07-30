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
            .define("bypassesInvulnerabilityPredicate", "getEntityType(entity) != 'minecraft:player' && !include(getSourceTags(source), 'neoforge:is_environment') && getSourceType(source) != 'minecraft:campfire'");

    public static final ForgeConfigSpec.ConfigValue<String> APPLIES_KNOCKBACK_PREDICATE = BUILDER
            .define("appliesKnockbackPredicate", "include(getSourceTags(getLastDamageSource(entity)), 'minecraft:no_knockback')");

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
