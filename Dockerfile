FROM eclipse-temurin:17-jre

COPY target/solutions-engine-auth-proxy-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app

ENTRYPOINT ["java", "-jar", "app.jar"]