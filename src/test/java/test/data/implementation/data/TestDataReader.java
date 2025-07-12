package test.data.implementation.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import test.data.implementation.model.LoginData;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestDataReader {
    public static List<LoginData> getLoginTestData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream is = TestDataReader.class
                .getClassLoader()
                .getResourceAsStream("testdata/loginData.json");
        {
            return objectMapper.readValue(is, new TypeReference<>() {
            });
        }
    }
}
