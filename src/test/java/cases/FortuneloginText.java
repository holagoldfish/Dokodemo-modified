package cases;

import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import page.FortuneloginPage;
import page.UCLoginPage;
import util.DataHelper;

import com.zendaimoney.Dokodemo.ETestWebUIBaseCase;
import com.zendaimoney.Dokodemo.engine.Browser;
import com.zendaimoney.Dokodemo.util.BrowserUtil;

public class FortuneloginText extends ETestWebUIBaseCase{

	FortuneloginPage page;

	@BeforeMethod
	public void setUp() throws Exception {
		DataHelper.initFullAppData(this);
		Browser ie = Browser.start(DataHelper.getAppData("url", 1));
		page = ie.cast(FortuneloginPage.class);
	}

/*	@AfterMethod
	public void tearDown() throws Exception {
		BrowserUtil.close_all_ies();
	}
	
	// 密码为空登录
	@Test
	public void InputNullPwdTest() throws Exception {
		page.submit.click();
		assertTrue(Browser.getDriver().getPageSource().contains("请输入您的登录名和密码"));
	}

	// 密码错误登录
	@Test
	public void InputWrongPwdTest() throws Exception {
		System.out.println(Reporter.getCurrentTestResult().getName());
		page.username.set(DataHelper.getAppData("username", 2));
		page.password.set(DataHelper.getAppData("password", 2));
		page.submit.click();
		assertTrue(Browser.getDriver().getPageSource().contains("登录名或密码错误"));
	}*/

	// 密码正确登录
	@Test
	public void InputRightPwdTest() throws Exception {
		page.username.set(DataHelper.getAppData("username", 3));
		page.password.set(DataHelper.getAppData("password", 3));
		page.submit.click();
		assertFalse(Browser.getDriver().getPageSource().contains("登录名或密码错误"));
	}
}
