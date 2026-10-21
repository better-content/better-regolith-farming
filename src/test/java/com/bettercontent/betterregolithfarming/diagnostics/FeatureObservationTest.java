package com.bettercontent.betterregolithfarming.diagnostics;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import static org.junit.jupiter.api.Assertions.*;
/** Actual scalar production emitters; not whole-native gameplay/RNG equivalence. */
final class FeatureObservationTest {
    public static void main(String[] args) { new FeatureObservationTest().actualEmitterStagesPrivacyAndObservationalPurity(); }
    @Test void actualEmitterStagesPrivacyAndObservationalPurity() {
        var records = new ArrayList<String>(); boolean[] enabled = {false};
        var log = new PlaytestLog("better_regolith_farming", new PlaytestLog.Sink() {
            public boolean enabled(String category, String level) { return enabled[0]; }
            public void write(String category, String level, String text) { records.add(text); }
        }, () -> 0L, new Properties());
        var reads = new AtomicInteger();
        Supplier<PlaytestLog.Context> first = () -> { reads.incrementAndGet(); return new PlaytestLog.Context("server", "minecraft:overworld", 10, "", "1,64,2"); };
        Supplier<PlaytestLog.Context> second = () -> { reads.incrementAndGet(); return new PlaytestLog.Context("server", "minecraft:overworld", 10, "", "3,64,2"); };
        for (boolean active : new boolean[]{false, true}) {
            enabled[0] = active;
            FeatureObservation.till(log, first, true, "unearthed:slate", "better_regolith_farming:slate_farmland");
            FeatureObservation.till(log, second, false, "unearthed:slate", "better_regolith_farming:slate_farmland");
            FeatureObservation.missingMapping(log, first, "unearthed:slate", "better_regolith_farming:slate_farmland");
            if (!active) { assertTrue(records.isEmpty()); assertEquals(0, reads.get()); }
        }
        assertEquals(3, records.size()); assertEquals(3, reads.get());
        assertTrue(records.get(0).contains("result=placed reason=native_set_block"));
        assertTrue(records.get(1).contains("result=rejected reason=native_set_block_false"));
        assertTrue(records.get(2).contains("result=rejected reason=missing_mapping"));
        assertTrue(records.get(0).contains("pos=1,64,2")); assertTrue(records.get(1).contains("pos=3,64,2"));
        assertTrue(records.get(2).contains("pos=1,64,2"));
        assertTrue(records.stream().allMatch(s -> s.contains("dimension=minecraft:overworld tick=10") && s.contains("source=unearthed:slate target=better_regolith_farming:slate_farmland")));
        assertTrue(records.stream().noneMatch(s -> s.contains("inventory=") || s.contains("nbt=") || s.contains("subject=")));
    }
}
