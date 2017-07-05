package cases;

import org.junit.Test;

import page.BaiduPage;

import com.zendaimoney.Dokodemo.ETestWebUIBaseCase;
import com.zendaimoney.Dokodemo.engine.Browser;
import com.zendaimoney.Dokodemo.util.BrowserUtil;




public class AppTest extends ETestWebUIBaseCase{
	
	
//	@BeforeClass
//	public static void before() throws InterruptedException{
//		Browser ie = Browser.start("http://www.baidu.com");
//		BrowserUtil.close_all_ies();
//		
//	}
//	@AfterClass
//	public static void after() throws InterruptedException{
//		BrowserUtil.close_all_ies();
//		
//	}
	
	@Test
	public void test() throws Exception{
		
		Browser ie = Browser.start("http://www.baidu.com");
		BaiduPage page = ie.cast(page.BaiduPage.class);
		page.input.set("hangzhou杭州");
		page.search.click();
		System.out.println(ie.getDriver().getTitle());
		BrowserUtil.close_all_ies();

	}
	
	
 
	@Test
	public void test1() throws Exception{
		
		Browser ie = Browser.start("http://www.baidu.com");
		BaiduPage page = ie.cast(page.BaiduPage.class);
		BrowserUtil.close_all_ies();

	}
	
	@Test
	public void test2() throws Exception{
		
		Browser ie = Browser.start("http://www.baidu.com");
		BaiduPage page = ie.cast(page.BaiduPage.class);
		page.input.set("侯佳刚");
		page.search.click();
		BrowserUtil.close_all_ies();

	}
}
