FROM openjdk:8
COPY Principal.java /principal/
WORKDIR /principal/
RUN ["javac", "Principal.java"]
ENTRYPOINT [ "java", "Principal" ]