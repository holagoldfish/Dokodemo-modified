package com.zendaimoney.Dokodemo.html;

import com.zendaimoney.Dokodemo.engine.Engine;

public class TextInputxpath extends Element {
	public TextInputxpath(String selector, PageModel parent, String name,
			String description) {
		super(selector, parent, name, description);
	}

	public void set(Integer value) throws Exception {
		set(value.toString());
	}

	public void set(String value) throws Exception {
		String message = "set(" + value + ")";
		if (isExist(message)) {
			try {
				engine.set(value);
				logElementPassed(message);
			} catch (Exception ex) {
				logElementFailed(ex, message);
			}
		}
		Engine.clearActionInfo();
	}

	public String value() throws Exception {
		String result = null;
		if (isExist()) {
			try {
				result = engine.value();
				logElementPassed();
			} catch (Exception ex) {
				logElementFailed(ex);
			}
		}
		Engine.clearActionInfo();
		return result;
	}

	public String maxlength() throws Exception {
		String result = null;
		if (isExist()) {
			try {
				result = engine.maxlength();
				logElementPassed();
			} catch (Exception ex) {
				logElementFailed(ex);
			}
		}
		Engine.clearActionInfo();
		return result;
	}

	public void maxlength(String length) throws Exception {
		String message = "maxlength(" + length + ")";
		if (isExist(message)) {
			try {
				engine.maxlength(length);
				logElementPassed(message);
			} catch (Exception ex) {
				logElementFailed(ex, message);
			}
		}
		Engine.clearActionInfo();
	}

	public String readonly() throws Exception {
		String result = null;
		if (isExist()) {
			try {
				result = engine.readonly();
				logElementPassed();
			} catch (Exception ex) {
				logElementFailed(ex);
			}
		}
		Engine.clearActionInfo();
		return result;
	}

	public void readonly(Boolean readonly) throws Exception {
		String message = "readonly(" + readonly + ")";
		if (isExist(message)) {
			try {
				engine.readonly(readonly);
				logElementPassed(message);
			} catch (Exception ex) {
				logElementFailed(ex, message);
			}
		}
		Engine.clearActionInfo();
	}

}