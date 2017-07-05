package com.zendaimoney.Dokodemo.engine;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.commons.lang.NullArgumentException;
import org.openqa.selenium.WebElement;

import com.google.common.base.Strings;

/**
 * 抽象类，代表抽象的html节点
 */
public abstract class Engine {

	private static ArrayList<String> nameInfo = new ArrayList<String>();
	private static ArrayList<String> descriptionInfo = new ArrayList<String>();

	/**
	 * quit the driver, close all windows
	 */
	public abstract void closeall();

	/**
	 * close the current browser
	 */
	public abstract void close();

	/**
	 * html里的控件类型如div, windows里的control name
	 */
	public abstract String control();

	/**
	 * html里的class
	 */
	public abstract String className();

	/**
	 * html里的id
	 */
	public abstract String id();

	/**
	 * 元素value属性值
	 */
	public abstract String value();

	/**
	 * 父亲节点
	 */
	protected abstract Engine parent();

	/**
	 * 儿子节点
	 */
	public abstract ArrayList<Engine> children();

	/**
	 * 后面一个节点
	 */
	protected abstract Engine next();

	/**
	 * 点击方法
	 */
	public abstract void click();

	/**
	 * 提交表单方法
	 */
	public abstract void submit();

	/**
	 * 点击方法：当click()方法报错时用该方法
	 */
	public abstract void click_js();

	public abstract void click_ie();

	/**
	 * 模拟用户点击方法
	 */
	public abstract void doclick();

	/**
	 * 获取文本
	 */
	public abstract String text();

	/**
	 * 获取属性
	 */
	public abstract String getAttribute(String name);

	/**
	 * 设置属性
	 */
	public abstract void setAttribute(String js);

	/**
	 * 模拟键盘事件
	 */
	public abstract void sendKey(String name);

	public abstract String innerHTML();

	public abstract String get(String name);

	public abstract String get_style(String name);

	public abstract boolean visible();

	public abstract void uncheck();

	public abstract void check();

	public abstract boolean checked();// +

	public abstract boolean isEmpty(String... message);

	public abstract void select(String value);

	public abstract void select_by_value(String value);

	public abstract void select_by_index(Integer index);

	public abstract ArrayList<String> selected_value();

	public abstract ArrayList<String> selected_innervalue();

	public abstract ArrayList<String> options();

	public abstract void mouseover();

	public abstract void doubleclick();

	public abstract void drag(WebElement element);

	public abstract void scrollIntoView(Boolean top);

	public abstract void fire_event(String name);

	public abstract void js_error();
	
	public abstract void display();

	public abstract String get_content();
	
	public abstract boolean is_alert_exist();

	public abstract String maxlength();

	public abstract void maxlength(String length);

	public abstract String readonly();

	public abstract void readonly(Boolean readonly);

	public abstract void dealdialog(String type);
	
	public abstract String dealPrompt(String input, boolean isYes);

	public abstract void focus();
	
	public abstract void selectFrame();

	/**
	 * 获取Action信息
	 */
	public abstract String getAction();

	public Engine findBySelector(String selector, String name,
			String description) {
		ArrayList<Engine> engines = findsBySelector(selector, name, description);
		if (engines.size() == 0) {
			return null;
			// throw new RuntimeException("找不到元素，selector为：" + selector);
		} else {
			return engines.get(0);
		}
	}

	public ArrayList<Engine> findsBySelector(String selector, String name,
			String description) {
		setActionInfo(name, description);
		return new Selector(this).find(selector).getCurrent();
	}

	// 下面是具体类型控件要用到的方法
	public abstract void set(String value);


	public abstract void setInnerText(String value);

	// 下面是具体的实现
	public boolean matchAttribute(String name, String operation, String value) {
		String actual = getAttribute(name);
		if (Strings.isNullOrEmpty(actual)) {
			return false;
		}/*
		 * else { actual = actual.toString(); }
		 */
		if (Strings.isNullOrEmpty(operation)) {
			return false;
		}
		switch (operation.charAt(0)) {
		case '=':
			return actual.equalsIgnoreCase(value);
		case '!':
			return !actual.equalsIgnoreCase(value);
		case '^':
			// 以..开始
			return actual.startsWith(value);
		case '$':
			// 以..结尾
			return actual.endsWith(value);
		case '*':
			// 包含
			return actual.contains(value);
		default:
			throw new RuntimeException(operation + "not supported");
		}
	}

	public boolean matchContent(String operation, String option) {
		if (operation.equalsIgnoreCase("contains")) {
			String e = text();
			if (e != null) {
				String value = option.replaceAll("'", "");
				return e.contains(value);
			} else {
				return false;
			}
		} else {
			throw new RuntimeException(operation + "not supported");
		}
	}

	public enum MatchType {
		Id, ControlName, ClassName, CssName, All
	}

