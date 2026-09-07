# 개발자 환경 설정 및 빌드 가이드

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **저장소 소스 코드 면책 조항**: 이 위키의 문서는 CurseForge 및 Modrinth의 공개 릴리스 빌드보다 앞선 최근 미출시 커밋 또는 개발 기능을 포함할 수 있는 **저장소의 현재 소스 코드 상태**를 반영합니다.

---

## 🛠️ 사전 요구사항 및 환경

**Vanilla Outsider: Biome Scale**에 기여하거나 직접 컴파일하려면 로컬 개발 환경이 다음 요구사항을 충족해야 합니다:

* **Java Development Kit (JDK)**: **JDK 25** (Eclipse Temurin 25 또는 OpenJDK 25 권장).
* **빌드 시스템**: Gradle (래퍼 스크립트 `./gradlew`가 저장소에 포함됨).
* **IDE**: IntelliJ IDEA 2025.3+ 또는 Minecraft Development 플러그인이 설치된 Eclipse.
* **Git**: Git 2.40+ 설치 및 구성 완료.

설치된 Java 버전 확인:
```bash
java -version
javac -version
```

---

## 📂 저장소 구조 및 다중 버전 구성

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

## 🏗️ 소스 코드에서 빌드하기

특정 마인크래프트 버전을 위한 프로덕션 JAR를 컴파일하려면 해당 버전의 하위 프로젝트 디렉터리에서 터미널을 엽니다:

### 마인크래프트 26.1용 빌드
```bash
# 26.1 앵커 디렉터리로 이동
cd "Biome Scale 26.1"

# 클린 빌드 및 리매핑 실행
./gradlew clean build --no-daemon
```

### 마인크래프트 26.3용 빌드
```bash
# 26.3 앵커 디렉터리로 이동
cd "Biome Scale v26.3/Biome Scale 26.3"

# 클린 빌드 및 리매핑 실행
./gradlew clean build --no-daemon
```

컴파일된 JAR 파일은 다음 경로에 생성됩니다:
```
build/libs/vanilla-outsider-biome-scale-1.0.0+26.1.jar
```
그리고 프로젝트의 로컬 아카이브 폴더로 자동 복사됩니다:
```
Archive Jar of all versions/MC 26.1.2/vanilla-outsider-biome-scale-1.0.0+26.1.jar
```

---

## 🧪 테스트 및 검증

자동화된 테스트 스위트 및 컴파일러 검사를 실행합니다:
```bash
./gradlew check --no-daemon
```

Gradle에서 테스트 클라이언트 또는 전용 서버를 직접 실행하려면:
```bash
# 테스트 클라이언트 실행
./gradlew runClient

# 테스트 전용 서버 실행
./gradlew runServer
```

---

## 📦 DasikLibrary 의존성 구성

Biome Scale은 동적 네임스페이스 게임 규칙 등록을 위해 **DasikLibrary**에 의존합니다. `build.gradle`에 선언된 Maven 저장소를 통해 해결됩니다:

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

## 🧭 네비게이션
* [[메인 포털로 돌아가기|ko_kr-Home]]
* [[버전 호환성|ko_kr-Version-Compatibility]]
* [[26.1 아키텍처 및 Mixin|ko_kr-26.1-Architecture-and-Mixins]]
* [[26.3 아키텍처 및 Mixin|ko_kr-26.3-Architecture-and-Mixins]]
