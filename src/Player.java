import javafx.util.Pair;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Ship> ships = new ArrayList<>();
    private List<Point> missingShuts = new ArrayList<>();

    public Player() {
    }

    public List<Point> getMissingShuts() {
        return missingShuts;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void randomShips() {
        List<Integer> squadron = Squadron.get();
        for (int i = 0; i < squadron.size(); i++) {
            Ship ship;
            do {
                ship = new Ship(squadron.get(i));
                int dx = (int) Math.round(Math.random());
                ship.setOrientation(dx, 1 - dx);
                ship.setX((int) Math.round(Math.random() * (Constants.xCellsNumber - ship.getWidth() - 1)));
                ship.setY((int) Math.round(Math.random() * (Constants.yCellsNumber - ship.getHeight() - 1)));
            } while (shipsCrosses(ship, ships));
            ships.add(ship);
        }
    }

    boolean shipsCrosses(Ship ship, List<Ship> ships) {
        for (Ship ship1 : ships) {
            if (Utils.ListsHasSamePairs(ship.getShipCells(), ship1.getAllBusyCells())) {
                return true;
            }
        }
        return false;
    }

    public Boolean checkPlayerLose(){
        for (int i=0; i<ships.size(); i++){
            if(!ships.get(i).killed()){
                return false;
            }
        }
        return true;
    }

    private void addShutsAroundKilledShip(Ship ship){
        List<Point> busyCells = ship.getAllBusyCells();
        for(int i=0; i<busyCells.size(); i++){
            if(!Utils.samePairInListPresent(getMissingShuts(), busyCells.get(i))  &&
                    !Utils.samePairInListPresent(ship.getShipCells(), busyCells.get(i)) &&
                    Utils.pairInsidePole( busyCells.get(i))){
                missingShuts.add(busyCells.get(i));
            }
        }
    }

    public Boolean turn(Point shut) {
        Ship hattedShip = getHattedShip(shut);
        if(hattedShip != null){
            if(!hattedShip.isHottedAlready(shut)) {
                hattedShip.addHottedDeck(shut);
            }else{
                System.out.println("Уже ранен в эту клетку!");
                return false;
            }
            if(hattedShip.killed()){
                System.out.println("Убит!");
                addShutsAroundKilledShip(hattedShip);
            }else{
                System.out.println("Ранен!");
            }
            return true;
        }
        if(Utils.samePairInListPresent(missingShuts, shut)){
            System.out.println("Уже стрелял в эту клетку!");
        }else{
            System.out.println("Мимо!");
            missingShuts.add(shut);
        }
        return false;
    }

    private Ship getHattedShip(Point shut){
        for(int i=0; i<ships.size(); i++){
            if(Utils.samePairInListPresent(ships.get(i).getShipCells(), shut)){
                return ships.get(i);
            }
        }
        return null;
    }
}
