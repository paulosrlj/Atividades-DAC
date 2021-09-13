FROM openjdk:8
COPY Principal.java /principal/
WORKDIR /principal/

RUN ["javac", "Principal.java"]
RUN ["jar", "cvfe", "Principal.jar", "Principal", "Principal.class"]

ENTRYPOINT [ "java", "-jar", "Principal.jar" ]