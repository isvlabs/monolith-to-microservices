# Monolith Application :: ID Card Manager (AngularJS and Spring Boot) 
[![Build Status](https://api.travis-ci.org/isvlabs/monolith-to-microservices.svg?branch=master)](https://travis-ci.org/isvlabs/monolith-to-microservices)

## Running idCardManager locally
```
	cd src-monolith/idcardmanager-client
	../mvnw clean install
        
	cd src-monolith/idcardmanager-server
	../mvnw spring-boot:run
```
You can then access the application here: http://localhost:8080/

### Active the dev Spring profile
In development mode, we recommand you yo use the ```dev``` Spring profile.
Just add the following VM option:
```
-Dspring.profiles.active=dev
```
All static resources changes will be monitored by the embedded LiveReload server of Spring Boot Devtools.
See [application-dev.properties](idcardmanager-server/src/main/resources/application-dev.properties) for details.


## Client-side Architecture
This SpringBoot Application is splitted in 2 modules - a client module and a server module:
* idcardmanager-client : static resources (images, fonts, style, angular JS code) packaged as a webjar.
* idcardmanager-server : Spring MVC REST API and an index.html template

## Looking for something in particular?

| Spring Boot Configuration     | Files |
|-------------------------------|-------|
| The Main Class                | [IDCardManagerApplication.java](idcardmanager-server/src/main/java/com/amazon/aws/isvlabs/samples/idcardmanager/IDCardManagerApplication.java)  |
| Common properties file        | [application.properties](idcardmanager-server/src/main/resources/application.properties)  |
| Development properties file   | [application-dev.properties](idcardmanager-server/src/main/resources/application-dev.properties)  |
| Production properties file    | [application-prod.properties](idcardmanager-server/src/main/resources/application-prod.properties)  |
| Caching: Cache with EhCache   | [CacheConfig.java](idcardmanager-server/src/main/java/com/amazon/aws/isvlabs/samples/idcardmanager/config/CacheConfig.java) |
| Homepage                      | Map root context to the index.html template: [WebConfig.java](idcardmanager-server/src/main/java/com/amazon/aws/isvlabs/samples/idcardmanager/config/WebConfig.java) |


| Front-end module  | Files |
|-------------------|-------|
| Node and NPM      | [The frontend-maven-plugin plugin downloads/installs Node and NPM locally then runs Bower and Gulp](idcardmanager-client/pom.xml)  |
| Bower             | [JavaScript libraries are defined by the manifest file bower.json](idcardmanager-client/bower.json)  |
| Gulp              | [Tasks automated by Gulp: minify CSS and JS, generate CSS from LESS, copy other static resources](idcardmanager-client/gulpfile.js)  |
| Angular JS        | [app.js, controllers and templates](idcardmanager-client/src/scripts/)  |

For pull requests, editor preferences are available in the [editor config](https://github.com/isvlabs/monolith-to-microservices/blob/master/.editorconfig) for easy use in common text editors. Read more and download plugins at <http://editorconfig.org>.

