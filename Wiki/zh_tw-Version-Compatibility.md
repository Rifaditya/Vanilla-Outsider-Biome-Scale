# 版本相容性與生命週期矩陣

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **存放庫原始碼免責聲明**：本 Wiki 中的文件反映了**存放庫中的當前原始碼狀態**，可能包含領先於 CurseForge 與 Modrinth 上公開版本的近期未發布提交或開發特性。

---

## 📊 Minecraft 版本相容性矩陣

**Vanilla Outsider: Biome Scale** 在現代 Minecraft 發行版中嚴格遵循 **單版本單 Jar (1 Jar 1 Version)** 的工程策略進行同步維護。

| Minecraft 版本 | 時代分類 | 建置版本號 | Fabric Loader | Fabric API | Java 執行環境 | DasikLibrary 版本 | 支援狀態 |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **26.1.2** | 現代標準版本 | `1.0.0+26.1` | `>=0.19.1` | `0.145.4+26.1.2` | Java 25 | `1.6.9+build.24` | 🟢 **活躍長期支援 (LTS)** |
| **26.3-snapshot-6** | 現代先導版本 | `1.0.0+26.3` | `>=0.19.3` | `0.156.1+26.3` | Java 25 | `1.8.36` | 🟡 **對齊封存版本** |

---

## 🌐 執行環境與端安全性分佈

| 環境 | 是否支援 | 要求與行為 |
| :--- | :--- | :--- |
| **專用伺服器 (Dedicated Server)** | ✅ **完全支援** | **世界生成所必需。** 所有區塊座標重新縮放均在世界建立與區塊生成期間於伺服端執行。 |
| **單人遊戲 (整合伺服器)** | ✅ **完全支援** | 在內部整合伺服器執行緒上執行；可透過聊天欄 `/gamerule` 即時控制。 |
| **原版客戶端加入伺服器** | ✅ **完全相容原版客戶端** | **未修改的原版客戶端無需安裝模組即可加入！** 生態域資料編碼於發送給客戶端的標準原版區塊封包調色盤中。 |

---

## 🧩 第三方世界生成與生態域模組相容性

由於 Vanilla Outsider: Biome Scale 在生態域尋找*之前*直接在座標採樣層 (`MultiNoiseBiomeSource`) 進行數學運算，因此它與外部世界生成模組具有零額外開銷的原生相容性：

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

### 已驗證相容的世界生成附加模組
1. **Terralith**：無縫擴展自訂巨觀生態域，同時完整保留原版洞穴走向。
2. **Biomes O' Plenty**：精準縮放自訂地表生態域，零 ID 衝突。
3. **Regions Unexplored**：跨所有自訂區域參數完美相容。
4. **自訂世界生成資料包 (Datapacks)**：任何使用原版 `minecraft:multi_noise` 生態源的資料包都會被自動縮放。

---

## 🛠️ Java 執行環境 (JRE) 要求

Minecraft 26.1 與 26.3 的建置產物均要求 **Java 25 (LTS)**。嘗試在 Java 21 或更低版本上執行將觸發即時的 JVM 類別載入位元組碼不相容警告：

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

## 🧭 頁面導航
* [[返回主門戶|zh_tw-Home]]
* [[開發者配置與建置指南|zh_tw-Developer-Setup-and-Building]]
* [[MC 26.1 概覽|zh_tw-26.1-Home]]
* [[MC 26.3 概覽|zh_tw-26.3-Home]]
