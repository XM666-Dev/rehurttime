package com.xm666.rehurttime;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(value = ReHurtTime.MODID, dist = Dist.CLIENT)
public class ReHurtTimeClient {
    public ReHurtTimeClient(ModContainer container) {
        //container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
}
