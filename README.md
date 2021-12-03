# Introduction

RESTful Api to Texo Test

# Running the service

Is written using https://projects.spring.io/spring-boot[Spring Boot] which
makes it easy to get it up and running so that you can start exploring the REST API.

The first step is to clone the Git repository:

```bash
$ git clone https://github.com/deividr/texotest.git
```

Once the clone is complete, you're ready to get the service up and running:

```bash
$ cd texotest
$ ./mvnw clean package
$ java -jar .\target\list-0.0.1-SNAPSHOT.jar
```

Or with Docker:

```bash
$ cd texotest
$ docker build -t texotest .
$ docker run -p 8080:8080 texotest
```
