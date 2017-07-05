package com.zendaimoney.Dokodemo.engine;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;

import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.Select;

import com.zendaimoney.Dokodemo.engine.Selector.Scope;
import com.zendaimoney.Dokodemo.util.FileUtils;


/*
 * 代表一个IE HTML节点
 */
public class ElementEngine extends Engine {
	private WebDriver driver;
	private WebElement current;
	private ArrayList<Integer> iframeIndex;
	private String actionInfo;// 组合了WebElement
	private static final String CONFIRM = "确定";
	private static final String CANCEL = "取消";
	// private static final String ENTER = "{Enter}";
	protected static final Log logger = LogFactory.getLog(ElementEngine.class);

	public ElementEngine(WebDriver driver, WebElement current,
			ArrayList<Integer> iframeIndex, String actionInfo) {
		this.driver = driver;
		this.current = current;
		this.iframeIndex = iframeIndex;
		this.actionInfo = actionInfo;
	}

	/**
	 * 会真实地去查找
	 */
	public ArrayList<Engine> children() {
		ArrayList<Engine> engineArrayList = new ArrayList<Engine>();
		ElementEngine elementEngine = null;
		if (current.findElements(By.xpath("./*")).isEmpty()) {
			String d_name = control();
			if (d_name.equalsIgnoreCase("iframe")) {
				iframeIndex.add(get_current_iframe_number());
				jump_into_iframe(iframeIndex);
				for (WebElement element : driver.findElements(By
						.xpath("./html"))) {
					elementEngine = new ElementEngine(driver, element,
							iframeIndex, actionInfo);
					engineArrayList.add(elementEngine);
				}
			}
		} else {
			for (WebElement element : current.findElements(By.xpath("./*"))) {
				elementEngine = new ElementEngine(driver, element, iframeIndex,
						actionInfo);
				engineArrayList.add(elementEngine);
			}
		}
		return engineArrayList;
	}

	public Integer get_current_iframe_number() {
		Map<WebElement, Integer> iframeIndexMap = new HashMap<WebElement, Integer>();
		int index = 0;
		for (WebElement element : driver.findElements(By.tagName(control()
				.toLowerCase()))) {
			iframeIndexMap.put(element, index);
			index = index + 1;
		}
		return iframeIndexMap.get(current);
	}

	@Override
	public void closeall() {
		driver.quit();
	}

	@Override
	public void close() {
		driver.close();
	}

	@Override
	public boolean isEmpty(String... message) {
		if (current == null) {
			String msg = "";
			if (null == message || message.length == 0) {
				msg = Thread.currentThread().getStackTrace()[2].getMethodName()
						+ "()";
			} else {
				msg = message[0];
			}
			logger.warn("undefined method" + msg + " for empty element!");
			return true;
		}
		return false;
	}

	@Override
	public String control() {
		if (!isEmpty()) {
			return current.getTagName();
		}
		return null;
	}

	@Override
	public String className() {
		if (!isEmpty()) {
			return current.getAttribute("class");
		}
		return null;
	}

	@Override
	public String id() {
		if (!isEmpty()) {
			return current.getAttribute("id");
		}
		return null;
	}

	@Override
	public void submit() {
		if (!isEmpty()) {
			scrollIntoView(true);
			current.submit();
		}
	}

	@Override
	protected Engine parent() {
		if (current.getTagName() == "html") {
			return null;
		} else {
			WebElement element = (WebElement) executeJs(
					"return arguments[0].parentNode;", current);
			ElementEngine ieEngine = new ElementEngine(driver, element,
					iframeIndex, actionInfo);
			return ieEngine;
		}
	}

	@Override
	protected Engine next() {
		WebElement element = (WebElement) executeJs(
				"return arguments[0].nextSibling;", current);
		if (element != null) {
			ElementEngine ieEngine = new ElementEngine(driver, element,
					iframeIndex, actionInfo);
			return ieEngine;
		} else {
			return null;
		}

	}

	@Override
	public boolean visible() {
		if (!isEmpty()) {
			return current.isDisplayed();
		}
		return false;
	}

