# Better Regolith Farming

## Scope and authority

This repository owns its mod-specific behavior and authoring inputs. Read [local instructions](AGENTS.md)
and the [shared documentation/policy index](../../better-content-modpack/docs/README.md).


Adds farmable block variants for the 17 Unearthed regolith types, with farmland tags, loot, moisture, crop support, and Sourceberry compatibility. Registry IDs use the `better_regolith_farming` namespace.

Requires Unearthed 2.3.0.

Build with `./gradlew verifyFast`, run GameTests with `./gradlew verifyFull`, and stage the reobfuscated jar with `./gradlew stageRuntimeJar`.
