# Matriz de Compatibilidade de Versões e Ciclo de Vida

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **Isenção de Responsabilidade do Código-Fonte do Repositório**: A documentação nesta Wiki reflete o **estado atual do código-fonte no repositório**, que pode incluir commits recentes não lançados ou recursos de desenvolvimento à frente das versões públicas no CurseForge e Modrinth.

---

## 📊 Matriz de Compatibilidade de Versões do Minecraft

**Vanilla Outsider: Biome Scale** é mantido em sincronia nas versões modernas do Minecraft sob a rígida política de engenharia **1 Jar 1 Version**.

| Versão do Minecraft | Era Geracional | Versão da Compilação | Fabric Loader | Fabric API | Runtime Java | Versão da DasikLibrary | Status de Suporte |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **26.1.2** | Padrão Moderno | `1.0.0+26.1` | `>=0.19.1` | `0.145.4+26.1.2` | Java 25 | `1.6.9+build.24` | 🟢 **Suporte Ativo de Longo Prazo (LTS)** |
| **26.3-snapshot-6** | Versão Avançada | `1.0.0+26.3` | `>=0.19.3` | `0.156.1+26.3` | Java 25 | `1.8.36` | 🟡 **Arquivo de Paridade** |

---

## 🌐 Distribuição de Ambiente e Segurança de Lados

| Ambiente | Suportado | Requisito e Comportamento |
| :--- | :--- | :--- |
| **Servidor Dedicado** | ✅ **Suporte Total** | **Necessário para a geração de mundos.** O redimensionamento de coordenadas ocorre no servidor durante a criação de mundos e chunks. |
| **Um Jogador (Servidor Integrado)** | ✅ **Suporte Total** | Executado na thread do servidor integrado; controle total em tempo real via `/gamerule` no chat. |
| **Cliente Vanilla Entrando no Servidor** | ✅ **Compatível com Cliente Vanilla** | **Clientes vanilla não modificados podem entrar sem instalar o mod!** Os biomas são codificados nos pacotes de chunks padrão. |

---

## 🧩 Compatibilidade com Mods de Geração de Mundo e Biomas de Terceiros

Como o Vanilla Outsider: Biome Scale opera matematicamente na camada de amostragem de coordenadas (`MultiNoiseBiomeSource`) *antes* da consulta do bioma, ele possui compatibilidade nativa de sobrecarga zero com mods externos:

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

### Complementos de Geração de Mundo Verificados
1. **Terralith**: Expande suavemente macrobiomas personalizados enquanto preserva cavernas vanilla.
2. **Biomes O' Plenty**: Escala perfeitamente biomas de superfície sem conflitos de ID.
3. **Regions Unexplored**: Compatível com todos os parâmetros de regiões personalizadas.
4. **Datapacks de Geração de Mundo**: Qualquer datapack que use fontes de bioma `minecraft:multi_noise` padrão é escalado automaticamente.

---

## 🛠️ Requisitos de Ambiente Java (JRE)

Os artefatos de build do Minecraft 26.1 e 26.3 exigem **Java 25 (LTS)**. Tentar executá-los no Java 21 ou anterior disparará um aviso imediato de incompatibilidade de bytecode do classloader da JVM:

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

## 🧭 Navegação
* [[Voltar ao Portal Principal|pt_br-Home]]
* [[Guia de Configuração e Compilação|pt_br-Developer-Setup-and-Building]]
* [[Visão Geral MC 26.1|pt_br-26.1-Home]]
* [[Visão Geral MC 26.3|pt_br-26.3-Home]]
