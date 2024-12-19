package entity.matadata;

import java.util.List;

public class Task {
    private String inputFiles;
    private String inputFileHashes;
    private String specification;
    private String DataModel;
    private final String language = "C";

    public String getInputFiles() {
        return inputFiles;
    }

    public void setInputFiles(String inputFiles) {
        this.inputFiles = inputFiles;
    }

    public String getInputFileHashes() {
        return inputFileHashes;
    }

    public void setInputFileHashes(String inputFileHashes) {
        this.inputFileHashes = inputFileHashes;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getDataModel() {
        return DataModel;
    }

    public void setDataModel(String dataModel) {
        DataModel = dataModel;
    }

    public String getLanguage() {
        return language;
    }
}
