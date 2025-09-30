package api.client;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class SpacesClient {

    private final String baseUri;

    public SpacesClient(String baseUri) {
        this.baseUri = baseUri;
    }

    @Step("GET /spaces/{id}")
    public String getSpaceById(int id) {
        return given()
                .filter(new AllureRestAssured())
                .baseUri(baseUri)
                .accept(ContentType.JSON)
                .pathParam("id", id)
                .when()
                .get("/spaces/{id}")
                .then()
                .extract().asString();
    }

    @Step("GET /spaces?page={page}&size={size}")
    public String listSpaces(int page, int size) {
        return given()
                .filter(new AllureRestAssured())
                .baseUri(baseUri)
                .accept(ContentType.JSON)
                .queryParam("page", page)
                .queryParam("size", size)
                .when()
                .get("/spaces")
                .then()
                .extract().asString();
    }
}
