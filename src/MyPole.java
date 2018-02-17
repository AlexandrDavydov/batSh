import java.awt.*;

public class MyPole extends BasePole {
    public MyPole(int startX, int startY) {
        super(startX, startY);
    }

    public void draw(Graphics graphics) {
        super.draw(graphics);
        drawLifeDecks(graphics);
    }
}
