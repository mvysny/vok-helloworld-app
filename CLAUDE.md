# vok-helloworld-app

A Vaadin-on-Kotlin (VoK) example/archetype app, originally the starting point for the
[VoK Getting Started tutorial](https://www.vaadinonkotlin.eu/tutorial/). The tutorial
source lives at `../vaadin-on-kotlin/docs/tutorial.md`.

## Branches

- **`master`** — skeletal starter: a single `WelcomeView` ([src/main/kotlin/com/example/vok/WelcomeView.kt](src/main/kotlin/com/example/vok/WelcomeView.kt)),
  Vaadin Boot bootstrap, H2 + Flyway, an empty Javalin REST servlet. No domain logic.
- **`complete`** — the finished tutorial app (a CRUD blog/article app modeled after the
  Ruby-on-Rails "Getting Started" tutorial).

## Stack

- Vaadin Flow on Vaadin Boot (embedded Jetty, `main()` entry point — no Spring)
- Kotlin 2.2, JVM 17
- Karibu-DSL for UI, Karibu-Tools utilities
- H2 in-memory + Flyway migrations, HikariCP
- JDBI-ORM via `vok-db` (see [build.gradle.kts](build.gradle.kts))
- Javalin for REST (`vok-rest`)
- Logging: SLF4J Simple

## Known problems with this app (the reason we're rethinking it)

1. **Outdated persistence stack.** The original tutorial uses `vok-orm`, which has been
   removed from Vaadin-on-Kotlin and replaced by **Ktorm**. The current `master` has
   already moved to `vok-db` (JDBI-ORM), but the `complete` branch and the tutorial
   prose still reflect the old vok-orm world.
2. **Wrong app shape.** The tutorial is a near-direct port of the Rails "Getting Started"
   blog app. That shape fits Rails' multi-page, request/response model. Vaadin apps are
   **single-page apps with stateful server-side components** — a multi-page CRUD-of-
   articles-and-comments example doesn't showcase what Vaadin is actually good at and
   teaches readers patterns that fight the framework.

## What we want to do

Design a **better example app** for the VoK tutorial — one that genuinely showcases
Vaadin's SPA nature. Open questions / directions to research:

- Look at what Vaadin's own docs / starters use as canonical examples (e.g. Bakery,
  Beverage Buddy, Walking Skeleton, Hilla CRM tutorial).
- A **master-detail CRUD** with live filtering, inline editing, and a side panel for
  the selected row — uses Grid, Binder, dialogs, things Vaadin shines at.
- A small dashboard with live-updating charts / KPIs (push, broadcaster) to demonstrate
  server-driven UI.
- Multi-view app with a layout shell, navigation, and a couple of related entities so
  Karibu-DSL routing and `Grid` + form patterns get exercised.

No decision yet — this is a brainstorming phase. Suggestions welcome before implementation.

## Build & run

Standard Vaadin Boot project — see the [Vaadin Boot README](https://github.com/mvysny/vaadin-boot)
for the canonical commands. Quick reference:

- `./gradlew run` — dev mode
- `./gradlew build` — full build incl. production bundle
- `./gradlew test` — JUnit 5 tests (none currently in `master`)

Main class: `com.example.vok.MainKt`.

## Conventions

- Follow the project-type skill autoloaded by `~/.claude/CLAUDE.md` for Gradle work.
- When proposing dependency upgrades, verify versions against Maven Central via the
  `maven-tools` MCP — JUnit 6 GA exists, etc.
- Karibu-Testing is available for browserless UI tests if/when we add them.
