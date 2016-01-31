package BrusselsMetro;

/**
 * Created by nic on 2016-01-26.
 */
import java.util.*;

public class MetroNode {
    String name;
    long lineNumber;
    ArrayList<MetroNode> neighbours;

    public MetroNode (String n, long lineNum, ArrayList<MetroNode> neighboursList) {
        name = n;
        lineNumber = lineNum;
        neighbours = neighboursList;
    }

    public String getName(){
        return name;
    }
    public long getLineNumber(){
        return lineNumber;
    }
    public ArrayList<MetroNode> getNeighbours(){
        return neighbours;
    }


}






