package cases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import page.FortunePage;
import page.FortunecustomerPage;
import page.LoginPage;
import util.DBUtils;

import com.zendaimoney.Dokodemo.ETestWebUIBaseCase;
import com.zendaimoney.Dokodemo.engine.Browser;
import com.zendaimoney.Dokodemo.engine.Engine;
import com.zendaimoney.Dokodemo.html.Locator;
import com.zendaimoney.Dokodemo.util.BrowserUtil;


/*
 * 作者：胡莹
 */
public class ForCustomerTest extends ETestWebUIBaseCase{
	@BeforeClass
	public static void SetUp() throws Exception{
		BrowserUtil.close_all_ies();		
	}
		
	
	@Test
	public void test_demo2() throws Exception {		
		Browser ie=Browser.start("http://172.16.230.205:8080/autofortune");
		WebDriver drr =ie.driver;
	    LoginPage login=ie.cast(LoginPage.class);
		login.username.set("admin");
		login.password.set("123456");
		login.dologin.click();
		sleep(4);
		
		//-------------转到左侧菜单 leftFrame--------------
		   WebElement frame=ie.driver.findElement(By.name("leftFrame")); 
		  ie.driver.switchTo().frame(frame);
		   // System.out.println(ie.driver.findElement(By.id("panle")).getTagName());
		    
		  //-------------js 点击客户列表菜单--------------
		    JavascriptExecutor js = (JavascriptExecutor)ie.driver;
		    String jsStr = "$('#mynav1').click();"; //注意分号
		    js.executeScript(jsStr); 
		   
		   //-------------转到默认mainFrame--------------
		    ie.driver.switchTo().defaultContent();  
		   frame=ie.driver.findElement(By.name("mainFrame"));  
		   ie.driver.switchTo().frame(frame);     
		  // System.out.println(ie.driver.findElement(By.xpath("//html//body//table")).getTagName());
		    
		  //-------------转到默认ifmeRight--------------
		    frame=ie.driver.findElement(By.name("ifmeRight"));  
		   ie.driver.switchTo().frame(frame);  
		   sleep(3);
		    
		  //-------------客户列表 table--------------
		    WebElement tableElement=ie.driver.findElement(By.xpath("//table[@class='ggkj_table']"));  
		    WebElement totalRowElement=ie.driver.findElement(By.xpath("//a[@class='ggkj_pageDowna']"));
		    totalRowElement.click();
		    sleep(3);
		    
		   WebElement newtableElement=ie.driver.findElement(By.xpath("//table[@class='ggkj_table']")); 
		    List<WebElement> rowsElementLists =newtableElement.findElements(By.tagName("tr"));
		    String YM_result=getCell(rowsElementLists.get(rowsElementLists.size()-1),0).getText();
		     
		   String sql="select * from crm_mv_crm_customer t where t.cr_state=1";
		    
		   Connection conn = DBUtils.getConn();
		   Statement statement = DBUtils.getStatement(conn);
		   ResultSet resultSet = DBUtils.getResultSet(statement, sql);
		   ResultSetMetaData rsm = resultSet.getMetaData(); //
		       
		   int count = 0;      
		  try {          
		   while(resultSet.next()){              
		    count = count + 1;           
		    }       
		   } catch(SQLException e1) {      
		     // TODO Auto-generated catch block       
		     e1.printStackTrace();      
		   }
		   System.out.println(YM_result+"    "+count);
		   Assert.assertEquals(count,Integer.parseInt(YM_result));
		   
		   
		   //--------------查询 客户编号是否存在---------------
		    WebElement we=ie.driver.findElement(By.xpath("//input[@name='code' and @class='ggkj_inputtext_110']"));
		    we.sendKeys("F05121304080004");
		    WebElement we1=ie.driver.findElement(By.xpath("//input[@name='Input' and @class='chaxun_btn']"));
		    we1.submit(); 
		    
		   WebElement we2=ie.driver.findElement(By.xpath("//table[@class='ggkj_table']")); 
		    if(we2.findElement(By.xpath("//td[contains(text(),'F05121304080004')]"))!=null)
		    {
		     WebElement we3=we2.findElement(By.xpath("//td[contains(text(),'F05121304080004')]"));
		     System.out.println("查询结果：：：：：");
		     System.out.println(we2.getText());
		    }
		    else
		    {
		     System.out.println("查询结果 没有记录");
		    }
		    
	}
	
	
	 private WebElement getCell(WebElement Row,int cell){  
		 List<WebElement> cells;  
		 WebElement target = null;  
		 //列里面有"<th>"、"<td>"两种标签，所以分开处理。  
		 if(Row.findElements(By.tagName("th")).size()>0){  
		  cells = Row.findElements(By.tagName("th"));  
		  target = cells.get(cell);  
		 }  
		 if(Row.findElements(By.tagName("td")).size()>0){  
		 cells = Row.findElements(By.tagName("td"));  
		 target = cells.get(cell);  
		  }  
		 return target;  
	       
}  

	
	
	
	
	
	/*@Test
	 * public void test_demo2() throws Exception {
		
		Browser ie=Browser.start("http://172.16.230.205:8080/autofortune");
		WebDriver drr =ie.driver;
	    LoginPage login=ie.cast(LoginPage.class);
		login.username.set("admin");
		login.password.set("123456");
		login.dologin.click();
		sleep(4);
		WebElement frame=ie.driver.findElement(By.name("leftFrame")); 
		ie.driver.switchTo().frame(frame);
		 System.out.println(ie.driver.findElement(By.id("panle")).getTagName());
		 
		 JavascriptExecutor js = (JavascriptExecutor)ie.driver;
		 String jsStr = "$('#mynav1').click();"; //注意分号
		 js.executeScript(jsStr); 
		 
		 ie.driver.switchTo().defaultContent();  
		 frame=ie.driver.findElement(By.name("mainFrame"));  
		 ie.driver.switchTo().frame(frame);  		 
		 System.out.println(ie.driver.findElement(By.xpath("//html//body//table")).getTagName()); 
		 
		 frame=ie.driver.findElement(By.name("ifmeRight"));  
		 ie.driver.switchTo().frame(frame);  
		 sleep(1);
		 WebElement we=ie.driver.findElement(By.xpath("//input[@name='code' and @class='ggkj_inputtext_110']"));
		 we.sendKeys("F05121304080004");
		 WebElement we1=ie.driver.findElement(By.xpath("//input[@name='Input' and @class='chaxun_btn']"));
		 we1.submit();
		 WebElement we2=ie.driver.findElement(By.xpath("//table[@class='ggkj_table']"));
		 System.out.println(we2.getTagName());
		 List<WebElement> rows = we2.findElements(By.tagName("tr")); 
		 System.out.println(rows.size());
		 
		 if(we2.findElement(By.xpath("//td[contains(text(),'F05121304080004')]"))!=null)
		 {
			 WebElement we3=we2.findElement(By.xpath("//td[contains(text(),'F05121304080004')]"));
			 System.out.println("查询结果：：：：："+we3.getTagName());
		 }	
	}
	*/
	

	
	

