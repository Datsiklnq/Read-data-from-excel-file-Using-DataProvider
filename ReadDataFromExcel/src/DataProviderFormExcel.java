
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.jetty.util.log.Log;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class DataProviderFormExcel extends DriverSettings {

	
	private static String EXCELPATH = "C:\\Users\\TBAG\\git\\Read-data-from-excel-file-Using-DataProvider\\ReadDataFromExcel\\src\\TestCase.xlsx";
	@BeforeClass(description = "Check driver settings")
	public void setUp() throws InterruptedException  {
		driver();
		
		
	}

	@Test(dataProvider = "FacebookForm" ,description = "Enter data to facebook form ")
	public void sFormValidation(String firstName, String secondName, String password, String firstEmail,
			String secondEmail) {
		PageObject pageObject = new PageObject(driver);

		pageObject.firstNameBox().sendKeys(firstName);
		pageObject.lastNameBox().sendKeys(secondName);
		pageObject.passwordBox().sendKeys(password);
		pageObject.mobilePhoneBox().sendKeys(firstEmail);
		pageObject.mobilePhoneBox().clear();
		pageObject.mobilePhoneBox().sendKeys(secondEmail);
		pageObject.firstNameBox().clear();
		pageObject.lastNameBox().clear();
		pageObject.mobilePhoneBox().clear();
		pageObject.passwordBox().clear();
		


	}
	
	@Test(description = "Example Fail test")
	public void validationUrl(){
		
		String actualUrl =driver.getCurrentUrl();
	
		Assert.assertEquals("www.socialmedia.com", actualUrl);
		
	
	}
	@Test(description = "Example Fail test number two")
public void validationUrlNumberTwo(){
		
		String actualUrl =driver.getCurrentUrl();
	
		Assert.assertEquals("www.instagram.com", actualUrl);
		
	
	}
	

<<<<<<< HEAD
	@DataProvider(name = "FacebookForm" )
	public Object[][] passDate()  {
=======
	@DataProvider(name = "FacebookForm")
	public Object[][] passData()  {
>>>>>>> refs/remotes/origin/master

		ExcelDataConfig cfg = new ExcelDataConfig(EXCELPATH);
		// excel sheet index value
		
		int rows = cfg.getRowCount(0);
		Object[][] data = new Object[rows][5];

		for (int i = 0; i < rows; i++) {
			data[i][0] = cfg.getData(0, i, 0);
			data[i][1] = cfg.getData(0, i, 1);
			data[i][2] = cfg.getData(0, i, 2);
			data[i][3] = cfg.getData(0, i, 3);
			data[i][4] = cfg.getData(0, i, 4);
			//cfg.getData(sheetNumber, row, column)
		
			
		}
			
		return data;
		

	}
	
	@AfterTest(description = "Check whether Excel Report is Generated")
	public void reportGeneration() throws ParserConfigurationException, IOException, Exception{
		
		//Create xlsx file 
		new ExcelReport().genereteExcelReport("ExcelReport.xlsx");
		
	}
	
	
}

