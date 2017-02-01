package org.stjs.bridge.example.reactjs.validation;

import static org.stjs.javascript.JSCollections.$array;

import org.stjs.javascript.Array;
import org.stjs.javascript.JSGlobal;
import org.stjs.javascript.JSStringAdapter;
import org.stjs.javascript.RegExp;
import org.stjs.javascript.dom.Attr;
import org.stjs.javascript.dom.Element;
import org.stjs.javascript.dom.Input;
import org.stjs.javascript.functions.Function1;

public class ValidationRule {
	private final Array<Function1<Element, String>> validators;

	public ValidationRule() {
		validators = $array();
	}

	public static String value(Element elm) {
		return ((Input) elm).value.trim();
	}

	public static double number(Element elm) {
		return JSGlobal.parseFloat(((Input) elm).value.trim());
	}

	public static String fieldName(Element elm) {
		Attr attr = elm.attributes.$get("name");
		return attr != null ? attr.value : "";
	}

	public ValidationRule required() {
		validators.push(elm -> value(elm).length() == 0 ? fieldName(elm) + " is required" : null);
		return this;
	}

	public ValidationRule alpha() {
		validators.push(elm -> JSStringAdapter.match(value(elm), new RegExp("[a-zA-Z0-9_]+")) == null ? fieldName(elm)
				+ " is not alphanumeric" : null);
		return this;
	}

	public ValidationRule numeric() {
		validators.push(elm -> JSStringAdapter.match(value(elm), new RegExp("\\d+")) == null ? fieldName(elm)
				+ " is not numeric" : null);
		return this;
	}

	public ValidationRule email() {
		RegExp emailRegExp =
				new RegExp(
						"^(([^<>()\\[\\]\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
		Function1<Element, String> validator = elm -> !emailRegExp.test(value(elm)) ? fieldName(elm) + " is not a correct email address" : null;
		validators.push(validator);
		return this;
	}

	public ValidationRule min(double min) {
		validators.push(elm -> number(elm) < min ? fieldName(elm) + " is too small" : null);
		return this;
	}

	public ValidationRule max(double max) {
		validators.push(elm -> number(elm) > max ? fieldName(elm) + " is too big" : null);
		return this;
	}

	public ValidationRule rule(Function1<Element, String> rule) {
		validators.push(rule);
		return this;
	}

	public Array<String> validate(Element elm) {
		Array<String> fieldErrors = $array();
		validators.$forEach(v -> {
			String error = v.$invoke(elm);
			if (error != null) {
				fieldErrors.push(error);
			}
		});

		return fieldErrors;
	}
}
