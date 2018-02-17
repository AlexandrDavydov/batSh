import java.util.ArrayList;
import java.util.List;

public final class Squadron {
    static List<Integer> squadron;

    // All player ships (4, 1) 4 - number of decks, 1 - number of ships
    public static List<Integer> get() {
        if (squadron == null) {
            squadron = new ArrayList<Integer>();
            squadron.add(4);
            squadron.add(3);
            squadron.add(3);
            squadron.add(2);
            squadron.add(2);
            squadron.add(2);
            squadron.add(1);
            squadron.add(1);
            squadron.add(1);
            squadron.add(1);
        }
        return squadron;
    }
}
