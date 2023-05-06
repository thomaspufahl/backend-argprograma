FROM amazoncorretto:17-alpine-jdk
MAINTAINER thomaspufahl
LABEL authors="thomaspufahl"

COPY out/artifacts/api_portfolio_jar/api-portfolio.jar argprograma-app.jar

ENTRYPOINT ["java", "-jar", "/argprograma-app.jar"]