package com.zendaimoney.Dokodemo.listener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.zendaimoney.Dokodemo.logger.LogHandler;
import com.zendaimoney.Dokodemo.util.FileUtils;
import com.zendaimoney.etest.core.AbstractTestListener;
import com.zendaimoney.etest.core.TestContext;
import com.zendaimoney.etest.util.DateConverter;

/**
 * 测试类初始化时生成文档，结束时统一写到文件
 * 
 * 
 */
public class ETestLogListener extends AbstractTestListener {
	protected static final Log logger = LogFactory.getLog(ETestLogListener.class);
	private final static String STATUS = "status";
	private final static String METHOD = "method";
	private final static String TESTCASE = "testcase";
	private final static String PACKAGE = "package";
	private final static String STEP = "step";
	@SuppressWarnings("unused")
	private final static String DESCRIPTION = "description";
	private final static String CLASS = "class";
	private final static String ROOT = "root";
	private final static String HREF = "href";
	private final static String TYPE = "type";
	private final static String XML_TYPE = "text/xsl";
	private final static String XML_STYLE_SHEET = "xml-stylesheet";
	private final static String LOGGER_STYLE_FILE = "logger.xsl";
	private static String BASE_DIR = "C:\\dokodemo_log\\";
	private final static String XML_EXTENSION = ".xml";
	private final static String AFTER_CLASS = "AfterClass";
	private final static String BEFORE_CLASS = "BeforeClass";

	private final static String BUILD_ID = "build_id";
	private final static String RESTART = "restart";
	private static Document doc = null;

	@Override
	public void beforeTestClass(TestContext testContext) throws Exception {
		createDoc(testContext.getTestClass());
	}

	@Override
	public void afterTestClass(TestContext testContext) throws Exception {
		saveDoc(doc, fileName);
	}

	public static Document getDocument() {
		return doc;
	}

	public static void createDoc(Class<?> Clazz) {
		String className = Clazz.getSimpleName();
		setFilePath(className);
		File file = new File(fileName);
		if (file.exists()) {// 打日志是否重启，目前一个Class生成一个日志
			String reatartflag = (String) System.getProperties().get(RESTART);
			logger.info("Kelude是否重启的标志为：" + reatartflag);
			file.delete();
		}
		Map<String, String> map = getTestClassTestMethods(Clazz);
		Package pac = Clazz.getPackage();
		String packageName = "";
		if (null != pac) {
			packageName = pac.getName();
		}
		doc = DocumentHelper.createDocument();
		// Processing Instruction
		Map<String, String> inMap = new HashMap<String, String>();
		inMap.put(TYPE, XML_TYPE);
		inMap.put(HREF, LOGGER_STYLE_FILE);
		doc.addProcessingInstruction(XML_STYLE_SHEET, inMap);
		// root element
		Element root = doc.addElement(ROOT).addAttribute(PACKAGE, packageName).addAttribute(CLASS, className);
		for (String method : map.keySet()) {
			Element tc = null;
			if (method.equals(AFTER_CLASS) || method.equals(BEFORE_CLASS)) {
				tc = root.addElement(method);
			} else {
				tc = root.addElement(TESTCASE);
			}
			tc.addAttribute(METHOD, map.get(method)).addAttribute(STATUS, "passed");
		}
	}

	/**
	 * 获取Test Class里面有@Test的Method列表
	 * 
	 * @param Clazz
	 * @return
	 */
	private static Map<String, String> getTestClassTestMethods(Class<?> Clazz) {
		Method[] methods = Clazz.getMethods();
		Map<String, String> map = new LinkedHashMap<String, String>();
		int i = 0;
		for (Method method : methods) {
			String methodName = method.getName();
			if (method.isAnnotationPresent(org.junit.BeforeClass.class) || method.isAnnotationPresent(org.junit.AfterClass.class)) {
				map.put(method.getAnnotations()[0].annotationType().getSimpleName(), methodName);
			} else if (method.isAnnotationPresent(org.junit.Test.class)) {
				map.put(String.valueOf(i), methodName);
				i++;
			}
		}

		return map;
	}

	private static String fileName = "";// 当前日志的路径
	private static String tagClassName = "";// 当前执行的测试类的名字

	/**
	 * 设置日志文件的名称和路径
	 * 
	 * @param className
	 */
	private static void setFilePath(String className) {
		if (null == className || className.trim().equals("")) {
			return;
		}
		if (!tagClassName.equals(className)) {
			tagClassName = className;
			fileName = "";
		}
		if (fileName.equals("")) {
			Properties properties = FileUtils.getProperties();
			BASE_DIR = properties.getProperty("logPath", BASE_DIR);
			if (!BASE_DIR.endsWith("/")) {
				BASE_DIR = BASE_DIR + "/";
			}
			String directoryPath = BASE_DIR + DateConverter.dateToStr(new Date(), "yyyy-MM-dd");
			File file = new File(directoryPath);
			if (!file.exists()) {
				file.mkdirs();
			}
			if (System.getProperties().get(BUILD_ID) != null) {
				fileName = className + "_" + System.getProperties().get(BUILD_ID) + XML_EXTENSION;
			} else {
				fileName = className + "_" + System.currentTimeMillis() + XML_EXTENSION;
			}
			fileName = directoryPath + "\\" + fileName;
		}
	}

	public static void saveDoc(Document doc, String filePath) {
		if (null == doc) {
			logger.warn("Document为null");
			return;
		}
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			File file = new File(filePath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
			writer.write(doc);
			writer.flush();
			writer.close();
		} catch (IOException ex) {
			logger.error(ex.getMessage());
		}
	}
	
	@Override
	public void afterTestMethod(TestContext testContext) throws Exception {
		if (null == doc) {
			return;
		}
		String methodName = testContext.getTestMethod().getName();
		Element testElement = null;
		@SuppressWarnings("unchecked")
		List<Element> elements = doc.getRootElement().elements(TESTCASE);
		if (null == elements || elements.isEmpty()) {
			return;
		}
		for (Element element : elements) {
			if (methodName.equals(element.attributeValue(METHOD))) {
				testElement = element;
				break;
			}
		}
		if (null == testElement) {
			return;
		}
		@SuppressWarnings("unchecked")
		List<Element> steps = testElement.elements(STEP);
		if (null == steps || steps.isEmpty()) {
			return;
		}
		StringBuffer ex = new StringBuffer("");
		for (Element step : steps) {
			String status = step.attributeValue(STATUS);
			if (!status.equalsIgnoreCase("passed")) {
				LogHandler.setMethodStatus("failed",methodName);
				ex.append(step.asXML());
			}
		}
		if (!ex.toString().equals("")) {
			throw new java.lang.AssertionError(ex.toString());
		}
	}
	
	
	

}
