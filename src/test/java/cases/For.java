package cases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import page.FortunecustomerPage;
import page.LoginPage;
import util.DataHelper;

import com.zendaimoney.Dokodemo.ETestWebUIBaseCase;
import com.zendaimoney.Dokodemo.engine.Browser;

public class For extends ETestWebUIBaseCase{

	/*@AfterClass
	public void tearDown() throws Exception {
		BrowserUtil.close_all_ies();
	}*/
	
	@Test
	public void InputRightPwdTest() throws Exception {
		Browser ie=Browser.start("http://172.16.230.205:8080/autofortune");
		LoginPage login=ie.cast(LoginPage.class);
		login.username.set("admin");
		login.password.set("123456");
		login.dologin.click();
		sleep(3);
		
		
		ie.driver.switchTo().frame("leftFrame");
		 System.out.println(ie.driver.findElement(By.id("panle")).getTagName());
		 System.out.println(ie.driver.findElement(By.xpath("//html//body//div//dl//dt")).getText());
		 //ie.driver.findElement(By.xpath("//html//body//div//dl//dt")).click();----错误 ，需要用  js 来 触发 click();
		 //System.out.println(ie.driver.findElement(By.xpath("//*[@id='mynav1']")).getText());  ----xpath正确 
		 
		 JavascriptExecutor js = (JavascriptExecutor)ie.driver;
		 String jsStr = "$('#mynav1').click();"; //注意分号
		 js.executeScript(jsStr);  
	}
	
}
