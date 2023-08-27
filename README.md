# Blogger Spring

<p align="center">
  <img width="500px" height="350px" src="https://res.cloudinary.com/dgczimxpo/image/upload/v1693165831/blogger-spring/github-readme_s579l1.png">
</p>

Simple blogger application using Spring Boot(v3) with Java(v17) together with MySQL Database.

- RESTFul API 
- Practice Spring Boot JPA with ManyToOne and OneToMany Relationship.
- Using Spring Boot Security with JwtAuthentication strategy.
- Fully Documenteded using Postman documentation and OpenAPI(Swagger).

## Getting Started

The application was created with maven configuration.

#### Requirements

1. OpenJDK, can be installed at https://aws.amazon.com/corretto.
2. (Optional) Docker installed.
   - To be able to run `docker-compose.yml` file in order to install MySQL Database create a file named `db_root_password.txt` at the root of the project and fill it with the password then run the command `docker-compose up -d`.
   - NOTE: if you want to use diffrent database that already installed in your mechine, you can skip this and configure the env variables accordinly.  

#### Configure the environment variables

1. Go to `resources` folder and create or rename the `application-secrets.properties.example` to just `application-secrets.properties`.
2. Fill all the required variables in `application-secrets.properties`.

### Running the app

To run the application open a terminal at the root of the project and run the command:  
- `mvn clean spring-boot:run`, if you don't have maven installed globaly, use the local script `./mvnw clean spring-boot:run`.

# API Documentation

[Postman](https://documenter.getpostman.com/view/8382285/2s9Y5YShoM)  
[Swagger (only for local development)](http://localhost:8081/swagger-ui/index.html)
