package com.zendaimoney.Dokodemo.engine;

import java.util.ArrayList;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.thoughtworks.selenium.Selenium;
import com.zendaimoney.Dokodemo.listener.MyEventListener;
import com.zendaimoney.Dokodemo.logger.LogUtils;

/**
 * 浏览器基类的相关操作
 */
public class BaseBrowser {
	protected WebDriver driver;
	protected WebDriverEventListener eventListener = new MyEventListener();

	public BaseBrowser() {
	}

	public BaseBrowser(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver getWebDriver() {
		return driver;
	}

	public void maximizeWindow(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.moveTo(0, 0); window.resizeTo(screen.width, screen.height);");
	}

	public void navigate(String url) {
		switch_to_current(get_window_handle());
		driver.get(url);
		maximizeWindow(driver);
	}

	public void go_to(String url) {
		driver.navigate().to(url);
		maximizeWindow(driver);
	}

	public String url() {
		 try {
		return driver.getCurrentUrl();
		} catch (Exception e) {
			LogUtils.setBrowserWarned("", "url()", null, e.getMessage());
			return null;
		}

	}

	private void switch_to_current(String id) {
		driver.switchTo().window(id);
	}

	public String get_window_handle() {
		ArrayList<String> WindowHandleArrayList = new ArrayList<String>();
		for (String handle : driver.getWindowHandles()) {
			WindowHandleArrayList.add(handle);
		}
		Integer index = WindowHandleArrayList.size();
		return WindowHandleArrayList.get(index - 1);
	}

	public void refresh() {
		driver.navigate().refresh();
	}

	public void bring_to_front() {
		driver.navigate().forward();
	}

	public void bring_to_back() {
		driver.navigate().back();
	}

	public String title() {
		return driver.getTitle();
	}

	public void scrollToUp() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,0);");

	}

	public void scrollToUp(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,0);");

	}

	public void scrollToDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
	}

	public void scrollToDown(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
	}

	public Engine getRootNode() {
		WebElement current = driver.findElement(By.xpath("./*"));
		ArrayList<Integer> iframeIndex = new ArrayList<Integer>();
		ElementEngine elementEngine = new ElementEngine(driver, current, iframeIndex, null);
		return elementEngine;
	}

	public Engine getRootNode(WebDriver driver) {
		WebElement current = driver.findElement(By.xpath("/html"));
		ArrayList<Integer> iframeIndex = new ArrayList<Integer>();
		ElementEngine elementEngine = new ElementEngine(driver, current, iframeIndex, null);
		return elementEngine;
	}

	/**
	 * Close the current window, quitting the browser if it's the last window
	 * currently open.
	 */
	public void close() {
		String id = driver.getWindowHandle();
		driver.close();
		int index = 0;
		for (Browser browser : Browser.browsers) {
			if (browser.handle.equals(id)) {
				Browser.browsers.remove(index);
				if (Browser.browsers.size() != 0) {
					switch_to_current(get_window_handle());
				}
				break;
			}
			index = index + 1;
		}
	}

	/**
	 * Quits this driver, closing every associated window.
	 */
	public void quit() {
		driver.quit();
		Browser.browsers = new ArrayList<Browser>();
	}

	/**
	 * 根据浏览器类型获取浏览器小版本
	 * 
	 * @param browserType
	 * @return
	 */
	public String version(String browserType) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String userAgent = (String) js.executeScript("return window.navigator.userAgent;");
		Pattern pattern = Pattern.compile(browserType + "\\/(\\d*)");
		Matcher matcher = pattern.matcher(userAgent);
		String version = null;
		while (matcher.find()) {
			String v = matcher.group();
			version = v.split("\\/")[1];
		}
		return browserType + version;
	}

	public void clear_cookie_and_cache() {
		driver.manage().deleteAllCookies();
	}

	public String getDriverPath(String browserType) {
		if (browserType == null)
			return "";
		BrowserType bt = Browser.isBrowser(browserType);
		return DriverInfoFactory.createPath(bt);
	}

	/**
	 * 获取JavascriptExecutor实例，可以直接用来执行js
	 * 
	 * @return a JavascriptExecutor instance
	 */
	public JavascriptExecutor getJavaScriptExecutor() {
		return (JavascriptExecutor) driver;
	}

	/**
	 * 处理alert弹出框
	 * 
	 * @param type
	 */
	public void dealDialog(String type) {
		if (null == type || "".equals(type)) {
			return;
		}
		if (type.equals("取消")) {
			driver.switchTo().alert().accept();
		} else if (type.equals("确定")) {
			driver.switchTo().alert().dismiss();
		}
	}

	/**
	 * 获取当前的WindowHandle
	 * 
	 * @return
	 */
	public String getCurrentWindowHandle() {
		String handle = "";
		ArrayList<String> handles = new ArrayList<String>(driver.getWindowHandles());
		handle = handles.get(handles.size() - 1);
		return handle;
	}

	/**
	 * 默认最后打开的一个页面为当前页面
	 */
	public void switchToCurrent() {
		String currentWindowHandle = "";
		currentWindowHandle = getCurrentWindowHandle();
		driver.switchTo().window(currentWindowHandle);
	}

	/**
	 * 定位到给定标题页面 the given
	 * 
	 * @param nameOrHandle
	 */
	public void switchToWindow(String windowTitle) {
		String currentHandle = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		for (String s : handles) {
			if (s.equals(currentHandle)) {
				if (driver.getTitle().contains(windowTitle)) {
					break;
				}
				continue;
			} else {
				driver.switchTo().window(s);
				if (driver.getTitle().contains(windowTitle)) {
					break;
				} else
					continue;
			}
		}
	}

	/**
	 * 获取当前页的源码
	 * 
	 * @return
	 */
	public String getPageSource() {
		String pageSource = "";
		pageSource = driver.getPageSource();
		return pageSource;
	}

	/**
	 * 暂停给定毫秒，如果time<1,则不会暂停
	 * 
	 * @param time
	 *            毫秒数
	 * 
	 */
	public void sleep(int time) {
		if (time < 1) {
			return;
		}
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 暂停给定毫秒，如果time<1,则不会暂停
	 * 
	 * @param time
	 *            毫秒数
	 * 
	 */

	public void sleep(String timeString) {
		int time = 0;
		try {
			time = Integer.parseInt(timeString);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		sleep(time);
	}

	public Selenium getSelenium() {
		try {
			Selenium selenium = new WebDriverBackedSelenium(driver, url());
			return selenium;
		} catch (Exception ex) {
			return null;
		}
	}
}