	public boolean matchElement(String element, MatchType type) {
		if (Strings.isNullOrEmpty(element)) {
			throw new NullArgumentException(element);
		}
		String temp;
		switch (type) {
		case Id:
			temp = id();
			if (Strings.isNullOrEmpty(temp)) {
				return false;
			} else {
				return element.equalsIgnoreCase(temp);
			}
		case ControlName:
			temp = control();
			if (Strings.isNullOrEmpty(temp)) {
				return false;
			} else {
				return element.equalsIgnoreCase(temp);
			}
		case ClassName:
			temp = className();
			if (Strings.isNullOrEmpty(temp)) {
				return false;
			} else {
				return element.equalsIgnoreCase(temp);
			}
		case CssName:
			temp = className();
			if (Strings.isNullOrEmpty(temp)) {
				return false;
			} else {
				temp = temp.replace(" ", ".");
				temp = "." + temp;
				return element.equalsIgnoreCase(temp);
			}
		case All:
			return true;
		default:
			throw new RuntimeException("not supported");
		}
	}

	/*
	 * need to be @Override,用cssSelector的方式查找元素
	 */
	public ArrayList<Engine> get_element_by_css_selector(String value,
			com.zendaimoney.Dokodemo.engine.Selector.Scope scope) {
		ArrayList<Engine> collection = getScopeElement(scope);
		return filterCollection(collection, value, MatchType.CssName);
	}

	/*
	 * need to be @Override
	 */
	public ArrayList<Engine> get_element_by_class_name(String value,
			com.zendaimoney.Dokodemo.engine.Selector.Scope scope) {
		ArrayList<Engine> collection = getScopeElement(scope);
		return filterCollection(collection, value, MatchType.ClassName);
	}

	/*
	 * need to be @Override
	 */
	public ArrayList<Engine> get_element_by_id(String value,
			com.zendaimoney.Dokodemo.engine.Selector.Scope scope) {
		ArrayList<Engine> collection = getScopeElement(scope);
		return filterCollection(collection, value, MatchType.Id);
	}

	/*
	 * need to be @Override
	 */
	public ArrayList<Engine> get_element_by_control_name(String value,
			com.zendaimoney.Dokodemo.engine.Selector.Scope scope) {
		ArrayList<Engine> collection = getScopeElement(scope);
		return filterCollection(collection, value, MatchType.ControlName);
	}

	/*
	 * need to be @Override
	 */
	public ArrayList<Engine> descendant() {
		ArrayList<Engine> result = new ArrayList<Engine>();
		Queue<Engine> queue = new LinkedList<Engine>();
		queue.addAll(children());
		Engine current;
		// 用队列(后进前出)代替递归，目前是层级遍历
		while ((current = queue.poll()) != null) {
			result.add(current);
			for (Engine engine : current.children()) {
				queue.offer(engine);
			}
		}
		return result;
	}

	/*
	 * need to be @Override
	 */
	public ArrayList<Engine> siblings() {
		Engine e = parent();
		if (e == null) {
			return null;
		} else {
			ArrayList<Engine> engines = new ArrayList<Engine>();
			engines.addAll(e.children());
			engines.remove(this);
			return engines;
		}
	}

	public ArrayList<Engine> getScopeElement(
			com.zendaimoney.Dokodemo.engine.Selector.Scope scope) {
		switch (scope) {
		case Descendant:
			return descendant();
		case Child:
			return children();
		case Next:
			Engine e = next();
			if (e == null)
				return null;
			else {
				ArrayList<Engine> engines = new ArrayList<Engine>();
				engines.add(e);
				return engines;
			}
		case Siblings:
			return siblings();
		case None:
			throw new RuntimeException("not supported");
		}
		throw new RuntimeException("not supported");
	}

	public ArrayList<Engine> filterCollection(ArrayList<Engine> collection,
			String element, MatchType type) {
		ArrayList<Engine> list = new ArrayList<Engine>();
		if (collection == null) {
			return null;
		} else {
			for (Engine engine : collection) {
				if (engine.matchElement(element, type)) {
					list.add(engine);
				}
			}
		}
		return list;
	}

	private void setActionInfo(String name, String description) {
		Engine.nameInfo.add(name);
		Engine.descriptionInfo.add(description);
	}

	public static void clearActionInfo() {//switch到最后一个窗口
		Engine.nameInfo = new ArrayList<String>();
		Engine.descriptionInfo = new ArrayList<String>();
		if (Browser.getBaseBrowser() != null) {
			try {
				int lenth = Browser.get_all_ies().size();
				Browser.getDriver().switchTo().window(Browser.get_all_ies().get(lenth - 1).handle);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static String getActionInfo(Engine engine) {
		if (!Engine.nameInfo.isEmpty()) {
			String actionInfo = Engine.nameInfo.get(0) + "("
					+ Engine.descriptionInfo.get(0) + ")";
			for (int i = 1; i < Engine.nameInfo.size(); i++) {
				actionInfo = actionInfo + "." + Engine.nameInfo.get(i) + "("
						+ Engine.descriptionInfo.get(i) + ")";
			}
			return actionInfo;
		}
		if (null == engine) {
			return null;
		}
		return engine.getAction();
	}


}