	@Override
	public void click() {
		if (!isEmpty()) {
			scrollIntoView(true);
			if (current.isDisplayed()) {
				current.click();
			} else {
				this.executeJs("arguments[0].click();", current);
			}
		}
	}

	@Override
	public void click_js() {
		if (!isEmpty()) {
			this.executeJs("arguments[0].click();", current);
		}
	}

	@Override
	public void click_ie() {
		if (!isEmpty()) {
			scrollIntoView(true);
			if (current.isDisplayed()) {
				int before = driver.getWindowHandles().size();
				String beforeUrl = driver.getCurrentUrl();
				current.click();
				int after = driver.getWindowHandles().size();
				String afterUrl = driver.getCurrentUrl();
				if ((after == before) && (beforeUrl.equals(afterUrl))) {
					current.click();
					// System.out.println("xxxxclick again");
				}
			} else {
				this.executeJs("arguments[0].click();", current);
			}
		}
	}

	private void waitfor() {
		if (executeJs("return window.document.readyState") != "complete") {
			driver.navigate().refresh();
			try {// sleep 3s
				Thread.sleep(3000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			waitfor();
		}
	}

	@Override
	public String text() {
		if (!isEmpty()) {
			return current.getText();
		}
		return null;
	}

	@Override
	public void setAttribute(String js) {
		if (!isEmpty()) {
			this.executeJs(js, current);
		}
	}

	@Override
	public void display() {
		if (!isEmpty()) {
			this.executeJs("arguments[0].style.display = 'block';", current);
		}
	}

	@Override
	public String getAttribute(String name) {
		if (!isEmpty()) {
			return current.getAttribute(name);
		}
		return null;
	}

	@Override
	public String value() {
		if (!isEmpty()) {
			return current.getAttribute("value");
		}
		return null;
	}

	@Override
	public String get(String name) {
		if (!isEmpty()) {
			if (name.equalsIgnoreCase("text"))
				return text();
			else if (name.equalsIgnoreCase("outerHTML"))
				return outerHTML();
			else
				return getAttribute(name);
		}
		return null;
	}

	public String innerHTML() {
		return current.getAttribute("innerHTML");
	}

	private String outerHTML() {
		return (String) this.executeJs("return arguments[0].outerHTML;",
				current);
	}

	@Override
	public String get_style(String name) {
		if (!isEmpty()) {
			return current.getCssValue(name);
		}
		return null;
	}

	@Override
	public void set(String value) {
		if (!isEmpty()) {
//			scrollIntoView(true);
			current.clear();
			current.sendKeys(value);
		}
	}

	@Override
	public void setInnerText(String value) {
		if (!isEmpty()) {
			this.executeJs("arguments[0].textContent = arguments[1];", current,
					value);
		}
	}


	@Override
	public void check() {
		if (!isEmpty()) {
			if (current.isSelected()) {
				return;
			} else {
				current.click();
			}
		}
	}

	@Override
	public void uncheck() {
		if (!isEmpty()) {
			if (current.isSelected()) {
				current.click();
			} else {
				return;
			}
		}
	}

	@Override
	public void select(String value) {
		if (!isEmpty()) {
			int index = 0;
			for (WebElement element : current.findElements(By.xpath("./*"))) {
				if (value.equalsIgnoreCase(element.getText())) {
					executeJs("arguments[0].selectedIndex = arguments[1];",
							current, index);
					return;
				}
				index = index + 1;
			}
			throw new RuntimeException("undefined 'value' for element!");
		}
	}

	@Override
	public void select_by_value(String value) {
		if (!isEmpty()) {
			Select select = new Select(current);
			select.selectByVisibleText(value);
		}
	}

	private Object executeJs(String script, Object... args) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(script, args);
	}

	private Object executeAsyncJs(String script, Object... args) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeAsyncScript(script, args);
	}

	public void select_by_index(Integer index) {
		if (!isEmpty()) {
			executeJs("arguments[0].selectedIndex = arguments[1];", current,
					index);
			return;
		}
		throw new RuntimeException("'index' is out of array!");
	}

