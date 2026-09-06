# Vanilla Outsider: 生物群系縮放 (Biome Scale) 維基

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **倉庫源碼免責聲明**：本維基文件反映了**目前倉庫中的原始碼開發狀態**，可能包含早於 CurseForge 與 Modrinth 公開發布版本的最新開發提交或未發布特性。

---

## 🌍 歡迎來到生物群系縮放文檔中心

**Vanilla Outsider: Biome Scale** 是針對現代 Minecraft Fabric 環境的輕量級、數學優雅的世界生成強化模組。透過在世界生成過程中攔截底層的多重噪聲（Multi-Noise）氣候座標採樣器，它以有機的方式擴展生物群系邊界，而不改變原生噪聲倍頻公式或引入人工生物群系。

本模組從根本上解決了 Minecraft 令人詬病的**「水果沙拉」生物群系雜亂問題**——即熱帶草原、惡地、黑森林與積雪平原在數百格內頻繁緊湊交替的不協調感——透過在水平方向拉伸噪聲圖，生成真實、壯闊的大陸級生物群系。

```
+-----------------------------------------------------------------------------------+
|                        VANILLA OUTSIDER: BIOME SCALE                              |
|                                                                                   |
|     原生生成 (碎片化頻繁交替)                      縮放後 (遼闊宏偉大陸)          |
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

## 🧭 多版本導航門戶

| 目標版本 | 時代定位 | 維護狀態 | Fabric Loader | Fabric API | Java 執行環境 | 快速導航入口 |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Minecraft 26.1** | 現代主流標準 | 🟢 長期支援 | `>=0.19.1` | `0.145.4+26.1.2` | Java 25 | [[👉 進入 MC 26.1 文件|26.1-Home]] |
| **Minecraft 26.3** | 現代先鋒快照 | 🟡 對齊歸檔 | `>=0.19.3` | `0.156.1+26.3` | Java 25 | [[👉 進入 MC 26.3 文件|26.3-Home]] |

---

## ⚡ 核心技術基石

1. **純座標膨脹（$\mathcal{O}(1)$ 執行時代價）**：
   - 直接在 `MultiNoiseBiomeSource.getNoiseBiome` 中重縮放座標 $x' = \lfloor x / S \rfloor$ 與 $z' = \lfloor z / S \rfloor$。
   - 生物群系採樣期間垃圾回收器（GC）堆記憶體**0 位元組分配**。
2. **通用動態遊戲規則**：
   - 基於 **DasikLibrary** 的 `DynamicGameRuleManager`，註冊於 `biome_scale:biome_scale` 命名空間下。
   - 使用指令 `/gamerule biome_scale:scale_modifier <數值>` 在遊戲內即時調整縮放倍率，無需重啟伺服器。
3. **原生相容模組生物群系**：
   - 由於座標變換發生於獨立生物群系註冊表之上，所有第三方世界生成模組（如 Terralith、Biomes O' Plenty）與資料包生物群系自動繼承縮放比例，無需任何相容補丁。
4. **沃羅諾伊（Voronoi）邊界完整性**：
   - 縮放底層的連續多重噪聲場（溫度、濕度、大陸性、侵蝕度、深度、奇特度），保證區塊邊界處的漸變平滑自然。

---

## 📚 通用開發者與管理員指南

* [[版本相容性矩陣|Version-Compatibility]] — 支援生命週期、工具鏈映射與 Java 環境要求。
* [[開發環境建置與編譯|Developer-Setup-and-Building]] — 環境配置、Loom Gradle 編譯步驟與測試驗證。

---

## ⚖️ 署名與開源授權

* **主創架構師與作者**：**Dasik (Rifaditya)**
* **開源授權**：**GNU 通用公共授權條款 v3.0 (GPLv3)**
* **程式碼倉庫**：[Vanilla-Outsider-Biome-Scale](https://github.com/Rifaditya/Vanilla-Outsider-Biome-Scale)
