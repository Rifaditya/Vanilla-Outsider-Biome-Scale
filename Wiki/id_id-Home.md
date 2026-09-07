# Wiki Vanilla Outsider: Biome Scale

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **Pernyataan Sumber Repositori**: Dokumentasi dalam Wiki ini mencerminkan **status kode sumber saat ini di repositori**, yang mungkin mencakup komit terbaru atau fitur pengembangan sebelum rilis publik di CurseForge dan Modrinth.

---

## 🌍 Selamat Datang di Pusat Dokumentasi Biome Scale

**Vanilla Outsider: Biome Scale** adalah mod peningkatan pembuatan dunia (world generation) yang ringan dan elegan secara matematis untuk lingkungan Minecraft Fabric modern. Dengan mencegat sampler koordinat iklim Multi-Noise tingkat rendah, mod ini memperluas batas bioma secara organik tanpa mengubah formula noise vanilla atau menambahkan bioma sintetis.

Mod ini menuntaskan masalah "salad buah" bioma Minecraft yang terkenal—di mana savana, badlands, hutan gelap, dan dataran salju bertabrakan dalam jarak beberapa ratus blok—dengan meregangkan peta noise untuk membentuk benua yang luas, realistis, dan imersif.

```
+-----------------------------------------------------------------------------------+
|                        VANILLA OUTSIDER: BIOME SCALE                              |
|                                                                                   |
|     Generasi Vanilla Standar (Bioma Terfragmentasi)   Skala Diperbesar (Benua Luas)       |
|   +----+----+----+----+----+----+----+----+    +----------------------------------+  |
|   | GUR| PAD| HUT| BAD| SAL| RIM| SAV| RAWA|   |                                  |  |
|   +----+----+----+----+----+----+----+----+    |                                  |  |
|   | PAD| HUT| GUR| TAI| RIM| SAV| GUR| PAD|    |              GURUN               |  |
|   +----+----+----+----+----+----+----+----+    |            (x2.0 - x4.0)         |  |
|   | BAD| GUR| SAL| RAW| PAD| RIM| HUT| BAD|    |                                  |  |
|   +----+----+----+----+----+----+----+----+    +----------------------------------+  |
+-----------------------------------------------------------------------------------+
```

---

## 🧭 Portal Pemilihan Versi

| Versi Target | Era Generasi | Status Dukungan | Fabric Loader | Fabric API | Lingkungan Java | Akses Cepat |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Minecraft 26.1** | Standar Modern | 🟢 Rilis Aktif | `>=0.19.1` | `0.145.4+26.1.2` | Java 25 | [[👉 Dokumentasi MC 26.1|id_id-26.1-Home]] |
| **Minecraft 26.3** | Snapshot Unggulan | 🟡 Arsip Paritas | `>=0.19.3` | `0.156.1+26.3` | Java 25 | [[👉 Dokumentasi MC 26.3|id_id-26.3-Home]] |

---

## ⚡ Pilar Teknis Utama

1. **Dilatasi Koordinat Murni (Waktu Eksekusi $\mathcal{O}(1)$)**:
   - Mentransformasi koordinat $x' = \lfloor x / S \rfloor$ dan $z' = \lfloor z / S \rfloor$ langsung di dalam `MultiNoiseBiomeSource.getNoiseBiome`.
   - **0 byte alokasi** memori heap Garbage Collector (GC) selama proses sampling bioma.
2. **GameRules Dinamis Universal**:
   - Dibangun di atas `DynamicGameRuleManager` dari **DasikLibrary** di bawah namespace `biome_scale:biome_scale`.
   - Menyesuaikan pengganda skala secara langsung di dalam game dengan `/gamerule biome_scale:scale_modifier <nilai>` tanpa perlu restart server.
3. **Kompatibilitas Alami dengan Mod Bioma Tambahan**:
   - Karena transformasi terjadi sebelum pencarian registri bioma, mod seperti Terralith dan Biomes O' Plenty secara otomatis mewarisi pembesaran skala tanpa patch khusus.
4. **Preservasi Sel Voronoi**:
   - Meregangkan medan kontinu parameter iklim dengan menjaga kehalusan transisi batas chunk.

---

## 📚 Panduan Pengembang & Administrator Server

* [[Matriks Kompatibilitas Versi|id_id-Version-Compatibility]] — Siklus dukungan, konfigurasi toolchain, dan persyaratan Java.
* [[Pengaturan & Kompilasi Pengembang|id_id-Developer-Setup-and-Building]] — Panduan JDK 25, kompilasi Loom Gradle, dan verifikasi pengujian.

---

## ⚖️ Atribusi & Lisensi

* **Arsitek Utama & Penulis**: **Dasik (Rifaditya)**
* **Lisensi**: **GNU General Public License v3.0 (GPLv3)**
* **Repositori Proyek**: [Vanilla-Outsider-Biome-Scale](https://github.com/Rifaditya/Vanilla-Outsider-Biome-Scale)
