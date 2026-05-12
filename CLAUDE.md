# vok-helloworld-app

A Vaadin-on-Kotlin (VoK) example/archetype app, originally the starting point for the
[VoK Getting Started tutorial](https://www.vaadinonkotlin.eu/tutorial/). The tutorial
source lives at `../vaadin-on-kotlin/docs/tutorial.md`.

## Branches

- **`master`** — the tutorial's **starting point**, and currently *is* [Chapter 0](#chapter-0--starting-point-on-master).
- **`complete`** — the tutorial's **ending point**. Branch does not yet exist; it will
  be rebuilt branch-by-chapter on top of `master` (strategy TBD when we start writing
  chapters). The previous tutorial's Rails-port `complete` tip is preserved as the
  `legacy-rails-tutorial` tag for historical reference.

## Stack

- Vaadin Flow on Vaadin Boot (embedded Jetty, `main()` entry point — no Spring)
- Kotlin 2.3, JVM 21 (VoK 0.19.0 requires Java 21)
- Karibu-DSL for UI, Karibu-Tools utilities
- H2 in-memory + Flyway migrations, HikariCP
- **Ktorm** via `vok-db` (= `eu.vaadinonkotlin:vok-framework-vokdb`, which depends on
  `ktorm-vaadin`). Wired in `Bootstrap.kt` via
  `VaadinOnKotlin.dataSource = HikariDataSource(...)` (extension property from
  `eu.vaadinonkotlin.vaadin.vokdb`).
- Javalin for REST (`vok-rest`)
- Logging: SLF4J Simple

## Why we're rewriting the tutorial

The previous tutorial was a near-direct port of the Rails "Getting Started" blog app.
That shape fits Rails' multi-page, request/response model. Vaadin apps are
**single-page apps with stateful server-side components** — a multi-page CRUD-of-
articles-and-comments example doesn't showcase what Vaadin is actually good at and
teaches readers patterns that fight the framework. BoltShop (below) is the replacement.

## The new tutorial app: **BoltShop**

A small neighborhood hardware store with an online product catalog and a Click &
Collect workflow. The customer browses, picks items, collects them in store.
The tutorial builds the **back-office catalog screen** the shopkeeper uses.

### Why this domain

- Universally relatable — everyone has bought a screw or a tin of paint.
- Distinct from existing VoK examples (no overlap with beverage-buddy, bookstore,
  task-list, security-demo).
- Distinct from Vaadin's official "Click & Collect" tutorial domain (which is a
  generic product catalog) — same *shape*, fresh *flavor*.
- The shape is **the canonical Vaadin SPA shape**: master-detail in a single view,
  side form, add dialog, reactive filters. Same pedagogical sequence Vaadin's own
  Getting Started uses.

### Entity (start with one)

`Product`:
- `sku` — string, e.g. `HX-M6-40`
- `name` — string, e.g. *"Hex bolt M6×40mm, zinc-plated"*
- `category` — enum: `Tools`, `Fasteners`, `Plumbing`, `Electrical`, `Paint`, `Garden`
- `price` — `BigDecimal`
- `stock` — int (current count on hand)
- `unit` — enum: `Each`, `Box`, `Meter`, `Kilogram`

### Single SPA screen — master-detail

- **Left half**: `Grid<Product>` with
  - search `TextField` over name/SKU (live filter)
  - `ComboBox<Category>` category filter
  - "Low stock only" `Checkbox` toggle
  - custom cell renderer: red **"Low stock"** badge when `stock < threshold`
- **Right half**: details panel for selected row
  - edit-in-place via `Binder<Product>`
  - Save / Delete buttons
- **`+ Add product`** button — opens a `Dialog` reusing the same form
- Karibu-Testing covers the whole screen browserless

### What this exercises (per Vaadin core)

- `Grid` with custom renderers, sorting, in-memory or DB-backed data provider
- `Binder` (forms, validation, save/restore)
- `Dialog` for add
- Reactive filter wiring — typing updates Grid live (the thing a multi-page app can't
  do cleanly)
- Karibu-DSL idiomatic layout

### Out of scope (for now)

- Customer-facing storefront, cart, checkout — this is the back-office only.
- Product images (defer until we have a clean story for asset handling).
- Authentication — the existing `vok-security-demo` already covers that.

## Tutorial chapter arc (`master` → `complete`)

**One new concept per chapter.** Each chapter ends with a runnable, demo-able app.
Tests are intentionally deferred to a single dedicated chapter at the end — the goal
in early chapters is *learning momentum*, not test discipline.

