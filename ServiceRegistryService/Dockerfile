FROM frolvlad/alpine-oraclejdk8:slim
COPY target/srs-0.0.1-SNAPSHOT.jar /usr/src/srs-0.0.1-SNAPSHOT.jar
CMD java -cp /usr/src/srs-0.0.1-SNAPSHOT.jar org.examples.srs
EXPOSE 1111