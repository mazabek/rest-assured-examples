package com.testinglaboratory.restassured.foundations.simple.crud;

import com.github.javafaker.Faker;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.restassured.response.ResponseBodyExtractionOptions;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Locale;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.in;

@Slf4j
public class AlterHumanTest {

    @Test
    public void putNewHumanInPlace() {
        JSONObject human = given()
                .get("/human/1")
                .then()
                .statusCode(in(List.of(200, 307)))
                .log().everything()
                .extract().body().as(JSONObject.class);

        Faker fake = new Faker(new Locale("PL_pl"));
        String firstName = fake.name().firstName();
        String lastName = fake.name().lastName();
        JSONObject newHuman = new JSONObject();
        newHuman.put("first_name", firstName);
        newHuman.put("last_name", lastName);
        log.info(newHuman.toString());

        ResponseBodyExtractionOptions body = given()
                .header("Content-Type", "application/json")
                .when()
                .body(newHuman.toMap())
                .put("/human/1")
                .then()
                .statusCode(202)
                .log().everything().extract().body();

        log.info(body.jsonPath().getString("."));

        JSONObject alteredHuman = given()
                .header("Accept", "application/json")
                .get("/human/1")
                .then()
                .statusCode(200)
                .log().everything()
                .extract().body().as(JSONObject.class);

        assert human.toString().equals(alteredHuman.toString());
    }

    @Test
    public void changeBridesMaidenName() {
        Integer humanId = 2;
        JsonObject bride = given()
                .basePath("/human/{humanId}")
                .pathParam("humanId", humanId)
                .get()
                .then()
                .statusCode(in(List.of(200, 307)))
                .log().everything()
                .extract().body().as(JsonObject.class);

        Faker fake = new Faker(new Locale("PL_pl"));
        String lastName = fake.name().lastName();
        JSONObject alteration = new JSONObject();
        alteration.put("last_name", lastName);
        log.info("Alteration " + alteration.toString());

        ResponseBodyExtractionOptions body = given()
                .header("Content-Type", "application/json")
                .body(alteration.toMap())
                .basePath("/human/{humanId}")
                .pathParam("humanId", humanId)
                .when()
                .patch()
                .then()
                .statusCode(202)
                .log().everything().extract().body();

        log.info(body.jsonPath().getString("."));

        JsonObject wife = given()
                .basePath("/human/{humanId}")
                .pathParam("humanId", humanId)
                .get()
                .then()
                .statusCode(in(List.of(200, 307)))
                .log().everything()
                .extract().body().as(JsonObject.class);

        assert bride.getAsJsonObject("human").get("first_name").getAsString()
                .equals(wife.getAsJsonObject("human").get("first_name").getAsString());
        assert !bride.getAsJsonObject("human").get("last_name").getAsString()
                .equals(wife.getAsJsonObject("human").get("last_name").getAsString());
        assert alteration.get("last_name")
                .equals(wife.getAsJsonObject("human").get("last_name").getAsString());
        assert !alteration.get("last_name")
                .equals(bride.getAsJsonObject("human").get("last_name").getAsString());
    }
}
