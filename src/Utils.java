import javafx.util.Pair;

import java.util.List;

public class Utils {
    static final public String[] upLine = "1,2,3,4,5,6,7,8,9,10".split(",");
    //static final public String[] upLine = "А,Б,В,Г,Д,Е,Ж,З,И,К".split(",");
    static public boolean ListsHasSamePairs(List<Pair<Integer, Integer>> l1, List<Pair<Integer, Integer>> l2){
        for(int i=0; i<l1.size(); i++){
            if(samePairInListPresent(l2, l1.get(i))){
                return true;
            }
        }
        return false;
    }
    static public boolean samePairInListPresent(List<Pair<Integer, Integer>> pairList, Pair<Integer, Integer> pair){
        for(int i=0; i<pairList.size(); i++){
            if(pairList.get(i).equals(pair)){
                return true;
            }
        }
        return false;
    }

    static public Boolean pairInsidePole(Pair<Integer, Integer> coordinates){
        if(coordinates.getKey() >=0 && coordinates.getKey() < Constants.xCellsNumber){
            if(coordinates.getValue() >=0 && coordinates.getValue() < Constants.yCellsNumber){
                return true;
            }
        }
        return false;
    }
}
