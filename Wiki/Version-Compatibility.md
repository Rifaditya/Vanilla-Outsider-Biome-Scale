# Version Compatibility & Lifecycle Matrix

> [!IMPORTANT]
> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## 📊 Minecraft Version Compatibility Matrix

**Vanilla Outsider: Biome Scale** is maintained in lockstep across modern Minecraft releases under the strict **1 Jar 1 Version** engineering policy.

| Minecraft Version | Generational Era | Build Milestone | Fabric Loader | Fabric API | Java Runtime | DasikLibrary Version | Support Status |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **26.1.2** | Modern Standard | `1.0.0+26.1` | `>=0.19.1` | `0.145.4+26.1.2` | Java 25 | `1.6.9+build.24` | 🟢 **Active Long-Term Support** |
| **26.3-snapshot-6** | Modern Lead | `1.0.0+26.3` | `>=0.19.3` | `0.156.1+26.3` | Java 25 | `1.8.36` | 🟡 **Parity Release Archive** |

---

## 🌐 Environment & Side-Safety Distribution

| Environment | Supported | Requirement & Behavior |
| :--- | :--- | :--- |
| **Dedicated Server** | ✅ **Full Support** | **Required for world generation.** All chunk coordinate rescaling runs server-side during world creation and chunk generation. |
| **Singleplayer (Integrated Server)** | ✅ **Full Support** | Runs on the internal integrated server thread; full real-time GameRule control via chat `/gamerule`. |
| **Vanilla Client Joining Server** | ✅ **Vanilla Client Compatible** | **Unmodified vanilla clients can join without installing the mod!** Biome data is encoded in standard vanilla chunk packet palettes sent to the client. |

---

## 🧩 Third-Party Worldgen & Biome Mod Compatibility

Because Vanilla Outsider: Biome Scale operates mathematically at the coordinate sampling tier (`MultiNoiseBiomeSource`) *before* biome lookup occurs, it features zero-overhead native compatibility with external worldgen mods:

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

### Verified Compatible Worldgen Addons
1. **Terralith**: Seamlessly expands custom macro-biomes while preserving vanilla cave routing.
2. **Biomes O' Plenty**: Correctly scales custom surface biomes with zero ID conflicts.
3. **Regions Unexplored**: Compatible across all custom region parameters.
4. **Custom Worldgen Datapacks**: Any datapack using vanilla `minecraft:multi_noise` biome sources is scaled automatically.

---

## 🛠️ Java Runtime Environment (JRE) Requirement

Both Minecraft 26.1 and 26.3 build artifacts require **Java 25 (LTS)**. Attempting to run on Java 21 or older will trigger an immediate JVM classloader bytecode incompatibility warning:

```
UnsupportedClassVersionError: net/vanillaoutsider/biomescale/BiomeScaleFabric has been compiled 
by a more recent version of the Java Runtime (class file version 69.0)
```

Ensure your launcher profile or dedicated server startup script points to a valid Java 25 binary:
```bash
# Verify your installed Java version
java -version
# Expected: openjdk version "25" (or higher)
```

---

## 🧭 Navigation
* [[Back to Master Portal|Home]]
* [[Developer Setup and Building|Developer-Setup-and-Building]]
* [[MC 26.1 Overview|26.1-Home]]
* [[MC 26.3 Overview|26.3-Home]]
