package app.life;

import java.awt.Color;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Vasiliy.Andricov
 */
public class LifeWorld {

    private final int worldWigth;
    private final int worldHeigth;
    private final Item[][] arrayWorld;
    private final int itemWigth;
    private final int itemHeigth;
    private long stepNum = 0;

    public LifeWorld(int worldWigth, int worldHeigth, int itemWigth, int itemHeigth) {
        this.worldWigth = worldWigth;
        this.worldHeigth = worldHeigth;
        this.arrayWorld = new Item[worldWigth][worldHeigth];
        this.itemWigth = itemWigth;
        this.itemHeigth = itemHeigth;
    }

    public Item[][] nextStep() {
        if (stepNum == 0) {
            initLife();            
        } else {
            nextWorldStep();
        };
        stepNum++;
        return this.arrayWorld;
    }

    private void initLife() {
        Random random = new Random();
        for (int i = 0; i < worldWigth; i++) {
            for (int j = 0; j < worldHeigth; j++) {
                if (random.nextInt() < 1) {
                    arrayWorld[i][j] = new Item(Color.GREEN);
                } else {
                    arrayWorld[i][j] = new Item(Color.GRAY);
                }
            }
        }
        updateWorldPoint();
    }

    private void nextWorldStep() {
        System.out.printf("Шаг игры %d\n", stepNum);
        updateWorldPoint();
    }

    private void updateWorldPoint() {
        for (int i = 0; i < worldWigth; i++) {
            for (int j = 0; j < worldHeigth; j++) {
                List<Point> pointList = Stream.of(new Point(i - 1, j - 1), new Point(i - 1, j), new Point(i - 1, j + 1),
                        new Point(i, j - 1), new Point(i, j + 1),
                        new Point(i + 1, j - 1), new Point(i + 1, j), new Point(i + 1, j + 1)
                )
                        .filter(t -> ((t.getI() >= 0) && (t.getJ() >= 0) && (t.getI() < worldWigth) && (t.getJ() < worldHeigth)))
                        .map(t -> {
                            t.setColor(arrayWorld[t.getI()][t.getJ()].getColor());
                            return t;
                        })
                        .collect(Collectors.toList());
                arrayWorld[i][j].getPointList().addAll(pointList);
            }
        }
    }

    /**
     * 
     */
    private void updateWorldLife(){
    
    }
}
