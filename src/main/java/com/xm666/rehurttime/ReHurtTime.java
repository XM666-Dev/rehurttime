package com.xm666.rehurttime;

import com.mojang.logging.LogUtils;
import com.xm666.rehurttime.handler.LogHandler;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(ReHurtTime.MODID)
public class ReHurtTime {
    public static final String MODID = "rehurttime";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ReHurtTime(FMLJavaModLoadingContext context) {
        this(context.getContainer(), context.getModEventBus());
    }

    public ReHurtTime(ModContainer container, IEventBus eventBus) {
        Config.init(container);
        LogHandler.LogHandlerConfig.init(eventBus);
    }
}
