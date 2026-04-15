package com.xm666.rehurttime.handler;

import com.xm666.rehurttime.Config;
import com.xm666.rehurttime.ReHurtTime;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.LivingAttackEvent;

public class LogHandler {
    private static class LogHandlerCommon {
        @SubscribeEvent
        static void onLivingIncomingDamage(LivingAttackEvent event) {
            var result = ExpressionHandler.execute(Config.LOG_FUNCTION.get(), event.getEntity(), event.getSource());
            ReHurtTime.LOGGER.info(result.toString());
        }
    }

    @Mod(value = ReHurtTime.MODID)
    public static class LogHandlerConfig {
        public LogHandlerConfig(IEventBus modEventBus) {
            modEventBus.register(LogHandlerConfig.class);
        }

        @SubscribeEvent
        static void onConfigLoading(ModConfigEvent.Loading event) {
            toggle();
        }

        @SubscribeEvent
        static void onConfigReloading(ModConfigEvent.Reloading event) {
            toggle();
        }

        static void toggle() {
            if (Config.LOG_ENABLED.get()) {
                NeoForge.EVENT_BUS.register(LogHandlerCommon.class);
            } else {
                NeoForge.EVENT_BUS.unregister(LogHandlerCommon.class);
            }
        }
    }
}
