#Deploying to AWS using MacOS

# Assumptions
This process assumes that you have created the contains using https://console.aws.amazon.com/ecs/home?region=us-east-1#/firstRun.

And you have installed the AWS CLI from http://docs.aws.amazon.com/cli/latest/userguide/cli-install-macos.html#awscli-install-osx-path.

# Building the container and pushing to ECR
###In the root of this project, run the following command:

./gradlew build

docker build -t spring-demo .

###Replace the AWS-ID with you own run the following command:

docker tag spring-demo:latest AWS-ID.dkr.ecr.us-east-1.amazonaws.com/spring-demo:latest

###If you have not supplied your credentials to AWS, do so with:

aws configure

###Create the spring-demo repository. This step is only done once.

aws ecr create-repository --repository-name spring-demo

###Get the AWS login information for docker:

aws ecr get-login --no-include-email --region us-east-1

###Copy the output of the previous command and paste to authenticate docker.

###Then push the container to AWS ECR replacing the AWS-ID.

docker push AWS-ID.dkr.ecr.us-east-1.amazonaws.com/spring-demo

#Deploying in AWS

#Create the task definition

Go to https://console.aws.amazon.com/ecs/home?region=us-east-1#/taskDefinitions.
Click "Create new Task Definition".
Give it a name in "Task Definition Name" and a "Task Role" of none.

Click "Add Contatiner"
Give the container a name. I put in SpringDemo.
And put in a Hard limit 0f 300.

In a separate tab, go to https://console.aws.amazon.com/ecs/home?region=us-east-1#/repositories/ and go to your repository.
Click on the spring-demo repository and copy the Reposity URI. You should have one image called latest.

Go back to the "Add Container" page and paste the repository URI into the Image field followed by "/latest".
Click Save.

#Add a new task on AWS

Go to https://console.aws.amazon.com/ecs/home?region=us-east-1#/clusters/default/tasks.
Click on the Tasks take and click "Run new Task".

Select the SpringDemo task in the "Task Definition" field. Make sure number of tasks is 1. Click the "Run Task" button.

The task will now take some time to run.

#Testing the service
On the https://console.aws.amazon.com/ecs/home?region=us-east-1#/clusters/default/tasks page, click on the Running task.

Click on the EC2 Instance id.

Copy the "Public DNS (IPv4)" field.

In another tab, past the DNS + ":8080/time/now"

#Don't forget to stop the task when you are done.