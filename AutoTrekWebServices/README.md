# AutoTrekWebServices
This repository contains the Web Services application for AutoTrek.

# Pre-install Software
* Git (clearly) - [download](https://git-scm.com/downloads)
* JDK (1.8) - [download](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* Vagrant - [download](https://www.vagrantup.com/downloads.html)
    * VirtualBox - [download](https://www.virtualbox.org/wiki/Downloads)
        * Don't forget to also install the Extensions

# Building
Maven is the tool used. There is no need to download and install as mvnw has been provided. This will automatically download and install the appropriate versions. From the top level directory you should be able to run the following command to compile, test, and package the application.

    prompt> ./mvnw clean install

# Running
There are other systems needed to run the web services, however these are provided using a Vagrant managed VM. From the top level of the project you should be able to create/run this VM with the following command.

    prompt> vagrant up

Once the machine is up and running, you can run the Web Services application. By default it will listen for connections on http://localhost:8080.

**The application will not run locally if there are not dependant services running that it can connect to. That is the purpose of the Vagrant configuration**

    prompt> ./mvnw spring-boot:run
