package com.zendaimoney.Dokodemo.util;

import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.zendaimoney.Dokodemo.engine.Browser;
import com.zendaimoney.Dokodemo.html.Element;

public class SeleniumHelper {
	protected static final Log logger = LogFactory.getLog(SeleniumHelper.class);

	private static WebDriver getDriver(WebDriver driver) {
		if (driver == null) {
			driver = Browser.getDriver();
		}
		return driver;
	}

	/**
	 * 执行异步Javascript函数
	 * 
	 * @param driver如果为null
	 *            ，driver=currentDriver
	 * @param script
	 * @param args
	 * @return
	 */
	public static Object executeAsyncJs(WebDriver driver, String script,
			Object... args) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver(driver);
		return js.executeAsyncScript(script, args);
	}

	/**
	 * 执行Javascript函数
	 * 
	 * @param driver如果为null
	 *            ，driver=currentDriver
	 * @param script
	 * @param args
	 * @return
	 */
	public static Object executeJs(WebDriver driver, String script,
			Object... args) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver(driver);
		return js.executeScript(script, args);
	}

	/**
	 * 高亮显示当前的element
	 * 
	 * @param driver
	 * @param element
	 */
	public static void highlight(WebDriver driver, WebElement element) {
		executeJs(driver, "arguments[0].style.border = \"5px solid yellow\"",
				element);
	}

	/**
	 * 验证当前页面的某个字符串是不是存在
	 * 
	 * @param driver
	 * @param content
	 * @return
	 */
	public static boolean isTextPresent(WebDriver driver, String text) {
		driver = getDriver(driver);
		boolean status = false;
		try {
			driver.findElement(By.xpath("//*[contains(.,'" + text + "')]"));
			logger.debug(text + " 存在!");
			status = true;
		} catch (NoSuchElementException e) {
			status = false;
			logger.debug("'" + text + "' 不存在!");
		}
		return status;
	}

	/**
	 * 根据标题，切换页面
	 * 
	 * @param driver
	 * @param windowTitle
	 * @return
	 */
	public static boolean switchToWindow(WebDriver driver, String windowTitle) {
		driver = getDriver(driver);
		boolean flag = false;
		try {
			String currentHandle = driver.getWindowHandle();
			Set<String> handles = driver.getWindowHandles();
			for (String handle : handles) {
				if (handle.equals(currentHandle))
					continue;
				else {
					driver.switchTo().window(handle);
					if (driver.getTitle().contains(windowTitle)) {
						flag = true;
						logger.debug("切换到窗口[" + windowTitle + "]成功！");
						break;
					} else
						continue;
				}
			}
		} catch (NoSuchWindowException e) {
			logger.debug("切换到窗口:[" + windowTitle + "]失败！");
			flag = false;
		}
		return flag;
	}

	/**
	 * 输入字符串
	 * 
	 * @param element
	 * @param text
	 */
	public static void type(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
	}

	/**
	 * 等待页面元素出现
	 * 
	 * @param element
	 * @param timeout
	 */
	public static void waitForDisplay(WebElement element, int timeout) {
		long elapse = System.currentTimeMillis() + timeout;
		while (System.currentTimeMillis() < elapse) {
			if (element.isDisplayed()) {
				return;
			}
		}
		logger.debug("加载页面元素:[" + element.getText() + "] 成功！");
	}
	
	/**
	 * 等待页面元素出现_element
	 * 
	 * @param element
	 * @param timeout
	 * @throws Exception 
	 */
	public static void waitForDisplay(Element element, int timeout) throws Exception {
		long elapse = System.currentTimeMillis() + timeout;
		while (System.currentTimeMillis() < elapse) {
			if (element.isExist()) {
				return;
			}
		}
		logger.debug("加载页面元素:[" + element.getEngine().text() + "] 成功！");
	}
}
