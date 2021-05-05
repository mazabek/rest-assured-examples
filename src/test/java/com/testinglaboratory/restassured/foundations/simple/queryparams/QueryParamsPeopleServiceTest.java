package com.testinglaboratory.restassured.foundations.simple.queryparams;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Slf4j
public class QueryParamsPeopleServiceTest {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @DisplayName("Shameful test")
    @Tag("Known Issue")
    @Test
    public void getPersonFailing() {
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
    public void getPersonPassing() {
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
    public void getPersonDuplicatingLastNameParam() {
        given().queryParam("first_name", "Stefan")
                .queryParam("last_name", "Jaracz")
                .queryParam("last_name", "Madafaka")
                .when()
                .get("/query_params")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("Greeting", equalTo("Hello, Stefan Jaracz!"));
    }


    @Test
    public void getPersonWithMiddleName() {
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

}
