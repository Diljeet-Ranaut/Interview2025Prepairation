package api.testing.seprateImplementation;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetUsersTest {
    @Test
    public void testGetUsers() {
        Response response = RestAssured
                .given()
                .header("x-api-key","reqres-free-v1")
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .extract().response();
        System.out.println("Response Body: " + response.asPrettyString());

        //Basic Assertion
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.asString().contains("total_pages"));
    }

    @Test(priority = 0)
    public void getUserList() {
        Response response = RestAssured
                .given()
                .header("x-api-key","reqres-free-v1")
                .queryParam("page", 2)
                .when()
                .get("https://reqres.in/api/users")
                .then()
                .statusCode(200)
                .extract().response();
        System.out.println("Response body: " + response.asPrettyString());
        Assert.assertTrue(response.asString().contains("total"));
    }

}
