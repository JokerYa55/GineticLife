package app.life;

import java.awt.Color;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author vasil
 */
@Data
@AllArgsConstructor
public class Point {

    private int i;
    private int j;
    private Color color;

    public Point(int i, int j) {
        this.i = i;
        this.j = j;
    }

}
