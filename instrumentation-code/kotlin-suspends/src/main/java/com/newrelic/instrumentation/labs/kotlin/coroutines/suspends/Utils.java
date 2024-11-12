package com.newrelic.instrumentation.labs.kotlin.coroutines.suspends;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import com.newrelic.agent.bridge.AgentBridge;
import com.newrelic.agent.tracers.ClassMethodSignature;
import com.newrelic.api.agent.NewRelic;

public class Utils {

	public static final String CREATEMETHOD1 = "Continuation at kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt$createCoroutineUnintercepted$$inlined$createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt$4";
	public static final String CREATEMETHOD2 = "Continuation at kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt$createCoroutineUnintercepted$$inlined$createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt$3";
	public static String sub = "createCoroutineFromSuspendFunction";
	private static final String CONT_LOC = "Continuation at";
	private static final String methodDescription = "(Ljava/lang/Object;)Ljava/lang/Object;";
	
	private static Map<Class<?>, Boolean> instrumented = new HashMap<>();

	public static Boolean isInstrumented(Class<?> clazz) {
		Boolean b = instrumented.get(clazz);
		return b;
	}
	
	public static void instrument(Class<?> clazz) {
		if(clazz != null) {
			Method[] methods = clazz.getDeclaredMethods();
			for(Method method : methods) {
				String name = method.getName();
				if(name.equals("invokeSuspend")) {
					AgentBridge.instrumentation.instrument(method, "Custom/KotlinCoroutines/Suspend/" + clazz.getName() + "/invokeSuspend");
					NewRelic.getAgent().getLogger().log(Level.FINE, "Instrumented suspend method {0}.invokeSuspend", clazz.getName());
					instrumented.put(clazz, true);
					return;
				}
			}
			instrumented.put(clazz, false);
		}
	}
	

	public static ClassMethodSignature getInvokeSuspendSignature(String classname) {
		
		return new ClassMethodSignature(classname, "invokeSuspend", methodDescription);
	}
	
	public static String getSuspendString(String cont_string, Object obj) {
		if(cont_string.equals(CREATEMETHOD1) || cont_string.equals(CREATEMETHOD2)) return sub;
		if(cont_string.startsWith(CONT_LOC)) {
			return cont_string;
		}

		int index = cont_string.indexOf('@');
		if(index > -1) {
			return cont_string.substring(0, index);
		}
		
		return obj.getClass().getName();
	}

}
