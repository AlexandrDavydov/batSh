
import javafx.util.Pair;

import java.awt.*;
import java.util.List;

public class BasePole {
    int startX;
    int startY;
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

    public BasePole(int startX, int startY) {
        this.startX = startX;
        this.startY = startY;
    }

    protected void drawLifeDeck(int x, int y, int w, int h, Graphics graphics) {
        for (int i = 0; i < 3; i++) {
            graphics.drawRect(x + i, y + i, w - (i * 2), h - (i * 2));
        }
    }

    protected void drawKilledDeck(int x, int y, int w, int h, Graphics graphics) {
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

    protected void drawShutCircle(int x, int y, int w, int h, Graphics graphics) {
        graphics.fillOval(x, y, w, h);

    }

    public void drawMissingShuts(Graphics graphics, List<Pair<Integer, Integer>> missingShuts) {
        for (int i = 0; i < missingShuts.size(); i++) {
            drawShutCircle(startX + missingShuts.get(i).getKey() * Constants.cellWidth + Constants.cellWidth / 2 - 5,
                    startY + missingShuts.get(i).getValue() * Constants.cellHeight + Constants.cellHeight / 2 - 5,
                    10, 10, graphics);
        }
    }

    public void drawCells(Graphics graphics) {
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

    public void drawLifeDecks(Graphics graphics) {
        List<Ship> ships = getPlayer().getShips();
        for (int i = 0; i < ships.size(); i++) {
            drawLifeDeck(ships.get(i).getLifeDecks(), graphics);
        }
    }

    public void drawKilledDecks(Graphics graphics) {
        List<Ship> ships = getPlayer().getShips();
        for (int i = 0; i < ships.size(); i++) {
            drawKilledDeck(ships.get(i).getHottedDecks(), graphics);
        }
    }

    private void drawKilledDeck(List<Pair<Integer, Integer>> shipCells, Graphics graphics) {
        for (int i = 0; i < shipCells.size(); i++) {
            drawKilledDeck(startX + shipCells.get(i).getKey() * Constants.cellWidth,
                    startY + shipCells.get(i).getValue() * Constants.cellHeight,
                    Constants.cellWidth, Constants.cellHeight, graphics);
        }
    }

    private void drawLifeDeck(List<Pair<Integer, Integer>> shipCells, Graphics graphics) {
        for (int i = 0; i < shipCells.size(); i++) {
            drawLifeDeck(startX + shipCells.get(i).getKey() * Constants.cellWidth,
                    startY + shipCells.get(i).getValue() * Constants.cellHeight,
                    Constants.cellWidth, Constants.cellHeight, graphics);
        }
    }

    public void draw(Graphics graphics) {
        drawCells(graphics);
        drawMissingShuts(graphics, getPlayer().getMissingShuts());
        drawKilledDecks(graphics);
    }
}
