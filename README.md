Build Tools With Gradle
===================
## Class Assignment 2 - Part 2

## *Alternative Solution*

For this assignment the students were asked to select an alternative technological solution to Errai/GWT, to which they should implement **Gradle**.

The alternative solution selected for this project was **Vue.js** for the client and **Spring Boot** as the resource server.

This project consists of a simple Todo list and was created based on this tutorial: https://developer.okta.com/blog/2018/11/20/build-crud-spring-and-vue

## Prerequisites

 * Java JDK 8
 * Gradle
 * Spring Boot
 * Vue.js
 * Node.js
 * Yarn 

## Vue.js

**Vue.js** is an open-source JavaScript framework for building user interfaces and single-page applications. 

Vue.js features an incrementally adoptable architecture that focuses on declarative rendering and component composition.

This framework was released in 2014, and was developed by Evan You after working for Google using AngularJS in a number of projects.

## Spring Boot

**Spring Boot** is an open source Java-based framework, often used to create a micro Services. It is developed by Pivotal Team and is used to build stand-alone and production ready spring applications.

Spring Boot provides a good platform for Java developers to develop a stand-alone and production-grade spring application that you can *just run*. You can get started with minimum configurations without the need for an entire Spring configuration setup.

## Spring Boot Gradle Plugin

The Spring Boot Gradle Plugin provides Spring Boot support in Gradle, allowing you to package executable jar or war archives, run Spring Boot applications, and use the dependency management provided by `spring-boot-dependencies`. Spring Boot’s Gradle plugin requires Gradle 4.4 or later.

## Starting the project

The Spring Boot app was created using [Spring Initializer](https://start.spring.io/) with the following selections:

* Project Type:  `Gradle Project`
* Dependencies: `JPA`, `H2`, `Web`, `Rest Repositories`, `Lombok`

The Spring Boot app was created containing the followig `build.gradle` file:

```groovy
plugins {
	id 'org.springframework.boot' version '2.1.3.RELEASE'
	id 'java'
}

apply plugin: 'io.spring.dependency-management'

group = 'com.example'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '1.8'

configurations {
	compileOnly {
		extendsFrom annotationProcessor
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.springframework.boot:spring-boot-starter-data-rest'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	compileOnly 'org.projectlombok:lombok'
	runtimeOnly 'com.h2database:h2'
	annotationProcessor 'org.projectlombok:lombok'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
```

As you can see, the Spring Boot Gradle plugin is applied:

```groovy
plugins {
	id 'org.springframework.boot' version '2.1.3.RELEASE'
    //more code
}
```

## Javadoc Generation
In a Spring Boot application, in order to generate Javadoc pages, you can use:

```
./gradlew javadoc
```

## Render PlantUML

To create the custom task to render the PlantUML diagrams, it was implemented a similar scenario to those of the Class Assignment 2 - Part 2 projects (See README [here](https://bitbucket.org/dianagsilva/devops-18-19-atb-1181702/src/master/CA2_Part2/README.md)).

A `builSrc` class was added to the project with a custom task type `RunPlantUmlTask` that was used to add the task `renderPlantUml` in the `build.gradle` file:

```groovy
task renderPlantUml(type: RenderPlantUmlTask) {
}
```

The task can be run using:

```
./gradlew renderPlantUml
```

## Run the Todo application

To run the Spring Boot server use:

```
./gradlew bootRun
```

After that, go to the **client** folder root and use a terminal to run:

```
yarn serve
```

After that you can access the application by using the following url: http://localhost:8080. 