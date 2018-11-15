[![Powered By Vaadin on Kotlin](http://vaadinonkotlin.eu/iconography/vok_badge.svg)](http://vaadinonkotlin.eu)
[![Build Status](https://travis-ci.org/mvysny/vok-helloworld-app-v10.svg?branch=master)](https://travis-ci.org/mvysny/vok-helloworld-app-v10)

# Vaadin-on-Kotlin Vaadin 10 Example App / Archetype

Template for a simple Vaadin-on-Kotlin Vaadin 10-based application that only requires a Servlet 3.0 container to run.
Just clone this repo and start building your awesome app!

You'll start with this app when you follow the [Getting Started tutorial](http://www.vaadinonkotlin.eu/gettingstarted-v10.html).

# Getting Started

To quickly start the app, make sure that you have Java 8 JDK installed. Then, just type this into your terminal:

```bash
git clone https://github.com/mvysny/vok-helloworld-app-v10
cd vok-helloworld-app-v10
./gradlew build web:appRun
```

The app will be running on [http://localhost:8080/](http://localhost:8080/).

## The 'complete' sources

You can switch the git branch from 'master' to ['complete'](../../tree/complete), to see the outcome application of the
[Vaadin-on-Kotlin Getting Started](http://www.vaadinonkotlin.eu/gettingstarted-v10.html) tutorial.

## Running The App

To compile the entire project, run `./gradlew`.

To quickly run the app on your machine, just run the following from your terminal:

```bash
$ ./gradlew web:appRun
```

Gradle will automatically download an embedded servlet container (Jetty) and will run your app in it. Your app will be running on
[http://localhost:8080](http://localhost:8080).

## Dissection of project files

Let's look at all files that this project is composed of, and what are the points where you'll add functionality:

| Files | Meaning
| ----- | -------
| [build.gradle.kts](build.gradle.kts), [settings.gradle](settings.gradle) | [Gradle](https://gradle.org/) build tool configuration files. Gradle is used to compile your app, download all dependency jars and build a war file
| [gradlew](gradlew), [gradlew.bat](gradlew.bat), [gradle/](gradle) | Gradle runtime files, so that you can build your app from command-line simply by running `./gradlew`, without having to download and install Gradle distribution yourself.
| [.travis.yml](.travis.yml) | Configuration file for [Travis-CI](http://travis-ci.org/) which tells Travis how to build the app. Travis watches your repo; it automatically builds your app and runs all the tests after every commit.
| [.gitignore](.gitignore) | Tells [Git](https://git-scm.com/) to ignore files that can be produced from your app's sources - be it files produced by Gradle, Intellij project files etc.
| [web/](web/) | The web Gradle module which will host the web application itself. You can add more Gradle modules as your project will grow. Visit the [web module docs](web/) for more documentation.

# Development with Intellij IDEA Ultimate

The easiest way (and the recommended way) to develop Karibu-DSL-based web applications is to use Intellij IDEA Ultimate.
It includes support for launching your project in any servlet container (Tomcat is recommended)
and allows you to debug the code, modify the code and hot-redeploy the code into the running Tomcat
instance, without having to restart Tomcat.

1. First, download Tomcat and register it into your Intellij IDEA properly: https://www.jetbrains.com/help/idea/2017.1/defining-application-servers-in-intellij-idea.html
2. Then just open this project in Intellij, simply by selecting `File / Open...` and click on the
   `build.gradle` file. When asked, select "Open as Project".
2. You can then create a launch configuration which will launch the `web` module as `exploded` in Tomcat with Intellij: just
   scroll to the end of this tutorial: https://kotlinlang.org/docs/tutorials/httpservlets.html
3. Start your newly created launch configuration in Debug mode. This way, you can modify the code
   and press `Ctrl+F9` to hot-redeploy the code. This only redeploys java code though, to
   redeploy resources just press `Ctrl+F10` and select "Update classes and resources"
