plugins {
	java
	id("org.springframework.boot") version "3.2.2"
	id("io.spring.dependency-management") version "1.1.4"
	id("com.google.cloud.tools.jib") version "3.4.0"
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
	implementation(project(":coupon-core"))
	implementation(project(":coupon-dataaccess"))
	// jackson
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
	implementation("com.fasterxml.jackson.core:jackson-databind")
	// lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	// spring boot
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.redisson:redisson-spring-boot-starter:3.16.4")
	// querydsl
	annotationProcessor("com.querydsl:querydsl-apt:5.0.0:jakarta")
	annotationProcessor("jakarta.annotation:jakarta.annotation-api")
	annotationProcessor("jakarta.persistence:jakarta.persistence-api")
	// database
	runtimeOnly("com.h2database:h2")
	runtimeOnly("com.mysql:mysql-connector-j")
	implementation("com.github.ben-manes.caffeine:caffeine")
	// prometheus
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("io.micrometer:micrometer-registry-prometheus")

	// test
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

jib {
	from {
		image = "amazoncorretto:17"
	}
	to {
		// image 도 변수로 지정해서 동적으로 지정하고 싶다면 아래와 같이...
//		image = "${project.name}-${project.version.toString().toLowerCase()}"
		// 이번 예제는 아래처럼 정해진 이름은 정해진 이름으로 확실하게 별도 지정합니다.
		image = "chagchagchag/coupon-issuer"
		tags = setOf("0.0.1", "0.0.1.coupon-issuer.01", "latest")
	}
	container {
		creationTime = "USE_CURRENT_TIMESTAMP"

		// JVM 옵션들
		jvmFlags = listOf("-Dspring.profiles.active=local", "-XX:+UseContainerSupport", "-Dserver.port=8080", "-Dfile.encoding=UTF-8")

		// 컨테이너 입장에서 자기 자신을 외부로 노출할 포트입니다.
		ports = listOf("8080")
	}
}