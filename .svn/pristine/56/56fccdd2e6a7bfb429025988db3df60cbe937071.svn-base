package com.zendaimoney.Dokodemo.util;



import org.apache.commons.lang.StringUtils;

/**
 * 
 */
public class OsUtils {

    public static enum Os {
        WINDOWS, LINUX, OTHERS
    }

    public static Os getOs() {
        String osStr = StringUtils.trimToEmpty(System.getProperty("os.name")).toLowerCase();
        if (osStr.startsWith("win")) {
            return Os.WINDOWS;
        }

        if (osStr.contains("linux") || osStr.contains("solaris") || osStr.contains("unix")) {
            return Os.LINUX;
        }

        return Os.OTHERS;
    }
}
