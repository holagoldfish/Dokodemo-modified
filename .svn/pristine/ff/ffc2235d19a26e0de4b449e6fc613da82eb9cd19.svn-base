package com.zendaimoney.Dokodemo.engine;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.thoughtworks.selenium.Selenium;
import com.zendaimoney.Dokodemo.html.PageModel;
import com.zendaimoney.Dokodemo.logger.LogUtils;
import com.zendaimoney.Dokodemo.util.FileUtils;
import com.zendaimoney.Dokodemo.util.OsUtils;

/**
 * 供脚本使用，代表浏览器的基本操作
 */
public class Browser {
	public static ArrayList<Browser> browsers = new ArrayList<Browser>();
	private static Properties properties = null;
	private static final String ETEST_DRIVER = "etest.driver";
	private static final String ETEST_URLPARAM = "tb_eagleeyex_octopus";
	protected static final Log logger = LogFactory.getLog(Browser.class);
	private static final String userAgent = null;
	private static BaseBrowser baseBrowser;
	static {
		if (null == properties) {
			properties = FileUtils.getProperties();
		}
		String ETESTDriver = (String) System.getProperties().get(ETEST_DRIVER);
		String urlParam = (String) System.getProperties().get(ETEST_URLPARAM);
		if (ETESTDriver != null && !ETESTDriver.trim().equals("")) {
			properties.put(ETEST_DRIVER, ETESTDriver);
		}
		if (urlParam != null && !urlParam.trim().equals("")) {
			properties.put(ETEST_URLPARAM, urlParam);
		}
	}
	public WebDriver driver;
	protected String handle;
	public String browserType;

	private Browser(WebDriver driver, String handle) {
		this.driver = driver;
		this.handle = handle;
	}

	private Browser(String userAgent, String browserType) {
		try {
			// 判断browsers保证一个用例一个driver，以此共享session和cooike
			addBrowser(userAgent, browserType);
			String handle = baseBrowser.get_window_handle();
			this.handle = handle;
			this.driver = baseBrowser.driver;
			this.browserType = browserType;
		} catch (Exception ex) {
			throw new RuntimeException("There is ERROR in starting browser:" + browserType + ". The error:" + ex.getMessage());
		}
	}

	/**
	 * 根据浏览器类型增加相应的浏览器
	 * 
	 * @param userAgent
	 * @param browserType
	 */
	private void addBrowser(String userAgent, String browserType) {
		switch (isBrowser(browserType)) {
		case ie:
			if (!browsers.isEmpty() && (isSrartBrowser(browserType) != null)) {
				open(isSrartBrowser(browserType));
			} else {
				baseBrowser = new IEBrowser();
			}
			break;
		case ff:
			if (!browsers.isEmpty() && (isSrartBrowser(browserType) != null)) {
				open(isSrartBrowser(browserType));
			} else {
				baseBrowser = new FFBrowser(userAgent);
			}
			break;
		case chrome:
			if (browsers.isEmpty() || (isSrartBrowser(browserType) == null)) {
				baseBrowser = new ChromeBrowser(userAgent);
			}
			break;
		default:
			break;
		}
	}

	/**
	 * 选中浏览器类型
	 * 
	 * @param browserType
	 * @return
	 */
	public static BrowserType isBrowser(String browserType) {
		for (BrowserType browser : BrowserType.values()) {
			if (browser.toString().equalsIgnoreCase(browserType)) {
				return browser;
			}
		}
		return BrowserType.ie;
	}

	/**
	 * 判断是否打开过某一浏览器类型
	 * 
	 * @param browserType
	 * @return
	 */
	private static WebDriver isSrartBrowser(String browserType) {
		for (Browser browser : browsers) {
			if (browser.browserType.equalsIgnoreCase(browserType) && (browser.driver != null)) {
				return browser.driver;
			}
		}
		return null;
	}

	/**
	 * 新开窗口
	 */
	private void open(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.open()");
	}

	/**
	 * 打开一个url,供user agent使用
	 * 
	 * @param url
	 * @param userAgent
	 * @return
	 * @throws Exception 
	 */
	public static Browser start(String url, String userAgent) throws Exception {
		return start(url, userAgent, getBrowserType());
	}

