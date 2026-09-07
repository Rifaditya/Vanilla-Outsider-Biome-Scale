# MC 26.1: 아키텍처 및 Mixin 분석

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **저장소 소스 코드 면책 조항**: 이 위키의 문서는 CurseForge 및 Modrinth의 공개 릴리스 빌드보다 앞선 최근 미출시 커밋 또는 개발 기능을 포함할 수 있는 **저장소의 현재 소스 코드 상태**를 반영합니다.

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
* [[Back to MC 26.1 Portal|ko_kr-26.1-Home]]
* [[26.1 Terrain & Noise Scaling|ko_kr-26.1-Terrain-and-Noise-Scaling]]
* [[26.1 Configuration & GameRules|ko_kr-26.1-Configuration-and-GameRules]]
* [[Master Documentation Home|ko_kr-Home]]
