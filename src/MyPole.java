import javafx.util.Pair;

import java.awt.*;
import java.util.List;

public class MyPole extends BasePole {
    public MyPole(int startX, int startY) {
        super(startX, startY);
    }

    public void draw(Graphics graphics) {
        super.draw(graphics);
        drawLifeDecks(graphics);
    }
}
