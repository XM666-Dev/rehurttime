package com.xm666.rehurttime.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.xm666.rehurttime.Config;
import com.xm666.rehurttime.handler.ExpressionHandler;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

public class AppliesKnockbackMixin {
    @Mixin(LivingEntity.class)
    private static class LivingEntityMixin {
        @ModifyVariable(method = "hurtServer", at = @At(value = "STORE", ordinal = 1), name = "flag1", ordinal = 1)
        private boolean modifyKnockback(boolean knockback, @Local(argsOnly = true) DamageSource source) {
            if (knockback) return true;

            var appliesKnockbackPredicate = Config.APPLIES_KNOCKBACK_PREDICATE.get();
            var living = (LivingEntity) (Object) this;
            return ExpressionHandler.test(appliesKnockbackPredicate, living, source);
        }
    }
}
