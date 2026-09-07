# Guide de configuration et de compilation développeur

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **Avis de non-responsabilité concernant le code source du dépôt** : La documentation de ce Wiki reflète **l'état actuel du code source dans le dépôt**, qui peut inclure des commits récents non publiés ou des fonctionnalités en cours de développement en avance sur les versions publiques sur CurseForge et Modrinth.

---

## 🛠️ Prérequis et environnement

Pour contribuer à **Vanilla Outsider: Biome Scale** ou le compiler, assurez-vous que votre environnement local satisfait ces prérequis :

* **Kit de développement Java (JDK)** : **JDK 25** (Eclipse Temurin 25 ou OpenJDK 25 recommandé).
* **Système de compilation** : Gradle (script wrapper `./gradlew` inclus dans le dépôt).
* **IDE** : IntelliJ IDEA 2025.3+ ou Eclipse avec le plugin Minecraft Development.
* **Git** : Git 2.40+ installé et configuré.

Vérifiez votre version de Java installée :
```bash
java -version
javac -version
```

---

## 📂 Structure du dépôt et organisation multi-versions

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

## 🏗️ Compilation depuis les sources

Pour compiler le JAR de production pour une version spécifique de Minecraft, ouvrez un terminal dans le sous-dossier correspondant :

### Compilation pour Minecraft 26.1
```bash
# Accéder au dossier ancre 26.1
cd "Biome Scale 26.1"

# Exécuter la compilation propre et le remappage
./gradlew clean build --no-daemon
```

### Compilation pour Minecraft 26.3
```bash
# Accéder au dossier ancre 26.3
cd "Biome Scale v26.3/Biome Scale 26.3"

# Exécuter la compilation propre et le remappage
./gradlew clean build --no-daemon
```

Les JARs compilés sont générés dans :
```
build/libs/vanilla-outsider-biome-scale-1.0.0+26.1.jar
```
et automatiquement archivés dans le dossier d'archives local du projet :
```
Archive Jar of all versions/MC 26.1.2/vanilla-outsider-biome-scale-1.0.0+26.1.jar
```

---

## 🧪 Tests et vérification

Exécutez les suites de tests automatisés et les vérifications du compilateur :
```bash
./gradlew check --no-daemon
```

Pour lancer un client ou un serveur de test directement depuis Gradle :
```bash
# Lancer le client de test
./gradlew runClient

# Lancer le serveur dédié de test
./gradlew runServer
```

---

## 📦 Configuration de dépendance DasikLibrary

Biome Scale s'appuie sur **DasikLibrary** pour l'enregistrement dynamique des GameRules. Elle est résolue via les dépôts Maven déclarés dans `build.gradle` :

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
* [[Retour au portail principal|fr_fr-Home]]
* [[Compatibilité des versions|fr_fr-Version-Compatibility]]
* [[26.1 Architecture et Mixins|fr_fr-26.1-Architecture-and-Mixins]]
* [[26.3 Architecture et Mixins|fr_fr-26.3-Architecture-and-Mixins]]
