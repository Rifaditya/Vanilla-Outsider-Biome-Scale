# Versionskompatibilität & Lebenszyklus-Matrix

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **Haftungsausschluss zum Repository-Quellcode**: Die Dokumentation in diesem Wiki spiegelt den **aktuellen Quellcode-Zustand im Repository** wider, der neuere unveröffentlichte Commits oder Entwicklungsfunktionen vor den öffentlichen Builds auf CurseForge und Modrinth enthalten kann.

---

## 📊 Minecraft-Versionskompatibilitätsmatrix

**Vanilla Outsider: Biome Scale** wird über moderne Minecraft-Releases hinweg unter der strikten **1-Jar-1-Version**-Richtlinie gepflegt.

| Minecraft-Version | Generations-Ära | Build-Meilenstein | Fabric Loader | Fabric API | Java-Laufzeit | DasikLibrary-Version | Support-Status |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **26.1.2** | Moderner Standard | `1.0.0+26.1` | `>=0.19.1` | `0.145.4+26.1.2` | Java 25 | `1.6.9+build.24` | 🟢 **Aktiver Langzeit-Support (LTS)** |
| **26.3-snapshot-6** | Moderner Vorreiter | `1.0.0+26.3` | `>=0.19.3` | `0.156.1+26.3` | Java 25 | `1.8.36` | 🟡 **Paritäts-Archiv-Release** |

---

## 🌐 Umgebung & Side-Safety-Verteilung

| Umgebung | Unterstützt | Anforderung & Verhalten |
| :--- | :--- | :--- |
| **Dedizierter Server** | ✅ **Volle Unterstützung** | **Erforderlich für Weltgenerierung.** Die Neuskalierung der Chunk-Koordinaten läuft serverseitig während der Welt- und Chunk-Erstellung. |
| **Einzelspieler (Integrierter Server)** | ✅ **Volle Unterstützung** | Läuft auf dem internen integrierten Server-Thread; vollständige Echtzeit-GameRule-Steuerung über Chat `/gamerule`. |
| **Vanilla-Client verbindet sich** | ✅ **Vanilla-Client kompatibel** | **Unmodifizierte Vanilla-Clients können beitreten, ohne den Mod zu installieren!** Biom-Daten werden in Standard-Paket-Paletten übertragen. |

---

## 🧩 Kompatibilität mit Weltgenerierungs- & Biom-Mods von Drittanbietern

Da Vanilla Outsider: Biome Scale mathematisch auf der Koordinaten-Sampling-Ebene (`MultiNoiseBiomeSource`) agiert, *bevor* die Biom-Suche erfolgt, bietet es native Kompatibilität ohne Overhead mit externen Weltgenerierungs-Mods:

```
[ Chunk Generator Pipeline ]
           │
           ▼
[ Vanilla Outsider: Biome Scale ]  <--- (Intercepts and dilates sampling coordinates: x' = x / S, z' = z / S)
           │
           ▼
[ Biome Source Registry ]
     ├── Vanilla Biomes (Plains, Desert, Jagged Peaks, etc.)
     ├── Terralith Biomes (Caldera, Mirage Desert, etc.)
     ├── Biomes O' Plenty (Cherry Blossom Grove, Outback, etc.)
     └── Datapack Custom Biomes
```

### Verifizierte kompatible Weltgenerierungs-Addons
1. **Terralith**: Erweitert nahtlos benutzerdefinierte Makrobiome und bewahrt gleichzeitig Vanilla-Höhlenrouten.
2. **Biomes O' Plenty**: Skaliert benutzerdefinierte Oberflächenbiome korrekt ohne ID-Konflikte.
3. **Regions Unexplored**: Kompatibel über alle benutzerdefinierten Regionsparameter hinweg.
4. **Benutzerdefinierte Datapacks**: Jedes Datapack, das Vanilla `minecraft:multi_noise`-Quellen nutzt, wird automatisch skaliert.

---

## 🛠️ Java Runtime Environment (JRE) Anforderungen

Sowohl Minecraft 26.1- als auch 26.3-Build-Artefakte erfordern **Java 25 (LTS)**. Der Versuch, sie unter Java 21 oder älter auszuführen, löst eine sofortige Bytecode-Inkompatibilitätswarnung des JVM-Classloaders aus:

```
UnsupportedClassVersionError: net/vanillaoutsider/biomescale/BiomeScaleFabric has been compiled 
by a more recent version of the Java Runtime (class file version 69.0)
```

```bash
# Verify your installed Java version
java -version
# Expected: openjdk version "25" (or higher)
```

---

## 🧭 Navigation
* [[Zurück zum Hauptportal|de_de-Home]]
* [[Entwickler-Setup & Build-Anleitung|de_de-Developer-Setup-and-Building]]
* [[MC 26.1 Übersicht|de_de-26.1-Home]]
* [[MC 26.3 Übersicht|de_de-26.3-Home]]
