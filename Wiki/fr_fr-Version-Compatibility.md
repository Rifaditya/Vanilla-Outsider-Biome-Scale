# Matrice de compatibilité des versions et du cycle de vida

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **Avis de non-responsabilité concernant le code source du dépôt** : La documentation de ce Wiki reflète **l'état actuel du code source dans le dépôt**, qui peut inclure des commits récents non publiés ou des fonctionnalités en cours de développement en avance sur les versions publiques sur CurseForge et Modrinth.

---

## 📊 Matrice de compatibilité des versions de Minecraft

**Vanilla Outsider: Biome Scale** est maintenu en synchronisation sur les versions modernes de Minecraft selon la politique d'ingénierie stricte **1 Jar 1 Version**.

| Version de Minecraft | Ère générationnelle | Version de compilation | Fabric Loader | Fabric API | Exécutable Java | Version de DasikLibrary | État du support |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **26.1.2** | Standard moderne | `1.0.0+26.1` | `>=0.19.1` | `0.145.4+26.1.2` | Java 25 | `1.6.9+build.24` | 🟢 **Support actif à long terme (LTS)** |
| **26.3-snapshot-6** | Tête de développement | `1.0.0+26.3` | `>=0.19.3` | `0.156.1+26.3` | Java 25 | `1.8.36` | 🟡 **Archive de parité** |

---

## 🌐 Environnement et sécurité des côtés

| Environnement | Supporté | Exigences et comportement |
| :--- | :--- | :--- |
| **Serveur dédié** | ✅ **Support complet** | **Requis pour la génération du monde.** Le redimensionnement des coordonnées s'exécute côté serveur lors de la création du monde et des chunks. |
| **Solo (Serveur intégré)** | ✅ **Support complet** | S'exécute sur le thread du serveur intégré ; contrôle total en temps réel via `/gamerule` dans le tchat. |
| **Client vanilla rejoignant le serveur** | ✅ **Compatible client vanilla** | **Les clients vanilla non modifiés peuvent se connecter sans installer le mod !** Les données de biomes sont transmises dans les paquets standards. |

---

## 🧩 Compatibilité avec les mods tiers de génération de monde et de biomes

Comme Vanilla Outsider: Biome Scale opère mathématiquement au niveau de l'échantillonnage des coordonnées (`MultiNoiseBiomeSource`) *avant* la recherche du biome, il offre une compatibilité native sans surcoût avec les mods externes :

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

### Addons de génération de monde vérifiés compatibles
1. **Terralith** : Étend harmonieusement les macro-biomes personnalisés tout en préservant les grottes vanilla.
2. **Biomes O' Plenty** : Met à l'échelle correctement les biomes de surface sans conflit d'identifiant.
3. **Regions Unexplored** : Compatible avec tous les paramètres de régions personnalisées.
4. **Datapacks de génération de monde personnalisés** : Tout datapack utilisant les sources `minecraft:multi_noise` est automatiquement mis à l'échelle.

---

## 🛠️ Exigences relatives à l'environnement Java (JRE)

Les artefacts de compilation Minecraft 26.1 et 26.3 nécessitent **Java 25 (LTS)**. Tenter de les exécuter sur Java 21 ou antérieur déclenchera un avertissement d'incompatibilité de bytecode JVM :

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

## 🧭 Navigation
* [[Retour au portail principal|fr_fr-Home]]
* [[Guide de configuration et compilation|fr_fr-Developer-Setup-and-Building]]
* [[Vue d'ensemble MC 26.1|fr_fr-26.1-Home]]
* [[Vue d'ensemble MC 26.3|fr_fr-26.3-Home]]
