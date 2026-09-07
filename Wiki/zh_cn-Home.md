# Vanilla Outsider: 生物群系缩放 (Biome Scale) 维基

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **仓库源码免责声明**：本维基文档反映了**当前仓库中的源码开发状态**，可能包含先于 CurseForge 与 Modrinth 公开发布版本的最新开发提交或未发布特性。

---

## 🌍 欢迎来到生物群系缩放文档中心

**Vanilla Outsider: Biome Scale** 是针对现代 Minecraft Fabric 环境的轻量级、数学优雅的世界生成强化模组。通过在世界生成过程中拦截底层的多重噪声（Multi-Noise）气候坐标采样器，它以有机的方式扩展生物群系边界，而不改变原生噪声倍频公式或引入人工生物群系。

本模组从根本上解决了 Minecraft 臭名昭著的**“水果沙拉”生物群系杂乱问题**——即热带草原、恶地、黑森林与积雪平原在几百格内频繁紧凑交替的不协调感——通过在水平方向拉伸噪声图，生成真实、宏伟的大陆级生物群系。

```
+-----------------------------------------------------------------------------------+
|                        VANILLA OUTSIDER: BIOME SCALE                              |
|                                                                                   |
|     原生生成 (碎片化频繁交替)                      缩放后 (辽阔宏伟大陆)          |
|   +----+----+----+----+----+----+----+----+    +----------------------------------+  |
|   | DES| PLA| JGL| BAD| SNW| FOR| SAV| SWA|    |                                  |  |
|   +----+----+----+----+----+----+----+----+    |                                  |  |
|   | PLA| JGL| DES| TAIG|FOR| SAV| DES| PLA|    |              沙漠                |  |
|   +----+----+----+----+----+----+----+----+    |            (x2.0 - x4.0)         |  |
|   | BAD| DES| SNW| SWA| PLA| FOR| JGL| BAD|    |                                  |  |
|   +----+----+----+----+----+----+----+----+    +----------------------------------+  |
+-----------------------------------------------------------------------------------+
```

---

## 🧭 多版本导航门户

| 目标版本 | 时代定位 | 维护状态 | Fabric Loader | Fabric API | Java 运行时 | 快速导航入口 |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Minecraft 26.1** | 现代主流标准 | 🟢 长期支持 | `>=0.19.1` | `0.145.4+26.1.2` | Java 25 | [[👉 进入 MC 26.1 文档|zh_cn-26.1-Home]] |
| **Minecraft 26.3** | 现代先锋快照 | 🟡 对齐归档 | `>=0.19.3` | `0.156.1+26.3` | Java 25 | [[👉 进入 MC 26.3 文档|zh_cn-26.3-Home]] |

---

## ⚡ 核心技术基石

1. **纯坐标膨胀（$\mathcal{O}(1)$ 运行时代价）**：
   - 直接在 `MultiNoiseBiomeSource.getNoiseBiome` 中重缩放坐标 $x' = \lfloor x / S \rfloor$ 与 $z' = \lfloor z / S \rfloor$。
   - 生物群系采样期间垃圾回收器（GC）堆内存**0 字节分配**。
2. **通用动态游戏规则**：
   - 基于 **DasikLibrary** 的 `DynamicGameRuleManager`，注册在 `biome_scale:biome_scale` 命名空间下。
   - 使用指令 `/gamerule biome_scale:scale_modifier <数值>` 在游戏内实时调整缩放倍率，无需重启服务器。
3. **原生兼容模组生物群系**：
   - 由于坐标变换发生在独立生物群系注册表之上，所有第三方世界生成模组（如 Terralith、Biomes O' Plenty）与数据包生物群系自动继承缩放比例，无需任何兼容补丁。
4. **泰森多边形（Voronoi）边界完整性**：
   - 缩放底层的连续多重噪声场（温度、湿度、大陆性、侵蚀度、深度、奇特度），保证区块边界处的渐变平滑自然。

---

## 📚 通用开发者与管理员指南

* [[版本兼容性矩阵|zh_cn-Version-Compatibility]] — 支持生命周期、工具链映射与 Java 环境要求。
* [[开发环境搭建与构建|zh_cn-Developer-Setup-and-Building]] — 环境配置、Loom Gradle 构建步骤与测试验证。

---

## ⚖️ 署名与开源协议

* **主创架构师与作者**：**Dasik (Rifaditya)**
* **开源协议**：**GNU 通用公共许可证 v3.0 (GPLv3)**
* **代码仓库**：[Vanilla-Outsider-Biome-Scale](https://github.com/Rifaditya/Vanilla-Outsider-Biome-Scale)