	/**
	 * 打开一个url
	 * 
	 * @param url
	 * @return
	 * @throws Exception 
	 */
	public static Browser start(String url) throws Exception {
		url = setUrlPrefix(url);
		return start(url, userAgent, getBrowserType());
	}

	private static String setUrlPrefix(String url) {
		if (url.startsWith("www")) {
			url = "http://" + url;
		}
		String urlParam = properties.getProperty(ETEST_URLPARAM, "");
		if (null==urlParam||urlParam.equals("")) {
			return url;
		}
		if (url.contains("=")) {
			url=url+"&"+ETEST_URLPARAM+"="+urlParam;
		}else {
			if (url.endsWith("?")) {
				url=url+ETEST_URLPARAM+"="+urlParam;
			}else {
				url=url+"?"+ETEST_URLPARAM+"="+urlParam;
			}
		}
		return url;
	}

	public static String getBrowserType() {
		return properties.getProperty(ETEST_DRIVER, "ie");
	}
	
	public static Browser start(String url, String userAgent, String browserType) throws Exception {
		try {

			Browser browser = new Browser(userAgent, browserType);
			browser.navigate(url);
			browsers.add(browser);
			LogUtils.setBrowserPassed(browserType, "start", url);
			return browser;
		} catch (Exception ex) {
			LogUtils.setBrowserFailed(browserType, "start", url, ex.getMessage());
			return null;
		}
	}

