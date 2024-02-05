plugins {
	java
	id("org.springframework.boot") version "3.2.2"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "io.chagchagchag.project"
version = "0.0.1"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
}

dependencies {
	// jackson
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
	implementation("com.fasterxml.jackson.core:jackson-databind")
	// lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	// spring boot
	implementation("org.springframework.boot:spring-boot-starter")

//	// redisson (core에서 사용안할 것이긴 하지만, 아직 확신이 없기에 주석처리로 대체)
//	implementation("org.redisson:redisson-spring-boot-starter:3.16.4")

//	// querydsl (core에서 사용안할 것이긴 하지만, 아직 확신이 없기에 주석처리로 대체)
//	annotationProcessor("com.querydsl:querydsl-apt:5.0.0:jakarta")
//	annotationProcessor("jakarta.annotation:jakarta.annotation-api")
//	annotationProcessor("jakarta.persistence:jakarta.persistence-api")

	// database
	runtimeOnly("com.h2database:h2")
	runtimeOnly("com.mysql:mysql-connector-j")
	implementation("com.github.ben-manes.caffeine:caffeine")

	// test
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.getByName("bootJar"){
	enabled = false
}

tasks.getByName("jar"){
	enabled = true
}