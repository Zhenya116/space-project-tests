package tests;

import api.client.SpacesClient;
import io.qameta.allure.AllureId;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class SpaceTests {

    private static SpacesClient client;

    @BeforeAll
    static void init() {
        String baseUri = System.getProperty("baseURI", "http://localhost:8080");
        client = new SpacesClient(baseUri);
    }

    @Test @DisplayName("1) getSpaceById_schemaOk")
    @AllureId("SPC-1")
    void getSpaceById_schemaOk() {
        given().baseUri(System.getProperty("baseURI", "http://localhost:8080"))
                .accept("application/json")
                .pathParam("id", 123)
                .when().get("/spaces/{id}")
                .then().statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/space.json"));
    }

    @Test @DisplayName("2) listSpaces_paginationOk")
    @AllureId("SPC-2")
    void listSpaces_paginationOk() {
        String body = client.listSpaces(1, 2);
        Assertions.assertTrue(body.contains("\"page\": 1"));
        Assertions.assertTrue(body.contains("\"size\": 2"));
    }

    @Test @DisplayName("3) getSpace_notFound_404")
    @AllureId("SPC-3")
    void getSpace_notFound_404() {
        given().baseUri(System.getProperty("baseURI", "http://localhost:8080"))
                .when().get("/spaces/900000")
                .then().statusCode(404);
    }
}
