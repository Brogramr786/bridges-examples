package org.stjs.bridge.example.reactjs.bridge.formsy;

import org.stjs.bridge.react.internal.Props;
import org.stjs.javascript.Map;
import org.stjs.javascript.annotation.SyntheticType;

@SyntheticType
public class FormsyMixinProps extends Props {
	public String name;
	public String value;
	public Map<String, String> validations;
	public String validationError;
	public Map<String, String> validationErrors;
	public Boolean required;
	public boolean formNoValidate;
}
