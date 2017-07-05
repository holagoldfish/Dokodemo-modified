package cases;

import org.junit.Test;

import page.ASindexPage;
import page.LoginPage;

import com.zendaimoney.Dokodemo.ETestWebUIBaseCase;
import com.zendaimoney.Dokodemo.engine.Browser;
import com.zendaimoney.Dokodemo.util.BrowserUtil;



public class ASTest extends ETestWebUIBaseCase{

	@Test
	public void test_iframe例子() throws Exception{
		Browser ie = Browser.start("http://192.16.220.209:7070/AS/index");
		LoginPage loginpage = ie.cast(page.LoginPage.class);
		loginpage.username.set("AA");
		loginpage.password.set("111");
		loginpage.dologin.click();
		
		ASindexPage asindex = ie.cast(page.ASindexPage.class);
		System.out.println(asindex.operation.size());
		System.out.println(asindex.operation.get(0).td);
//		System.out.println(asindex.operation.get(0).opers.size());
		asindex.operation.get(0).opers.get(1).click();
		BrowserUtil.close_all_ies();

	}
	
	@Test
	public void test_页面跳转() throws Exception{
		Browser ie = Browser.start("http://192.16.220.209:7070/AS/index");
		LoginPage loginpage = ie.cast(page.LoginPage.class);
		loginpage.username.set("AA");
		loginpage.password.set("111");
		loginpage.dologin.click();
		ie.go_to("http://wwww.baidu.com");
		
		
		BrowserUtil.close_all_ies();

	}
}
