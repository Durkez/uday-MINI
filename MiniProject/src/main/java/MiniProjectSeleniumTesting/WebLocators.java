package MiniProjectSeleniumTesting;


import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
 
public class WebLocators {

	// returning drag element
	public static WebElement getDragElement(WebDriver d) {
		return d.findElement(By.id("draggable"));
	}
	// returning drop element
	public static WebElement getDropElement(WebDriver d) {
			return d.findElement(By.id("droppable"));
		}
	//returning date element
	public static WebElement getDateElement(WebDriver d) {                   
		return d.findElement(By.id("datePickerMonthYearInput"));
	}
	//returning calendar elements of date 
	public static List<WebElement> getCalendarElements(WebDriver d) {
		return d.findElements(By.xpath("//div[@class='react-datepicker__month']/div/div"));
	}

	// returning dateAndtime element
	public static WebElement getDateAndTimeElement(WebDriver d) {
		return d.findElement(By.xpath("//*[@id=\"dateAndTimePickerInput\"]"));
	}
	// returning calendar elements of dateAndTime
	public static List<WebElement> getCalendarElements2(WebDriver d) {
			return d.findElements(By.xpath("//div[@class='react-datepicker__month']/div/div"));
		}
	// returning time elements of dateAndTime element
	public static List<WebElement> getTimeElements(WebDriver d){
		return d.findElements(By.xpath("//ul[@class='react-datepicker__time-list']/li"));
	}
	// returning screenshot element
	public static WebElement screenshotElement(WebDriver d) {
		return d.findElement(By.id("datePickerContainer"));
	}

	// launching the webDriver
	public static WebDriver getDriver(int inputBrowser) {
		WebDriver driver = null;
		System.out.println("Opening URL,Please Wait...");
		if(inputBrowser==1) {
			driver = WebBrowser.createChromeDriver();	
		}
		else if(inputBrowser==2){
	    	driver = WebBrowser.createEdgeDriver();
		}
		return driver;

		}
 
	
	// performing the drag and drop function and verifying the result in this method
    public static void performDragAndDropFunction(WebDriver driver) {

    	//Navigating to the base URL
		WebBrowser.navigateToBaseUrl(driver);           
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		// getting drag and drop elements from the methods
		WebElement dragElement = getDragElement(driver);    
		WebElement dropElement = getDropElement(driver);

		driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		//scrolling webPage to the drag element
		js.executeScript("arguments[0].scrollIntoView();",dragElement);  
		// performing drag and drop function
		Actions act = new Actions(driver);                          
		act.dragAndDrop(dragElement, dropElement).perform();
		String validationMsg = dropElement.getText();
		String expectedValidationMsg = "Dropped!";

		// verifying whether the drag object is dropped to the drop object or not
		System.out.println("RESULT : ");                             
		if(validationMsg.equals(expectedValidationMsg)) {
			System.out.println("PASS: Drag object is dropped to target as expected");	
		}
		else {
			System.out.println("FAIL: Drag object couldn't be dropped to target as expected");
		}
		// navigating to dataPicker URL
		WebBrowser.navigateToDatePickerUrl(driver);                

  }

    // navigating to the next URL and performing the date pick function
    public static void datePickFunction(WebDriver driver) {
    	// getting DateInput element
		WebElement dateElement = getDateElement(driver);  
		// scrolling webPage to the dateInput element
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("arguments[0].scrollIntoView();", dateElement); 
		//finding tomorrow date
		String todayDate =dateElement.getAttribute("value");    
		String arr[] = todayDate.split("/");   //03/14/2024
		
		String temp = "";
		if(arr[1].charAt(0)=='0') {
		temp = arr[1].replace("0", "");
		}
		else {
			temp = arr[1];
		}
		String tomorrowDate = (Integer.parseInt(temp)+1)+"";
		//System.out.println(tomorrowDate);

		// getting all date elements of Calendar
		dateElement.click();
		List<WebElement> datesOfCalendarElement= getCalendarElements(driver); 
		// clicking the tomorrow date in DateInput element
		for(int i=0; i<datesOfCalendarElement.size();i++) {	               
					if(datesOfCalendarElement.get(i).getText().equals(tomorrowDate)) {
						//e.click();
						js.executeScript("arguments[0].click();",datesOfCalendarElement.get(i));
						break;
					}
				}
		// getting dateAndTimeInput Element
		WebElement dateAndTimeElement = getDateAndTimeElement(driver);  
		dateAndTimeElement.click();
		//getting all date elements of calendar2
		List<WebElement> datesOfCalendarElement2 = getCalendarElements(driver); 
		// clicking the tomorrow date in dateAndTimeInput element
		for(int i=0; i<datesOfCalendarElement2.size();i++) {         
					if(datesOfCalendarElement2.get(i).getText().equals(tomorrowDate)) {
						//e.click();
						js.executeScript("arguments[0].click();",datesOfCalendarElement2.get(i));
						break;
					}
		}

		// clicking the time in dateAndTimeInput element
		List<WebElement>timeElements = getTimeElements(driver);  
		//System.out.println(timeElements);
		for(WebElement e : timeElements) {
					if(e.getText().equals("06:00")) {
						e.click();
						break;
					}	
				}

  }
      // performing the screenshot function
	  public static void screenshotFunction(WebDriver driver) throws IOException {
		  Screenshot.takeScreenshot(driver);
	  }
 
}
