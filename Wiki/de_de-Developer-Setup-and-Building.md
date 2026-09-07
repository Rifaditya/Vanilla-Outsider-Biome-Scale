# Entwickler-Setup & Build-Anleitung

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **Haftungsausschluss zum Repository-Quellcode**: Die Dokumentation in diesem Wiki spiegelt den **aktuellen Quellcode-Zustand im Repository** wider, der neuere unveröffentlichte Commits oder Entwicklungsfunktionen vor den öffentlichen Builds auf CurseForge und Modrinth enthalten kann.

---

## 🛠️ Voraussetzungen & Umgebung

Um zu **Vanilla Outsider: Biome Scale** beizutragen oder es zu kompilieren, stellen Sie sicher, dass Ihre lokale Umgebung folgende Anforderungen erfüllt:

* **Java Development Kit (JDK)**: **JDK 25** (Eclipse Temurin 25 oder OpenJDK 25 empfohlen).
* **Build-System**: Gradle (Wrapper-Skript `./gradlew` im Repository enthalten).
* **IDE**: IntelliJ IDEA 2025.3+ oder Eclipse mit Minecraft Development Plugin.
* **Git**: Git 2.40+ installiert und konfiguriert.

Überprüfen Sie Ihre installierte Java-Version:
```bash
java -version
javac -version
```

---

## 📂 Repository-Layout & Multi-Versions-Organisation

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

## 🏗️ Aus dem Quellcode bauen

Um die Produktions-JAR für eine bestimmte Minecraft-Version zu kompilieren, öffnen Sie ein Terminal im Unterverzeichnis der jeweiligen Version:

### Erstellen für Minecraft 26.1
```bash
# Wechseln Sie in das 26.1-Verzeichnis
cd "Biome Scale 26.1"

# Führen Sie Clean-Build und Remapping aus
./gradlew clean build --no-daemon
```

### Erstellen für Minecraft 26.3
```bash
# Wechseln Sie in das 26.3-Verzeichnis
cd "Biome Scale v26.3/Biome Scale 26.3"

# Führen Sie Clean-Build und Remapping aus
./gradlew clean build --no-daemon
```

Kompilierte JARs werden erzeugt in:
```
build/libs/vanilla-outsider-biome-scale-1.0.0+26.1.jar
```
und automatisch in den lokalen Archivordner gespiegelt:
```
Archive Jar of all versions/MC 26.1.2/vanilla-outsider-biome-scale-1.0.0+26.1.jar
```

---

## 🧪 Testen & Verifikation

Führen Sie automatisierte Test-Suites und Compiler-Checks aus:
```bash
./gradlew check --no-daemon
```

Starten Sie einen Test-Client oder Test-Server direkt über Gradle:
```bash
# Test-Client starten
./gradlew runClient

# Dedizierten Test-Server starten
./gradlew runServer
```

---

## 📦 DasikLibrary-Abhängigkeitskonfiguration

Biome Scale nutzt **DasikLibrary** für die Registrierung dynamischer Namespaced GameRules. DasikLibrary wird über Maven-Repositories in `build.gradle` aufgelöst:

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

## 🧭 Navigation
* [[Zurück zum Hauptportal|de_de-Home]]
* [[Versionskompatibilität|de_de-Version-Compatibility]]
* [[26.1 Architektur & Mixins|de_de-26.1-Architecture-and-Mixins]]
* [[26.3 Architektur & Mixins|de_de-26.3-Architecture-and-Mixins]]
