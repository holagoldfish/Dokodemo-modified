package com.zendaimoney.Dokodemo.html;

public class IFrame extends PageModel{

	public IFrame(String selector, PageModel parent, String name,
			String description) {
		super(selector, parent, name, description);
	}
	

	
	public void select() throws Exception{
		if(isExist()){
			try{
				engine.selectFrame();
				
			}catch (Exception ex) {
				logElementFailed(ex);
			}
		}
			
		
		
		
	}
	

}
