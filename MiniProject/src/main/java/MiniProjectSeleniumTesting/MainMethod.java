package MiniProjectSeleniumTesting;

import java.io.IOException;
import java.util.*;
import org.openqa.selenium.WebDriver;
 
public class MainMethod {	
 
	public static void main(String[] args) throws IOException {
 
		
		//Displaying the browser options and getting input from user
		Scanner sc= new Scanner(System.in);        
		System.out.println("Enter '1' for Opening Chrome Browser\nEnter '2' for Opening Edge Browser \n----------------------------"
	    + "\nEnter The Browser Option You Want To Launch :");
		int inputBrowser = sc.nextInt();         
		while(inputBrowser<1||inputBrowser>2) {
			System.out.println("Please,Enter a valid input");
			inputBrowser = sc.nextInt();
				}

		// performing the operation of automating the Website.
		WebDriver driver = WebLocators.getDriver(inputBrowser);
		WebLocators.performDragAndDropFunction(driver);
		WebLocators.datePickFunction(driver);
		WebLocators.screenshotFunction(driver);
		WebBrowser.closeBrowser(driver);
	}
 
}