	/*
	 * 获取selectList选中框的text值
	 */
	@Override
	public ArrayList<String> selected_value() {
		ArrayList<String> eles = new ArrayList<String>();
		if (!isEmpty()) {
			for (WebElement element : current.findElements(By.xpath("./*"))) {
				if (element.isSelected()) {
					eles.add(element.getText().toString());
				}
			}
		}
		return eles;
	}

	/*
	 * 获取selectList选中框的value值
	 */
	@Override
	public ArrayList<String> selected_innervalue() {
		ArrayList<String> eles = new ArrayList<String>();
		if (!isEmpty()) {
			for (WebElement element : current.findElements(By.xpath("./*"))) {
				if (element.isSelected()) {
					eles.add(element.getAttribute("value"));
				}
			}
		}
		return eles;
	}

	@Override
	public ArrayList<String> options() {
		ArrayList<String> eles = new ArrayList<String>();
		for (WebElement element : current.findElements(By.xpath("./*"))) {
			eles.add(element.getText().toString());
		}
		return eles;
	}

	

	private static void SendKeys(String inputText) {
		try {
			Robot robot = new Robot();
			String text = inputText.toUpperCase();
			for (int i = 0; i < text.length(); i++) {
				robot.keyPress(text.charAt(i));
			}
		} catch (java.awt.AWTException ex) {
			logger.error(ex.getMessage());
		}
	}

	public void sendKey(String name) {
		try {
			Robot robot = new Robot();
			if (name.equalsIgnoreCase("{Enter}")) {
				robot.keyPress(KeyEvent.VK_ENTER);
			} else if (name.equalsIgnoreCase("{Tab}")) {
				robot.keyPress(KeyEvent.VK_TAB);
			}
		} catch (java.awt.AWTException ex) {
			logger.error(ex.getMessage());
		}
	}

	@Override
	public void dealdialog(String type) {
		if (type == CONFIRM) {
			driver.switchTo().alert().accept();
		} else if (type == CANCEL) {
			driver.switchTo().alert().dismiss();
		}
	}
	
	 @Override
     public String dealPrompt(String input, boolean isYes) {
            driver.switchTo().alert().sendKeys(input);
           String promptMessage=driver.switchTo().alert().getText();
            if(isYes)
                 driver.switchTo().alert().accept();
            else
                 driver.switchTo().alert().dismiss();
            return promptMessage;
     }


	/**
	 * for alert_get_content use
	 */
	@Override
	public String get_content() {
		String content = driver.switchTo().alert().getText();
		return content;
	}

