package kotlin.coroutines.jvm.internal;

//import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.kotlin.coroutines.suspends.SuspendIgnores;
import com.newrelic.instrumentation.labs.kotlin.coroutines.suspends.Utils;

import kotlin.coroutines.Continuation;

@Weave(type = MatchType.BaseClass)
public abstract class BaseContinuationImpl {
	
	
	public BaseContinuationImpl(Continuation<Object> c) {
		
//		if (!SuspendIgnores.ignoreSuspend(this)) {
//			Boolean b = Utils.isInstrumented(getClass());
//			if (b == null) {
//				Utils.instrument(getClass());
//			} 
//		}
	}

	protected Object invokeSuspend(Object obj) {
		Object resultObj = Weaver.callOriginal();
		
		return resultObj;
	}
	
//	@Trace
	public void resumeWith(Object obj) {
		Weaver.callOriginal();
	}
}
