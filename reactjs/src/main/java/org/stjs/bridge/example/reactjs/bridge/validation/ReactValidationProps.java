package org.stjs.bridge.example.reactjs.bridge.validation;

import org.stjs.bridge.react.internal.Props;
import org.stjs.javascript.annotation.SyntheticType;

@SyntheticType
public class ReactValidationProps extends Props {
	public native void handleValidation(String name);
}
