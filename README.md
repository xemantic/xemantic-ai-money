# xemantic-ai-money
Kotlin multiplatform library for real-time calculation of LLM usage costs

[<img alt="Maven Central Version" src="https://img.shields.io/maven-central/v/com.xemantic.ai/xemantic-ai-money">](https://central.sonatype.com/namespace/com.xemantic.ai)
[<img alt="GitHub Release Date" src="https://img.shields.io/github/release-date/xemantic/xemantic-ai-money">](https://github.com/xemantic/xemantic-ai-money/releases)
[<img alt="license" src="https://img.shields.io/github/license/xemantic/xemantic-ai-money?color=blue">](https://github.com/xemantic/xemantic-ai-money/blob/main/LICENSE)

[<img alt="GitHub Actions Workflow Status" src="https://img.shields.io/github/actions/workflow/status/xemantic/xemantic-ai-money/build-main.yml">](https://github.com/xemantic/xemantic-ai-money/actions/workflows/build-main.yml)
[<img alt="GitHub branch check runs" src="https://img.shields.io/github/check-runs/xemantic/xemantic-ai-money/main">](https://github.com/xemantic/xemantic-ai-money/actions/workflows/build-main.yml)
[<img alt="GitHub commits since latest release" src="https://img.shields.io/github/commits-since/xemantic/xemantic-ai-money/latest">](https://github.com/xemantic/xemantic-ai-money/commits/main/)
[<img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/xemantic/xemantic-ai-money">](https://github.com/xemantic/xemantic-ai-money/commits/main/)

[<img alt="GitHub contributors" src="https://img.shields.io/github/contributors/xemantic/xemantic-ai-money">](https://github.com/xemantic/xemantic-ai-money/graphs/contributors)
[<img alt="GitHub commit activity" src="https://img.shields.io/github/commit-activity/t/xemantic/xemantic-ai-money">](https://github.com/xemantic/xemantic-ai-money/commits/main/)
[<img alt="GitHub code size in bytes" src="https://img.shields.io/github/languages/code-size/xemantic/xemantic-ai-money">]()
[<img alt="GitHub Created At" src="https://img.shields.io/github/created-at/xemantic/xemantic-ai-money">](https://github.com/xemantic/xemantic-ai-money/commit/39c1fa4c138d4c671868c973e2ad37b262ae03c2)
[<img alt="kotlin version" src="https://img.shields.io/badge/dynamic/toml?url=https%3A%2F%2Fraw.githubusercontent.com%2Fxemantic%2Fxemantic-ai-money%2Fmain%2Fgradle%2Flibs.versions.toml&query=versions.kotlin&label=kotlin">](https://kotlinlang.org/docs/releases.html)

[<img alt="discord server" src="https://dcbadge.limes.pink/api/server/https://discord.gg/vQktqqN2Vn?style=flat">](https://discord.gg/vQktqqN2Vn)
[<img alt="discord users online" src="https://img.shields.io/discord/811561179280965673">](https://discord.gg/vQktqqN2Vn)
[<img alt="X (formerly Twitter) Follow" src="https://img.shields.io/twitter/follow/KazikPogoda">](https://x.com/KazikPogoda)

## Why?

The APIs of AI companies, like [OpenAI API](https://platform.openai.com/docs/api-reference/introduction)
and [Anthropic API](https://docs.anthropic.com/en/api/getting-started), are providing the usage information regarding input and output tokens
associated with each API call. Depending on many factors, like the model being used, batch
processing, involved cache, etc., these tokens can be billed according to particular rules.
This library is fulfilling the need of proper accounting of API usage by:

* representing monetary amounts as [Money](src/commonMain/kotlin/Money.kt) interface,
  supporting big decimal arithmetics and operator overloading,
* representing ratios of monetary amounts (e.g. input token cost for given model)
  as [Money.Ratio](src/commonMain/kotlin/Money.kt) interface.


> [!NOTE]
>  The `xemantic-ai-money` was initially a part of the
> [anthropic-sdk-kotlin](https://github.com/xemantic/anthropic-sdk-kotlin), but was eventually externalized,
> as a common functionality applicable across various API-related use cases.

## Usage

In `build.gradle.kts` add:

```kotlin
dependencies {
  implementation("com.xemantic.ai:xemantic-ai-money:0.2")
}
```

See [test cases](src/commonTest/kotlin) for further information.

## Big decimal arithmetics

The implementation of big decimal arithmetic in use will depend on the multiplatform
targets:

* `java.math.BigDecimal` for JVM target, for maximal performance and stability.
* [kotlin-multiplatform-bignum](https://github.com/ionspin/kotlin-multiplatform-bignum) for non-JVM targets.

## Development

Clone this repo and then in the project dir:

```shell
./gradlew build
```
