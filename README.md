# NewRelic-ClassTransformer-Error
The app-code directory contains an simple Ktor/Kotlin application that will generate the Class Transformer error into the New Relic agent log if the kotlin-suspends.jar instrumentation is applied. 
  
## Build Application
Navigate into the app-code directory.   
Run the following command to build an application jar    
./gradlew clean build     

This will create a jar file named app-code-all.jar in the build/libs directory.   

## Agent setup
The New Relic Java Agent agent is included in this repository in the newrelic directory.  It already includes the kotlin-suspends.jar instrumentation module in the extensions directory.    
The only setup that is needed is to edit newrelic.yml and enter a valid license key.   

## Runnig the application with a New Relic agent
The application requires Java 11 or later.   
From the app-code directory run the following command:   
java -javaagent:../newrelic/newrelic.jar -jar build/libs/app-code-all.jar

