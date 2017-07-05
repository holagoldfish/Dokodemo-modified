package com.zendaimoney.Dokodemo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zendaimoney.Dokodemo.listener.ETestLogListener;
import com.zendaimoney.etest.ETestSpringContextBaseCase;
import com.zendaimoney.etest.core.TestListeners;

@TestListeners( {  ETestLogListener.class })
public class ETestWebUIBaseCase extends ETestSpringContextBaseCase {
	protected static final Log logger = LogFactory
			.getLog(ETestWebUIBaseCase.class);

	// 原来封装的有问题，会丢失最初的异常堆栈信息
	/**
	 * Thread.sleep
	 * 
	 * @param seconds
	 */
	public void sleep(double seconds) {
		DokodemoTool.sleep(seconds);
	}

	/**
	 * Thread.sleep
	 * 
	 * @param seconds
	 */
	public void sleep(int seconds) {
		DokodemoTool.sleep(seconds);
	}

	/**
	 * 验证传入的boolean值是否为true
	 * 
	 * @param actual
	 * @throws Exception 
	 */
	protected void assertTrue(boolean actual) throws Exception {
		DokodemoTool.assertTrue(actual);
	}

	/**
	 * 验证传入的boolean值是否为true
	 * 
	 * @param actual
	 * @throws Exception 
	 */
	protected void verifyTrue(boolean actual) throws Exception {
		DokodemoTool.verifyTrue(actual);
	}

	/**
	 * 验证传入的boolean值是否为true
	 * 
	 * @param actual
	 * @param message
	 * @throws Exception 
	 */
	protected void assertTrue(boolean actual, String message) throws Exception {
		DokodemoTool.assertTrue(actual, message);
	}

	/**
	 * 验证传入的boolean值是否为true
	 * 
	 * @param actual
	 * @param message
	 * @throws Exception 
	 */
	protected void verifyTrue(boolean actual, String message) throws Exception {
		DokodemoTool.verifyTrue(actual, message);
	}

	/**
	 * 验证传入的boolean值是否为 false
	 * 
	 * @param actual
	 * @throws Exception 
	 */
	protected void assertFalse(boolean actual) throws Exception {
		DokodemoTool.assertFalse(actual);
	}

	/**
	 * 验证传入的boolean值是否为 false
	 * 
	 * @param actual
	 * @throws Exception 
	 */
	protected void verifyFalse(boolean actual) throws Exception {
		DokodemoTool.verifyFalse(actual);
	}

	/**
	 * 验证传入的boolean值是否为 false
	 * 
	 * @param actual
	 * @param message
	 * @throws Exception 
	 */
	protected void assertFalse(boolean actual, String message) throws Exception {
		DokodemoTool.assertFalse(actual, message);
	}

	/**
	 * 验证传入的boolean值是否为 false
	 * 
	 * @param actual
	 * @param message
	 * @throws Exception 
	 */
	protected void verifyFalse(boolean actual, String message) throws Exception {
		DokodemoTool.verifyFalse(actual, message);
	}

	/**
	 * 验证传入实际值和期望值相等
	 * 
	 * @param <T>
	 * @param actual
	 * @param expect
	 * @throws Exception 
	 */
	protected <T> void assertEqual(T actual, T expect) throws Exception {
		DokodemoTool.assertEqual(actual, expect);
	}

