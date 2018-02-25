import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class ComputerPlayer {
    private Player enemy;

    public ComputerPlayer(Player enemy) {
        this.enemy = enemy;
    }

    public Pair<Integer, Integer> getTurn(){
        Integer x;
        Integer y;
        Ship hottedShip = hottedShipPresent(enemy.getShips());
        if(hottedShip == null){
            List<Pair<Integer, Integer>> freeCells = getRandomFreeCells();
            if(freeCells.size() > 1){
                int index = Math.toIntExact(Math.round(Math.random()*(freeCells.size()-1)));
                x =freeCells.get(index).getKey();
                y =freeCells.get(index).getValue();
            }else{
                x = 0;
                y = 0;
            }
        }else{
            List<Pair<Integer, Integer>> expectedCells = getExpectedCellsToTurn(hottedShip);
            Integer index = 0;
            if(expectedCells.size()>1){
                index = Math.toIntExact(Math.round(Math.random()*(expectedCells.size()-1)));
            }
            x=expectedCells.get(index).getKey();
            y=expectedCells.get(index).getValue();
        }
        return new Pair<>(x,y);
    }

    private List<Pair<Integer, Integer>> getRandomFreeCells(){
        List<Pair<Integer, Integer>> freeCells = new ArrayList<>();
        for(int x=0; x<Constants.xCellsNumber; x++){
            for(int y=0; y<Constants.yCellsNumber; y++){
                Pair<Integer, Integer> possibleTurn = new Pair<>(x, y);
                if(!Utils.samePairInListPresent(enemy.getMissingShuts() ,possibleTurn)){
                    if(notKilledDeck(possibleTurn)){
                        freeCells.add(possibleTurn);
                    }
                }
            }
        }
        return freeCells;
    }

    private boolean notKilledDeck(Pair<Integer, Integer> turn){
        List<Ship> ships = enemy.getShips();
        for(int i=0; i<ships.size(); i++){
            List<Pair<Integer, Integer>> killedDecks = ships.get(i).getHottedDecks();
            if(Utils.samePairInListPresent(killedDecks, turn)){
                return false;
            }
        }
        return true;
    }

    private List<Pair<Integer, Integer>> getCellsAround(){
        List<Pair<Integer, Integer>> cellsAround = new ArrayList<>();
        cellsAround.add(new Pair<>(0,-1));
        cellsAround.add(new Pair<>(1,0));
        cellsAround.add(new Pair<>(0,1));
        cellsAround.add(new Pair<>(-1,0));
        return cellsAround;
    }

    private List<Pair<Integer, Integer>> getExpectedCellsToTurn(Ship ship){
        Pair<Integer, Integer> expectedTurn;
        List<Pair<Integer, Integer>> expectedCells = new ArrayList<>();
        List<Pair<Integer, Integer>> cellsAround = getCellsAround();
        if(ship.getHottedDecks().size() == 1){//
            for(int i=0; i<cellsAround.size(); i++){
                expectedTurn = new Pair<>(
                        ship.getHottedDecks().get(0).getKey() + cellsAround.get(i).getKey(),
                        ship.getHottedDecks().get(0).getValue() + cellsAround.get(i).getValue());

                if(isItPossibleToShutHere(expectedTurn)){
                    expectedCells.add(expectedTurn);
                }
            }
        }else{
            if(ship.isVerticalOrientation()){
                expectedTurn = new Pair<>(ship.getX(), ship.getMaxYHottedCoordinate() + 1);
                if(isItPossibleToShutHere(expectedTurn)) {
                    expectedCells.add(expectedTurn);
                }
                expectedTurn = new Pair<>(ship.getX(), ship.getMinYHottedCoordinate()-1);
                if(isItPossibleToShutHere(expectedTurn)) {
                    expectedCells.add(expectedTurn);
                }

            }else{
                expectedTurn = new Pair<>(ship.getMaxXHottedCoordinate()+1, ship.getY());
                if(isItPossibleToShutHere(expectedTurn)) {
                    expectedCells.add(expectedTurn);
                }

                expectedTurn = new Pair<>(ship.getMinXHottedCoordinate()-1, ship.getY());
                if(isItPossibleToShutHere(expectedTurn)) {
                    expectedCells.add(expectedTurn);
                }
            }
        }
        return expectedCells;
    }

    private boolean isItPossibleToShutHere(Pair<Integer, Integer> expectedTurn){
        if(cellFreeForTurn(expectedTurn) && Utils.pairInsidePole(expectedTurn)){
            return true;
        }
        return false;
    }

    private Boolean cellFreeForTurn(Pair<Integer, Integer> turn){
        List<Pair<Integer, Integer>> missingShuts = enemy.getMissingShuts();
        for(int i=0; i<missingShuts.size(); i++){
            if(Utils.samePairInListPresent(missingShuts, turn)){
                return false;
            }
        }
        return true;
    }

    private Ship hottedShipPresent(List<Ship> ships){
        for(int i=0; i<ships.size(); i++){
            if(ships.get(i).isHotted()){
                return ships.get(i);
            }
        }
        return null;
    }
}
