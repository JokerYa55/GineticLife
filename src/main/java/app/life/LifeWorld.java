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

    /**
     *
     * @return
     */
    public Item[][] nextStep() {
        if (stepNum == 0) {
            initLife();
        } else {
            nextWorldStep();
        };
        stepNum++;
        return this.arrayWorld;
    }

    /**
     *
     */
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

    /**
     *
     */
    private void updateWorldPoint() {
        for (int i = 0; i < worldWigth; i++) {
            for (int j = 0; j < worldHeigth; j++) {
                List<Point> pointList = Stream.of(new Point(i - 1, j - 1), new Point(i - 1, j), new Point(i - 1, j + 1),
                        new Point(i, j - 1), new Point(i, j + 1),
                        new Point(i + 1, j - 1), new Point(i + 1, j), new Point(i + 1, j + 1)
                )
                        .filter(t -> ((t.getI() >= 0) && (t.getJ() >= 0) && (t.getI() < worldWigth) && (t.getJ() < worldHeigth)))
                        .collect(Collectors.toList());
                arrayWorld[i][j].getPointList().addAll(pointList);
            }
        }
    }

    /**
     *
     */
    private void nextWorldStep() {
        System.out.printf("Шаг игры %d\n", stepNum);
        for (int i = 0; i < worldWigth; i++) {
            for (int j = 0; j < worldHeigth; j++) {
                // Проверка на смерть
                long lifePoint = arrayWorld[i][j].getPointList()
                        .stream()
                        .filter(t -> arrayWorld[t.getI()][t.getJ()].getColor() == Color.GREEN)
                        .count();
                // Умирает все соседи
                if (lifePoint > 3) {
                    arrayWorld[i][j].getPointList()
                        .stream()
                        .filter(t -> arrayWorld[t.getI()][t.getJ()].getColor() == Color.GREEN)
                            .forEach((t) -> {
                                arrayWorld[t.getI()][t.getJ()].setColor(Color.GRAY);
                            });
                    arrayWorld[i][j].setColor(Color.GRAY);
                } else if (lifePoint == 2) {
                    // Рождаем нового
                    Point newLifePoint = arrayWorld[i][j].getPointList()
                            .stream()
                            .filter(t -> arrayWorld[t.getI()][t.getJ()].getColor() == Color.GRAY)
                            .findAny().get();
                    arrayWorld[newLifePoint.getI()][newLifePoint.getJ()].setColor(Color.GREEN);
                }
            }
        }
    }

    /**
     *
     */
    public void printWorld() {
        for (int i = 0; i < worldWigth; i++) {
            String line = Stream.of(arrayWorld[i]).map(t -> t.getColor() == Color.GREEN ? "X" : "0").collect(Collectors.joining(" | ", " | ", " |"));
            System.out.println(line);
        }
    }
}
