package handler;

import entity.content.Content;
import entity.content.Segment;
import entity.content.WayPoint;
import entity.testcase.TestCase;
import entity.testcase.Variable;
import parser.WayPointParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class CCodeHandler {
    private static int lineCounter = 0;

    public static Content handleCCode(String cCodeFilePath, TestCase testCase) throws IOException, InterruptedException {
        Variable[] variables = testCase.getValues();
        int index = 0;
        File file = new File(cCodeFilePath);

        String fileParent = file.getParent();
        String modifiedFileName = "modifiedFile.c";
        File midifiedFile = new File(fileParent, modifiedFileName);
        String modifiedFilePath = midifiedFile.getAbsolutePath();
        String executableFileName = "executableFile";
        File executableFile = new File(fileParent, executableFileName);
        String executableFilePath = executableFile.getAbsolutePath();

        BufferedReader reader = new BufferedReader(new FileReader(cCodeFilePath));
        BufferedWriter writer = new BufferedWriter(new FileWriter(modifiedFilePath));


        writer.write("#include <stdio.h>\n");

        String line;
        Pattern ifPattern = Pattern.compile("if\\s*\\((.*)\\)");

        while ((line = reader.readLine()) != null) {
            lineCounter++;
            String trimmedLine = line.trim();


            if (trimmedLine.contains("__VERIFIER_nondet_uchar") && !trimmedLine.contains("extern")) {

                String[] segment = trimmedLine.split(" ");
                StringBuilder modifiedLine = new StringBuilder();
                for (String s : segment) {
                    if (s.contains("__VERIFIER_nondet_uchar")){
                        modifiedLine.append(variables[index].getValue());

                    }else {
                        modifiedLine.append(s);
                    }
                    modifiedLine.append(" ");
                }

                writer.write(modifiedLine.toString().trim() + ";\n");
                writer.write("printf(\"line " + lineCounter + " assumption " + variables[index].getVarName() + " == " + variables[index].getValue() + "\\n\");\n");

                index++;

            } else if (ifPattern.matcher(trimmedLine).find()) {

                Matcher matcher = ifPattern.matcher(trimmedLine);
                if (matcher.find()) {
                    String condition = matcher.group(1);
                    writer.write(String.format("printf(\"Line " + lineCounter + " branching %%s\\n\", (%s) ? \"true\" : \"false\");\n", condition, condition));
                }
                writer.write(line + "\n");
            } else if (trimmedLine.contains("__VERIFIER_error") && !trimmedLine.contains("void")) {

                writer.write("printf(\"Line " + lineCounter + " target\\n\");\n");
                writer.write(line + "\n");
            }else {
                writer.write(line + "\n");
            }
        }

        reader.close();
        writer.close();


        System.out.println("Output file: " + modifiedFilePath);


        System.out.println("Compiling...");
        Process compileProcess = Runtime.getRuntime().exec("gcc " + modifiedFilePath + " -o " + executableFilePath);
        compileProcess.waitFor();
        System.out.println("finished.");


        System.out.println("Running C program...");
        Process runProcess = Runtime.getRuntime().exec(executableFilePath);
        BufferedReader processOutput = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
        String outputLine;

        Content content = new Content();
        List<Segment> segments= new ArrayList<>();

        while ((outputLine = processOutput.readLine()) != null) {

            Segment segment = new Segment();
            WayPoint wayPoint = WayPointParser.parseWayPoint(outputLine, cCodeFilePath);
            segment.setWayPoint(wayPoint);
            segments.add(segment);
        }
        content.setSegments(segments);

        processOutput.close();
        runProcess.waitFor();
        System.out.println("finished.");

        return content;

    }


}
