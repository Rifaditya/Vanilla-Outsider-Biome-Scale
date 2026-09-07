# Panduan Penyiapan Pengembang & Kompilasi

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **Penafian Kode Sumber Repositori**: Dokumentasi di Wiki ini mencerminkan **status kode sumber saat ini di repositori**, yang mungkin mencakup komit terbaru yang belum dirilis atau fitur eksperimental sebelum rilis publik di CurseForge dan Modrinth.

---

## 🛠️ Prasyarat & Lingkungan

Untuk berkontribusi atau mengompilasi **Vanilla Outsider: Biome Scale**, pastikan lingkungan lokal Anda memenuhi prasyarat berikut:

* **Java Development Kit (JDK)**: **JDK 25** (disarankan Eclipse Temurin 25 atau OpenJDK 25).
* **Sistem Build**: Gradle (skrip wrapper `./gradlew` disertakan dalam repositori).
* **IDE**: IntelliJ IDEA 2025.3+ atau Eclipse dengan Plugin Minecraft Development.
* **Git**: Git 2.40+ terpasang dan terkonfigurasi.

Verifikasi versi Java yang terinstal:
```bash
java -version
javac -version
```

---

## 📂 Tata Letak Repositori & Organisasi Multi-Versi

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

## 🏗️ Mengompilasi dari Kode Sumber

Untuk mengompilasi JAR produksi untuk versi Minecraft tertentu, buka terminal di subdirektori versi tersebut:

### Mengompilasi untuk Minecraft 26.1
```bash
# Pindah ke direktori 26.1
cd "Biome Scale 26.1"

# Jalankan build bersih dan pemetaan ulang
./gradlew clean build --no-daemon
```

### Mengompilasi untuk Minecraft 26.3
```bash
# Pindah ke direktori 26.3
cd "Biome Scale v26.3/Biome Scale 26.3"

# Jalankan build bersih dan pemetaan ulang
./gradlew clean build --no-daemon
```

JAR yang dikompilasi akan dihasilkan di:
```
build/libs/vanilla-outsider-biome-scale-1.0.0+26.1.jar
```
dan secara otomatis diarsipkan ke folder arsip proyek:
```
Archive Jar of all versions/MC 26.1.2/vanilla-outsider-biome-scale-1.0.0+26.1.jar
```

---

## 🧪 Pengujian & Verifikasi

Jalankan suite pengujian otomatis dan pemeriksaan kompilator menggunakan:
```bash
./gradlew check --no-daemon
```

Untuk menjalankan klien Minecraft atau server pengujian langsung dari Gradle:
```bash
# Luncurkan klien pengujian
./gradlew runClient

# Luncurkan server khusus pengujian
./gradlew runServer
```

---

## 📦 Konfigurasi Ketergantungan DasikLibrary

Biome Scale mengandalkan **DasikLibrary** untuk pendaftaran GameRule dinamis ber-namespace. DasikLibrary diselesaikan melalui repositori maven di `build.gradle`:

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

## 🧭 Navigasi
* [[Kembali ke Portal Utama|id_id-Home]]
* [[Kompatibilitas Versi|id_id-Version-Compatibility]]
* [[26.1 Arsitektur & Mixin|id_id-26.1-Architecture-and-Mixins]]
* [[26.3 Arsitektur & Mixin|id_id-26.3-Architecture-and-Mixins]]
