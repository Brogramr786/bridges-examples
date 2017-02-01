package org.stjs.bridge.example.reactjs;

import static org.stjs.bridge.react.React.DOM.button;
import static org.stjs.bridge.react.React.DOM.div;
import static org.stjs.bridge.react.React.DOM.form;
import static org.stjs.bridge.react.React.DOM.input;
import static org.stjs.bridge.react.React.DOM.label;
import static org.stjs.bridge.react.React.DOM.span;
import static org.stjs.javascript.Global.alert;
import static org.stjs.javascript.Global.console;
import static org.stjs.javascript.JSCollections.$array;
import static org.stjs.javascript.JSCollections.$map;

import org.stjs.bridge.example.reactjs.validation.ValidationContext;
import org.stjs.bridge.react.internal.Event;
import org.stjs.bridge.react.internal.IsReactClass;
import org.stjs.bridge.react.internal.ReactClass;
import org.stjs.bridge.react.internal.ReactElement;
import org.stjs.javascript.Array;
import org.stjs.javascript.dom.Input;
import org.stjs.javascript.functions.Callback1;

@IsReactClass
public class MyForm extends ReactClass<MyFormProps, MyFormState> {

	@Override
	public MyFormState getInitialState() {
		ValidationContext v = new ValidationContext();
		v.field("name").required().numeric().min(10);
		v.field("email").required().email();

		return new MyFormState() {
			{
				validation = v;
			}
		};
	}

	@Override
	public ReactElement<?> render() {
		return div(null,
			form($map("onSubmit", (Callback1<Event>) this::submitForm),
				div(
					$map("className", state.validation.className("name")),
					label(null, "Name"),
					input($map(
						"type", (Object) "text",
						"name", "name",
						"onBlur", (Callback1<Event>) this::changeName,
						"defaultValue", state.name
					)),
					displayError("name")
				),
				div(
					$map("className", state.validation.className("email")),
					label(null, "Email"),
					input($map(
						"type", (Object) "text",
						"name", "email",
						"onBlur", (Callback1<Event>) this::changeEmail,
						"defaultValue", state.email
					)),
					displayError("email")
				),

				span(null, "Name:" + state.name + ", Email:" + state.email),
				button(null, "Submit")));
	}

	private Object displayError(String field) {
		if (state.validation.isFieldValid(field)) {
			return null;
		}
		Array<ReactElement<?>> errors = $array();
		state.validation.messages(field).$forEach(msg -> errors.push(span($map("className", "error-message"), msg)));
		return errors;
	}

	private void changeName(Event ev) {
		if (!state.validation.validateField(ev.target)) {
			forceUpdate(null);
			return;
		}

		setState(new MyFormState() {
			{
				name = ((Input) ev.target).value;
			}
		});
	}

	private void changeEmail(Event ev) {
		if (!state.validation.validateField(ev.target)) {
			forceUpdate(null);
			return;
		}

		setState(new MyFormState() {
			{
				email = ((Input) ev.target).value;
			}
		});
	}

	private void submitForm(Event ev) {
		ev.preventDefault();

		if (!state.validation.validateForm(ev.target)) {
			console.info("Not valid");
			forceUpdate(null);
			return;
		}

		alert("Form submitted");
	}

}
