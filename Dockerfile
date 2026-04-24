# ==================== ETAP BUDOWANIA ====================
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# Kopiujemy cały projekt
COPY . .

# Budujemy tylko module3 (najwydajniej)
RUN mvn clean package -DskipTests -pl module3 -am

# ==================== ETAP PRODUKCYJNY ====================
FROM eclipse-temurin:21-jre
WORKDIR /app

# Kopiujemy jar z właściwej lokalizacji
COPY --from=build /app/module3/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]