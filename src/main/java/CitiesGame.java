import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class CitiesGame extends JFrame implements ActionListener {
    private JTextField inputField;
    private JLabel computerLabel;
    private JButton moveButton;
    private ArrayList<String> cities;
    private HashSet<String> usedCities;
    private String lastCity;

    public CitiesGame() {
        setTitle("City Game");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        inputField = new JTextField();
        computerLabel = new JLabel("Enter a city to start");
        moveButton = new JButton("Make a move");
        moveButton.addActionListener(this);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.add(inputField);
        panel.add(computerLabel);
        panel.add(moveButton);

        add(panel);

        cities = new ArrayList<>();
        loadCitiesFromFile(); // Завантаження міст із текстового файлу

        usedCities = new HashSet<>();

        setVisible(true);
    }

    private void loadCitiesFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("cities.txt"), "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                cities.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = inputField.getText().trim();

        if (usedCities.contains(input)) {
            JOptionPane.showMessageDialog(this, "This city has already been used. Try another city.");
            return;
        }

        if (lastCity != null && !input.startsWith(lastCity.substring(lastCity.length() - 1).toUpperCase())) {
            JOptionPane.showMessageDialog(this, "The city should start with the last letter of the previous city. Try another city.");
            return;
        }

        usedCities.add(input);

        String nextCity = "";
        for (String city : cities) {
            if (!usedCities.contains(city) && city.startsWith(input.substring(input.length() - 1).toUpperCase())) {
                nextCity = city;
                break;
            }
        }

        if (nextCity.isEmpty()) {
            JOptionPane.showMessageDialog(this, "You win! The computer can't find the next city.");
            System.exit(0);
            return;
        }

        computerLabel.setText(nextCity);
        usedCities.add(nextCity);
        lastCity = nextCity;

        inputField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CitiesGame());
    }
}
