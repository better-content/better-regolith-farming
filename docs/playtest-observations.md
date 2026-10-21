# Bounded playtest observations

## Authority and feature scope

This repository owns the observations at `RegolithFarmlandTilling`. Authoritative source remains the
behavior owner; the local full gate tests native behavior. Logging source/privacy contracts
are not pack runtime, performance or visual acceptance.

Tilling records the returned native setBlockAndUpdate Boolean, not mere invocation; existing source and target registered IDs are safe authored identifiers. Successful/false native writes and missing-mapping rejection carry existing dimension/tick/till-target coordinates plus source and target registered IDs. This is the causal ground block, not a player location, inventory identity or new persistent operation ID. Context is created only after the enabled guard; no added block lookup is needed. Missing mapping remains a rejection and retains the existing normal warning. No native state, sound, durability or cancellation behavior is changed to improve a log outcome.

## Controls, bounds and lifecycle

The existing loader SLF4J backend owns `bc.playtest.better_regolith_farming.*`; no runtime provider
is bundled. Startup reports real mod version, effective loaded instrumentation and own-artifact
source metadata when available, otherwise UNKNOWN. Metadata is bounded and read only from this
mod's exact Forge ModFile at initialization. Normal standalone logging stays INFO; an explicitly
selected DEBUG profile enables causal records. One process-local instance shares 120 detail
records/minute, burst20, bounded256 aggregation keys and 2048-character single-line records.
Native END-server-tick boundaries coalesce monotonic30-second summaries; server stop clears
keys without replenishing the bucket or rearming TRACE. Actual failures preserve existing
normal severity/stack traces independently of detail suppression.

Focused TRACE remains default off and requires `bc.playtest.trace.better_regolith_farming.dimension`
and exactly one `.subject` UUID or `.pos=x,y,z`; the once-per-process window begins on the first
matching detail and expires at60seconds/1000records, also constrained by the common bucket.
These feature boundaries currently emit causal DEBUG, not per-tick/per-frame TRACE. The helper
supports focused details but a focus property does not invent an uninstrumented native observer.
Hot diagnostics guard before native context/field creation; no extra inventory/chunk scan,
RNG draw, entity construction, packet or persistent ledger is added.

## Native source fixture boundary

The full gate now uses Forge's actual fresh retained `run-gametest/attempts/<UUID>` RunConfig,
not a warm world or JavaExec's premature source-root default. The prior latest-log deletion
hook is removed; all old directories/logs stay intact. Missing properties use native defaults
without overwriting an existing file. Pure setup negatives exercise ERROR/FATAL rejection;
actual native completion requires exactly3 authored required cases and strict error-free logs.
The new fixture/projection needs its fresh full gate; Gradle exit0 alone is not acceptance.

## Verification boundary

The unchanged shared helper contract covers off/lazy behavior, escaping/truncation,
budget/cardinality/overflow/fakeclock/reset/focus and own-metadata validation. The owner-specific
source contract asserts actual event seams/privacy markers, not native success. Existing native
feature tests and the owning documented full gate remain required before publication. Exact
candidate gameplay, paired native UI review where relevant and performance acceptance are
separate; do not infer them from a causal log or a source-only pass.
