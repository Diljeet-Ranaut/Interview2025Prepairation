package api.testing.seprateImplementation;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserTest_Delete {
    @Test
    public void deleteUser(){
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("x-api-key", "reqres-free-v1")
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204)
                .extract().response();

    }
}
