# MC 26.3: Guide de configuration et GameRules

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **Avis de non-responsabilité concernant le code source du dépôt** : La documentation de ce Wiki reflète **l'état actuel du code source dans le dépôt**, qui peut inclure des commits récents non publiés ou des fonctionnalités en cours de développement en avance sur les versions publiques sur CurseForge et Modrinth.

---

## 📋 Technical Infobox

| Specification | Technical Details |
| :--- | :--- |
| **System Architecture** | Dynamic Namespaced GameRules (powered by DasikLibrary) |
| **Controlling GameRule** | `biome_scale:scale_modifier` |
| **Legacy Compatibility Alias** | `biomescale:horizontal_scale` |
| **GameRule Category** | `biome_scale:biome_scale` ("Vanilla Outsider: Biome Scale") |
| **Valid Value Range** | `1` to `2147483647` (`Integer.MAX_VALUE`) |
| **Default Setting** | `200` (represents $2.0\times$ horizontal expansion) |
| **Java Implementation** | [`BiomeScaleRules.java`](file:///Biome%20Scale%20v26.3/Biome%20Scale%2026.3/src/main/java/net/vanillaoutsider/biomescale/BiomeScaleRules.java) |

---

## 🎮 Step-by-Step Administrator Workflow

Managing worldgen biome scaling in Minecraft 26.3 is completely integrated into the native `/gamerule` command suite:

```
[ Administrator issues /gamerule ]
               │
               ▼
[ DynamicGameRuleManager dispatches value ]
               │
               ▼
[ BiomeScaleRules server tick listener updates cachedScaleModifier ]
               │
               ▼
[ MultiNoiseBiomeSourceMixin reads updated scale without reload ]
```

### In-Game Command Usage
1. **Query Current Value**:
   ```text
   /gamerule biome_scale:scale_modifier
   ```
2. **Set New Scale Modifier**:
   ```text
   /gamerule biome_scale:scale_modifier 300
   ```
3. **Reset to Vanilla Defaults**:
   ```text
   /gamerule biome_scale:scale_modifier 100
   ```

---

## 🛡️ Player Agency & Anti-Nanny Invariant

In accordance with our core engineering constitution:
- **No Artificial Ceilings**: The GameRule accepts any positive integer up to `2147483647`. If a server operator wants biomes spanning $10,000\times$ vanilla width, the engine complies without arbitrary restrictions.
- **Crash Prevention Only**: Values are constrained strictly at a lower bound ($\ge 1$) to prevent division by zero in the coordinate transformation pipeline.

---

## 💻 Developer & API Extension Hooks

The GameRule is registered through `DasikLibrary` in [`BiomeScaleRules.java`](file:///Biome%20Scale%20v26.3/Biome%20Scale%2026.3/src/main/java/net/vanillaoutsider/biomescale/BiomeScaleRules.java):

```java
public class BiomeScaleRules {
    public static volatile double cachedScaleModifier = 2.0;

    public static void register() {
        DynamicGameRuleManager.integerRule("biome_scale:scale_modifier", CATEGORY, 200)
            .name("Biome Scale Modifier")
            .description("Percentage multiplier for horizontal biome noise sampling (100 = 1.0x).")
            .range(1, Integer.MAX_VALUE)
            .onChanged((server, rule) -> {
                cachedScaleModifier = Math.max(0.01, rule.get() / 100.0);
            })
            .register();
    }
}
```

---

## 🧭 Version Navigation
* [[Back to MC 26.3 Portal|fr_fr-26.3-Home]]
* [[26.3 Terrain & Noise Scaling|fr_fr-26.3-Terrain-and-Noise-Scaling]]
* [[26.3 Architecture & Mixins|fr_fr-26.3-Architecture-and-Mixins]]
* [[Master Documentation Home|fr_fr-Home]]
