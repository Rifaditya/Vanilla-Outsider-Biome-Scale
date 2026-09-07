# Wiki Vanilla Outsider: Biome Scale

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **Avertissement relatif au code source du dépôt** : La documentation de ce wiki reflète **l'état actuel du code source du dépôt**, qui peut inclure des commits récents non publiés ou des fonctionnalités en cours de développement précédant les versions publiques sur CurseForge et Modrinth.

---

## 🌍 Bienvenue sur le centre de documentation de Biome Scale

**Vanilla Outsider: Biome Scale** est une amélioration mathématique légère et élégante de la génération de monde pour Minecraft Fabric. En interceptant l'échantillonnage des coordonnées climatiques du Multi-Noise, le mod étire naturellement les frontières des biomes sans modifier les formules de bruit vanilla ni ajouter de biomes artificiels.

Le mod résout le problème de la « salade de fruits » de biomes — où savanes, badlands, forêts sombres et plaines enneigées s'entrechoquent sur quelques centaines de blocs — en dilatant les cartes de bruit pour former de vastes continents immersifs.

```
+-----------------------------------------------------------------------------------+
|                        VANILLA OUTSIDER: BIOME SCALE                              |
|                                                                                   |
|     Génération Vanilla (Biomes fragmentés)          Mise à l'échelle (Vastes Continents)  |
|   +----+----+----+----+----+----+----+----+    +----------------------------------+  |
|   | DES| PLA| JGL| BAD| NEI| FOR| SAV| MAR|    |                                  |  |
|   +----+----+----+----+----+----+----+----+    |                                  |  |
|   | PLA| JGL| DES| TAI| FOR| SAV| DES| PLA|    |              DÉSERT              |  |
|   +----+----+----+----+----+----+----+----+    |            (x2.0 - x4.0)         |  |
|   | BAD| DES| NEI| MAR| PLA| FOR| JGL| BAD|    |                                  |  |
|   +----+----+----+----+----+----+----+----+    +----------------------------------+  |
+-----------------------------------------------------------------------------------+
```

---

## 🧭 Portail de sélection de versions

| Version Cible | Ère | Statut | Fabric Loader | Fabric API | Exécution Java | Accès Rapide |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Minecraft 26.1** | Standard Moderne | 🟢 Version Active | `>=0.19.1` | `0.145.4+26.1.2` | Java 25 | [[👉 Documentation MC 26.1|fr_fr-26.1-Home]] |
| **Minecraft 26.3** | Snapshot Leader | 🟡 Archive de Parité | `>=0.19.3` | `0.156.1+26.3` | Java 25 | [[👉 Documentation MC 26.3|fr_fr-26.3-Home]] |

---

## ⚡ Piliers techniques essentiels

1. **Dilatation pure des coordonnées (Temps d'exécution $\mathcal{O}(1)$)** :
   - Modifie directement les coordonnées $x' = \lfloor x / S \rfloor$ et $z' = \lfloor z / S \rfloor$ dans `MultiNoiseBiomeSource.getNoiseBiome`.
   - **0 octet d'allocation** sur le tas du ramasse-miettes (GC) lors de l'échantillonnage.
2. **GameRules dynamiques universelles** :
   - Propulsé par `DynamicGameRuleManager` de **DasikLibrary** sous l'espace de noms `biome_scale:biome_scale`.
   - Ajustez l'échelle en temps réel avec `/gamerule biome_scale:scale_modifier <valeur>` sans redémarrage du serveur.
3. **Compatibilité native avec les biomes moddés** :
   - L'interception s'effectuant en amont du registre des biomes, les mods tiers (Terralith, Biomes O' Plenty) et datapacks héritent directement de l'échelle.
4. **Préservation des cellules de Voronoï** :
   - Étire le champ continu des paramètres climatiques pour garantir des transitions douces entre les tronçons (chunks).

---

## 📚 Guides universels pour développeurs et administrateurs

* [[Matrice de compatibilité des versions|fr_fr-Version-Compatibility]] — Cycle de support, environnement d'outils et exigences Java.
* [[Installation et compilation pour développeurs|fr_fr-Developer-Setup-and-Building]] — Configuration JDK 25, étapes Loom Gradle et tests.

---

## ⚖️ Attribution & Licence

* **Architecte principal et auteur** : **Dasik (Rifaditya)**
* **Licence** : **GNU General Public License v3.0 (GPLv3)**
* **Dépôt GitHub** : [Vanilla-Outsider-Biome-Scale](https://github.com/Rifaditya/Vanilla-Outsider-Biome-Scale)
