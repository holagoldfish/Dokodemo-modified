package com.zendaimoney.Dokodemo.html;

import com.zendaimoney.Dokodemo.engine.Engine;



public class NoWaitElement extends Element {
	public NoWaitElement(String selector, PageModel parent, String name,
			String description) {
		super(selector, parent, name, description);
	}

	@Override
	public void click() throws Exception {
		if (isExist()) {
			engine.click();
			try {
				// logElementPassed(url);
			} catch (Exception ex) {
				logElementFailed(ex);
			}
		}
	}

	

	public String get_content() throws Exception {
		String result = null;
		if (isExist()) {
			try {
				result = engine.get_content();
				// logElementPassed();
			} catch (Exception ex) {
				logElementFailed(ex);
			}
		}
		return result;
	}

	

	public void deal_dialog(String type) throws Exception {
		String message = "deal_dialog(" + type + ")";
		if (isExist(message)) {
			try {
				engine.dealdialog(type);
				logElementPassed(message);
			} catch (Exception ex) {
				logElementFailed(ex, message);
			}
		}
		Engine.clearActionInfo();
	}
	
	public void deal_prompt(String input,boolean isYes) throws Exception {
		String message = "deal_prompt(" + input +"," + isYes + ")";
		if (isExist(message)) {
			try {
				engine.dealPrompt(input,isYes);
				logElementPassed(message);
			} catch (Exception ex) {
				logElementFailed(ex, message);
			}
		}
		Engine.clearActionInfo();
	}
	
	
	public boolean isAlertExist() throws Exception {
		boolean result = false;
		if (isExist()) {
			try {
				result  = engine.is_alert_exist();
			} catch (Exception ex) {
				logElementFailed(ex);
			}
		}
		return result;
	}
}
