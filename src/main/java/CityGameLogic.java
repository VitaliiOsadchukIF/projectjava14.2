import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class CityGameLogic {
    private ArrayList<String> cities;
    private HashSet<String> usedCities;
    private String lastCity;

    public CityGameLogic() {
        cities = new ArrayList<>();
        loadCitiesFromFile();
        usedCities = new HashSet<>();
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

    public String makeMove(String input) {
        if (usedCities.contains(input)) {
            return "This city has already been used. Try another city.";
        }

        if (lastCity != null && !input.startsWith(lastCity.substring(lastCity.length() - 1).toUpperCase())) {
            return "The city should start with the last letter of the previous city. Try another city.";
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
            return "You win! The computer can't find the next city.";
        }

        lastCity = nextCity;
        usedCities.add(nextCity);

        return nextCity;
    }
}
//
