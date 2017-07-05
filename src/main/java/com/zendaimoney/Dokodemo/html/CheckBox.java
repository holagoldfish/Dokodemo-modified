package com.zendaimoney.Dokodemo.html;

import com.zendaimoney.Dokodemo.engine.Engine;

public class CheckBox extends Element {
	public CheckBox(String selector, PageModel parent, String name,
			String description) {
		super(selector, parent, name, description);
	}

	public void set() throws Exception {
		if (isExist()) {
			try {
				engine.check();
				logElementPassed();
			} catch (Exception ex) {
				logElementFailed(ex);
			}
		}
		Engine.clearActionInfo();
	}

	public void clear() throws Exception {
		if (isExist()) {
			try {
				engine.uncheck();
				logElementPassed();
			} catch (Exception ex) {
				logElementFailed(ex);
			}
		}
		Engine.clearActionInfo();
	}

	public boolean checked() throws Exception {
		boolean result = false;
		if (isExist()) {
			try {
				result = engine.checked();
				logElementPassed();
			} catch (Exception ex) {
				logElementFailed(ex);
			}
		}
		Engine.clearActionInfo();
		return result;
	}
}
