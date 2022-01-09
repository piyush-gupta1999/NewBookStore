
## Technology Used
1. Java Version - 11
2. Framework - Spring Framework
3. Database Used - H2   
4. Testing Tool - Postman


## How to start the project.
1. First you should have H2 instance up and running on your system.
2. You can change the configurations of database in application.properties based on your server details. Below ones are mine.
-  spring.h2.console.path=localhost:8082
-  spring.datasource.url=jdbc:h2:tcp://localhost/~/test
-  spring.datasource.driverClassName=org.h2.Driver
-  spring.datasource.username=sa
-  spring.datasource.password=
3. Added createTables.sql file to add tables in H2 database server instance.
4. Now you just need to start the Main ReadingisgoodApplication and your application will start on localhost and port 8080.
5. Once the application start you can test the API's with Postman Request.
6. Exported the postman request file in root directory. You can directly import that file in postman and start testing immediately.
7. Added a Dockerfile to containerize the application. First create image by running the docker file. Then you need to run the image by binding it to a port.
- `Command - docker run -d -p 8000:8000 --name rest-server-name node-docker-image`
- *Note* You can change security configuration in application.properties file.