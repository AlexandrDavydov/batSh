
import javafx.util.Pair;

import java.awt.*;
import java.util.List;

public class BasePole {
    private int startX;
    private int startY;
    private Player player;

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public Player getPlayer() {
        return player;
    }

    BasePole(int startX, int startY) {
        this.startX = startX;
        this.startY = startY;
    }

    private void drawLifeDeck(int x, int y, int w, int h, Graphics graphics) {
        for (int i = 0; i < 3; i++) {
            graphics.drawRect(x + i, y + i, w - (i * 2), h - (i * 2));
        }
    }

    private void drawKilledDeck(int x, int y, int w, int h, Graphics graphics) {
        for (int i = 0; i < 3; i++) {
            graphics.drawRect(x + i, y + i, w - (i * 2), h - (i * 2));
        }
        graphics.drawLine(x, y, x + w, y + h);
        graphics.drawLine(x + 1, y, x + w, y + h - 1);
        graphics.drawLine(x, y + 1, x + w - 1, y + h);

        graphics.drawLine(x + w, y, x, y + h);
        graphics.drawLine(x + w - 1, y, x, y + h - 1);
        graphics.drawLine(x + w, y - 1, x - 1, y + h);


    }

    private void drawShutCircle(int x, int y, int w, int h, Graphics graphics) {
        graphics.fillOval(x, y, w, h);

    }

    private void drawMissingShuts(Graphics graphics, List<Pair<Integer, Integer>> missingShuts) {
        for (Pair<Integer, Integer> missingShut : missingShuts) {
            drawShutCircle(startX + missingShut.getKey() * Constants.cellWidth + Constants.cellWidth / 2 - 5,
                    startY + missingShut.getValue() * Constants.cellHeight + Constants.cellHeight / 2 - 5,
                    10, 10, graphics);
        }
    }

    private void drawCells(Graphics graphics) {
        for (int x = 0; x < Constants.xCellsNumber; x++) {
            for (int y = 0; y < Constants.yCellsNumber; y++) {
                graphics.drawRect(
                        startX + x * Constants.cellWidth,
                        startY + y * Constants.cellHeight,
                        Constants.cellWidth,
                        Constants.cellHeight);
            }
        }
    }

    void drawPlayerLifeDecks(Graphics graphics) {
        List<Ship> ships = getPlayer().getShips();
        drawShipsLifeDecks(graphics, ships);
    }

    void drawShipsLifeDecks(Graphics graphics, List<Ship> ships) {
        for (Ship ship : ships) {
            drawLifeDeck(ship.getLifeDecks(), graphics);
        }
    }

    private void drawKilledDecks(Graphics graphics) {
        List<Ship> ships = getPlayer().getShips();
        drawShipsKilledDecks(graphics, ships);
    }

    void drawShipsKilledDecks(Graphics graphics, List<Ship> ships) {
        for (Ship ship : ships) {
            drawKilledDeck(ship.getHottedDecks(), graphics);
        }
    }

    private void drawKilledDeck(List<Pair<Integer, Integer>> shipCells, Graphics graphics) {
        for (Pair<Integer, Integer> shipCell : shipCells) {
            drawKilledDeck(startX + shipCell.getKey() * Constants.cellWidth,
                    startY + shipCell.getValue() * Constants.cellHeight,
                    Constants.cellWidth, Constants.cellHeight, graphics);
        }
    }

    private void drawLifeDeck(List<Pair<Integer, Integer>> shipCells, Graphics graphics) {
        for (Pair<Integer, Integer> shipCell : shipCells) {
            drawLifeDeck(startX + shipCell.getKey() * Constants.cellWidth,
                    startY + shipCell.getValue() * Constants.cellHeight,
                    Constants.cellWidth, Constants.cellHeight, graphics);
        }
    }

    public void draw(Graphics graphics) {
        drawCells(graphics);
        drawMissingShuts(graphics, getPlayer().getMissingShuts());
        drawKilledDecks(graphics);
    }
}
