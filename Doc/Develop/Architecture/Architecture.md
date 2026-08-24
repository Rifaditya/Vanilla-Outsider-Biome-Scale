# Biome Scale Architecture

## System Overview

```mermaid
flowchart TD
    A[Chunk Generator Noise Sampling] --> B{MultiNoiseBiomeSourceMixin}
    B --> C[Apply BiomeScaleRules Inverse Modifier]
    C --> D[Return Stretched Noise Map Coordinates]
```

## Module Responsibilities

- **BiomeScaleRules**: Registers the GameRule and caches the modifier on tick.
- **MultiNoiseBiomeSourceMixin**: Intercepts `getNoiseBiome` coordinate arguments to modify `x` and `z`.

## Design Decisions

- Hooked directly into `MultiNoiseBiomeSource` arguments to preserve vanilla noise blending without modifying actual chunk generation logic.
