package page;

import org.openqa.selenium.By;

import com.zendaimoney.Dokodemo.html.Button;
import com.zendaimoney.Dokodemo.html.PageModel;
import com.zendaimoney.Dokodemo.html.Table;
import com.zendaimoney.Dokodemo.html.TextInput;

public class FortunecustomerPage extends PageModel {

	public FortunecustomerPage(String selector, PageModel parent, String name, String description) {
		super(selector, parent, name, description);
	}

	public TextInput username = new TextInput("input[id=username]", this, "username", "登录名");
	public TextInput password = new TextInput("input[id=password]", this, "password", "密码");
	public Button submit = new Button("a[class=btn]", this, "submit", "登录");
	
	
	public TextInput code = new TextInput("input[name=code][class=ggkj_inputtext_110]", this, "code", "客户编号");		
	public TextInput chaxunBtn = new TextInput("input[@name='Input' and @class='chaxun_btn']", this, "Input", "查询");	
	public Table table=new Table("table[class=ggkj_table]",this,"customerTable","客户table");
	
	
	
}
