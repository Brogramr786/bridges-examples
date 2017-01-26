package org.stjs.bridge.example.reactjs.bridge.validation;

import org.stjs.bridge.react.internal.IsReactClass;
import org.stjs.bridge.react.internal.Props;
import org.stjs.bridge.react.internal.ReactClass;
import org.stjs.bridge.react.internal.State;
import org.stjs.javascript.Array;
import org.stjs.javascript.annotation.SyntheticType;
import org.stjs.javascript.functions.Callback0;
import org.stjs.javascript.functions.Callback1;

@IsReactClass
@SyntheticType
abstract public class ReactValidationMixin<P extends Props, S extends State> extends ReactClass<P, S> {
	/**
	 * can be a function or a map
	 */
	protected Object validatorTypes;

	abstract protected Object getValidatorData();

	protected native void validate(Callback1<Boolean> callback);

	protected native void validate(String key, Callback1<Boolean> callback);

	protected native boolean isValid();

	protected native boolean isValid(String key);

	protected native Array<String> getValidationMessages();

	protected native Array<String> getValidationMessages(String key);

	protected native void clearValidations();

	protected native void clearValidations(Callback0 callback);

}
