package app.life;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Vasiliy.Andricov
 */
@Data
public class Item {

    private Color color;
    private final List<Point> pointList = new ArrayList<>();

    public Item(Color color) {
        this.color = color;
    }

}
