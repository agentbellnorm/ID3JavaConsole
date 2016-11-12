import algorithm.Id3Performer;
import algorithm.Id3Result;
import model.Model;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {


        Model golfModel = new Model(getConfig("golf"));
        Id3Performer id3Performer = new Id3Performer(golfModel);
        Id3Result id3Result = id3Performer.perform();
        System.out.println(id3Result.printResult());


    }

    private static Map<String, String> getConfig(String model) {
        switch (model) {
            case "golf":
                return getGolfConfig();
            default:
                throw new RuntimeException("could not find config for: "+model);
        }

    }

    private static Map<String, String> getGolfConfig() {
        Map<String, String> config = new HashMap<>();
        config.put("filepath", "model/golf/data.txt");
        config.put("outcome", "Play");
        config.put("positiveoutcome", "Yes");
        config.put("negativeoutcome", "No");
        config.put("identifier", "Day");
        config.put("delimeter", ",");
        return config;
    }
}
