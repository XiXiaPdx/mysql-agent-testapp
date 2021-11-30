Sample spring app for testing Java Agent sql obfuscation of large parameters (10MB +)
Original source code from: https://github.com/spring-guides/gs-accessing-data-mysql

There are two services - mysql and spring-app.  The spring-app has an endpoint `/add` that will insert 
a large text string into the mysql database.  

The large text string is created from `/resources/largeFile.txt` 

1. Copy the java agent jar into `/newrelic` and save the jar filename as `newrelic.jar`. Configure the jar
   with `newrelic.yml` or environment properties on app start up. 

2. Start up mysql:
`docker compose up`

3. In Intellij, create and edit a run configuration for the Spring app. Add the necessary
VM options for `-javaagent` and remote debugger with java agent source. 

4. Run the Spring app

At this point, the Spring app should have successfully started with the java agent and it
should be connected to the mysql db. 

5. Connect with remote debugger from java agent project. Set your breakpoints.

6. Execute test script 
`./runtest.sh`

