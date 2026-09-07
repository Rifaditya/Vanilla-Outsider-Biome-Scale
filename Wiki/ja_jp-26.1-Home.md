# Minecraft 26.1 — Biome Scale ドキュメントポータル

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **リポジトリソースコードに関する免責事項**: この Wiki のドキュメントは**リポジトリ内の現在のソースコードの状態**を反映しており、CurseForge および Modrinth での公開リリースビルドに先駆けた最新の未リリースコミットや開発中の機能が含まれている場合があります。

---

## 🌲 概要: Minecraft 26.1 向け Biome Scale

**Minecraft 26.1** 向け **Vanilla Outsider: Biome Scale** の公式ドキュメントスイートへようこそ。

Minecraft 26.1 では、ワールド生成は 6 次元の気候サンプラー `MultiNoiseBiomeSource` に依存しています。Biome Scale はこれらの座標を動的にインターセプトし、水平方向のノイズサンプリング空間を設定可能な倍率で拡張します。

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

## 📑 26.1 ドキュメントサブシステムマトリクス

| サブシステムページ | 対象読者 | 主なトピック |
| :--- | :--- | :--- |
| **[[地形とノイズスケーリング|ja_jp-26.1-Terrain-and-Noise-Scaling]]** | 🎮 プレイヤーおよび開発者 | 座標拡張方程式、ボロノイ領域の保持、GC ヒープ割り当てゼロのプロファイル。 |
| **[[設定とゲームルール|ja_jp-26.1-Configuration-and-GameRules]]** | 🎮 プレイヤーおよび管理者 | `biome_scale:scale_modifier`、サーバーステータスの動的キャッシュ、`/gamerule` コマンド。 |
| **[[アーキテクチャと Mixin|ja_jp-26.1-Architecture-and-Mixins]]** | 💻 開発者およびモッダー | Mixin バイトコード解析、注入ポイント記述子、DasikLibrary 統合。 |

---

## 🚀 サーバー管理者向けクイックスタートガイド

1. **前提依存関係の導入**:
   - サーバーに 26.1 対応の **Fabric Loader** および **Fabric API** を導入します。
   - **DasikLibrary** を導入します。
2. **Mod JAR の配置**:
   - `vanilla-outsider-biome-scale-1.0.0+26.1.jar` をサーバーの `mods/` フォルダに配置します。
3. **バイオーム規模の設定**:
   - 標準のスケーリング修飾子は **`200`**（$2.0\times$ 倍の拡大に相当）：
     ```text
     /gamerule biome_scale:scale_modifier 200
     ```
   - 広大な大陸規模のワールド（$4.0\times$ 倍の拡大）：
     ```text
     /gamerule biome_scale:scale_modifier 400
     ```
   - 目まぐるしく変化するマイクロバイオーム（$0.5\times$ 倍の規模）：
     ```text
     /gamerule biome_scale:scale_modifier 50
     ```

---

## 🧭 バージョンナビゲーション
* [[メインポータルに戻る|ja_jp-Home]]
* [[バージョン互換性マトリクス|ja_jp-Version-Compatibility]]
* [[開発環境構築およびビルドガイド|ja_jp-Developer-Setup-and-Building]]
* [[MC 26.3 Wiki に切り替え|ja_jp-26.3-Home]]
