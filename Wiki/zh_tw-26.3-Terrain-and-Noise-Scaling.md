# MC 26.3: 地形與雜訊縮放機制

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **存放庫原始碼免責聲明**：本 Wiki 中的文件反映了**存放庫中的當前原始碼狀態**，可能包含領先於 CurseForge 與 Modrinth 上公開版本的近期未發布提交或開發特性。

---

## 📋 技術資訊框

| Specification | Technical Details |
| :--- | :--- |
| **功能子系統** | 多雜訊座標膨脹引擎 (Multi-Noise Coordinate Dilation Engine) |
| **目標 Minecraft 版本** | `26.3-snapshot-6 (Modern Lead)` |
| **Java 實作** | [`MultiNoiseBiomeSourceMixin.java`](file:///Biome%20Scale%20v26.3/Biome%20Scale%2026.3/src/main/java/net/vanillaoutsider/biomescale/mixin/MultiNoiseBiomeSourceMixin.java) |
| **目標引擎類別** | `net.minecraft.world.level.biome.MultiNoiseBiomeSource` |
| **目標方法** | `getNoiseBiome(IIILnet/minecraft/world/level/biome/Climate$Sampler;)Lnet/minecraft/core/Holder;` |
| **控制遊戲規則** | `biome_scale:scale_modifier` (預設: `200` = $2.0\times$) |
| **時間複雜度** | $\mathcal{O}(1)$ (常數時間基本型別算術運算) |
| **堆積記憶體開銷** | **0 位元組** (每次採樣零 GC 堆積記憶體分配) |
| **執行緒安全性** | 在平行區塊生成執行緒之間完全執行緒安全 |

---

## 🎮 玩家工作流程與機制行為

在生成了 **Vanilla Outsider: Biome Scale** 的世界中探索時，地形生成在底層平滑運作：

```
[ 觸發區塊生成 (MC 26.3) ]
               │
               ▼
[ 採樣器請求位於 (X, Y, Z) 的生態域 ]
               │
               ▼
[ Biome Scale Mixin 攔截 X 與 Z ]
               │
               ├── 從伺服端 Tick 中讀取 cachedScaleModifier
               └── 轉換計算: X' = (int)(X / S), Z' = (int)(Z / S)
               │
               ▼
[ 原版雜訊採樣器在 (X', Y, Z') 處評估 6 維氣候參數 ]
               │
               ▼
[ 返回 Biome Holder 並繪製入區塊子段 ]
```

1. **區塊生成**：
   - 世界生成器採樣溫度、濕度、大陸性、侵蝕度、深度與奇特度。
2. **即時座標膨脹**：
   - 座標進入生態域採樣器時，X 與 Z 座標除以當前啟用的規模因子。
   - 原本相距 2,000 方塊的兩點在 $2.0\times$ 縮放下被當成僅相距 1,000 方塊來評估，使得生態域在物理距離上擴展為兩倍。
3. **探索體驗**：
   - 玩家穿行在連綿數千格的廣袤沙漠、雄偉山脈與無垠大洋之中，告別原版幾百格就突兀切換的拼湊碎片化生態域。
4. **遊戲內動態變更**：
   - 在遊戲中調整 `/gamerule biome_scale:scale_modifier` **僅影響新生成的區塊**。已生成的區塊將保持完全完整。

---

## 📐 數學公式與沃羅諾伊多邊形幾何學

### 1. 遊戲規則縮放乘數公式
儲存在 `biome_scale:scale_modifier` 中的整數值表示百分比縮放因子 $S$：

$$S = \frac{V_{\text{gamerule}}}{100.0}$$

其中：
* $V_{\text{gamerule}} = 200 \implies S = 2.0$ ($2.0\times$ 原版直徑)
* $V_{\text{gamerule}} = 400 \implies S = 4.0$ ($4.0\times$ 大陸級規模)
* $V_{\text{gamerule}} = 50 \implies S = 0.5$ ($0.5\times$ 微型生態域規模)

為防止除零崩潰或負座標反轉，系統強制施加了下限保護：

$$S_{\text{eff}} = \max(S, 0.01)$$

### 2. 座標轉換方程式
進入 `MultiNoiseBiomeSource.getNoiseBiome` 的輸入座標 $(x, y, z)$ 被映射到膨脹採樣座標 $(x', y', z')$：

$$x' = \begin{cases} x & \text{if } S_{\text{eff}} \le 0.01 \\ \left\lfloor \frac{x}{S_h} \right\rfloor & \text{otherwise} \end{cases}$$

$$z' = \begin{cases} z & \text{if } S_{\text{eff}} \le 0.01 \\ \left\lfloor \frac{z}{S_h} \right\rfloor & \text{otherwise} \end{cases}$$

$$y' = \begin{cases} y & \text{if } S_v \le 0.01 \\ \left\lfloor \frac{y}{S_v} \right\rfloor & \text{otherwise} \end{cases}$$

*(在當前 MC 26.3 的實作中，$S_h = S_{\text{eff}}$ 且 $S_v = 1.0$，從而在水平擴大生態域的同時，完整保留垂直三維洞穴與山脈的氣候帶分層)。*

### 3. 沃羅諾伊多邊形與氣候向量保持
Minecraft 透過在 6 維氣候空間中尋找採樣氣候向量 $C(x', y', z')$ 與預註冊生態域目標點 $B_k$ 之間的最小歐氏距離來確定生態域：

$$C(x', y', z') = \big\langle T(x',y',z'),\, H(x',y',z'),\, C_{\text{cont}}(x',y',z'),\, E(x',y',z'),\, D(x',y',z'),\, W(x',y',z') \big\rangle$$

$$\text{Selected Biome} = \arg\min_{k} \left\| C(x', y', z') - B_k \right\|_2$$

由於座標映射 $(x, z) \mapsto (x/S, z/S)$ 具備連續性與單調性：
* **多邊形拓撲完整保留**：沃羅諾伊單元邊界保留自然的有機弧度，絕無斷裂撕裂。
* **倍頻程梯度平滑擴張**：雜訊梯度 $\nabla N(x', z')$ 按 $\frac{1}{S}$ 平滑縮放，帶來極其宏偉漸進的氣候過渡。

```
       [ VANILLA 26.3 (S = 1.0) ]                 [ SCALED 26.3 (S = 2.0) ]
       
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

The core transformation is executed in [`MultiNoiseBiomeSourceMixin`](file:///Biome%20Scale%20v26.3/Biome%20Scale%2026.3/src/main/java/net/vanillaoutsider/biomescale/mixin/MultiNoiseBiomeSourceMixin.java):

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
* [[Back to MC 26.3 Portal|zh_tw-26.3-Home]]
* [[26.3 Configuration & GameRules|zh_tw-26.3-Configuration-and-GameRules]]
* [[26.3 Architecture & Mixins|zh_tw-26.3-Architecture-and-Mixins]]
* [[Master Documentation Home|zh_tw-Home]]
