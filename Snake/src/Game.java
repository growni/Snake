import javax.swing.*;
import java.awt.*;
import static Utils.Values.*;
import static Utils.Values.UNIT_SIZE;


public class Game extends JPanel {



    public Game() {

    }

    public void draw(Graphics g) {

        if (running) {
            g.setColor(Color.red);
            g.fillOval(GamePanel.apple.getX(), GamePanel.apple.getY(), UNIT_SIZE, UNIT_SIZE);

            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(xPossition[i], yPossition[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(new Color(45, 180, 0));
                    g.fillRect(xPossition[i], yPossition[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            g.setColor(Color.red);
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());
        } else {
            gameOver(g);
        }
        //visualize shortener
        if (applesEaten % 7 == 0 && applesEaten > 0 && GamePanel.shortener == null) {
            GamePanel.shortener = new Shortener();
        }
        if(GamePanel.shortener != null) {
            g.setColor(Color.orange);
            g.fillOval(GamePanel.shortener.getX(), GamePanel.shortener.getY(), UNIT_SIZE, UNIT_SIZE);
        }
    }

    public void gameOver(Graphics g) {
        //Score text
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());

        //Game Over text
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);
    }

    public void checkCollisions() {
        //check if head collides with body
        for (int i = bodyParts; i > 0; i--) {

            if ((xPossition[0] == xPossition[i]) && (yPossition[0] == yPossition[i])) {
                running = false;
                System.out.printf("Snake bit itself at: \nx[0] = %d, x[i] = %d \ny[0] = %d, y[i] = %d", xPossition[0], xPossition[i], yPossition[0], yPossition[i]);
            }
        }

        //check if head touches left border
        if (xPossition[0] < 0) {
            xPossition[0] = SCREEN_WIDTH - UNIT_SIZE;
        }
        //check if head touches right border
        if (xPossition[0] >= SCREEN_WIDTH) {
            xPossition[0] = 0;
        }
        //check if head touches top border
        if (yPossition[0] < 0) {
            yPossition[0] = SCREEN_HEIGHT - UNIT_SIZE;
        }
        //check if head touches bottom border
        if (yPossition[0] >= SCREEN_HEIGHT) {
            yPossition[0] = 0;
        }
        if (!running) {
            timer.stop();
        }
    }

}
