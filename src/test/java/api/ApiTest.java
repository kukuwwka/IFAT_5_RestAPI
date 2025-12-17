package api;

import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertTrue;

public class ApiTest {
    public String baseUrl = "https://restful-booker.herokuapp.com/booking/";
    public int id = 303;

    @Test
    public void checkOnlinerRates() {
        String responseBody = given()
                .log().all()
        .when()
                .get(baseUrl + id)
        .then()
                .log().all()
                .statusCode(200)
                .body("firstname", equalTo("Jim"))
                .body("totalprice", equalTo(111))
                .body("bookingdates.checkin", equalTo("2018-01-01"))
                .extract()
                .body().asString();
        System.out.println(responseBody + "QQQQQQQQQQQQQQQ");
        System.out.println(responseBody.toUpperCase() + "QQQQQQQQQQQQQQQ");

        String regex = "[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(responseBody);
        assertTrue(matcher.find(), "");
    }
}