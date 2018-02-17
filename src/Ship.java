import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Ship {
    private int numberOfDeck;
    private List<Pair<Integer, Integer>> hottedDeck = new ArrayList<>();
    private int dx;
    private int dy;
    private int x;
    private int y;


    public Ship(int numberOfDeck) {
        this.numberOfDeck = numberOfDeck;
    }

    public void setOrientation(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
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

    public List<Pair<Integer, Integer>> getShipCells() {
        List<Pair<Integer, Integer>> cells = new ArrayList<>();
        for (int i = 0; i < numberOfDeck; i++) {
            cells.add(new Pair(x + dx * i, y + dy * i));
        }
        return cells;
    }

    public List<Pair<Integer, Integer>> getAllBusyCells() {
        List<Pair<Integer, Integer>> allBusyCells = new ArrayList<>();
        for (int x = this.x - 1; x <= this.x + dx * numberOfDeck + 1 - dx; x++) {
            for (int y = this.y - 1; y <= this.y + dy * numberOfDeck + 1 - dy; y++) {
                if (coordinatesInsidePole(x, y)) {
                    allBusyCells.add(new Pair<>(x, y));
                }
            }
        }
        return allBusyCells;
    }

    public void addHottedDeck(Pair<Integer, Integer> hotted) {
        hottedDeck.add(hotted);
    }

    public boolean isHottedAlready(Pair<Integer, Integer> shut) {
        return Utils.samePairInListPresent(hottedDeck, shut);
    }

    public Boolean killed() {
        return numberOfDeck == hottedDeck.size();
    }

    public List<Pair<Integer, Integer>> getHottedDecks() {
        return hottedDeck;
    }

    public List<Pair<Integer, Integer>> getLifeDecks() {
        List<Pair<Integer, Integer>> shipCells = getShipCells();
        List<Pair<Integer, Integer>> lifeDecks = new ArrayList<>();
        for (int i = 0; i < shipCells.size(); i++) {
            if (!Utils.samePairInListPresent(hottedDeck, shipCells.get(i))) {
                lifeDecks.add(shipCells.get(i));
            }
        }
        return lifeDecks;
    }

    private boolean coordinatesInsidePole(int x, int y) {
        return x >= 0 && x <= Pole.WIDTH && y >= 0 && y <= Pole.HEIGHT;
    }
}
