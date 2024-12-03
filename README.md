# NewRelic-ClassTransformer-Error

This is a fork of a repro demonstrating a ClassTransformer error that arises with Kotlin Coroutines and the New Relic Java Agent.  

## Build Application
Navigate into the `app-code` directory. It should be built and run with Java 11.   
Run the following command to build an application jar 

```
./gradlew clean build
```

This will create a jar file named app-code-all.jar in the build/libs directory.  

## Running the application with a New Relic agent
This project already includes the needed New Relic jars and configuration. 

From the app-code directory run the following command:   
```
java -javaagent:../newrelic/newrelic.jar -jar build/libs/app-code-all.jar
```

If you look in New Relic Java agent log (at `newrelic/logs/newrelic_agent.log`), you will find an error like this one:   
```
2024-11-12T15:21:58,256-0600 [40070 1] com.newrelic FINE: Unexpected exception thrown in class transformer: jdk.internal.loader.ClassLoaders$AppClassLoader@14899482--io/ktor/client/engine/cio/CIOEngine$1   
java.lang.ArrayIndexOutOfBoundsException: Index 1 out of bounds for length 1    
&nbsp;&nbsp;&nbsp;at com.newrelic.agent.deps.org.objectweb.asm.Frame.merge(Frame.java:1280) ~[newrelic.jar:8.6.0]    
&nbsp;&nbsp;&nbsp;at com.newrelic.agent.deps.org.objectweb.asm.Frame.merge(Frame.java:1255) ~[newrelic.jar:8.6.0]
```


Remove the kotlin-suspends.jar from the extensions directory and restart and the Class Transformer errors disappear.

Also, try commenting out the suspend function `doWorld` in `main` and replace it with the otherwise identical, 
non-suspend function `otherWorld()`. The errors disappear. 

## Additional info: Agent setup
The New Relic Java Agent agent is included in this repository in the newrelic directory.  It already includes the kotlin-suspends.jar instrumentation module in the extensions directory.    
The newrelic.yml file contains a dummy license key, which is sufficient to throw the error.   

## Additional info: kotlin-suspends module

The `instrumentation-code` directory contains the no-op instrumentation source for `invokeSuspend`. This instrumentation
has already been added to the `newrelic/extensions` directory described above and no further action is needed. 

If you want to modify the instrumentation, cd into `instrumentation-code`, implement your changes, then run `./gradlew clean jar`.
This will output a new jar named `kotlin-suspends.jar` to the `kotlin-suspends/build/libs` directory.

To use this jar, copy `kotlin-suspends.jar` into the `newrelic/extensions` directory. 
