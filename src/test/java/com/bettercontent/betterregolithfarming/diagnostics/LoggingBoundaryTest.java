package com.bettercontent.betterregolithfarming.diagnostics;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/** Supplementary wiring/privacy invariant, not the actual emitter or native behavior test. */
final class LoggingBoundaryTest {
    @Test void authoritativeFeatureBoundaryAndPrivacyRemainExplicit() throws Exception {
        String source = Files.readString(Path.of("src/main/java/com/bettercontent/betterregolithfarming/compat/RegolithFarmlandTilling.java")) +
            Files.readString(Path.of("src/main/java/com/bettercontent/betterregolithfarming/diagnostics/FeatureObservation.java"));
        assertTrue(source.contains("FeatureObservation.till"), "FeatureObservation.till");
        assertTrue(source.contains("native_set_block_false"), "native_set_block_false");
        assertTrue(source.contains("boolean placed = level.setBlockAndUpdate"), "boolean placed = level.setBlockAndUpdate");
        assertFalse(source.contains("fields(\"nbt\""));
        assertFalse(source.contains("fields(\"payload\""));
        assertFalse(source.contains("fields(\"seed\""));
        assertTrue(source.contains("enabled("));
    }
}
