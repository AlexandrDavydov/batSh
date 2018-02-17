import javafx.util.Pair;

import java.util.List;

public class GameProperty {
    private List<Ship> ships;
    private List<Pair<Integer, Integer>> missingShuts;

    public GameProperty(List<Ship> ships, List<Pair<Integer, Integer>> missingShuts) {
        this.ships = ships;
        this.missingShuts = missingShuts;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public List<Pair<Integer, Integer>> getMissingShuts() {
        return missingShuts;
    }
}
