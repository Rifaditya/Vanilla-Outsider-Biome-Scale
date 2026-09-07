# MC 26.1: Gelände- & Rausch-Skalierungsmechanik

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **Haftungsausschluss zum Repository-Quellcode**: Die Dokumentation in diesem Wiki spiegelt den **aktuellen Quellcode-Zustand im Repository** wider, der neuere unveröffentlichte Commits oder Entwicklungsfunktionen vor den öffentlichen Builds auf CurseForge und Modrinth enthalten kann.

---

## 📋 Technische Infobox

| Specification | Technical Details |
| :--- | :--- |
| **Feature-Subsystem** | Multi-Noise Koordinaten-Dilatations-Engine |
| **Minecraft-Zielversion** | `26.1.2 (Modern Standard)` |
| **Java-Implementierung** | [`MultiNoiseBiomeSourceMixin.java`](file:///Biome%20Scale%2026.1/src/main/java/net/vanillaoutsider/biomescale/mixin/MultiNoiseBiomeSourceMixin.java) |
| **Engine-Zielklasse** | `net.minecraft.world.level.biome.MultiNoiseBiomeSource` |
| **Zielmethode** | `getNoiseBiome(IIILnet/minecraft/world/level/biome/Climate$Sampler;)Lnet/minecraft/core/Holder;` |
| **Steuernde GameRule** | `biome_scale:scale_modifier` (Standard: `200` = $2.0\times$) |
| **Zeitkomplexität** | $\mathcal{O}(1)$ (primitive Arithmetik in konstanter Zeit) |
| **Heap-Speicherprofil** | **0 Bytes** (null GC-Heap-Allokation pro Abfrage) |
| **Thread-Sicherheit** | Vollständig threadsicher über parallele Chunk-Generierungs-Threads |

---

## 🎮 Spieler-Workflow & Mechanik-Verhalten

Beim Erkunden einer Welt mit **Vanilla Outsider: Biome Scale** arbeitet die Geländegenerierung nahtlos im Hintergrund:

```
[ Chunk-Generierung ausgelöst (MC 26.1) ]
               │
               ▼
[ Sampler fordert Biom bei (X, Y, Z) an ]
               │
               ▼
[ Biome Scale Mixin fängt X und Z ab ]
               │
               ├── Liest cachedScaleModifier aus dem Server-Tick
               └── Transformiert: X' = (int)(X / S), Z' = (int)(Z / S)
               │
               ▼
[ Vanilla-Noise-Sampler bewertet 6D-Klimaparameter bei (X', Y, Z') ]
               │
               ▼
[ Biome Holder zurückgegeben & in Chunk-Abschnitte gemalt ]
```

1. **Chunk-Generierung**:
   - Der Weltgenerator tastet Temperatur, Feuchtigkeit, Kontinentalität, Erosion, Tiefe und Seltsamkeit ab.
2. **Sofortige Koordinatendilatation**:
   - X- und Z-Koordinaten werden durch den aktiven Skalierungsmodifikator geteilt.
   - Punkte, die ursprünglich 2.000 Blöcke voneinander entfernt waren, werden so bewertet, als lägen sie nur 1.000 Blöcke auseinander (bei $2.0\times$).
3. **Erkundungserlebnis**:
   - Gewaltige Wüsten, durchgehende Gebirgszüge und monumentale Ozeane statt ständiger Flickenteppich-Biome.
4. **Dynamische Spielanpassung**:
   - Die Anpassung von `/gamerule biome_scale:scale_modifier` betrifft **nur neu generierte Chunks**.

---

## 📐 Mathematical Formulation & Voronoi Geometry

### 1. GameRule Scaling Multiplier Formula
The integer value stored in `biome_scale:scale_modifier` represents a percentage scale factor $S$:

$$S = \frac{V_{\text{gamerule}}}{100.0}$$

Where:
* $V_{\text{gamerule}} = 200 \implies S = 2.0$ ($2.0\times$ vanilla diameter)
* $V_{\text{gamerule}} = 400 \implies S = 4.0$ ($4.0\times$ continental scale)
* $V_{\text{gamerule}} = 50 \implies S = 0.5$ ($0.5\times$ micro-biome scale)

Lower bound protection:

$$S_{\text{eff}} = \max(S, 0.01)$$

### 2. Coordinate Transformation Equations
Input coordinates $(x, y, z)$ entering `MultiNoiseBiomeSource.getNoiseBiome` map to dilated coordinates $(x', y', z')$:

$$x' = \begin{cases} x & \text{if } S_{\text{eff}} \le 0.01 \\ \left\lfloor \frac{x}{S_h} \right\rfloor & \text{otherwise} \end{cases}$$

$$z' = \begin{cases} z & \text{if } S_{\text{eff}} \le 0.01 \\ \left\lfloor \frac{z}{S_h} \right\rfloor & \text{otherwise} \end{cases}$$

$$y' = \begin{cases} y & \text{if } S_v \le 0.01 \\ \left\lfloor \frac{y}{S_v} \right\rfloor & \text{otherwise} \end{cases}$$

*(In MC 26.1, $S_h = S_{\text{eff}}$ and $S_v = 1.0$, preserving vertical 3D cave/mountain climate bands while dilating horizontal expanse).*

### 3. Voronoi Cell Preservation
$$C(x', y', z') = \big\langle T(x',y',z'),\, H(x',y',z'),\, C_{\text{cont}}(x',y',z'),\, E(x',y',z'),\, D(x',y',z'),\, W(x',y',z') \big\rangle$$

$$\text{Selected Biome} = \arg\min_{k} \left\| C(x', y', z') - B_k \right\|_2$$

```
       [ VANILLA 26.1 (S = 1.0) ]                 [ SCALED 26.1 (S = 2.0) ]
       
   (X=0)              (X=1000)             (X=0)                                (X=2000)
     +--------------------+                  +------------------------------------+
     | PLAINS   | DESERT  |                  |               PLAINS               |
     | [500 blk]| [500 blk|                  |             [1000 blk]             |
     +--------------------+                  +------------------------------------+
```

---

## 📊 Scale Multiplier Reference Table

| GameRule Value | Effective Scale ($S$) | Relative Biome Area ($S^2$) | Average Biome Diameter | Recommended Playstyle |
| :--- | :--- | :--- | :--- | :--- |
| `25` | $0.25\times$ | $0.0625\times$ | ~150 blocks | Fast mini-game survival, compact challenges |
| `50` | $0.50\times$ | $0.25\times$ | ~300 blocks | Accelerated variety exploration |
| `100` | $1.00\times$ | $1.00\times$ | ~600 blocks | Pure vanilla Minecraft generation |
| **`200` (Default)** | **$2.00\times$** | **$4.00\times$** | **~1,200 blocks** | **Standard Vanilla Outsider Immersion** |
| `300` | $3.00\times$ | $9.00\times$ | ~1,800 blocks | Expansive realistic wilderness |
| `400` | $4.00\times$ | $16.00\times$ | ~2,400 blocks | Continental exploration & massive rail networks |
| `1000` | $10.00\times$ | $100.00\times$ | ~6,000 blocks | Mega-scale simulation & survival roleplay servers |

---

## 💾 Codec & Dynamic State Schema

```json
{
  "gamerule": {
    "namespace": "biome_scale",
    "path": "scale_modifier",
    "type": "integer",
    "default": 200,
    "current": 200,
    "effective_multiplier": 2.0,
    "bounds": {
      "min_safe": 1,
      "max_safe": 2147483647
    }
  }
}
```

---

## 💻 Developer & Mixin Implementation Hooks

The core transformation is executed in [`MultiNoiseBiomeSourceMixin`](file:///Biome%20Scale%2026.1/src/main/java/net/vanillaoutsider/biomescale/mixin/MultiNoiseBiomeSourceMixin.java):

```java
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

---

## 🧭 Version Navigation
* [[Back to MC 26.1 Portal|de_de-26.1-Home]]
* [[26.1 Configuration & GameRules|de_de-26.1-Configuration-and-GameRules]]
* [[26.1 Architecture & Mixins|de_de-26.1-Architecture-and-Mixins]]
* [[Master Documentation Home|de_de-Home]]
