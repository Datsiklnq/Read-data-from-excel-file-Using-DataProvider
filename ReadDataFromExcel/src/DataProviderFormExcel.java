import org.eclipse.jetty.util.log.Log;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderFormExcel extends DriverSettings {
	/*
	 * private static String firstName = "Przemek"; private static String
	 * secondName = "Kieloch"; private static String password = "Check"; private
	 * static String firstPhoneNumber = "08777777"; private static String
	 * secondPhoneNumber = "0877777744"; private static String errorMessage =
	 * "Please enter a valid mobile number or email address.\nClose popup and return"
	 * ;
	 * 
	 */
	
	private static String EXCELPATH = "C:\\Users\\TBAG\\workspace\\ReadDataFromExcel\\src\\TestCase.xlsx";
	@BeforeClass
	public void setUp() throws InterruptedException {
		driver();
	}

	@Test(dataProvider = "FacebookForm")
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

	@DataProvider(name = "FacebookForm")
	public Object[][] passData()  {

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
}
