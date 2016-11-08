import algorithm.ID3Performer;
import algorithm.ID3Result;
import model.Model;

public class Main {

    public static void main(String[] args) {
        String dataPath = "model/golf/data.txt";
        String successAttribute = "Play";
        String successValue = "Yes";

        Model golfModel = new Model(dataPath, successAttribute, successValue);
        ID3Performer id3Performer = new ID3Performer(golfModel);
        ID3Result id3Result = id3Performer.perform();
        System.out.println(id3Result.printResult());


    }
}
