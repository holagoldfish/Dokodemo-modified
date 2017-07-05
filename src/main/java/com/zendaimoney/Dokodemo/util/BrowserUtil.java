package com.zendaimoney.Dokodemo.util;

import com.zendaimoney.Dokodemo.engine.Browser;
import com.zendaimoney.Dokodemo.logger.LogUtils;

public class BrowserUtil {

	public static void close_all_ies() throws Exception {
		String browserType = Browser.getBrowserType();
		Browser.closeAllBrowsers(browserType);
		LogUtils.setBrowserPassed(Browser.getBrowserType(), "close_all_ies", "");
	}

	public static void close_ies() throws Exception  {
		try {
			close_all_ies();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void kill_all_cookie() throws Exception {
		clear_cookie_and_cache();
	}

	public static void clear_cookie_and_cache() throws Exception {
		Browser.clear_cookie_and_cache();
		LogUtils.setBrowserPassed(Browser.getBrowserType(), "kill_all_cookie",
				"");
	}


}
