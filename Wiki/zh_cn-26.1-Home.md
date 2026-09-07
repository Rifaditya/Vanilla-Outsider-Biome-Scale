# Minecraft 26.1 — 生态缩放 (Biome Scale) 文档门户

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **仓库源码免责声明**：本 Wiki 中的文档反映了**仓库中的当前源代码状态**，可能包含领先于 CurseForge 和 Modrinth 上公开版本的近期未发布提交或开发特性。

---

## 🌲 概览：Minecraft 26.1 版本的 Biome Scale

欢迎阅读针对 **Minecraft 26.1** 的 **Vanilla Outsider: Biome Scale** 官方文档。

在 Minecraft 26.1 中，世界生成依托于 `MultiNoiseBiomeSource` 气候采样器，并在 6 维参数空间中评估采样点。Biome Scale 动态拦截这些坐标，将水平噪声采样空间按可配置的倍率进行空间膨胀。

```
                  ┌─────────────────────────────────────┐
                  │    Minecraft 26.1 Chunk Engine     │
                  └──────────────────┬──────────────────┘
                                     │
                                     ▼
                ┌─────────────────────────────────────────┐
                │ MultiNoiseBiomeSourceMixin (MC 26.1)    │
                │        x' = x / S , z' = z / S          │
                └────────────────────┬────────────────────┘
                                     │
                                     ▼
                ┌─────────────────────────────────────────┐
                │   Climate$Sampler Evaluation (Vanilla)   │
                │ Continuous Voronoi Cell Macro Continents │
                └─────────────────────────────────────────┘
```

---

## 📑 26.1 文档子系统矩阵

| 子系统页面 | 目标受众 | 核心涵盖主题 |
| :--- | :--- | :--- |
| **[[地形与噪声缩放|zh_cn-26.1-Terrain-and-Noise-Scaling]]** | 🎮 玩家与开发者 | 坐标膨胀方程、沃罗诺伊多边形保持、倍频程拉伸、零 GC 内存堆开销。 |
| **[[配置与游戏规则|zh_cn-26.1-Configuration-and-GameRules]]** | 🎮 玩家与服主 | `biome_scale:scale_modifier`、`biomescale:horizontal_scale`、动态服务器 tick 缓存、`/gamerule` 指令。 |
| **[[架构与 Mixin|zh_cn-26.1-Architecture-and-Mixins]]** | 💻 开发者与模组作者 | Mixin 字节码分析、注入点描述符、DasikLibrary 集成、零开销采样。 |

---

## 🚀 服务器管理员快速上手指南

1. **安装前置依赖**：
   - 确保你的服务器安装了适用于 26.1 的 **Fabric Loader** 与 **Fabric API**。
   - 安装 **DasikLibrary**。
2. **放置模组 JAR**：
   - 将 `vanilla-outsider-biome-scale-1.0.0+26.1.jar` 放入服务端的 `mods/` 文件夹。
3. **配置生态群系规模**：
   - 默认规模修改值为 **`200`**（代表 $2.0\times$ 扩大）：
     ```text
     /gamerule biome_scale:scale_modifier 200
     ```
   - 宏伟辽阔的大陆级世界（$4.0\times$ 扩大）：
     ```text
     /gamerule biome_scale:scale_modifier 400
     ```
   - 紧凑快速变换的微型生态群系（$0.5\times$ 尺寸）：
     ```text
     /gamerule biome_scale:scale_modifier 50
     ```

---

## 🧭 版本导航
* [[返回主门户|zh_cn-Home]]
* [[版本兼容性矩阵|zh_cn-Version-Compatibility]]
* [[开发者配置与构建指南|zh_cn-Developer-Setup-and-Building]]
* [[切换至 MC 26.3 维基|zh_cn-26.3-Home]]
