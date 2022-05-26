FROM openjdk:17
EXPOSE 8082:8082
ADD target/spring_boot_conditional-0.0.1-SNAPSHOT.jar prodapp.jar
ENTRYPOINT ["java","-jar","/prodapp.jar"]