# Minecraft 26.3 — Portal de Documentação Biome Scale

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **Isenção de Responsabilidade do Código-Fonte do Repositório**: A documentação nesta Wiki reflete o **estado atual do código-fonte no repositório**, que pode incluir commits recentes não lançados ou recursos de desenvolvimento à frente das versões públicas no CurseForge e Modrinth.

---

## 🧪 Visão Geral: Biome Scale para Snapshot Minecraft 26.3

Bem-vindo à documentação oficial de **Vanilla Outsider: Biome Scale** para o **Minecraft 26.3**.

No Minecraft 26.3, a geração de mundos baseia-se no amostrador de clima `MultiNoiseBiomeSource` em um espaço de 6 parâmetros. O Biome Scale intercepta essas coordenadas dinamicamente, dilatando o ruído horizontal por um multiplicador configurável.

```
                  ┌─────────────────────────────────────┐
                  │    Minecraft 26.3 Chunk Engine     │
                  └──────────────────┬──────────────────┘
                                     │
                                     ▼
                ┌─────────────────────────────────────────┐
                │ MultiNoiseBiomeSourceMixin (MC 26.3)    │
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

## 📑 Matriz de Subsistemas de Documentação 26.3

| Página do Subsistema | Público-Alvo | Principais Tópicos Abordados |
| :--- | :--- | :--- |
| **[[Escala de Terreno e Ruído|pt_br-26.3-Terrain-and-Noise-Scaling]]** | 🎮 Jogadores e Desenvolvedores | Equações de dilatação de coordenadas, preservação de células Voronoi, alocação zero no GC. |
| **[[Configuração e GameRules|pt_br-26.3-Configuration-and-GameRules]]** | 🎮 Jogadores e Administradores | `biome_scale:scale_modifier`, cache de ticks do servidor, comandos `/gamerule`. |
| **[[Arquitetura e Mixins|pt_br-26.3-Architecture-and-Mixins]]** | 💻 Desenvolvedores | Análise de bytecode Mixin, descritores de injeção, integração com DasikLibrary. |

---

## 🚀 Guia de Início Rápido para Administradores de Servidor

1. **Instalar Dependências**:
   - Garanta que seu servidor tenha o **Fabric Loader** e a **Fabric API** para 26.3.
   - Instale a **DasikLibrary**.
2. **Inserir o JAR**:
   - Coloque `vanilla-outsider-biome-scale-1.0.0+26.3.jar` na pasta `mods/` do servidor.
3. **Configurar o Tamanho dos Biomas**:
   - O modificador padrão é **`200`** (representando uma expansão de $2.0\times$):
     ```text
     /gamerule biome_scale:scale_modifier 200
     ```
   - Para mundos continentais massivos (expansão de $4.0\times$):
     ```text
     /gamerule biome_scale:scale_modifier 400
     ```
   - Para micro-biomas de exploração rápida (tamanho $0.5\times$):
     ```text
     /gamerule biome_scale:scale_modifier 50
     ```

---

## 🧭 Navegação de Versões
* [[Voltar ao Portal Principal|pt_br-Home]]
* [[Matriz de Compatibilidade de Versões|pt_br-Version-Compatibility]]
* [[Guia de Configuração e Compilação|pt_br-Developer-Setup-and-Building]]
* [[Alternar para Wiki MC 26.1|pt_br-26.1-Home]]
