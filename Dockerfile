FROM openjdk:11
WORKDIR /spring-msyql
COPY ./gradle ./
COPY ./gradlew ./
COPY ./settings.gradle ./
COPY ./build.gradle ./
COPY ./ ./
RUN ./gradlew clean build
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005
CMD ["java", "-javaagent:newrelic/newrelic.jar", "-jar", "./build/libs/accessing-data-mysql-0.0.1-SNAPSHOT.jar"]