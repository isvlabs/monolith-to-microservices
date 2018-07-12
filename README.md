## Setting up your Development environment

Prerequisites:

- Java (JRE)
- AWS CLI tool
- Git
- more...

We have a bunch of automation and tools ready that you can use for the duration of the lab.

Browse to, [https://github.com/isvlabs/monolith-to-microservices](https://github.com/isvlabs/monolith-to-microservices) and browse the repository if you haven't already.

Keep this open in a browser tab. We'll be using it frequently throughout the lab.

## Host and launch the Monolith

### Creating a build artifact repository in S3

Log into S3 and create a bucket. You'll use this as a staging area to store your application artifact, and to deploy from.

N.B. We're not suggesting this is anything close to best practice for continuous build or deployment, but it's pragmatic for the purpose of this lab, and to easily deploy our monolith to [AWS Elastic Beanstalk](https://aws.amazon.com/documentation/elastic-beanstalk/).

If you use the AWS CLI, do something like the following:

	$ aws s3api create-bucket --bucket monolith-build --region ap-southeast-2 --create-bucket-configuration LocationConstraint=ap-southeast-2

Once you've created your bucket, make a note of its name. It should be private to your AWS account, and you'll use it throughout the lab.

### Build the monolith

Follow the build instructions and create a **.war** file of your mono

Upload your **.war** file to your S3 bucket

### Deploy the monolith to Beanstalk

We've supplied a CloudFormation template to deploy your application to AWS Elastic Beanstalk. It takes a few arguments, one of which is the S3 location to your application artifact.

We will deploy this to Tomcat, and also build a MySQL database on the [Amazon Relational Database Service (RDS)](https://aws.amazon.com/rds/) for you.







