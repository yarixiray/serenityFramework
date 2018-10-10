package utils;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class TestUtils {

    public static String getRandomValue() {
        Random random = new Random();
        int randomInt = random.nextInt();
        return Integer.toString(randomInt);
    }

    public static JSONArray mappingJsonToArray(String filePath) {

        JSONArray jsonArray = null;
        JSONParser parser = new JSONParser();

        Object obj = null;
        try {
            obj = parser.parse(new FileReader(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        jsonArray = (JSONArray) obj;

        return jsonArray;
    }
}
