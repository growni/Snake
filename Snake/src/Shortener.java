import java.util.Random;

import static Utils.Values.*;

public class Shortener {
    Random random = new Random();
    private int x = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
    private int y = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;

    public Shortener() {
        setX(x);
        setY(y);
    }

    public boolean check() {
        if (xPossition[0] == getX() && yPossition[0] == getY()) {
            bodyParts /= 2;
            applesEaten++;
            return true;
        }
        return false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
