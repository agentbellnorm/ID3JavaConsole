import algorithm.Id3Performer;
import algorithm.Id3Result;
import model.Model;

public class Main {

    public static void main(String[] args) {
        String dataPath = "model/golf/data.txt";
        String successAttribute = "Play";
        String positiveOutcome = "Yes";
        String negativeOutcome = "No";

        Model golfModel = new Model(dataPath, successAttribute, positiveOutcome, negativeOutcome);
        Id3Performer id3Performer = new Id3Performer(golfModel);
        Id3Result id3Result = id3Performer.perform();
        System.out.println(id3Result.printResult());


    }
}
