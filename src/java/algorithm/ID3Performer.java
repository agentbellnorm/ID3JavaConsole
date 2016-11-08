package algorithm;

import model.AttributeList;
import model.Model;
import model.Table;

/**
 * Created by Morgan on 2016-11-06.
 */
public class ID3Performer {

    private Model model;

    public ID3Performer(Model model) {
        this.model = model;
    }

    public ID3Result perform(){
        Table table = model.getTable();
        return new ID3Result(id3(table, table.getAttributes(), "root"));
    }

    private Node id3(Table table, AttributeList attributes, String branchLabel) {
        return new Node();
    }

}
