# Architecture & Symbol Index: Vanilla Outsider: Biome Scale

## 1. Mod Metadata & Entrypoint
- **Mod ID**: `vanilla-outsider-biome-scale`
- **Main Entrypoint**: `net.vanillaoutsider.biomescale.BiomeScaleFabric` (`net.fabricmc.api.ModInitializer`)
- **Client Entrypoint**: `net.vanillaoutsider.biomescale.BiomeScaleFabricClient`

## 2. Bytecode Mixin Target Registry
| Target Vanilla Class | Mixin Class | Purpose |
| :--- | :--- | :--- |
| `Vanilla Class` | `net.vanillaoutsider.biomescale.mixin.MultiNoiseBiomeSourceMixin` | Core mixin hook |

## 3. Core Mechanics & Subsystems
- **Source Root**: `src/main/java/`
- **Resource Root**: `src/main/resources/`

## 4. Dynamic GameRules & Commands
- **GameRules / Commands**: Configured dynamically via namespaced keys (`vanilla-outsider-biome-scale:*`).

## 5. Configuration & Sidedness Isolation
- **Sidedness**: Server-safe logic in main, client isolated in `src/client/java` or client entrypoint.
