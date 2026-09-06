# Vanilla Outsider: Biome Scale Wiki

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **Repository-Quellcode-Hinweis**: Die Dokumentation in diesem Wiki gibt den **aktuellen Quellcode-Stand im Repository** wieder, der ungesendete Commits oder Entwicklungsfeatures enthalten kann, die den öffentlichen Releases auf CurseForge und Modrinth voraus sind.

---

## 🌍 Willkommen im Dokumentationszentrum für Biome Scale

**Vanilla Outsider: Biome Scale** ist eine leichtgewichtige und mathematisch präzise Weltgenerierungs-Erweiterung für moderne Minecraft-Fabric-Umgebungen. Durch das Abfangen der Multi-Noise-Klimakoordinatenabtastung werden die Grenzen der Biome organisch gestreckt, ohne Vanilla-Rauschformeln zu verändern oder künstliche Biome einzuführen.

Die Mod behebt das bekannte „Obstsalat-Problem“ – bei dem Savanne, Ödland, dichter Wald und schneebedeckte Ebenen alle paar hundert Blöcke aufeinandertreffen – indem Rauschkarten gestreckt werden, um riesige, realistische Kontinente zu formen.

```
+-----------------------------------------------------------------------------------+
|                        VANILLA OUTSIDER: BIOME SCALE                              |
|                                                                                   |
|     Standard-Vanilla (Starke Biom-Zerstückelung)    Skaliert (Riesige Kontinente)         |
|   +----+----+----+----+----+----+----+----+    +----------------------------------+  |
|   | WÜS| EBE| DSU| ÖDL| SCH| WAL| SAV| SUM|    |                                  |  |
|   +----+----+----+----+----+----+----+----+    |                                  |  |
|   | EBE| DSU| WÜS| TAI| WAL| SAV| WÜS| EBE|    |              WÜSTE               |  |
|   +----+----+----+----+----+----+----+----+    |            (x2.0 - x4.0)         |  |
|   | ÖDL| WÜS| SCH| SUM| EBE| WAL| DSU| ÖDL|    |                                  |  |
|   +----+----+----+----+----+----+----+----+    +----------------------------------+  |
+-----------------------------------------------------------------------------------+
```

---

## 🧭 Versionsauswahl-Portal

| Zielversion | Ära | Wartungsstatus | Fabric Loader | Fabric API | Java-Laufzeit | Schnellzugriff |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Minecraft 26.1** | Moderner Standard | 🟢 Aktiv unterstützt | `>=0.19.1` | `0.145.4+26.1.2` | Java 25 | [[👉 MC 26.1 Dokumentation|26.1-Home]] |
| **Minecraft 26.3** | Moderner Snapshot | 🟡 Paritäts-Archiv | `>=0.19.3` | `0.156.1+26.3` | Java 25 | [[👉 MC 26.3 Dokumentation|26.3-Home]] |

---

## ⚡ Zentrale technische Säulen

1. **Reine Koordinatendilatation ($\mathcal{O}(1)$ Laufzeit)**:
   - Modifiziert Koordinaten $x' = \lfloor x / S \rfloor$ und $z' = \lfloor z / S \rfloor$ direkt in `MultiNoiseBiomeSource.getNoiseBiome`.
   - **0 Bytes Heap-Allokation** im Garbage Collector während der Biom-Abtastung.
2. **Universelle dynamische GameRules**:
   - Basiert auf **DasikLibrary** (`DynamicGameRuleManager`) im Namespace `biome_scale:biome_scale`.
   - Passt Skalierungsfaktoren in Echtzeit mit `/gamerule biome_scale:scale_modifier <Wert>` ohne Server-Neustart an.
3. **Automatische Kompatibilität mit Mod-Biomen**:
   - Da die Transformation vor der Biom-Registrierung ansetzt, profitieren Drittanbieter-Mods (Terralith, Biomes O' Plenty) automatisch von der Skalierung.
4. **Erhalt der Voronoi-Zellen**:
   - Streckt das kontinuierliche Klimafeld und garantiert weiche Übergänge an Chunk-Grenzen.

---

## 📚 Leitfäden für Entwickler & Server-Administratoren

* [[Versionskompatibilitäts-Matrix|Version-Compatibility]] — Support-Lebenszyklus, Toolchain und Java-Anforderungen.
* [[Entwickler-Setup und Kompilierung|Developer-Setup-and-Building]] — JDK 25 Einrichtung, Loom-Gradle-Builds und Tests.

---

## ⚖️ Attribution & Lizenz

* **Leitender Architekt & Autor**: **Dasik (Rifaditya)**
* **Lizenz**: **GNU General Public License v3.0 (GPLv3)**
* **Projekt-Repository**: [Vanilla-Outsider-Biome-Scale](https://github.com/Rifaditya/Vanilla-Outsider-Biome-Scale)
