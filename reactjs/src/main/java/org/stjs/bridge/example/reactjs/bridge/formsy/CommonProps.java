package org.stjs.bridge.example.reactjs.bridge.formsy;

import org.stjs.javascript.Array;
import org.stjs.javascript.annotation.SyntheticType;
import org.stjs.javascript.functions.Callback0;

@SyntheticType
public class CommonProps extends FormsyMixinProps {
	public Array<String> errorMessages;//		false
	public String help;//false
	public String label;//false

	/**
	 * horizontal, vertical, elementOnly
	 */
	public String layout;//false
	public Boolean showErrors;//	false	false
	public Callback0 onChange;
	public Callback0 onSetValue;

}
