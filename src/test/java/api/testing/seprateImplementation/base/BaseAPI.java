package api.testing.seprateImplementation.base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseAPI {
    @BeforeClass
    public void setupBaseURI() {
        RestAssured.baseURI = "https://reqres.in";
    }
}
