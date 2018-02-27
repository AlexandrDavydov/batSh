import javafx.util.Pair;

import java.awt.*;
import java.util.List;

public class BasePole {
    private int startX;
    private int startY;
    private Player player;
    private boolean myTurn;

    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }

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

    private void drawMissingShuts(Graphics graphics, List<Point> missingShuts) {
        graphics.setColor(Color.GRAY);
        for (Point missingShut : missingShuts) {
            drawShutCircle(startX + missingShut.x * Constants.cellWidth + Constants.cellWidth / 2 - 5,
                    startY + missingShut.y * Constants.cellHeight + Constants.cellHeight / 2 - 5,
                    10, 10, graphics);
        }
    }

    private void drawCells(Graphics graphics) {
        graphics.setColor(Color.GRAY);
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

    public void drawPlayerLifeDecks(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        List<Ship> ships = getPlayer().getShips();
        drawShipsLifeDecks(graphics, ships);
    }

    public void drawShipsLifeDecks(Graphics graphics, List<Ship> ships) {
        for (Ship ship : ships) {
            drawLifeDeck(ship.getLifeDecks(), graphics);
        }
    }

    private void drawKilledDecks(Graphics graphics) {
        List<Ship> ships = getPlayer().getShips();
        drawShipsKilledDecks(graphics, ships);
    }

    public void drawShipsKilledDecks(Graphics graphics, List<Ship> ships) {
        for (Ship ship : ships) {
            if(ship.killed()){
                graphics.setColor(Color.BLACK);
            }else{
                graphics.setColor(Color.RED);
            }
            drawKilledDeck(ship.getHottedDecks(), graphics);
        }
    }

    private void drawKilledDeck(List<Point> shipCells, Graphics graphics) {
        for (Point shipCell : shipCells) {
            drawKilledDeck(startX + shipCell.x * Constants.cellWidth,
                    startY + shipCell.y * Constants.cellHeight,
                    Constants.cellWidth, Constants.cellHeight, graphics);
        }
    }

    private void drawLifeDeck(List<Point> shipCells, Graphics graphics) {
        for (Point shipCell : shipCells) {
            drawLifeDeck(startX + shipCell.x * Constants.cellWidth,
                    startY + shipCell.y * Constants.cellHeight,
                    Constants.cellWidth, Constants.cellHeight, graphics);
        }
    }

    public void setPoleActive(Graphics graphics){
        graphics.setColor(Color.RED);
        int x = startX - 5;
        int y = startY - 5;
        int width = Constants.xCellsNumber * Constants.cellWidth + 10;
        int height = Constants.yCellsNumber * Constants.cellHeight + 10;
        graphics.drawRect(x, y, width, height);
        graphics.drawRect(x+1, y+1, width-2, height-2);
    }

    public void draw(Graphics graphics) {
        hideRamka(graphics);
        if(myTurn){
            setPoleActive(graphics);
        }
        drawCells(graphics);
        drawMissingShuts(graphics, getPlayer().getMissingShuts());
        drawKilledDecks(graphics);
    }

    private void hideRamka(Graphics graphics){
        int x = startX - 5;
        int y = startY - 5;
        int width = Constants.xCellsNumber * Constants.cellWidth + 11;
        int height = Constants.yCellsNumber * Constants.cellHeight + 11;
        graphics.clearRect(x, y, width, 4);
        graphics.clearRect(x, y, 4, height);
        graphics.clearRect(x + width - 4, y, 4, height);
        graphics.clearRect(x, y + height -4, width, y + height);
    }
}
