import entity.content.Content;
import entity.matadata.Metadata;
import entity.testcase.TestCase;
import handler.CCodeHandler;
import handler.WitnessHandler;
import parser.XMLParser;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {

        String cCodeFilePath = null;
        String metadataFilePath = null;
        String testcaseFilePath = null;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-c":
                    cCodeFilePath = args[i + 1];
                    break;
                case "-m":
                    metadataFilePath = args[i + 1];
                    break;
                case "-t":
                    testcaseFilePath = args[i + 1];
                    break;
            }
        }

        Metadata metadata = XMLParser.parseMetadata(metadataFilePath);
        TestCase testCase = XMLParser.parseTestCase(testcaseFilePath);
        Content content = CCodeHandler.handleCCode(cCodeFilePath, testCase);

        WitnessHandler.writeWitness(metadata, content, cCodeFilePath);
    }
}
