FROM openjdk:11
WORKDIR /
ADD ./target/readingisgood-0.0.1-SNAPSHOT.jar readingisgood-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD java -jar readingisgood-0.0.1-SNAPSHOT.jar
