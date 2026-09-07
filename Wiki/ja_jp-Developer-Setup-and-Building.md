# 開発環境構築およびビルドガイド

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **リポジトリソースコードに関する免責事項**: この Wiki のドキュメントは**リポジトリ内の現在のソースコードの状態**を反映しており、CurseForge および Modrinth での公開リリースビルドに先駆けた最新の未リリースコミットや開発中の機能が含まれている場合があります。

---

## 🛠️ 前提条件と環境構築

**Vanilla Outsider: Biome Scale** への貢献やビルドを行う際は、開発環境が以下の要件を満たしていることを確認してください：

* **Java Development Kit (JDK)**: **JDK 25** (Eclipse Temurin 25 または OpenJDK 25 推奨)。
* **ビルドシステム**: Gradle (ラッパースクリプト `./gradlew` がリポジトリに同梱)。
* **IDE**: IntelliJ IDEA 2025.3+ または Minecraft Development プラグイン導入済みの Eclipse。
* **Git**: Git 2.40+ が導入・構成済みであること。

導入されている Java バージョンを確認：
```bash
java -version
javac -version
```

---

## 📂 リポジトリ構成とマルチバージョン管理構造

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

## 🏗️ ソースコードからのビルド

特定の Minecraft バージョン向けの製品 JAR をコンパイルするには、対応するサブプロジェクトディレクトリでターミナルを開きます：

### Minecraft 26.1 向けのビルド
```bash
# 26.1 ディレクトリへ移動
cd "Biome Scale 26.1"

# クリーンビルドおよび再マッピングを実行
./gradlew clean build --no-daemon
```

### Minecraft 26.3 向けのビルド
```bash
# 26.3 ディレクトリへ移動
cd "Biome Scale v26.3/Biome Scale 26.3"

# クリーンビルドおよび再マッピングを実行
./gradlew clean build --no-daemon
```

コンパイルされた JAR は以下に出力されます：
```
build/libs/vanilla-outsider-biome-scale-1.0.0+26.1.jar
```
また、プロジェクト内のアーカイブフォルダに自動的にミラーリング保存されます：
```
Archive Jar of all versions/MC 26.1.2/vanilla-outsider-biome-scale-1.0.0+26.1.jar
```

---

## 🧪 テストおよび動作検証

自動テストスイートとコンパイラ検証を実行：
```bash
./gradlew check --no-daemon
```

Gradle から直接テスト用クライアントまたはサーバーを起動：
```bash
# テスト用クライアントの起動
./gradlew runClient

# テスト用専用サーバーの起動
./gradlew runServer
```

---

## 📦 DasikLibrary 依存関係の設定

Biome Scale は動的名前空間付き GameRule の登録に **DasikLibrary** を使用しています。`build.gradle` で宣言された Maven リポジトリから解決されます：

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

## 🧭 ナビゲーション
* [[メインポータルに戻る|ja_jp-Home]]
* [[バージョン互換性|ja_jp-Version-Compatibility]]
* [[26.1 アーキテクチャと Mixin|ja_jp-26.1-Architecture-and-Mixins]]
* [[26.3 アーキテクチャと Mixin|ja_jp-26.3-Architecture-and-Mixins]]
