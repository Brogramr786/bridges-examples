package org.stjs.bridge.example.reactjs;

import static org.stjs.javascript.Global.window;

import org.stjs.bridge.react.React;
import org.stjs.bridge.react.ReactDOM;

public class App {
	public static void start(String elementId) {
		ReactDOM.render(React.createElement(MyForm.class, new MyFormProps()), window.document.getElementById(elementId));
	}
}
