FROM frolvlad/alpine-oraclejdk8:slim
COPY ServiceRegistryService/target/srs-0.0.1-SNAPSHOT.jar /usr/src/srs-0.0.1-SNAPSHOT.jar
COPY QuizSystem/target/qs-0.0.1-SNAPSHOT.jar /usr/src/qs-0.0.1-SNAPSHOT.jar
# COPY EmailSystem/target/es-0.0.1-SNAPSHOT.jar /usr/src/es-0.0.1-SNAPSHOT.jar
# COPY QuestionCollection/target/qc-0.0.1-SNAPSHOT.jar /usr/src/qc-0.0.1-SNAPSHOT.jar
COPY JudgeWorker/target/qs-0.0.1-SNAPSHOT.jar /usr/src/jw-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/
EXPOSE 1111 4444 3333

