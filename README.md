#com-ms3-microproject
###TechStack
Java, Spring, Hibernate, PostgreSQL, Swagger.io, Angular, Angular Material, Docker

###Pre-requisite
1. Docker, Maven, Node
2. Run `docker-compose -f doceker-compose.yml up` to execute sql scripts
3. Open psql on docker using command `docker exec -it postgres-node psql --u alona`
4. Run the script from `contact-management/src/main/resources/db`. Start with `base.sql` then the specific ones

###Run #1 - Run directly on local
1. Open terminal and go to the spring boot project `cd contact-management`
2. Run `mvn clean install` 
3. Run `mvn spring-boot:run`
4. Open another terminal and go to the angular project `cd contact-management-angular`
5. Run `npm install`
6. Run `ng serve`
7. Go to browser and open `http://localhost:4200/`
8. To open swagger api please go to this link `http://localhost:1296/swagger-ui/index.html?configUrl=%2Fv3%2Fapi-docs%2Fswagger-config&urls.primaryName=CustomApis`

###Run #2 - Run using docker on local
1. Open terminal and go to the spring boot project `cd contact-management`
2. Run `mvn clean package` to create jar file
3. Run `docker build --tag=contact-management:latest .`
4. Go to the angular project `cd ../contact-management-angular`
5. Run `ng build`
6. Run `docker build -t contact-management-app .`
7. On spring-boot project run the `docker-compose -f doceker-compose-custom.yml up` to run the spring boot and angular app. You can also run it manually by running `docker run .....` command for each of the images.
8. You can now test it using the links above.

###Software Architecture
1. Bi-directional access @OneToMany from Identification and @ManyToOne to Address and Communication.
2. Created Api's for Identification, Address and Communication but only Identification was used
3. Added audit fields such as createdBy, modifiedBy, createdDatetime, modifiedDatetime for auditing who owns/update the resource, verNo for optimistic locking, status for soft delete, inactive status and other statuses
4. RestControllerAdvice for global handling of errors
5. CORSFilter for inter network access
6. SwaggerAPI for API documentation
7. Sorting in UI
8. Api can be testing also using postman in `contact-management/src/main/resources/postman`, but there are some inconsistencies in the request body
