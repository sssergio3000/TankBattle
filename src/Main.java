import javax.swing.*;
import java.awt.*;

public class Main extends JPanel {
    final int BF_WIDTH = 576;
    final int BF_HEIGHT = 576;
    final int TANK_WIDTH = 50;
    final int TANK_HIGHT = 50;
    final int TANK_SPEED = 5;
    final int BULLET_SPEED = 2;


    //Direction: 1- Up, 2 - Down, 3 - Left, 4 - Right

    int direction = 2;
    int tankX = 256;
    int tankY = 256;

    int bulletX = 320;
    int bulletY = 320;

    void fire() throws Exception {
        bulletX = tankX + 19;
        bulletY = tankY + 19;
        while (bulletX > 0 && bulletX < BF_WIDTH && bulletY > 0 && bulletY < BF_HEIGHT) {
            switch (direction) {
                case 1: {
                    bulletY--;
                    break;
                }
                case 2: {
                    bulletY++;
                    break;
                }
                case 3: {
                    bulletX--;
                    break;
                }
                case 4: {
                    bulletX++;
                    break;
                }
            }
            Thread.sleep(BULLET_SPEED);
            repaint();
        }


    }


    void move(int direction) throws Exception {
        this.direction = direction;

        if (direction == 1) {
            tankY--;
        } else if (direction == 2) {
            tankY++;
        } else if (direction == 3) {
            tankX--;
        } else if (direction == 4) {
            tankX++;
        }
        Thread.sleep(TANK_SPEED);
        repaint();
    }

    void runTheGame() throws Exception {
        fire();
//        while (tankY < BF_HEIGHT-TANK_HIGHT) {
//            move(4);
//        }

    }

    void moveUp() throws Exception {
        direction = 1;
        while (tankY > 0) {
            tankY--;
            Thread.sleep(TANK_SPEED);
            repaint();
        }
    }

    void moveDown() throws Exception {
        direction = 2;
        while (tankY < (BF_HEIGHT - TANK_HIGHT)) {
            tankY++;
            Thread.sleep(TANK_SPEED);
            repaint();
        }
    }

    void moveLeft() throws Exception {
        direction = 3;
        while (tankX > 0) {
            tankX--;
            Thread.sleep(TANK_SPEED);
            repaint();
        }
    }

    void moveRight() throws Exception {
        direction = 4;
        while (tankX < BF_WIDTH - TANK_WIDTH) {
            tankX++;
            Thread.sleep(TANK_SPEED);
            repaint();
        }
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.runTheGame();
//        main.moveDown();
//        main.moveLeft();
//        main.moveRight();

    }

    Main() {
        JFrame frame = new JFrame("Dandy tanks");
        frame.setMinimumSize(new Dimension(BF_WIDTH + TANK_WIDTH, BF_HEIGHT + TANK_HIGHT));
        frame.getContentPane().add(this);
        frame.setLocation(100, 0);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GREEN);
        g.fillRect(tankX, tankY, TANK_WIDTH, TANK_HIGHT);
        g.setColor(Color.BLACK);

        switch (direction) {
            case 1: {
                g.fillRect(tankX + 20, tankY, 10, 30);
                break;
            }
            case 2: {
                g.fillRect(tankX + 20, tankY + 20, 10, 30);
                break;
            }
            case 3: {
                g.fillRect(tankX, tankY + 20, 30, 10);
                break;
            }
            case 4: {
                g.fillRect(tankX + 20, tankY + 20, 30, 10);
                break;
            }
        }
        g.setColor(Color.RED);
        g.fillRect(bulletX, bulletY, 12, 12);
    }
}
