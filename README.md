[![Powered By Vaadin on Kotlin](http://vaadinonkotlin.eu/iconography/vok_badge.svg)](http://vaadinonkotlin.eu)
[![Build Status](https://github.com/mvysny/vok-helloworld-app/actions/workflows/gradle.yml/badge.svg)](https://github.com/mvysny/vok-helloworld-app/actions/workflows/gradle.yml)

# BoltShop — VoK Getting Started

This is the starting point for the [Vaadin-on-Kotlin Getting Started tutorial](https://www.vaadinonkotlin.eu/tutorial/).
Across the tutorial chapters you'll build **BoltShop**: a back-office product catalog screen
for a small neighborhood hardware store. The finished app is a single-page master-detail
view with a filterable `Grid<Product>`, edit-in-place form, add dialog, low-stock badge,
Karibu-Testing coverage, and a small REST API.

Out of the box `master` is just the framework wired up — Vaadin Boot, Karibu-DSL,
H2 + Flyway + ktorm (via `vok-db`), and a minimal `WelcomeView`. No entities, no
migrations, no domain code yet. You build all of it as you work through the chapters.

Requires Java 21+.

# Build & run

Standard [Vaadin Boot](https://github.com/mvysny/vaadin-boot) project:

- `./gradlew run` — dev mode, embedded Jetty
- `./gradlew build` — full build incl. production bundle
- `./gradlew test` — JUnit 5 tests

Main class: `com.example.vok.MainKt`.
