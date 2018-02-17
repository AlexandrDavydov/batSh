import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EnemyShipsView extends BasePole {
    private List<Ship> ships = new ArrayList<>();

    EnemyShipsView(int startX, int startY) {
        super(startX, startY);
        initShips();
    }

    public void markKilledShips(List<Ship> enemyShips) {
        for (int i = 0; i < enemyShips.size(); i++) {
            if (enemyShips.get(i).killed()) {
                ships.get(i).setAsKilled();
            }
        }
    }

    private void initShips() {
        Ship ship = new Ship(4);
        ship.setOrientation(1, 0);
        ship.setX(0);
        ship.setY(0);
        ships.add(ship);

        ship = new Ship(3);
        ship.setOrientation(1, 0);
        ship.setX(5);
        ship.setY(0);
        ships.add(ship);

        ship = new Ship(3);
        ship.setOrientation(1, 0);
        ship.setX(9);
        ship.setY(0);
        ships.add(ship);

        ship = new Ship(2);
        ship.setOrientation(1, 0);
        ship.setX(13);
        ship.setY(0);
        ships.add(ship);

        ship = new Ship(2);
        ship.setOrientation(1, 0);
        ship.setX(0);
        ship.setY(2);
        ships.add(ship);

        ship = new Ship(2);
        ship.setOrientation(1, 0);
        ship.setX(3);
        ship.setY(2);
        ships.add(ship);

        ship = new Ship(1);
        ship.setOrientation(1, 0);
        ship.setX(6);
        ship.setY(2);
        ships.add(ship);

        ship = new Ship(1);
        ship.setOrientation(1, 0);
        ship.setX(8);
        ship.setY(2);
        ships.add(ship);

        ship = new Ship(1);
        ship.setOrientation(1, 0);
        ship.setX(10);
        ship.setY(2);
        ships.add(ship);

        ship = new Ship(1);
        ship.setOrientation(1, 0);
        ship.setX(12);
        ship.setY(2);
        ships.add(ship);
    }

    @Override
    public void draw(Graphics graphics) {
        drawShipsLifeDecks(graphics, ships);
        drawShipsKilledDecks(graphics, ships);
    }
}
