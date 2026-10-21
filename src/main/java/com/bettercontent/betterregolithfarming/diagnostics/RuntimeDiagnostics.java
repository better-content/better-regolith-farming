package com.bettercontent.betterregolithfarming.diagnostics;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.server.ServerStoppedEvent;

/** One process-local budget; never retains world/player/menu references. */
@Mod.EventBusSubscriber(modid = "better_regolith_farming")
public final class RuntimeDiagnostics {
    public static final PlaytestLog LOG = new PlaytestLog("better_regolith_farming");
    private RuntimeDiagnostics() {}
    public static void initialize() {
        var mods = net.minecraftforge.fml.ModList.get();
        var own = mods.getModFileById("better_regolith_farming");
        String source = LOG.sourceIdentity(own == null ? null : own.getFile().findResource("META-INF", "better-content-source.properties"));
        String version = mods.getModContainerById("better_regolith_farming").map(c -> c.getModInfo().getVersion().toString()).orElse("UNKNOWN");
        LOG.status("common", version, source, () -> PlaytestLog.fields("enabled", true, "unearthed", mods.isLoaded("unearthed"), "ars_nouveau", mods.isLoaded("ars_nouveau")));
    }
    public static void event(String category, String event, String result, String reason) {
        if (!LOG.enabled(category)) return;
        FeatureObservation.decision(LOG, category, event, result, reason);
    }
    /** Existing till target, not a player location or a diagnostic position ledger. */
    public static PlaytestLog.Context tillContext(net.minecraft.world.level.Level level, net.minecraft.core.BlockPos pos) {
        return new PlaytestLog.Context(level.isClientSide ? "client" : "server", level.dimension().location().toString(), level.getGameTime(), "",
            pos.getX() + "," + pos.getY() + "," + pos.getZ());
    }
    public static PlaytestLog.Context context(net.minecraft.world.level.Level level) {
        return new PlaytestLog.Context(level.isClientSide ? "client" : "server", level.dimension().location().toString(), level.getGameTime(), "", "");
    }
    @SubscribeEvent public static void tick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) LOG.summary("server");
    }
    @SubscribeEvent public static void stopped(ServerStoppedEvent event) { LOG.reset("server"); }
}