	@Override
	public boolean is_alert_exist() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException Ex) {
			return false;
		}
	}

	/**
	 * for js_error use
	 */
	@Override
	public void js_error() {
		StringBuffer script = new StringBuffer(
				"var src=document.createElement('script');");
		script.append("src.type='text/javascript';");
		script
				.append("src.text = 'onerror = handleErr;function handleErr(msg,url,line){err = \"Msg:\"+msg+\" Url:\"+url+\" Line:\"+line;document.title=err;return true;}';");
		script.append("var head = document.getElementsByTagName('head')[0];");
		script.append("head.appendChild(src);");
		this.executeJs(script.toString());
	}

	/**
	 * for fire_event use
	 */
	@Override
	public void fire_event(String name) {
		if (!isEmpty()) {
			String eventName = name.replaceFirst("on", "");
			StringBuffer script = new StringBuffer(
					"var element = arguments[0];");
			script.append("var name = arguments[1];");
			script
					.append("canBubble = (typeof(canBubble) == 'undefined') ? true : false;");
			script.append("var evt = document.createEvent('HTMLEvents');");
			script.append("evt.shiftKey = false;" + "evt.metaKey = false;");
			script.append("evt.altKey = false;" + "evt.ctrlKey = false;");
			script.append("evt.initEvent(name, canBubble, true);");
			script.append("element.dispatchEvent(evt);");
			executeJs(script.toString(), current, eventName);
		}
	}

	@Override
	public void mouseover() {
		driver.switchTo().window(driver.getWindowHandle());
		HasInputDevices mouse = (HasInputDevices) driver;
		mouse.getMouse()
				.mouseMove(((Locatable) current).getCoordinates(), 0, 0);
	}

	@Override
	public void drag(WebElement element) {
		driver.switchTo().window(driver.getWindowHandle());
		Actions builder = new Actions(driver);
		Action action = builder.dragAndDrop(current, element).build();
		action.perform();
	}

	@Override
	public void doubleclick() {
		driver.switchTo().window(driver.getWindowHandle());
		Actions action = new Actions(driver);
		action.doubleClick(current);
		action.perform();
	}

	@Override
	public void scrollIntoView(Boolean top) {
		this.executeJs("arguments[0].scrollIntoView(arguments[1]);", current,
				top);
	}

	@Override
	public void focus() {
		this.executeJs("return arguments[0].focus();", current);
	}

	@Override
	public ArrayList<Engine> get_element_by_css_selector(String value,
			Scope scope) {
		this.actionInfo = Engine.getActionInfo(this);
		if (scope.equals(Scope.Descendant)) {
			ArrayList<Engine> eles = new ArrayList<Engine>();
			jump_into_iframe(iframeIndex);
			for (WebElement element : current.findElements(By
					.cssSelector(value))) {
				ElementEngine ieEngine = new ElementEngine(driver, element,
						iframeIndex, actionInfo);
				eles.add(ieEngine);
			}
			if (eles.isEmpty()) {
				// System.out.print("the element is not found, go to the iframe");
				Collection<WebElement> iframes = current.findElements(By
						.tagName("iframe"));
				if (iframes.size() == 0) {
					return eles;
				} else {
					int index = 0;
					for (WebElement element1 : iframes) {
						ArrayList<Integer> iframeIndex = new ArrayList<Integer>();
						iframeIndex.add(index);
						ElementEngine ieEngine1 = new ElementEngine(driver,
								element1, iframeIndex, actionInfo);
						ArrayList<Engine> tempeles = ieEngine1
								.get_element_by_css_selector(value, scope);
						iframeIndex.remove(iframeIndex.size() - 1);
						jump_into_iframe(iframeIndex);
						eles.addAll(tempeles);
						if (eles.isEmpty()) {
							index = index + 1;
						} else {
							break;
						}
					}
					return eles;
				}
			} else {
				return eles;
			}
		} else {
			return super.get_element_by_css_selector(value, scope);
		}
	}

	@Override
	public ArrayList<Engine> get_element_by_class_name(String value, Scope scope) {
		this.actionInfo = Engine.getActionInfo(this);
		if (scope.equals(Scope.Descendant)) {
			ArrayList<Engine> eles = new ArrayList<Engine>();
			jump_into_iframe(iframeIndex);
			for (WebElement element : current.findElements(By.className(value))) {
				ElementEngine ieEngine = new ElementEngine(driver, element,
						iframeIndex, actionInfo);
				eles.add(ieEngine);
			}
			if (eles.isEmpty()) {
				// System.out.print("the element is not found, go to the iframe");
				Collection<WebElement> iframes = current.findElements(By
						.tagName("iframe"));
				if (iframes.size() == 0) {
					return eles;
				} else {
					int index = 0;
					for (WebElement element1 : iframes) {
						ArrayList<Integer> iframeIndex = new ArrayList<Integer>();
						iframeIndex.add(index);
						ElementEngine ieEngine1 = new ElementEngine(driver,
								element1, iframeIndex, actionInfo);
						ArrayList<Engine> tempeles = ieEngine1
								.get_element_by_class_name(value, scope);
						iframeIndex.remove(iframeIndex.size() - 1);
						jump_into_iframe(iframeIndex);
						eles.addAll(tempeles);
						if (eles.isEmpty()) {
							index = index + 1;
						} else {
							break;
						}
					}
					return eles;
				}
			} else {
				return eles;
			}
		} else {
			return super.get_element_by_class_name(value, scope);
		}
	}

	@Override
	public ArrayList<Engine> get_element_by_id(String value, Scope scope) {
		this.actionInfo = Engine.getActionInfo(this);
		if (scope.equals(Scope.Descendant)) {
			ArrayList<Engine> eles = new ArrayList<Engine>();
			jump_into_iframe(iframeIndex);
			for (WebElement element : current.findElements(By.id(value))) {
				ElementEngine ieEngine = new ElementEngine(driver, element,
						iframeIndex, actionInfo);
				eles.add(ieEngine);
			}
			if (eles.isEmpty()) {
				// System.out.print("the element is not found, go to the iframe");
				Collection<WebElement> iframes = current.findElements(By
						.tagName("iframe"));
				if (iframes.size() == 0) {
					return eles;
				} else {
					int index = 0;
					for (WebElement element1 : iframes) {
						ArrayList<Integer> iframeIndex = new ArrayList<Integer>();
						iframeIndex.add(index);
						ElementEngine ieEngine1 = new ElementEngine(driver,
								element1, iframeIndex, actionInfo);
						ArrayList<Engine> tempeles = ieEngine1
								.get_element_by_id(value, scope);
						iframeIndex.remove(iframeIndex.size() - 1);
						jump_into_iframe(iframeIndex);
						eles.addAll(tempeles);
						if (eles.isEmpty()) {
							index = index + 1;
						} else {
							break;
						}
					}
					return eles;
				}
			} else {
				return eles;
			}
		} else {
			return super.get_element_by_id(value, scope);
		}
	}

	private void jump_into_iframe(ArrayList<Integer> iframeIndex) {
		if (iframeIndex.size() == 0) {
			return;
		} else {
			driver.switchTo().defaultContent();
			for (Integer index : iframeIndex) {
				driver.switchTo().frame(index);
			}
			try {
				current = driver.findElement(By.xpath("./html"));
			} catch (NoSuchElementException ex) {
				logger.error(ex.getMessage());
			}
		}
	}

	public ArrayList<Engine> get_element_by_control_name(String value,
			Scope scope) {
		this.actionInfo = Engine.getActionInfo(this);
		if (scope.equals(Scope.Descendant)) {
			ArrayList<Engine> eles = new ArrayList<Engine>();
			jump_into_iframe( iframeIndex);
			for (WebElement element : current.findElements(By.tagName(value))) {
				ElementEngine ieEngine = new ElementEngine(driver, element,
						iframeIndex, actionInfo);
				eles.add(ieEngine);
			}
			if (eles.isEmpty()){
				Collection<WebElement> iframes = current.findElements(By.tagName("iframe"));
				if(iframes.size() == 0){
					return eles;								
				} else {				
					int index=0;
					for(WebElement element1 : iframes){
						ArrayList<Integer> iframeIndex = new ArrayList<Integer>();
						iframeIndex.add(index);
						ElementEngine ieEngine1 = new ElementEngine(driver,element1,iframeIndex,actionInfo);
						ArrayList<Engine> tempeles = ieEngine1.get_element_by_control_name(value, scope);
						iframeIndex.remove(iframeIndex.size()-1);
						jump_into_iframe(iframeIndex);
						eles.addAll(tempeles);
						if (eles.isEmpty()) {
							index = index + 1;						
						} else {							
							break;
						}
						
						
					}
					
					return eles;
					
				}
				
				
			} else {
			
				return eles;
			}
		} else {
//			ArrayList<Engine> collection = getScopeElement(scope);
//			return filterCollection(collection, value, MatchType.ControlName);
			return super.get_element_by_control_name(value, scope);
		}
	}

	@Override
	public void doclick() {
		if (!isEmpty()) {
			Actions builder = new Actions(driver);
			Action action = builder.moveToElement(current).click().build();
			action.perform();
			waitfor();
		}
	}

	@Override
	public String maxlength() {
		return current.getAttribute("maxLength");
	}

	@Override
	public void maxlength(String length) {
		this.executeJs("arguments[0].maxLength = arguments[1];", current,
				length);
	}

	@Override
	public String readonly() {
		return current.getAttribute("readOnly");
	}

	@Override
	public void readonly(Boolean readonly) {
		this.executeJs("arguments[0].readOnly = arguments[1];", current,
				readonly);
	}

	@Override
	public boolean checked() {
		if (!isEmpty()) {
			return current.isSelected();
		}
		return false;
	}

	@Override
	public String getAction() {
		return actionInfo;
	}

	@Override
	public void selectFrame() {
		if (!isEmpty()) {
			driver.switchTo().frame("hjgh");
		
		}
		
		
	}

}
