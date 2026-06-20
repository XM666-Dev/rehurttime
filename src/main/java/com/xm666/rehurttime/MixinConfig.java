package com.xm666.rehurttime;

import net.neoforged.neoforge.common.ModConfigSpec;

public class MixinConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue BYPASSES_INVULNERABILITY_ENABLED = BUILDER
            .define("bypassesInvulnerabilityEnabled", true);

    public static final ModConfigSpec.BooleanValue APPLIES_KNOCKBACK_ENABLED = BUILDER
            .define("appliesKnockbackEnabled", true);

    public static final ModConfigSpec SPEC = BUILDER.build();
}
