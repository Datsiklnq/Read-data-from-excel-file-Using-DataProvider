

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ExcelReport {

	private XSSFWorkbook book = new XSSFWorkbook();
	private XSSFCellStyle pass = book.createCellStyle();
	private XSSFCellStyle fail = book.createCellStyle();
	private XSSFCellStyle skip = book.createCellStyle();
	private String error = "fail";
	public static int ID = 101;

	public void genereteExcelReport(String destFileName) throws ParserConfigurationException, Exception, IOException {

		String path = ExcelReport.class.getClassLoader().getResource("./").getPath();
		path = path.replaceAll("bin", "src");
		File xmlFile = new File(path + "../test-output/testng-results.xml");
		// check whether xml file exist
		DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
		DocumentBuilder build = fact.newDocumentBuilder();
		Document doc = build.parse(xmlFile);
		doc.getDocumentElement().normalize();

		// retrieve data from xml by tag name "test"
		NodeList testList = doc.getElementsByTagName("test");
		for (int i = 0; i < testList.getLength(); i++) {
			int r = 1;
			Node testNode = testList.item(i);
			// retrieve data from xml by tag name
			String testName = ((Element) testNode).getAttribute("name");
			XSSFSheet sheet = book.createSheet(testName);
			NodeList classList = ((Element) testNode).getElementsByTagName("class");

			for (int j = 0; j < classList.getLength(); j++) {

				Node classNode = classList.item(j);
				String className = ((Element) classNode).getAttribute("name");
				NodeList testMethod = ((Element) classNode).getElementsByTagName("test-method");

				for (int k = 0; k < testMethod.getLength(); k++) {

					Node testMethodNode = testMethod.item(k);
					String testMethodName = ((Element) testMethodNode).getAttribute("name");
					String testMethodStatus = ((Element) testMethodNode).getAttribute("status");
					String testDuration = ((Element) testMethodNode).getAttribute("duration-ms");
					String testStartedAt = ((Element) testMethodNode).getAttribute("started-at");
					String testFinishAt = ((Element) testMethodNode).getAttribute("finished-at");
					String testDescription = ((Element) testMethodNode).getAttribute("description");

					XSSFRow row = sheet.createRow(r++);

					// Name of the class
					XSSFCell cellClass = row.createCell(1);
					cellClass.setCellValue(className);
					// Name of testCases (Methods)
					XSSFCell cellTestMethod = row.createCell(2);
					cellTestMethod.setCellValue(testMethodName);
					// Status of test case fail , skip or pass
					XSSFCell cellStatus = row.createCell(7);
					cellStatus.setCellValue(testMethodStatus);
					// Duration of test in milliseconds
					XSSFCell cellDuration = row.createCell(8);
					cellDuration.setCellValue(testDuration);
					// Started at cell
					XSSFCell cellStartedAt = row.createCell(9);
					cellStartedAt.setCellValue(testStartedAt);
					// Finished at cell
					XSSFCell cellFinishedAt = row.createCell(10);
					cellFinishedAt.setCellValue(testFinishAt);
					// Description cell
					XSSFCell cellDescription = row.createCell(11);
					cellDescription.setCellValue(testDescription);
					// Test case id cell
					XSSFCell cellId = row.createCell(0);
					cellId.setCellValue(ID++);
					// Call method and pass parameters
					passFailOrSkip(testMethodStatus, cellStatus);

					// Write into excel ,what error occurred during testing
					if (error.equalsIgnoreCase(testMethodStatus)) {
						NodeList message = ((Element) testMethodNode).getElementsByTagName("message");
						Node expNode = message.item(0);
						String expMessage = expNode.getTextContent();
						XSSFCell cellMessage = row.createCell(12);
						cellMessage.setCellValue(expMessage);

					}
				}

			}

		}

		// Called method to create headings
		createHeadings(book, destFileName);
		FileOutputStream fos = new FileOutputStream(path + destFileName);
		book.write(fos);
		fos.close();

		System.out.println("Report Generated Successfully ");
	}

	public void createHeadings(XSSFWorkbook workbook, String destFileName) throws IOException {
		XSSFSheet sheet = workbook.getSheetAt(0);
		String[] headers = new String[] { "Test ID", "Class Name", "Test Case", "Second Name", "Password",
				"First Email", "Second Email", "Status", "Duration-ms", "Test Started-At", "Test Finished-At",
				"Description", "Error" };
		// creating a row
		Row header = sheet.createRow(0);
		XSSFCellStyle style = workbook.createCellStyle();
		for (int h = 0; h < headers.length; h++) {
			// creating two cells and set cell value to headers
			header.createCell(h).setCellValue(headers[h]);
			style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			/*style.setBorderTop(XSSFCellStyle.BORDER_MEDIUM);
			style.setBorderRight(XSSFCellStyle.BORDER_MEDIUM);
			style.setBorderLeft(XSSFCellStyle.BORDER_MEDIUM);
			style.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);*/
			header.getCell(h).setCellStyle(style);
			// Adjust cell size to the text also for loop can be used to adjust
			// all
			// columns
			sheet.autoSizeColumn(h);
		}

	}

	public void passFailOrSkip(String testStatus, XSSFCell cellStatus) {

		pass.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
		pass.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		fail.setFillForegroundColor(IndexedColors.RED.getIndex());
		fail.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		skip.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
		skip.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		if (testStatus.equalsIgnoreCase("Pass")) {
			cellStatus.setCellStyle(pass);

		} else if (testStatus.equalsIgnoreCase("Fail")) {
			cellStatus.setCellStyle(fail);
		} else {

			cellStatus.setCellStyle(skip);

		}

	}

}
