package com.xm666.rehurttime;

import com.mojang.logging.LogUtils;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;

@Mod(ReHurtTime.MODID)
public class ReHurtTime {
    public static final String MODID = "rehurttime";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ReHurtTime(ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
