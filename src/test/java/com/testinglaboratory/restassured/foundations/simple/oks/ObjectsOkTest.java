package com.testinglaboratory.restassured.foundations.simple.oks;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;

/**
 * as per living documentation in OpenApi
 * valid response is:
 * {
 * "basicInformation": "This is GET example for status code 200",
 * "responseInformation": {
 * "shortDescription": "OK",
 * "longDescription": "The request has succeeded"
 * },
 * "timestamp": "2021-05-04 08:14:34.778895"
 * }
 */
public class ObjectsOkTest {

    private Response response;

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @BeforeEach
    public void methodSetUp() {
        response = when()
                .get("/ok");
    }

    @Test
    public void checkStatus() {
        response.then()
                .statusCode(not(404))
                .statusCode(not(500))
                .statusCode(200);
    }

    @Test
    public void checkBodyFieldBasicInformation() {
        response
                .then()
                .body("basicInformation",
                        equalTo("This is GET example for status code 200")
                );
    }

    @Test
    public void extractingPartOfBody() {
        response
                .then()
                .body("basicInformation", equalTo("This is GET example for status code 200"),
                        "responseInformation.shortDescription", equalTo("OK"),
                        "responseInformation.longDescription", equalTo("The request has succeeded")
                )
                .time(lessThan(1000L));
        LocalDateTime dateTime = LocalDateTime.parse(
                response.body().jsonPath().getString("timestamp")
                        .replace(" ", "T")
        );

        assertThat(dateTime)
                .as("Time of response")
                .isGreaterThan(
                        new LocalDateTime(DateTimeZone.forOffsetHours(-2)).minusMinutes(1)
                );

    }
}
