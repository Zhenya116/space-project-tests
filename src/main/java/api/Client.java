package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.with;

public abstract class Client {
    protected RequestSpecification spec;

    protected Client(String baseUri) {
        this.spec = new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .addFilter(AllureRest.filter())
                .build();
    }

    protected RequestSpecification given() {
        return with().spec(spec);
    }
}
