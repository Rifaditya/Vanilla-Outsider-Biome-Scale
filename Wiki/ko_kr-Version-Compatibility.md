# 버전 호환성 및 라이프사이클 매트릭스

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **저장소 소스 코드 면책 조항**: 이 위키의 문서는 CurseForge 및 Modrinth의 공개 릴리스 빌드보다 앞선 최근 미출시 커밋 또는 개발 기능을 포함할 수 있는 **저장소의 현재 소스 코드 상태**를 반영합니다.

---

## 📊 마인크래프트 버전 호환성 매트릭스

**Vanilla Outsider: Biome Scale**은 엄격한 **1 Jar 1 Version** 엔지니어링 정책에 따라 최신 마인크래프트 릴리스 전반에서 지속적으로 동기화 유지 관리됩니다.

| 마인크래프트 버전 | 세대 구분 | 빌드 마일스톤 | Fabric Loader | Fabric API | Java 런타임 | DasikLibrary 버전 | 지원 상태 |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **26.1.2** | 모던 표준 | `1.0.0+26.1` | `>=0.19.1` | `0.145.4+26.1.2` | Java 25 | `1.6.9+build.24` | 🟢 **활성 장기 지원 (LTS)** |
| **26.3-snapshot-6** | 최신 스냅샷 | `1.0.0+26.3` | `>=0.19.3` | `0.156.1+26.3` | Java 25 | `1.8.36` | 🟡 **패리티 아카이브 릴리스** |

---

## 🌐 실행 환경 및 사이드 안전성 분포

| 환경 | 지원 여부 | 요구사항 및 동작 |
| :--- | :--- | :--- |
| **전용 서버 (Dedicated Server)** | ✅ **완전 지원** | **월드 생성에 필수.** 모든 청크 좌표 리스케일링은 월드 생성 및 청크 생성 중 서버 측에서 수행됩니다. |
| **싱글플레이 (통합 서버)** | ✅ **완전 지원** | 내부 통합 서버 스레드에서 실행되며 채팅창의 `/gamerule`을 통해 실시간으로 완벽 제어 가능. |
| **바닐라 클라이언트 접속** | ✅ **바닐라 클라이언트 호환** | **모드를 설치하지 않은 순수 바닐라 클라이언트도 바로 접속 가능!** 바이옴 데이터는 클라이언트로 전송되는 표준 바닐라 청크 패킷 팔레트에 인코딩됩니다. |

---

## 🧩 타사 월드 생성 및 바이옴 모드 호환성

Vanilla Outsider: Biome Scale은 바이옴 조회가 수행되기 *전*에 좌표 샘플링 계층(`MultiNoiseBiomeSource`)에서 수학적으로 직접 개입하므로 외부 월드 생성 모드와 오버헤드 없는 완벽한 네이티브 호환성을 제공합니다:

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

### 검증된 호환 월드 생성 애드온
1. **Terralith**: 바닐라 동굴 구조를 그대로 보존하면서 커스텀 매크로 바이옴을 매끄럽게 확장.
2. **Biomes O' Plenty**: ID 충돌 없이 커스텀 지표면 바이옴을 정확하게 스케일링.
3. **Regions Unexplored**: 모든 커스텀 지역 파라미터 전반에서 완벽하게 호환.
4. **커스텀 월드 생성 데이터팩**: 바닐라 `minecraft:multi_noise` 바이옴 소스를 사용하는 모든 데이터팩이 자동으로 스케일링됩니다.

---

## 🛠️ Java 런타임 환경 (JRE) 요구사항

마인크래프트 26.1 및 26.3 빌드 아티팩트는 모두 **Java 25 (LTS)**를 요구합니다. Java 21 이하 버전에서 실행하려고 하면 즉시 JVM 클래스로더 바이트코드 비호환 경고가 발생합니다:

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

## 🧭 네비게이션
* [[메인 포털로 돌아가기|ko_kr-Home]]
* [[개발자 환경 설정 및 빌드 가이드|ko_kr-Developer-Setup-and-Building]]
* [[MC 26.1 개요|ko_kr-26.1-Home]]
* [[MC 26.3 개요|ko_kr-26.3-Home]]
