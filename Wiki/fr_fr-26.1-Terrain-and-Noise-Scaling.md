# MC 26.1: Mécaniques d'échelle du terrain et du bruit

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **Avis de non-responsabilité concernant le code source du dépôt** : La documentation de ce Wiki reflète **l'état actuel du code source dans le dépôt**, qui peut inclure des commits récents non publiés ou des fonctionnalités en cours de développement en avance sur les versions publiques sur CurseForge et Modrinth.

---

## 📋 Infobox technique

| Specification | Technical Details |
| :--- | :--- |
| **Sous-système** | Moteur de dilatation des coordonnées Multi-Noise |
| **Version cible de Minecraft** | `26.1.2 (Modern Standard)` |
| **Implémentation Java** | [`MultiNoiseBiomeSourceMixin.java`](file:///Biome%20Scale%2026.1/src/main/java/net/vanillaoutsider/biomescale/mixin/MultiNoiseBiomeSourceMixin.java) |
| **Classe moteur cible** | `net.minecraft.world.level.biome.MultiNoiseBiomeSource` |
| **Méthode cible** | `getNoiseBiome(IIILnet/minecraft/world/level/biome/Climate$Sampler;)Lnet/minecraft/core/Holder;` |
| **GameRule de contrôle** | `biome_scale:scale_modifier` (Défaut : `200` = $2.0\times$) |
| **Complexité temporelle** | $\mathcal{O}(1)$ (arithmétique primitive en temps constant) |
| **Profil de mémoire heap** | **0 octet** (zéro allocation GC par échantillon) |
| **Sécurité des threads** | Totalement thread-safe entre les threads parallèles de génération |

---

## 🎮 Expérience joueur et comportement

Lors de l'exploration d'un monde avec **Vanilla Outsider: Biome Scale**, la génération de terrain opère en toute transparence :

```
[ Déclenchement de génération de chunk (MC 26.1) ]
               │
               ▼
[ Le sampler requiert le biome en (X, Y, Z) ]
               │
               ▼
[ Biome Scale Mixin intercepte X et Z ]
               │
               ├── Lit cachedScaleModifier depuis le tick serveur
               └── Transformation : X' = (int)(X / S), Z' = (int)(Z / S)
               │
               ▼
[ Le sampler de bruit évalue les 6 paramètres climatiques en (X', Y, Z') ]
               │
               ▼
[ Biome Holder retourné et peint dans les sections du chunk ]
```

1. **Génération de chunks** :
   - Échantillonnage de la température, humidité, continentalité, érosion, profondeur et bizarrerie.
2. **Dilatation instantanée des coordonnées** :
   - Les coordonnées X et Z sont divisées par le modificateur d'échelle actif.
   - Des points distants de 2 000 blocs sont évalués comme distants de 1 000 blocs (avec une échelle de $2.0\times$).
3. **Expérience d'exploration** :
   - Déserts gigantesques, chaînes de montagnes ininterrompues et océans colossaux.
4. **Modifications en cours de jeu** :
   - Modifier `/gamerule biome_scale:scale_modifier` n'affecte **que les nouveaux chunks**.

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
* [[Back to MC 26.1 Portal|fr_fr-26.1-Home]]
* [[26.1 Configuration & GameRules|fr_fr-26.1-Configuration-and-GameRules]]
* [[26.1 Architecture & Mixins|fr_fr-26.1-Architecture-and-Mixins]]
* [[Master Documentation Home|fr_fr-Home]]
