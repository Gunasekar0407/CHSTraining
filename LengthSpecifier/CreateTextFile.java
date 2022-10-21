package LengthSpecifier;

import java.io.File;
import java.io.IOException;

public class CreateTextFile {
	static boolean result;

	public static void createFile() {
		File file = new File("PlayersInformation.txt");
		try {
			result = file.createNewFile();
			if (result) {
				System.out.println("file created " + file.getCanonicalPath());
			} else {
				System.out.println("File already exist at location: " + file.getCanonicalPath());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
