# MC 26.1: 架構與 Mixin 解析

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **存放庫原始碼免責聲明**：本 Wiki 中的文件反映了**存放庫中的當前原始碼狀態**，可能包含領先於 CurseForge 與 Modrinth 上公開版本的近期未發布提交或開發特性。

---

## 📋 Architectural Infobox

| Component | Technical Details |
| :--- | :--- |
| **Package Root** | `net.vanillaoutsider.biomescale` |
| **Main Mod Initializer** | [`BiomeScaleFabric.java`](file:///Biome%20Scale%2026.1/src/main/java/net/vanillaoutsider/biomescale/BiomeScaleFabric.java) |
| **Client Mod Initializer** | [`BiomeScaleFabricClient.java`](file:///Biome%20Scale%2026.1/src/main/java/net/vanillaoutsider/biomescale/BiomeScaleFabricClient.java) |
| **GameRule Subsystem** | [`BiomeScaleRules.java`](file:///Biome%20Scale%2026.1/src/main/java/net/vanillaoutsider/biomescale/BiomeScaleRules.java) |
| **Mixin Class** | [`MultiNoiseBiomeSourceMixin.java`](file:///Biome%20Scale%2026.1/src/main/java/net/vanillaoutsider/biomescale/mixin/MultiNoiseBiomeSourceMixin.java) |
| **Target Engine Class** | `net.minecraft.world.level.biome.MultiNoiseBiomeSource` |
| **Target Method** | `getNoiseBiome(IIILnet/minecraft/world/level/biome/Climate$Sampler;)Lnet/minecraft/core/Holder;` |
| **Bytecode Injector** | `@ModifyVariable(at = @At("HEAD"), argsOnly = true)` |
| **Library Dependency** | `net.dasik:dasik-library:1.6.9+build.24` |

---

## 🏗️ Architectural Overview & Package Hierarchy

```
net.vanillaoutsider.biomescale/
├── BiomeScaleFabric.java           # Server-authoritative mod initializer
├── BiomeScaleFabricClient.java     # Optional client initialization entrypoint
├── BiomeScaleRules.java            # Dynamic GameRule registry integration
└── mixin/
    └── MultiNoiseBiomeSourceMixin.java # Bytecode coordinate interception
```

### Subsystem Flow Diagram
```
┌──────────────────────────────────────────────────────────┐
│               FABRIC LOADER (MC 26.1)                   │
│                            │                             │
│       ┌────────────────────┴────────────────────┐        │
│       ▼                                         ▼        │
│ [BiomeScaleFabric]                     [BiomeScaleRules] │
│ (Mod Initializer)                      (GameRules Init)  │
│       │                                         │        │
│       ▼                                         ▼        │
│ [MultiNoiseBiomeSourceMixin] <─────── [cachedScaleModifier]
└──────────────────────────────────────────────────────────┘
```

---

## 🔍 Detailed Mixin Bytecode & Injection Analysis

```java
package net.vanillaoutsider.biomescale.mixin;

import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.vanillaoutsider.biomescale.BiomeScaleRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(MultiNoiseBiomeSource.class)
public class MultiNoiseBiomeSourceMixin {

    @ModifyVariable(
        method = "getNoiseBiome(IIILnet/minecraft/world/level/biome/Climate$Sampler;)Lnet/minecraft/core/Holder;",
        at = @At("HEAD"),
        argsOnly = true,
        ordinal = 0
    )
    private int biomescale$modifyX(int x) {
        double scale = BiomeScaleRules.cachedScaleModifier;
        if (scale <= 0.01) return x;
        return (int) (x / scale);
    }

    @ModifyVariable(
        method = "getNoiseBiome(IIILnet/minecraft/world/level/biome/Climate$Sampler;)Lnet/minecraft/core/Holder;",
        at = @At("HEAD"),
        argsOnly = true,
        ordinal = 2
    )
    private int biomescale$modifyZ(int z) {
        double scale = BiomeScaleRules.cachedScaleModifier;
        if (scale <= 0.01) return z;
        return (int) (z / scale);
    }
}
```

| Injection Method | Target Method | Injected Parameter | Injection Point | Ordinal | Technical Action |
| :--- | :--- | :--- | :---: | :---: | :--- |
| `biomescale$modifyX` | `getNoiseBiome` | `int x` | `@At("HEAD")` | `0` | Intercepts chunk coordinate $X$ and divides by scale $S_h$. |
| `biomescale$modifyZ` | `getNoiseBiome` | `int z` | `@At("HEAD")` | `2` | Intercepts chunk coordinate $Z$ and divides by scale $S_h$. |

---

## 🧭 Version Navigation
* [[Back to MC 26.1 Portal|zh_tw-26.1-Home]]
* [[26.1 Terrain & Noise Scaling|zh_tw-26.1-Terrain-and-Noise-Scaling]]
* [[26.1 Configuration & GameRules|zh_tw-26.1-Configuration-and-GameRules]]
* [[Master Documentation Home|zh_tw-Home]]
