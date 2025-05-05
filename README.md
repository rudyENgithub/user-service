# User API Service built with haxagonal architecture and DDD
Diagrama c4
![Diagrama c4 User Service DDD y Architecture hexagonal - Rudy Sorto](https://github.com/user-attachments/assets/8c8be117-16e6-4663-b907-c91044592d75)

Diagrama ERD
![image](https://github.com/user-attachments/assets/8da6dd03-9135-441d-afe2-61387494df7b)

# Table of Contents

- [User API Service](#user-api-service)
  - [Features](#features)
  - [Technologies](#Technologies)
  - [Profile configuration](#profile-configuration)
  - [Flyway](#flyway)
  - [Execution](#execution)
    - [Test](#test)
    - [Production](#production)
  - [resources](#resources)
  - [Cases Use](#cases-use)

## Features

- **API RESTful de creación de usuarios:**  
    - Permite registrar usuarios con nombre, correo, contraseña y múltiples teléfonos con el siguiente formato:
 
      ![image](https://github.com/user-attachments/assets/763d22f7-4d69-44fb-9ff3-3b9473200ec3)

    {
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.org",
    "password": "hunter2",
    "phones": [
         {
             "number": "1234567",
             "citycode": "1",
            "contrycode": "57"
        }
  ]
}

  - Valida credenciales, actualiza el campo `lastLogin` y genera un token JWT.

- **Validaciones y Reglas de Negocio:**
  - Validación de formato de correo y contraseña (expresiones regulares configurables).
  - Verificación de unicidad en el correo y en los números de teléfono asociados a cada usuario.
- **Seguridad:**
  - Encriptación de contraseña utilizando BCrypt.
  - Generación de token de acceso mediante JWT.
  - Integración con Spring Security.
- **Persistencia:**
  - **Producción:** Utiliza PostgreSQL, configurado para el perfil `prod`.
  - **Test/Desarrollo:** Utiliza H2 en memoria, facilitando pruebas y desarrollo local.
- **Documentación:**
  - API con Swagger/OpenAPI.
- **Contenerización y Despliegue:**
  - Dockerfile multi-stage y Docker Compose para el despliegue de la API y la base de datos en producción.



## Technologies
- **Arquitectura:**
    - DDD y arquitectura hexagonal
- **Lenguaje y Frameworks:**
    - Java 21
    - Spring Boot 3.3.10
    - Junit, Mockito
- **Persistencia y Base de Datos:**
    - Spring Data JPA
    - H2 (en desarrollo, configurable para otros entornos)
    - PostgreSQL (producción)
- **Migración de Base de Datos:**
  - Flyway
- **Seguridad:**
    - Spring Security
    - JWT (usando JJWT)
- **Documentación:**
    - Springdoc OpenAPI/Swagger UI
- **Contenerización y Orquestación:**
    - Docker
    - Docker Compose
- **Build y CI/CD:**
    - Maven

## Profile configuration

La aplicación utiliza perfiles para diferenciar ambientes:

- **Ambiente Test/Desarrollo:**  
  Se utiliza H2 en memoria. La configuración se encuentra en `application-test.yaml` en la sección predeterminada.  
  La consola de H2 estará disponible en:  
  `http://localhost:8080/h2-console`

- **Ambiente Producción:**  
  Se utiliza PostgreSQL y Flyway para gestionar migraciones. Este perfil se activa mediante `SPRING_PROFILES_ACTIVE=prod`.  
  La configuración de PostgreSQL se encuentra en `application-prod.yaml`.
### Flyway
Los scripts de migración se ubican en src/main/resources/db/test/migration  y src/main/resources/db/prod/migration. Cada script debe seguir la convención de nombres de Flyway, por ejemplo:
V1__create_tables_users.sql.
## Prerequisites
- JDK 21
- Maven 3.x
- Git
- Docker
## Execution
- ### Test

  - #### 1. Clone the Repository
  ```bash
  git clone https://github.com/rudyENgithub/user-service.git
  cd user-service
  ```
  - #### 2. Build
  ```
  mvn clean install
  ```
  - #### 2. Run Test
  ```
  mvn test
  ```
  - #### 3 Run 
  To start project, run:
  ```
  mvn spring-boot:run
  ```
  API disponible en [http://localhost:8080](http://localhost:8080).
  - #### 2.3 Swagger Documentacion
    - http://localhost:8080/swagger-ui.html
  - #### 2.4 Acceder a la consola H2
    - http://localhost:8080/h2-console

      Utiliza las siguientes credenciales (por defecto):

          JDBC URL: jdbc:h2:mem:database_users
          Username: user_nisumci
          Password: admin123

- ### Production
  - #### 1. Clone the Repository
    ```bash
    git clone https://github.com/rudyENgithub/user-service.git
    cd user-service
    ```
  - #### 2. Build and run
    ```
    docker-compose up --build -d
    ```
    API disponible en [http://localhost:8080](http://localhost:8080)

- ### Resources
  Los diagramas de la solución, colección de Postman y el script SQL los encuentras en: 
       `/user-service/resources`

        /diagrams
        /postman-api
        /database-script
  - ### Cases Use
    - #### 1 Creacion de usuario
      ![image](https://github.com/user-attachments/assets/40b3aee7-8792-4ec9-828a-705518b424fe)

    - #### 2 Usuario Existe
      ![image](https://github.com/user-attachments/assets/d4f4ca16-de14-48c4-9278-0a15c99bb683)

    - #### 3 Password incorrecto por Expresion Regular
      ![image](https://github.com/user-attachments/assets/29b5ccfe-a140-4bcc-b252-d98c42b8667f)

    - #### 4 Email incorrecto por Expresion Regular
      ![image](https://github.com/user-attachments/assets/0e88d46d-1c8e-4aff-8a60-ba47b4848f31)

    - #### 5 Revision de data en base de datos
      ![image](https://github.com/user-attachments/assets/d820bd5b-ff46-4d13-b972-f6046e3e7bc2)

      ![image](https://github.com/user-attachments/assets/786b0e81-d857-4b0f-9136-e238eae1eb31)


      


      
