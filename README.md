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

Navigate to http://localhost:8080.    
This results in Page Not loading error page.  

If you look in New Relic Java agent log, you will find several Class Transformer errors like this one:   
2024-11-12T15:21:58,256-0600 [40070 1] com.newrelic FINE: Unexpected exception thrown in class transformer: jdk.internal.loader.ClassLoaders$AppClassLoader@14899482--io/ktor/client/engine/cio/CIOEngine$1   
java.lang.ArrayIndexOutOfBoundsException: Index 1 out of bounds for length 1    
&nbsp;&nbsp;&nbsp;at com.newrelic.agent.deps.org.objectweb.asm.Frame.merge(Frame.java:1280) ~[newrelic.jar:8.15.0]    
&nbsp;&nbsp;&nbsp;at com.newrelic.agent.deps.org.objectweb.asm.Frame.merge(Frame.java:1255) ~[newrelic.jar:8.15.0]    

Remove the kotlin-suspends.jar from the extensions directory and restart and the Class Transformer errors disappear and the Web Page loads as expected.   
