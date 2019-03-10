# Spring-boot-keycloak


This project shows a simple integration of Keycloak 4 in Spring Boot 2 applications.

The following versions are used:

* __Docker__: 18.03.1-ce
* __Docker-compose__: 1.21.1
* __Apache Maven__: 3.5.3
* __Spring Boot__: 2.0.3.RELEASE
* __Keycloak__: 4.8.3.Final

## Keycloak Setup

First Keycloak and the database needs to be started via:

```
docker-compose -f keycloak/docker-compose.yml up
```

It imports a demo realm with an example user and sets the admin credentials to `admin:password`. You can visit the administration console at [http://localhost:8180/auth/](http://localhost:8180/auth/).

Use that URL in your browser to access the administration console of Keycloak.
It is located at `http://localhost:8180/auth`. Connect using the Keycloak default administration user
`admin/admin` [if it's the first time you login. Keycloak requires changing this password at first login. Since I mentioned it in docker-compose file this can be skipped]

#### Configure application realms and authorizations

Create a new Keycloak realm by importing the `/keycloak/config/realm-export.json` file.
Create two users 'alice' and 'cooper' with student and teacher roles respectively.

## Spring Boot Application

After Keycloak started successfully, you can start the Spring Boot application in a separate terminal via

```
mvn package spring-boot:run
```

It is accessible on [http://localhost:7070/management/v1/students](http://localhost:7070/management/v1/students). After accessing this url, the login page from Keycloak shows up. Use the credentials of a user(cooper/alice). Signing in with that user should redirect you back to the application, where as if the user has restricted access forbidden is shown.