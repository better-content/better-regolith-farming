package com.bettercontent.betterregolithfarming;

import com.bettercontent.betterregolithfarming.compat.RegolithFarmlandPalette;
import com.bettercontent.betterregolithfarming.compat.RegolithFarmlandTilling;
import com.bettercontent.betterregolithfarming.gametest.SourceberryFarmlandGameTests;
import net.minecraftforge.event.RegisterGameTestsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ModMain.MOD_ID)
public final class ModMain {
    public static final String MOD_ID = "better_regolith_farming";

    public ModMain() {
        var bus = FMLJavaModLoadingContext.get().getModEventBus();
        RegolithFarmlandPalette.BLOCKS.register(bus);
        RegolithFarmlandPalette.ITEMS.register(bus);
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.register(RegolithFarmlandTilling.class);
        bus.addListener(this::registerTests);
    }
    private void registerTests(RegisterGameTestsEvent event) {
        event.register(SourceberryFarmlandGameTests.class);
    }
}
