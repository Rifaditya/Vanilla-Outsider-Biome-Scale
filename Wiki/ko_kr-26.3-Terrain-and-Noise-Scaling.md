# MC 26.3: 지형 및 노이즈 스케일링 메커니즘

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **저장소 소스 코드 면책 조항**: 이 위키의 문서는 CurseForge 및 Modrinth의 공개 릴리스 빌드보다 앞선 최근 미출시 커밋 또는 개발 기능을 포함할 수 있는 **저장소의 현재 소스 코드 상태**를 반영합니다.

---

## 📋 기술 정보 상자

| Specification | Technical Details |
| :--- | :--- |
| **기능 서브시스템** | 다중 노이즈 좌표 확장 엔진 (Multi-Noise Coordinate Dilation Engine) |
| **대상 마인크래프트 버전** | `26.3-snapshot-6 (Modern Lead)` |
| **Java 구현체** | [`MultiNoiseBiomeSourceMixin.java`](file:///Biome%20Scale%20v26.3/Biome%20Scale%2026.3/src/main/java/net/vanillaoutsider/biomescale/mixin/MultiNoiseBiomeSourceMixin.java) |
| **대상 엔진 클래스** | `net.minecraft.world.level.biome.MultiNoiseBiomeSource` |
| **대상 메서드** | `getNoiseBiome(IIILnet/minecraft/world/level/biome/Climate$Sampler;)Lnet/minecraft/core/Holder;` |
| **제어 게임 규칙** | `biome_scale:scale_modifier` (기본값: `200` = $2.0\times$) |
| **시간 복잡도** | $\mathcal{O}(1)$ (상수 시간 원시 연산) |
| **힙 메모리 사용량** | **0 바이트** (샘플당 가비지 컬렉션 할당 제로) |
| **스레드 안전성** | 병렬 청크 생성 스레드 간 완벽한 스레드 안전성 보장 |

---

## 🎮 플레이어 워크플로 및 메커니즘 동작

**Vanilla Outsider: Biome Scale**이 적용된 월드를 탐험할 때 지형 생성은 내부에서 매끄럽게 이루어집니다:

```
[ 청크 생성 트리거 (MC 26.3) ]
               │
               ▼
[ 샘플러가 (X, Y, Z) 좌표의 바이옴을 요청 ]
               │
               ▼
[ Biome Scale Mixin이 X 및 Z 좌표를 가로챔 ]
               │
               ├── 서버 틱에서 cachedScaleModifier 읽기
               └── 변환 계산: X' = (int)(X / S), Z' = (int)(Z / S)
               │
               ▼
[ 바닐라 노이즈 샘플러가 (X', Y, Z')에서 6D 기후 파라미터 평가 ]
               │
               ▼
[ Biome Holder 반환 및 청크 섹션에 페인팅 ]
```

1. **청크 생성**:
   - 월드 생성기가 온도, 습도, 대륙성, 침식도, 깊이, 기이성 좌표를 샘플링.
2. **즉각적인 좌표 확장**:
   - 바이옴 샘플러로 들어가는 X 및 Z 좌표가 활성 스케일 배율로 나뉩니다.
   - 원래 2,000블록 떨어져 있던 두 지점이 $2.0\times$ 스케일에서는 마치 1,000블록만 떨어져 있는 것처럼 평가됩니다.
3. **탐험의 몰입감**:
   - 수백 블록마다 어지럽게 뒤바뀌던 미니 바이옴 대신 거대한 사막, 끝없는 산맥, 광활한 대양을 탐험할 수 있습니다.
4. **인게임 실시간 변경**:
   - 게임 도중 `/gamerule biome_scale:scale_modifier`를 변경하면 **새로 생성되는 청크에만 즉시 적용**됩니다.

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

*(In MC 26.3, $S_h = S_{\text{eff}}$ and $S_v = 1.0$, preserving vertical 3D cave/mountain climate bands while dilating horizontal expanse).*

### 3. Voronoi Cell Preservation
$$C(x', y', z') = \big\langle T(x',y',z'),\, H(x',y',z'),\, C_{\text{cont}}(x',y',z'),\, E(x',y',z'),\, D(x',y',z'),\, W(x',y',z') \big\rangle$$

$$\text{Selected Biome} = \arg\min_{k} \left\| C(x', y', z') - B_k \right\|_2$$

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
* [[Back to MC 26.3 Portal|ko_kr-26.3-Home]]
* [[26.3 Configuration & GameRules|ko_kr-26.3-Configuration-and-GameRules]]
* [[26.3 Architecture & Mixins|ko_kr-26.3-Architecture-and-Mixins]]
* [[Master Documentation Home|ko_kr-Home]]
