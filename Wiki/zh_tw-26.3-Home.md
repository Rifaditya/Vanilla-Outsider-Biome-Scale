# Minecraft 26.3 — 生態縮放 (Biome Scale) 文件門戶

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **存放庫原始碼免責聲明**：本 Wiki 中的文件反映了**存放庫中的當前原始碼狀態**，可能包含領先於 CurseForge 與 Modrinth 上公開版本的近期未發布提交或開發特性。

---

## 🧪 概覽：Minecraft 26.3 快照版本的 Biome Scale

歡迎閱讀針對 **Minecraft 26.3** 的 **Vanilla Outsider: Biome Scale** 官方文件。

在 Minecraft 26.3 中，世界生成依託於 `MultiNoiseBiomeSource` 氣候採樣器，並在 6 維參數空間中評估採樣點。Biome Scale 動態攔截這些座標，將水平雜訊採樣空間按可設定的倍率進行空間膨脹。

```
                  ┌─────────────────────────────────────┐
                  │    Minecraft 26.3 Chunk Engine     │
                  └──────────────────┬──────────────────┘
                                     │
                                     ▼
                ┌─────────────────────────────────────────┐
                │ MultiNoiseBiomeSourceMixin (MC 26.3)    │
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

## 📑 26.3 文件子系統矩陣

| 子系統頁面 | 目標受眾 | 核心涵蓋主題 |
| :--- | :--- | :--- |
| **[[地形與雜訊縮放|zh_tw-26.3-Terrain-and-Noise-Scaling]]** | 🎮 玩家與開發者 | 座標膨脹方程式、沃羅諾伊多邊形保持、倍頻程拉伸、零 GC 記憶體堆開銷。 |
| **[[設定與遊戲規則|zh_tw-26.3-Configuration-and-GameRules]]** | 🎮 玩家與服主 | `biome_scale:scale_modifier`、`biomescale:horizontal_scale`、動態伺服器 tick 快取、`/gamerule` 指令。 |
| **[[架構與 Mixin|zh_tw-26.3-Architecture-and-Mixins]]** | 💻 開發者與模組作者 | Mixin 位元組碼分析、注入點描述符、DasikLibrary 整合、零開銷採樣。 |

---

## 🚀 伺服器管理員快速上手指南

1. **安裝前置相依項**：
   - 確保你的伺服器安裝了適用於 26.3 的 **Fabric Loader** 與 **Fabric API**。
   - 安裝 **DasikLibrary**。
2. **放置模組 JAR**：
   - 將 `vanilla-outsider-biome-scale-1.0.0+26.3.jar` 放入伺服端的 `mods/` 資料夾。
3. **設定生態域規模**：
   - 預設規模修改值為 **`200`**（代表 $2.0\times$ 擴大）：
     ```text
     /gamerule biome_scale:scale_modifier 200
     ```
   - 宏偉遼闊的大陸級世界（$4.0\times$ 擴大）：
     ```text
     /gamerule biome_scale:scale_modifier 400
     ```
   - 緊湊快速變換的微型生態域（$0.5\times$ 尺寸）：
     ```text
     /gamerule biome_scale:scale_modifier 50
     ```

---

## 🧭 版本導航
* [[返回主門戶|zh_tw-Home]]
* [[版本相容性矩陣|zh_tw-Version-Compatibility]]
* [[開發者配置與建置指南|zh_tw-Developer-Setup-and-Building]]
* [[切換至 MC 26.1 維基|zh_tw-26.1-Home]]
