package org.stjs.bridge.example.reactjs;

import static org.stjs.bridge.react.React.DOM.div;
import static org.stjs.bridge.react.React.DOM.form;

import org.stjs.bridge.example.reactjs.bridge.formsy.Input;
import org.stjs.bridge.example.reactjs.bridge.formsy.InputProps;
import org.stjs.bridge.react.React;
import org.stjs.bridge.react.internal.IsReactClass;
import org.stjs.bridge.react.internal.ReactClass;
import org.stjs.bridge.react.internal.ReactElement;

@IsReactClass
public class MyForm extends ReactClass<MyFormProps, MyFormState> {

	@Override
	public ReactElement<?> render() {
		return div(null,
			form(null,
				React.createElement(Input.class, new InputProps() {
					{
						type = "text";
					}
				})));
	}
}
