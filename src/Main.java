import javax.swing.*;
import java.awt.*;

public class Main extends JPanel {
    final int BF_WIDTH = 450;
    final int BF_HEIGHT = 450;
    final int TANK_WIDTH = 50;
    final int TANK_HIGHT = 50;
    final int TANK_SPEED = 5;
    final int BULLET_SPEED = 4;
    final int obstSize = 50;
    final int UP = 1;
    final int DOWN = 2;
    final int LEFT = 3;
    final int RIGHT = 4;


    //Direction: 1- Up, 2 - Down, 3 - Left, 4 - Right

    int direction = 2;
    int tankX = 250;
    int tankY = 250;

    int bulletX = 0;
    int bulletY = 0;
    String[][] obst = {{"B", "W", "B", "G", "G", "W", "B", "G", "B"},
                       {"B", "W", "B", "G", "G", "B", "B", "G", "B"},
                       {"G", "W", "W", "G", "G", "W", "G", "G", "W"},
                       {"B", "B", "B", "G", "B", "W", "B", "W", "B"},
                       {"B", "W", "B", "G", "G", "B", "B", "W", "B"},
                       {"G", "W", "B", "W", "G", "W", "G", "G", "B"},
                       {"W", "W", "B", "W", "B", "W", "B", "G", "W"},
                       {"B", "W", "B", "G", "G", "W", "B", "G", "B"},
                       {"B", "W", "B", "W", "B", "W", "G", "G", "W"}

    };
    boolean cantMove() {
        return (direction == UP && tankY == 0) || (direction == DOWN && tankY == BF_HEIGHT - TANK_HIGHT)
    || (direction == LEFT && tankX == 0) || (direction == RIGHT && tankX == BF_WIDTH-TANK_WIDTH);
    }
    boolean processInterception(){
        int y = bulletY/50;
        int x = bulletX/50;
        if (obst[y][x].equals("B")){
            obst[y][x] = "G";
            return true;
        }
        return false;
    }

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
                if (processInterception()){
                    destroyBullet();

            }
            Thread.sleep(BULLET_SPEED);
            repaint();
        }
        destroyBullet();



    }
    void destroyBullet(){
        bulletX = -100;
        bulletY = -100;
        repaint();

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
           while(true) {
               fire();
           }
//        while (tankX > 0) {
//            move(LEFT);
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
        for (int i = 0; i < obst.length; i++) {
            for (int j = 0; j < obst.length; j++) {


                if (obst[j][i].equals("B")) {
                    g.setColor(new Color(147, 71, 18, 231));

                } else if (obst[j][i].equals("G")) {
                    g.setColor(new Color(96, 105, 108, 231));
                } else if (obst[j][i].equals("W")) {
                    g.setColor(new Color(34, 175, 190, 231));

                }
                g.fillRect(i * TANK_HIGHT, j*TANK_WIDTH, obstSize, obstSize);
            }
//            int x = i;


        }

        //draw tank
        g.setColor(Color.GREEN);
        g.fillRect(tankX, tankY, TANK_WIDTH, TANK_HIGHT);

        //draw gun
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
        //draw bullet
        g.setColor(Color.RED);
        g.fillRect(bulletX, bulletY, 12, 12);
    }
}
