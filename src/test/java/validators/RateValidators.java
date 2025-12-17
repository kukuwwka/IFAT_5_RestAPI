package validators;

import api.ApiTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertTrue;

public class RateValidators extends ApiTest {
    public void validateSchema() {
        given()
                .when()
                .get(baseUrl + id)
                .then()
                .body(matchesJsonSchemaInClasspath("schemas/rate_schema.json"));
    }

    public void validateHeaders() {
        given()
                .when()
                .get(baseUrl + id)
                .then()
                .header("Content-Type", containsString("application/json"));
    }

    public void validateKeys() {
        given()
                .when()
                .get(baseUrl + id)
                .then()
                .body("$", hasKey("firstname"))
                .body("$", hasKey("totalprice"))
                .body("bookingdates", hasKey("checkin"))
                .body("bookingdates.checkin", equalTo("2018-01-01"));
    }

    public void validateDateRegex(String responseBody) {
        String regex = "[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(responseBody);
        assertTrue(matcher.find(), "");
    }
}