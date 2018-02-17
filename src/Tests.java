import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Tests {
    @Test
    public void shipsCrosses(){
        Ship ship = new Ship(4);
        ship.setOrientation(0, 1);
        ship.setX(7);
        ship.setY(2);
        List<Ship> ships = new ArrayList<>();
        ships.add(ship);

        ship = new Ship(3);
        ship.setOrientation(1, 0);
        ship.setX(6);
        ship.setY(3);

        Player p = new Player();
        boolean shipsDoNotCrosses = p.shipsCrosses(ship, ships);
        assert shipsDoNotCrosses == false;
        System.out.println("shipsCrosses = "+shipsDoNotCrosses);
    }

    @Test
    public void shipsNotCrosses(){
        Ship ship = new Ship(4);
        ship.setOrientation(0, 1);
        ship.setX(7);
        ship.setY(2);
        List<Ship> ships = new ArrayList<>();
        ships.add(ship);

        ship = new Ship(3);
        ship.setOrientation(0, 1);
        ship.setX(3);
        ship.setY(3);

        assert new Player().shipsCrosses(ship, ships);
    }
}
