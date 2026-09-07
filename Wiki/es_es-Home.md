# Wiki de Vanilla Outsider: Biome Scale

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **Aviso sobre el código fuente del repositorio**: La documentación de esta Wiki refleja el **estado actual del código fuente en el repositorio**, que puede incluir confirmaciones recientes no publicadas o características en desarrollo antes de las versiones públicas en CurseForge y Modrinth.

---

## 🌍 Bienvenido al Centro de Documentación de Biome Scale

**Vanilla Outsider: Biome Scale** es una optimización matemática ligera y elegante para la generación de mundos en entornos modernos de Minecraft Fabric. Al interceptar los muestreadores climáticos de coordenadas de ruido múltiple (Multi-Noise), amplía de forma orgánica los límites de los biomas sin alterar las fórmulas originales ni añadir biomas artificiales.

El mod resuelve el problema de la "ensalada de frutas" de biomas (donde sabanas, badlands, bosques oscuros y llanuras nevadas colisionan a escasos bloques de distancia) estirando los mapas de ruido para formar continentes inmensos y realistas.

```
+-----------------------------------------------------------------------------------+
|                        VANILLA OUTSIDER: BIOME SCALE                              |
|                                                                                   |
|     Generación Vanilla (Biomas fragmentados)        Escalado (Inmensos Continentes)       |
|   +----+----+----+----+----+----+----+----+    +----------------------------------+  |
|   | DES| LLA| SEL| BAD| NIE| BOS| SAB| PAN|    |                                  |  |
|   +----+----+----+----+----+----+----+----+    |                                  |  |
|   | LLA| SEL| DES| TAI| BOS| SAB| DES| LLA|    |             DESIERTO             |  |
|   +----+----+----+----+----+----+----+----+    |            (x2.0 - x4.0)         |  |
|   | BAD| DES| NIE| PAN| LLA| BOS| SEL| BAD|    |                                  |  |
|   +----+----+----+----+----+----+----+----+    +----------------------------------+  |
+-----------------------------------------------------------------------------------+
```

---

## 🧭 Portal de Selección de Versiones

| Versión Objetivo | Era de Generación | Estado | Fabric Loader | Fabric API | Entorno Java | Acceso Directo |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Minecraft 26.1** | Estándar Moderno | 🟢 Versión Activa | `>=0.19.1` | `0.145.4+26.1.2` | Java 25 | [[👉 Documentación MC 26.1|es_es-26.1-Home]] |
| **Minecraft 26.3** | Snapshot Líder | 🟡 Archivo de Paridad | `>=0.19.3` | `0.156.1+26.3` | Java 25 | [[👉 Documentación MC 26.3|es_es-26.3-Home]] |

---

## ⚡ Pilares Técnicos Fundamentales

1. **Dilatación Pura de Coordenadas ($\mathcal{O}(1)$ en ejecución)**:
   - Modifica directamente las coordenadas $x' = \lfloor x / S \rfloor$ y $z' = \lfloor z / S \rfloor$ en `MultiNoiseBiomeSource.getNoiseBiome`.
   - **0 bytes de asignación** en la memoria del recolector de basura (GC) durante el muestreo.
2. **GameRules Dinámicas Universales**:
   - Desarrollado sobre el `DynamicGameRuleManager` de **DasikLibrary** bajo el espacio de nombres `biome_scale:biome_scale`.
   - Modifica los factores de escala en tiempo real con `/gamerule biome_scale:scale_modifier <valor>` sin reiniciar el servidor.
3. **Compatibilidad Nativa con Biomas Modificados**:
   - Como la transformación se aplica antes del registro de biomas, cualquier mod de generación de terreno (Terralith, Biomes O' Plenty) hereda la escala automáticamente.
4. **Preservación de Celdas de Voronoi**:
   - Escala el campo continuo de parámetros climáticos garantizando transiciones suaves en las fronteras entre chunks.

---

## 📚 Guías Universales para Desarrolladores y Administradores

* [[Matriz de Compatibilidad de Versiones|es_es-Version-Compatibility]] — Ciclo de soporte, cadenas de herramientas y requisitos de Java.
* [[Configuración y Compilación de Desarrollo|es_es-Developer-Setup-and-Building]] — Configuración de entorno, comandos de compilación Loom Gradle y pruebas.

---

## ⚖️ Atribución y Licencia

* **Arquitecto Principal y Autor**: **Dasik (Rifaditya)**
* **Licencia**: **GNU General Public License v3.0 (GPLv3)**
* **Repositorio**: [Vanilla-Outsider-Biome-Scale](https://github.com/Rifaditya/Vanilla-Outsider-Biome-Scale)
