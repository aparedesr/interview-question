# Shorten URLs
Welcome to the shorten URLs Spring Boot App. These instructions will help you to build and run the application. 

### Build
Go to the your source folder and run 
```
maven package
```
or alternatively you can also use
```
mvn install
```

### Run
Run this command:
```
 mvn spring-boot:run
```
***Note:** See the application is running using embedded Tomcat on port 5000 (original port)


### Usage 
```
http://localhost:5000/short?url=<url>
```

```
http://localhost:5000/long?tinyUrl=<tinyUrl>
```