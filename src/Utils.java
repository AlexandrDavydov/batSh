import javafx.util.Pair;

import java.awt.*;
import java.util.List;

public class Utils {
    static final public String[] upLine = "1,2,3,4,5,6,7,8,9,10".split(",");
    //static final public String[] upLine = "А,Б,В,Г,Д,Е,Ж,З,И,К".split(",");
    static public boolean ListsHasSamePairs(List<Point> l1, List<Point> l2){
        for(int i=0; i<l1.size(); i++){
            if(samePairInListPresent(l2, l1.get(i))){
                return true;
            }
        }
        return false;
    }
    static public boolean samePairInListPresent(List<Point> pairList, Point point){
        for(int i=0; i<pairList.size(); i++){
            if(pairList.get(i).equals(point)){
                return true;
            }
        }
        return false;
    }

    static public Boolean pairInsidePole(Point coordinates){
        if(coordinates.x >=0 && coordinates.x < Constants.xCellsNumber){
            if(coordinates.y >=0 && coordinates.y < Constants.yCellsNumber){
                return true;
            }
        }
        return false;
    }
}
