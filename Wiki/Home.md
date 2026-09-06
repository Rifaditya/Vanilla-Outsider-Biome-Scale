# Vanilla Outsider: Biome Scale Wiki

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## 🌍 Welcome to the Biome Scale Documentation Hub

**Vanilla Outsider: Biome Scale** is a lightweight, mathematically elegant world generation enhancement for modern Minecraft Fabric environments. By intercepting the low-level multi-noise climate coordinate samplers during world generation, it rescales biome boundaries organically without altering vanilla noise octave formulas or introducing synthetic biomes.

The mod resolves Minecraft's notorious **"fruit salad" biome clustering**—where small patches of savannah, badlands, dark forest, and snowy plains collide in jarring proximity—by stretching terrain noise maps to form vast, realistic, continental-scale biomes.

```
+-----------------------------------------------------------------------------------+
|                        VANILLA OUTSIDER: BIOME SCALE                              |
|                                                                                   |
|   Standard Vanilla (Frequent Biome Clutter)    Scaled Biome (Vast Immense Continents)  |
|   +----+----+----+----+----+----+----+----+    +----------------------------------+  |
|   | DES| PLA| JGL| BAD| SNW| FOR| SAV| SWA|    |                                  |  |
|   +----+----+----+----+----+----+----+----+    |                                  |  |
|   | PLA| JGL| DES| TAIG|FOR| SAV| DES| PLA|    |           DESERT BIOME           |  |
|   +----+----+----+----+----+----+----+----+    |            (x2.0 - x4.0)         |  |
|   | BAD| DES| SNW| SWA| PLA| FOR| JGL| BAD|    |                                  |  |
|   +----+----+----+----+----+----+----+----+    +----------------------------------+  |
+-----------------------------------------------------------------------------------+
```

---

## 🧭 Multi-Version Selection Portal

Select your targeted Minecraft version below to navigate directly to its dedicated, isolated documentation tree:

| Target Version | Generational Era | Status | Fabric Loader | Fabric API | Java Runtime | Quick Navigation Portal |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Minecraft 26.1** | Modern Standard | 🟢 Active Release | `>=0.19.1` | `0.145.4+26.1.2` | Java 25 | [[👉 Enter MC 26.1 Wiki|26.1-Home]] |
| **Minecraft 26.3** | Modern Lead Snapshot | 🟡 Parity Archive | `>=0.19.3` | `0.156.1+26.3` | Java 25 | [[👉 Enter MC 26.3 Wiki|26.3-Home]] |

---

## ⚡ Core Technical Pillars

1. **Pure Coordinate Dilation ($\mathcal{O}(1)$ Runtime)**:
   - Rescales coordinates $x' = \lfloor x / S \rfloor$ and $z' = \lfloor z / S \rfloor$ directly within `MultiNoiseBiomeSource.getNoiseBiome`.
   - Zero object allocation on the garbage collector heap during biome sampling ticks.
2. **Universal Dynamic GameRules**:
   - Driven by **DasikLibrary**'s `DynamicGameRuleManager` under the `biome_scale:biome_scale` namespaced category.
   - Adjust scale factors in real-time in game without server restarts using `/gamerule biome_scale:scale_modifier <value>`.
3. **Automatic Modded Biome Compatibility**:
   - Because coordinate dilation occurs above individual biome registries, all third-party world generation mods (such as Terralith, Biomes O' Plenty, and custom datapack biomes) automatically inherit scaled proportions without custom compatibility patches.
4. **Voronoi Boundary Integrity**:
   - Scales the continuous underlying multi-noise field (temperature, humidity, continentalness, erosion, depth, weirdness) preserving seamless gradient transitions at chunk borders.

---

## 📚 Universal Developer & Administration Guides

* [[Version Compatibility Matrix|Version-Compatibility]] — Exhaustive support lifecycle, toolchain mappings, and Java requirements.
* [[Developer Setup and Building|Developer-Setup-and-Building]] — Environment configuration, Loom Gradle build steps, testing, and dependency linkage.

---

## ⚖️ Attribution & License

* **Lead Architect & Author**: **Dasik (Rifaditya)**
* **License**: **GNU General Public License v3.0 (GPLv3)**
* **Mod Repository**: [Vanilla-Outsider-Biome-Scale](https://github.com/Rifaditya/Vanilla-Outsider-Biome-Scale)
