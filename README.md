[![Powered By Vaadin on Kotlin](http://vaadinonkotlin.eu/iconography/vok_badge.svg)](http://vaadinonkotlin.eu)
[![Build Status](https://github.com/mvysny/vok-helloworld-app/actions/workflows/gradle.yml/badge.svg)](https://github.com/mvysny/vok-helloworld-app/actions/workflows/gradle.yml)

# Vaadin-on-Kotlin Vaadin 14 Example App / Archetype

Template for a simple Vaadin-on-Kotlin Vaadin 14-based application that only
requires a Servlet 3.0 container to run. Just clone this repo and start building your awesome app!

You'll start with this app when you follow the [Getting Started tutorial](http://www.vaadinonkotlin.eu/gettingstarted-v10.html).

# Preparing Environment

The Vaadin 14 build requires node.js and npm. You can either use the Vaadin Gradle plugin to install nodejs+npm for
you automatically (recommended, you don't have to do anything), or you can install nodejs+npm to your OS:

* Windows: [node.js Download site](https://nodejs.org/en/download/) - use the .msi 64-bit installer
* Linux: `sudo apt install npm`

Also make sure that you have Java 8 (or higher) JDK installed.

# Getting Started

To quickly start the app, just type this into your terminal:

```bash
git clone https://github.com/mvysny/vok-helloworld-app
cd vok-helloworld-app
./gradlew build web:appRun
```

Gradle will automatically download an embedded servlet container (Jetty) and will run your app in it. Your app will be running on
[http://localhost:8080](http://localhost:8080).

Since the build system is a Gradle file written in Kotlin, we suggest you
use [Intellij IDEA](https://www.jetbrains.com/idea/download)
to edit the project files. The Community edition is enough to run the server
via Gretty's `./gradlew appRun`. The Ultimate edition will allow you to run the
project in Tomcat - this is the recommended
option for a real development.

## The 'complete' sources

You can switch the git branch from 'master' to ['complete'](../../tree/complete), to see the outcome application of the
[Vaadin-on-Kotlin Getting Started](http://www.vaadinonkotlin.eu/gettingstarted-v10.html) tutorial.

## Supported Modes

Runs in Vaadin 14 npm mode, using the [Vaadin Gradle Plugin](https://github.com/vaadin/vaadin-gradle-plugin).

Both the [development and production modes](https://vaadin.com/docs/v14/flow/production/tutorial-production-mode-basic.html) are supported.
To prepare for development mode, just run:

```bash
./gradlew clean vaadinPrepareFrontend
```

If you don't have node installed, you can use Vaadin plugin to download node.js for you:

```bash
./gradlew vaadinPrepareNode
```

To build in production mode, just run:

```bash
./gradlew clean build -Pvaadin.productionMode
```

If you don't have node installed in your CI environment, you can use Vaadin plugin to download node.js for you beforehand:

```bash
./gradlew clean vaadinPrepareNode build -Pvaadin.productionMode
```

# Workflow

To compile the entire project in production mode, run `./gradlew -Pvaadin.productionMode`.

To run the application in development mode, run `./gradlew appRun` and open [http://localhost:8080/](http://localhost:8080/).

To produce a deployable production-mode WAR:
- run `./gradlew -Pvaadin.productionMode`
- You will find the WAR file in `build/libs/*.war`
- To revert your environment back to development mode, just run `./gradlew` or `./gradlew vaadinPrepareFrontend`
  (omit the `-Pvaadin.productionMode`) switch.

This will allow you to quickly start the example app and allow you to do some basic modifications.

## Dissection of project files

Let's look at all files that this project is composed of, and what are the points where you'll add functionality:

| Files | Meaning
| ----- | -------
| [build.gradle.kts](build.gradle.kts), [settings.gradle.kts](settings.gradle.kts) | [Gradle](https://gradle.org/) build tool configuration files. Gradle is used to compile your app, download all dependency jars and build a war file
| [gradlew](gradlew), [gradlew.bat](gradlew.bat), [gradle/](gradle) | Gradle runtime files, so that you can build your app from command-line simply by running `./gradlew`, without having to download and install Gradle distribution yourself.
| [.travis.yml](.travis.yml) | Configuration file for [Travis-CI](http://travis-ci.org/) which tells Travis how to build the app. Travis watches your repo; it automatically builds your app and runs all the tests after every commit.
| [.gitignore](.gitignore) | Tells [Git](https://git-scm.com/) to ignore files that can be produced from your app's sources - be it files produced by Gradle, Intellij project files etc.
| [web/](web/) | The web Gradle module which will host the web application itself. You can add more Gradle modules as your project will grow. Visit the [web module docs](web/) for more documentation.

# Development with Intellij IDEA Ultimate

The easiest way (and the recommended way) to develop Karibu-DSL-based web applications is to use Intellij IDEA Ultimate.
It includes support for launching your project in any servlet container (Tomcat is recommended)
and allows you to debug the code, modify the code and hot-redeploy the code into the running Tomcat
instance, without having to restart Tomcat.

1. First, download Tomcat and register it into your IntelliJ IDEA properly:
   https://www.jetbrains.com/help/idea/configuring-and-managing-application-server-integration.html
2. Then just open this project in Intellij, simply by selecting `File / Open...`
   and click on the `build.gradle` file. When asked, select "Open as Project".
2. You can then create a launch configuration which will launch the `web` module as
   `exploded` in Tomcat with Intellij: just scroll to the end of this tutorial:
   https://kotlinlang.org/docs/tutorials/httpservlets.html
3. Start your newly created launch configuration in Debug mode. This way, you
   can modify the code and press `Ctrl+F9` to hot-redeploy the code. This only
   redeploys java code though, to redeploy resources just press `Ctrl+F10` and select "Update classes and resources"
