package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestDataUtils {

	/**
	 * This method is used to get the data from excel
	 * @param tcID
	 * @return
	 * @throws IOException
	 */
	
	public static HashMap<String, String> getDataFromDataprovider(String tcID) throws IOException {
		FileInputStream file = null;
		HashMap<String, String> apiInformation = new HashMap<String, String>();
		try {

			DataFormatter formatter = new DataFormatter();

			file = new FileInputStream(System.getProperty("user.dir") + "\\Resources\\API_Information.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			for (int row = 1; row < sheet.getPhysicalNumberOfRows(); row++) {
				for (int col = 0; col < sheet.getRow(0).getPhysicalNumberOfCells(); col++) {
					if (sheet.getRow(row).getCell(0).getStringCellValue().equalsIgnoreCase(tcID)) {

						String colHeader = formatter.formatCellValue(sheet.getRow(0).getCell(col));
						String colValue = formatter.formatCellValue(sheet.getRow(row).getCell(col));
						apiInformation.put(colHeader, colValue);
					}
				}
			}
			return apiInformation;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			file.close();
		}
		return apiInformation;
	}

}
