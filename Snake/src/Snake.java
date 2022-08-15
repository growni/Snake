import static Utils.Values.*;

public class Snake {

    public Snake() {

    }

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            xPossition[i] = xPossition[i - 1];
            yPossition[i] = yPossition[i - 1];
            // System.out.printf("x: %d, y: %d \n", x[i], y[i]);
        }
        switch (direction) {
            case 'U':
                yPossition[0] = yPossition[0] - UNIT_SIZE;
                break;
            case 'D':
                yPossition[0] = yPossition[0] + UNIT_SIZE;
                break;
            case 'L':
                xPossition[0] = xPossition[0] - UNIT_SIZE;
                break;
            case 'R':
                xPossition[0] = xPossition[0] + UNIT_SIZE;
                break;
        }
    }
}
