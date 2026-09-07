# Kompatibilitas Versi & Matriks Siklus Hidup

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **Penafian Kode Sumber Repositori**: Dokumentasi di Wiki ini mencerminkan **status kode sumber saat ini di repositori**, yang mungkin mencakup komit terbaru yang belum dirilis atau fitur eksperimental sebelum rilis publik di CurseForge dan Modrinth.

---

## 📊 Matriks Kompatibilitas Versi Minecraft

**Vanilla Outsider: Biome Scale** dipelihara secara serempak di seluruh rilis modern Minecraft di bawah kebijakan rekayasa ketat **1 Jar 1 Version**.

| Versi Minecraft | Era Generasi | Tonggak Kompilasi | Fabric Loader | Fabric API | Runtime Java | Versi DasikLibrary | Status Dukungan |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **26.1.2** | Standar Modern | `1.0.0+26.1` | `>=0.19.1` | `0.145.4+26.1.2` | Java 25 | `1.6.9+build.24` | 🟢 **Dukungan Jangka Panjang Aktif (LTS)** |
| **26.3-snapshot-6** | Pelopor Modern | `1.0.0+26.3` | `>=0.19.3` | `0.156.1+26.3` | Java 25 | `1.8.36` | 🟡 **Arsip Rilis Paritas** |

---

## 🌐 Distribusi Lingkungan & Keamanan Sisi

| Lingkungan | Didukung | Persyaratan & Perilaku |
| :--- | :--- | :--- |
| **Server Khusus (Dedicated Server)** | ✅ **Dukungan Penuh** | **Diperlukan untuk pembuatan dunia.** Semua penskalaan ulang koordinat chunk berjalan di sisi server selama pembuatan dunia dan chunk. |
| **Pemain Tunggal (Server Terintegrasi)** | ✅ **Dukungan Penuh** | Berjalan pada thread server terintegrasi internal; kontrol GameRule penuh secara real-time melalui obrolan `/gamerule`. |
| **Klien Vanilla Bergabung ke Server** | ✅ **Kompatibel Klien Vanilla** | **Klien vanilla tanpa modifikasi dapat bergabung tanpa menginstal mod!** Data bioma dienkode dalam palet paket chunk standar. |

---

## 🧩 Kompatibilitas Mod Pembuatan Dunia & Bioma Pihak Ketiga

Karena Vanilla Outsider: Biome Scale beroperasi secara matematis pada tingkat pengambilan sampel koordinat (`MultiNoiseBiomeSource`) *sebelum* pencarian bioma terjadi, mod ini memiliki kompatibilitas asli tanpa overhead dengan mod pembuatan dunia eksternal:

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

### Addon Pembuatan Dunia yang Terverifikasi Kompatibel
1. **Terralith**: Memperluas makro-bioma kustom dengan mulus sambil mempertahankan rute gua vanilla.
2. **Biomes O' Plenty**: Menskalakan bioma permukaan kustom dengan benar tanpa konflik ID.
3. **Regions Unexplored**: Kompatibel di semua parameter wilayah kustom.
4. **Datapack Pembuatan Dunia Kustom**: Setiap datapack yang menggunakan sumber bioma `minecraft:multi_noise` vanilla diskalakan secara otomatis.

---

## 🛠️ Persyaratan Java Runtime Environment (JRE)

Artefak build Minecraft 26.1 dan 26.3 memerlukan **Java 25 (LTS)**. Mencoba menjalankannya di Java 21 atau yang lebih lama akan memicu peringatan ketidakcocokan bytecode classloader JVM:

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

## 🧭 Navigasi
* [[Kembali ke Portal Utama|id_id-Home]]
* [[Panduan Penyiapan Pengembang & Kompilasi|id_id-Developer-Setup-and-Building]]
* [[Ringkasan MC 26.1|id_id-26.1-Home]]
* [[Ringkasan MC 26.3|id_id-26.3-Home]]
