package com.zendaimoney.Dokodemo.listener;

import org.testng.IInvokedMethod;
import org.testng.ITestNGListener;
import org.testng.ITestResult;

public interface IInvokedMethodListener extends ITestNGListener {

	void beforeInvocation(IInvokedMethod method, ITestResult testResult);

	void afterInvocation(IInvokedMethod method, ITestResult testResult);

}
