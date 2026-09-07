# 版本兼容性与生命周期矩阵

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **仓库源码免责声明**：本 Wiki 中的文档反映了**仓库中的当前源代码状态**，可能包含领先于 CurseForge 和 Modrinth 上公开版本的近期未发布提交或开发特性。

---

## 📊 Minecraft 版本兼容性矩阵

**Vanilla Outsider: Biome Scale** 在现代 Minecraft 发行版中严格遵循 **单版本单 Jar (1 Jar 1 Version)** 的工程策略进行同步维护。

| Minecraft 版本 | 时代分类 | 构建版本号 | Fabric Loader | Fabric API | Java 运行环境 | DasikLibrary 版本 | 支持状态 |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **26.1.2** | 现代标准版本 | `1.0.0+26.1` | `>=0.19.1` | `0.145.4+26.1.2` | Java 25 | `1.6.9+build.24` | 🟢 **活跃长期支持 (LTS)** |
| **26.3-snapshot-6** | 现代先导版本 | `1.0.0+26.3` | `>=0.19.3` | `0.156.1+26.3` | Java 25 | `1.8.36` | 🟡 **对齐归档版本** |

---

## 🌐 运行环境与端安全性分布

| 环境 | 是否支持 | 要求与行为 |
| :--- | :--- | :--- |
| **专用服务器 (Dedicated Server)** | ✅ **完全支持** | **世界生成所必需。** 所有区块坐标重缩放均在世界创建和区块生成期间在服务端运行。 |
| **单人游戏 (集成服务器)** | ✅ **完全支持** | 在内部集成服务器线程上运行；可通过聊天栏 `/gamerule` 实时控制。 |
| **原版客户端加入服务器** | ✅ **完全兼容原版客户端** | **未修改的原版客户端无需安装模组即可加入！** 生态群系数据编码在发送给客户端的标准原版区块数据包调色板中。 |

---

## 🧩 第三方世界生成与生态群系模组兼容性

由于 Vanilla Outsider: Biome Scale 在生物群系查找*之前*直接在坐标采样层 (`MultiNoiseBiomeSource`) 进行数学运算，因此它与外部世界生成模组具有零额外开销的原生兼容性：

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

### 已验证兼容的世界生成附加模组
1. **Terralith**：无缝扩展自定义宏观生态群系，同时完整保留原版洞穴走向。
2. **Biomes O' Plenty**：精准缩放自定义地表生态群系，零 ID 冲突。
3. **Regions Unexplored**：跨所有自定义区域参数完美兼容。
4. **自定义世界生成数据包 (Datapacks)**：任何使用原版 `minecraft:multi_noise` 生态源的数据包都会被自动缩放。

---

## 🛠️ Java 运行环境 (JRE) 要求

Minecraft 26.1 与 26.3 的构建产物均要求 **Java 25 (LTS)**。尝试在 Java 21 或更低版本上运行将触发即时的 JVM 类加载字节码不兼容警告：

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

## 🧭 页面导航
* [[返回主门户|zh_cn-Home]]
* [[开发者配置与构建指南|zh_cn-Developer-Setup-and-Building]]
* [[MC 26.1 概览|zh_cn-26.1-Home]]
* [[MC 26.3 概览|zh_cn-26.3-Home]]
