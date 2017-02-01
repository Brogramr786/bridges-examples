package org.stjs.bridge.example.reactjs.validation;

import static org.stjs.javascript.JSCollections.$map;

import org.stjs.javascript.JSStringAdapter;
import org.stjs.javascript.Map;
import org.stjs.javascript.RegExp;
import org.stjs.javascript.annotation.Namespace;
import org.stjs.javascript.dom.Attr;
import org.stjs.javascript.dom.Element;
import org.stjs.javascript.dom.Input;
import org.stjs.javascript.functions.Function1;

@Namespace("vforms")
public class ValidationRules {
	private Map<String, Function1<Element, String>> validators;

	public ValidationRules() {
		validators = $map();

		RegExp emailRegExp =
				new RegExp(
						"^(([^<>()\\[\\]\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
		rule("email", elm -> !emailRegExp.test(value(elm)) ? fieldName(elm) + " is not a correct email address" : null);

		rule("required", elm -> value(elm).length() == 0 ? fieldName(elm) + " is required" : null);
		rule("alpha", elm -> JSStringAdapter.match(value(elm), new RegExp("[a-zA-Z0-9_]+")) == null ? fieldName(elm)
				+ " is not alphanumeric" : null);
		rule("numeric", elm -> JSStringAdapter.match(value(elm), new RegExp("\\d+")) == null ? fieldName(elm)
				+ " is not numeric" : null);

	}

	public static String value(Element elm) {
		return ((Input) elm).value.trim();
	}

	public static String fieldName(Element elm) {
		Attr attr = elm.attributes.$get("name");
		return attr != null ? attr.value : "";
	}

	public String validate(Element elm, String rule) {
		Function1<Element, String> validator = validators.$get(rule);
		if (validator == null) {
			return "Unknown validation rule '" + rule + "'";
		}
		return validator.$invoke(elm);
	}

	public ValidationRules rule(String rule, Function1<Element, String> validator) {
		validators.$put(rule, validator);
		return this;
	}
}
