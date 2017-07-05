package cases;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeTest;


import org.testng.annotations.Test;

import page.AlertPage;
import util.DataHelper;

import com.zendaimoney.Dokodemo.ETestWebUIBaseCase;
import com.zendaimoney.Dokodemo.engine.Browser;
import com.zendaimoney.Dokodemo.util.BrowserUtil;

public class AlertTest extends ETestWebUIBaseCase {
	
//	@BeforeTest
	public void setUp(){
//		DataHelper.initFullAppData(this);
	}

	@Test(description="just a test!!!")
	public void test() throws Exception {
		Browser ie = Browser.start(DataHelper.getAppData("url", 1));
		AlertPage page = ie.cast(AlertPage.class);
		// page.alert.click();
		// sleep(2);
		// page.alert.deal_dialog("确定");

		page.prompt.click();
		// page.prompt.deal_prompt("hjjjgjjgj", true);
		BrowserUtil.close_all_ies();
	}

	// @Test
	public void testAAA() {
		String filename = "[失败]元素找不到:wd(搜索).click()操作，找不到元素".replaceAll("\\s", "_").replaceAll(":", "").replaceAll("\"", " ").replaceAll("\\.", " ") + ".png";
		filename = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "-" + filename;
		System.out.println(filename);
	}

	// @Test
	public void testAA() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("minghongjun", "joyce");
		System.out.println(map.get("minghongjun"));
		if (!(map.get("joyce") == null)) {
			System.out.println(map.get("joyce"));
		}
	}

//	@AfterMethod
	public void tearDown() throws Exception {
		BrowserUtil.close_all_ies();
	}
	
	@Test
	public void testHelpOthers(){
		WebDriver driver = new FirefoxDriver();
		driver.get("https://member.feiniu.com/order/orderList");
		driver.findElement(By.id("mememail")).sendKeys("t1@t1.com");;
		driver.findElement(By.id("mPwd")).sendKeys("123456");
		driver.findElement(By.id("btnSubmit")).click();
		driver.findElement(By.xpath("//a[@onci]"));
	}

}
