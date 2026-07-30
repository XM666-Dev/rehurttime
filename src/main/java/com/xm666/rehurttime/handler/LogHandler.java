package com.xm666.rehurttime.handler;

import com.xm666.rehurttime.Config;
import com.xm666.rehurttime.ReHurtTime;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.config.ModConfigEvent;

public class LogHandler {
    public static class LogHandlerCommon {
        @SubscribeEvent
        public static void onLivingIncomingDamage(LivingAttackEvent event) {
            var result = ExpressionHandler.execute(Config.LOG_FUNCTION.get(), event.getEntity(), event.getSource());
            ReHurtTime.LOGGER.info(result.toString());
        }
    }

    public static class LogHandlerConfig {
        public static void init(IEventBus modEventBus) {
            modEventBus.register(LogHandlerConfig.class);
        }

        @SubscribeEvent
        public static void onConfigLoading(ModConfigEvent.Loading event) {
            toggle();
        }

        @SubscribeEvent
        public static void onConfigReloading(ModConfigEvent.Reloading event) {
            toggle();
        }

        private static void toggle() {
            if (Config.LOG_ENABLED.get()) {
                MinecraftForge.EVENT_BUS.register(LogHandlerCommon.class);
            } else {
                MinecraftForge.EVENT_BUS.unregister(LogHandlerCommon.class);
            }
        }
    }
}
