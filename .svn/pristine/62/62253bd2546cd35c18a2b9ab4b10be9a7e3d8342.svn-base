package com.zendaimoney.Dokodemo.engine;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Chrome浏览器相关操作
 */
public class ChromeBrowser extends BaseBrowser {
	
	public ChromeBrowser(String userAgent) {   
		String chromedriverpath = getDriverPath("chrome");
		System.setProperty("webdriver.chrome.driver", chromedriverpath);
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability("chrome.switches", Arrays.asList(
				"--user-data-dir=default", "--start-maximized"));
		if (userAgent == "iphone") {
			capabilities
					.setCapability(
							"chrome.switches",
							Arrays
									.asList(
											"--user-agent=Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_0_2 like Mac OS X; en-us) AppleWebKit/532.9 (KHTML, like Gecko) Version/4.0.5 Mobile/8A400 Safari/6531.22.7",
											"--user-data-dir=default",
											"--start-maximized"));
		}
//		driver = new ChromeDriver(capabilities);
		driver = new EventFiringWebDriver(new ChromeDriver(capabilities)).register(eventListener);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Override
	public void close() {//关闭单个窗口
		driver.close();
		int index = 0;
		Browser.browsers.remove(index);
	}

	@Override
	public String version(String browserType) {
		return super.version("Chrome");
	}

}
