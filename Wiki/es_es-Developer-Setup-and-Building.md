# Guía de configuración y compilación para desarrolladores

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **Descargo de responsabilidad del código fuente del repositorio**: La documentación de esta Wiki refleja el **estado actual del código fuente en el repositorio**, que puede incluir confirmaciones recientes no publicadas o características en desarrollo antes de las compilaciones públicas en CurseForge y Modrinth.

---

## 🛠️ Requisitos previos y entorno

Para contribuir o compilar **Vanilla Outsider: Biome Scale**, asegúrese de que su entorno local cumpla con los siguientes requisitos:

* **Kit de desarrollo Java (JDK)**: **JDK 25** (se recomienda Eclipse Temurin 25 u OpenJDK 25).
* **Sistema de compilación**: Gradle (el script contenedor `./gradlew` viene incluido).
* **IDE**: IntelliJ IDEA 2025.3+ o Eclipse con el plugin Minecraft Development.
* **Git**: Git 2.40+ instalado y configurado.

Verifique su versión de Java instalada:
```bash
java -version
javac -version
```

---

## 📂 Diseño del repositorio y organización multiversión

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

## 🏗️ Compilación desde el código fuente

Para compilar el JAR de producción para una versión específica de Minecraft, abra un terminal en el subdirectorio de esa versión:

### Compilación para Minecraft 26.1
```bash
# Vaya al directorio de la versión 26.1
cd "Biome Scale 26.1"

# Ejecute la compilación limpia y el remapeo
./gradlew clean build --no-daemon
```

### Compilación para Minecraft 26.3
```bash
# Vaya al directorio de la versión 26.3
cd "Biome Scale v26.3/Biome Scale 26.3"

# Ejecute la compilación limpia y el remapeo
./gradlew clean build --no-daemon
```

Los JAR compilados se generan en:
```
build/libs/vanilla-outsider-biome-scale-1.0.0+26.1.jar
```
y se copian automáticamente en la carpeta de archivo del proyecto:
```
Archive Jar of all versions/MC 26.1.2/vanilla-outsider-biome-scale-1.0.0+26.1.jar
```

---

## 🧪 Pruebas y verificación

Ejecute las suites de pruebas automatizadas y las comprobaciones del compilador con:
```bash
./gradlew check --no-daemon
```

Para ejecutar un cliente o servidor de prueba directamente desde Gradle:
```bash
# Iniciar cliente de prueba
./gradlew runClient

# Iniciar servidor dedicado de prueba
./gradlew runServer
```

---

## 📦 Configuración de dependencias de DasikLibrary

Biome Scale depende de **DasikLibrary** para el registro dinámico de GameRules con espacio de nombres. Se resuelve mediante los repositorios Maven declarados en `build.gradle`:

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

## 🧭 Navegación
* [[Volver al Portal Principal|es_es-Home]]
* [[Compatibilidad de versiones|es_es-Version-Compatibility]]
* [[26.1 Arquitectura y Mixins|es_es-26.1-Architecture-and-Mixins]]
* [[26.3 Arquitectura y Mixins|es_es-26.3-Architecture-and-Mixins]]
