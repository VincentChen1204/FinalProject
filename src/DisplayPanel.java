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
    private int ballSpawnX;
    private int ballSpawnY;
    public DisplayPanel() {
        score = 0;
        try {
            background = ImageIO.read(new File("Background.webp"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
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
