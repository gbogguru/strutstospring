import java.io.File;
import java.io.FileWriter;
import java.io.IOException; // Import the IOException class to handle errors
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class XMLTest3 {
	
	public static void main(String[] args) throws Exception {
		
		String fileName =  "C:\\codebase\\Migration\\struts-config.xml"; 
		Document document = getDocument(fileName);

		String xpathExpression_path = "";
		String xpathExpression_type = "";
		String xpathExpression_name = "";

		/******* Get attribute values using xpath ******/

		// Get all employee ids

		//NodeList nodeList = document.getElementsByTagName("action-mappings");
		NodeList nodeList = document.getElementsByTagName("action");
		//for (int itr = 0; itr < nodeList.getLength(); itr++) {
			
			
			xpathExpression_path = "/struts-config/action-mappings/action/@path";
			xpathExpression_type = "/struts-config/action-mappings/action/@type";
			xpathExpression_name = "/struts-config/action-mappings/action/forward/@name";
			List<String> pathlist = evaluateXPath(document, xpathExpression_path);
			List<String> typelist = evaluateXPath(document, xpathExpression_type);
			List<String> forwardlist = evaluateXPath(document, xpathExpression_name);

			String strPath = (String) pathlist.get(0);
			String strtype = (String) typelist.get(0);
			String strforwardlist = (String) forwardlist.get(0);

			//createJavaClass(strPath, strtype, strforwardlist);
			createJavaClasses(pathlist, typelist, forwardlist);
			
			/*
			 * System.out.println(strPath); System.out.println(strtype);
			 * System.out.println(strforwardlist);
			 */
		//}
		

	}

	private static List<String> evaluateXPath(Document document, String xpathExpression) throws Exception {
		// Create XPathFactory object
		XPathFactory xpathFactory = XPathFactory.newInstance();

		// Create XPath object
		XPath xpath = xpathFactory.newXPath();

		List<String> values = new ArrayList<>();
		try {
			// Create XPathExpression object
			XPathExpression expr = xpath.compile(xpathExpression);

			// Evaluate expression result on XML document
			NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);

			 
			for (int i = 0; i < nodes.getLength(); i++) {
				
				//values.add(nodes.item(i).getNodeValue());
				String nodeValue = nodes.item(i).getNodeValue();
				String actionName = nodeValue.substring(nodeValue.lastIndexOf(".") + 1);
				//String nodeValues[] = nodeValue.split(".");
				values.add(actionName);				
			}

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return values;
	}

	private static Document getDocument(String fileName) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		// File file = new File("F:\\Santosh\\WiproProject\\struts-config.xml");
		File file = new File(fileName);
		Document doc = builder.parse(file);
		return doc;
	}
	
	private static void createJavaClasses(List<String> strPath, List<String> strtype, List<String> strforwardlist) throws Exception {
		try {

			for (int i = 0; i < strPath.size(); i++) {
				
				String fileName = "C:\\codebase\\Migration\\" + strtype.get(i) + ".java";

				File myObj = new File(fileName);
				if (myObj.createNewFile()) {
					FileWriter myWriter = new FileWriter(fileName);

					// String subject="Knowledge base '" + text + "' Approval Request";

					int index = strtype.get(i).lastIndexOf('.');
					String strtype_Action = strtype.get(i).substring(index + 1, strtype.get(i).length());
					int index1 = strtype_Action.lastIndexOf("Action");
					String strtype_NoAction = strtype_Action.substring(0, index1);

					StringBuffer sb = new StringBuffer();
					sb.append("package com.org.digital;");
					sb.append("\n\n");
					sb.append("import javax.servlet.http.HttpServletRequest;\n");
					sb.append("import javax.servlet.http.HttpServletResponse;\n");
					sb.append("import java.util.*;\n");
					sb.append("import org.springframework.web.bind.annotation.RequestMapping;\n");
					sb.append("import org.springframework.web.bind.annotation.RestController;\n");
					sb.append("\n");
					
					sb.append("@RestController \n");
					sb.append("@RequestMapping(value = \"/application\") \n");
					sb.append("public class " + strtype.get(i) + " { \n\n");					
					sb.append("@GetMapping(value=");
					sb.append("\"");
					sb.append(strPath.get(i));
					sb.append("\"");

					sb.append(", method = RequestMethods.GET );");
					sb.append("\n");
					sb.append("public String execute");
					sb.append(strtype_NoAction + "");
					sb.append("(HttpServletRequset request , HttpServletResponse response) throws Exception {");
					sb.append("\n");
					sb.append("\n");
					sb.append("try{");
					sb.append("\n");
					sb.append("\t");
					sb.append(strtype_Action + "\t");
					String firstLetter = strtype_Action.substring(0, 1).toLowerCase();
					String restLetters = strtype_Action.substring(1);
					sb.append(firstLetter);
					sb.append(restLetters + " =");
					sb.append("\t");
					sb.append(strtype_Action + ".get" + strtype_Action + "Instance(request,response)");
					sb.append("\n");
					sb.append("\t");
					sb.append("String resultView =");
					sb.append("\t");
					sb.append(firstLetter);
					sb.append(restLetters + ".");
					sb.append(strforwardlist.get(i) + "(request,response);");
					sb.append("\n");
					sb.append("\t");
					sb.append("if(resultView.equalsIgnoreCase(");
					sb.append("\"");
					sb.append(strforwardlist.get(i));
					sb.append("\"");
					sb.append("));");
					sb.append("\t");
					sb.append("\n");
					sb.append("\t");
					sb.append("return");
					sb.append(" \"");
					sb.append(strPath.get(i).substring(1));
					sb.append("\";");
					sb.append("\n");
					sb.append("\t");
					sb.append("} catch ( Exception e) {");
					sb.append("\n");
					sb.append("\t");
					sb.append("e.printStackTrace();");
					sb.append("\n");
					sb.append("}");
					sb.append("\n");
					sb.append("return");
					sb.append(" \"");
					sb.append("\";");
					sb.append("\n");
					sb.append("}");
					
					sb.append("\n }");
					myWriter.write(sb.toString());
					myWriter.close();
					
					System.out.println(" Successfully " + strtype.get(i) + ".java created...");

				} else {
					System.out.println("File already exists.");
				}

			}
			
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
}