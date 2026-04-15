package com.xm666.rehurttime.handler;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.Set;
import java.util.stream.Collectors;

public class ExpressionHandler {
    public static String getEntityType(Entity entity) {
        if (entity != null) {
            return BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).toString();
        }
        return "";
    }

    public static Set<String> getEntityTags(Entity entity) {
        if (entity != null) {
            return entity.getTags();
        }
        return Set.of();
    }

    public static String getItemType(ItemStack stack) {
        if (stack != null) {
            return BuiltInRegistries.ITEM.getKey(stack.getItem()).toString();
        }
        return "";
    }

    public static Set<String> getItemTags(ItemStack stack) {
        if (stack != null) {
            return stack.getTags().map(TagKey::location).map(Identifier::toString).collect(Collectors.toSet());
        }
        return Set.of();
    }

    public static String getHolderType(Holder<?> holder) {
        if (holder != null) {
            var key = holder.getKey();
            if (key != null) {
                return key.identifier().toString();
            }
        }
        return "";
    }

    public static Set<String> getHolderTags(Holder<?> holder) {
        if (holder != null) {
            return holder.tags().map(TagKey::location).map(Identifier::toString).collect(Collectors.toSet());
        }
        return Set.of();
    }

    public static String getSourceType(DamageSource source) {
        if (source != null) {
            return getHolderType(source.typeHolder());
        }
        return "";
    }

    public static Set<String> getSourceTags(DamageSource source) {
        if (source != null) {
            return getHolderTags(source.typeHolder());
        }
        return Set.of();
    }

    public static Object execute(String expression, LivingEntity entity, DamageSource source) {
        return EvaluatorHandler.execute(expression, "entity", entity, "source", source);
    }

    public static boolean test(String expression, LivingEntity entity, DamageSource source) {
        return (boolean) execute(expression, entity, source);
    }
}
