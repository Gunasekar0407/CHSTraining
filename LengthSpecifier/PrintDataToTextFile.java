package LengthSpecifier;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PrintDataToTextFile {

	static boolean result;

	public static void readData() {
		int columnIndex;
		int[] stringLength = { 3, 5, 10 };
		try {

			FileInputStream file = new FileInputStream(new File("Guna.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			PrintWriter writer = new PrintWriter("PlayersInformation.txt", "UTF-8");
			Iterator<Row> rowIterator = sheet.iterator();

			while (rowIterator.hasNext()) {

				Row row = rowIterator.next();

				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();

					switch (cell.getCellType()) {
					case STRING:
						columnIndex = cell.getColumnIndex();
						if (cell.getStringCellValue().length() > stringLength[columnIndex]) {
							writer.print((cell.getStringCellValue().substring(0, stringLength[columnIndex])));
						} else {
							writer.print(cell.getStringCellValue());
						}
						writer.print(" ");
						break;
					default:
						break;
					}
				}
				writer.println(" ");
			}
			System.out.println("Data Printed to Text File");
			file.close();
			workbook.close();
			writer.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
