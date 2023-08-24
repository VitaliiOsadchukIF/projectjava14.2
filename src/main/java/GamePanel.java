import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {
    private JTextField inputField;
    private JLabel computerLabel;
    private JButton moveButton;
    private CityGameLogic gameLogic;

    public GamePanel() {
        setLayout(new GridLayout(3, 1));

        inputField = new JTextField();
        computerLabel = new JLabel("Enter a city to start");
        moveButton = new JButton("Make a move");

        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText().trim();
                String computerResponse = gameLogic.makeMove(input);

                computerLabel.setText(computerResponse);
                inputField.setText("");
            }
        });

        add(inputField);
        add(computerLabel);
        add(moveButton);

        gameLogic = new CityGameLogic();
    }
}
