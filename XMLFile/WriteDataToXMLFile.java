package XMLFile;

import org.testng.annotations.Test;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;

public class WriteDataToXMLFile {
	@Test
	public void createXMLFile() {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			 Element rootElement = doc.createElement("Test");
			 doc.appendChild(rootElement);

			// supercars element
			Element testCase = doc.createElement("TestCase");
			rootElement.appendChild(testCase);

//			Element slNO = doc.createElement("Sl.NO");
//			slNO.appendChild(doc.createTextNode("1"));
//			testCase.appendChild(slNO);

			Element testCaseID = doc.createElement("TestCaseID");
			testCaseID.appendChild(doc.createTextNode("TU01"));
			testCase.appendChild(testCaseID);

			Element description = doc.createElement("Description");
			description.appendChild(doc.createTextNode("Check Customer Login with valid Data"));
			testCase.appendChild(description);

			Element client = doc.createElement("Client");
			client.appendChild(doc.createTextNode("Atmecs"));
			testCase.appendChild(client);

			testCase = doc.createElement("TestCase");
			rootElement.appendChild(testCase);

//			slNO = doc.createElement("Sl.NO");
//			slNO.appendChild(doc.createTextNode("2"));
//			testCase.appendChild(slNO);

			testCaseID = doc.createElement("TestCaseID");
			testCaseID.appendChild(doc.createTextNode("TU02"));
			testCase.appendChild(testCaseID);

			description = doc.createElement("Description");
			description.appendChild(doc.createTextNode("Check Customer Login with invalid Data"));
			testCase.appendChild(description);

			client = doc.createElement("Client");
			client.appendChild(doc.createTextNode("ConwayHealth"));
			testCase.appendChild(client);
			
			testCase = doc.createElement("TestCase");
			rootElement.appendChild(testCase);
			
			testCaseID = doc.createElement("TestCaseID");
			testCaseID.appendChild(doc.createTextNode("TU03"));
			testCase.appendChild(testCaseID);

			description = doc.createElement("Description");
			description.appendChild(doc.createTextNode("Check response when valid email and password is entered"));
			testCase.appendChild(description);

			client = doc.createElement("Client");
			client.appendChild(doc.createTextNode("Aspire"));
			testCase.appendChild(client);


			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			String userWorkingDirectory = System.getProperty("user.dir");
			String pathSeparator = System.getProperty("file.separator");
			StreamResult result = new StreamResult(new File(userWorkingDirectory + pathSeparator + "src" + pathSeparator
					+ "main" + pathSeparator + "java" + pathSeparator + "XMLFile" + pathSeparator + "TestCasexml.xml"));
			transformer.transform(source, result);
			StreamResult consoleResult = new StreamResult(System.out);
			transformer.transform(source, consoleResult);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
