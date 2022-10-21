package CSVFile;

import java.io.FileWriter;
import java.io.IOException;
import com.opencsv.CSVWriter;

public class WritingDataToCSVFile {
	public void writeCSVFile() throws IOException {
		try {
			String userWorkingDirectory = System.getProperty("user.dir");
			String pathSeparator = System.getProperty("file.separator");
			CSVWriter csvWriter = new CSVWriter(
					new FileWriter(userWorkingDirectory + pathSeparator + "src" + pathSeparator + "main" + pathSeparator
							+ "java" + pathSeparator + "CSVFile" + pathSeparator + "TestCase.csv"));
			String[] header = { "Sl.No", "TestCase ID", "TestCase Description", "Client" };
			csvWriter.writeNext(header);
			String[] data1 = { "1", "TU01", "Check Customer Login with valid Data", "Atmecs" };
			csvWriter.writeNext(data1);
			String[] data2 = { "2", "TU02", "Check Customer Login with invalid Data", "ConwayHealth" };
			csvWriter.writeNext(data2);
			String[] data3 = { "3", "TU03", "Check response when valid email and password is entered", "Aspire" };
			csvWriter.writeNext(data3);
			String[] data4 = { "4", "TU04", "Check response when a User ID is Empty & Login Button is pressed",
					"HrBerry" };
			csvWriter.writeNext(data4);
			System.out.println("CSV file wroted successfully");
			csvWriter.close();
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}

	}

}