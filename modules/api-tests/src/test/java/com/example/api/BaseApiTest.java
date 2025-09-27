package com.example.api;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.filters;
import io.qameta.allure.restassured.AllureRestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public abstract class BaseApiTest {

    @BeforeAll
    static void setup() {
        String base = com.example.common.config.ConfigFactory.cfg().apiBaseUrl();
        RestAssured.baseURI = base;
        enableLoggingOfRequestAndResponseIfValidationFails();
        System.out.printf(">>> env=%s, baseURI=%s%n",
                System.getProperty("env", "test"), RestAssured.baseURI);
    }

    @Step("Log pretty response")
    protected void attach(io.restassured.response.Response resp) {
        io.qameta.allure.Allure.addAttachment(
                "Response", "application/json", resp.asPrettyString(), ".json");
    }

    @BeforeAll
    public static void setUp() {
        baseURI = System.getProperty("baseURI", "http://localhost:8080");
        filters(new AllureRestAssured());
    }
}
