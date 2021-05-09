package com.testinglaboratory.restassured.foundations.simple.queryparams;

//TODO exercise query parameters

import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Create tests for:
 * /get_all_people_sliced
 * /get_all_people_paged
 * /get_all_people_by
 * endpoints
 */
@Slf4j
public class ExerciseQueryParamsPeopleServiceTest {
    @Test
    public void shouldReturnPeopleSliced() {
        Response response = given()
                .queryParam("from_number", 2)
                .queryParam("up_to_number", 10)
                .when()
                .get("/get_all_people_sliced")
                .then()
                .log().everything()
                .extract().response();
        List<Map<String, String>> people = response.body().jsonPath().getList(".");
        log.info(String.valueOf(people));
        assertThat(people)
                .hasSize(8);
    }

    @Test
    public void shouldReturnPeoplePaged(){
        Response response = given()
                .queryParam("page_size", 2)
                .queryParam("page_number", 3)
                .when()
                .get("/get_all_people_paged")
                .then()
                .log().everything()
                .extract().response();
        List<Map<String, String>> people = response.body().jsonPath().getList(".");
        log.info(String.valueOf(people));
        assertThat(people)
                .hasSize(2);
    }
//    @Test
//    public void shouldReturnPeople(){
//        Response response = given()
//                .queryParam("first_name", "Jerzy")
//                .queryParam("last_name", "Strzała")
//                .when()
//                .get("/get_all_people_by")
//                .then()
//                .log().everything()
//                .extract().response();
//        List<Map<String, String>> people = response.body().jsonPath().getList(".");
//        log.info(String.valueOf(people));
//        assertThat(people)
//                .
//    }


    }


