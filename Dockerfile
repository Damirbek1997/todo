FROM openjdk:17
ARG JAR_FILE=target/todolist-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} todolist.jar
ENTRYPOINT ["java", "-jar", "todolist.jar"]
EXPOSE 8080