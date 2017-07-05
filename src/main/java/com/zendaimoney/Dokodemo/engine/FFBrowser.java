package com.zendaimoney.Dokodemo.engine;



import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.WinReg;
/**
 * Firefox浏览器相关操作
 */
public class FFBrowser extends BaseBrowser {
    public static final String FIREFOX_REGISTRY_KEY = "SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\App Paths\\firefox.exe";

    public FFBrowser(String userAgent) {
        String path = getDriverPath("ff");
        if (StringUtils.isNotBlank(path)) {
            System.setProperty("webdriver.firefox.bin", path);
        }
        ProfilesIni defaultProfiles = new ProfilesIni();
        FirefoxProfile profile = defaultProfiles.getProfile("default");
        if (userAgent == "iphone") {
            profile.setPreference(
                    "general.useragent.override",
                    "Mozilla/5.0 (iPhone; U; CPU iPhone OS 3_0 like Mac OS X; en-us) AppleWebKit/528.18 (KHTML, like Gecko) Version/4.0 Mobile/7A341 Safari/528.16");
        }
//        driver = new FirefoxDriver(profile);
        driver = new EventFiringWebDriver(new FirefoxDriver(profile)).register(eventListener);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
    }

    @Override
    public String version(String browserType) {
        return super.version("Firefox");
    }
}
