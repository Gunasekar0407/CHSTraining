package CSVFile;

import java.io.IOException;

public class RunnerClass {
	public static void main(String[] args) throws IOException {
		WritingDataToCSVFile writingDataToCSVFile = new WritingDataToCSVFile();
		ReadFromCSVFile readFromCSVFile = new ReadFromCSVFile();
		writingDataToCSVFile.writeCSVFile();
		readFromCSVFile.readCSVFile();
	}
}
