package com.zendaimoney.Dokodemo.html;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

import com.zendaimoney.Dokodemo.engine.Browser;
import com.zendaimoney.Dokodemo.engine.Engine;
import com.zendaimoney.Dokodemo.logger.LogUtils;



public class ElementList<T extends Locator> extends ArrayList<T> {
	private static final long serialVersionUID = 1L;
	private String selector;
	private PageModel parent;
	private String name;
	private String description;
	private Class<T> type;
	private ArrayList<Engine> current;
	
	LogUtils logutils = new LogUtils();

	public ElementList(Class<T> type, String selector, PageModel parent,
			String name, String description) {
		this.selector = selector;
		this.parent = parent;
		this.name = name;
		this.description = description;
		this.type = type;
	}

	@Override
	public int size() {
		locate();
		return current.size();
	}

	public boolean empty() {
		locate();
		return current.size() == 0;
	}

	public boolean exist() {
		locate();
		return current.size() != 0;
	}

	/**
	 * 通过p.subModels.get(0)的方式，返回元素
	 * 
	 * @param index
	 *            返回第index个元素
	 */
	public T get(int index) {
		locate();
		if (current == null || index >= current.size()) {
			try {
				LogUtils.setElementEmpty(Browser.getUrl(), "get(" + index + ")",
						Engine.getActionInfo(parent.engine));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return NullObjectPageModel.getInstance(type);
		}
		Engine e = current.get(index);
		T result = construct(type, selector, parent, name, description);
		result.setEngine(e);
		return result;
	}

	private void locate() {
		parent.locate();
		if (current == null) {
			current = parent.getEngine().findsBySelector(selector, name,
					description);
		}
	}

	/**
	 * 通过p.subModels.get("文本内容")的方式，返回元素
	 * 
	 * @param text
	 *            需要查找的文本
	 * @throws Exception 
	 */
	public T get(String text) throws Exception {
		locate();
		T result = null;
		for (Engine ele : current) {
			if (ele.text().contains(text)) {
				result = construct(type, selector, parent, name, description);
				result.setEngine(ele);
				break;
			}
		}
		if (current == null || current.size() == 0 || result == null) {
			LogUtils.setElementEmpty(Browser.getUrl(), "get(" + text + ")",
					Engine.getActionInfo(parent.engine));
			return NullObjectPageModel.getInstance(type);
		} else {
			return result;
		}
	}

	/**
	 * 通过p.subModels.getbyId("id")的方式，返回元素
	 * 
	 * @param text
	 *            返回符合id=text的元素
	 * @throws Exception 
	 */
	public T getbyId(String text) throws Exception {
		locate();
		T result = null;
		for (Engine ele : current) {
			if (ele.id().contains(text)) {
				result = construct(type, selector, parent, name, description);
				result.setEngine(ele);
				break;
			}
		}
		if (current == null || current.size() == 0 || result == null) {
			LogUtils.setElementEmpty(Browser.getUrl(), "getbyId(" + text + ")",
					Engine.getActionInfo(parent.engine));
			return NullObjectPageModel.getInstance(type);
		} else {
			return result;
		}
	}
	
	/**
	 * 通过p.subModels.getbyclass("class")的方式，返回元素
	 * 
	 * @param text
	 *            返回符合class=text的元素
	 * @throws Exception 
	 */
	
	public T getbyClass(String text) throws Exception {
		locate();
		T result = null;
		for (Engine ele : current) {
			if (ele.className().contains(text)) {
				result = construct(type, selector, parent, name, description);
				result.setEngine(ele);
				break;
			}
		}
		if (current == null || current.size() == 0 || result == null) {
			LogUtils.setElementEmpty(Browser.getUrl(), "getbyClass(" + text + ")",
					Engine.getActionInfo(parent.engine));
			return NullObjectPageModel.getInstance(type);
		} else {
			return result;
		}
	}
	
	private T construct(Class<T> type, String arg1, PageModel arg2,
			String arg3, String arg4) {
		try {
			Constructor<T> constructor = type.getConstructor(String.class,
					PageModel.class, String.class, String.class);
			return constructor.newInstance(arg1, arg2, arg3, arg4);
		} catch (Exception ex) {
			throw new RuntimeException(type.getName() + "必须含有双参构造函数", ex);
		}
	}
}
