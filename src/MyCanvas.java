import javafx.util.Pair;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MyCanvas extends Canvas {
    private MyPole myPole = new MyPole(50,50);
    private EnemyPole enemyPole = new EnemyPole(500, 50);
    private EnemyShipsView enemyShipsView = new EnemyShipsView(50, 500);

    MyCanvas(Game game) {
        myPole.setPlayer(game.getMyPlayer());
        enemyPole.setPlayer(game.getEnemyPlayer());
    }

    @Override
    public void paint(Graphics graphics) {
        enemyShipsView.markKilledShips(enemyPole.getPlayer().getShips());
        enemyPole.draw(graphics);
        myPole.draw(graphics);
        enemyShipsView.draw(graphics);
    }

    public void clicked(MouseEvent e) {
        Boolean playerTurnAgain;
        if (mouseInsideEnemyPole(e.getX(), e.getY())) {
            int _x = (e.getX() - enemyPole.getStartX())/Constants.cellWidth;
            int _y = (e.getY() - enemyPole.getStartY())/Constants.cellHeight;
            System.out.println("mouse.x = " + _x);
            System.out.println("mouse.y = " + _y);
            playerTurnAgain = enemyPole.getPlayer().turn(new Pair<>(_x, _y));
            paint(getGraphics());
            if(playerTurnAgain){
                Boolean lose = enemyPole.getPlayer().checkPlayerLose();
                if(lose){
                   System.out.println("Ты победил!");
                }
            }
        }
    }
    private boolean mouseInsideEnemyPole(int x, int y){
        return (x > enemyPole.getStartX()
                && x <= enemyPole.getStartX() + Constants.cellWidth * Constants.xCellsNumber)
                &&
                (y > enemyPole.getStartY()
                        && y <= enemyPole.getStartY() + Constants.cellHeight * Constants.yCellsNumber);
    }
}
