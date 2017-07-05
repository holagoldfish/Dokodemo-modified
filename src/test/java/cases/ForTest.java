package cases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ForTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriver drr = new ChromeDriver();
		drr.get("http://172.16.230.205:8080/autofortune/account/user/main");	
		drr.findElement(By.id("username")).sendKeys("admin");
		drr.findElement(By.id("password")).sendKeys("123456");
		drr.findElement(By.className("btn")).click();
		

	}

}
