import javafx.util.Pair;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MyCanvas extends Canvas {
    private MyPole myPole = new MyPole(50,50);
    private EnemyPole enemyPole = new EnemyPole(500, 50);
    private EnemyShipsView enemyShipsView = new EnemyShipsView(50, 500);
    private ComputerPlayer computerPlayer;

    MyCanvas(Game game) {
        myPole.setPlayer(game.getMyPlayer());
        enemyPole.setPlayer(game.getEnemyPlayer());
        computerPlayer = new ComputerPlayer(game.getMyPlayer());
    }

    @Override
    public void paint(Graphics graphics) {
        enemyShipsView.markKilledShips(enemyPole.getPlayer().getShips());
        enemyPole.draw(graphics);
        myPole.draw(graphics);
        enemyShipsView.draw(graphics);
    }

    public void clicked(MouseEvent e) {
        Boolean ITurnAgain;
        if (mouseInsideEnemyPole(e.getX(), e.getY())) {
            int _x = (e.getX() - enemyPole.getStartX())/Constants.cellWidth;
            int _y = (e.getY() - enemyPole.getStartY())/Constants.cellHeight;
            //System.out.println("mouse.x = " + _x);
            //System.out.println("mouse.y = " + _y);
            ITurnAgain = enemyPole.getPlayer().turn(new Pair<>(_x, _y));
            paint(getGraphics());
            if(ITurnAgain){
                Boolean lose = enemyPole.getPlayer().checkPlayerLose();
                if(lose){
                   System.out.println("Ты победил!");
                }
            }else{
                computerTurn();
            }
        }
    }

    private void computerTurn() {
        Boolean enemyTurnAgain;
        Pair<Integer, Integer> enemyShut = computerPlayer.getTurn();
        enemyTurnAgain = myPole.getPlayer().turn(enemyShut);
        paint(getGraphics());
        Boolean lose = myPole.getPlayer().checkPlayerLose();
        if(lose){
            System.out.println("Ты проиграл!");
            return;
        }
        if(enemyTurnAgain){
            computerTurn();
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
