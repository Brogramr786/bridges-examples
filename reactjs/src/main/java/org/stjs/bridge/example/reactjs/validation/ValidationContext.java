package org.stjs.bridge.example.reactjs.validation;

import static org.stjs.javascript.Global.console;
import static org.stjs.javascript.JSCollections.$array;
import static org.stjs.javascript.JSCollections.$map;
import static org.stjs.javascript.JSGlobal.$or;

import org.stjs.javascript.Array;
import org.stjs.javascript.Map;
import org.stjs.javascript.annotation.Namespace;
import org.stjs.javascript.dom.Element;
import org.stjs.javascript.dom.Form;

@Namespace("vforms")
public class ValidationContext {
	private Map<String, Array<String>> errors;
	private Map<String, ValidationRule> validators;
	private String errorClass = "error";

	public ValidationContext() {
		errors = $map();
		validators = $map();
	}

	public ValidationRule field(String key) {
		ValidationRule validationRule = new ValidationRule();
		validators.$put(key, validationRule);
		return validationRule;
	}

	public String className(String key) {
		return isFieldValid(key) ? "" : errorClass;
	}

	public String getErrorClass() {
		return errorClass;
	}

	public void setErrorClass(String errorClass) {
		this.errorClass = errorClass;
	}

	@SuppressWarnings("unchecked")
	public Array<String> messages(String key) {
		return (Array<String>) $or(errors.$get(key), $array());
	}

	public void clear() {
		errors = $map();
	}

	public boolean isFieldValid(String key) {
		Array<String> fieldErrors = errors.$get(key);
		return fieldErrors == null || fieldErrors.$length() == 0;
	}

	public boolean validateField(Element elm) {
		ValidationRule fieldValidators = validators.$get(ValidationRules.fieldName(elm));
		if (fieldValidators == null) {
			console.warn("No validators for field " + ValidationRules.fieldName(elm));
			return true;
		}

		Array<String> fieldErrors = fieldValidators.validate(elm);

		if (fieldErrors.$length() > 0) {
			errors.$put(ValidationRules.fieldName(elm), fieldErrors);
		} else {
			errors.$delete(ValidationRules.fieldName(elm));
		}

		return fieldErrors.$length() == 0;
	}

	public boolean validateForm(Element elm) {
		Form form = (Form) elm;
		for (String field : validators) {
			Element fieldInput = form.elements.$get(field);
			validateField(fieldInput);
		}
		return isFormValid();
	}

	public boolean isFormValid() {
		for (String key : errors) {
			if (errors.$get(key) != null && errors.$get(key).$length() > 0) {
				return false;
			}
		}
		return true;
	}

}
