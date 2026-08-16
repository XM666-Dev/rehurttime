package com.xm666.rehurttime;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

@Mod(ReHurtTime.MODID)
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.ConfigValue<String> BYPASSES_INVULNERABILITY_PREDICATE = BUILDER
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
            .define("bypassesInvulnerabilityPredicate", "getEntityType(entity) != 'minecraft:player' && !include(getSourceTags(source), 'neoforge:is_environment')");

    public static final ModConfigSpec.ConfigValue<String> APPLIES_KNOCKBACK_PREDICATE = BUILDER
            .define("appliesKnockbackPredicate", "include(getSourceTags(getLastDamageSource(entity)), 'minecraft:no_knockback')");

    public static final ModConfigSpec.BooleanValue LOG_ENABLED = BUILDER
            .define("logEnabled", false);

    public static final ModConfigSpec.ConfigValue<String> LOG_FUNCTION = BUILDER
            .define("logFunction", "seq.map('entityType', getEntityType(entity), 'sourceEntityType', getEntityType(getEntity(source)), 'sourceType', getSourceType(source))");

    private static final ModConfigSpec SPEC = BUILDER.build();

    public Config(ModContainer container) {
        container.registerConfig(ModConfig.Type.COMMON, SPEC);
    }
}
