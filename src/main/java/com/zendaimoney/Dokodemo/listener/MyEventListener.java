package com.zendaimoney.Dokodemo.listener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.Reporter;
import org.uncommons.reportng.Comm;

public class MyEventListener implements WebDriverEventListener {
	public void onException(Throwable ex, WebDriver driver) {
		String filename = generateRandomFilename(ex);
		takeScreenShot(driver, filename);
	}

	private String generateRandomFilename(Throwable ex) {
		String filename = ex.getMessage();
		int i = filename.indexOf('\n');
		filename = filename.substring(0, i).replaceAll("\\s", "_").replaceAll(":", "").replace("\"", " ") + ".png";
		filename = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "-" + filename;
		return filename;
	}

	public void takeScreenShot(WebDriver driver, String filename) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destFileName = "test-output\\html\\screenshot\\" + filename;
		try {
			File f2 = new File(destFileName);
			FileUtils.copyFile(scrFile, f2);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Comm.testNameInvokeImg.put(Reporter.getCurrentTestResult().getName(), "screenshot\\" + filename);
	}

	@Override
	public void afterClickOn(WebElement arg0, WebDriver arg1) {
	}

	@Override
	public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
	}

	@Override
	public void afterNavigateBack(WebDriver arg0) {
	}

	@Override
	public void afterNavigateForward(WebDriver arg0) {
	}

	@Override
	public void afterNavigateTo(String arg0, WebDriver arg1) {
	}

	@Override
	public void afterScript(String arg0, WebDriver arg1) {
	}

	@Override
	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1) {
	}

	@Override
	public void beforeClickOn(WebElement arg0, WebDriver arg1) {
	}

	@Override
	public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {
	}

	@Override
	public void beforeNavigateBack(WebDriver arg0) {
	}

	@Override
	public void beforeNavigateForward(WebDriver arg0) {
	}

	@Override
	public void beforeNavigateTo(String arg0, WebDriver arg1) {
	}

	@Override
	public void beforeScript(String arg0, WebDriver arg1) {
	}

	@Override
	public void afterChangeValueOf(WebElement arg0, WebDriver arg1) {
	}
}