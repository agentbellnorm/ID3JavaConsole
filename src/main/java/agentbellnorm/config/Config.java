package agentbellnorm.config;

import com.google.gson.*;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Morgan on 2016-11-28.
 */
public class Config {
    private String dataFileName;
    private String outcomeAttribute;
    private String positiveOutcome;
    private String negativeOutcome;
    private String delimeter;
    private String identifier;
    private List<String> predictiveColumns;

    public String getDataFileName() {
        return dataFileName;
    }

    public String getOutcomeAttribute() {
        return outcomeAttribute;
    }

    public String getPositiveOutcome() {
        return positiveOutcome;
    }

    public String getNegativeOutcome() {
        return negativeOutcome;
    }

    public String getDelimeter() {
        return delimeter;
    }

    public String getIdentifier() {
        return identifier;
    }

    public List<String> getPredictiveColumns() {
        return predictiveColumns;
    }

    public static Config load(String dataSetName) {
        String filename = "model/"+dataSetName+"/config.json";
        String URL = Config.class.getClassLoader().getResource(filename).getPath();
        Path path = Paths.get(URL);
        try {
            return new Gson().fromJson(new String(Files.readAllBytes(path)), Config.class);
        } catch (IOException e) {
            throw new RuntimeException("Could not read data from file");
        }
    }
}
