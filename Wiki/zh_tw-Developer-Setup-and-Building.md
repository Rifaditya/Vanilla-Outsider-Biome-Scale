# 開發者環境配置與建置指南

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **存放庫原始碼免責聲明**：本 Wiki 中的文件反映了**存放庫中的當前原始碼狀態**，可能包含領先於 CurseForge 與 Modrinth 上公開版本的近期未發布提交或開發特性。

---

## 🛠️ 前置條件與環境要求

若要參與貢獻或編譯 **Vanilla Outsider: Biome Scale**，請確保你的本機開發環境滿足以下要求：

* **Java 開發工具包 (JDK)**: **JDK 25**（推薦 Eclipse Temurin 25 或 OpenJDK 25）。
* **建置系統**: Gradle（程式碼存放庫已內建 Gradle Wrapper 指令碼 `./gradlew`）。
* **整合開發環境 (IDE)**: IntelliJ IDEA 2025.3+ 或裝有 Minecraft Development 外掛程式的 Eclipse。
* **版本控制**: Git 2.40+ 已安裝並配置。

驗證當前安裝的 Java 版本：
```bash
java -version
javac -version
```

---

## 📂 程式碼存放庫佈局與多版本工程結構

```
Vanilla-Outsider-Biome-Scale/
├── Biome Scale 26.1/                 # Minecraft 26.1.2 Anchor
│   ├── build.gradle                  # Loom build script with auto-archiving
│   ├── gradle.properties             # MC 26.1.2 toolchain versions
│   ├── src/main/java/                # Java 25 source code
│   └── src/main/resources/           # Mixin config, assets, lang JSONs
├── Biome Scale v26.3/
│   └── Biome Scale 26.3/             # Minecraft 26.3 Snapshot Anchor
│       ├── build.gradle              # Snapshot toolchain config
│       └── src/main/java/            # 26.3 parity sources
├── Wiki/                             # Central Multi-Version GitHub Wiki pages
├── LICENSE                           # GNU General Public License v3.0
└── MASTER_RELEASE_QUEUE.md           # Ground-truth multi-version tracking
```

---

## 🏗️ 從原始碼編譯建置

要編譯針對特定 Minecraft 版本的正式生產 JAR 檔，請在該版本對應的子工程目錄中開啟終端機：

### 為 Minecraft 26.1 建置
```bash
# 切換至 26.1 錨點目錄
cd "Biome Scale 26.1"

# 執行清理建置與重新映射
./gradlew clean build --no-daemon
```

### 為 Minecraft 26.3 建置
```bash
# 切換至 26.3 錨點目錄
cd "Biome Scale v26.3/Biome Scale 26.3"

# 執行清理建置與重新映射
./gradlew clean build --no-daemon
```

編譯產生的 JAR 製品位於：
```
build/libs/vanilla-outsider-biome-scale-1.0.0+26.1.jar
```
並自動鏡像歸檔至本專案的版本歸檔資料夾：
```
Archive Jar of all versions/MC 26.1.2/vanilla-outsider-biome-scale-1.0.0+26.1.jar
```

---

## 🧪 測試與驗證

執行自動化測試套件與編譯器檢查：
```bash
./gradlew check --no-daemon
```

直接透過 Gradle 啟動測試 Minecraft 客戶端或伺服端：
```bash
# 啟動測試客戶端
./gradlew runClient

# 啟動測試專用伺服端
./gradlew runServer
```

---

## 📦 DasikLibrary 相依性配置

Biome Scale 相依 **DasikLibrary** 實作動態命名空間遊戲規則註冊。DasikLibrary 透過 `build.gradle` 中宣告的 Maven 存放庫解析：

```groovy
repositories {
    maven {
        name = "DasikMaven"
        url = "https://maven.vanillaoutsider.net/releases"
    }
}

dependencies {
    modImplementation "net.dasik:dasik-library:${project.dasik_library_version}"
}
```

---

## 🧭 頁面導航
* [[返回主門戶|zh_tw-Home]]
* [[版本相容性|zh_tw-Version-Compatibility]]
* [[26.1 架構與 Mixin|zh_tw-26.1-Architecture-and-Mixins]]
* [[26.3 架構與 Mixin|zh_tw-26.3-Architecture-and-Mixins]]
