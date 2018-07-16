// TO BE UPDATED (DATABASE, travis location, image & speaker pack location)

# Sample Application :: ID Card Manager (AngularJS and Spring Boot) 
[![Build Status](https://api.travis-ci.org/isvlabs/monolith-to-microservices.svg?branch=master)](https://travis-ci.org/isvlabs/monolith-to-microservices)

## Understanding the ID Card Manager application with a few diagrams
<a href="TBC">See the presentation here</a>

## Running idCardManager locally
```
	git clone https://github.com/isvlabs/monolith-to-microservices.git
	cd src-monolith/idcardmanager-client
	./mvnw clean install
	cd src-monolith/idcardmanager-server
	../mvnw spring-boot:run
```

You can then access the application here: http://localhost:8080/

## In case you find a bug/suggested improvement for ID Card Manager
Our issue tracker is available here: https://github.com/isvlabs/monolith-to-microservices/issues

## Database configuration

In its default configuration, this application uses an in-memory database (HSQLDB) which
gets populated at startup with data. A similar setup is provided for MySql in case a persistent database configuration is needed.
Note that whenever the database type is changed, the data-access.properties file needs to be updated and the mysql-connector-java artifact from the pom.xml needs to be uncommented.

You may start a MySql database with docker:

```
docker run -e MYSQL_ROOT_PASSWORD=petclinic -e MYSQL_DATABASE=petclinic -p 3306:3306 mysql:5.7.8
```

## Working with the Application in Eclipse/STS

### prerequisites
The following items should be installed in your system:
* Maven 3 (https://maven.apache.org/install.html)
* git command line tool (https://help.github.com/articles/set-up-git)
* Eclipse with the m2e plugin (m2e is installed by default when using the STS (http://www.springsource.org/sts) distribution of Eclipse)

Note: when m2e is available, there is an m2 icon in Help -> About dialog.
If m2e is not there, just follow the install process here: http://eclipse.org/m2e/download/


### Steps:

1) In the command line
```
git clone https://github.com/isvlabs/monolith-to-microservices.git
```
2) Inside Eclipse
```
File -> Import -> Maven -> Existing Maven project
```

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


# Contributing
The [issue tracker](https://github.com/isvlabs/monolith-to-microservices/issues) is the preferred channel for bug reports, features requests and submitting pull requests.

For pull requests, editor preferences are available in the [editor config](https://github.com/isvlabs/monolith-to-microservices/blob/master/.editorconfig) for easy use in common text editors. Read more and download plugins at <http://editorconfig.org>.

