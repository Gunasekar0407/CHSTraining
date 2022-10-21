package LengthSpecifier;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class WriteDataToExcel {
@Test
	public static void writeData() {
		try {

			XSSFWorkbook workbook = new XSSFWorkbook();

			XSSFSheet spreadsheet = workbook.createSheet(" DataSheet ");

			XSSFRow row;

			Map<Integer, Object[]> studentData = new HashMap<Integer, Object[]>();

			studentData.put(0, new Object[] { "FirstName ", "LastName", "Address" });
			studentData.put(1, new Object[] { "Suresh", "Raina", "No.646, Urappakkam 603210" });
			studentData.put(2, new Object[] { "Hardik", "Pandya", "No.647, Urappakkam 603210" });
			studentData.put(3, new Object[] { "Suryakumar", "Yadav", "No.648, Urappakkam 603210" });
			studentData.put(4, new Object[] { "Virat", "Kohli", "No.649, Urappakkam 603210" });
			studentData.put(5, new Object[] { "Mohammad", "Shami", "No.650, Urappakkam 603210" });
			studentData.put(6, new Object[] { "Jasprit", "Bumrah", "No.651, Urappakkam 603210" });
			studentData.put(7, new Object[] { "Rohit", "Sharma", "No.652, Urappakkam 603210" });
			studentData.put(8, new Object[] { "Bhuvaneshwar", "Kumar", "No.653, Urappakkam 603210" });
			studentData.put(9, new Object[] { "Krunal", "Pandya", "No.654, Urappakkam 603210" });
			studentData.put(10, new Object[] { "RishabPant", "p", "No.655, Urappakkam 603210" });
			

			Set<Integer> keyid = studentData.keySet();

			int rowid = 0;

			for (Integer key : keyid) {

				row = spreadsheet.createRow(rowid++);
				Object[] objectArr = studentData.get(key);
				int cellid = 0;

				for (Object obj : objectArr) {
					Cell cell = row.createCell(cellid++);
					cell.setCellValue((String) obj);
				}
			}

			FileOutputStream out = new FileOutputStream(
					new File("C:\\Users\\gunasekar.dhanapal\\eclipse-workspace\\CHSMiramar\\Guna.xlsx"));
			workbook.write(out);
			out.close();
			workbook.close();
			System.out.println("Writing on XLSX file Finished ...");
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
	}
}