package com.xm666.rehurttime;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.jetbrains.annotations.NotNull;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.ConfigValue<@NotNull String> BYPASSES_INVULNERABILITY_PREDICATE = BUILDER
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

    public static final ModConfigSpec.ConfigValue<@NotNull String> APPLIES_KNOCKBACK_PREDICATE = BUILDER
            .define("appliesKnockbackPredicate", "include(getSourceTags(getLastDamageSource(entity)), 'minecraft:no_knockback')");

    public static final ModConfigSpec.BooleanValue LOG_ENABLED = BUILDER
            .define("logEnabled", false);

    public static final ModConfigSpec.ConfigValue<@NotNull String> LOG_FUNCTION = BUILDER
            .define("logFunction", "seq.map('entityType', getEntityType(entity), 'sourceEntityType', getEntityType(getEntity(source)), 'sourceType', getSourceType(source))");

    static final ModConfigSpec SPEC = BUILDER.build();
}
