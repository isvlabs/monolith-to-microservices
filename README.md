# Monolith to Microservices - Part I

Welcome to the Monolith to Microservices Immersion Day!

This is the first part of a new workshop we’ve been building, targeted at a **200 level – Intermediate** knowledge of AWS and application development principles.

We aim to give you experience hosting an example monolith on [AWS Elastic Beanstalk](https://aws.amazon.com/elasticbeanstalk/), and then starting to decompose this into a number of microservices using Docker and the [Amazon Elastic Container Service (ECS)](https://aws.amazon.com/ecs/).

We try to strike a healthy balance between AWS and application level concerns.

**Software and other prerequisites**

There are some basic prerequisites that you'll need installed and configured locally to get the most out of this lab:
 
- You'll need local admin access to the machine you use today. Basically, make sure you have the rights to install software.
- Have a [Java 8+ JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html) installed
- Have [Git](https://git-scm.com/) installed and working for your OS
- Make sure you have [Maven](https://maven.apache.org/download.cgi) installed and working
- Have a recent Eclipse IDE for Java installed, e.g. the [Spring Tool Suite](https://spring.io/tools) (or an equivalent that you are comfortable working with day-day e.g. IntelliJ)
- Have the [AWS CLI](https://aws.amazon.com/cli/) installed. We’ll configure this below with the credentials you'll use for the hands-on lab
- Have installed and configured [Node.js v8.10](https://nodejs.org/en/) or later
- Have a SQL client (which supports MySQL) or simply use the embedded IDE database client
 
**What we’ll cover:**
 
- Building and deploying an existing Java (AngularJS + Bootstrap) monolithic application to AWS
- Hosting the monolith using AWS Elastic Beanstalk and MySQL RDS
- Creating an ECS cluster to host our microservices
- Decomposing the monolith one microservice at a time (Restful APIs and Node.js)
- Dockerising our microservice and hosting this on ECS, taking care to also refactor the MySQL database

**Assumed knowledge:**  
(100 - 200 level / Beginner - Intermediate)

- AWS Console & AWS CLI
- AWS CloudFormation
- Amazon ECS
- AWS Elastic Beanstalk
- Amazon DynamoDB
- Java application development
- MySQL database
- Node.js

## Setting up your Development environment

If you haven't satisfied the pre-requisites above, please do that now. You won't make much progress in the lab without these tools installed and ready.

We have a bunch of infrastructure automation and sample Java and Node.js application code ready for you to use for the duration of the lab. We've hosted this on [Github](https://github.com/) for you.

Browse to, [https://github.com/isvlabs/monolith-to-microservices](https://github.com/isvlabs/monolith-to-microservices) and become familiar with the repository if you haven't already (you might already be there!).

We'd suggest keeping this Github repository open in a browser tab. We'll be using and referring to it frequently throughout the lab.

## Setting up access to your AWS lab account

### Configuring the AWS CLI

You'll need to configure the AWS CLI to use the credentials supplied to you for this lab.

Run:

	aws configure

and configure with your **AWS Access Key ID** and **AWS Secret Access Key**.

Many developers are very at home automating their AWS infrastructure from the command line. Much of the lab will suppose this. If you're not in this camp, that's OK, you can also use the AWS Console instead. We won't show you step by step how to use the console though, but use the account ID and password given to you.

### Logging into the AWS Console (optional)

If you'd prefer to do things via the AWS Console, browse to:

https://console.aws.amazon.com/

Use the account number and password provided to you for the purpose of this lab, and log in. We won't show you how to use the AWS console. We assume you are experienced enough with AWS to do this on your own.

## The Sample Application :: ID Card Manager (AngularJS and Spring Boot)

[![Build Status](https://api.travis-ci.org/isvlabs/monolith-to-microservices.svg?branch=master)](https://travis-ci.org/isvlabs/monolith-to-microservices)

### Understanding the ID Card Manager application with a few diagrams

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

Our issue tracker is available here: [https://github.com/isvlabs/monolith-to-microservices/issues](https://github.com/isvlabs/monolith-to-microservices/issues).

If you find an issue or would like to make an improvement to this lab, please check the issue tracker, create an issue and then optionally send us a pull request :)

## Database configuration

In its default configuration, this application uses an in-memory database (HSQLDB) which gets populated at startup with data. A similar setup is provided for MySql in case a persistent database configuration is needed.

Note that whenever the database type is changed, the data-access.properties file needs to be updated and the mysql-connector-java artifact from the pom.xml needs to be uncommented.

You may start also a MySql database with docker if you're so inclined:

```
docker run -e MYSQL_ROOT_PASSWORD=petclinic -e MYSQL_DATABASE=petclinic -p 3306:3306 mysql:5.7.8
```

## Working with the application in Eclipse/STS

### Prerequisites

The following items should be installed in your system:

* Maven 3 - [https://maven.apache.org/install.html](https://maven.apache.org/install.html)
* Git command line tool - [https://help.github.com/articles/set-up-git](https://help.github.com/articles/set-up-git)
* Eclipse with the m2e plugin - m2e is installed by default when using the STS - [http://www.springsource.org/sts](http://www.springsource.org/sts) distribution of Eclipse

Note: when m2e is available, there is an m2 icon in Help -> About dialog.

If m2e is not there, just follow the install process here: [http://eclipse.org/m2e/download/](http://eclipse.org/m2e/download/)

### Steps:

1) On the command line

```
git clone https://github.com/isvlabs/monolith-to-microservices.git
```

2) Inside Eclipse
```
File -> Import -> Maven -> Existing Maven project
```

### Activate the ```dev``` Spring profile

In development mode, we recommend you yo use the ```dev``` Spring profile.

Just add the following VM option:
```
-Dspring.profiles.active=dev
```

All static resources changes will be monitored by the embedded LiveReload server of Spring Boot Devtools.

See [application-dev.properties](idcardmanager-server/src/main/resources/application-dev.properties) for details.

## Client-side Architecture

This SpringBoot Application is split in 2 modules - a client module and a server module:

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


## Building the application locally

To build the the .war file locally:



## Hosting and launching the Monolith on AWS

Now that we have the monolith running locally, we're going to host it on AWS. Because the monolith is a simple 2/3 tier application architecture, AWS Elastic Beanstalk with Amazon RDS makes this really easy.

### Creating a build artifact repository in S3

We're going to create an S3 bucket to store our monolith artifact. You'll use this as a staging area to store your application artifact, and to deploy it.

N.B. We're not suggesting this is anything close to best practice for continuous build or deployment, but it's pragmatic for the purpose of this lab, and to easily deploy our monolith to [AWS Elastic Beanstalk](https://aws.amazon.com/documentation/elastic-beanstalk/).

If you use the AWS CLI, do something like the following:

	aws s3api create-bucket --bucket monolith-build --region ap-southeast-2 --create-bucket-configuration LocationConstraint=ap-southeast-2

You'll want to create a unique name for your S3 bucket. S3 uses a global namespace, so if you use the specific example above, it  should fail as the bucket name **monolith-build** is already taken.

Once you've created your bucket, make a note of its name. It should be private to your AWS account, and you'll use it throughout the lab.

### Building the monolith

The first thing you'll need to do is check out the monolith code. The monolith is part of the Github repository, so clone this to your local environment.

Import the app into your IDE of choice and check that it compiles. Use Gradle to build the application. This will generate a **.war** file containing your application.

Upload your **.war** file to your S3 bucket. Using the AWS CLI that would be something like:

	aws s3 cp monolith.war s3://monolith-build

We'll now use this as the source location to deploy the monolith to Beanstalk.

**NOTE:** this isn't a best practice way to build and deploy your application. You'd almost certainly use an SCM like Git to instantiate a build pipeline in your continuous integration tool of choice, run unit tests etc, build your artifact and then manage deploying this change to your application environment. However, the point of this lab isn't to teach you CICD or DevOps best practices, but focus on the work required to start iterating a monolith towards a microservices architecture.

### Deploying the monolith to Beanstalk

We've supplied a CloudFormation template to deploy your application to AWS Elastic Beanstalk. It takes a few arguments, one of which is the S3 location to your application artifact.

We will deploy the monolith to Tomcat, and also build a MySQL database on the [Amazon Relational Database Service (RDS)](https://aws.amazon.com/rds/) for you as part of the deployment.

If you browser to the `cfn/` directory in your repository you should see a file `eb.yml`. This is a YAML CloudFormation template that will take your monolith and deploy it to Tomcat for you.

To do this via the AWS CLI from the `cfn/` directory in your repository, run something like:

	aws cloudformation create-stack --stack-name monolith-beanstalk \
	--template-body file://eb.yml	\
	--parameters ParameterKey=ArtifactS3Bucket,ParameterValue=monolith-build ParameterKey=ArtifactName,ParameterValue=monolith.war \
	--tags Key=Name,Value="Monolith on Elastic Beanstalk"

You'll see a stack being created if you query the status:

	aws cloudformation describe-stacks --stack-name monolith-beanstalk

Once your application has deployed, you'll see CloudFormation print a number of outputs. One of them will be the DNS endpoint you can use to browse to your monolith. Do this.

![Monolith on Elastic Beanstalk](img/aws-monolith-eb.png)

## Decomposing the Monolith

Instructions for how to reason about and start decomposing the monolith.

## Summary

Well, hopefully that was interesting! You've managed to build and deploy an old-fashioned monolithic application to AWS using Elastic Beanstalk, and started to reason about how to decompose it.

You've also hosted a micro service built in Node.js and dockerised this, hosting it on Amazon ECS. You've also thought about refactoring the database to achieve a decouple data model for your micro service.

This is just the beginning! What would you do next? Build a new micro service and host it on ECS, or perhaps on AWS Lambda? Would you decompose any more services?

Tell us what you'd do next, or better yet - go ahead and do it, and let us know!


## Contributing

The [issue tracker](https://github.com/isvlabs/monolith-to-microservices/issues) is the preferred channel for bug reports, features requests and submitting pull requests.

For pull requests, editor preferences are available in the [editor config](https://github.com/isvlabs/monolith-to-microservices/blob/master/.editorconfig) for easy use in common text editors. Read more and download plugins at <http://editorconfig.org>.









