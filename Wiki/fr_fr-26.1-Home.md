# Minecraft 26.1 — Portail de documentation Biome Scale

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **Avis de non-responsabilité concernant le code source du dépôt** : La documentation de ce Wiki reflète **l'état actuel du code source dans le dépôt**, qui peut inclure des commits récents non publiés ou des fonctionnalités en cours de développement en avance sur les versions publiques sur CurseForge et Modrinth.

---

## 🌲 Présentation : Biome Scale pour Minecraft 26.1

Bienvenue dans la documentation officielle de **Vanilla Outsider: Biome Scale** pour **Minecraft 26.1**.

Dans Minecraft 26.1, la génération de monde repose sur le sampler climatique `MultiNoiseBiomeSource` dans un espace à 6 dimensions. Biome Scale intercepte dynamiquement ces coordonnées, dilatant l'espace d'échantillonnage de bruit horizontal par un multiplicateur configurable.

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

## 📑 Matrice des sous-systèmes de documentation 26.1

| Page du sous-système | Public cible | Sujets clés couverts |
| :--- | :--- | :--- |
| **[[Échelle du terrain|fr_fr-26.1-Terrain-and-Noise-Scaling]]** | 🎮 Joueurs et développeurs | Équations de dilatation des coordonnées, préservation des cellules de Voronoï, zéro allocation GC. |
| **[[Configuration et GameRules|fr_fr-26.1-Configuration-and-GameRules]]** | 🎮 Joueurs et administrateurs | `biome_scale:scale_modifier`, mise en cache par tick serveur, commandes `/gamerule`. |
| **[[Architecture et Mixins|fr_fr-26.1-Architecture-and-Mixins]]** | 💻 Développeurs | Analyse bytecode Mixin, descripteurs d'injection, intégration DasikLibrary. |

---

## 🚀 Guide de démarrage rapide pour les administrateurs

1. **Installer les dépendances** :
   - Assurez-vous que votre serveur dispose de **Fabric Loader** et **Fabric API** pour 26.1.
   - Installez **DasikLibrary**.
2. **Déposer le JAR** :
   - Placez `vanilla-outsider-biome-scale-1.0.0+26.1.jar` dans le dossier `mods/` de votre serveur.
3. **Configurer la taille des biomes** :
   - Le modificateur par défaut est **`200`** (représentant une expansion de $2.0\times$) :
     ```text
     /gamerule biome_scale:scale_modifier 200
     ```
   - Pour des mondes continentaux immenses (expansion de $4.0\times$) :
     ```text
     /gamerule biome_scale:scale_modifier 400
     ```
   - Pour des micro-biomes rapides (taille $0.5\times$) :
     ```text
     /gamerule biome_scale:scale_modifier 50
     ```

---

## 🧭 Navigation entre versions
* [[Retour au portail principal|fr_fr-Home]]
* [[Matrice de compatibilité des versions|fr_fr-Version-Compatibility]]
* [[Guide de configuration et compilation|fr_fr-Developer-Setup-and-Building]]
* [[Passer au Wiki MC 26.3|fr_fr-26.3-Home]]
