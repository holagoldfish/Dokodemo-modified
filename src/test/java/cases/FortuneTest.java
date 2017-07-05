package cases;



import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import page.FortunePage;
import page.LoginPage;

import com.zendaimoney.Dokodemo.ETestWebUIBaseCase;
import com.zendaimoney.Dokodemo.engine.Browser;
import com.zendaimoney.Dokodemo.util.BrowserUtil;




public class FortuneTest extends ETestWebUIBaseCase{
	@BeforeClass
	public static void SetUp() throws Exception{
		BrowserUtil.close_all_ies();
		
	}
//	
//	@AfterClass
//	public static void TearDown() throws InterruptedException{
//		BrowserUtil.close_all_ies();
//		
//	}
	
	
/*	@Test
	public void test_demo1() throws Exception {
		//WebDriver driver = new FirefoxDriver();
		//WebDriver driver = new ChromeDriver();
		//driver.findElement(By.xpath("//a[@href='']"));
		
		Browser ie=Browser.start("http://172.16.230.212:8080/carfortune");
		LoginPage login=ie.cast(LoginPage.class);
		login.username.set("hyfortune77");
		login.password.set("123456");
		login.dologin.click();
		sleep(2);
		ie.go_to("http://172.16.230.212:8080/carfortune/fortune/invest_list?flowId=02000002");
		FortunePage fortune = ie.cast(FortunePage.class);
//		fortune.business.click();
//		fortune.invest.click();
		
//		fortune.frame.lists.get(1).choice.click();
//		fortune.frame.lists.get(5).choice.click();
//		System.out.println(fortune.lists.exist());
		fortune.lists.get(1).choice.click();
		fortune.lists.get(3).choice.click();

	}
	*/
	
	
	@Test
	public void test_demo1() throws Exception {
		
		Browser ie=Browser.start("http://172.16.230.205:8080/autofortune");
		WebDriver drr =ie.driver;
	LoginPage login=ie.cast(LoginPage.class);
		login.username.set("admin");
		login.password.set("123456");
		login.dologin.click();
		
	  /*  WebDriver drr = new ChromeDriver();
		drr.get("http://172.16.230.205:8080/autofortune/account/user/main");
		drr.findElement(By.id("username")).sendKeys("admin");
		drr.findElement(By.id("password")).sendKeys("123456");
		drr.findElement(By.className("btn")).click();
		
		drr.get("http://172.16.230.205:8080/autofortune/fortune/invest_list?flowId=02000002");*/
		//sleep(2);
		//ie.go_to("http://172.16.230.205:8080/autofortune/fortune/invest_list?flowId=02000002");
		//FortunePage fortune = ie.cast(FortunePage.class);
		//fortune.lists.get(1).choice.click();
		//fortune.lists.get(3).choice.click();

	}
	
	
	
}
