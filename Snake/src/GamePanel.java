import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Arrays;
import java.util.Random;
import static Utils.Values.*;


public class GamePanel extends JPanel implements ActionListener {

    Apple apple;
    Shortener shortener;
    Snake snake;
    Random random;

    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(new Color(50, 50, 50));
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() {
        snake = new Snake();
        apple = new Apple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {

        if (running) {
//            for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
//                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
//                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
//            }
            g.setColor(Color.red);
            g.fillOval(apple.getX(), apple.getY(), UNIT_SIZE, UNIT_SIZE);

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
        if (applesEaten % 7 == 0 && applesEaten > 0 && shortener == null) {
            shortener = new Shortener();
        }
        if(shortener != null) {
            g.setColor(Color.orange);
            g.fillOval(shortener.getX(), shortener.getY(), UNIT_SIZE, UNIT_SIZE);
        }
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            snake.move();
            if(apple.check()) {
                apple = new Apple();
            }
            if(shortener != null && shortener.check()) {
                shortener = null;
            }
            checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    }
}
