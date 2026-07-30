package com.xm666.rehurttime;

import com.mojang.logging.LogUtils;
import com.xm666.rehurttime.handler.LogHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(ReHurtTime.MODID)
public class ReHurtTime {
    public static final String MODID = "rehurttime";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ReHurtTime(FMLJavaModLoadingContext context) {
        var container = context.getContainer();
        var eventBus = context.getModEventBus();
        Config.init(container);
        LogHandler.LogHandlerConfig.init(eventBus);
    }
}
