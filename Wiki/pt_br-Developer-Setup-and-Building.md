# Guia de Configuração e Compilação para Desenvolvedores

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **Isenção de Responsabilidade do Código-Fonte do Repositório**: A documentação nesta Wiki reflete o **estado atual do código-fonte no repositório**, que pode incluir commits recentes não lançados ou recursos de desenvolvimento à frente das versões públicas no CurseForge e Modrinth.

---

## 🛠️ Pré-requisitos e Ambiente

Para contribuir ou compilar o **Vanilla Outsider: Biome Scale**, certifique-se de que seu ambiente local satisfaça os seguintes pré-requisitos:

* **Kit de Desenvolvimento Java (JDK)**: **JDK 25** (Eclipse Temurin 25 ou OpenJDK 25 recomendado).
* **Sistema de Compilação**: Gradle (o script wrapper `./gradlew` está incluso no repositório).
* **IDE**: IntelliJ IDEA 2025.3+ ou Eclipse com o plugin Minecraft Development.
* **Git**: Git 2.40+ instalado e configurado.

Verifique sua versão instalada do Java:
```bash
java -version
javac -version
```

---

## 📂 Estrutura do Repositório e Organização Multi-Versão

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

## 🏗️ Compilando a partir do Código-Fonte

Para compilar o JAR de produção de uma versão específica do Minecraft, abra um terminal no subdiretório dessa versão:

### Compilando para Minecraft 26.1
```bash
# Navegue até o diretório 26.1
cd "Biome Scale 26.1"

# Execute a compilação limpa e o remapeamento
./gradlew clean build --no-daemon
```

### Compilando para Minecraft 26.3
```bash
# Navegue até o diretório 26.3
cd "Biome Scale v26.3/Biome Scale 26.3"

# Execute a compilação limpa e o remapeamento
./gradlew clean build --no-daemon
```

Os JARs compilados serão gerados em:
```
build/libs/vanilla-outsider-biome-scale-1.0.0+26.1.jar
```
e espelhados automaticamente na pasta de arquivos do projeto:
```
Archive Jar of all versions/MC 26.1.2/vanilla-outsider-biome-scale-1.0.0+26.1.jar
```

---

## 🧪 Testes e Verificação

Execute suítes de testes automatizados e verificações de compilador usando:
```bash
./gradlew check --no-daemon
```

Para iniciar um cliente ou servidor dedicado de teste diretamente pelo Gradle:
```bash
# Iniciar cliente de teste
./gradlew runClient

# Iniciar servidor de teste
./gradlew runServer
```

---

## 📦 Configuração de Dependência da DasikLibrary

O Biome Scale depende da **DasikLibrary** para o registro dinâmico de GameRules com namespace. A DasikLibrary é resolvida via repositórios Maven no `build.gradle`:

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

## 🧭 Navegação
* [[Voltar ao Portal Principal|pt_br-Home]]
* [[Compatibilidade de Versões|pt_br-Version-Compatibility]]
* [[26.1 Arquitetura e Mixins|pt_br-26.1-Architecture-and-Mixins]]
* [[26.3 Arquitetura e Mixins|pt_br-26.3-Architecture-and-Mixins]]
