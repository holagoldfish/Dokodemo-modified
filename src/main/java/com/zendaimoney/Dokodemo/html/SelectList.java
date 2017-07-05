package com.zendaimoney.Dokodemo.html;

import java.util.ArrayList;

import com.zendaimoney.Dokodemo.engine.Engine;

public class SelectList extends Element {
	public SelectList(String selector, PageModel parent, String name,
			String description) {
		super(selector, parent, name, description);
	}

	/**
	 * @param <T>
	 * @throws Exception *
	***/
	public <T> void select(T value) throws Exception {
		String message = "set(" + value + ")";
		if (isExist(message)) {
			try {
				engine.select_by_value(value.toString());
				logElementPassed(message);
			} catch (Exception ex) {
				logElementFailed(ex, message);
			}
		}
		Engine.clearActionInfo();
	}

	public void set(String value) throws Exception {
		String message = "set(" + value + ")";
		if (isExist(message)) {
			try {
				engine.select(value);
				logElementPassed(message);
			} catch (Exception ex) {
				logElementFailed(ex, message);
			}
		}
		Engine.clearActionInfo();
	}

	public void set(Integer index) throws Exception {
		String message = "set(" + index + ")";
		if (isExist(message)) {
			try {
				engine.select_by_index(index);
				logElementPassed(message);
			} catch (Exception ex) {
				logElementFailed(ex, message);
			}
		}
		Engine.clearActionInfo();
	}

	public String selected_value() throws Exception {
		String result = null;
		if (isExist()) {
			try {
				result = engine.selected_value().get(0);
				logElementPassed();
			} catch (Exception ex) {
				logElementFailed(ex);
			}
		}
		Engine.clearActionInfo();
		return result;
	}

	public String selected_innervalue() throws Exception {
		String result = null;
		if (isExist()) {
			try {
				result = engine.selected_innervalue().get(0);
				logElementPassed();
			} catch (Exception ex) {
				logElementFailed(ex);
			}
		}
		Engine.clearActionInfo();
		return result;
	}

	public ArrayList<String> options() throws Exception {
		ArrayList<String> result = null;
		if (isExist()) {
			try {
				result = engine.options();
				logElementPassed();
			} catch (Exception ex) {
				logElementFailed(ex);
			}
		}
		Engine.clearActionInfo();
		return result;
	}
}