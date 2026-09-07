# Minecraft 26.3 — Portal Dokumentasi Biome Scale

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **Penafian Kode Sumber Repositori**: Dokumentasi di Wiki ini mencerminkan **status kode sumber saat ini di repositori**, yang mungkin mencakup komit terbaru yang belum dirilis atau fitur eksperimental sebelum rilis publik di CurseForge dan Modrinth.

---

## 🧪 Gambaran Umum: Biome Scale untuk Snapshot Minecraft 26.3

Selamat datang di rangkaian dokumentasi resmi **Vanilla Outsider: Biome Scale** untuk **Minecraft 26.3**.

Di Minecraft 26.3, pembuatan dunia mengandalkan sampler iklim `MultiNoiseBiomeSource` dalam ruang parameter 6 dimensi. Biome Scale mencegat koordinat ini secara dinamis, mendilatasi ruang sampling kebisingan horizontal dengan pengali yang dapat dikonfigurasi.

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

## 📑 Matriks Subsistem Dokumentasi 26.3

| Halaman Subsistem | Target Pembaca | Topik Utama yang Dibahas |
| :--- | :--- | :--- |
| **[[Penskalaan Medan & Kebisingan|id_id-26.3-Terrain-and-Noise-Scaling]]** | 🎮 Pemain & Pengembang | Persamaan dilatasi koordinat, pelestarian sel Voronoi, alokasi heap GC nol. |
| **[[Konfigurasi & GameRules|id_id-26.3-Configuration-and-GameRules]]** | 🎮 Pemain & Admin Server | `biome_scale:scale_modifier`, caching tick server dinamis, perintah `/gamerule`. |
| **[[Arsitektur & Mixin|id_id-26.3-Architecture-and-Mixins]]** | 💻 Pengembang & Modder | Analisis bytecode Mixin, deskriptor titik injeksi, integrasi DasikLibrary. |

---

## 🚀 Panduan Mulai Cepat untuk Admin Server

1. **Pasang Dependensi**:
   - Pastikan server Anda memiliki **Fabric Loader** dan **Fabric API** untuk 26.3.
   - Pasang **DasikLibrary**.
2. **Tempatkan JAR**:
   - Letakkan `vanilla-outsider-biome-scale-1.0.0+26.3.jar` di folder `mods/` server.
3. **Konfigurasikan Ukuran Bioma**:
   - Pengubah skala standar adalah **`200`** (mewakili ekspansi $2.0\times$):
     ```text
     /gamerule biome_scale:scale_modifier 200
     ```
   - Untuk dunia benua yang masif (ekspansi $4.0\times$):
     ```text
     /gamerule biome_scale:scale_modifier 400
     ```
   - Untuk mikro-bioma cepat (ukuran $0.5\times$):
     ```text
     /gamerule biome_scale:scale_modifier 50
     ```

---

## 🧭 Navigasi Versi
* [[Kembali ke Portal Utama|id_id-Home]]
* [[Matriks Kompatibilitas Versi|id_id-Version-Compatibility]]
* [[Panduan Penyiapan Pengembang & Kompilasi|id_id-Developer-Setup-and-Building]]
* [[Beralih ke Wiki MC 26.1|id_id-26.1-Home]]
