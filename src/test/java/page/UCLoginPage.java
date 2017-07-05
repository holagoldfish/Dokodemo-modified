package page;

import com.zendaimoney.Dokodemo.html.Button;
import com.zendaimoney.Dokodemo.html.PageModel;
import com.zendaimoney.Dokodemo.html.TextInput;

public class UCLoginPage extends PageModel {

	public UCLoginPage(String selector, PageModel parent, String name, String description) {
		super(selector, parent, name, description);
	}

	public TextInput username = new TextInput("input[id=username]", this, "username", "登录名");

	public TextInput password = new TextInput("input[id=password]", this, "password", "密码");

	public Button submit = new Button("a[class=btn]", this, "submit", "登录");
}
