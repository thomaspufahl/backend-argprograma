FROM amazoncorretto:19-alpine-jdk
MAINTAINER thomaspufahl
LABEL authors="thomaspufahl"

COPY production/artifacts/api_portfolio_jar/ api/

ENTRYPOINT ["java", "-jar", "/api/api-portfolio.jar"]