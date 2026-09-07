# 开发者环境配置与构建指南

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **仓库源码免责声明**：本 Wiki 中的文档反映了**仓库中的当前源代码状态**，可能包含领先于 CurseForge 和 Modrinth 上公开版本的近期未发布提交或开发特性。

---

## 🛠️ 前置条件与环境要求

若要参与贡献或编译 **Vanilla Outsider: Biome Scale**，请确保你的本地开发环境满足以下要求：

* **Java 开发工具包 (JDK)**: **JDK 25**（推荐 Eclipse Temurin 25 或 OpenJDK 25）。
* **构建系统**: Gradle（代码仓库已内置 Gradle Wrapper 脚本 `./gradlew`）。
* **集成开发环境 (IDE)**: IntelliJ IDEA 2025.3+ 或装有 Minecraft Development 插件的 Eclipse。
* **版本控制**: Git 2.40+ 已安装并配置。

验证当前安装的 Java 版本：
```bash
java -version
javac -version
```

---

## 📂 代码仓库布局与多版本工程结构

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

## 🏗️ 从源码编译构建

要编译针对特定 Minecraft 版本的正式生产 JAR 包，请在该版本对应的子工程目录中打开终端：

### 为 Minecraft 26.1 构建
```bash
# 切换至 26.1 锚点目录
cd "Biome Scale 26.1"

# 执行清理构建与重映射
./gradlew clean build --no-daemon
```

### 为 Minecraft 26.3 构建
```bash
# 切换至 26.3 锚点目录
cd "Biome Scale v26.3/Biome Scale 26.3"

# 执行清理构建与重映射
./gradlew clean build --no-daemon
```

编译生成的 JAR 制品位于：
```
build/libs/vanilla-outsider-biome-scale-1.0.0+26.1.jar
```
并自动镜像归档至本项目的版本归档文件夹：
```
Archive Jar of all versions/MC 26.1.2/vanilla-outsider-biome-scale-1.0.0+26.1.jar
```

---

## 🧪 测试与验证

运行自动化测试套件与编译器检查：
```bash
./gradlew check --no-daemon
```

直接通过 Gradle 启动测试 Minecraft 客户端或服务端：
```bash
# 启动测试客户端
./gradlew runClient

# 启动测试专用服务端
./gradlew runServer
```

---

## 📦 DasikLibrary 依赖配置

Biome Scale 依赖 **DasikLibrary** 实现动态命名空间游戏规则注册。DasikLibrary 通过 `build.gradle` 中声明的 Maven 仓库解析：

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

## 🧭 页面导航
* [[返回主门户|zh_cn-Home]]
* [[版本兼容性|zh_cn-Version-Compatibility]]
* [[26.1 架构与 Mixin|zh_cn-26.1-Architecture-and-Mixins]]
* [[26.3 架构与 Mixin|zh_cn-26.3-Architecture-and-Mixins]]
