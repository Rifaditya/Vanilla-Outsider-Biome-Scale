# MC 26.3: 地形与噪声缩放机制

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **仓库源码免责声明**：本 Wiki 中的文档反映了**仓库中的当前源代码状态**，可能包含领先于 CurseForge 和 Modrinth 上公开版本的近期未发布提交或开发特性。

---

## 📋 技术信息框

| Specification | Technical Details |
| :--- | :--- |
| **功能子系统** | 多噪声坐标膨胀引擎 (Multi-Noise Coordinate Dilation Engine) |
| **目标 Minecraft 版本** | `26.3-snapshot-6 (Modern Lead)` |
| **Java 实现** | [`MultiNoiseBiomeSourceMixin.java`](file:///Biome%20Scale%20v26.3/Biome%20Scale%2026.3/src/main/java/net/vanillaoutsider/biomescale/mixin/MultiNoiseBiomeSourceMixin.java) |
| **目标引擎类** | `net.minecraft.world.level.biome.MultiNoiseBiomeSource` |
| **目标方法** | `getNoiseBiome(IIILnet/minecraft/world/level/biome/Climate$Sampler;)Lnet/minecraft/core/Holder;` |
| **控制游戏规则** | `biome_scale:scale_modifier` (默认: `200` = $2.0\times$) |
| **时间复杂度** | $\mathcal{O}(1)$ (常数时间基本类型算术运算) |
| **堆内存开销** | **0 字节** (每次采样零 GC 堆内存分配) |
| **线程安全性** | 在并行区块生成线程之间完全线程安全 |

---

## 🎮 玩家工作流与机制行为

在生成了 **Vanilla Outsider: Biome Scale** 的世界中探索时，地形生成在底层平滑运作：

```
[ 触发区块生成 (MC 26.3) ]
               │
               ▼
[ 采样器请求位于 (X, Y, Z) 的生物群系 ]
               │
               ▼
[ Biome Scale Mixin 拦截 X 与 Z ]
               │
               ├── 从服务端 Tick 中读取 cachedScaleModifier
               └── 转换计算: X' = (int)(X / S), Z' = (int)(Z / S)
               │
               ▼
[ 原版噪声采样器在 (X', Y, Z') 处评估 6 维气候参数 ]
               │
               ▼
[ 返回 Biome Holder 并绘制入区块子段 ]
```

1. **区块生成**：
   - 世界生成器采样温度、湿度、大陆性、侵蚀度、深度和奇特度。
2. **即时坐标膨胀**：
   - 坐标进入生物群系采样器时，X 和 Z 坐标除以当前启用的规模因子。
   - 原本相距 2,000 方块的两点在 $2.0\times$ 缩放下被当成仅相距 1,000 方块来评估，使得生态群系在物理距离上扩展为两倍。
3. **探索体验**：
   - 玩家穿行在连绵数千格的广袤沙漠、雄伟山脉与无垠大洋之中，告别原版几百格就突兀切换的拼凑碎片化群系。
4. **游戏内动态变更**：
   - 在游戏中调整 `/gamerule biome_scale:scale_modifier` **仅影响新生成的区块**。已生成的区块将保持完全完整。

---

## 📐 数学公式与沃罗诺伊多边形几何学

### 1. 游戏规则缩放乘数公式
存储在 `biome_scale:scale_modifier` 中的整数值表示百分比缩放因子 $S$：

$$S = \frac{V_{\text{gamerule}}}{100.0}$$

其中：
* $V_{\text{gamerule}} = 200 \implies S = 2.0$ ($2.0\times$ 原版直径)
* $V_{\text{gamerule}} = 400 \implies S = 4.0$ ($4.0\times$ 大陆级规模)
* $V_{\text{gamerule}} = 50 \implies S = 0.5$ ($0.5\times$ 微型生态群系规模)

为防止除零崩溃或负坐标反转，系统强制施加了下限保护：

$$S_{\text{eff}} = \max(S, 0.01)$$

### 2. 坐标转换方程式
进入 `MultiNoiseBiomeSource.getNoiseBiome` 的输入坐标 $(x, y, z)$ 被映射到膨胀采样坐标 $(x', y', z')$：

$$x' = \begin{cases} x & \text{if } S_{\text{eff}} \le 0.01 \\ \left\lfloor \frac{x}{S_h} \right\rfloor & \text{otherwise} \end{cases}$$

$$z' = \begin{cases} z & \text{if } S_{\text{eff}} \le 0.01 \\ \left\lfloor \frac{z}{S_h} \right\rfloor & \text{otherwise} \end{cases}$$

$$y' = \begin{cases} y & \text{if } S_v \le 0.01 \\ \left\lfloor \frac{y}{S_v} \right\rfloor & \text{otherwise} \end{cases}$$

*(在当前 MC 26.3 的实现中，$S_h = S_{\text{eff}}$ 且 $S_v = 1.0$，从而在水平扩大群系的同时，完整保留垂直三维洞穴与山脉的气候带分层)。*

### 3. 沃罗诺伊多边形与气候向量保持
Minecraft 通过在 6 维气候空间中寻找采样气候向量 $C(x', y', z')$ 与预注册生态群系目标点 $B_k$ 之间的最小欧氏距离来确定生物群系：

$$C(x', y', z') = \big\langle T(x',y',z'),\, H(x',y',z'),\, C_{\text{cont}}(x',y',z'),\, E(x',y',z'),\, D(x',y',z'),\, W(x',y',z') \big\rangle$$

$$\text{Selected Biome} = \arg\min_{k} \left\| C(x', y', z') - B_k \right\|_2$$

由于坐标映射 $(x, z) \mapsto (x/S, z/S)$ 具备连续性与单调性：
* **多边形拓扑完整保留**：沃罗诺伊单元边界保留自然的有机弧度，绝无断裂撕裂。
* **倍频程梯度平滑扩张**：噪声梯度 $\nabla N(x', z')$ 按 $\frac{1}{S}$ 平滑缩放，带来极其宏伟渐进的气候过渡。

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
* [[Back to MC 26.3 Portal|zh_cn-26.3-Home]]
* [[26.3 Configuration & GameRules|zh_cn-26.3-Configuration-and-GameRules]]
* [[26.3 Architecture & Mixins|zh_cn-26.3-Architecture-and-Mixins]]
* [[Master Documentation Home|zh_cn-Home]]
