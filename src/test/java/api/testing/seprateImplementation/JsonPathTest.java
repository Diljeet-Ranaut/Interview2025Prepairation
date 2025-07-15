package api.testing.seprateImplementation;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertNotNull;

public class JsonPathTest {
    @Test
    public void extractFirstName() {
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("x-api-key", "reqres-free-v1")
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .extract().response();

        // ✅ Extract second user's first name
        String firstName = response.jsonPath().getString("data[1].first_name");
        System.out.println("First Name of second user: " + firstName);

        assertNotNull(firstName);
    }

}
