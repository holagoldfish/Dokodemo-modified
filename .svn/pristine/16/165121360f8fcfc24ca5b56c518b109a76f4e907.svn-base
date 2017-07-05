package com.zendaimoney.Dokodemo.logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;

import com.zendaimoney.Dokodemo.listener.ETestLogListener;
import com.zendaimoney.etest.util.DateConverter;

public class LogHandler {
	protected static final Log logger = LogFactory.getLog(LogHandler.class);
	private final static String STATUS = "status";
	private final static String METHOD = "method";
	private static List<String[]> logsBefore = new ArrayList<String[]>();
	private static String currentTestMethod = null;

	private static enum STEP {
		step, type, time, element, status, operation, url, description
	}

	/**
	 * 将日志记录信息追加到指定的日志文件
	 * 
	 * 
	 * @param filePath
	 * @param Logger
	 */
	public static void setStep(String type, String element, String status,
			String operation, String url, String desc) {
		String time = DateConverter.dateToStr(new Date(), null);
		String[] Arr = { type, time, element, status, operation, url, desc };
		System.out.println(desc);
		if (isMethod(org.junit.Before.class)) {
			logsBefore.add(Arr);
			return;
		} else if (isMethod(org.junit.After.class)) {
			writeLog(currentTestMethod, Arr);
		} else {
			currentTestMethod = getCurrentTestMethodName();
			if (!logsBefore.isEmpty()) {
				for (String[] arr : logsBefore) {
					writeLog(currentTestMethod, arr);
				}
				logsBefore.clear();
			}
			writeLog(currentTestMethod, Arr);
		}
	}

	public static void setStep(String methodName, String type, String element,
			String status, String operation, String url, String desc) {
		String time = DateConverter.dateToStr(new Date(), null);
		String[] Arr = { type, time, element, status, operation, url, desc };
		writeLog(methodName, Arr);
	}

	/**
	 * 
	 * 判断传入的类中是不是含有特定的annotation的方法
	 * 
	 * @return
	 */
	private static boolean isMethod(Class<? extends Annotation> comparedClass) {
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		for (StackTraceElement stack : trace) {
			String className = stack.getClassName();
			Class<?> clazz = null;
			try {
				clazz = Class.forName(className);
			} catch (ClassNotFoundException e) {
				continue;
			}
			Method[] methods = clazz.getMethods();
			for (Method method : methods) {
				if (method.isAnnotationPresent(comparedClass)) {
					if (method.getName().equals(stack.getMethodName())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private static void writeLog(String testMethod, String[] Arr) {
		Document doc = ETestLogListener.getDocument();
		if (null == doc) {
			return;
		}
		if (null != testMethod && !testMethod.trim().isEmpty()) {
			Element ele = findElement(doc, testMethod);
			if (ele != null) {
				addElement(ele, Arr);
			}
		}
	}

	/**
	 * 设置Test Method的Status
	 * 
	 * @param status
	 */
	@SuppressWarnings("deprecation")
	public static void setMethodStatus(String status,String methodName) {
		Document doc = ETestLogListener.getDocument();
		if (null == doc) {
			logger.error("找不到日志文档");
			return;
		}
		Element ele = findElement(doc, methodName);
		ele.setAttributeValue(STATUS, status);
	}

	/**
	 * 获取到当前的测试方法名称
	 * 
	 * @return
	 */
	private static String getCurrentTestMethodName() {
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		List<String> Arr = getAllTestMethods();
		for (StackTraceElement stackTraceElement : trace) {
			String current = stackTraceElement.getMethodName();
			if (null == current || current.trim().isEmpty())
				continue;
			for (String xmlMethod : Arr) {
				if (current.equalsIgnoreCase(xmlMethod)) {
					return current;
				}
			}
		}
		return null;
	}

	/**
	 * 获取xml里面的method名称列表
	 */
	private static List<String> getAllTestMethods() {
		List<String> Arr = new ArrayList<String>();
		Document doc = ETestLogListener.getDocument();
		if (null == doc) {
			logger.warn("没有生成日志，请看下测试基类有没有继承ETestWebUIBaseCase？");
			return Arr;
		}
		Element root = doc.getRootElement();
		// 枚举根节点下所有子节点
		for (Iterator<?> ie = root.elementIterator(); ie.hasNext();) {
			Element element = (Element) ie.next();
			Arr.add(element.attributeValue(METHOD));
		}
		return Arr;
	}

	/**
	 * 根据传入的方法名，获取到该节点
	 * 
	 * @param methodName
	 * @return
	 */
	private static Element findElement(Document doc, String methodName) {
		if (null == doc) {
			return null;
		}
		Element root = doc.getRootElement();
		for (Iterator<?> it = root.elementIterator(); it.hasNext();) {
			Element tc = (Element) it.next();
			if (tc.attributeValue(METHOD).equalsIgnoreCase(methodName)) {
				return tc;
			}
		}
		return null;
	}

	/**
	 * 给指定的element添加子element
	 * 
	 * @param element
	 * @param Arr
	 */
	private static void addElement(Element ele, String[] Arr) {
		ele.addElement(STEP.step.toString()).addAttribute(STEP.type.toString(),
				Arr[0]).addAttribute(STEP.time.toString(), Arr[1])
				.addAttribute(STEP.element.toString(), Arr[2]).addAttribute(
						STEP.status.toString(), Arr[3]).addAttribute(
						STEP.operation.toString(), Arr[4]).addAttribute(
						STEP.url.toString(), Arr[5]).addElement(
						STEP.description.toString()).addCDATA(Arr[6]);

	}
}
