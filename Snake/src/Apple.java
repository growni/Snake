import java.util.Random;
import static Utils.Values.*;


public class Apple {
    Random random = new Random();
    private int x;
    private int y;

    public Apple() {
        setX();
        setY();
    }

    public boolean check() {

        if (applesEaten % 2 == 0 && applesEaten != 0) {
            //quicken the gameplay
            timer.setDelay(DELAY - applesEaten);
        }
        if ((xPossition[0] == getX()) && (yPossition[0] == getY())) {
            bodyParts++;
            applesEaten++;
            return true;
        }
        return false;
    }

    public int getX() {
        return x;
    }

    public void setX() {
        this.x = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
    }

    public int getY() {
        return y;
    }

    public void setY() {
        this.y = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;;
    }
}
