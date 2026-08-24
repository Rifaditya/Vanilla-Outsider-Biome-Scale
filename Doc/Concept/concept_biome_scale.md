# Concept: Biome Scale Tweaker (Minecraft 26.x)

## Philosophy Fit
**Vanilla Outsider (VO)**: "One Click, One Action."
Instead of arbitrarily injecting new structures or custom biomes, this mod fundamentally enhances the core foundation of world generation. It solves the pervasive "fruit salad" problem—where tiny, disparate biomes clash jarringly against one another—by organically stretching the terrain to create vast, cohesive, and immersive continents that respect the game's original design intent.

**Instant Gratification (IG)**: "Respect Player's Time."
Provides immediate, observable changes to world generation without complex configuration files. A single GameRule adjustment instantly scales all newly generated chunks.

## Core Mechanics & Implementation Hooks

### 1. Universal Noise Rescaling
- **Description**: Re-scales the internal multi-noise biome source parameters during world generation. Lowering the frequency (increasing the scale) of the noise maps forces biomes to stretch out naturally across the map, preserving Vanilla's organic blending algorithms.
- **Implementation Hook**: Inject into `MultiNoiseBiomeSource` and `Climate$Sampler` (or the 26.x equivalent, such as `NoiseRouterData` coordinate samplers). Apply a constant, inverse multiplier to the coordinate sampling inputs (`x, y, z`) immediately before the internal noise maps are evaluated. This must be an `@Inject` or `@Redirect` on the coordinate calculation.

### 2. Native Modded Biome Compatibility
- **Description**: By modifying the core noise evaluation mathematically, any datapack or mod (e.g., Terralith, Biomes O' Plenty) hooking into the standard Minecraft 26.x dimension generator automatically inherits this scaling. No hardcoded API dependencies or explicit compatibility patches are required.
- **Implementation Hook**: Target the `NoiseRouter` or `BiomeSupplier` at a structural layer *above* specific biome registry definitions. Ensure Mixins apply securely at the highest coordinate-sampling tier, avoiding conflicts with biome registry injections.

## Configuration Engine
Leveraging **DasikLibrary** to enforce the "Thin Mod, Fat Library" architecture. Allows for dynamic, real-time adjustments via GameRules (changes apply exclusively to newly generated chunks).

- **GameRule Registration**: Must use DasikLibrary's `DynamicGameRuleManager` to ensure namespaced ID support (`biome_scale:scale_modifier`) and UI visibility in Minecraft 26.x.
- **GameRule**: `globalBiomeScaleModifier` (Double, default `2.0`). 
  - A value of `2.0` yields biomes roughly twice the size of standard Vanilla.
  - A value of `0.5` generates micro-biomes.
- **Localization Mandate**: Tooltips MUST use the dot-notation key (`gamerule.biome_scale.scale_modifier.description`) and provide exhaustive, premium descriptions. Display Names MUST use spaces (no underscores).

## Project Metadata
- **Version Format**: `1.0.0+build.1` (Strict adherence to semantic versioning with build numbers).
- **Internal Dependency**: `"dasik-library": "*"` (Standalone usage, no JiJ bundling).
- **Archive Strategy**: Store old build `.jar` artifacts in `/Archive/builds/`.

## Asset Requirements
- **Particles**: None (Pure Code/Mixin implementation)
- **Sounds**: None
- **Models**: None

## Quality Assurance [PRO PROTOCOL]
- **Debugging & Telemetry**: 
  - Pre-generate a 5000x5000 map block. Install the mod with `globalBiomeScaleModifier 5.0` and generate the exact same seed in a parallel instance to visually verify the continent-sweeping effect.
  - Stress-test alongside heavy world-generation mods (e.g., Terralith) to confirm seamless noise scaling without logic conflicts or `StackOverflowError`s in recursive noise sampling.
- **Test Cases**:
  - **Chunk Blending**: Verify smooth transitions at chunk boundaries if the scaling modifier is altered mid-game (using chunk border tests).
  - **Structure Integrity**: Validate that rivers, villages, and monuments continue to generate correctly and logically despite the stretched terrain noise. Ensure structure spacing isn't adversely broken.

## Implementation Checklist
- [ ] Hook into `MultiNoiseBiomeSource` / `Climate$Sampler` (Minecraft 26.x) to intercept parameter inputs.
- [ ] Apply the configurable inverse scale to noise evaluation coordinates.
- [ ] Register the `globalBiomeScaleModifier` GameRule via DasikLibrary's `DynamicGameRuleManager`.
- [ ] Add rigorous localization for GameRules per the Localization Mandate.
- [ ] Author and update Platform Documentation (CurseForge/Modrinth) using the Zenith Scribe standards.
