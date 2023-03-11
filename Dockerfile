#############################################################
# Development environment                                   #
#############################################################
FROM maven:3.9-eclipse-temurin-17-alpine as development

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

EXPOSE 8080 8081

CMD mvn spring-boot:run -Dspring-boot.run.profiles=docker


#############################################################
# Production environment                                    #
#############################################################
FROM eclipse-temurin:17-jre-alpine as production

WORKDIR /app

COPY --from=development /app/target/*.jar /app/application.jar

RUN rm -fr target

EXPOSE 8080 8081

CMD java -Dspring.profiles.active=docker \
  -Xdebug \
  -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8081 \
  -jar application.jar
