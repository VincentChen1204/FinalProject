import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DisplayPanel extends JPanel implements MouseListener, KeyListener {
    private int score;
    private boolean gameStarted;
    private boolean gameEnded;
    private BufferedImage background;
    private int targetSpawnX;
    private int targetSpawnY;
    private int targetRadius = 50;
    private int buttonWidth = 200;
    private int buttonHeight = 100;
    private int buttonX;
    private int buttonY;

    public DisplayPanel() {
        score = 0;

        try {
            background = ImageIO.read(new File("Background.webp"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        //Randomizes the coordinates and placement of the target
        targetSpawnX = (int) (Math.random() * 400) + 50;
        targetSpawnY = (int) (Math.random() * 400) + 50;
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Draws in the background of the display panel
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        }

        //Produced by AI - ChatGPT
        buttonX = (getWidth() - buttonWidth) / 2;
        buttonY = (getHeight() - buttonHeight) / 2;

        if ((!gameStarted) && (!gameEnded)) {
            //Draws the play button at the start to begin user interaction
            g.setColor(Color.RED);
            g.drawRect(buttonX, buttonY, buttonWidth, buttonHeight);
            g.drawString("PLAY", buttonX + 85, buttonY + 55);

            //Draws the title of the program (Aim Trainer)
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("AIM TRAINER!", 255, 170);
        }

        //Keeps track of the score or number of targets hit by the user
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.drawString("Score: " + score, 25, 25);

        //Ai provided ".fillOval()" - Google
        //Draws a red circle onto the display panel as the target
        if (gameStarted) {
            g.setColor(Color.RED);
            g.fillOval(targetSpawnX, targetSpawnY, targetRadius, targetRadius);
        }

        if (gameEnded) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("GAME FINISHED" , 240, 175);
            g.setFont(new Font("Arial", Font.BOLD, 25));
            g.drawString("Score: " + score, 345, 315);
        }
    }

    //Determines whether the user clicked within the area of the target or not
    public void targetHitbox(MouseEvent e) {
        if (gameStarted) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                Point clickLocation = e.getPoint();
                int x = clickLocation.x;
                int y = clickLocation.y;
                if (x > targetSpawnX) {
                    x -= targetSpawnX;
                } else {
                    x = targetSpawnX - x;
                }
                if (y > targetSpawnY) {
                    y -= targetSpawnY;
                } else {
                    y = targetSpawnY - y;
                }
                if (((x * x) + (y * y)) <= (targetRadius * targetRadius)) {
                    score++;
                    respawnTarget();
                    repaint();
                }
            }
        }
    }

    //After the user successfully hits the target, it will respawn again at a randomized point
    private void respawnTarget() {
        targetSpawnX = (int) ((Math.random() * 700) + 50);
        targetSpawnY = (int) ((Math.random() * 467) + 33);
    }

    //Has the user click on the play button before playing
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            Point clickLocation = e.getPoint();
            int x = clickLocation.x;
            int y = clickLocation.y;
            if (x >= buttonX && x <= buttonX + buttonWidth && y >= buttonY && y <= buttonY + buttonHeight) {
                gameStarted = true;
                repaint();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    //Calls the targetHitbox method
    @Override
    public void mouseReleased(MouseEvent e) {
        targetHitbox(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //Ends the game and shows the resulting score
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            gameStarted = false;
            gameEnded = true;
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
