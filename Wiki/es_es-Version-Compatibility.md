# Matriz de compatibilidad de versiones y ciclo de vida

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **Descargo de responsabilidad del código fuente del repositorio**: La documentación de esta Wiki refleja el **estado actual del código fuente en el repositorio**, que puede incluir confirmaciones recientes no publicadas o características en desarrollo antes de las compilaciones públicas en CurseForge y Modrinth.

---

## 📊 Matriz de compatibilidad de versiones de Minecraft

**Vanilla Outsider: Biome Scale** se mantiene sincronizado en las versiones modernas de Minecraft bajo la estricta política de ingeniería **1 Jar 1 Version**.

| Versión de Minecraft | Era generacional | Versión de compilación | Fabric Loader | Fabric API | Entorno Java | Versión de DasikLibrary | Estado de soporte |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **26.1.2** | Estándar moderno | `1.0.0+26.1` | `>=0.19.1` | `0.145.4+26.1.2` | Java 25 | `1.6.9+build.24` | 🟢 **Soporte activo a largo plazo (LTS)** |
| **26.3-snapshot-6** | Líder de desarrollo | `1.0.0+26.3` | `>=0.19.3` | `0.156.1+26.3` | Java 25 | `1.8.36` | 🟡 **Archivo de paridad** |

---

## 🌐 Entorno y seguridad de lados

| Entorno | Compatible | Requisitos y comportamiento |
| :--- | :--- | :--- |
| **Servidor dedicado** | ✅ **Soporte completo** | **Requerido para la generación de mundos.** El reescalado de coordenadas se ejecuta en el servidor durante la creación del mundo y de los chunks. |
| **Un jugador (servidor integrado)** | ✅ **Soporte completo** | Se ejecuta en el hilo del servidor integrado; control total en tiempo real con `/gamerule` en el chat. |
| **Cliente vainilla uniéndose al servidor** | ✅ **Compatible con cliente vainilla** | **¡Los clientes vainilla sin modificar pueden unirse sin instalar el mod!** Los datos de biomas se codifican en las paletas de paquetes estándar. |

---

## 🧩 Compatibilidad con mods de generación de mundo y biomas de terceros

Debido a que Vanilla Outsider: Biome Scale opera matemáticamente en el nivel de muestreo de coordenadas (`MultiNoiseBiomeSource`) *antes* de que ocurra la búsqueda de biomas, presenta compatibilidad nativa sin sobrecarga con mods externos:

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

### Addons de generación de mundos verificados
1. **Terralith**: Expande fluidamente los macrobiomas personalizados preservando el enrutamiento de cuevas vainilla.
2. **Biomes O' Plenty**: Escala correctamente los biomas de superficie sin conflictos de ID.
3. **Regions Unexplored**: Compatible con todos los parámetros de regiones personalizadas.
4. **Datapacks de generación personalizados**: Cualquier datapack que use fuentes de biomas `minecraft:multi_noise` vainilla se escala automáticamente.

---

## 🛠️ Requisitos del entorno de ejecución Java (JRE)

Tanto los artefactos de Minecraft 26.1 como 26.3 requieren **Java 25 (LTS)**. Intentar ejecutarlos en Java 21 o anterior provocará una advertencia de incompatibilidad de bytecode en el cargador de clases de la JVM:

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

## 🧭 Navegación
* [[Volver al Portal Principal|es_es-Home]]
* [[Guía de configuración y compilación|es_es-Developer-Setup-and-Building]]
* [[Resumen de MC 26.1|es_es-26.1-Home]]
* [[Resumen de MC 26.3|es_es-26.3-Home]]
