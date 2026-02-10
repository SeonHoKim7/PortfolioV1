# 1단계: 빌드용 이미지 (Gradle 사용)
FROM gradle:7.6-jdk17 AS build
WORKDIR /app
COPY . .
# gradlew에 실행 권한을 주고 빌드 시작
RUN chmod +x ./gradlew
RUN ./gradlew clean build -x test

# 2단계: 실행용 이미지 (더 확실한 자바 17 이미지로 교체)
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
# 빌드 단계에서 생성된 jar 파일을 복사
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]