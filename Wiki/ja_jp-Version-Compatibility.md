# バージョン互換性およびライフサイクルマトリクス

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **リポジトリソースコードに関する免責事項**: この Wiki のドキュメントは**リポジトリ内の現在のソースコードの状態**を反映しており、CurseForge および Modrinth での公開リリースビルドに先駆けた最新の未リリースコミットや開発中の機能が含まれている場合があります。

---

## 📊 Minecraft バージョン互換性マトリクス

**Vanilla Outsider: Biome Scale** は、厳格な **1 Jar 1 Version** ポリシーに基づき、最新の Minecraft リリース間で足並みを揃えて保守されています。

| Minecraft バージョン | 世代分類 | ビルドマイルストーン | Fabric Loader | Fabric API | Java 実行環境 | DasikLibrary バージョン | サポート状態 |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **26.1.2** | 現代標準 | `1.0.0+26.1` | `>=0.19.1` | `0.145.4+26.1.2` | Java 25 | `1.6.9+build.24` | 🟢 **アクティブ長期サポート (LTS)** |
| **26.3-snapshot-6** | 先行開発スナップショット | `1.0.0+26.3` | `>=0.19.3` | `0.156.1+26.3` | Java 25 | `1.8.36` | 🟡 **同等性アーカイブ** |

---

## 🌐 実行環境とサイド安全性

| 環境 | サポート | 要件と挙動 |
| :--- | :--- | :--- |
| **専用サーバー (Dedicated Server)** | ✅ **完全サポート** | **ワールド生成に必須。** チャンク座標の再スケーリングは、ワールド生成時およびチャンク生成時にすべてサーバー側で実行されます。 |
| **シングルプレイヤー (統合サーバー)** | ✅ **完全サポート** | 内部統合サーバーのスレッドで実行。チャット欄の `/gamerule` を通じてリアルタイムに完全制御可能。 |
| **バニラクライアントの参加** | ✅ **バニラクライアント完全対応** | **Mod を導入していない通常のバニラクライアントでも参加可能！** バイオーム情報は標準のバニラチャンクパケットパレットにエンコードされて送信されます。 |

---

## 🧩 サードパーティ製ワールド生成およびバイオーム Mod との互換性

Vanilla Outsider: Biome Scale はバイオーム判定が実行される*前*に座標サンプリング層 (`MultiNoiseBiomeSource`) で数学的に処理を行うため、外部のワールド生成 Mod とオーバーヘッドなしで完全な互換性を発揮します：

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

### 検証済みの互換ワールド生成アドオン
1. **Terralith**: バニラの洞窟構造を完全に維持したまま、カスタムマクロバイオームを自然に拡大。
2. **Biomes O' Plenty**: ID の競合を一切起こさずにカスタム地表バイオームを正確にスケーリング。
3. **Regions Unexplored**: すべてのカスタム地域パラメータにおいて完璧に動作。
4. **カスタムワールド生成データパック**: バニラの `minecraft:multi_noise` バイオームソースを使用するデータパックは自動的にスケーリングされます。

---

## 🛠️ Java 実行環境 (JRE) の要件

Minecraft 26.1 および 26.3 のビルド成果物はどちらも **Java 25 (LTS)** を必須とします。Java 21 以前で起動しようとすると、即座に JVM クラスローダーのバイトコード非互換エラーが発生します：

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

## 🧭 ナビゲーション
* [[メインポータルに戻る|ja_jp-Home]]
* [[開発環境構築およびビルドガイド|ja_jp-Developer-Setup-and-Building]]
* [[MC 26.1 概要|ja_jp-26.1-Home]]
* [[MC 26.3 概要|ja_jp-26.3-Home]]
