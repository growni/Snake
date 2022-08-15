package Utils;

import javax.swing.*;
import java.util.Random;

public final class Values {

    private Values() {

    }

    public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 600;
    public static final int UNIT_SIZE = 25;
    public static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    public static int DELAY = 100;
    public static int applesEaten = 0;
    public static Random random;
    public static int bodyParts = 6;
    public static final int xPossition[] = new int[GAME_UNITS];
    public static Timer timer;
    public static final int yPossition[] = new int[GAME_UNITS];
    public static char direction = 'R';
    public static boolean running = false;
}
