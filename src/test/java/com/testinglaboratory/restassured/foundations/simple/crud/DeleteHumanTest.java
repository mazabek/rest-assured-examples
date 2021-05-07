package com.testinglaboratory.restassured.foundations.simple.crud;

import com.github.javafaker.Faker;
import com.google.common.collect.Iterables;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.in;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

@Slf4j
public class DeleteHumanTest {
    private Integer humanId;

    @BeforeEach
    public void setUp() {
        Faker fake = new Faker(new Locale("PL_pl"));
        String firstName = fake.name().firstName();
        String lastName = fake.name().lastName();
        JSONObject human = new JSONObject();
        human.put("first_name", firstName);
        human.put("last_name", lastName);
        log.info(human.toString());
        String message = given()
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .body(human)
                .post("/human/")
                .then()
                .log().everything()
                .assertThat()
                .body(containsString(firstName))
                .body(containsString(lastName))
                .extract().body().jsonPath()
                .getString("message");
        humanId = Integer.parseInt(
                Iterables.getLast(
                        Arrays.stream(message.split(" "))
                                .collect(Collectors.toList()))
        );
    }

    @Test
    public void deleteHuman() {
        given()
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .pathParam("humanId", humanId)
                .delete("/human/{humanId}")
                .then()
                .statusCode(202)
                .log().everything();

        given()
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .pathParam("humanId", humanId)
                .get("/human/{humanId}")
                .then()
                .statusCode(200)
                .assertThat()
                .body("human", is(nullValue()))
                .log().everything();


    }
}
