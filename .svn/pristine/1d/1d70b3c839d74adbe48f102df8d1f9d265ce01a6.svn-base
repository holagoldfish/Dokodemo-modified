package com.zendaimoney.Dokodemo.html;

import org.openqa.selenium.WebElement;

import com.zendaimoney.Dokodemo.engine.Engine;

/*
 * 所有控件的基类，用于PageModel的控件，默认类型的控件
 */
public class Element extends Locator {
	public Element(String selector, PageModel parent, String name,
			String description) {
		super(selector, parent, name, description);
	}

	public boolean exist() {
		locate();
		return engine != null;
	}

	public boolean empty() {
		locate();
		return engine == null;
	}

	public void click() throws Exception {
		if (isExist()) {
			try {
				engine.click();
				logElementPassed();
			} catch (Exception ex) {
				logElementFailed(ex);
			}
		}
		Engine.clearActionInfo();
	}

	public void click_js() throws Exception {
		if (isExist()) {
			try {
				engine.click_js();
				logElementPassed();
			} catch (Exception ex) {
				logElementFailed(ex);
			}
		}
		Engine.clearActionInfo();
	}

	public void click_ie() throws Exception {
		if (isExist()) {
			try {
				engine.click_ie();
				logElementPassed();
			} catch (Exception ex) {
				logElementFailed(ex);
			}
		}
		Engine.clearActionInfo();
	}

	public void submit() throws Exception {
		if (isExist()) {
			try {
				engine.submit();
				logElementPassed();
			} catch (Exception ex) {
				logElementFailed(ex);
			}
		}
		Engine.clearActionInfo();
	}

	public void focus() throws Exception {
		if (isExist()) {
			try {
				engine.focus();
				logElementPassed();
			} catch (Exception ex) {
				logElementFailed(ex);
			}
		}
		Engine.clearActionInfo();
	}

	public String text() throws Exception {
		String result = null;
		if (isExist()) {
			try {
				result = engine.text();
				logElementPassed();
			} catch (Exception ex) {
				logElementFailed(ex);
			}
		}
		Engine.clearActionInfo();
		return result;
	}

	public String getAttribute(String name) throws Exception {
		String result = null;
		if (isExist("getAttribute(" + name + ")")) {
			try {
				result = engine.getAttribute(name);
				logElementPassed("getAttribute(" + name + ")");
			} catch (Exception ex) {
				logElementFailed(ex, "getAttribute(" + name + ")");
			}
		}
		Engine.clearActionInfo();
		return result;
	}

	public void setAttribute(String js) throws Exception {
		if (isExist("setAttribute(" + name + ")")) {
			try {
				engine.setAttribute(js);
				logElementPassed("setAttribute(" + name + ")");
			} catch (Exception ex) {
				logElementFailed(ex, "setAttribute(" + name + ")");
			}
		}
		Engine.clearActionInfo();
	}

	public String control() throws Exception {
		String result = null;
		if (isExist()) {
			try {
				result = engine.control();
				logElementPassed();
			} catch (Exception ex) {
				logElementFailed(ex);
			}
		}
		Engine.clearActionInfo();
		return result;
	}

	public String className() throws Exception {
		String result = null;
		if (isExist()) {
			try {
				result = engine.className();
				logElementPassed();
			} catch (Exception ex) {
				logElementFailed(ex);
			}
		}
		Engine.clearActionInfo();
		return result;
	}

	public String id() throws Exception {
		String result = null;
		if (isExist()) {
			try {
				result = engine.id();
				logElementPassed();
			} catch (Exception ex) {
				logElementFailed(ex);
			}
		}
		Engine.clearActionInfo();
		return result;
	}

	public String innerHTML() throws Exception {
		String result = null;
		if (isExist()) {
			try {
				result = engine.innerHTML();
				logElementPassed();
			} catch (Exception ex) {
				logElementFailed(ex);
			}
		}
		Engine.clearActionInfo();
		return result;
	}

	public String get(String name) throws Exception {
		String result = null;
		if (isExist("get(" + name + ")")) {
			try {
				result = engine.get(name);
				logElementPassed("get(" + name + ")");
			} catch (Exception ex) {
				logElementFailed(ex, "get(" + name + ")");
			}
		}
		Engine.clearActionInfo();
		return result;
	}

	public String get_style(String name) throws Exception {
		String result = null;
		if (isExist("get_style(" + name + ")")) {
			try {
				result = engine.get_style(name);
				logElementPassed("get_style(" + name + ")");
			} catch (Exception ex) {
				logElementFailed(ex, "get_style(" + name + ")");
			}
		}
		Engine.clearActionInfo();
		return result;
	}

	public void sendKey(String name) throws Exception {
		if (isExist("sendKey(" + name + ")")) {
			try {
				engine.sendKey(name);
				logElementPassed("sendKey(" + name + ")");
			} catch (Exception ex) {
				logElementFailed(ex, "sendKey(" + name + ")");
			}
		}
		Engine.clearActionInfo();
	}

	public void drag(WebElement element) throws Exception {
		if (isExist()) {
			try {
				engine.drag(element);
				logElementPassed();
			} catch (Exception ex) {
				logElementFailed(ex);
			}
		}
		Engine.clearActionInfo();
	}

	public void mouseover() throws Exception {
		if (isExist()) {
			try {
				engine.mouseover();
				logElementPassed();
			} catch (Exception ex) {
				logElementFailed(ex);
			}
		}
		Engine.clearActionInfo();
	}

	public void doubleclick() throws Exception {
		if (isExist()) {
			try {
				engine.doubleclick();
				logElementPassed();
			} catch (Exception ex) {
				logElementFailed(ex);
			}
		}
		Engine.clearActionInfo();
	}

	public void fire_event(String name) throws Exception {
		if (isExist("fire_event(" + name + ")")) {
			try {
				engine.fire_event(name);
				logElementPassed("fire_event(" + name + ")");
			} catch (Exception ex) {
				logElementFailed(ex, "fire_event(" + name + ")");
			}
		}
		Engine.clearActionInfo();
	}

	public void scrollIntoView(Boolean top) throws Exception {
		if (isExist("scrollIntoView(" + top + ")")) {
			try {
				engine.scrollIntoView(top);
				logElementPassed("scrollIntoView(" + top + ")");
			} catch (Exception ex) {
				logElementFailed(ex, "scrollIntoView(" + top + ")");
			}
		}
		Engine.clearActionInfo();
	}

	public void display() throws Exception {
		if (isExist()) {
			try {
				engine.display();
				logElementPassed();
			} catch (Exception ex) {
				logElementFailed(ex);
			}
		}
		Engine.clearActionInfo();
	}

	public void jsError() {
		engine.js_error();
	}
}
