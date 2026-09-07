# Minecraft 26.1 — Biome Scale 문서 포털

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> [!IMPORTANT]
> 📌 **저장소 소스 코드 면책 조항**: 이 위키의 문서는 CurseForge 및 Modrinth의 공개 릴리스 빌드보다 앞선 최근 미출시 커밋 또는 개발 기능을 포함할 수 있는 **저장소의 현재 소스 코드 상태**를 반영합니다.

---

## 🌲 개요: 마인크래프트 26.1용 Biome Scale

**마인크래프트 26.1**을 위한 **Vanilla Outsider: Biome Scale** 공식 문서 스위트에 오신 것을 환영합니다.

마인크래프트 26.1에서 월드 생성은 6차원 파라미터 공간의 `MultiNoiseBiomeSource` 기후 샘플러에 크게 의존합니다. Biome Scale은 이러한 좌표를 동적으로 가로채어 수평 노이즈 샘플링 공간을 설정 가능한 배율로 확장합니다.

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

## 📑 26.1 문서 서브시스템 매트릭스

| 서브시스템 페이지 | 대상 독자 | 다루는 핵심 주제 |
| :--- | :--- | :--- |
| **[[지형 및 노이즈 스케일링|ko_kr-26.1-Terrain-and-Noise-Scaling]]** | 🎮 플레이어 및 개발자 | 좌표 확장 방정식, 보로노이 셀 보존, GC 힙 할당 제로 프로파일. |
| **[[설정 및 게임 규칙|ko_kr-26.1-Configuration-and-GameRules]]** | 🎮 플레이어 및 서버 관리자 | `biome_scale:scale_modifier`, 동적 서버 틱 캐싱, `/gamerule` 명령어. |
| **[[아키텍처 및 Mixin|ko_kr-26.1-Architecture-and-Mixins]]** | 💻 개발자 및 모더 | Mixin 바이트코드 분석, 인젝션 디스크립터, DasikLibrary 통합, 제로 오버헤드. |

---

## 🚀 서버 관리자를 위한 빠른 시작 가이드

1. **의존성 설치**:
   - 서버에 26.1용 **Fabric Loader** 및 **Fabric API**가 설치되어 있는지 확인합니다.
   - **DasikLibrary**를 설치합니다.
2. **JAR 파일 배치**:
   - `vanilla-outsider-biome-scale-1.0.0+26.1.jar` 파일을 서버의 `mods/` 폴더에 넣습니다.
3. **바이옴 크기 구성**:
   - 기본 스케일 수정자는 **`200`**($2.0\times$ 확장을 나타냄)입니다:
     ```text
     /gamerule biome_scale:scale_modifier 200
     ```
   - 거대한 대륙형 월드($4.0\times$ 확장):
     ```text
     /gamerule biome_scale:scale_modifier 400
     ```
   - 빠르게 전환되는 소형 마이크로 바이옴($0.5\times$ 크기):
     ```text
     /gamerule biome_scale:scale_modifier 50
     ```

---

## 🧭 버전 네비게이션
* [[메인 포털로 돌아가기|ko_kr-Home]]
* [[버전 호환성 매트릭스|ko_kr-Version-Compatibility]]
* [[개발자 환경 설정 및 빌드 가이드|ko_kr-Developer-Setup-and-Building]]
* [[MC 26.3 위키로 전환|ko_kr-26.3-Home]]
