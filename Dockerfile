FROM amazoncorretto:19-alpine-jdk
MAINTAINER thomaspufahl
LABEL authors="thomaspufahl"

COPY production/artifacts/api_portfolio_jar/api-portfolio.jar argprograma-app.jar

ENTRYPOINT ["java", "-jar", "/argprograma-app.jar"]