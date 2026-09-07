# Настройка среды разработчика и руководство по сборке

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **Отказ от ответственности за исходный код репозитория**: Документация в этой Wiki отражает **текущее состояние исходного кода в репозитории**, которое может включать недавние невыпущенные коммиты или разрабатываемые функции, опережающие общедоступные сборки на CurseForge и Modrinth.

---

## 🛠️ Предварительные требования и среда

Для участия в разработке или самостоятельной сборки **Vanilla Outsider: Biome Scale** убедитесь, что локальное окружение соответствует требованиям:

* **Java Development Kit (JDK)**: **JDK 25** (рекомендуется Eclipse Temurin 25 или OpenJDK 25).
* **Система сборки**: Gradle (скрипт-обертка `./gradlew` включен в репозиторий).
* **IDE**: IntelliJ IDEA 2025.3+ или Eclipse с плагином Minecraft Development.
* **Git**: Установленный и настроенный Git 2.40+.

Проверьте установленную версию Java:
```bash
java -version
javac -version
```

---

## 📂 Структура репозитория и организация мульти-версионности

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

## 🏗️ Сборка из исходного кода

Чтобы скомпилировать рабочий JAR для конкретной версии Minecraft, откройте терминал в поддиректории соответствующего якоря:

### Сборка для Minecraft 26.1
```bash
# Перейдите в каталог версии 26.1
cd "Biome Scale 26.1"

# Выполните чистую сборку и ремаппинг
./gradlew clean build --no-daemon
```

### Сборка для Minecraft 26.3
```bash
# Перейдите в каталог версии 26.3
cd "Biome Scale v26.3/Biome Scale 26.3"

# Выполните чистую сборку и ремаппинг
./gradlew clean build --no-daemon
```

Скомпилированные JAR-файлы создаются в:
```
build/libs/vanilla-outsider-biome-scale-1.0.0+26.1.jar
```
и автоматически дублируются в архивную папку проекта:
```
Archive Jar of all versions/MC 26.1.2/vanilla-outsider-biome-scale-1.0.0+26.1.jar
```

---

## 🧪 Тестирование и проверка

Запустите автоматические тесты и проверки компилятора:
```bash
./gradlew check --no-daemon
```

Запуск тестового клиента Minecraft или выделенного сервера напрямую через Gradle:
```bash
# Запустить тестовый клиент
./gradlew runClient

# Запустить тестовый сервер
./gradlew runServer
```

---

## 📦 Конфигурация зависимости DasikLibrary

Biome Scale использует **DasikLibrary** для регистрации динамических GameRules с пространством имен. DasikLibrary подключается через репозитории Maven в `build.gradle`:

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

## 🧭 Навигация
* [[Назад к главному порталу|ru_ru-Home]]
* [[Совместимость версий|ru_ru-Version-Compatibility]]
* [[26.1 Архитектура и Mixin|ru_ru-26.1-Architecture-and-Mixins]]
* [[26.3 Архитектура и Mixin|ru_ru-26.3-Architecture-and-Mixins]]
