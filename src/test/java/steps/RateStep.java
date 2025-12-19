package steps;

import api.ApiTest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RateStep extends ApiTest {
    public Response getResponse() {
        return given()
                .log().all()
                .when()
                .get(baseUrl + id)
                .then()
                .log().all()
                .statusCode(200)
/*                .body("firstname", equalTo("Jim"))
                .body("totalprice", equalTo(111))
                .body("bookingdates.checkin", equalTo("2018-01-01"))*/
                .extract()
                .response();
    }
}