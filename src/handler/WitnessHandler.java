package handler;

import entity.content.Content;
import entity.content.Segment;
import entity.matadata.Metadata;

import java.io.*;

public class WitnessHandler {
    public static void writeWitness(Metadata metadata, Content content, String cCodeFilePath){

        File file = new File(cCodeFilePath);

        String fileParent = file.getParent();
        String witnessFileName = "witness.yml";
        File witnessFile = new File(fileParent, witnessFileName);
        String witnessFilePath = witnessFile.getAbsolutePath();
        System.out.println(witnessFilePath);

        try {
            PrintWriter writer = new PrintWriter(new FileWriter(witnessFilePath));

            writer.println("- entry_type: \"violation_sequence\"");
            writer.println("  metadata:");
            writer.println("    format_version: \"" + metadata.getFormatVersion() + "\"");
            writer.println("    uuid: \"" + metadata.getUUID() +"\"");
            writer.println("    creation_time: \"" + metadata.getCreationTime() + "\"");
            writer.println("    producer:");
            writer.println("      name: \"" + metadata.getProducer().getName() + "\"");
            writer.println("      version: \"" + metadata.getProducer().getVersion() + "\"");
            writer.println("      configuration: \"" + metadata.getProducer().getConfiguration() + "\"");
            writer.println("    task:");
            writer.println("      input_files:");
            writer.println("      - \"" + metadata.getTask().getInputFiles() + "\"");
            writer.println("      input_file_hashes:");
            writer.println("        \"" + metadata.getTask().getInputFiles() + "\": " + "\"" + metadata.getTask().getInputFileHashes() + "\"");
            writer.println("      specification: \"" + metadata.getTask().getSpecification() + "\"");
            writer.println("      data_model: \"" + metadata.getTask().getDataModel() + "\"");
            writer.println("      language: \"" + metadata.getTask().getLanguage() + "\"");
            writer.println("  content:");
            for (Segment segment : content.getSegments()){
                writer.println("  - segment:");
                writer.println("    - waypoint:");
                writer.println("        type: \"" + segment.getWayPoint().getType() + "\"");
                writer.println("        action: \"" + segment.getWayPoint().getAction() + "\"");
                if (segment.getWayPoint().getConstraint() != null){
                    writer.println("        constraint:");
                    writer.println("          value: \"" + segment.getWayPoint().getConstraint().getValue() + "\"");
                }
                writer.println("        location:");
                writer.println("          file_name: \"" + segment.getWayPoint().getLocation().getFileName() + "\"");
                writer.println("          line: " + segment.getWayPoint().getLocation().getLine());
                writer.println("          function: \"" + segment.getWayPoint().getLocation().getFunction() + "\"");

            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
