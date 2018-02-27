import javafx.util.Pair;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Ship {
    private int numberOfDeck;
    private List<Point> hottedDeck = new ArrayList<>();
    private int dx;
    private int dy;
    private int x;
    private int y;


    Ship(int numberOfDeck) {
        this.numberOfDeck = numberOfDeck;
    }

    public void setOrientation(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public boolean isVerticalOrientation(){
        return dx == 0;
    }

    public Boolean isHotted(){
        return numberOfDeck != hottedDeck.size() && hottedDeck.size() > 0;
    }

    public Integer getMaxXHottedCoordinate(){
        Integer maxNumber = 0;
        for(int i=0; i<hottedDeck.size(); i++){
            if(hottedDeck.get(i).x > maxNumber){
                maxNumber = hottedDeck.get(i).x;
            }
        }
        return maxNumber;
    }

    public Integer getMaxYHottedCoordinate(){
        Integer maxNumber = 0;
        for(int i=0; i<hottedDeck.size(); i++){
            if(hottedDeck.get(i).y > maxNumber){
                maxNumber = hottedDeck.get(i).y;
            }
        }
        return maxNumber;
    }

    public Integer getMinXHottedCoordinate(){
        Integer minNumber = 9;
        for(int i=0; i<hottedDeck.size(); i++){
            if(hottedDeck.get(i).x < minNumber){
                minNumber = hottedDeck.get(i).x;
            }
        }
        return minNumber;
    }

    public Integer getMinYHottedCoordinate(){
        Integer minNumber = 9;
        for(int i=0; i<hottedDeck.size(); i++){
            if(hottedDeck.get(i).y < minNumber){
                minNumber = hottedDeck.get(i).y;
            }
        }
        return minNumber;
    }

    public void setAsKilled() {
        List<Point> decks = getShipCells();
        hottedDeck = new ArrayList<>();
        for (Point deck : decks) {
            hottedDeck.add(new Point(deck.x, deck.y));
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return dx * numberOfDeck;
    }

    public int getHeight() {
        return dy * numberOfDeck;
    }

    public int getNumberOfDeck() {
        return numberOfDeck;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public List<Point> getShipCells() {
        List<Point> cells = new ArrayList<>();
        for (int i = 0; i < numberOfDeck; i++) {
            cells.add(new Point(x + dx * i, y + dy * i));
        }
        return cells;
    }

    public List<Point> getAllBusyCells() {
        List<Point> allBusyCells = new ArrayList<>();
        for (int x = this.x - 1; x <= this.x + dx * numberOfDeck + 1 - dx; x++) {
            for (int y = this.y - 1; y <= this.y + dy * numberOfDeck + 1 - dy; y++) {
                if (coordinatesInsidePole(x, y)) {
                    allBusyCells.add(new Point(x, y));
                }
            }
        }
        return allBusyCells;
    }

    public void addHottedDeck(Point hotted) {
        hottedDeck.add(hotted);
    }

    public boolean isHottedAlready(Point shut) {
        return Utils.samePairInListPresent(hottedDeck, shut);
    }

    public Boolean killed() {
        return numberOfDeck == hottedDeck.size();
    }

    public List<Point> getHottedDecks() {
        return hottedDeck;
    }

    public List<Point> getLifeDecks() {
        List<Point> shipCells = getShipCells();
        List<Point> lifeDecks = new ArrayList<>();
        for (Point shipCell : shipCells) {
            if (!Utils.samePairInListPresent(hottedDeck, shipCell)) {
                lifeDecks.add(shipCell);
            }
        }
        return lifeDecks;
    }

    private boolean coordinatesInsidePole(int x, int y) {
        return x >= 0 && x <= Constants.xCellsNumber && y >= 0 && y <= Constants.yCellsNumber;
    }
}
