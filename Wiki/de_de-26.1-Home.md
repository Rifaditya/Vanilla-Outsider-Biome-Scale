# Minecraft 26.1 — Biome Scale Dokumentationsportal

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **Haftungsausschluss zum Repository-Quellcode**: Die Dokumentation in diesem Wiki spiegelt den **aktuellen Quellcode-Zustand im Repository** wider, der neuere unveröffentlichte Commits oder Entwicklungsfunktionen vor den öffentlichen Builds auf CurseForge und Modrinth enthalten kann.

---

## 🌲 Übersicht: Biome Scale für Minecraft 26.1

Willkommen zur Dokumentation von **Vanilla Outsider: Biome Scale** für **Minecraft 26.1**.

In Minecraft 26.1 stützt sich die Weltgenerierung auf den `MultiNoiseBiomeSource`-Klimasampler im 6-dimensionalen Parameterraum. Biome Scale fängt diese Koordinaten dynamisch ab und dehnt den horizontalen Rausch-Samplingraum um einen konfigurierbaren Multiplikator.

```
                  ┌─────────────────────────────────────┐
                  │    Minecraft 26.1 Chunk Engine     │
                  └──────────────────┬──────────────────┘
                                     │
                                     ▼
                ┌─────────────────────────────────────────┐
                │ MultiNoiseBiomeSourceMixin (MC 26.1)    │
                │        x' = x / S , z' = z / S          │
                └────────────────────┬────────────────────┘
                                     │
                                     ▼
                ┌─────────────────────────────────────────┐
                │   Climate$Sampler Evaluation (Vanilla)   │
                │ Continuous Voronoi Cell Macro Continents │
                └─────────────────────────────────────────┘
```

---

## 📑 26.1-Dokumentations-Subsystemmatrix

| Subsystem-Seite | Zielgruppe | Behandelte Kernthemen |
| :--- | :--- | :--- |
| **[[Gelände- & Rausch-Skalierung|de_de-26.1-Terrain-and-Noise-Scaling]]** | 🎮 Spieler & Entwickler | Koordinatendilatations-Gleichungen, Voronoi-Zell-Erhaltung, null GC-Heap-Allokation. |
| **[[Konfiguration & Regeln|de_de-26.1-Configuration-and-GameRules]]** | 🎮 Spieler & Server-Admins | `biome_scale:scale_modifier`, dynamisches Tick-Caching, `/gamerule`-Befehle. |
| **[[Architektur & Mixins|de_de-26.1-Architecture-and-Mixins]]** | 💻 Entwickler & Modder | Mixin-Bytecode-Analyse, Injektionspunkte, DasikLibrary-Integration, Zero-Overhead. |

---

## 🚀 Schnellstartanleitung für Server-Administratoren

1. **Abhängigkeiten installieren**:
   - Stellen Sie sicher, dass Ihr Server **Fabric Loader** und **Fabric API** für 26.1 besitzt.
   - Installieren Sie **DasikLibrary**.
2. **JAR einfügen**:
   - Platzieren Sie `vanilla-outsider-biome-scale-1.0.0+26.1.jar` im `mods/`-Ordner des Servers.
3. **Biomgrößen konfigurieren**:
   - Der Standard-Skalierungsmodifikator ist **`200`** (entspricht einer $2.0\times$-Vergrößerung):
     ```text
     /gamerule biome_scale:scale_modifier 200
     ```
   - Für gewaltige kontinentale Welten ($4.0\times$-Vergrößerung):
     ```text
     /gamerule biome_scale:scale_modifier 400
     ```
   - Für schnelle Mini-Biome ($0.5\times$-Größe):
     ```text
     /gamerule biome_scale:scale_modifier 50
     ```

---

## 🧭 Versions-Navigation
* [[Zurück zum Hauptportal|de_de-Home]]
* [[Versionskompatibilitätsmatrix|de_de-Version-Compatibility]]
* [[Entwickler-Setup & Build-Anleitung|de_de-Developer-Setup-and-Building]]
* [[Zu MC 26.3 Wiki wechseln|de_de-26.3-Home]]
