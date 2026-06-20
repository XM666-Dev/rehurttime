package com.xm666.rehurttime.handler;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.Set;
import java.util.stream.Collectors;

public class ExpressionHandler {
    public static String getEntityType(Entity entity) {
        if (entity == null) return "";

        return BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).toString();

    }

    public static Set<String> getEntityTags(Entity entity) {
        if (entity == null) return Set.of();

        return entity.getTags();
    }

    public static String getItemType(ItemStack stack) {
        if (stack == null) return "";

        return BuiltInRegistries.ITEM.getKey(stack.getItem()).toString();
    }

    public static Set<String> getItemTags(ItemStack stack) {
        if (stack == null) return Set.of();

        return stack.getTags().map(TagKey::location).map(ResourceLocation::toString).collect(Collectors.toSet());
    }

    public static String getHolderType(Holder<?> holder) {
        if (holder == null) return "";

        var key = holder.unwrapKey().orElse(null);
        if (key == null) return "";

        return key.location().toString();
    }

    public static Set<String> getHolderTags(Holder<?> holder) {
        if (holder == null) return Set.of();

        return holder.tags().map(TagKey::location).map(ResourceLocation::toString).collect(Collectors.toSet());
    }

    public static String getSourceType(DamageSource source) {
        if (source == null) return "";

        return getHolderType(source.typeHolder());
    }

    public static Set<String> getSourceTags(DamageSource source) {
        if (source == null) return Set.of();

        return getHolderTags(source.typeHolder());
    }

    public static Object execute(String expression, LivingEntity entity, DamageSource source) {
        return EvaluatorHandler.execute(expression, "entity", entity, "source", source);
    }

    public static boolean test(String expression, LivingEntity entity, DamageSource source) {
        return (boolean) execute(expression, entity, source);
    }
}
