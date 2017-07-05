package com.zendaimoney.Dokodemo.html;

import com.zendaimoney.Dokodemo.engine.Browser;
import com.zendaimoney.Dokodemo.engine.Engine;
import com.zendaimoney.Dokodemo.logger.LogUtils;

/**
 * 用于SearchArrayList的初始化，统一Element和PageModel
 */
public class Locator {
	protected String selector;
	protected PageModel parent;
	protected String name;
	protected String description;
	protected Engine engine; // 非空时，代表这个Element已经被找到

	LogUtils logutils = new LogUtils();

	public Locator(String selector, PageModel parent, String name, String description) {
		this.selector = selector;
		this.parent = parent;
		this.name = name;
		this.description = description;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public Engine getEngine() {
		return engine;
	}

	/**
	 * 从父亲节点的engine得到自己的engine.刚开始的时候，父亲节点只有engine是有值的，selector和parent都是nil
	 */
	protected void locate() {
		if (this.engine == null) {// 如果没有engine，说明还没有被定位过了，否则不需要处理。
			if (parent != null) {
				parent.locate();
				try {
					this.engine = parent.getEngine().findBySelector(selector, name, description);
				} catch (Exception e) {
					// 于凡修改了，实现原来同样的功能,精简代码
					System.out.println("This is the second time to find element~");
					parent.setEngine(Browser.getBaseBrowser().getRootNode());// 重新定位父亲为根
					this.engine = parent.getEngine().findBySelector(selector, name, description);
				}
			} else {
				throw new UnsupportedOperationException("当engine不存在时，必须要有parent");
			}
		}
	}

	protected boolean isEmpty(String... message) throws Exception {
		if (null == message || message.length == 0) {
			message = new String[] { Thread.currentThread().getStackTrace()[2].getMethodName() + "()" };
		}
		if (null == engine) {
			// logutils.setElementEmptyScreenShot(Browser.getUrl(), message[0],
			// Engine.getActionInfo(engine),Browser.getWebDriver());
			LogUtils.setElementEmpty(Browser.getUrl(), message[0], Engine.getActionInfo(engine));
			return true;
		}
		// Engine.clearActionInfo();
		return false;
	}

	protected void logElementPassed(String... message) {
		if (null == message || message.length == 0) {
			message = new String[] { Thread.currentThread().getStackTrace()[2].getMethodName() + "()" };
		}
		LogUtils.setElementPassed(Browser.getUrl(), message[0], Engine.getActionInfo(engine));
	}

	protected void logElementFailed(Exception ex, String... message) throws Exception {
		if (null == message || message.length == 0) {
			message = new String[] { Thread.currentThread().getStackTrace()[2].getMethodName() + "()" };
		}
		LogUtils.setElementFailed(Browser.getUrl(), message[0], Engine.getActionInfo(engine), ex.getMessage());
	}

	public boolean empty(String... message) throws Exception {
		if (null == message || message.length == 0) {
			message = new String[] { Thread.currentThread().getStackTrace()[2].getMethodName() + "()" };
		}
		try {
			locate();
		} catch (Exception ex) {
			logElementFailed(ex, message[0]);
			return true;
		}
		// Engine.clearActionInfo();
		return isEmpty(message);
	}

	public boolean isExist(String... message) throws Exception {
		if (null == message || message.length == 0) {
			message = new String[] { Thread.currentThread().getStackTrace()[2].getMethodName() + "()" };
		}
		// Engine.clearActionInfo();
		return !empty(message);
	}

}
