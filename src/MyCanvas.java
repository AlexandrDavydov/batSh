import javafx.util.Pair;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyCanvas extends Canvas {
    private MyPole myPole = new MyPole(50,50);
    private EnemyPole enemyPole = new EnemyPole(500, 50);
    private EnemyShipsView enemyShipsView = new EnemyShipsView(50, 500);
    private ComputerPlayer computerPlayer;
    private MouseListener mouseListener;
    private Boolean mouseActive = true;

    MyCanvas(Game game) {
        myPole.setPlayer(game.getMyPlayer());
        enemyPole.setPlayer(game.getEnemyPlayer());
        computerPlayer = new ComputerPlayer(game.getMyPlayer());
        mouseListener = new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                if(mouseActive) {
                    clicked(e);
                }

            }
        };
        this.addMouseListener(this.mouseListener);
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

    private void pause(int milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    public void setMyTurn(boolean active){
        if(!active) {
            myPole.setMyTurn(true);
            enemyPole.setMyTurn(false);
        }else{
            myPole.setMyTurn(false);
            enemyPole.setMyTurn(true);
        }
        paint(getGraphics());
    }

    private void computerTurn() {
        setMyTurn(false);
        mouseActive = false;
        Boolean enemyTurnAgain;
        Pair<Integer, Integer> enemyShut = computerPlayer.getTurn();
        pause(500);
        enemyTurnAgain = myPole.getPlayer().turn(enemyShut);
        paint(getGraphics());
        Boolean lose = myPole.getPlayer().checkPlayerLose();
        if(lose){
            System.out.println("Ты проиграл!");
            enemyPole.drawShipsLifeDecks(getGraphics(), enemyPole.getPlayer().getShips());
            return;
        }
        if(enemyTurnAgain){
            computerTurn();
        }
        mouseActive = true;
        setMyTurn(true);
    }

    private boolean mouseInsideEnemyPole(int x, int y){
        return (x > enemyPole.getStartX()
                && x <= enemyPole.getStartX() + Constants.cellWidth * Constants.xCellsNumber)
                &&
                (y > enemyPole.getStartY()
                        && y <= enemyPole.getStartY() + Constants.cellHeight * Constants.yCellsNumber);
    }
}
