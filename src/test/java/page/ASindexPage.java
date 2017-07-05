package page;

import com.zendaimoney.Dokodemo.html.Link;
import com.zendaimoney.Dokodemo.html.PageModel;
import com.zendaimoney.Dokodemo.html.Element;
import com.zendaimoney.Dokodemo.html.ElementList;




public class ASindexPage extends PageModel{

	public ASindexPage(String selector, PageModel parent, String name,
			String description) {
		super(selector, parent, name, description);
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public Iframe iframe=new Iframe("iframe[src=/AS/workflow/jobins/getAllJob?taskinsId=90940]", this, "iframe", "iframe");
	
	public Element icon=new Element("div.panel-icon\\ icon-tab", this, "icon", "icon");
	
		public static class Iframe extends PageModel{

			public Iframe(String selector, PageModel parent, String name,
					String description) {
				super(selector, parent, name, description);
				// TODO Auto-generated constructor stub
			}
			
			
			public Element mustdo=new Element("div.panel\\ layout-panel\\ layout-panel-east\\ layout-split-east", this, "mustdo", "mustdo");
			
			
		}
		 
		
	
		
		public ElementList<Opers> operation=new ElementList<Opers>(Opers.class, "table.datagrid-btable>tbody>tr.datagrid-row", this, "operation", "办理中的操作列表");
		
		
		public static class Opers extends PageModel{

			public Opers(String selector, PageModel parent, String name,
					String description) {
				super(selector, parent, name, description);
				// TODO Auto-generated constructor stub
			}
			public Element td=new Element("td[field=opt]>div>a", this, description, description);
			
			public ElementList<Link> opers=new ElementList<Link>(Link.class,"td[field=opt]>div>a", this, "banlis", "banlis");
			
			
		}
		
		
		
		
		
		
	
	
	

}
