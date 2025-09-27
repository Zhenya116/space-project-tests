package com.example.api;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.InputStream;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;


@Epic("Spaces")
@Feature("Read")
class SpaceApiTest extends BaseApiTest {

    @Test
    void get_space_by_id_schema_ok() {
        // 1) загрузим схему руками с classpath
        InputStream schema = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("schemas/space.json");

        // 2) если не нашли — сразу понятная ошибка
        assertThat("schemas/space.json must be on test classpath", schema, notNullValue());

        // 3) используем поток схемы в матчерах
        given()
                .when().get("/spaces/123")
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(schema))
                .body("id", equalTo(123))
                .body("name", startsWith("Space #"));
    }

    @Test
    @Story("Несуществующий id -> 404")
    void get_space_not_found_404() {
        given().accept(JSON)
                .when().get("/spaces/900000")
                .then().statusCode(404);
    }

    @ParameterizedTest(name = "Пагинация: page={0}, size={1}")
    @CsvSource({ "0, 2", "1, 2", "3, 5" })
    @Story("Пагинация (динамический ответ WireMock)")
    void list_spaces_pagination_ok(int page, int size) {
        given().accept(JSON)
                .queryParam("page", page)
                .queryParam("size", size)
                .when().get("/spaces")
                .then()
                .statusCode(200)
                .body("page", equalTo(page))
                .body("size", equalTo(size))
                .body("items.size()", greaterThanOrEqualTo(2))
                .body("total", equalTo(42));
    }
}