	public static void closeAllBrowsers(String browserType) {
		switch (OsUtils.getOs()) {
		case WINDOWS:
			closeWindowsAllBrowsers(browserType);
			break;
		default:
			closeLinuxAllBrowsers(browserType);
			break;
		}
		browsers = new ArrayList<Browser>();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void closeLinuxAllBrowsers(String browserType) {
		Runtime runTime = Runtime.getRuntime();
		try {
			runTime.exec("killall -9 downloadfile");
			runTime.exec("killall -9 savefile");
			switch (isBrowser(browserType)) {
			case ie:
				runTime.exec("killall -9 iexplore");
				runTime.exec("killall -9 IEDriverServer");
				break;
			case ff:
				runTime.exec("killall -9 firefox");
				break;
			case chrome:
				runTime.exec("killall -9 chrome");
				runTime.exec("killall -9 chromedriver");
				break;
			case safari:
				runTime.exec("killall -9 safari");
				break;
			case opera:
				runTime.exec("killall -9 opera");
				break;
			default:
				break;
			}
		} catch (IOException ex) {
			LogUtils.setBrowserWarned(browserType, "killall杀浏览器进程失败", "", ex.getMessage());
		}
	}

	public static void closeWindowsAllBrowsers(String browserType) {
		Runtime runTime = Runtime.getRuntime();
		try {
			runTime.exec("tskill downloadfile");
			runTime.exec("tskill savefile");
			switch (isBrowser(browserType)) {
			case ie:
				runTime.exec("tskill iexplore");
				runTime.exec("tskill IEDriverServer");
				break;
			case ff:
				runTime.exec("tskill firefox");
				break;
			case chrome:
				runTime.exec("tskill chrome");
				runTime.exec("tskill chromedriver");
				break;
			case safari:
				runTime.exec("tskill safari");
				break;
			case opera:
				runTime.exec("tskill opera");
				break;
			default:
				break;
			}
		} catch (IOException ex) {
			try {
				runTime.exec("TASKKILL /F /IM downloadfile.exe");
				runTime.exec("TASKKILL /F /IM savefile.exe");
				switch (isBrowser(browserType)) {
				case ie:
					runTime.exec("TASKKILL /F /IM iexplore.exe");
					runTime.exec("TASKKILL /F /IM IEDriverServer.exe");
					break;
				case ff:
					runTime.exec("TASKKILL /F /IM firefox.exe");
					break;
				case chrome:
					runTime.exec("TASKKILL /F /IM chrome.exe");
					runTime.exec("TASKKILL /F /IM chromedriver.exe");
					break;
				case safari:
					runTime.exec("TASKKILL /F /IM safari.exe");
					break;
				case opera:
					runTime.exec("TASKKILL /F /IM opera.exe");
					break;
				default:
					break;
				}
			} catch (IOException e) {
				LogUtils.setBrowserWarned(browserType, "taskkill杀浏览器进程失败", "", e.getMessage());
			}
			LogUtils.setBrowserWarned(browserType, "tskill杀浏览器进程失败", "", ex.getMessage());
		}
	}

	public void navigate(String url) throws Exception {
		try {
			baseBrowser.navigate(url);
		} catch (Exception ex) {
			LogUtils.setBrowserFailed(getBrowserType(), "navigate", url, ex.getMessage());
		}
	}

	public <T extends PageModel> T cast(Class<T> clazz) throws Exception {
		try {
			Engine engine = baseBrowser.getRootNode();
			T pageModle = construct(clazz, null, null, null, null);
			pageModle.setEngine(engine);
			LogUtils.setBrowserPassed(getBrowserType(), "cast", clazz.getName(), getUrl());
			return pageModle;
		} catch (Exception ex) {
			LogUtils.setBrowserFailed(getBrowserType(), "cast", clazz.getName(), getUrl(), ex.getMessage());
			return null;
		}
	}

	public <T extends PageModel> T cast(Class<T> clazz, WebDriver driver) throws Exception {
		try {
			Engine engine = baseBrowser.getRootNode(driver);
			T pageModle = construct(clazz, null, null, null, null);
			pageModle.setEngine(engine);
			LogUtils.setBrowserPassed(getBrowserType(), "cast", clazz.getName(), getUrl());
			return pageModle;
		} catch (Exception ex) {
			LogUtils.setBrowserFailed(getBrowserType(), "cast", clazz.getName(), getUrl(), ex.getMessage());
			return null;
		}
	}

	private <T extends Object> T construct(Class<T> type, String arg1, PageModel arg2, String arg3, String arg4) {
		try {
			Constructor<T> constructor = type.getConstructor(String.class, PageModel.class, String.class, String.class);
			return constructor.newInstance(arg1, arg2, arg3, arg4);
		} catch (Exception ex) {
			throw new RuntimeException(type.getName() + "必须有双参构造函数", ex);
		}
	}

	public void close() throws Exception {
		String url = getUrl();
		try {
			LogUtils.setBrowserPassed(getBrowserType(), "close", url);
			baseBrowser.close();
		} catch (Exception ex) {
			System.out.println(ex);
			LogUtils.setBrowserFailed(getBrowserType(), "close", url, ex.getMessage());
		}
	}

	public void close(WebDriver driver) throws Exception {
		String url = getUrl();
		try {
			LogUtils.setBrowserPassed(getBrowserType(), "close", url);
			driver.close();
		} catch (Exception ex) {
			System.out.println(ex);
			LogUtils.setBrowserFailed(getBrowserType(), "close", url, ex.getMessage());
		}
	}

	public void quit() throws Exception {
		String url = getUrl();
		try {
			LogUtils.setBrowserPassed(getBrowserType(), "quit", url);
			baseBrowser.quit();
		} catch (Exception ex) {
			System.out.println(ex);
			LogUtils.setBrowserFailed(getBrowserType(), "quit", url, ex.getMessage());
		}
	}

	public String version() throws Exception {
		String result = null;
		try {
			result = baseBrowser.version(getBrowserType());
			LogUtils.setBrowserPassed(getBrowserType(), "version", getUrl());
		} catch (Exception ex) {
			LogUtils.setBrowserFailed(getBrowserType(), "version", getUrl(), ex.getMessage());
		}
		return result;
	}

	public static String getUrl() {// 于凡添加空指针校验
		return (baseBrowser == null) ? null : baseBrowser.url();
	}
	
	public static WebDriver returndriver(){
		return baseBrowser.driver;
	}

	public static ArrayList<Browser> get_all_ies() {//改成public供engine使用
		ArrayList<Browser> browserArrayList = new ArrayList<Browser>();
		Browser browser = null;
		for (String handle : baseBrowser.driver.getWindowHandles()) {
			browser = new Browser(baseBrowser.driver, handle);
			browserArrayList.add(browser);
		}
		return browserArrayList;
	}

	public static ArrayList<Browser> get_all_ies(WebDriver driver) {
		ArrayList<Browser> browserArrayList = new ArrayList<Browser>();
		for (String handle : driver.getWindowHandles()) {
			Browser browser = new Browser(driver, handle);
			browserArrayList.add(browser);
		}
		return browserArrayList;
	}

	public static Browser attach(String url) throws Exception {
		try {
			Browser attachBrowser = null;
			for (Browser browser : get_all_ies()) {
				baseBrowser.driver.switchTo().window(browser.handle);
				if (getUrl().contains(url)) {
					attachBrowser = browser;
					break;
				}
			}
			LogUtils.setBrowserPassed(getBrowserType(), "attach", "/" + url + "/");
			return attachBrowser;
		} catch (Exception ex) {
			LogUtils.setBrowserFailed(getBrowserType(), "attach", "/" + url + "/", ex.getMessage());
			return null;
		}
	}

	public static Browser last_ie() throws Exception {
		try {
			int lenth = get_all_ies().size();
			baseBrowser.driver.switchTo().window(get_all_ies().get(lenth - 1).handle);
			LogUtils.setBrowserPassed(getBrowserType(), "last_ie", getUrl());
			return get_all_ies().get(lenth - 1);
		} catch (Exception ex) {
			LogUtils.setBrowserFailed(getBrowserType(), "last_ie", getUrl(), ex.getMessage());
			return null;
		}
	}

	public static Browser last_ie(WebDriver driver) {
		try {
			int lenth = get_all_ies(driver).size();
			driver.switchTo().window(get_all_ies(driver).get(lenth - 1).handle);
			return get_all_ies(driver).get(lenth - 1);
		} catch (Exception ex) {
			return null;
		}
	}

	public String title() {
		return baseBrowser.title();
	}

	public String url() {// 于凡添加空指针校验
		return baseBrowser.url();
	}

	public void maximizeWindow() {
		try {
			baseBrowser.maximizeWindow(baseBrowser.driver);
			LogUtils.setBrowserPassed(getBrowserType(), "maximizeWindow", getUrl());
		} catch (Exception ex) {
			LogUtils.setBrowserPassed(getBrowserType(), "maximizeWindow", getUrl());
		}
	}

	public void go_to(String url) {
		try {
			url = setUrlPrefix(url);
			baseBrowser.go_to(url);
			LogUtils.setBrowserPassed(getBrowserType(), "goto", getUrl());
		} catch (Exception ex) {
			LogUtils.setBrowserPassed(getBrowserType(), "goto", getUrl(), ex.getMessage());
		}
	}

	public void go_to(String url, WebDriver driver) {
		try {
			url = setUrlPrefix(url);
			driver.navigate().to(url);
		} catch (Exception ex) {
		}
	}

	public void bring_to_front() throws Exception {
		try {
			baseBrowser.bring_to_front();
			LogUtils.setBrowserPassed(getBrowserType(), "bring_to_front", getUrl());
		} catch (Exception ex) {
			LogUtils.setBrowserFailed(getBrowserType(), "bring_to_front", getUrl(), ex.getMessage());
		}
	}

	public void bring_to_back() throws Exception {
		try {
			baseBrowser.bring_to_back();
			LogUtils.setBrowserPassed(getBrowserType(), "bring_to_back", getUrl());
		} catch (Exception ex) {
			LogUtils.setBrowserFailed(getBrowserType(), "bring_to_back", getUrl(), ex.getMessage());
		}
	}

	public void refresh() throws Exception {
		try {
			baseBrowser.refresh();
			LogUtils.setBrowserPassed(getBrowserType(), "refresh", getUrl());
		} catch (Exception ex) {
			LogUtils.setBrowserFailed(getBrowserType(), "refresh", getUrl(), ex.getMessage());
		}
	}

	public void refresh(WebDriver driver) {
		try {
			driver.navigate().refresh();
		} catch (Exception ex) {
		}
	}

	public void scrollToUp() throws Exception {
		try {
			baseBrowser.scrollToUp();
			LogUtils.setBrowserPassed(getBrowserType(), "scrollToUp", getUrl());
		} catch (Exception ex) {
			LogUtils.setBrowserFailed(getBrowserType(), "scrollToUp", getUrl(), ex.getMessage());
		}
	}

	public void scrollToUp(WebDriver driver) {
		try {
			baseBrowser.scrollToUp(driver);
		} catch (Exception ex) {

		}
	}

	public void scrollToDown() throws Exception {
		try {
			baseBrowser.scrollToDown();
			LogUtils.setBrowserPassed(getBrowserType(), "scrollToDown", getUrl());
		} catch (Exception ex) {
			LogUtils.setBrowserFailed(getBrowserType(), "scrollToDown", getUrl(), ex.getMessage());
		}
	}

	public void scrollToDown(WebDriver driver) {
		try {
			baseBrowser.scrollToDown(driver);
		} catch (Exception ex) {

		}
	}

	public static WebDriver getDriver() {
		try {
			return baseBrowser.driver;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 
	 * @return
	 */
	public static Selenium getSelenium() {
		try {
			return baseBrowser.getSelenium();
		} catch (Exception ex) {
			return null;
		}
	}

	public static void clear_cookie_and_cache() throws Exception {
		try {
			baseBrowser.clear_cookie_and_cache();
			LogUtils.setBrowserPassed(getBrowserType(), "clear_cookie_and_cache", getUrl());
		} catch (Exception ex) {
			LogUtils.setBrowserFailed(getBrowserType(), "clear_cookie_and_cache", getUrl(), ex.getMessage());
		}
	}

	public static BaseBrowser getBaseBrowser() {
		return baseBrowser;
	}


	/**
	 * 处理alert弹出框
	 * 
	 * @param type
	 * @throws Exception 
	 */
	public void dealDialog(String type) throws Exception {
		try {
			baseBrowser.dealDialog(type);
			LogUtils.setBrowserPassed(getBrowserType(), "dealDialog", getUrl());
		} catch (Exception ex) {
			LogUtils.setBrowserFailed(getBrowserType(), "dealDialog", getUrl(), ex.getMessage());
		}
	}


	/**
	 * 获取当前的WindowHandle
	 * 
	 * @return
	 */
	public String getCurrentWindowHandle() {
		String handle = "";
		try {
			handle = baseBrowser.getCurrentWindowHandle();
			LogUtils.setBrowserPassed(getBrowserType(), "getCurrentWindowHandle", getUrl());
		} catch (Exception ex) {
			LogUtils.setBrowserPassed(getBrowserType(), "getCurrentWindowHandle", getUrl(), ex.getMessage());
		}
		return handle;
	}

	/**
	 * 默认最后打开的一个页面为当前页面
	 */
	public void switchToCurrent() {
		try {
			baseBrowser.switchToCurrent();
			LogUtils.setBrowserPassed(getBrowserType(), "switchToCurrent", getUrl());
		} catch (Exception ex) {
			LogUtils.setBrowserPassed(getBrowserType(), "switchToCurrent", getUrl(), ex.getMessage());
		}
	}

	/**
	 * Switch the focus of future commands for this driver to the window with
	 * the given
	 * 
	 * @param nameOrHandle
	 */
	public void switchToWindow(String windowTitle) {
		try {
			baseBrowser.switchToWindow(windowTitle);
			LogUtils.setBrowserPassed(getBrowserType(), "switchToWindow", getUrl());
		} catch (Exception ex) {
			LogUtils.setBrowserPassed(getBrowserType(), "switchToWindow", getUrl(), ex.getMessage());
		}
	}

	/**
	 * 获取当前页的源码
	 * 
	 * @return
	 */
	public String getPageSource() {
		String pageSource = "";
		try {
			pageSource = baseBrowser.getPageSource();
			LogUtils.setBrowserPassed(getBrowserType(), "getPageSource", getUrl());
		} catch (Exception ex) {
			LogUtils.setBrowserPassed(getBrowserType(), "getPageSource", getUrl(), ex.getMessage());
		}
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
		try {
			baseBrowser.sleep(time);
			LogUtils.setBrowserPassed(getBrowserType(), "sleep", getUrl());
		} catch (Exception ex) {
			LogUtils.setBrowserPassed(getBrowserType(), "sleep", getUrl(), ex.getMessage());
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
		int time = Integer.parseInt(timeString);
		sleep(time);
	}
	

	/**
	 * 获取JavascriptExecutor实例，可以直接用来执行js
	 * 
	 * @return a JavascriptExecutor instance
	 */
	public static JavascriptExecutor getJavaScriptExecutor() {
		return baseBrowser.getJavaScriptExecutor();
	}

	public static WebDriver getWebDriver() {
		return baseBrowser.getWebDriver();
	}

}