	/*  @Test
	 * public void test_demo1() throws Exception {	
		
		Browser ie=Browser.start("http://172.16.230.205:8080/autofortune");
		WebDriver drr =ie.driver;
	    LoginPage login=ie.cast(LoginPage.class);
		login.username.set("admin");
		login.password.set("123456");
		login.dologin.click();
		sleep(4);
		WebElement frame=ie.driver.findElement(By.name("leftFrame")); 
		ie.driver.switchTo().frame(frame);
		 System.out.println(ie.driver.findElement(By.id("panle")).getTagName());
		 
		 JavascriptExecutor js = (JavascriptExecutor)ie.driver;
		 String jsStr = "$('#mynav1').click();"; //注意分号
		 js.executeScript(jsStr); 
		 
		 ie.driver.switchTo().defaultContent();  
		 frame=ie.driver.findElement(By.name("mainFrame"));  
		 ie.driver.switchTo().frame(frame);  		 
		 System.out.println(ie.driver.findElement(By.xpath("//html//body//table")).getTagName()); 
		 
		 frame=ie.driver.findElement(By.name("ifmeRight"));  
		 ie.driver.switchTo().frame(frame);  
		 sleep(1);
		 WebElement we=ie.driver.findElement(By.xpath("//input[@name='code' and @class='ggkj_inputtext_110']"));
		 we.sendKeys("F05121304080004");
		 WebElement we1=ie.driver.findElement(By.xpath("//input[@name='Input' and @class='chaxun_btn']"));
		 we1.submit();
		 WebElement we2=ie.driver.findElement(By.xpath("//table[@class='ggkj_table']"));
		 System.out.println(we2.getTagName());
		 List<WebElement> rows = we2.findElements(By.tagName("tr")); 
		 System.out.println(rows.size());
		 
		 if(we2.findElement(By.xpath("//td[contains(text(),'F05121304080004')]"))!=null)
		 {
			 WebElement we3=we2.findElement(By.xpath("//td[contains(text(),'F05121304080004')]"));
			 System.out.println("查询结果：：：：："+we3.getTagName());
		 }
		 
		 
		 
		 
		 
		
		 //System.out.println(ie.driver.findElement(By.xpath("//*[@id='mainForm']")).getTagName());
		 //System.out.println(ie.driver.findElement(By.xpath("//html//body//form//div[2]//table//tbody//tr//td//input']")).getTagName());
		// ie.driver.findElement(By.xpath("//html//body//form//div[2]//table//tbody//tr//td//input']")).sendKeys("nihao");
		//ie.driver.findElement(By.name("code")).sendKeys("nihao");
		 
		// ie.driver.findElement(By.xpath("//*[@id='mainForm']//div[2]']"));
		// WebElement men=ie.driver.findElement(By.xpath("//*[@id='mainForm']"));
		 
		 WebElement we=ie.driver.findElement(By.xpath("//input[@name='code' and @class='ggkj_inputtext_110']"));
		 System.out.println("::::::::::::::::::::::::::::::" + we.getTagName());
		 we.sendKeys("haha");
		 
		 WebElement we=ie.driver.findElement(By.xpath("//input[@class='ggkj_inputtext_110']"));
		 System.out.println("::::::::::::::::::::::::::::::" + we.getTagName());
		 we.sendKeys("f");
		 
		 //driver.findElement(By.xpath("//input[@name='pass'and@class=’ textfild’]")).
		 
		 
		// ie.driver.findElement(By.xpath("//*[@id='mainForm']//div[2]//table//tbody//tr//td//input']")).sendKeys("nihao");
		                                  
		// System.out.println("::::::::::::::::::::::::::::::" + ie.driver.findElement(By.name("code")).getText());
		// WebElement we=ie.driver.findElement(By.id("fn"));
		 
		 //js.executeScript("(function(){document.getElement})()"); 
		 
		ie.driver.switchTo().frame("leftFrame");
		 JavascriptExecutor js = (JavascriptExecutor)ie.driver;
		 String jsStr = "$('#mynav1').click();"; //注意分号
		 js.executeScript(jsStr);  
		
	    WebDriver drr = new ChromeDriver();
		drr.get("http://172.16.230.205:8080/autofortune/account/user/main");
		drr.findElement(By.id("username")).sendKeys("admin");
		drr.findElement(By.id("password")).sendKeys("123456");
		drr.findElement(By.className("btn")).click();
		
		//drr.get("http://172.16.230.205:8080/autofortune/fortune/invest_list?flowId=02000002");
		//sleep(2);
		//ie.go_to("http://172.16.230.205:8080/autofortune/fortune/invest_list?flowId=02000002");
		//FortunePage fortune = ie.cast(FortunePage.class);
		//fortune.lists.get(1).choice.click();
		//fortune.lists.get(3).choice.click();

	}
	*/
	
	
}
