package api.testing.seprateImplementation.utils;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.util.Arrays;

public class JsonReader {
    @DataProvider(name = "userData")
    public static Object[][] readJson() throws Exception {
        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(new FileReader("src/test/java/api/testing/seprateImplementation/data/UserData.json"));

        Object[][] testData = new Object[array.size()][2];
        System.out.println(STR."Array size check:\{Arrays.deepToString(testData)}");

        for (int i = 0; i < array.size(); i++) {
            JSONObject user = (JSONObject) array.get(i);
            testData[i][0] = user.get("name");
            testData[i][1] = user.get("job");
        }

        return testData;
    }
}