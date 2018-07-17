# Monolith to Microservices - Part I

Welcome to the Monolith to Microservices Immersion Day!

This is the first part of a new workshop we’ve been building, targeted at a **200 level – Intermediate** knowledge of AWS and application development principles.

We aim to give you experience hosting an example monolith on [AWS Elastic Beanstalk](https://aws.amazon.com/elasticbeanstalk/), and then starting to decompose this into a number of microservices using Docker and the [Amazon Elastic Container Service (ECS)](https://aws.amazon.com/ecs/).

We try to strike a healthy balance between AWS and application level concerns.

**Software and other prerequisites**

There are some basic prerequisites that you'll need installed and configured locally to get the most out of this lab:
 
1. You'll need local admin access to the machine you use today. Basically, make sure you have the rights to install software.
2. Have a [Java 8+ JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html) installed
3. Have [Git](https://git-scm.com/) installed and working for your OS
4. Make sure you have [Maven 3+](https://maven.apache.org/download.cgi) installed and working
5. Have the [AWS CLI](https://aws.amazon.com/cli/) installed. We’ll configure this below with the credentials you'll use for the hands-on lab
6. Have installed and configured [Node.js v8.10](https://nodejs.org/en/) or later
7. Have a SQL client (which supports MySQL) or simply use the embedded IDE database client
8. (Optional) A recent Eclipse IDE for Java installed, e.g. the [Spring Tool Suite](https://spring.io/tools) (or an equivalent that you are comfortable working with day-day e.g. IntelliJ

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

### Creating a build artifact repository in S3

We're going to create an S3 bucket to store our monolith artifact. You'll use this as a staging area to store your application artifact, and to deploy it.

**NOTE:** We're not suggesting this is anything close to best practice for continuous build or deployment, but it's pragmatic for the purpose of this lab, and to easily deploy our monolith to [AWS Elastic Beanstalk](https://aws.amazon.com/documentation/elastic-beanstalk/).

If you use the AWS CLI, do something like the following:

	aws s3api create-bucket --bucket monolith-build --region ap-southeast-2 --create-bucket-configuration LocationConstraint=ap-southeast-2

You'll want to create a unique name for your S3 bucket. S3 uses a global namespace, so if you use the specific example above, it  should fail as the bucket name **monolith-build** is already taken.

Once you've created your bucket, make a note of its name. It should be private to your AWS account, and you'll use it throughout the lab.


### The Monolithic Application :: ID Card Manager (AngularJS and Spring Boot)

## Git checkout

The first thing you'll need to do is check out the repository. The monolith is part of the Github repository, so clone this to your local environment.

	git clone https://github.com/isvlabs/monolith-to-microservices.git
        

## Building idCardManager locally

From a terminal (or your preferred IDE) use Maven to build the application. 

Note: There are two modules, which need to be built sequentially.

	cd src-monolith/idcardmanager-client
	../mvnw clean install
	cd src-monolith/idcardmanager-server
	../mvnw clean install

Once this has completed succesfully. 
        
        Navigate to: src-monolith/idcardmanager-server/target/

The Application artefact (SpringBoot App) should now exist : **idcardmanager.jar**.

## Uploading the application artefact to S3

Upload your **idcardmanager.jar** file to your S3 bucket. Using the AWS CLI that would be something like:

	aws s3 cp idcardmanager.jar s3://monolith-build

We'll now use this as the source location to deploy the monolith to Beanstalk.

**NOTE:** this isn't a best practice way to build and deploy your application. You'd almost certainly use an SCM like Git to instantiate a build pipeline in your continuous integration tool of choice, run unit tests etc, build your artifact and then manage deploying this change to your application environment. However, the point of this lab isn't to teach you CICD or DevOps best practices, but focus on the work required to start iterating a monolith towards a microservices architecture.

### Deploying the monolith to Beanstalk

We've supplied a CloudFormation template to deploy your application to AWS Elastic Beanstalk. It takes a few arguments, one of which is the S3 location to your application artifact.

We will deploy the monolith to Tomcat, and also build a MySQL database on the [Amazon Relational Database Service (RDS)](https://aws.amazon.com/rds/) for you as part of the deployment.

If you browse to the `cfn/` directory in your repository you should see a file `eb.yml`. This is a YAML CloudFormation template that will take your monolith and deploy it to Tomcat for you.

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

