package api.helper;

import api.client.AuthClient;
import api.models.AuthRequest;
import io.restassured.response.Response;

public class AuthHelper {
    private final AuthClient authClient;

    public AuthHelper(String baseUri) {
        this.authClient = new AuthClient(baseUri);
    }

    public Response loginOk() {
        return authClient.login(AuthRequest.of("qa", "qa123"));
    }
}
