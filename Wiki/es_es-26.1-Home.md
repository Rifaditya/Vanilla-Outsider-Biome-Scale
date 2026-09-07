# Minecraft 26.1 — Portal de documentación de Biome Scale

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **Descargo de responsabilidad del código fuente del repositorio**: La documentación de esta Wiki refleja el **estado actual del código fuente en el repositorio**, que puede incluir confirmaciones recientes no publicadas o características en desarrollo antes de las compilaciones públicas en CurseForge y Modrinth.

---

## 🌲 Descripción general: Biome Scale para Minecraft 26.1

Bienvenido a la suite de documentación de **Vanilla Outsider: Biome Scale** para **Minecraft 26.1**.

En Minecraft 26.1, la generación del mundo depende del muestreador climático `MultiNoiseBiomeSource` en un espacio de 6 dimensiones. Biome Scale intercepta dinámicamente estas coordenadas, dilatando el espacio de ruido horizontal según un multiplicador configurable.

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

## 📑 Matriz de subsistemas de documentación 26.1

| Página del subsistema | Audiencia | Temas clave tratados |
| :--- | :--- | :--- |
| **[[Escalado de terreno|es_es-26.1-Terrain-and-Noise-Scaling]]** | 🎮 Jugadores y desarrolladores | Ecuaciones de dilatación de coordenadas, preservación de celdas Voronoi, cero asignación GC. |
| **[[Configuración y GameRules|es_es-26.1-Configuration-and-GameRules]]** | 🎮 Jugadores y administradores | `biome_scale:scale_modifier`, caché en ticks del servidor, comandos `/gamerule`. |
| **[[Arquitectura y Mixins|es_es-26.1-Architecture-and-Mixins]]** | 💻 Desarrolladores | Análisis de bytecode Mixin, descriptores de inyección, integración con DasikLibrary. |

---

## 🚀 Guía de inicio rápido para administradores

1. **Instale las dependencias**:
   - Asegúrese de que su servidor tenga **Fabric Loader** y **Fabric API** para 26.1.
   - Instale **DasikLibrary**.
2. **Coloque el archivo JAR**:
   - Coloque `vanilla-outsider-biome-scale-1.0.0+26.1.jar` en la carpeta `mods/` del servidor.
3. **Configure el tamaño de los biomas**:
   - El modificador predeterminado es **`200`** (representa una expansión de $2.0\times$):
     ```text
     /gamerule biome_scale:scale_modifier 200
     ```
   - Para mundos continentales gigantescos (expansión de $4.0\times$):
     ```text
     /gamerule biome_scale:scale_modifier 400
     ```
   - Para micro-biomas de transición rápida (tamaño $0.5\times$):
     ```text
     /gamerule biome_scale:scale_modifier 50
     ```

---

## 🧭 Navegación de versiones
* [[Volver al Portal Principal|es_es-Home]]
* [[Matriz de compatibilidad de versiones|es_es-Version-Compatibility]]
* [[Guía de configuración y compilación|es_es-Developer-Setup-and-Building]]
* [[Cambiar a Wiki MC 26.3|es_es-26.3-Home]]
