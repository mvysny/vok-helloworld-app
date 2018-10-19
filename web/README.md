# The Web module

This Gradle module hosts your web application and is able to build a WAR file which
you can run in your favourite Servlet container.

## Dissection of project files

Let's look at all files that this project is composed of, and what are the points where you'll add functionality:

| Files | Meaning
| ----- | -------
| [build.gradle.kts](build.gradle.kts) | [Gradle](https://gradle.org/) build tool configuration files. Gradle is used to compile your app, download all dependency jars and build a war file
| [src/main/resources/](src/main/resources) | A bunch of static files not compiled by Kotlin in any way; see below for explanation.
| [logback.xml](src/main/resources/logback.xml) | We're using [Slf4j](https://www.slf4j.org/) for logging and this is the configuration file for Slf4j
| [db/migration/](src/main/resources/db/migration) | Database upgrade instructions for the [Flyway](https://flywaydb.org/) framework. Database is upgraded on every server boot, to ensure it's always up-to-date. See the [Migration Naming Guide](https://flywaydb.org/documentation/migrations#naming) for more details.
| [webapp/](src/main/webapp) | static files provided as-is to the browser. Includes the image of Chuck Norris
| [src/main/kotlin/](src/main/kotlin) | The main Kotlin sources of your web app. You'll be mostly editing files located in this folder.
| [Bootstrap.kt](src/main/kotlin/com/example/vok/Bootstrap.kt) | When Servlet Container (such as Tomcat) starts your app, it will run the `Bootstrap.contextInitialized()` function before any calls to your app are made. We need to bootstrap the Vaadin-on-Kotlin framework, in order to have support for the database; then we'll run Flyway migration scripts, to make sure that the database is up-to-date. After that's done, your app is ready to be serving client browsers.
| [WelcomeView.kt](src/main/kotlin/com/example/vok/WelcomeView.kt) | The view, shown when the user browses the root page. Contains a happy picture of Chuck Norris.
| [Utils.kt](src/main/kotlin/com/example/vok/Utils.kt) | Utility stuff - a simple yes-no dialog which may come handly.
