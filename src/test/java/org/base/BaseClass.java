package org.base;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class BaseClass {
	public static WebDriver driver;

	public void getDriver(String browserType) {

		switch (browserType) {
		case "Chrome":

			// To Config Browser
			System.setProperty("webdriver.chrome.driver",
					"C:\\Eclipse Tools\\BrowserLaunch\\BasicTest\\Driver\\chromedriver.exe");

			driver = new ChromeDriver();
			break;

		case "Edge":
			// To Config Browser
			System.setProperty("webdriver.edge.driver",
					"C:\\Eclipse Tools\\BrowserLaunch\\BasicTest\\Driver\\msedgedriver.exe");
			
			driver = new EdgeDriver();
			break;
		case "FireFox":
			// To Config Browser
			System.setProperty("webdriver.fireFox.driver",
					"C:\\Eclipse Tools\\BrowserLaunch\\BasicTest\\Driver\\selenium-firefox-driver-3.4.0.jar");
			
			driver = new FirefoxDriver();
			break;	
		default:
			System.out.println("Invalid Browser Type");
			break;
		}

	}

	public void getUrl(String url) {
		driver.get(url);
	}

	public void winMax() {
		driver.manage().window().maximize();
	}

	public void textSendByJava(WebElement element, String keysToSend) {
		element.sendKeys(keysToSend);
	}

	public void textSendByJS(WebElement element, String keysToSend) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value','" + keysToSend + "')", element);

	}

	public void buttonClick(WebElement element) {
		element.click();
	}

	public void selectByvalue(WebElement element, String value) {
		Select s = new Select(element);
		s.selectByValue(value);

	}

	public void selectByIndex(WebElement element, int index) {
		Select s = new Select(element);
		s.selectByIndex(index);
	}

	public static void screenCapture(String name) throws IOException {

		TakesScreenshot tk = (TakesScreenshot) driver;
		// To get screenShot from TakescreenShot
		File source = tk.getScreenshotAs(OutputType.FILE);

		File Destin = new File(
				"C:\\Eclipse Tools\\BrowserLaunch\\BasicTest\\Screenshot\\" + name+ ".png");

		FileUtils.copyFile(source, Destin); // throws IOException

	}

	public void actionsFunction(String actionType, WebElement targetElement) {

		Actions act = new Actions(driver);

		switch (actionType) {
		case "MouseOver":
			act.moveToElement(targetElement).build().perform();
			break;

		case "DoubleClick":
			act.doubleClick(targetElement).build().perform();
			break;

		case "RightClick":
			act.contextClick(targetElement).build().perform();
			break;

		default:
			break;
		}

	}

	public void dragandDropActions(WebElement sourceElement, WebElement targetElement) {
		Actions act = new Actions(driver);
		act.dragAndDrop(sourceElement, targetElement).build().perform();

	}

	public String excelRead(int rownum, int cellnum) throws IOException {

			File fileLocation = new File("C:\\Eclipse Tools\\MD.xlsx");

			FileInputStream inputStream = new FileInputStream(fileLocation); // throws FileNotFoundException

			Workbook book = new XSSFWorkbook(inputStream); // throws IOException

			org.apache.poi.ss.usermodel.Sheet sheet = book.getSheet("Sheet1");
			Row row = sheet.getRow(rownum);

			org.apache.poi.ss.usermodel.Cell cell = row.getCell(cellnum);

			// To Find Cell type
			CellType cellType = cell.getCellType();
			
			String value ="";

			switch (cellType) {
			case STRING:
				 value = cell.getStringCellValue();

				break;

			case NUMERIC:
				// boolean cellDateFormatted = DateUtil.isCellDateFormatted(cell);
				if (DateUtil.isCellDateFormatted(cell)) {
					Date dateCellValue = cell.getDateCellValue();
					SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy");
					 value = simple.format(dateCellValue);

				} else {

					double numericCellValue = cell.getNumericCellValue();
					BigDecimal valueOf = BigDecimal.valueOf(numericCellValue);
					 value = valueOf.toString();
				}
				
				break;
			default:
				System.out.println("None of the Format");
				break;
			}
			return value;
			
	}
}
