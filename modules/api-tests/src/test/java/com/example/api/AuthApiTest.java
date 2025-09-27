package com.example.api;

import io.qameta.allure.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("Auth")
@Feature("Login")
@Severity(SeverityLevel.CRITICAL)
class AuthApiTest extends BaseApiTest {

    @Test
    @Story("Успешный логин")
    void login_ok() {
        Response resp =
                given()
                        .contentType(ContentType.JSON)
                        .body("{\"username\":\"qa\",\"password\":\"qa123\"}")
                        .when()
                        .post("/auth/login")
                        .then()
                        .statusCode(200)
                        .body("token_type", equalTo("Bearer"))
                        .body("access_token", not(isEmptyOrNullString()))
                        .body("expires_in", greaterThan(0))
                        .extract().response();

        attach(resp);
    }

    @ParameterizedTest(name = "Неверные креды -> 401 [{index}] user={0}")
    @CsvSource({ "qa, wrong", "qa, 123", "admin, xxx" })
    @Story("Неверные креды")
    void login_wrong_credentials_401(String user, String pwd) {
        given()
                .contentType(ContentType.JSON)
                .body(String.format("{\"username\":\"%s\",\"password\":\"%s\"}", user, pwd))
                .when().post("/auth/login")
                .then().statusCode(401);
    }

    @Test
    @Story("Битый JSON -> 400")
    void login_malformed_json_400() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"username\":\"qa\",\"password\":")
                .when().post("/auth/login")
                .then().statusCode(400);
    }
}
