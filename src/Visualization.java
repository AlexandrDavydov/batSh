import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Visualization extends JFrame{
    private MyCanvas myCanvas;
    private EnemyShipsView enemyShipsView;

    Visualization(Game game) {
        createMainWindow();
        myCanvas= new MyCanvas(game);
        enemyShipsView = new EnemyShipsView(50, 600);

        this.add(myCanvas);
        this.setVisible(true);
        startMouseListener();
    }

    private void createMainWindow() {
        this.setSize(1200, 800);
        this.setTitle("Морской бой");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public void startMouseListener() {
        myCanvas.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                myCanvas.clicked(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
}
