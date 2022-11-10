## Sample API test automation project in Java using Santander NitroAPIKit framework

## Technical Overview

The NitroApiKit created using Java, Spring-Boot, RestAssured, JUnit, Cucumber BDD and Jackson JSON Parser software tool stack.

## Features

- BDD based test case writing
- RestAssured and RestTemplate libraries for REST API calls
- Easy dependency injection to initiate classes
- Junit test runners
- Flexible way to load additional properties
- Kafka and AWS S3 uploads

## Project folders and files usage

    src/test/java - all test automation related files are kept
    src/test/java/feature - folder to keep cucumber feature files
    src/test/java/steps - folder to keep all step definition class files
    sec/test/java/suites - folder to keep JUnit+Cucumber Test runner files
    src/test/resource - folder to keep additional property files and other resources

    CucumberContextConfig.java - this is required for spring boot framework, ensure only one file kept under step definition folder
    bootstrap.properties - system property file to keep all application specific configuration
    kafka.json - configuration detail for kafka upload

    .github/workflows - contains github workflow file for running maven test in GitHub Runner

### Steps to create your own project

1. Download copy of this project to your local (Code --> Download Zip)
2. Extract the zip file in local drive
3. Open the project in Intellij and edit pom.xml file to change the project name as per your project need
   
    ```xml
        <groupId>com.santander.test.automation</groupId>
        <artifactId>nitroapicage</artifactId>
        <version>1.0-SNAPSHOT</version>
    ```
4. Run "git init" command from your local project folder using git bash

    Please note: the default branch will be "master", however as per GitHub standard default branch should be "main"
    Run "git checkout -b main" to change the branch name

5. Create your project remote repository in GitHub
6. Add remote repository reference to your local project folder using following command
    git remote add origin <your remote repo link>
   
7. Push your code to remote repository (GitHub) by running following command
    git push -u origin main

* __While running it from locally:__

    1. Create copy of github-settings.xml and rename as settings-local.xml and change following section
    
        <username>github user id</username>
        <password>github personal access token</password>

        enable <proxies> settings, add your santander user ID and password 

        ***Note: add settings-local.xml in .gitignore file

    2. To build the project run the below command from the project root directory.
        
        ***mvn clean compile -U -s settings-local.xml -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true -DskipTests=true -Dapp.env=cloud***
       
    3. Once the Build is success
    4. Run the sample test by running the below command from the project root directory
       
        ***mvn test -U -s settings-local.xml -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true -Dapp.env=cloud***

## 4. Documentation
* __[User Manual](https://confluence.almuk.santanderuk.corp/display/TAAS/NitroCloudAPI+-+API+Framework+on+Github)__


## 5. Support
    For any support please contact TaaS at TaaSSupport@produban.co.uk




