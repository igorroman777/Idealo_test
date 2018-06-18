# Coding puzzle: Toy Robot

This is a sample Java / Maven / Spring Boot (version 2.0.0) and AngularJS (version 1.6.9) application.

## Code problem details:

- The application is a simulation of a toy robot moving on a square tabletop, of
dimensions 5 x 5 units.
- There are no other obstructions on the table surface.
- The robot is free to roam around the surface of the table, but must be prevented from
falling to destruction. Any movement that would result in the robot falling from the table mube prevented, 
however further valid movement commands must still be allowed.

## How to run

This application is packaged as a war which has Tomcat 8 embedded. No Tomcat or JBoss
installation is necessary. You run it using the ```java -jar```  command.

* Clone this repository 
* Make sure you are using JDK 1.8 and Maven 3.x
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service by one of these two methods:


```
java -jar target/idealo-toy-robot-0.1.0.jar
or
mvn spring-boot:run
```
More interestingly, you can start agularJS client in the browser:
```
localhost:8080

```

## About the service
 The service is just a REST-API service. 
 
### Swagger API documentation
Every Spring Boot microservice that is annotated with `@EnableSwagger2` exposes the Swagger API documentation under the path `/v2/api-docs`.
Here's the Swagger UI for our sample microservices system, available under:
```
http://localhost:8080/swagger-ui.html

```

Here are some endpoints you can call:

### PLACE will create and put the toy robot on the table in position X,Y and facing NORTH,
SOUTH, EAST or WEST. The origin (0,0) can be considered to be the SOUTH WEST
most corner.
```
GET /place/{x}/{y}/{facing}

Response: HTTP 200
Content: robot json
{
    "idRobot": "59",
    "x": 0,
    "y": 0,
    "facing": "NORTH"
}
```

### MOVE will move the toy robot one unit forward in the direction it is currently facing and throws an exception if robot not found.
```
GET /move/{idRobot}

Response: HTTP 200
Content: robot json
{
    "idRobot": "59",
    "x": 0,
    "y": 1,
    "facing": "NORTH"
}

throws an exception if robot not found.
Response: HTTP 400

{
    "timestamp": "2018-06-17T11:02:03.050+0000",
    "status": 400,
    "error": "Bad Request",
    "message": "ROBOT MISSING",
    "path": "/robot/move/3"
}

```

### LEFT and RIGHT will rotate the robot 90 degrees in the specified direction without changing the position of the robot and throws an exception if robot not found.

```
GET /rotate/{idRobot}/{left_right}

Response: HTTP 200
Content: robot json

{
    "idRobot": "59",
    "x": 0,
    "y": 0,
    "facing": "WEST"
}

throws an exception if robot not found.
Response: HTTP 400

{
    "timestamp": "2018-06-17T11:02:03.050+0000",
    "status": 400,
    "error": "Bad Request",
    "message": "ROBOT MISSING",
    "path": "/robot/move/3"
}

```

### REPORT will announce the X,Y and F of the robot and throws an exception if robot not found.

```
GET /report/{idRobot}

Response: HTTP 200
Content: robot json

{
    "message": "0,1,WEST",
    "robot": {
        "idRobot": "59",
        "x": 0,
        "y": 1,
        "facing": "WEST"
    }
}

throws an exception if robot not found.
Response: HTTP 400

{
    "timestamp": "2018-06-17T11:02:03.050+0000",
    "status": 400,
    "error": "Bad Request",
    "message": "ROBOT MISSING",
    "path": "/robot/move/3"
}

```
