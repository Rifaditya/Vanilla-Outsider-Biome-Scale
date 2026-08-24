# Audit Helper for Moderators

This document provides a quick overview for human moderators (e.g., CurseForge, Modrinth) to verify the safety and scope of this mod.

## Scope
- Server-side logic for chunk generation. Client-side only needs it for singleplayer worlds.

## Network
- NO network packets are sent or received by this mod.

## File System
- NO files are read or written outside of standard Minecraft/Fabric procedures. No config file is generated.

## Mixins
- `MultiNoiseBiomeSourceMixin`: Intercepts `getNoiseBiome` to modify biome coordinate scales.

This mod relies solely on `fabric-api` and `dasik-library`.
