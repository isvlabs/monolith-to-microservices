## Monolith to Microservices

Welcome to the Monolith to Microservices Immersion Day!

This is a **200-300 level** hands-on lab to give you hands-on experience  hosting an example monolith on [AWS Elastic Beanstalk](https://aws.amazon.com/elasticbeanstalk/), and then starting to decompose this into a number of microservices using Docker and the [Amazon Elastic Container Service (ECS)](https://aws.amazon.com/ecs/).

We try to strike a healthy balance between AWS and application level concerns.

Assumed knowledge:

- AWS Console & AWS CLI
- AWS CloudFormation
- Amazon ECS
- AWS Elastic Beanstalk
- Amazon DynamoDB
- Java application development
- MySQL database
- Node.js

## Setting up your Development environment

You'll need the following installed and working on your local machine. If you don't have these working in your local environment, you'll need to do this first.

A working install of:

- Java JDK or JRE version 7 or higher
- Node.js v8.10 or higher
- AWS CLI tool
- Git
- Gradle
- more...

We have a bunch of infrastructure automation and sample Java and Node.js application code ready for you to use for the duration of the lab. We've hosted this on [Github](https://github.com/) for you.

Browse to, [https://github.com/isvlabs/monolith-to-microservices](https://github.com/isvlabs/monolith-to-microservices) and become familiar with the repository if you haven't already.

We'd suggest keeping this open in a browser tab. We'll be using and referring to the repository frequently throughout the lab.

## Host and launch the Monolith

### Creating a build artifact repository in S3

Log into S3 and create a bucket. You'll use this as a staging area to store your application artifact, and to deploy from.

N.B. We're not suggesting this is anything close to best practice for continuous build or deployment, but it's pragmatic for the purpose of this lab, and to easily deploy our monolith to [AWS Elastic Beanstalk](https://aws.amazon.com/documentation/elastic-beanstalk/).

If you use the AWS CLI, do something like the following:

`$ aws s3api create-bucket --bucket monolith-build --region ap-southeast-2 --create-bucket-configuration LocationConstraint=ap-southeast-2`

Once you've created your bucket, make a note of its name. It should be private to your AWS account, and you'll use it throughout the lab.

### Build the monolith

Follow the build instructions and create a **.war** file of your mono

Upload your **.war** file to your S3 bucket

### Deploy the monolith to Beanstalk

We've supplied a CloudFormation template to deploy your application to AWS Elastic Beanstalk. It takes a few arguments, one of which is the S3 location to your application artifact.

We will deploy the monolith to Tomcat, and also build a MySQL database on the [Amazon Relational Database Service (RDS)](https://aws.amazon.com/rds/) for you as part of the deployment.










