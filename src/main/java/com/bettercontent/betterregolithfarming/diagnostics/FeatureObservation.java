package com.bettercontent.betterregolithfarming.diagnostics;
import java.util.function.Supplier;
/** Pure owner-specific projection of already computed native outcomes; no gameplay writes or RNG. */
public final class FeatureObservation {
    private FeatureObservation() {}
    public static void till(PlaytestLog log, Supplier<PlaytestLog.Context> context, boolean placed, Object source, String target) {
        if (!log.enabled("tilling")) return;
        log.debug("tilling", "farmland.till", context.get(), placed ? "placed" : "rejected", placed ? "native_set_block" : "native_set_block_false", () -> PlaytestLog.fields("source", source, "target", target));
    }
    public static void missingMapping(PlaytestLog log, Supplier<PlaytestLog.Context> context, Object source, String target) {
        if (!log.enabled("tilling")) return;
        log.debug("tilling", "farmland.till", context.get(), "rejected", "missing_mapping", () -> PlaytestLog.fields("source", source, "target", target));
    }
    public static void decision(PlaytestLog log, String category, String event, String result, String reason) {
        if (!log.enabled(category)) return;
        log.event(category, event, "server", result, reason, () -> "");
    }
}
