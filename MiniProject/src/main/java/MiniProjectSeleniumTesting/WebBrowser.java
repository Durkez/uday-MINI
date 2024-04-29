package MiniProjectSeleniumTesting;

import java.io.FileInputStream;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
 
public class WebBrowser {
	public static FileInputStream file;
	public static Properties p = new Properties();

	// opening chrome browser
	public static WebDriver createChromeDriver() {           
		return new ChromeDriver();
 
	}
	//opening edge browser
	public static  WebDriver createEdgeDriver() {             
		return new EdgeDriver();
	}
	// navigating to the base URL
	public static void navigateToBaseUrl(WebDriver d) {      
		try {
			file = new FileInputStream("C:\\Users\\2318365\\eclipse-workspace\\MiniProject\\src\\main\\resources\\userinput.properties");
			p.load(file);
			}
			catch(Exception e) {
				System.out.println("File not found");
			}
	     	d.get(p.getProperty("URL"));	
	}
	// navigating to DataPicker URL
	public static void navigateToDatePickerUrl(WebDriver d) {       
		d.navigate().to(p.getProperty("dateURL"));
	}

	// closing the browser
	public static void closeBrowser(WebDriver d) {             
		d.quit();
	}


 
}