
import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataConfig {

	XSSFWorkbook wb;
	XSSFSheet sheet1;

	public ExcelDataConfig(String excelPath) {

		try {
			File src = new File(excelPath);
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	// Method which allows to read data from excel
	public String getData(int sheetNumber, int row , int column) {
	
		sheet1 = wb.getSheetAt(sheetNumber);
		String data = sheet1.getRow(row).getCell(column).getStringCellValue();
		return data;
		
	}

	public int getRowCount(int sheetIndex) {

		// it will return index number of rows
		int row = wb.getSheetAt(sheetIndex).getLastRowNum();
		//Return exact number of rows if i add + 1
		row = row + 1;

		
		return row;

	}
}

