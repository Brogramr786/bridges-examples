package org.stjs.bridge.example.reactjs.bridge.formsy;

import org.stjs.bridge.react.internal.ReactClass;
import org.stjs.bridge.react.internal.State;
import org.stjs.javascript.Array;

public abstract class FormsyMixinComponent<P extends FormsyMixinProps, S extends State> extends ReactClass<P, S> {
	public native String getValue();

	public native void setValue(String value);

	public native void resetValue();

	public native String getErrorMessage();

	public native Array<String> getErrorMessages();

	public native boolean isValid();

	public native boolean isValidValue();

	public native boolean isRequired();

	public native boolean showRequired();

	public native boolean showError();

	public native boolean isPristine();

	public native boolean isFormDisabled();

	public native boolean isFormSubmitted();

	public native boolean validate();
}
