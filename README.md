## Monolith to Microservices

Welcome to the Monolith to Microservices Immersion Day!

This is a **200-300 level** hands-on lab to give you experience hosting an example monolith on [AWS Elastic Beanstalk](https://aws.amazon.com/elasticbeanstalk/), and then starting to decompose this into a number of microservices using Docker and the [Amazon Elastic Container Service (ECS)](https://aws.amazon.com/ecs/).

We try to strike a healthy balance between AWS and application level concerns.

**Assumed knowledge:**

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

## Setting up access to your AWS lab account

### Configuring the AWS CLI

You'll need to configure the AWS CLI to use the credentials supplied to you for this lab.

Run:

	aws configure

and configure with your **AWS Access Key ID** and **AWS Secret Access Key**.

Many developers are more at home automating their AWS infrastructure from the command line. Much of the lab will suppose this. If you're not in this camp, that's OK, you can also use the AWS Console instead. We won't show you step by step how to use the console though.

### Logging into the AWS Console (optional)

If you'd prefer to do things via the AWS Console, browse to:

https://console.aws.amazon.com/

Use the account number and password provided to you for the purpose of this lab, and log in. We won't show you how to use the AWS console. We assume you are experienced enough with AWS to do this on your own.

## Hosting and launching the Monolith

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

	aws cloudformation create-stack --stack-name monolith-beanstalk --template-body file://eb.yml --parameters ParameterKey=S3ArtifactURL,ParameterValue=s3://monolith-build/monolith.war --tags Key=Name,Value=Monolith on Elastic Beanstalk

You'll see a stack being created if you query the status:

	aws cloudformation describe-stacks --stack-name monolith-beanstalk

Once your application has deployed, you'll see CloudFormation print a number of outputs. One of them will be the DNS endpoint you can use to browse to your monolith. Do this.

![Monolith on Elastic Beanstalk](img/aws-monolith-eb.png)

## Decomposing the Monolith











