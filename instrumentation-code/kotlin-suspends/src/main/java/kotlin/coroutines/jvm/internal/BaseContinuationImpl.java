package kotlin.coroutines.jvm.internal;

import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.api.agent.NewRelic;
import java.util.logging.Level;

import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Weave(type = MatchType.BaseClass)
public abstract class BaseContinuationImpl {
	
	
	public BaseContinuationImpl(Continuation<Object> c) {

	}


	protected Object invokeSuspend(Object obj) {
		return Weaver.callOriginal();
	}

	public void resumeWith(Object obj) {
		Weaver.callOriginal();
	}
}
