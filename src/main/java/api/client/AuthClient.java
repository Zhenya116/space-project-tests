package api.client;

import api.models.AuthRequest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class AuthClient {
    private final String baseUri;

    public AuthClient(String baseUri) {
        this.baseUri = baseUri;
    }

    public Response login(AuthRequest body) {
        return given()
                .baseUri(baseUri)
                .contentType(JSON)
                .body(body)
                .when()
                .post("/auth/login");
    }
}
