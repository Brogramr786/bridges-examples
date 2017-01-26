package org.stjs.bridge.example.reactjs;

import static org.stjs.bridge.react.React.DOM.div;
import static org.stjs.bridge.react.React.DOM.form;
import static org.stjs.bridge.react.React.DOM.input;
import static org.stjs.javascript.JSCollections.$map;

import org.stjs.bridge.example.reactjs.bridge.validation.ReactValidationMixin;
import org.stjs.bridge.react.internal.Event;
import org.stjs.bridge.react.internal.IsReactClass;
import org.stjs.bridge.react.internal.ReactElement;
import org.stjs.javascript.dom.Input;
import org.stjs.javascript.functions.Callback1;

@IsReactClass
public class MyForm extends ReactValidationMixin<MyFormProps, MyFormState> {

	@Override
	public ReactElement<?> render() {
		return div(null,
			form(null,
				input($map(
					"type", (Object) "text",
					"onChange", (Callback1<Event>) ev -> changeName(((Input) ev.target).name),
					"onBlur", (Callback1<Event>) ev -> props.handleValidation("name"),
					"value", state.name
				))));
	}

	private void changeName(String theName) {
		setState(new MyFormState() {
			{
				name = theName;
			}
		});
	}

	@Override
	protected Object getValidatorData() {
		return state;
	}
}
