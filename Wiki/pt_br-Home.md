# Wiki de Vanilla Outsider: Biome Scale

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **Aviso sobre o código-fonte do repositório**: A documentação nesta Wiki reflete o **estado atual do código-fonte no repositório**, o que pode incluir commits recentes não lançados ou recursos em desenvolvimento anteriores aos lançamentos públicos no CurseForge e Modrinth.

---

## 🌍 Bem-vindo à Central de Documentação do Biome Scale

**Vanilla Outsider: Biome Scale** é uma melhoria matemática leve e elegante para a geração de mundos no ecossistema moderno do Minecraft Fabric. Ao interceptar os amostradores climáticos de coordenadas do Multi-Noise durante a criação do mundo, ele expande os limites dos biomas organicamente sem alterar as fórmulas vanilla ou criar biomas artificiais.

O mod resolve o problema da "salada de frutas" de biomas (onde savanas, badlands, florestas escuras e planícies nevadas colidem em distâncias minúsculas) esticando os mapas de ruído para gerar continentes gigantescos e imersivos.

```
+-----------------------------------------------------------------------------------+
|                        VANILLA OUTSIDER: BIOME SCALE                              |
|                                                                                   |
|     Geração Vanilla Padrão (Biomas Fragmentados)     Escala Expandida (Grandes Continentes)|
|   +----+----+----+----+----+----+----+----+    +----------------------------------+  |
|   | DES| PLA| FLO| BAD| NEV| FLO| SAV| PAN|    |                                  |  |
|   +----+----+----+----+----+----+----+----+    |                                  |  |
|   | PLA| FLO| DES| TAI| FLO| SAV| DES| PLA|    |             DESERTO              |  |
|   +----+----+----+----+----+----+----+----+    |            (x2.0 - x4.0)         |  |
|   | BAD| DES| NEV| PAN| PLA| FLO| FLO| BAD|    |                                  |  |
|   +----+----+----+----+----+----+----+----+    +----------------------------------+  |
+-----------------------------------------------------------------------------------+
```

---

## 🧭 Portal de Seleção de Versão

| Versão Alvo | Era | Status | Fabric Loader | Fabric API | Ambiente Java | Acesso Rápido |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Minecraft 26.1** | Padrão Moderno | 🟢 Versão Ativa | `>=0.19.1` | `0.145.4+26.1.2` | Java 25 | [[👉 Documentação MC 26.1|26.1-Home]] |
| **Minecraft 26.3** | Snapshot Líder | 🟡 Arquivo de Paridade | `>=0.19.3` | `0.156.1+26.3` | Java 25 | [[👉 Documentação MC 26.3|26.3-Home]] |

---

## ⚡ Pilares Técnicos Fundamentais

1. **Dilatação Pura de Coordenadas ($\mathcal{O}(1)$ em tempo de execução)**:
   - Modifica coordenadas $x' = \lfloor x / S \rfloor$ e $z' = \lfloor z / S \rfloor$ diretamente em `MultiNoiseBiomeSource.getNoiseBiome`.
   - **0 bytes de alocação** na memória heap do Garbage Collector durante a amostragem de biomas.
2. **GameRules Dinâmicas Universais**:
   - Desenvolvido sobre o `DynamicGameRuleManager` da **DasikLibrary** no namespace `biome_scale:biome_scale`.
   - Ajuste o multiplicador em tempo real com `/gamerule biome_scale:scale_modifier <valor>` sem reiniciar o servidor.
3. **Compatibilidade Nativa com Biomas Modificados**:
   - Como a dilatação atua antes do registro individual de biomas, mods como Terralith e Biomes O' Plenty herdam o escalonamento automaticamente.
4. **Preservação das Células de Voronoi**:
   - Escala o campo contínuo de parâmetros climáticos preservando transições suaves nas bordas dos chunks.

---

## 📚 Guias Universais para Desenvolvedores e Administradores

* [[Matriz de Compatibilidade de Versões|Version-Compatibility]] — Ciclo de suporte, ferramentas e requisitos Java.
* [[Configuração e Compilação para Desenvolvedores|Developer-Setup-and-Building]] — Configuração do JDK 25, comandos Loom Gradle e testes.

---

## ⚖️ Atribuição e Licença

* **Arquiteto Principal e Autor**: **Dasik (Rifaditya)**
* **Licença**: **GNU General Public License v3.0 (GPLv3)**
* **Repositório**: [Vanilla-Outsider-Biome-Scale](https://github.com/Rifaditya/Vanilla-Outsider-Biome-Scale)
