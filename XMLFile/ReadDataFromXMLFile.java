package XMLFile;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReadDataFromXMLFile {
	int rowIndex;

	public void readingXMLFile(String info, String input) {
		String userWorkingDirectory = System.getProperty("user.dir");
		String pathSeparator = System.getProperty("file.separator");
		try {
			File file = new File(userWorkingDirectory + pathSeparator + "src" + pathSeparator + "main" + pathSeparator
					+ "java" + pathSeparator + "XMLFile" + pathSeparator + "TestCasexml.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName("TestCase");
			ArrayList<Object> myList = new ArrayList<Object>();
			myList.add("TestCaseID, Description, Client");
			rowIndex = nodeList.getLength();
			for (int itr = 0; itr < nodeList.getLength(); itr++) {
				Node node = nodeList.item(itr);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					myList.add("" + eElement.getElementsByTagName("TestCaseID").item(0).getTextContent() + ","
							+ eElement.getElementsByTagName("Description").item(0).getTextContent() + ","
							+ eElement.getElementsByTagName("Client").item(0).getTextContent());
				}
			}
			List<String> myrow = new ArrayList<String>();
			Boolean resultColumn = printColumn(myList, myrow, input);
			Boolean resultRow = printRow(myList, myrow, input);
			if (!resultColumn && !resultRow) {
				System.out.println("No Match Found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Boolean printColumn(ArrayList<Object> myList, List<String> myrow, String input) {
		Boolean isMatch = false;
		String str = (String) myList.get(0);
		myrow.add(str);
		String[] res = str.replace("[", "").replace("]", "").split(",", 5);
		for (int columnIndex = 0; columnIndex <= 2; columnIndex++) {
			String mytext = (res[columnIndex]).trim();
			if (mytext.equals(input)) {
				System.out.println("Result:  ");
				for (int row = 1; row <= rowIndex; row++) {
					str = (String) myList.get(row);
					myrow.add(str);
					res = str.replace("[", "").replace("]", "").split(",", 5);
					System.out.println(res[columnIndex]);
				}

				isMatch = true;
			}
		}
		return isMatch;

	}

	public Boolean printRow(ArrayList<Object> myList, List<String> myrow, String input) {
		Boolean isMatch = false;
		for (int Index = 1; Index <= rowIndex; Index++) {
			String str = (String) myList.get(Index);
			myrow.add(str);
			String[] res = str.replace("[", "").replace("]", "").split(",", 5);
			for (int column = 0; column <= 2; column++) {
				String mytext = (res[column]).trim();
				if (mytext.equals(input)) {
					System.out.println("Result:  ");
					System.out.println(myrow.get(Index));
					isMatch = true;
				}
			}
		}
		return isMatch;
	}

	public static void main(String[] argS) {
		ReadDataFromXMLFile testSample = new ReadDataFromXMLFile();
		testSample.readingXMLFile("Enter the Data you want::", "TestCaseID");
	}
}
