package CSVFile;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import com.opencsv.CSVReader;

public class ReadFromCSVFile {
	public void readCSVFile() {
		try {
			String userWorkingDirectory = System.getProperty("user.dir");
			String pathSeparator = System.getProperty("file.separator");
			String path = userWorkingDirectory + pathSeparator + "src" + pathSeparator + "main" + pathSeparator + "java"
					+ pathSeparator + "CSVFile" + pathSeparator + "TestCase.csv";
			File file = new File(path);
			FileReader filereader = new FileReader(file);
			CSVReader csvReader = new CSVReader(filereader);
			String[] nextLine;
			ArrayList<Object> myList = new ArrayList<Object>();
			while ((nextLine = csvReader.readNext()) != null) {
				String one = Arrays.toString(nextLine);
				myList.add(one);
			}
			Scanner scanner = new Scanner(System.in);
			System.out.println("Please enter the Data You Want");
			String line;
			line = scanner.nextLine();
			List<String> myrow = new ArrayList<String>();
			Boolean resultColumn = printColumn(myList, myrow, line);
			Boolean resultRow = printRow(myList, myrow, line);
			if (!resultColumn && !resultRow) {
				System.out.println("No Match Found");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public Boolean printColumn(ArrayList<Object> myList, List<String> myrow, String line) {
		Boolean isMatch = false;
		String str = (String) myList.get(0);
		myrow.add(str);
		String[] res = str.replace("[", "").replace("]", "").split(",", 5);
		for (int columnIndex = 0; columnIndex <= 3; columnIndex++) {
			String mytext = (res[columnIndex]).trim();
			if (mytext.equals(line)) {
				System.out.println("Result:  ");
				for (int column = 1; column <= 4; column++) {
					str = (String) myList.get(column);
					myrow.add(str);
					res = str.replace("[", "").replace("]", "").split(",", 5);
					System.out.println(res[columnIndex]);
				}

				isMatch = true;
			}
		}
		return isMatch;

	}

	public Boolean printRow(ArrayList<Object> myList, List<String> myrow, String line) {
		Boolean isMatch = false;
		for (int rowIndex = 1; rowIndex <= 4; rowIndex++) {
			String str = (String) myList.get(rowIndex);
			myrow.add(str);
			String[] res = str.replace("[", "").replace("]", "").split(",", 5);
			for (int row = 0; row <= 3; row++) {
				String mytext = (res[row]).trim();
				if (mytext.equals(line)) {
					System.out.println("Result:  ");
					System.out.println(myrow.get(rowIndex));
					isMatch = true;
				}
			}
		}
		return isMatch;
	}
}
