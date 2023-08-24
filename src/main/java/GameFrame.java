import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        setTitle("City Game");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        GamePanel gamePanel = new GamePanel();
        add(gamePanel);

        setVisible(true);
    }
}
