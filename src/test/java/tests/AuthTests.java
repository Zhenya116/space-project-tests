package tests;

import api.helper.AuthHelper;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

@Epic("Auth")
@Feature("Login")
public class AuthTests {
    private final String base = "http://localhost:8080";

    @Test
    @DisplayName("loginOk")
    void loginOk() {
        new AuthHelper(base)
                .loginOk()
                .then()
                .statusCode(200)
                .body("token", notNullValue());
    }

    @Test
    @DisplayName("loginWrongCredentials401")
    void loginWrongCredentials401() {
        new api.client.AuthClient(base)
                .login(new api.models.AuthRequest("qa","bad"))
                .then()
                .statusCode(401);
    }

    @Test
    @DisplayName("loginMalformedJson400")
    void loginMalformedJson400() {
        new api.client.AuthClient(base)
                .login(new api.models.AuthRequest(null,null))
                .then()
                .statusCode(anyOf(is(400), is(401)));
    }
}