	/**
	 * 验证传入实际值和期望值相等
	 * 
	 * @param <T>
	 * @param actual
	 * @param expect
	 * @throws Exception 
	 */
	protected <T> void verifyEqual(T actual, T expect) throws Exception {
		DokodemoTool.verifyEqual(actual, expect);
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
	protected static <T> void assertEqual(T actual, T expect, String message) throws Exception {
		DokodemoTool.assertEqual(actual, expect, message);
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
	protected <T> void verifyEqual(T actual, T expect, String message) throws Exception {
		DokodemoTool.verifyEqual(actual, expect, message);
	}

	/**
	 * 验证传入的字符串是否符合传入的正则表达式
	 * 
	 * @param actual
	 * @param regxp
	 * @throws Exception 
	 */
	protected void assertMatch(String actual, String regxp) throws Exception {
		DokodemoTool.assertMatch(actual, regxp);
	}

	/**
	 * 验证传入的字符串是否符合传入的正则表达式
	 * 
	 * @param actual
	 * @param regxp
	 * @throws Exception 
	 */
	protected void verifyMatch(String actual, String regxp) throws Exception {
		DokodemoTool.verifyMatch(actual, regxp);
	}

	/**
	 * 验证传入的字符串是否符合传入的正则表达式
	 * 
	 * @param actual
	 * @param regxp
	 * @param message
	 * @throws Exception 
	 */
	protected void assertMatch(String actual, String regxp, String message) throws Exception {
		DokodemoTool.assertMatch(actual, regxp, message);
	}

	/**
	 * 验证传入的字符串是否符合传入的正则表达式
	 * 
	 * @param actual
	 * @param regxp
	 * @param message
	 * @throws Exception 
	 */
	protected void verifyMatch(String actual, String regxp, String message) throws Exception {
		DokodemoTool.verifyMatch(actual, regxp, message);
	}

	/**
	 * 验证传入的字符串是否包含字符串
	 * 
	 * @param actual
	 * @param regxp
	 * @param message
	 * @throws Exception 
	 */
	protected void assertContains(String actual, String regxp) throws Exception {
		DokodemoTool.assertContains(actual, regxp);
	}

	/**
	 * 验证传入的字符串是否包含字符串
	 * 
	 * @param actual
	 * @param regxp
	 * @param message
	 * @throws Exception 
	 */
	protected void verifyContains(String actual, String regxp) throws Exception {
		DokodemoTool.verifyContains(actual, regxp);
	}

	/**
	 * 验证传入的字符串是否包含字符串
	 * 
	 * @param actual
	 * @param regxp
	 * @param message
	 * @throws Exception 
	 */
	protected void assertContains(String actual, String regxp, String message) throws Exception {
		DokodemoTool.assertContains(actual, regxp, message);
	}

	/**
	 * 验证传入的字符串是否包含字符串
	 * 
	 * @param actual
	 * @param regxp
	 * @param message
	 * @throws Exception 
	 */
	protected void verifyContains(String actual, String regxp, String message) throws Exception {
		DokodemoTool.verifyContains(actual, regxp, message);
	}

	/**
	 * 用于输出用户日志
	 * 
	 * @param message
	 */
	protected static void log(String message) {
		DokodemoTool.log(message);
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

	/**
	 * 验证传入的boolean值是否为true
	 * 
	 * @param actual
	 * @throws Exception 
	 */
	public void assertTrue(String condition) throws Exception {
		boolean actual = Boolean.valueOf(condition);
		assertTrue(actual);
	}

	/**
	 * 验证传入的boolean值是否为true
	 * 
	 * @param actual
	 * @throws Exception 
	 */
	public void verifyTrue(String condition) throws Exception {
		boolean actual = Boolean.valueOf(condition);
		verifyTrue(actual);
	}

	/**
	 * 验证传入的boolean值是否为 false
	 * 
	 * @param actual
	 * @throws Exception 
	 */
	public void assertFalse(String condition) throws Exception {
		boolean actual = Boolean.valueOf(condition);
		assertFalse(actual);
	}

	/**
	 * 验证传入的boolean值是否为 false
	 * 
	 * @param actual
	 * @throws Exception 
	 */
	public void verifyFalse(String condition) throws Exception {
		boolean actual = Boolean.valueOf(condition);
		verifyFalse(actual);
	}

	/**
	 * 验证传入实际值和期望值相等
	 * 
	 * @param <T>
	 * @param actual
	 * @param expect
	 * @throws Exception 
	 */
	public void assertEqual(String conditionInfo) throws Exception {
		String conditions[] = conditionInfo.split(",");
		if (conditions.length < 2) {
			return;
		}
		String actual = conditions[1];
		String expect = conditions[2];
		assertEqual(actual, expect);
	}

	/**
	 * 验证传入实际值和期望值相等
	 * 
	 * @param <T>
	 * @param actual
	 * @param expect
	 * @throws Exception 
	 */
	public void verifyEqual(String conditionInfo) throws Exception {
		String condition[] = conditionInfo.split(",");
		if (condition.length < 2) {
			return;
		}
		String actual = condition[1];
		String expect = condition[2];
		verifyEqual(actual, expect);
	}

	/**
	 * 验证传入的字符串是否包含字符串
	 * 
	 * @param actual
	 * @param regxp
	 * @param message
	 * @throws Exception 
	 */
	public void assertContains(String conditionInfo) throws Exception {
		String condition[] = conditionInfo.split(",");
		if (condition.length < 2) {
			return;
		}
		String actual = condition[1];
		String regxp = condition[2];
		assertContains(actual, regxp);
	}

	/**
	 * 验证传入的字符串是否包含字符串
	 * 
	 * @param actual
	 * @throws Exception 
	 */
	public void verifyContains(String conditionInfo) throws Exception {
		String condition[] = conditionInfo.split(",");
		if (condition.length < 2) {
			return;
		}
		String actual = condition[1];
		String regxp = condition[2];
		verifyContains(actual, regxp);
	}
	
	/**
	 * 断言是否匹配
	 * 
	 * @param actual
	 * @throws Exception 
	 */
	public void assertMatch(String conditionInfo) throws Exception {
		String condition[] = conditionInfo.split(",");
		if (condition.length < 2) {
			return;
		}
		String actual = condition[1];
		String regxp = condition[2];
		assertMatch(actual, regxp);
	}
	
	/**
	 * 验证是否匹配
	 * 
	 * @param actual
	 * @throws Exception 
	 */
	public void verifyMatch(String conditionInfo) throws Exception {
		String condition[] = conditionInfo.split(",");
		if (condition.length < 2) {
			return;
		}
		String actual = condition[1];
		String regxp = condition[2];
		verifyMatch(actual, regxp);
	}
	
	
}