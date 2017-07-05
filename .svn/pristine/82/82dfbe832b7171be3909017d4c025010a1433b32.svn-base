package com.zendaimoney.Dokodemo.engine;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  对selector进行处理
 */
public class Tokenizer {

	private int position;
	private String selector;

	private static String Element_Str = "([#.]?)((?:[\\w_-]|\\\\.)+|\\*)";
	private static String Element_Mixed_Str = "(([#.]?)((?:[\\w_-]|\\\\.)+|\\*))+";
	private static String Attribute_Str = "\\[([\\w_-]+)(.*?=)?((?:[^\\\\\\]]|\\\\.)+)?\\]";
	private static String Filter_Str = ":([\\w_-]+)(?:\\(((?:[^\\\\\\)]|\\\\.)+)?\\))?";
	private static String Hierachy_Str = "\\s*[,>+~ ]\\s*";
	public static Pattern Element_Regex = Pattern.compile(Element_Str);
	public static Pattern Attribute_Regex = Pattern.compile(Attribute_Str);
	public static Pattern Filter_Regex = Pattern.compile(Filter_Str);
	public static Pattern Hierachy_Regex = Pattern.compile(Hierachy_Str);
	public static Pattern RE = joinRegex(Element_Str, Attribute_Str,
			Filter_Str, Hierachy_Str);
	public static Pattern RE_Mixed = joinRegex(Element_Mixed_Str,
			Attribute_Str, Filter_Str, Hierachy_Str);

	private static Pattern joinRegex(String... strArr) {
		if (null == strArr || strArr.length < 2) {// 修改了可能出现的空指针异常
			throw new RuntimeException("拼接参数个数必须大于2");
		}
		StringBuilder sb = new StringBuilder(strArr[0]);
		for (int i = 1; i < strArr.length; i++) {
			sb.append("|").append(strArr[i]);
		}
		return Pattern.compile(sb.toString());
	}

	public Tokenizer(String selector) {
		this.selector = selector;
		this.position = 0;
	}

	private String getNextWithRegex(Pattern pattern) {
		if (position < selector.length()) {
			String str = getRemainSelector();
			// 如果有"~"，直接返回，不做处理
			if (str.contains("~")) {
				position += selector.length();
				return str;
			}
			Matcher matcher = pattern.matcher(str);
			if (matcher.find()) {
				String matched = matcher.group();
				position += matched.length();
				String trim = matched.trim();
				if (trim.isEmpty()) {
					return " ";
				} else {
					return trim;
				}
			}
		}
		return null;
	}

	public String getNext() {
		return getNextWithRegex(RE);
	}

	// 把所有element组合在一起
	// 可能出现
	// .class
	// #id
	// Element
	private String getNextWithMixedElement() {
		return getNextWithRegex(RE_Mixed);
	}

	private String getRemainSelector() {
		return selector.substring(position, selector.length());
	}

	private static ArrayList<String> orderElement(String selector) {
		ArrayList<String> arr = new ArrayList<String>();
		if (selector == null || selector.isEmpty()) {
			return arr;
		}
		Tokenizer t = new Tokenizer(selector);
		String next = null;
		while ((next = t.getNext()) != null) {
			if (next.startsWith(".") || next.startsWith("#")) {// 插前面
				arr.add(0, next);
			} else {// 插后面
				arr.add(next);
			}
		}
		return arr;
	}

	private ArrayList<String> remaining = new ArrayList<String>();

	// 另一种换过顺序的next，按照先id和class，后tag来换顺序
	public String getNextOrderByElement() {
		if (remaining == null || remaining.isEmpty()) {
			String mixed = getNextWithMixedElement();
			remaining = orderElement(mixed);
		}
		if (remaining == null || remaining.isEmpty()) {
			return null;
		} else {
			return remaining.remove(0);
		}
	}
}
