# MC 26.1: Guia de Configuração e GameRules

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **Isenção de Responsabilidade do Código-Fonte do Repositório**: A documentação nesta Wiki reflete o **estado atual do código-fonte no repositório**, que pode incluir commits recentes não lançados ou recursos de desenvolvimento à frente das versões públicas no CurseForge e Modrinth.

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
| **Java Implementation** | [`BiomeScaleRules.java`](file:///Biome%20Scale%2026.1/src/main/java/net/vanillaoutsider/biomescale/BiomeScaleRules.java) |

---

## 🎮 Step-by-Step Administrator Workflow

Managing worldgen biome scaling in Minecraft 26.1 is completely integrated into the native `/gamerule` command suite:

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

The GameRule is registered through `DasikLibrary` in [`BiomeScaleRules.java`](file:///Biome%20Scale%2026.1/src/main/java/net/vanillaoutsider/biomescale/BiomeScaleRules.java):

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
* [[Back to MC 26.1 Portal|pt_br-26.1-Home]]
* [[26.1 Terrain & Noise Scaling|pt_br-26.1-Terrain-and-Noise-Scaling]]
* [[26.1 Architecture & Mixins|pt_br-26.1-Architecture-and-Mixins]]
* [[Master Documentation Home|pt_br-Home]]
