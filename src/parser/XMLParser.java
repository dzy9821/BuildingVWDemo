package parser;

import entity.matadata.Metadata;
import entity.matadata.Producer;
import entity.matadata.Task;
import entity.testcase.TestCase;
import entity.testcase.Variable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLParser {


    public static Metadata parseMetadata(String filePath){

        Metadata metadata = new Metadata();
        Task task = new Task();

        try {
            File file = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);


            Element root = doc.getDocumentElement();
            NodeList childNodes = root.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++){
                Node node = childNodes.item(i);
                if (node.getNodeName().equals("producer")){

                    parseProducer(node.getTextContent(), metadata);
                } else if (node.getNodeName().equals("programfile")) {

                    task.setInputFiles(node.getTextContent());
                } else if (node.getNodeName().equals("programhash")) {

                    task.setInputFileHashes(node.getTextContent());
                } else if (node.getNodeName().equals("creationtime")) {

                    metadata.setCreationTime(node.getTextContent());
                } else if (node.getNodeName().equals("architecture")) {

                    if (node.getTextContent().equals("32bit")){
                        task.setDataModel("ILP32");
                    }else {
                        task.setDataModel("LP64");
                    }
                } else if (node.getNodeName().equals("specification")) {
                    task.setSpecification(node.getTextContent());
                }
            }
            metadata.setTask(task);

            /*
            System.out.println(metadata.getFormatVersion());
            System.out.println(metadata.getUUID());
            System.out.println(metadata.getCreationTime());
            System.out.println(metadata.getProducer().getName());
            System.out.println(metadata.getProducer().getVersion());
            System.out.println(metadata.getProducer().getConfiguration());
            System.out.println(metadata.getTask().getInputFiles());
            System.out.println(metadata.getTask().getInputFileHashes());
            System.out.println(metadata.getTask().getSpecification());
            System.out.println(metadata.getTask().getDataModel());
            System.out.println(metadata.getTask().getLanguage());
             */

        } catch (Exception e) {
            e.printStackTrace();
        }

        return metadata;
    }
    public static TestCase parseTestCase(String filePath){
        TestCase testCase = new TestCase();


        try {
            File file = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);


            NodeList inputs = doc.getElementsByTagName("input");
            Variable[] variables = new Variable[inputs.getLength()];
            int index = 0;
            for (int i = 0; i < inputs.getLength(); i++) {
                Node node = inputs.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String varName = element.getAttribute("variable");
                    String type = element.getAttribute("type");
                    String value = element.getTextContent().trim();
                    Variable variable = new Variable(varName, value);
                    variables[index] =variable;
                    index++;

                    System.out.println("Variable: " + varName + ", Type: " + type + ", Value: " + value);
                }
            }
            testCase.setValues(variables);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return testCase;
    }
    public static void parseProducer(String nameAndVersion, Metadata metadata){
        String[] split = nameAndVersion.split(" ");
        Producer producer = new Producer(split[0], split[1]);
        metadata.setProducer(producer);
    }
}
