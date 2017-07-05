package com.zendaimoney.Dokodemo.engine;

import org.apache.commons.lang.StringUtils;

import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.WinReg;
import com.zendaimoney.Dokodemo.util.FileUtils;
import com.zendaimoney.Dokodemo.util.OsUtils;


/**
 * 
 */
public class DriverInfoFactory {

    public static final String FIREFOX_REGISTRY_KEY = "SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\App Paths\\firefox.exe";

    public static String createPath(BrowserType bt) {
        switch (OsUtils.getOs()) {
        case WINDOWS:
            return getWindowsPath(bt);
        default:
            break;
        }

        return getLinuxPath(bt);
    }

    public static String getWindowsPath(BrowserType bt) {
        String path = "";
        switch (bt) {
        case chrome:
            path = FileUtils.getFilePath("/chromedriver.exe");
            if (path == null) {
                path = "c:\\chromedriver.exe";
            }
            break;
        case ff:
            path = getFirefoxWindowsPath();
            break;
        case ie:
            path = iedriverpath();
        default:
            break;
        }

        return path;
    }

    public static String getLinuxPath(BrowserType bt) {
        String path = "";
        switch (bt) {
        case chrome:
            path = FileUtils.getFilePath("/chromedriver");
            if (StringUtils.isBlank(path)) {
                path = "~/chromedriver";
            }
            break;
        case ie:
            throw new RuntimeException("The os is not support "
                    + BrowserType.ie + "!!!");
        default:
            break;
        }

        return path;
    }

    private static String getFirefoxWindowsPath() {
        String path = null;
        try {
            path = Advapi32Util.registryGetStringValue(
                    WinReg.HKEY_LOCAL_MACHINE, FIREFOX_REGISTRY_KEY, "Path");
        } catch (Throwable e) {
        }
        if (StringUtils.isNotBlank(path)) {
            path += "\\firefox.exe";
        }
        return path;
    }

    private static String iedriverpath() {
        String iedriverpath = FileUtils.getFilePath("/IEDriverServer.exe");
        if (iedriverpath == null) {
            iedriverpath = "c:\\IEDriverServer.exe";
        }
        return iedriverpath;
    }
}
