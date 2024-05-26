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

jib {
	from {
		image = "amazoncorretto:17"
	}
	to {
		// image 도 변수로 지정해서 동적으로 지정하고 싶다면 아래와 같이...
//		image = "${project.name}-${project.version.toString().toLowerCase()}"
		// 이번 예제는 아래처럼 정해진 이름은 정해진 이름으로 확실하게 별도 지정합니다.
		image = "chagchagchag/coupon-core"
		tags = setOf("0.0.1", "0.0.1.coupon-core.01", "latest")
	}
	container {
		creationTime = "USE_CURRENT_TIMESTAMP"

		// JVM 옵션들
		// 서버 포트로는 8080을 사용했으며, profile로는 local을 선택했다.
		jvmFlags = listOf("-Dspring.profiles.active=local", "-XX:+UseContainerSupport", "-Dserver.port=8080", "-Dfile.encoding=UTF-8")

		// 컨테이너 입장에서 자기 자신을 외부로 노출할 포트입니다.
		ports = listOf("8080")
	}
}