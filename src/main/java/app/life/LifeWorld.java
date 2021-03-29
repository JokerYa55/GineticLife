package app.life;

import java.awt.Color;
import java.util.Random;

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
            initLife();
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
    }

}
