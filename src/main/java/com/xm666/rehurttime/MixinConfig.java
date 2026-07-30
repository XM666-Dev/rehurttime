package com.xm666.rehurttime;

import net.minecraftforge.common.ForgeConfigSpec;

public class MixinConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.BooleanValue BYPASSES_INVULNERABILITY_ENABLED = BUILDER
            .define("bypassesInvulnerabilityEnabled", true);

    public static final ForgeConfigSpec.BooleanValue APPLIES_KNOCKBACK_ENABLED = BUILDER
            .define("appliesKnockbackEnabled", true);

    public static final ForgeConfigSpec SPEC = BUILDER.build();
}
