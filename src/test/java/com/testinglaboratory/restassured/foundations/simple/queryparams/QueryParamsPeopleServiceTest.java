package com.testinglaboratory.restassured.foundations.simple.queryparams;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Slf4j
public class QueryParamsPeopleServiceTest {

    @DisplayName("Shameful test") //nazwa niezależna od metody
    @Tag("Known Issue")
    @Test
    @Disabled
    public void shouldGreetPersonWithFirstNameAndLastName() {
        given().queryParam("first_name", "Tomasz")
                .queryParam("last_name", "Kowalski")
                .when()
                .get("/query_params")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("Greeting", equalTo("Hello, Andrzej Kowalski!"));
    }

    @Test
    public void shouldGreetPersonWithFullName() {
        given().queryParam("first_name", "Tomasz")
                .queryParam("last_name", "Kowalski")
                .when()
                .get("/query_params")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("Greeting", equalTo("Hello, Tomasz Kowalski!"));
    }

    @Test
    public void shouldOverwriteDuplicatedParam() {
        given().queryParam("first_name", "Stefan")
                .queryParam("last_name", "Jaracz")
                .queryParam("last_name", "Madafaka")
                .when()
                .get("/query_params")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("Greeting", equalTo("Hello, Stefan Madafaka!"));
    }


    @Test
    public void shouldIncludeProvidedMiddleNameWithGreeting() {
        Faker fake = new Faker(new Locale("PL_pl"));
        String firstName = fake.name().firstName();
        String middleName = fake.name().firstName();
        String lastName = fake.name().lastName();
        log.info(firstName);
        log.info(middleName);
        log.info(lastName);
        given().queryParam("first_name", firstName)
                .queryParam("last_name", lastName)
                .queryParam("middle_name", middleName)
                .when()
                .get("/query_params")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("Greeting", equalTo(
                        String.format("Hello, %s %s %s!", firstName, middleName, lastName)))
                .log().everything();
    }

    @Test
    public void shouldReturnListOfAllPeople() {
        Response response = when()
                .get("/get_all_people")
                .then().log().everything().extract().response();
        Map<String, Map<String, String>> people = response.body().jsonPath().getMap(".");
        log.info(String.valueOf(people));
        assertThat(people)
                .as("People of this beautiful country")
                .hasSizeGreaterThanOrEqualTo(1000);
    }
}
