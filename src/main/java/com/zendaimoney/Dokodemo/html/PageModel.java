package com.zendaimoney.Dokodemo.html;

import com.zendaimoney.Dokodemo.engine.Engine;

public class PageModel extends Locator {
	public PageModel(String selector, PageModel parent, String name,
			String description) {
		super(selector, parent, name, description);
	}

	public String text() {
		String result = null;
		if (exist()) {
			Engine.clearActionInfo();
			result = engine.text();
			logElementPassed();
		}
		return result;
	}

	public String getAttribute(String name) {
		String result = null;
		if (exist()) {
			Engine.clearActionInfo();
			result = engine.getAttribute(name);
			logElementPassed();
		}
		return result;
	}

	public boolean exist() {
		locate();
		return engine != null;
	}

	public boolean empty() {
		locate();
		return engine == null;
	}
}