### Chapter 0 — starting point on `master`

What learners get when they clone the repo:

- Vaadin Boot wired (`./gradlew run` boots an empty app)
- H2 + Flyway + ktorm (via `vok-db`) on the classpath, **no migrations, no entities**
- A minimal `WelcomeView` rendering *"Welcome to BoltShop"*
- `Bootstrap.kt` initializes ktorm via the `VaadinOnKotlin.dataSource` extension
- REST scaffold (`JavalinRestServlet`) left in place — unused until Chapter 11
- `README.md` describing BoltShop and linking to the tutorial

So `master` is *"the framework starts; nothing domain-specific exists yet."*
Everything BoltShop is built by the learner.

### Chapter list

| # | Title | New concept | What the learner builds |
|---|---|---|---|
| 1 | Hello, Karibu-DSL | Building UI in Kotlin with Karibu-DSL; event handlers | Replace `WelcomeView` with a greeting form: `TextField` + `Button` → shows a `Notification` |
| 2 | A Grid of products (in-memory) | `Grid` component, columns, in-memory data | `Product` data class; `Grid<Product>` from a hardcoded `listOf(...)` of 5–10 items |
| 3 | Persisting to the database | Flyway migration + ktorm entity + finder. **Heaviest chapter** — call this out in prose. | `V1__create_product.sql`; make `Product` a ktorm entity; replace hardcoded list with `Product.findAll()` |
| 4 | Live filtering | `DataProvider`, reactive server-side filter | Search `TextField` above Grid; refreshes data provider on each keystroke (search by name/SKU) |
| 5 | Category filter | `ComboBox`, enums in UI | `Category` enum + `ComboBox` filter; combines with the search field |
| 6 | Editing the selected product | Side panel + `Binder` (two-way data binding) | Right-hand panel shows form for selected row; Save persists |
| 7 | Adding new products | `Dialog`, extracting a reusable form component | `+ Add` button opens a dialog reusing the form `KComposite` |
| 8 | Validation | `Binder` validators, inline error messages | Required name, price > 0, stock ≥ 0, SKU format regex |
| 9 | Custom cell rendering | `ComponentRenderer` / themed badges | Red "Low stock" badge when `stock < threshold`; "Low stock only" toggle |
| 10 | Browserless tests | Karibu-Testing, `MockVaadin`, `_get`/`_click`/`_setValue` | Tests covering filter, edit, add, validation |
| 11 | Exposing a REST API | Wiring `vok-rest` / Javalin; hands-on `curl` | `GET /api/products`, `GET /api/products/{sku}`, `POST /api/products`. Chapter must end with literal `curl` examples the reader pastes into their terminal. |

### Ending point on `complete`

The full BoltShop back-office screen: filterable Grid (search + category +
low-stock toggle), master-detail with `Binder` and validation, add dialog, low-stock
badge, Karibu-Testing coverage, REST endpoint with documented `curl` examples.

## Beyond `complete` — possible follow-up tutorials

Out of scope for the main tutorial; candidates for later parts. Each adds one concept
without rewriting earlier code:

1. Promote `Category` from enum to its own entity → second route + admin screen →
   introduces routing & `MainLayout`.
2. Add `Supplier` with FK to `Product` → first **ktorm join** demonstration.
3. Vaadin Push — broadcast stock changes to all open sessions so two browser tabs
   see live updates.

## Build & run

Standard Vaadin Boot project — see the [Vaadin Boot README](https://github.com/mvysny/vaadin-boot)
for the canonical commands. Quick reference:

- `./gradlew run` — dev mode
- `./gradlew build` — full build incl. production bundle
- `./gradlew test` — JUnit 6 tests (none currently in `master`)

Main class: `com.example.vok.MainKt`.

## Conventions

- Follow the project-type skill autoloaded by `~/.claude/CLAUDE.md` for Gradle work.
- When proposing dependency upgrades, verify versions against Maven Central via the
  `maven-tools` MCP — JUnit 6 GA exists, etc.
- Karibu-Testing will be the test stack from Chapter 10 onwards. **Caveat:** as of the
  Vaadin 25.1.5 bump, there is no `karibu-testing-v25` artifact on Maven Central yet —
  the `kaributesting = "...:karibu-testing-v24:2.7.0"` entry in `libs.versions.toml` is
  dead config (not wired to any `testImplementation`) and needs to be resolved before
  Chapter 10 lands.
