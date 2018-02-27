import javafx.util.Pair;

import java.awt.*;
import java.util.List;

public class GameProperty {
    private List<Ship> ships;
    private List<Point> missingShuts;

    public GameProperty(List<Ship> ships, List<Point> missingShuts) {
        this.ships = ships;
        this.missingShuts = missingShuts;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public List<Point> getMissingShuts() {
        return missingShuts;
    }
}
