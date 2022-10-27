package ExcelToXMLReader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReadXMLFile {
	int rowIndex;
	int columnIndex;
	List<String> columnTestData = new ArrayList<String>();
	int index = 0;
	String resultRow = "";
	
	public static void main(String[] argS) {
		String enterTestCaseID="104137";
		String enterClientName="Medica_LEP";
		String enterFieldName="Mail_Kit_Name";
		ReadXMLFile testSample = new ReadXMLFile();
		String result = testSample.readingXMLFile(enterTestCaseID, enterClientName, enterFieldName);
		System.out.println("Output:" + result);
	}
	
	public String readingXMLFile(String userInput, String getClientName, String rowName) {
		String userWorkingDirectory = System.getProperty("user.dir");
		String pathSeparator = System.getProperty("file.separator");

		try {
			File file = new File(userWorkingDirectory + pathSeparator + "src" + pathSeparator + "main" + pathSeparator
					+ "java" + pathSeparator + "ExcelToXMLReader" + pathSeparator + "TestData.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			NodeList worksheetList = doc.getElementsByTagName("worksheet");

			for (int i = 0; i < worksheetList.getLength(); i++) {
				Element worksheetElement = (Element) worksheetList.item(i);
				String clientName = (String) worksheetElement.getAttribute("name");

				if (getClientName.equals(clientName)) {
					NodeList nodeList = worksheetElement.getElementsByTagName("Row");
					ArrayList<Object> myList = new ArrayList<Object>();
					rowIndex = nodeList.getLength();
					String columnNameString = "";
					NodeList firstElement = nodeList.item(0).getChildNodes();

					for (int j = 0; j < firstElement.getLength(); j++) {
						Node childNode = firstElement.item(j);
						if (!childNode.getNodeName().equals("#text")) {
							columnNameString = columnNameString + childNode.getNodeName();
							columnNameString = columnNameString + ",";
							columnIndex++;
						}
					}

					myList.add(columnNameString);

					for (int itr = 0; itr < nodeList.getLength(); itr++) {
						Node node = nodeList.item(itr);
						Element eElement = (Element) node;
						NodeList childList = node.getChildNodes();
						columnNameString = "";

						for (int j = 0; j < childList.getLength(); j++) {
							Node childNode = childList.item(j);
							if (!childNode.getNodeName().equals("#text")) {
								String fieldNameString = childNode.getNodeName();
								columnNameString = columnNameString
										+ eElement.getElementsByTagName(fieldNameString).item(0).getTextContent();
								columnNameString = columnNameString + ",";
							}
						}
						myList.add(columnNameString);

					}

					List<String> getRow = new ArrayList<String>();
					List<String> myrow = new ArrayList<String>();
					index = getIndexValue(myList, getRow, rowName);
					Boolean resultColumn = printColumn(myList, myrow, userInput);
					resultRow = printRow(myList, myrow, userInput);
					if (!resultColumn && (resultRow.isEmpty())) {
						System.out.println("No Match Found");
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] arrOfStr = resultRow.split(",");
		return arrOfStr[index];
	}

	public Boolean printColumn(ArrayList<Object> myList, List<String> myrow, String userInput) {
		Boolean isMatch = false;
		String str = (String) myList.get(0);
		myrow.add(str);
		String[] res = str.replace("[", "").replace("]", "").split(",", columnIndex + 1);
		for (int column = 0; column <= columnIndex; column++) {
			String mytext = (res[column]).trim();
			if (mytext.equals(userInput)) {
				System.out.println("Result:  ");
				for (int row = 1; row <= rowIndex; row++) {
					str = (String) myList.get(row);
					myrow.add(str);
					res = str.replace("[", "").replace("]", "").split(",", columnIndex + 1);
					columnTestData.add(res[column]);
				}

				isMatch = true;
			}
		}
		return isMatch;

	}

	public String printRow(ArrayList<Object> myList, List<String> myrow, String userInput) {
		String rowTestData = "";
		for (int Index = 1; Index <= rowIndex; Index++) {
			String str = (String) myList.get(Index);
			myrow.add(str);
			String[] res = str.replace("[", "").replace("]", "").split(",", columnIndex + 1);
			for (int column = 0; column <= columnIndex; column++) {
				String mytext = (res[column]).trim();
				if (mytext.equals(userInput)) {
					rowTestData = (myrow.get(Index));
				}
			}
		}
		return rowTestData;
	}

	public int getIndexValue(ArrayList<Object> myList, List<String> myrow, String rowName) {
		String str = (String) myList.get(0);
		int indexing = 0;
		myrow.add(str);
		String[] res = str.replace("[", "").replace("]", "").split(",", columnIndex + 1);
		for (int column = 0; column <= columnIndex; column++) {
			String mytext = (res[column]).trim();
			if (mytext.equals(rowName)) {
				indexing = column;
			}
		}
		return indexing;
	}

}
