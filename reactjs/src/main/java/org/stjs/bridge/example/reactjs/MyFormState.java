package org.stjs.bridge.example.reactjs;

import org.stjs.bridge.example.reactjs.validation.ValidationContext;
import org.stjs.bridge.react.internal.State;
import org.stjs.javascript.annotation.SyntheticType;

@SyntheticType
public class MyFormState extends State {
	public String name;

	public ValidationContext validation;

	public String email;
}
