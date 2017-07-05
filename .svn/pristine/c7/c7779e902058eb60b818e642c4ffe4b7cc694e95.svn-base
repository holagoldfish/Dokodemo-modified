package com.zendaimoney.Dokodemo;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.awt.Robot;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.Description;

import com.zendaimoney.Dokodemo.engine.Browser;
import com.zendaimoney.Dokodemo.logger.LogUtils;


/**
 * 将静态的断言、sleep封装在这里
 * 
 * 
 */
public class DokodemoTool {
	protected static final Log logger = LogFactory.getLog(DokodemoTool.class);

	/**
	 * Thread.sleep
	 * 
	 * @param seconds
	 */
	public static void sleep(double seconds) {
		try {
			Thread.sleep(Float.valueOf((float) (seconds * 1000)).longValue());
			LogUtils.setSleep(String.valueOf(seconds));
		} catch (InterruptedException ex) {
			logger.error(ex.getMessage());
		}
	}

	/**
	 * Thread.sleep
	 * 
	 * @param seconds
	 */
	public static void sleep(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
			LogUtils.setSleep(String.valueOf(seconds));
		} catch (InterruptedException ex) {
			logger.error(ex.getMessage());
		}
	}

	/**
	 * 验证传入的boolean值是否为true
	 * 
	 * @param actual
	 * @throws Exception 
	 */
	public static void assertTrue(boolean actual) throws Exception {
		try {
			assertThat(actual, is(true));
			LogUtils.setAssertPassed(Browser.getUrl(), "assertTrue", String.valueOf(actual), String.valueOf(true), String.valueOf(actual), "assertTrue操作");
		} catch (AssertionError ex) {
			LogUtils.setAssertFailed(Browser.getUrl(), "assertTrue", String.valueOf(actual), String.valueOf(true), String.valueOf(actual), "assertTrue操作" + "异常：" + ex.getMessage());
			throw ex;
		}
	}

	/**
	 * 验证传入的boolean值是否为true
	 * 
	 * @param actual
	 * @throws Exception 
	 */
	public static void verifyTrue(boolean actual) throws Exception {
		try {
			assertTrue(actual);
		} catch (AssertionError e) {
		}
	}

	/**
	 * 验证传入的boolean值是否为true
	 * 
	 * @param actual
	 * @param message
	 * @throws Exception 
	 */
	public static void assertTrue(boolean actual, String message) throws Exception {
		try {
			assertThat(message, actual, is(true));
			LogUtils.setAssertPassed(Browser.getUrl(), "assertTrue", String.valueOf(actual), String.valueOf(true), String.valueOf(actual), message);
		} catch (AssertionError ex) {
			LogUtils.setAssertFailed(Browser.getUrl(), "assertTrue", String.valueOf(actual), String.valueOf(true), String.valueOf(actual), message + "异常：" + ex.getMessage());
			throw ex;
		}
	}

	/**
	 * 验证传入的boolean值是否为true
	 * 
	 * @param actual
	 * @param message
	 * @throws Exception 
	 */
	public static void verifyTrue(boolean actual, String message) throws Exception {
		try {
			assertTrue(actual, message);
		} catch (AssertionError e) {
		}
	}

	/**
	 * 验证传入的boolean值是否为 false
	 * 
	 * @param actual
	 * @throws Exception 
	 */
	public static void assertFalse(boolean actual) throws Exception {
		try {
			assertThat(actual, is(false));
			LogUtils.setAssertPassed(Browser.getUrl(), "assertFalse", String.valueOf(actual), String.valueOf(false), String.valueOf(actual), "assertFalse操作");
		} catch (AssertionError ex) {
			LogUtils.setAssertFailed(Browser.getUrl(), "assertFalse", String.valueOf(actual), String.valueOf(false), String.valueOf(actual), "assertFalse操作" + "异常：" + ex.getMessage());
			throw ex;
		}
	}

	/**
	 * 验证传入的boolean值是否为 false
	 * 
	 * @param actual
	 * @throws Exception 
	 */
	public static void verifyFalse(boolean actual) throws Exception {
		try {
			assertFalse(actual);
		} catch (AssertionError e) {
		}
	}

	/**
	 * 验证传入的boolean值是否为 false
	 * 
	 * @param actual
	 * @param message
	 * @throws Exception 
	 */
	public static void assertFalse(boolean actual, String message) throws Exception {
		try {
			assertThat(message, actual, is(false));
			LogUtils.setAssertPassed(Browser.getUrl(), "assertFalse", String.valueOf(actual), String.valueOf(false), String.valueOf(actual), message);
		} catch (AssertionError ex) {
			LogUtils.setAssertFailed(Browser.getUrl(), "assertFalse", String.valueOf(actual), String.valueOf(false), String.valueOf(actual), message + "异常：" + ex.getMessage());
			throw ex;
		}
	}

	/**
	 * 验证传入的boolean值是否为 false
	 * 
	 * @param actual
	 * @param message
	 * @throws Exception 
	 */
	public static void verifyFalse(boolean actual, String message) throws Exception {
		try {
			assertFalse(actual, message);
		} catch (AssertionError e) {
		}
	}
	

	
	
	/**
	 * 验证传入实际值和期望值相等
	 * 
	 * @param <T>
	 * @param actual
	 * @param expect
	 * @throws Exception 
	 */
	public static <T> void assertEqual(T actual, T expect) throws Exception {
		try {
			assertThat(actual, equalTo(expect));
			LogUtils.setAssertPassed(Browser.getUrl(), "assertEqual", String.valueOf(actual), String.valueOf(expect), String.valueOf(actual), "assertEqual操作");
		} catch (AssertionError ex) {
			LogUtils.setAssertFailed(Browser.getUrl(), "assertEqual", String.valueOf(actual), String.valueOf(expect), String.valueOf(actual), "assertEqual操作" + "异常：" + ex.getMessage());
			throw ex;
		}
	}

	/**
	 * 验证传入实际值和期望值相等
	 * 
	 * @param <T>
	 * @param actual
	 * @param expect
	 * @throws Exception 
	 */
	public static <T> void verifyEqual(T actual, T expect) throws Exception {
		try {
			assertEqual(actual, expect);
		} catch (AssertionError e) {
		}
	}

	/**
	 * 验证传入实际值和期望值相等
	 * 
	 * @param <T>
	 * @param actual
	 * @param expect
	 * @param message
	 * @throws Exception 
	 */
	public static <T> void assertEqual(T actual, T expect, String message) throws Exception {
		try {
			assertThat(message, actual, equalTo(expect));
			LogUtils.setAssertPassed(Browser.getUrl(), "assertEqual", String.valueOf(actual), String.valueOf(expect), String.valueOf(actual), message);
		} catch (AssertionError ex) {
			LogUtils.setAssertFailed(Browser.getUrl(), "assertEqual", String.valueOf(actual), String.valueOf(expect), String.valueOf(actual), message + "异常：" + ex.getMessage());
			throw ex;
		}
	}

	/**
	 * 验证传入实际值和期望值相等
	 * 
	 * @param <T>
	 * @param actual
	 * @param expect
	 * @param message
	 * @throws Exception 
	 */
	public static <T> void verifyEqual(T actual, T expect, String message) throws Exception {
		try {
			assertEqual(actual, expect, message);
		} catch (AssertionError e) {
		}
	}

	/**
	 * 验证传入的字符串是否符合传入的正则表达式
	 * 
	 * @param actual
	 * @param regxp
	 * @throws Exception 
	 */
	public static void assertMatch(String actual, String regxp) throws Exception {
		try {
			Assert.assertTrue(actual.matches(regxp));
			LogUtils.setAssertPassed(Browser.getUrl(), "assertMatch", actual, regxp, actual, "assertMatch操作");
		} catch (AssertionError ex) {
			LogUtils.setAssertFailed(Browser.getUrl(), "assertMatch", actual, regxp, actual, "assertMatch操作" + "异常：" + ex.getMessage());
			throw ex;
		}
	}

	/**
	 * 验证传入的字符串是否符合传入的正则表达式
	 * 
	 * @param actual
	 * @param regxp
	 * @throws Exception 
	 */
	public static void verifyMatch(String actual, String regxp) throws Exception {
		try {
			assertMatch(actual, regxp);
		} catch (AssertionError e) {
		}
	}

	/**
	 * 验证传入的字符串是否符合传入的正则表达式
	 * 
	 * @param actual
	 * @param regxp
	 * @param message
	 * @throws Exception 
	 */
	public static void assertMatch(String actual, String regxp, String message) throws Exception {
		try {
			Assert.assertTrue(actual.matches(regxp));
			LogUtils.setAssertPassed(Browser.getUrl(), "assertMatch", actual, regxp, actual, message);
		} catch (AssertionError ex) {
			LogUtils.setAssertFailed(Browser.getUrl(), "assertMatch", actual, regxp, actual, message + "异常：" + ex.getMessage());
			throw ex;
		}
	}

	/**
	 * 验证传入的字符串是否符合传入的正则表达式
	 * 
	 * @param actual
	 * @param regxp
	 * @param message
	 * @throws Exception 
	 */
	public static void verifyMatch(String actual, String regxp, String message) throws Exception {
		try {
			assertMatch(actual, regxp, message);
		} catch (AssertionError e) {
		}
	}

	/**
	 * 验证传入的字符串是否包含字符串
	 * 
	 * @param actual
	 * @param regxp
	 * @param message
	 * @throws Exception 
	 */
	public static void assertContains(String actual, String regxp) throws Exception {
		try {
			assertThat(actual, is(containsString(regxp)));
			LogUtils.setAssertPassed(Browser.getUrl(), "assertContains", actual, regxp, actual, "assertContains操作");
		} catch (AssertionError ex) {
			LogUtils.setAssertFailed(Browser.getUrl(), "assertContains", actual, regxp, actual, "assertContains操作" + "异常：" + ex.getMessage());
			throw ex;
		}
	}

	/**
	 * 验证传入的字符串是否包含字符串
	 * 
	 * @param actual
	 * @param regxp
	 * @param message
	 * @throws Exception 
	 */
	public static void verifyContains(String actual, String regxp) throws Exception {
		try {
			assertContains(actual, regxp);
		} catch (AssertionError e) {
		}
	}

	/**
	 * 验证传入的字符串是否包含字符串
	 * 
	 * @param actual
	 * @param regxp
	 * @param message
	 * @throws Exception 
	 */
	public static void assertContains(String actual, String regxp, String message) throws Exception {
		try {
			assertThat(message, actual, is(containsString(regxp)));
			LogUtils.setAssertPassed(Browser.getUrl(), "assertContains", actual, regxp, actual, message);
		} catch (AssertionError ex) {
			LogUtils.setAssertFailed(Browser.getUrl(), "assertContains", actual, regxp, actual, message + "异常：" + ex.getMessage());
			throw ex;
		}
	}

	/**
	 * 验证传入的字符串是否包含字符串
	 * 
	 * @param actual
	 * @param regxp
	 * @param message
	 * @throws Exception 
	 */
	public static void verifyContains(String actual, String regxp, String message) throws Exception {
		try {
			assertContains(actual, regxp, message);
		} catch (AssertionError e) {
		}
	}

	/**
	 * 用于输出用户日志
	 * 
	 * @param message
	 */
	public static void log(String message) {
		LogUtils.setMessage(message);
	}

	/**
	 * @param str
	 * @param encoding
	 * 
	 * @return 编码后的string
	 */
	public static String encode(String str, String encoding) {
		try {
			return URLEncoder.encode(str, encoding);
		} catch (UnsupportedEncodingException e) {
			return str;
		}
	}
	  /******************************************************** autorobot增加的新方法 ******************************************/

	public static void sendKeys(String inputText) {
		try {
			Robot robot = new Robot();
			String text = inputText.toUpperCase();
			for (int i = 0; i < text.length(); i++) {
				robot.keyPress(text.charAt(i));
			}
		} catch (java.awt.AWTException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 输出对比失败的日志
	 * 
	 * @param actual
	 * @param expect
	 * @param ex
	 * @param d
	 * @throws Exception 
	 */
	public static <T> void failLog(T actual, T expect, Throwable ex,
			Description d) throws Exception {
		LogUtils.setAssertFailed(Browser.getUrl(), "assertLenientEquals",
				String.valueOf(actual), String.valueOf(expect),
				String.valueOf(actual),
				"assertLenientEquals操作" + "异常：" + ex.toString());
	}
	
	
	/**
	 * 输出对比成功的日志
	 * 
	 * @param actual
	 * @param expect
	 * @param d
	 */
	public static <T> void successLog(T actual, T expect, Description d) {
		LogUtils.setAssertPassed(Browser.getUrl(), "assertLenientEquals",
				String.valueOf(actual), String.valueOf(expect),
				String.valueOf(actual), "assertLenientEquals操作");
	}
}
