FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Lister les fichiers pour vérifier la copie
RUN ls -l /app

# Vérifier les dépendances Maven
RUN mvn dependency:resolve
#construire le projet
#RUN mvn clean install
RUN mvn -f pom.xml -Pprod clean package


FROM openjdk:17-alpine AS run
WORKDIR /app
COPY --from=build /app/target/*.jar  ./shopping-app-aws.jar
EXPOSE 8089
CMD ["java","-jar","/shopping-app-aws.jar"]