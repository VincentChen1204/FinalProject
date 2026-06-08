import javax.imageio.ImageIO;
import javax.swing.JPanel;
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
    private BufferedImage background;
    private BufferedImage playButton;
    private int ballSpawnX;
    private int ballSpawnY;
    public DisplayPanel() {
        score = 0;
        try {
            background = ImageIO.read(new File("Background.webp"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            playButton = ImageIO.read(new File("Play button.jpg"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        //Randomizes the coordinates and placement of the target ball
        ballSpawnX = (int) (Math.random() * 500) + 50;
        ballSpawnY = (int) (Math.random() * 400) + 50;
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        }
        //AI produced - Google
        //Draws a red circle on the display panel
        g.setColor(java.awt.Color.RED);
        g.fillOval(ballSpawnX, ballSpawnY, 50, 50);
    }

    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            Point clickLocation = e.getPoint();
            int x = clickLocation.x;
            int y = clickLocation.y;
            repaint();
        }
    }

    //AI helped produce - Google
    //Creates a hitbox for the target so the program knows when the player has hit the target
    public void targetHitboxHit(MouseEvent e, Graphics g) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            Point clickLocation = e.getPoint();
            int x = clickLocation.x;
            int y = clickLocation.y;
            if ((x > ballSpawnX - 50) && (x < ballSpawnX + 50)) {
                if ((y > ballSpawnY -50) && (y < ballSpawnY + 50)) {
                    score++;
                    g.setColor(java.awt.Color.RED);
                    g.fillOval(ballSpawnX, ballSpawnY, 50, 50);
                    repaint();
                }
            }
        }
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

    @Override
    public void keyPressed(KeyEvent e) {

    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
