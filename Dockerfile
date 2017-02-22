FROM openjdk:8-jre-alpine

ADD target/teamOnboard-1.0.0-SNAPSHOT.jar /

EXPOSE 8182

WORKDIR /

ENTRYPOINT ["java", "-cp", "/conf:/", "-jar", "teamOnboard-1.0.0-SNAPSHOT.jar" ]
