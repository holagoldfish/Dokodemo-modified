package cases;

import org.junit.Test;

import page.IFramePage;

import com.zendaimoney.Dokodemo.ETestWebUIBaseCase;
import com.zendaimoney.Dokodemo.engine.Browser;
//import com.zendaimoney.Dokodemo.page.IFramePage.Iframemini;
import com.zendaimoney.Dokodemo.util.BrowserUtil;

public class IframeTest extends ETestWebUIBaseCase {

	@Test
	public void test() throws Exception {
		BrowserUtil.close_all_ies();
		Browser ie = Browser.start("E:\\main.html");
		IFramePage page =ie.cast(IFramePage.class);
		page.frame.lists.get(0).input1.set("1-1");
		page.frame.lists.get(0).input2.set("1-2");
		
//		page.frame.lists.get(1)
		page.frame.lists.get(1).input1.set("2-1");
		page.frame.lists.get(1).input2.set("2-2");
		
		
		System.out.println(page.frame.lists.get(0).input1.value());
		System.out.println(page.frame.lists.get(0).input2.value());
//		page.k.select();
//		page.k.iin.set("hjg");
		

		
	}

}