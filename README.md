# loan-service


# Kalix Workshop - Loan application - Java
Not supported by Lightbend in any conceivable way, not open for contributions. 

## Prerequisite
Java 11 or later<br>
Apache Maven 3.6 or higher<br>
[Kalix CLI](https://docs.kalix.io/kalix/install-kalix.html) <br>
Docker 20.10.14 or higher (to run locally)<br>
You can use Kalix container registry as mentioned below <br>
Access to the `gcr.io/kalix-public` container registry<br>
cURL<br>
IDE / editor<br>

## Setup
### Install Kalix cli
### Create a kalix project from the cli

Check login
``kalix auth login

List kalix projects (if any you have created)
```kalix projects list

Let's create one if none:

Get Organization name
```kalix organizations list

Get the container registry URL (Go to the link for help if needed, https://docs.kalix.io/operations/container-registries.html)
```kalix regions list --organization lightbend

Create project
```kalix projects new training --region aws-eu-central-1 --organization lightbend

Set kalix current project (it shows 'training' as selected project in mine, yours is definitely something else you have created)
```kalix config set project training

## Create kickstart maven project

```mvn archetype:generate \
-DarchetypeGroupId=io.kalix \
-DarchetypeArtifactId=kalix-maven-archetype \
-DarchetypeVersion=1.5.6
```
Define value for property 'groupId': `io.kx`<br>
Define value for property 'artifactId': `loan-application` <br>
Define value for property 'version' 1.0-SNAPSHOT: :<br>
Define value for property 'package' io.kx: : `io.kx.loanapp`<br>


## Update POM file

Check if docker is running with version => 18.03
```docker --version

Configure container registry for your current project
```kalix auth container-registry configure

Show container registry URL
```kalix container-registry print

In `pom.xml`:
<kalixContainerRegistry>kcr.eu-central-1.kalix.io</kalixContainerRegistry>
<kalixOrganization>lightbend</kalixOrganization>
<dockerImage>${kalixContainerRegistry}/${kalixOrganization}/training/${project.artifactId}</dockerImage>


## Import generated project in your IDE/editor
<i><b>Delete all generated sample proto files after done</b></i>


## Compile and complete code generation
```mvn clean compile


## After skeleton files generated for your entity, views or actions is completed, you can run it locally via the following command:
``` mvn kalix:runAll

## Alternatively you can run the following command which will open up a console in your default browser at http://localhost:3000, that is helpful in a lot of ways during application development:
```kalix local run

## Run the following to push image to Kalix container registry
```mvn deploy

## Deploy the Kalix service, use image tag from your earlier command log
```kalix service deploy loanservice ${replace_with_kalix_container_registry_image}


## List kalix service (shows ready if it is ready otherwise unavailable, takes sometime to show it to Ready status)
```kalix service list

### Visit https://docs.kalix.io/ for additional help with developer documentation.


### Test some commands via grpcurl
```kalix service proxy loanservice --grpcui


