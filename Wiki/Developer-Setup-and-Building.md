# Developer Setup & Build Guide

> [!IMPORTANT]
> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## 🛠️ Prerequisites & Environment

To contribute to or compile **Vanilla Outsider: Biome Scale**, ensure your local environment satisfies these prerequisites:

* **Java Development Kit (JDK)**: **JDK 25** (Eclipse Temurin 25 or OpenJDK 25 recommended).
* **Build System**: Gradle (wrapper script `./gradlew` bundled in repository).
* **IDE**: IntelliJ IDEA 2025.3+ or Eclipse with Minecraft Development Plugin.
* **Git**: Git 2.40+ installed and configured.

Verify your installed Java version:
```bash
java -version
javac -version
```

---

## 📂 Repository Layout & Multi-Version Organization

The repository follows a clean, modular structure split across version anchors:

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

## 🏗️ Building from Source

To compile the production JAR for a specific Minecraft version, open a terminal in that version's subproject directory:

### Building for Minecraft 26.1
```bash
# Navigate to the 26.1 anchor
cd "Biome Scale 26.1"

# Execute clean compile and remapping
./gradlew clean build --no-daemon
```

### Building for Minecraft 26.3
```bash
# Navigate to the 26.3 anchor
cd "Biome Scale v26.3/Biome Scale 26.3"

# Execute clean compile and remapping
./gradlew clean build --no-daemon
```

Compiled JARs will be generated in:
```
build/libs/vanilla-outsider-biome-scale-1.0.0+26.1.jar
```
and automatically mirrored into the project's local archive folder:
```
Archive Jar of all versions/MC 26.1.2/vanilla-outsider-biome-scale-1.0.0+26.1.jar
```

---

## 🧪 Testing & Verification

Run automated test suites and compiler checks using:
```bash
./gradlew check --no-daemon
```

To run a test Minecraft client or dedicated server directly from Gradle:
```bash
# Launch test Minecraft client
./gradlew runClient

# Launch test Minecraft dedicated server
./gradlew runServer
```

---

## 📦 DasikLibrary Dependency Configuration

Biome Scale relies on **DasikLibrary** for dynamic, namespaced GameRule registration. DasikLibrary is resolved through maven repositories declared in `build.gradle`:

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
* [[Back to Master Portal|Home]]
* [[Version Compatibility|Version-Compatibility]]
* [[26.1 Architecture & Mixins|26.1-Architecture-and-Mixins]]
* [[26.3 Architecture & Mixins|26.3-Architecture-and-Mixins]]
