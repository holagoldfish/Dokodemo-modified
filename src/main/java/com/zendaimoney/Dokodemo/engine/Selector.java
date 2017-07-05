package com.zendaimoney.Dokodemo.engine;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.zendaimoney.Dokodemo.engine.Engine.MatchType;
/**
 * Selector的查找方式
 */
public class Selector {
	private ArrayList<Engine> current = new ArrayList<Engine>();

	public enum Scope {
		Descendant, Child, Next, Siblings, None
	}

	public Selector(Engine engine) {
		this.current.add(engine);
	}

	public Selector(ArrayList<Engine> engines) {
		this.current.addAll(engines);
	}

	public ArrayList<Engine> getCurrent() {
		return current;
	}

	/**
	 * @param selector
	 * @return
	 */
	public Selector find(String selector) {
		return internalLookUp(selector, Scope.Descendant);
	}

	/**
	 * @param selector
	 * @param name
	 * @return
	 */
	private Selector internalLookUp(String selector, Scope scope) {
		Selector result = this;
		Tokenizer tokenizer = new Tokenizer(selector);
		String token;
		Method action = null;
		ArrayList<String> param = null;
		while ((token = tokenizer.getNextOrderByElement()) != null) {
			switch (token.charAt(0)) {
			case ' ':
				scope = Scope.Descendant;
				continue;
			case '>':
				scope = Scope.Child;
				continue;
			case '[':
				param = getParameter(Tokenizer.Attribute_Regex, token);
				param.set(3, translate(param.get(3)));
				action = getAction("[attribute]");
				break;
			case ':':
				param = getParameter(Tokenizer.Filter_Regex, token);
				param.set(2, translate(param.get(2)));
				action = getAction(param.get(1));
				break;
			default:
				param = getParameter(Tokenizer.Element_Regex, token);
				// 如果token中有"~"，保留原来的token
				if (token.contains("~")) {
					param.set(2, token.substring(1));
				}
				// 去除"\"
				param.set(2, translate(param.get(2)));
				action = getAction("[element]");
				break;
			}
			try {
				result = (Selector) action.invoke(this, result, scope, param);
				if (result.current.size() == 0) {
					return result;
				}
				scope = Scope.None;
			} catch (Exception e) {
				throw new RuntimeException("方法调用失败", e);
			}
		}
		return result;
	}

	private static final Map<String, Method> methods = new ConcurrentHashMap<String, Method>();

	private static Method getAction(String name) {
		if (name.equals("[element]")) {
			name = "DoElement";
		} else if (name.equals("[attribute]")) {
			name = "DoAttribute";
		} else if (name.equals("eq") || (name.equals("first"))
				|| (name.equals("last"))) {
			name = "DoFilter";
		} else if (name.equals("contains")) {
			name = "DoContentFilter";
		}
		if (!methods.containsKey(name)) {
			try {
				methods.put(name, Selector.class.getMethod(name,
						Selector.class, Scope.class, ArrayList.class));
			} catch (Exception ex) {
				throw new RuntimeException("从类" + Selector.class.getName()
						+ "中获取方法" + name + "失败", ex);
			}
		}
		return methods.get(name);
	}

	private static ArrayList<String> getParameter(Pattern regex, String token) {
		ArrayList<String> arr = new ArrayList<String>();
		Matcher m = regex.matcher(token);
		if (m.find()) {
			for (int i = 0; i <= m.groupCount(); i++) {
				arr.add(m.group(i));
			}
		} else {
			throw new RuntimeException("no match found");
		}
		return arr;
	}

	// 处理转义字符
	private static String translate(String text) {
		if (text != null) {
			return text.replace("\\", ""); // bug here
		} else {
			return text;
			// throw new NullPointerException("不翻译NULL字符串");
		}
	}

	public static Selector DoAttribute(Selector selector, Scope scope,
			ArrayList<String> param) {
		String name = param.get(1);
		String operation = param.get(2);
		String value = param.get(3);
		ArrayList<Engine> list = new ArrayList<Engine>();
		for (Engine engine : selector.current) {
			if (engine.matchAttribute(name, operation, value)) {
				list.add(engine);
			}
		}
		return new Selector(list);

	}

	public static Selector DoFilter(Selector selector, Scope scope,
			ArrayList<String> param) {
		String opration = param.get(1);
		String option = param.get(2);
		ArrayList<Engine> list = new ArrayList<Engine>();
		if (opration.equals("eq")) {
			list.add(selector.current.get(Integer.parseInt(option)));
		} else if (opration.equals("first")) {
			list.add(selector.current.get(0));
		} else if (opration.equals("last")) {
			list.add(selector.current.get(selector.current.size() - 1));
		} else
			throw new RuntimeException("not supported");

		return new Selector(list);
	}

	/*
	 * 添加DoContentFilter，处理contains
	 */
	public static Selector DoContentFilter(Selector selector, Scope scope,
			ArrayList<String> param) {
		String opration = param.get(1);
		String option = param.get(2);
		ArrayList<Engine> list = new ArrayList<Engine>();
		for (Engine engine : selector.current) {
			if (engine.matchContent(opration, option)) {
				list.add(engine);
			}
		}
		return new Selector(list);
	}

	public static Selector DoElement(Selector selector, Scope scope,
			ArrayList<String> param) {
		String element = param.get(2);
		MatchType type;
		ArrayList<Engine> list = new ArrayList<Engine>();
		if (param.get(1).equals("#")) {
			type = MatchType.Id;
		} else {
			if (param.get(1).equals(".")) {
				type = MatchType.ClassName;
			} else {
				if (element.equals("*")) {
					type = MatchType.All;
				} else {
					type = MatchType.ControlName;
				}
			}
		}
		if (scope.equals(Scope.None)) {
			switch (type) {
			case Id:
			case ControlName:
			case ClassName:
				for (Engine engine : selector.current) {
					if (engine.matchElement(element, type)) {
						list.add(engine);
					}
				}
				break;
			case All:
				throw new RuntimeException("not supported");
			}
		} else {
			// #scope lookup
			switch (type) {

			case Id:
				for (Engine engine : selector.current) {
					list.addAll(engine.get_element_by_id(element, scope));
				}
				break;
			case ControlName:
				for (Engine engine : selector.current) {
					list.addAll(engine.get_element_by_control_name(element,
							scope));
				}
				break;
			case ClassName:
				for (Engine engine : selector.current) {
					// 添加用cssSelector的方式查找元素，By.cssSelector(".input.allWidth")
					if (element.contains(" ")) {
						element = element.replaceAll(" ", ".");
						element = "." + element;
						list.addAll(engine.get_element_by_css_selector(element,
								scope));
					} else {
						list.addAll(engine.get_element_by_class_name(element,
								scope));
					}
				}
				break;
			case All:
				switch (scope) {

				case Descendant:
					for (Engine engine : selector.current) {
						list.addAll(engine.descendant());
					}
					break;
				case Child:
					for (Engine engine : selector.current) {
						list.addAll(engine.children());
					}
					break;
				case Next:
					for (Engine engine : selector.current) {
						list.add(engine.next());
					}
					break;
				case Siblings:
					for (Engine engine : selector.current) {
						list.addAll(engine.siblings());
					}
					break;
				case None:
					throw new RuntimeException("not supported");
				}
				throw new RuntimeException("not supported");
			}

		}
		return new Selector(list);
	}
}

