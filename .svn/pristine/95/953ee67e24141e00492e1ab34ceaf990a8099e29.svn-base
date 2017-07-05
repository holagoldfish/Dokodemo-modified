package com.zendaimoney.Dokodemo.engine;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;


/**
 * IE浏览器相关操作
 */
public class IEBrowser extends BaseBrowser {
	public IEBrowser() {
		System.setProperty("webdriver.ie.driver", getDriverPath("ie"));
		DesiredCapabilities capabilities = DesiredCapabilities
				.internetExplorer();
		capabilities
				.setCapability(
						InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
		capabilities.setCapability("ignoreZoomSetting", true);
		capabilities.setCapability("enableElementCacheCleanup", true);
		capabilities.setCapability("unexpectedAlertBehaviour", "ignore");
//		driver = new InternetExplorerDriver(capabilities);
		driver = new EventFiringWebDriver(new InternetExplorerDriver(capabilities)).register(eventListener);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Override
	public String version(String browserType) {
		return super.version("MSIE");
	}

}