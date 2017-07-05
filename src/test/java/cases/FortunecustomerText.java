package cases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import page.FortunePage;
import page.FortunecustomerPage;
import page.LoginPage;
import util.DBUtils;
import util.DataHelper;

import com.zendaimoney.Dokodemo.ETestWebUIBaseCase;
import com.zendaimoney.Dokodemo.engine.Browser;
import com.zendaimoney.Dokodemo.util.BrowserUtil;

public class FortunecustomerText extends ETestWebUIBaseCase{

	FortunecustomerPage page;
	Browser ie;

	@BeforeClass
	public void setUp() throws Exception {
		DataHelper.initFullAppData(this);
		 ie = Browser.start(DataHelper.getAppData("url", 1));
		page = ie.cast(FortunecustomerPage.class);
	}

	/*@AfterClass
	public void tearDown() throws Exception {
		BrowserUtil.close_all_ies();
	}*/
	
	@Test
	public void InputRightPwdTest() throws Exception {
	//Browser ie=Browser.start("http://172.16.230.205:8080/autofortune");
		//http://172.16.230.187:9105/uc-cas/login?service=http%3A%2F%2F172.16.230.205%3A8080%2Fautofortune%2Fj_spring_cas_security_check
			Browser ie=Browser.start("http://172.16.230.187:9105/uc-cas/login?service=http%3A%2F%2F172.16.230.205%3A8080%2Fautofortune%2Fj_spring_cas_security_check");
	LoginPage login=ie.cast(LoginPage.class);
	login.username.set("admin");
	login.password.set("123456");
	login.dologin.click();
	/*sleep(2);
  //  WebDriver driver=ie.driver;
	 ie.driver.switchTo().frame("leftFrame");
	 System.out.println(ie.driver.findElement(By.id("panle")).getTagName());
	 System.out.println(ie.driver.findElement(By.xpath("//html//body//div//dl//dt")).getText());
	 //ie.driver.findElement(By.xpath("//html//body//div//dl//dt")).click();----错误 ，需要用  js 来 触发 click();
	 //System.out.println(ie.driver.findElement(By.xpath("//*[@id='mynav1']")).getText());  ----xpath正确 
	 
	 JavascriptExecutor js = (JavascriptExecutor)ie.driver;
	 String jsStr = "$('#mynav1').click();"; //注意分号
	 js.executeScript(jsStr);  
	 

	 
	//ie.driver.findElement(By.xpath("//*[@id='mynav1']")).click();
	//*[@id="mynav1"]/a
    
    //ie.driver.findElement(By.id("mynav1")).click();
    
  //  /html/body/div/dl/dt
*/	
	
	}
	
	

/*	// 密码正确登录
	@Test
	public void InputRightPwdTest() throws Exception {
		page.username.set(DataHelper.getAppData("username", 3));
		page.password.set(DataHelper.getAppData("password", 3));
		page.submit.click();
		assertFalse(Browser.getDriver().getPageSource().contains("登录名或密码错误"));
		
	    ie.driver.get("http://172.16.230.205:8080/autofortune/fortune/invest_list?flowId=02000002");
		
		
		
		//ie.go_to("http://172.16.230.205:8080/autofortune/fortune/invest_list?flowId=02000002");
		
		//By by=By.xpath("//html//body//div//dl//dt");
		//WebElement el=ie.driver.switchTo().frame("leftFrame").findElement(by);	
		
		WebDriver driver=Browser.getDriver();		
		driver.switchTo().frame("leftFrame");
		By by=By.xpath("//html//body//div//dl//dt");
		driver.findElement(by);		
		
		
		sleep(5);		
		WebDriver drr = new ChromeDriver();
		drr.get("http://172.16.230.205:8080/autofortune/account/user/main");	
		drr.findElement(By.id("username")).sendKeys("admin");
		drr.findElement(By.id("password")).sendKeys("123456");
		drr.findElement(By.className("btn")).click();
		
		//drr.switchTo().frame("leftFrame");	
		drr.switchTo().frame(1);
		sleep(3);
		drr.switchTo().frame("mainFramestId");
		drr.findElement(By.id("mynav1")).click();
		//drr.findElement(By.id("topFrameset"));		
		//drr.switchTo().frame("frame");   
		
        
		
	}
	
	//检查客户列表总数是否正确
	@Test
	public void CheckCustomerTest() throws Exception {
		
		//ie.go_to("http://172.16.230.205:8080/autofortune/fortune/invest_list?flowId=02000002");
		FortunePage fortune = ie.cast(FortunePage.class);
		fortune.lists.get(1).choice.click();
		fortune.lists.get(3).choice.click();
		
		// Declare

       // WebDriver driver = new FirefoxDriver();

        // Load page

        //driver.get("http://172.16.230.205:8080/autofortune/account/user/main");        

        //Thread.sleep(15000);
        // Load subpage

       //driver.findElement(By.id("btnLoad")).click();

         

        // Back up main page's handler

        
		
		
		
		By by=By.xpath("//html//body//div//dl//dt");	
	    ie.driver.findElement(by).click();
	    
	    sleep(5);
	    
		By by2=By.xpath("//html//body//div//dl//dd//div//ul//li//a");	
		ie.driver.findElement(by2).click();
	    sleep(5);
	    
	   // /html/body/form/div[3]
	    
	   // By by3=By.xpath("//html//body//form//div[3]");			    
	    assertFalse(Browser.getDriver().getPageSource().contains("客户编号"));
	    
		//sleep(5);
	  //  ie.go_to("http://172.16.230.205:8080/autofortune/crm/list");
	  //  FortunePage fortune = ie.cast(FortunePage.class);
	    
	  
		
		
		//selenium.click("//div/table/tbody/tr[1]/td[1]/table/tbody/tr[1]/td[1]");
		
		//element.getText();
		
		By by=By.xpath("//html//body//div//dl//dt");	
	    WebElement el=ie.driver.findElement(by);
	    System.out.println("ce: "+el.getText());
		
		
		
		
	    
	   		
		String sql="select * from crm_mv_crm_customer t where t.status=1";
		
		Connection conn = DBUtils.getConn();
		Statement statement = DBUtils.getStatement(conn);
		ResultSet resultSet = DBUtils.getResultSet(statement, sql);
		ResultSetMetaData rsm = resultSet.getMetaData(); //
		int col = rsm.getColumnCount(); //
		
		page.table.exist();
		
		
		page.username.set(DataHelper.getAppData("username", 1));
		page.password.set(DataHelper.getAppData("password", 1));
		page.submit.click();
		assertFalse(Browser.getDriver().getPageSource().contains("登录名或密码错误"));
		
		
		
	}
	*/
	
	
	
}
