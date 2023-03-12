FROM openjdk:17-jdk-alpine
MAINTAINER rus-zhu
COPY target/url_shortener-0.0.1-SNAPSHOT.jar url_shortener-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/url_shortener-0.0.1.jar"]